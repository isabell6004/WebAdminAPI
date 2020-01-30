package net.fashiongo.webadmin.data.repository.stats;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.sql.JPASQLQuery;
import net.fashiongo.webadmin.data.entity.stats.QSearchEntity;
import net.fashiongo.webadmin.data.model.statistics.HotSearchKeyword;
import net.fashiongo.webadmin.utility.MSSQLServer2012Templates;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class SearchEntityRepositoryCustomImpl implements SearchEntityRepositoryCustom {
    @PersistenceContext(unitName = "statEntityManager")
    private EntityManager entityManager;

    @Override
    public List<HotSearchKeyword> getHotSearchKeyword(Integer periodType, LocalDateTime fromDate, LocalDateTime toDate, String keyword) {
        QSearchEntity search = new QSearchEntity("Search");
        JPASQLQuery<HotSearchKeyword> query = new JPASQLQuery<>(entityManager, new MSSQLServer2012Templates());

        Expression<Integer> constant = Expressions.constant(1);
        BooleanExpression filter = Expressions.asNumber(1).eq(constant);
        StringPath dt = Expressions.stringPath("dt");

        if (fromDate != null) {
            filter = filter.and(search.searchedOn.goe(fromDate));
        }
        if (toDate != null) {
            filter = filter.and(search.searchedOn.loe(toDate));
        }
        filter = filter.and(search.searchQuery.eq(keyword));

        query.select(Projections.constructor(HotSearchKeyword.class,
                Expressions.stringTemplate("convert(varchar(5),{0},101)", search.searchedOn).as("dt"),
                search.searchID.count().as("cnt")))
                .from(search)
                .where(filter)
                .groupBy(search.searchedOn)
                .orderBy(dt.asc());

        return query.fetch();
    }
}
