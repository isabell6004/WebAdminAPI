package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.QFeaturedItemEntity;
import net.fashiongo.webadmin.data.entity.primary.QProductImageEntity;
import net.fashiongo.webadmin.data.entity.primary.QProductsEntity;
import net.fashiongo.webadmin.data.entity.primary.QSystemImageServersEntity;
import net.fashiongo.webadmin.data.entity.primary.QVendorEntity;
import net.fashiongo.webadmin.data.model.sitemgmt.FeaturedItemCount;
import net.fashiongo.webadmin.data.model.sitemgmt.FeaturedItemList;
import net.fashiongo.webadmin.data.repository.QueryDSLSQLFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class FeaturedItemEntityRepositoryCustomImpl implements FeaturedItemEntityRepositoryCustom {

    @PersistenceContext(unitName = "primaryEntityManager")
    private EntityManager entityManager;

    @Autowired
    private QueryDSLSQLFunctions queryDSLSQLFunctions;

    @Override
    public List<FeaturedItemCount> getFeaturedItemCount(String period) {
        QFeaturedItemEntity FI = QFeaturedItemEntity.featuredItemEntity;
        QProductsEntity PS = QProductsEntity.productsEntity;

        JPAQuery<FeaturedItemCount> query = new JPAQuery<>(entityManager);

        query.select(Projections.constructor(FeaturedItemCount.class,
                FI.featuredItemDate,
                FI.featuredItemID.count(),
                PS.active.castToNum(Integer.class).sum(),
                Expressions.cases().when(PS.active.eq(true)).then(0).otherwise(Expressions.cases().when(PS.active.eq(false)).then(0).otherwise(1)).sum(),
                Expressions.cases().when(FI.bestItemUse.isNotNull()).then(FI.bestItemUse).otherwise(0).sum()))
                .from(FI)
                .leftJoin(FI.productsEntity, PS)
                .where(this.isMatchYearMonth(FI, period))
                .groupBy(FI.featuredItemDate)
                .orderBy(FI.featuredItemDate.asc());

        return query.fetch();

    }

    @Override
    public List<FeaturedItemList> getFeaturedItemList(String period) {
        QFeaturedItemEntity FI = QFeaturedItemEntity.featuredItemEntity;
        QProductsEntity PRD = QProductsEntity.productsEntity;
        QProductImageEntity PRDI = QProductImageEntity.productImageEntity;
        QVendorEntity VDR = QVendorEntity.vendorEntity;
        QSystemImageServersEntity IMGSVR = QSystemImageServersEntity.systemImageServersEntity;

        JPAQuery<FeaturedItemList> query = new JPAQuery<>(entityManager);

        NumberExpression rowIndex = Expressions.asNumber(0);

        query.select(Projections.constructor(FeaturedItemList.class,
                FI.featuredItemID,
                FI.featuredItemDate,
                FI.bestItemUse,
                FI.wholeSalerID,
                FI.wholeSalerName,
                FI.productID,
                FI.productName,
                FI.createdOn,
                FI.createdBy,
                PRD.productID,
                PRD.wholeSalerID,
                PRD.productName,
                PRD.unitPrice,
                PRDI.imageName.as("PictureGeneral"),
                IMGSVR.urlPath,
                VDR.dirname,
                queryDSLSQLFunctions.isnull(Boolean.class, PRD.active, 0).as("Active"),
                PRD.activatedOn,
                PRD.createdOn,
                PRD.modifiedOn,
                rowIndex
                ))
                .from(FI)
                .leftJoin(FI.productsEntity, PRD)
                .leftJoin(PRDI).on(PRD.productID.eq(PRDI.productID).and(PRDI.listOrder.eq(1)))
                .leftJoin(PRD.wholeSaler, VDR)
                .leftJoin(IMGSVR).on(IMGSVR.imageServerID.eq(7))
                .where(this.isMatchYearMonth(FI, period))
                .orderBy(FI.featuredItemDate.asc(), FI.featuredItemID.asc());

        return query.fetch();
    }

    private BooleanExpression isMatchYearMonth(QFeaturedItemEntity FI, String period) {
        String year = period.substring(0,4);
        NumberExpression yearEx = Expressions.asNumber(Integer.valueOf(year));
        String month = period.substring(5,7);
        NumberExpression monthEx = Expressions.asNumber(Integer.valueOf(month));

        BooleanExpression yearMatch = Expressions.asBoolean(FI.featuredItemDate.year().eq(yearEx));
        BooleanExpression monthMatch = Expressions.asBoolean(FI.featuredItemDate.month().eq(monthEx));

        return yearMatch.and(monthMatch);
    }

    @Override
    @Transactional(transactionManager = "primaryTransactionManager")
    public List<FeaturedItemList> getFeaturedItemListDay(String period) {
        QFeaturedItemEntity FI = QFeaturedItemEntity.featuredItemEntity;
        QProductsEntity PRD = QProductsEntity.productsEntity;
        QProductImageEntity PRDI = QProductImageEntity.productImageEntity;
        QVendorEntity VDR = QVendorEntity.vendorEntity;
        QSystemImageServersEntity IMGSVR = QSystemImageServersEntity.systemImageServersEntity;

        JPAQuery<FeaturedItemList> query = new JPAQuery<>(entityManager);

        NumberExpression rowIndex = Expressions.asNumber(0);

        query.select(Projections.constructor(FeaturedItemList.class,
                FI.featuredItemID,
                FI.featuredItemDate,
                FI.bestItemUse,
                FI.wholeSalerID,
                FI.wholeSalerName,
                FI.productID,
                FI.productName,
                FI.createdOn,
                FI.createdBy,
                PRD.productID,
                PRD.wholeSalerID,
                PRD.productName,
                PRD.unitPrice,
                PRDI.imageName.as("PictureGeneral"),
                IMGSVR.urlPath,
                VDR.dirname,
                queryDSLSQLFunctions.isnull(Boolean.class, PRD.active, 0).as("Active"),
                PRD.activatedOn,
                PRD.createdOn,
                PRD.modifiedOn,
                rowIndex
        ))
                .from(FI)
                .leftJoin(FI.productsEntity, PRD)
                .leftJoin(PRDI).on(PRD.productID.eq(PRDI.productID).and(PRDI.listOrder.eq(1)))
                .leftJoin(PRD.wholeSaler, VDR)
                .leftJoin(IMGSVR).on(IMGSVR.imageServerID.eq(7))
                .where(this.isMatchYearMonthDay(FI, period))
                .orderBy(FI.featuredItemDate.asc(), FI.featuredItemID.asc());

        List<FeaturedItemList> result = query.fetch();

        for(int i = 0; i < result.size(); i++) {
            result.get(i).setRowIndex(i + 1);
        }

        return result;
    }

    private BooleanExpression isMatchYearMonthDay(QFeaturedItemEntity FI, String period) {
        String day = period.substring(8,10);
        NumberExpression dayEx = Expressions.asNumber(Integer.valueOf(day));

        BooleanExpression dayMatch = Expressions.asBoolean(FI.featuredItemDate.dayOfMonth().eq(dayEx));

        return this.isMatchYearMonth(FI, period).and(dayMatch);
    }
}
