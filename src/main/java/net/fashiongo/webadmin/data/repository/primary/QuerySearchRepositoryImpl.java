package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.SimplePath;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.sql.JPASQLQuery;
import com.querydsl.sql.SQLExpressions;
import net.fashiongo.webadmin.data.entity.primary.QWebAdminSearchQuerySearchIDEntity;
import net.fashiongo.webadmin.data.entity.primary.QWebAdminSearchQueryUserNameEntity;
import net.fashiongo.webadmin.data.model.statistics.HotSearch;
import net.fashiongo.webadmin.utility.MSSQLServer2012Templates;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class QuerySearchRepositoryImpl implements QuerySearchRepository {
    @PersistenceContext(unitName = "primaryEntityManager")
    private EntityManager entityManager;


    @Override
    @Transactional
    public List<HotSearch> getHotSearch(Integer top, LocalDateTime fromDate, LocalDateTime toDate, String orderBy, String searchfield, String searchkeyword) {
        String searchQuery = "";
        int rank = 0;
        int frequency = 0;

        if (searchfield.equals("keyword")) {
            searchQuery = searchkeyword;
        } else if (searchfield.equals("rank")) {
            if (StringUtils.isNotEmpty(searchkeyword))
                rank = Integer.parseInt(searchkeyword);
        } else if (searchfield.equals("frequency")) {
            if (StringUtils.isNotEmpty(searchkeyword))
                frequency = Integer.parseInt(searchkeyword);
        }

        JPASQLQuery<HotSearch> query = new JPASQLQuery<HotSearch>(entityManager, new MSSQLServer2012Templates());
        JPASQLQuery subquery = new JPASQLQuery(entityManager, new MSSQLServer2012Templates());

        Expression<Integer> constant = Expressions.constant(1);
        BooleanExpression filter = Expressions.asNumber(1).eq(constant);

        if (orderBy.equals("UniqueUser")) {
            QWebAdminSearchQueryUserNameEntity UN = new QWebAdminSearchQueryUserNameEntity("UN");

            if (fromDate != null) {
                filter = filter.and(UN.searchedOn.goe(fromDate));
            }
            if (toDate != null) {
                filter = filter.and(UN.searchedOn.loe(toDate));
            }
            if (StringUtils.isNotEmpty(searchQuery)) {
                filter = filter.and(UN.searchQuery.contains(searchQuery));
            }

            NumberPath<Long> pathUN_rankno = Expressions.numberPath(Long.class, "rankno");

            subquery.select(
                    UN.searchQuery,
                    UN.userNameCount.sum().as("CountSum"),
                    SQLExpressions.rank().over().orderBy(UN.userNameCount.sum().desc()).as("rankno")
            ).from(UN)
            .where(filter)
            .groupBy(UN.searchQuery)
            .orderBy(pathUN_rankno.asc())
            .offset(0).limit(top);
        } else {
            QWebAdminSearchQuerySearchIDEntity SI = QWebAdminSearchQuerySearchIDEntity.webAdminSearchQuerySearchIDEntity;

            if (fromDate != null) {
                filter = filter.and(SI.searchedOn.goe(fromDate));
            }
            if (toDate != null) {
                filter = filter.and(SI.searchedOn.loe(toDate));
            }
            if (StringUtils.isNotEmpty(searchQuery)) {
                filter = filter.and(SI.searchQuery.contains(searchQuery));
            }

            NumberPath<Long> pathSI_rankno = Expressions.numberPath(Long.class, "rankno");

            subquery.select(
                    SI.searchQuery,
                    SI.searchIDCount.sum().as("CountSum"),
                    SQLExpressions.rank().over().orderBy(SI.searchIDCount.sum().desc()).as("rankno")
            ).from(SI)
            .where(filter)
            .groupBy(SI.searchQuery)
            .orderBy(pathSI_rankno.asc())
            .offset(0).limit(top);
        }

        SimplePath<Object> pathX = Expressions.path(Object.class, "X");
        NumberPath<Long> pathX_rankno = Expressions.numberPath(Long.class, "rankno");
        NumberPath<Integer> pathX_count = Expressions.numberPath(Integer.class, "CountSum");
        StringPath pathX_SearchQuery = Expressions.stringPath("searchQuery");

        query.select(Projections.constructor(HotSearch.class,
                pathX_SearchQuery,
                pathX_count,
                pathX_rankno
        )).from(subquery, pathX);

        return query.fetch();
    }
}
