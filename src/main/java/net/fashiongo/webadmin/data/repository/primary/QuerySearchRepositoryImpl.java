package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.sql.JPASQLQuery;
import com.querydsl.sql.SQLExpressions;
import net.fashiongo.webadmin.data.entity.primary.QWebAdminSearchQuerySearchIDEntity;
import net.fashiongo.webadmin.data.entity.primary.QWebAdminSearchQueryUserNameEntity;
import net.fashiongo.webadmin.data.model.statistics.HotSearch;
import net.fashiongo.webadmin.utility.MSSQLServer2012Templates;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class QuerySearchRepositoryImpl implements QuerySearchRepository {
    @PersistenceContext(unitName = "primaryEntityManager")
    private EntityManager entityManager;


    @Override
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
            NumberPath<Integer> pathUN_count = Expressions.numberPath(Integer.class, "CountSum");

            if (frequency != 0) {
                filter = filter.and(pathUN_count.goe(frequency));
            }
            if (rank != 0) {
                filter = filter.and(pathUN_rankno.loe(0));
            }

            query.select(Projections.constructor(HotSearch.class,
                    UN.searchQuery,
                    UN.userNameCount.sum().as("CountSum"),
                    SQLExpressions.rank().over().orderBy(UN.userNameCount.sum().desc()).as("rankno")
                    ))
                    .from(UN)
                    .where(filter)
                    .groupBy(UN.searchQuery)
                    .having(UN.userNameCount.sum().goe(0))
                    .orderBy(pathUN_rankno.asc());

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
            NumberPath<Integer> pathSI_count = Expressions.numberPath(Integer.class, "CountSum");

            if (frequency != 0) {
                filter = filter.and(pathSI_count.goe(frequency));
            }
            if (rank != 0) {
                filter = filter.and(pathSI_rankno.loe(0));
            }

            query.select(Projections.constructor(HotSearch.class,
                    SI.searchQuery,
                    SI.searchIDCount.sum().as("CountSum"),
                    SQLExpressions.rank().over().orderBy(SI.searchIDCount.sum().desc()).as("rankno")
            ))
                    .from(SI)
                    .where(filter)
                    .groupBy(SI.searchQuery)
                    .having(SI.searchIDCount.sum().goe(0))
                    .orderBy(pathSI_rankno.asc());
        }

        query.offset(0).limit(top);

        return query.fetch();
    }
}
