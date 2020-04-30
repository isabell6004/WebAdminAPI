package net.fashiongo.webadmin.dao.primary.impl;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.SubQueryExpression;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.BooleanPath;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.SimplePath;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.sql.JPASQLQuery;
import com.querydsl.sql.SQLExpressions;
import net.fashiongo.webadmin.dao.primary.PaymentTransactionEntityRepositoryCustom;
import net.fashiongo.webadmin.data.entity.primary.QConsolidationEntity;
import net.fashiongo.webadmin.data.entity.primary.QListPaymentStatusEntity;
import net.fashiongo.webadmin.data.entity.primary.QMergeOrdersEntity;
import net.fashiongo.webadmin.data.entity.primary.QOrderPaymentStatusEntity;
import net.fashiongo.webadmin.data.entity.primary.QOrdersEntity;
import net.fashiongo.webadmin.data.entity.primary.QPaymentTransactionEntity;
import net.fashiongo.webadmin.model.pojo.payment.PaymentStatusList;
import net.fashiongo.webadmin.utility.MSSQLServer2012Templates;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class PaymentTransactionEntityRepositoryCustomImpl implements PaymentTransactionEntityRepositoryCustom {
    @PersistenceContext(unitName = "primaryEntityManager")
    private EntityManager entityManager;

    @Override
    @Transactional(value = "primaryTransactionManager", isolation = Isolation.READ_UNCOMMITTED)
    public Page<PaymentStatusList> getPaymentStatusList(int pageNum, int pageSize, Integer wholesalerID,
                                                        Integer paymentStatusID, LocalDateTime fromDate,
                                                        LocalDateTime toDate, String poNumber, Integer consolidationID,
                                                        String buyerName, Integer transactionType, Integer searchSuccess, String orderBy) {
        QPaymentTransactionEntity pt = new QPaymentTransactionEntity("pt");
        QOrdersEntity tos = new QOrdersEntity("tos");
        QOrderPaymentStatusEntity ops = new QOrderPaymentStatusEntity("ops");
        QListPaymentStatusEntity lps = new QListPaymentStatusEntity("lps");
        QConsolidationEntity con = new QConsolidationEntity("tos");
        QMergeOrdersEntity mo = new QMergeOrdersEntity("tos");

        long offset = (pageNum - 1) * pageSize;

        int startIndex = pageSize * (pageNum - 1) + 1;
        int endIndex = startIndex + pageSize - 1;

        Expression<Integer> constant = Expressions.constant(1);
        BooleanExpression filter = Expressions.asNumber(1).eq(constant);
        BooleanExpression filter2 = Expressions.asNumber(1).eq(constant);
        BooleanExpression filter3 = Expressions.asNumber(1).eq(constant);

        if (wholesalerID != null) {
            filter = filter.and(tos.wholeSalerID.eq(wholesalerID));
            filter2 = filter2.and(Expressions.asNumber(0).eq(constant));
        }

        if (paymentStatusID != null) {
            filter = filter.and(ops.paymentStatusID.eq(paymentStatusID));
            filter2 = filter2.and(ops.paymentStatusID.eq(paymentStatusID));
            filter3 = filter3.and(ops.paymentStatusID.eq(paymentStatusID));
        }

        if (fromDate != null) {
        	filter = filter.and(pt.createdOn.goe(Timestamp.valueOf(fromDate)));
        	filter2 = filter2.and(pt.createdOn.goe(Timestamp.valueOf(fromDate)));
        	filter3 = filter3.and(pt.createdOn.goe(Timestamp.valueOf(fromDate)));
            //filter = filter.and(pt.createdOn.goe(Expressions.stringTemplate("convert(nvarchar(50),{0},101", fromDate)));
            //filter2 = filter2.and(pt.createdOn.goe(Expressions.stringTemplate("convert(nvarchar(50),{0},101", fromDate)));
            //filter3 = filter3.and(pt.createdOn.goe(Expressions.stringTemplate("convert(nvarchar(50),{0},101", fromDate)));
        }

        if (toDate != null) {
        	filter = filter.and(pt.createdOn.loe(Timestamp.valueOf(toDate)));
        	filter2 = filter2.and(pt.createdOn.loe(Timestamp.valueOf(toDate)));
        	filter3 = filter3.and(pt.createdOn.loe(Timestamp.valueOf(toDate)));        	
            //filter = filter.and(pt.createdOn.loe(Expressions.stringTemplate("convert(nvarchar(50),{0},101", toDate)));
            //filter2 = filter2.and(pt.createdOn.loe(Expressions.stringTemplate("convert(nvarchar(50),{0},101", toDate)));
            //filter3 = filter3.and(pt.createdOn.loe(Expressions.stringTemplate("convert(nvarchar(50),{0},101", toDate)));
        }

        if (poNumber != null) {
            filter = filter.and(tos.poNumber.like(poNumber+ "%"));
            filter2 = filter2.and(Expressions.asNumber(0).eq(constant));
            filter3 = filter3.and(mo.mergePONumber.like(poNumber+ "%"));
        }

        if (consolidationID != null) {
            filter = filter.and(Expressions.asNumber(0).eq(constant));
            filter2 = filter2.and(pt.referenceID.like(consolidationID + "%"));
        }

        if (buyerName != null) {
            filter = filter.and(tos.retailerCompanyName.like(buyerName+ "%"));
            filter2 = filter2.and(con.buyerCompanyName.like(buyerName+ "%"));
            filter3 = filter3.and(mo.retailerCompanyName.like(buyerName+ "%"));            
        }

        if (transactionType != null) { // 1: order, 2: consolidation, 3: merged order
            if (transactionType == 1) {
                filter2 = filter2.and(Expressions.asNumber(0).eq(constant));
                filter3 = filter3.and(Expressions.asNumber(0).eq(constant));
            } else if (transactionType == 2) {
                filter = filter.and(Expressions.asNumber(0).eq(constant));
                filter3 = filter3.and(Expressions.asNumber(0).eq(constant));
            } else if (transactionType == 3) {
                filter = filter.and(Expressions.asNumber(0).eq(constant));
                filter2 = filter2.and(Expressions.asNumber(0).eq(constant));
            }
        }

        if (searchSuccess != null) {
            boolean searchSuccessBoolean;
            if (searchSuccess == 1) {
                searchSuccessBoolean = true;
            } else {
                searchSuccessBoolean = false;
            }
            filter = filter.and(pt.success.eq(searchSuccessBoolean));
            filter2 = filter2.and(pt.success.eq(searchSuccessBoolean));
//            filter3 = filter3.and(pt.success.eq(searchSuccessBoolean)); // 기존 Stored Procedure에는 없는 필터
        }

        OrderSpecifier orderSpecifier = null;
        if (orderBy == null) {
            orderSpecifier = pt.transactionID.desc();
        }

        MSSQLServer2012Templates mssqlServer2012Templates = new MSSQLServer2012Templates();

        JPASQLQuery<PaymentStatusList> query = new JPASQLQuery<PaymentStatusList>(entityManager, mssqlServer2012Templates);
        JPASQLQuery query1 = new JPASQLQuery(entityManager, mssqlServer2012Templates);
        JPASQLQuery query2 = new JPASQLQuery(entityManager, mssqlServer2012Templates);
        JPASQLQuery query3 = new JPASQLQuery(entityManager, mssqlServer2012Templates);

        query1.select(
                pt.transactionID,
                pt.createdOn,
                tos.wholeCompanyName,
                tos.wholeSalerID,
                tos.poNumber,
                tos.orderSessionGUID,
                tos.retailerCompanyName,
                tos.retailerID,
                tos.firstName,
                tos.lastName,
                pt.amount.as("TotalAmount"),
                tos.cardNumber,
                tos.paymentMethod,
                lps.paymentStatus,
                lps.paymentStatusID,
                pt.referenceID.as("OrderID"),
                Expressions.as(Expressions.nullExpression(), "ConsolidationID"), //ConsolidationID
                Expressions.as(Expressions.nullExpression(), "MergeID"), //MergeID
                pt.success,
                pt.detail,
                pt.creditCardID,
                pt.declineCode,
                pt.transactionType
        ).from(pt)
        .innerJoin(tos).on(pt.referenceID.eq(tos.orderID))
        .innerJoin(ops).on(pt.referenceID.eq(ops.referenceID))
        .innerJoin(lps).on(ops.paymentStatusID.eq(lps.paymentStatusID))
        .where(pt.referenceTypeID.eq(1).and(filter));

        query2.select(
                pt.transactionID,
                pt.createdOn,
                Expressions.asString("FG Consolidation").as("WholeCompanyName"),
                Expressions.asNumber(0).as("WholeSalerID"),
                Expressions.as(Expressions.nullExpression(), "PONumber"), //PONumber
                Expressions.as(Expressions.nullExpression(), "OrderSessionGUID"), //OrderSessionGUID
                con.buyerCompanyName.as("RetailerCompanyName"),
                con.retailerID,
                con.nameOnCard.as("FirstName"),
                Expressions.asString("").as("LastName"),
                pt.amount.as("TotalAmount"),
                con.last4Digit.as("CardNumber"),
                con.cardType.concat(" ending in ").concat(con.last4Digit).as("PaymentMethod"),
                lps.paymentStatus,
                lps.paymentStatusID,
                Expressions.as(Expressions.nullExpression(), "OrderID"), //OrderID
                pt.referenceID.as("ConsolidationID"),
                Expressions.as(Expressions.nullExpression(), "MergeID"), //MergeID
                pt.success,
                pt.detail,
                pt.creditCardID,
                pt.declineCode,
                pt.transactionType
        ).from(pt)
        .innerJoin(con).on(pt.referenceID.eq(con.id))
        .innerJoin(ops).on(pt.referenceID.eq(ops.referenceID))
        .innerJoin(lps).on(ops.paymentStatusID.eq(lps.paymentStatusID))
        .where(pt.referenceTypeID.eq(0).and(filter2));

        query3.select(
                pt.transactionID,
                pt.createdOn,
                mo.wholeCompanyName,
                mo.wholeSalerID,
                mo.mergePONumber.as("PONumber"),
                Expressions.as(Expressions.nullExpression(), "OrderSessionGUID"), //OrderSessionGUID
                mo.retailerCompanyName,
                mo.retailerID,
                mo.nameOnCard.as("FirstName"),
                Expressions.asString("").as("LastName"),
                pt.amount.as("TotalAmount"),
                mo.last4Digit.as("CardNumber"),
                mo.paymentMethod,
                lps.paymentStatus,
                lps.paymentStatusID,
                Expressions.as(Expressions.nullExpression(), "OrderID"), //OrderID
                Expressions.as(Expressions.nullExpression(), "ConsolidationID"), //ConsolidationID
                mo.mergeID,
                pt.success,
                pt.detail,
                pt.creditCardID,
                pt.declineCode,
                pt.transactionType
        ).from(pt)
        .innerJoin(mo).on(pt.referenceID.eq(mo.mergeID))
        .innerJoin(ops).on(pt.referenceID.eq(ops.referenceID))
        .innerJoin(lps).on(ops.paymentStatusID.eq(lps.paymentStatusID))
        .where(pt.referenceTypeID.eq(2).and(filter3));


        SubQueryExpression subquery = SQLExpressions.unionAll(query1,query2,query3);

        SimplePath<Object> w = Expressions.path(Object.class, "w");
        NumberPath<Long> pathW_rowno = Expressions.numberPath(Long.class, "rowno");
        NumberPath<Integer> pathW_TransactionID = Expressions.numberPath(Integer.class, "TransactionID");
        DateTimePath<Timestamp> pathW_CreatedOn = Expressions.dateTimePath(Timestamp.class, "CreatedOn");
        StringPath pathW_wholeCompanyName = Expressions.stringPath("WholeCompanyName");
        NumberPath<Integer> pathW_WholeSalerID = Expressions.numberPath(Integer.class, "WholeSalerID");
        StringPath pathW_PONumber = Expressions.stringPath("PONumber");
        StringPath pathW_OrderSessionGUID = Expressions.stringPath("OrderSessionGUID");
        StringPath pathW_RetailerCompanyName = Expressions.stringPath("RetailerCompanyName");
        NumberPath<Integer> pathW_RetailerID = Expressions.numberPath(Integer.class, "RetailerID");
        StringPath pathW_FirstName = Expressions.stringPath("FirstName");
        StringPath pathW_LastName = Expressions.stringPath("LastName");
        NumberPath<BigDecimal> pathW_Amount = Expressions.numberPath(BigDecimal.class, "TotalAmount");
        StringPath pathW_CardNumber = Expressions.stringPath("CardNumber");
        StringPath pathW_PaymentMethod = Expressions.stringPath("PaymentMethod");
        StringPath pathW_PaymentStatus = Expressions.stringPath("PaymentStatus");
        NumberPath<Integer> pathW_PaymentStatusID = Expressions.numberPath(Integer.class, "PaymentStatusID");
        NumberPath<Integer> pathW_OrderID = Expressions.numberPath(Integer.class, "OrderID");
        NumberPath<Integer> pathW_ConsolidationID = Expressions.numberPath(Integer.class, "ConsolidationID");
        NumberPath<Integer> pathW_MergeID = Expressions.numberPath(Integer.class, "MergeID");
        BooleanPath pathW_Success = Expressions.booleanPath("Success");
        StringPath pathW_Detail = Expressions.stringPath("Detail");
        NumberPath<Integer> pathW_CreditCardID = Expressions.numberPath(Integer.class, "CreditCardID");
        StringPath pathW_DeclineCode = Expressions.stringPath("DeclineCode");
        NumberPath<Integer> pathW_TransactionType = Expressions.numberPath(Integer.class, "TransactionType");


        query.select(Projections.constructor(PaymentStatusList.class,
                SQLExpressions.rowNumber().over().orderBy(pathW_TransactionID.desc()).as("rowno"),
                pathW_TransactionID,
                pathW_CreatedOn,
                pathW_wholeCompanyName,
                pathW_WholeSalerID,
                pathW_PONumber,
                pathW_OrderSessionGUID,
                pathW_RetailerCompanyName,
                pathW_RetailerID,
                pathW_FirstName,
                pathW_LastName,
                pathW_Amount,
                pathW_CardNumber,
                pathW_PaymentMethod,
                pathW_PaymentStatus,
                pathW_PaymentStatusID,
                pathW_OrderID,
                pathW_ConsolidationID,
                pathW_MergeID,
                pathW_Success,
                pathW_Detail,
                pathW_CreditCardID,
                pathW_DeclineCode,
                pathW_TransactionType
        )).from(subquery, w)
        .offset(offset).limit(pageSize);

        QueryResults<PaymentStatusList> result = query.fetchResults();
        long total = result.getTotal();
        List<PaymentStatusList> resultList = result.getResults();

        PageRequest pageRequest = PageRequest.of(pageNum-1, pageSize);

        return PageableExecutionUtils.getPage(resultList,pageRequest,()->total);
    }
}
