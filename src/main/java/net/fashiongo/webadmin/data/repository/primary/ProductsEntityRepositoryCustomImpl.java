package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.model.sitemgmt.ProductsSize;
import net.fashiongo.webadmin.data.entity.primary.QInventoryEntity;
import net.fashiongo.webadmin.data.entity.primary.QProductsEntity;
import net.fashiongo.webadmin.data.entity.primary.QXColorEntity;
import net.fashiongo.webadmin.data.entity.primary.QXSizeChartEntity;
import net.fashiongo.webadmin.data.model.sitemgmt.ProductsColors;
import net.fashiongo.webadmin.data.model.sitemgmt.ProductsInfo;
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


}
