package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.sql.SQLExpressions;
import net.fashiongo.webadmin.data.entity.primary.QWholeSalerEntity;
import net.fashiongo.webadmin.data.model.sitemgmt.ProductsSize;
import net.fashiongo.webadmin.data.entity.primary.QInventoryEntity;
import net.fashiongo.webadmin.data.entity.primary.QProductsEntity;
import net.fashiongo.webadmin.data.entity.primary.QXColorEntity;
import net.fashiongo.webadmin.data.entity.primary.QXSizeChartEntity;
import net.fashiongo.webadmin.data.model.sitemgmt.ProductsColors;
import net.fashiongo.webadmin.data.model.sitemgmt.ProductsInfo;
import net.fashiongo.webadmin.data.model.statistics.AdminServerProducts;
import net.fashiongo.webadmin.data.repository.QueryDSLSQLFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ProductsEntityRepositoryCustomImpl implements ProductsEntityRepositoryCustom {

    @PersistenceContext(unitName = "primaryEntityManager")
    private EntityManager entityManager;

    @Autowired
    private QueryDSLSQLFunctions queryDSLSQLFunctions;

    @Override
    public List<ProductsInfo> getProductsInfo(Integer productID) {
        QProductsEntity products = QProductsEntity.productsEntity;

        JPAQuery<ProductsInfo> query = new JPAQuery<>(entityManager);

        query.select(Projections.constructor(ProductsInfo.class,
                products.productID,
                products.productName,
                products.productName2,
                products.productDescription,
                products.unitPrice,
                products.unitPrice1,
                products.unitPrice2,
                products.pictureGeneral,
                products.patternID,
                products.lengthID,
                products.styleID,
                products.fabricDescription,
                products.madeIn,
                products.stockAvailability,
                products.itemName,
                products.availableOn,
                queryDSLSQLFunctions.isnull(String.class, Expressions.cases().when(products.labelTypeID.eq(1)).then("Labeled").when(products.labelTypeID.eq(2)).then("Printed").otherwise(""), "").as("Labeled")
                ))
                .from(products)
                .where(products.productID.eq(productID));

        return query.fetch();
    }

    @Override
    public List<ProductsColors> getProductsColors(Integer productsID) {
        QProductsEntity PRD = QProductsEntity.productsEntity;
        QInventoryEntity IVT = QInventoryEntity.inventoryEntity;
        QXColorEntity XC = QXColorEntity.xColorEntity;

        JPAQuery<ProductsColors> query = new JPAQuery<>(entityManager);

        query.select(Projections.constructor(ProductsColors.class,
                PRD.productID,
                XC.colorID,
                XC.color))
                .from(PRD)
                .leftJoin(PRD.inventory, IVT)
                .leftJoin(IVT.xColors, XC)
                .where(XC.active.eq(true).and(PRD.productID.eq(productsID)));

        return query.fetch();
    }

    @Override
    public List<ProductsSize> getProductsSizes(Integer productsID) {
        QProductsEntity PRD = QProductsEntity.productsEntity;
        QXSizeChartEntity XS = QXSizeChartEntity.xSizeChartEntity;

        StringExpression comma = Expressions.asString(",");

        StringExpression sizes = Expressions
                .asString(Expressions.cases().when(XS.s1.isNull()).then("").otherwise(XS.s1)
                .concat(Expressions.cases().when(XS.s2.isNull()).then("").otherwise(comma.concat(XS.s2)))
                .concat(Expressions.cases().when(XS.s3.isNull()).then("").otherwise(comma.concat(XS.s3)))
                .concat(Expressions.cases().when(XS.s4.isNull()).then("").otherwise(comma.concat(XS.s4)))
                .concat(Expressions.cases().when(XS.s5.isNull()).then("").otherwise(comma.concat(XS.s5)))
                .concat(Expressions.cases().when(XS.s6.isNull()).then("").otherwise(comma.concat(XS.s6)))
                .concat(Expressions.cases().when(XS.s7.isNull()).then("").otherwise(comma.concat(XS.s7)))
                .concat(Expressions.cases().when(XS.s8.isNull()).then("").otherwise(comma.concat(XS.s8)))
                .concat(Expressions.cases().when(XS.s9.isNull()).then("").otherwise(comma.concat(XS.s9)))
                .concat(Expressions.cases().when(XS.s10.isNull()).then("").otherwise(comma.concat(XS.s10)))
                .concat(Expressions.cases().when(XS.s11.isNull()).then("").otherwise(comma.concat(XS.s11)))
        );

        JPAQuery<ProductsSize> query = new JPAQuery<>(entityManager);

        query.select(Projections.constructor(ProductsSize.class,
                PRD.productID,
                sizes))
                .from(PRD)
                .innerJoin(PRD.xSizeChart, XS)
                .where(PRD.productID.eq(productsID));

        return query.fetch();
    }

    @Override
    public Long getTotalItemCount(Integer adminWebServerID, Integer imageServerID) {
        QProductsEntity P = QProductsEntity.productsEntity;
        QWholeSalerEntity W = QWholeSalerEntity.wholeSalerEntity;

        Expression<Integer> constant = Expressions.constant(1);
        BooleanExpression expression = Expressions.asNumber(1).eq(constant);

        if (adminWebServerID != null) {
            expression.and(W.adminWebServerID.eq(adminWebServerID));
        }
        if (imageServerID != null) {
            expression.and(W.imageServerID.eq(imageServerID));
        }

        JPAQuery<Long> query = new JPAQuery<>(entityManager);
        query.select(P.productID.count())
                .from(P)
                .innerJoin(W).on(P.wholeSalerID.eq(W.wholeSalerID))
                .where(expression
                        .and(W.active.eq(true).and(W.shopActive.eq(true)).and(W.orderActive.eq(true))));

        return query.fetchFirst();
    }

    @Override
    public List<AdminServerProducts> getAdminServerProducts(Integer adminWebServerID, Integer imageServerID, String vendorname) {
        QProductsEntity P = QProductsEntity.productsEntity;
        QWholeSalerEntity W = QWholeSalerEntity.wholeSalerEntity;

        QProductsEntity P1 = new QProductsEntity("P1");
        QWholeSalerEntity W1 = new QWholeSalerEntity("W1");

        Expression<Integer> constant = Expressions.constant(1);
        BooleanExpression expression = Expressions.asNumber(1).eq(constant);
        BooleanExpression expression1 = Expressions.asNumber(1).eq(constant);

        if (adminWebServerID != null) {
            expression.and(W.adminWebServerID.eq(adminWebServerID));
            expression1.and(W1.adminWebServerID.eq(adminWebServerID));
        }
        if (imageServerID != null) {
            expression.and(W.imageServerID.eq(imageServerID));
            expression1.and(W1.imageServerID.eq(imageServerID));
        }

        JPAQuery<AdminServerProducts> query = new JPAQuery<>(entityManager);
        query.select(Projections.constructor(AdminServerProducts.class,
                W.wholeSalerID,
                W.companyName,
                W.adminWebServerID.max().as("adminwebserverid"),
                W.imageServerID.max().as("imageserverid"),
                P.productID.count().as("ItemCount"),
                (P.productID.count().divide(SQLExpressions.select(
                        P1.productID.count().floatValue())
                        .from(P1)
                        .innerJoin(W1).on(P1.wholeSalerID.eq(W1.wholeSalerID))
                        .where(expression1)
                        ).as("pcnt"))
                ))
                .from(P)
                .innerJoin(W).on(P.wholeSalerID.eq(W.wholeSalerID))
                .where(expression
                        .and(W.active.eq(true))
                        .and(W.shopActive.eq(true))
                        .and(W.orderActive.eq(true))
                        .and(W.companyName.contains(vendorname)))
                .groupBy(W.wholeSalerID,W.companyName)
                .orderBy(P.productID.count().desc());

        return query.fetch();
    }


}
