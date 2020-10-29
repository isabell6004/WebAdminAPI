package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.SubQueryExpression;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.SimplePath;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.sql.JPASQLQuery;
import com.querydsl.sql.SQLExpressions;
import com.querydsl.sql.SQLQuery;
import net.fashiongo.webadmin.data.entity.primary.QCodeCreditCardTypeEntity;
import net.fashiongo.webadmin.data.entity.primary.QConsolidationEntity;
import net.fashiongo.webadmin.data.entity.primary.QListCardStatusEntity;
import net.fashiongo.webadmin.data.entity.primary.QMergeOrdersEntity;
import net.fashiongo.webadmin.data.entity.primary.QOrderPaymentStatusEntity;
import net.fashiongo.webadmin.data.entity.primary.QOrdersEntity;
import net.fashiongo.webadmin.data.entity.primary.QPaymentCreditCardEntity;
import net.fashiongo.webadmin.data.entity.primary.QPaymentTransactionEntity;
import net.fashiongo.webadmin.data.entity.primary.QRetailerEntity;
import net.fashiongo.webadmin.data.model.payment.CreditCardInfo;
import net.fashiongo.webadmin.data.model.payment.PaymentCreditCardInfo;
import net.fashiongo.webadmin.data.repository.QueryDSLSQLFunctions;
import net.fashiongo.webadmin.utility.MSSQLServer2012Templates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PaymentCreditCardEntityRepositoryCustomImpl implements PaymentCreditCardEntityRepositoryCustom {
    @PersistenceContext(unitName = "primaryEntityManager")
    private EntityManager entityManager;

    @Autowired
    private QueryDSLSQLFunctions queryDSLSQLFunctions;

    @Override
    public List<PaymentCreditCardInfo> getPaymentCreditCardInfo(Integer creditCardID) {
        QPaymentCreditCardEntity A = new QPaymentCreditCardEntity("a");
        QCodeCreditCardTypeEntity B = new QCodeCreditCardTypeEntity("b");
        QListCardStatusEntity C = new QListCardStatusEntity("c");

        JPAQuery<PaymentCreditCardInfo> query = new JPAQuery<>(entityManager);

        query.select(Projections.constructor(PaymentCreditCardInfo.class,
                A.creditCardID, A.retailerID, A.referenceID, A.cardTypeID, A.last4Digit, A.cvv, A.expirationMonth, A.expirationYear, A.nameOnCard, A.fingerprint, A.email,
                A.street, A.city, A.state, A.zipcode, A.country, A.countryID, A.isDefaultCard, A.cardStatusID, A.isDeleted, A.createdOn, A.createdBy, A.modifiedOn, A.modifiedBy,
                A.referenceID2, A.source, A.sourceDate, A.cardFunding, B.creditCardType.as("CreditCardTypeName"), C.cardStatusName))
            .from(A)
            .innerJoin(B).on(A.cardTypeID.eq(B.creditCardTypeID))
            .innerJoin(C).on(A.cardStatusID.eq(C.cardStatusID))
            .where(A.creditCardID.eq(creditCardID));

        return query.fetch();
    }

    @Override
    public Page<CreditCardInfo> getCreditCardInfo(Integer pageNum, Integer pageSize, String cardID, Boolean isDefaultCard, Integer cardTypeID, Integer cardStatusID, String billingID, String country, String state, String buyer, String referencedID, String orderBy, String orderGUBN) {
        QPaymentCreditCardEntity PCC = new QPaymentCreditCardEntity("PCC");
        QRetailerEntity TR = new QRetailerEntity("TR");
        QCodeCreditCardTypeEntity CCT = new QCodeCreditCardTypeEntity("CCT");
        QListCardStatusEntity LCS = new QListCardStatusEntity("LCS");

        Expression<Integer> constant = Expressions.constant(1);
        BooleanExpression sqlWhere = Expressions.asNumber(1).eq(constant);

        JPASQLQuery<CreditCardInfo> query = new JPASQLQuery<CreditCardInfo>(entityManager, new MSSQLServer2012Templates());

        query.select(Projections.constructor(CreditCardInfo.class,
                PCC.creditCardID, PCC.retailerID, PCC.referenceID, PCC.last4Digit,
                PCC.nameOnCard, PCC.countryID,
                PCC.isDefaultCard, PCC.cardStatusID, PCC.isDeleted, PCC.createdOn, PCC.modifiedOn,
                PCC.cardTypeID, CCT.creditCardType,
                TR.companyName.concat("(").concat(TR.lastName).concat(TR.firstName).concat(")").as("Buyer"),
                CCT.creditCardType.concat(" ending in ").concat(PCC.last4Digit).as("CreditCardInfo"),
                LCS.cardStatusName,
                PCC.street.concat(" ").concat(PCC.city).concat(" ").concat(PCC.state).concat(" ").concat(PCC.zipcode).concat(" ").concat(PCC.country).as("BillingAddress"),
                PCC.expirationMonth, PCC.expirationYear,
                PCC.expirationMonth.stringValue().concat("/").concat(PCC.expirationYear.stringValue()).as("ExpDate"),
                queryDSLSQLFunctions.isnull(Long.class,ufnGetPendingPaymentCountByCreditCardID(PCC.creditCardID), 0).as("PendingCount"),
                queryDSLSQLFunctions.isnull(String.class, ufnGetLastPendingPaymentReasonByCreditCardID(PCC.creditCardID), "").as("Reason")
        )).from(PCC)
                .innerJoin(TR).on(PCC.retailerID.eq(TR.retailerID))
                .leftJoin(CCT).on(PCC.cardTypeID.eq(CCT.creditCardTypeID))
                .leftJoin(LCS).on(PCC.cardStatusID.eq(LCS.cardStatusID));

        if (cardID != null) {
            sqlWhere = sqlWhere.and(PCC.creditCardID.like("%"+cardID+"%"));
        }
        if (isDefaultCard != null) {
            sqlWhere = sqlWhere.and(PCC.isDefaultCard.eq(isDefaultCard));
        }
        if (cardTypeID != 0) {
            sqlWhere = sqlWhere.and(PCC.cardTypeID.eq(cardTypeID));
        }
        if (cardStatusID != 0) {
            if (cardStatusID == 99) {
                sqlWhere = sqlWhere.and(PCC.isDeleted.eq(true));
            } else {
                sqlWhere = sqlWhere.and(PCC.isDeleted.eq(false));
                sqlWhere = sqlWhere.and(PCC.cardStatusID.eq(cardStatusID));
            }
        }
        if (buyer != null) {
            sqlWhere = sqlWhere.and(TR.companyName.concat("(").concat(TR.lastName).concat(TR.firstName).concat(")").contains(buyer));
        }
        if (referencedID != null) {
            sqlWhere = sqlWhere.and(PCC.referenceID.contains(referencedID));
        }
        if (billingID != null) {
            if (billingID.equals("229") || billingID.equals("38")) {
                sqlWhere = sqlWhere.and(TR.billCountryID.eq(Integer.valueOf(billingID)));
            } else {
                sqlWhere = sqlWhere.and(queryDSLSQLFunctions.isnull(Integer.class,TR.billCountryID,0).ne(229).and(queryDSLSQLFunctions.isnull(Integer.class, TR.billCountryID,0).ne(38)));
                if (!country.equals("ALL")) {
                    sqlWhere = sqlWhere.and(queryDSLSQLFunctions.isnull(Integer.class, TR.billCountryID, 0).eq(Integer.valueOf(country)));
                }
            }
            if (state != null && !state.equals("ALL")) {
                sqlWhere = sqlWhere.and(TR.state.eq(state));
            }
        }

        Order orderAD = Order.DESC;
        if (orderGUBN.equals("ASC")) {
            orderAD = Order.ASC;
        }

        OrderSpecifier orderSpecifier = new OrderSpecifier(Order.DESC, PCC.createdOn);

        if (orderBy != null) {
            if (orderBy.equals("Buyer")) {
                orderSpecifier = new OrderSpecifier(orderAD, TR.companyName.concat("(").concat(TR.lastName).concat(TR.firstName).concat(")"));
            } else if (orderBy.equals("CreditCardInfo")) {
                orderSpecifier = new OrderSpecifier(orderAD, CCT.creditCardType.concat(" ending in ").concat(PCC.last4Digit));
            } else if (orderBy.equals("ExpDate")) {
                orderSpecifier = new OrderSpecifier(orderAD, PCC.expirationMonth.stringValue().concat("/").concat(PCC.expirationYear.stringValue()));
            } else if (orderBy.equals("BillingAddress")) {
                orderSpecifier = new OrderSpecifier(orderAD, PCC.street.concat(" ").concat(PCC.city).concat(" ").concat(PCC.state).concat(" ").concat(PCC.zipcode).concat(" ").concat(PCC.country));
            } else {
                orderSpecifier = new OrderSpecifier(orderAD, Expressions.path(Object.class, orderBy));
            }
        }

        query.orderBy(orderSpecifier).where(sqlWhere)
                .offset((pageNum - 1) * pageSize).limit(pageSize);

        QueryResults<CreditCardInfo> result = query.fetchResults();
        long total = result.getTotal();
        List<CreditCardInfo> resultList = result.getResults();
        PageRequest pageRequest = PageRequest.of(pageNum-1, pageSize);

        return PageableExecutionUtils.getPage(resultList,pageRequest,()->total);
    }

    private SQLQuery ufnGetPendingPaymentCountByCreditCardID(NumberPath<Integer> creditCardID) {
        QOrderPaymentStatusEntity OPS = new QOrderPaymentStatusEntity("OPS");
        QOrdersEntity O = new QOrdersEntity("O");
        QRetailerEntity R = new QRetailerEntity("R");
        QConsolidationEntity CON = new QConsolidationEntity("CON");
        QMergeOrdersEntity MO = new QMergeOrdersEntity("MO");

        SimplePath<Object> X = Expressions.simplePath(Object.class,"X");
        NumberPath<Long> pathX_Cnt = Expressions.numberPath(Long.class, X,"cnt");

        SQLQuery query1 = SQLExpressions.select(O.creditCardID, O.creditCardID.count().as("cnt"))
                .from(OPS)
                .innerJoin(O).on(OPS.referenceID.eq(O.orderID).and(O.creditCardID.eq(creditCardID)))
                .innerJoin(R).on(OPS.retailerID.eq(R.retailerID))
                .where(OPS.isOrder.eq(1).and(OPS.paymentStatusID.eq(99)))
                .groupBy(O.creditCardID);

        SQLQuery query2 = SQLExpressions.select(CON.creditCardId, CON.creditCardId.count().as("cnt"))
                .from(OPS)
                .innerJoin(CON).on(OPS.referenceID.eq(CON.id).and(CON.creditCardId.eq(creditCardID)))
                .innerJoin(R).on(OPS.retailerID.eq(R.retailerID))
                .where(OPS.isOrder.eq(0).and(OPS.paymentStatusID.eq(99)))
                .groupBy(CON.creditCardId);

        SQLQuery query3 = SQLExpressions.select(MO.creditCardID, MO.creditCardID.count().as("cnt"))
                .from(OPS)
                .innerJoin(MO).on(OPS.referenceID.eq(MO.mergeID).and(MO.creditCardID.eq(creditCardID)))
                .innerJoin(R).on(OPS.retailerID.eq(R.retailerID))
                .where(OPS.isOrder.eq(2).and(OPS.paymentStatusID.eq(99)))
                .groupBy(MO.creditCardID);

        SubQueryExpression subquery = SQLExpressions.unionAll(query1, query2, query3);

        return SQLExpressions.select(pathX_Cnt.sum()).from(subquery, X);
    }

    private SQLQuery ufnGetLastPendingPaymentReasonByCreditCardID(NumberPath<Integer> creditCardID) {
        QOrderPaymentStatusEntity OPS = new QOrderPaymentStatusEntity("OPS");
        QOrdersEntity O = new QOrdersEntity("O");
        QRetailerEntity R = new QRetailerEntity("R");
        QConsolidationEntity CON = new QConsolidationEntity("CON");
        QMergeOrdersEntity MO = new QMergeOrdersEntity("MO");
        QPaymentTransactionEntity PT = new QPaymentTransactionEntity("PT");

        SimplePath<Object> X = Expressions.simplePath(Object.class, "X");
        NumberPath<Integer> pathX_ReferenceID = Expressions.numberPath(Integer.class, X,"ReferenceID");
        NumberPath<Integer> pathX_IsOrder = Expressions.numberPath(Integer.class, X,"IsOrder");


        SQLQuery query1 = SQLExpressions.select(OPS.referenceID, OPS.isOrder, O.creditCardID)
                .from(OPS)
                .innerJoin(O).on(OPS.referenceID.eq(O.orderID).and(O.creditCardID.eq(creditCardID)))
                .innerJoin(R).on(OPS.retailerID.eq(R.retailerID))
                .where(OPS.isOrder.eq(1).and(OPS.paymentStatusID.eq(99)));

        SQLQuery query2 = SQLExpressions.select(OPS.referenceID, OPS.isOrder, CON.creditCardId)
                .from(OPS)
                .innerJoin(CON).on(OPS.referenceID.eq(CON.id).and(CON.creditCardId.eq(creditCardID)))
                .innerJoin(R).on(OPS.retailerID.eq(R.retailerID))
                .where(OPS.isOrder.eq(0).and(OPS.paymentStatusID.eq(99)));

        SQLQuery query3 = SQLExpressions.select(OPS.referenceID, OPS.isOrder, MO.creditCardID)
                .from(OPS)
                .innerJoin(MO).on(OPS.referenceID.eq(MO.mergeID).and(MO.creditCardID.eq(creditCardID)))
                .innerJoin(R).on(OPS.retailerID.eq(R.retailerID))
                .where(OPS.isOrder.eq(2).and(OPS.paymentStatusID.eq(99)));

        SubQueryExpression subquery = SQLExpressions.unionAll(query1, query2, query3);

        return SQLExpressions.select(Expressions.asString("Reason: ").concat(PT.detail))
                .from(subquery, X)
                .innerJoin(PT).on(pathX_ReferenceID.eq(PT.referenceID).and(pathX_IsOrder.eq(PT.referenceTypeID)))
                .orderBy(PT.transactionID.desc())
                .offset(0).limit(1);
    }
}
