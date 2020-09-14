package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.sql.JPASQLQuery;
import com.querydsl.sql.SQLExpressions;
import lombok.RequiredArgsConstructor;
import net.fashiongo.webadmin.data.entity.primary.QStatisticsWaBestItemPerDayEntity;
import net.fashiongo.webadmin.data.model.statistics.BestItems;
import net.fashiongo.webadmin.data.repository.QueryDSLSQLFunctions;
import net.fashiongo.webadmin.utility.MSSQLServer2012Templates;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class StatisticsWaBestItemPerDayEntityRepositoryCustomImpl implements StatisticsWaBestItemPerDayEntityRepositoryCustom {

    @PersistenceContext(unitName = "primaryEntityManager")
    private EntityManager entityManager;

    final QueryDSLSQLFunctions queryDSLSQLFunctions;

    @Override
    public List<BestItems> selectBestItems(Integer pageNo, Integer pageSize, LocalDateTime fromDate, LocalDateTime toDate,
                                        Integer statisticsType, Integer lastCategoryID, Integer wholeSalerId) {

        QStatisticsWaBestItemPerDayEntity SWBI = new QStatisticsWaBestItemPerDayEntity("SWBI");
        JPASQLQuery<BestItems> query = new JPASQLQuery<>(entityManager, new MSSQLServer2012Templates());

        Expression<Integer> constant = Expressions.constant(1);
        BooleanExpression filter = Expressions.asNumber(1).eq(constant);

        BooleanExpression wholeSalerIDZero = SWBI.wholeSalerID.eq(wholeSalerId);
        if (wholeSalerId == 0) {
            wholeSalerIDZero = wholeSalerIDZero.or(filter);
        } else {
            wholeSalerIDZero = wholeSalerIDZero.or(filter.not());
        }

        BooleanExpression categoryIDNull;
        if (lastCategoryID == 0) {
            categoryIDNull = filter.or(SWBI.categoryID.isNull()).or(SWBI.parentCategoryID.isNull()).or(SWBI.parentParentCategoryID.isNull());
        } else {
            categoryIDNull = filter.not();
            categoryIDNull = categoryIDNull.or(SWBI.categoryID.eq(lastCategoryID)).or(SWBI.parentCategoryID.eq(lastCategoryID)).or(SWBI.parentParentCategoryID.eq(lastCategoryID));
        }

        OrderSpecifier<?> subOrderSpecifier = null;
        if (statisticsType == 1) {
            subOrderSpecifier = SWBI.orderAmount.sum().desc();
        } else if (statisticsType == 2) {
            subOrderSpecifier = SWBI.orderItemQty.sum().desc();
        }

        Date fromDateValue = Date.from(fromDate.atZone(ZoneId.systemDefault()).toInstant());
        Date toDateValue = Date.from(toDate.plusDays(1).atZone(ZoneId.systemDefault()).toInstant());

        query.select(
                Projections.constructor(BestItems.class,
                SQLExpressions.rowNumber().over().orderBy(subOrderSpecifier).as("RowNo"),
                SWBI.productID.as("ProductID"),
                SWBI.orderAmount.sum().as("TotAmt"),
                SWBI.orderItemQty.sum().as("TotQty")))
                .from(SWBI)
                .where(SWBI.startDate.goe(fromDateValue)
                        .and(SWBI.startDate.lt(toDateValue))
                        .and(wholeSalerIDZero)
                        .and(categoryIDNull)
                        .and(SWBI.wholeSalerID.notIn(2858)))
                .groupBy(SWBI.productID)
                .orderBy(subOrderSpecifier)
                .offset((pageNo - 1) * pageSize).limit(pageSize);

        return query.fetch();
    }
}
