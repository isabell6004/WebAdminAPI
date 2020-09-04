package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.SimplePath;
import com.querydsl.jpa.sql.JPASQLQuery;
import com.querydsl.sql.ProjectableSQLQuery;
import com.querydsl.sql.SQLExpressions;
import lombok.RequiredArgsConstructor;
import net.fashiongo.webadmin.data.entity.primary.QProductImageEntity;
import net.fashiongo.webadmin.data.entity.primary.QProductsEntity;
import net.fashiongo.webadmin.data.entity.primary.QStatisticsWaBestItemPerDayEntity;
import net.fashiongo.webadmin.data.entity.primary.QSystemImageServersEntity;
import net.fashiongo.webadmin.data.entity.primary.QWholeSalerEntity;
import net.fashiongo.webadmin.data.model.statistics.BestItems;
import net.fashiongo.webadmin.data.repository.QueryDSLSQLFunctions;
import net.fashiongo.webadmin.utility.MSSQLServer2012Templates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
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

    @Override
    @Deprecated
    public List<BestItems> getBestItems(Integer pageNo, Integer pageSize, LocalDateTime fromDate, LocalDateTime toDate,
                                        Integer statisticsType, Integer lastCategoryID, Integer wholeSalerId, String orderBy) {
        QStatisticsWaBestItemPerDayEntity SWBI = new QStatisticsWaBestItemPerDayEntity("SWBI");
        QProductsEntity P = QProductsEntity.productsEntity;
        QProductImageEntity PRDI = QProductImageEntity.productImageEntity;
        QWholeSalerEntity W = QWholeSalerEntity.wholeSalerEntity;
        QSystemImageServersEntity SI = QSystemImageServersEntity.systemImageServersEntity;

        SimplePath<Object> pathT = Expressions.path(Object.class, "T");
        NumberPath<Long> pathT_RowNo = Expressions.numberPath(Long.class, pathT, "RowNo");
        NumberPath<Integer> pathT_ProductID = Expressions.numberPath(Integer.class, pathT, "ProductID");
        NumberPath<Long> pathT_OrderItemQty = Expressions.numberPath(Long.class, pathT, "OrderItemQty");
        NumberPath<Long> pathT_OrderAmount = Expressions.numberPath(Long.class, pathT, "OrderAmount");

        JPASQLQuery<BestItems> jpasqlQuery = new JPASQLQuery<>(entityManager, new MSSQLServer2012Templates());
        JPASQLQuery subquery = new JPASQLQuery(entityManager, new MSSQLServer2012Templates());

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


        OrderSpecifier subOrderSpecifier = null;
        if (statisticsType == 1) {
            subOrderSpecifier = SWBI.orderAmount.sum().desc();
        } else if (statisticsType == 2) {
            subOrderSpecifier = SWBI.orderItemQty.sum().desc();
        }

        Date fromDateValue = Date.from(fromDate.atZone(ZoneId.systemDefault()).toInstant());
        Date toDateValue = Date.from(toDate.plusDays(1).atZone(ZoneId.systemDefault()).toInstant());

        subquery.select(SQLExpressions.rowNumber().over().orderBy(subOrderSpecifier).as("RowNo"),
                SWBI.productID.as("ProductID"),
                SWBI.orderItemQty.sum().as("OrderItemQty"),
                SWBI.orderAmount.sum().as("OrderAmount"))
                .from(SWBI)
                .where(SWBI.startDate.goe(fromDateValue)
                        .and(SWBI.startDate.lt(toDateValue))
                        .and(wholeSalerIDZero)
                        .and(categoryIDNull)
                        .and(SWBI.wholeSalerID.notIn(2858)))
                .groupBy(SWBI.productID)
                .orderBy(subOrderSpecifier)
                .offset((pageNo - 1) * pageSize).limit(pageSize);

        List<OrderSpecifier> orderSpecifierList = new ArrayList<>();
        OrderSpecifier orderSpecifier = null;
        switch (orderBy) {
            case "Newest":
                orderSpecifier = P.activatedOn.desc().nullsLast();
                break;
            case "UnitpriceDesc":
                orderSpecifier = P.unitPrice.desc().nullsLast();
                break;
            case "Oldest":
                orderSpecifier = P.activatedOn.asc().nullsFirst();
                break;
            case "UnitpriceAsc":
                orderSpecifier = P.unitPrice.asc().nullsFirst();
                break;
        }
        if (orderSpecifier != null) {
            orderSpecifierList.add(orderSpecifier);
        }
        orderSpecifierList.add(pathT_RowNo.asc());

        OrderSpecifier[] orderSpecifiers = orderSpecifierList.toArray(new OrderSpecifier[orderSpecifierList.size()]);

        jpasqlQuery.select(Projections.constructor(BestItems.class,
                SQLExpressions.rowNumber().over().orderBy(orderSpecifiers).as("RowNo"),
                pathT_ProductID,
                pathT_OrderAmount.as("TotAmt"),
                pathT_OrderItemQty.as("TotQty"),
                P.activatedOn,
                P.productID,
                P.productName,
                W.companyName,
                P.unitPrice,
                SI.urlPath.as("ImageUrlRoot"),
                W.dirName,
                PRDI.imageName.as("PictureGeneral")))
                .from(subquery, pathT)
                .leftJoin(P).on(pathT_ProductID.eq(P.productID).and(P.wholeSalerID.notIn(2858)))
                .leftJoin(PRDI).on(P.productID.eq(PRDI.productID).and(PRDI.listOrder.eq(1).and(PRDI.active.eq(true))))
                .leftJoin(W).on(P.wholeSalerID.eq(W.wholeSalerID).and(W.wholeSalerID.notIn(2858)))
                .leftJoin(SI).on(W.imageServerID.eq(SI.imageServerID))
                .orderBy(orderSpecifiers)
                .offset((pageNo - 1) * pageSize).limit(pageSize);

        return jpasqlQuery.fetch();
    }
}
