package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.sql.SQLExpressions;
import net.fashiongo.webadmin.data.entity.primary.QProductsEntity;
import net.fashiongo.webadmin.data.entity.primary.QWholeSalerEntity;
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
    public Long getTotalItemCount(Integer adminWebServerID, Integer imageServerID) {
        QProductsEntity P = QProductsEntity.productsEntity;
        QWholeSalerEntity W = QWholeSalerEntity.wholeSalerEntity;

        Expression<Integer> constant = Expressions.constant(1);
        BooleanExpression expression = Expressions.asNumber(1).eq(constant);

        if (adminWebServerID != null && adminWebServerID != 0) {
            expression = expression.and(W.adminWebServerID.eq(adminWebServerID));
        }
        if (imageServerID != null && imageServerID != 0) {
            expression = expression.and(W.imageServerID.eq(imageServerID));
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

        if (adminWebServerID != null && adminWebServerID != 0) {
            expression = expression.and(W.adminWebServerID.eq(adminWebServerID));
            expression1 = expression1.and(W1.adminWebServerID.eq(adminWebServerID));
        }
        if (imageServerID != null && imageServerID != 0) {
            expression = expression.and(W.imageServerID.eq(imageServerID));
            expression1 = expression1.and(W1.imageServerID.eq(imageServerID));
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
