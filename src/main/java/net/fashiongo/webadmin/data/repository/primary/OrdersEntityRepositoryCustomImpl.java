package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.SubQueryExpression;
import com.querydsl.core.types.dsl.*;
import com.querydsl.jpa.sql.JPASQLQuery;
import com.querydsl.sql.SQLExpressions;
import net.fashiongo.webadmin.data.entity.primary.*;
import net.fashiongo.webadmin.data.model.buyer.OrderHistory;
import net.fashiongo.webadmin.data.repository.QueryDSLSQLFunctions;
import net.fashiongo.webadmin.utility.MSSQLServer2012Templates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.support.PageableExecutionUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrdersEntityRepositoryCustomImpl implements OrdersEntityRepositoryCustom {

	@PersistenceContext(unitName = "primaryEntityManager")
	private EntityManager entityManager;

	@Autowired
	private QueryDSLSQLFunctions queryDSLSQLFunctions;

	private static class VW_ORDER_LIST_PATH {

		public static NumberPath<Integer> orderID(Path<?> parent) { return Expressions.numberPath(Integer.class, parent,"OrderID");}
		public static NumberPath<Integer> transactionType(Path<?> parent) { return Expressions.numberPath(Integer.class, parent,"TransactionType");}
		public static DateTimePath<Timestamp> checkoutDate(Path<?> parent) { return Expressions.dateTimePath(Timestamp.class, parent,"CheckoutDate");}
		public static StringPath wholeCompanyName(Path<?> parent) {return Expressions.stringPath(parent,"WholeCompanyName");}
		public static StringPath poNumber(Path<?> parent) {return Expressions.stringPath(parent,"PONumber");}
		public static NumberPath<BigDecimal> totalAmountWOSC(Path<?> parent) {return Expressions.numberPath(BigDecimal.class, parent,"TotalAmountWOSC");}
		public static StringPath createdBy(Path<?> parent) {return Expressions.stringPath(parent,"CreatedBy");}
		public static StringPath orderStatusName(Path<?> parent) {return Expressions.stringPath(parent,"OrderStatusName");}
		public static NumberPath<Integer> happenedAt(Path<?> parent) {return Expressions.numberPath(Integer.class, parent,"HappenedAt");}
		public static NumberPath<Integer> orderStatusID(Path<?> parent) {return Expressions.numberPath(Integer.class, parent,"OrderStatusID");}
		public static NumberPath<Integer> retailerID(Path<?> parent) {return Expressions.numberPath(Integer.class, parent,"RetailerID");}
		public static StringPath productName(Path<?> parent) {return Expressions.stringPath(parent,"ProductName");}
		public static NumberPath<Integer> referenceTypeID(Path<?> parent) {return Expressions.numberPath(Integer.class, parent,"ReferenceTypeID");}
		public static StringPath paymentMethod(Path<?> parent) {return Expressions.stringPath(parent,"PaymentMethod");}
		public static NumberPath<Integer> paymentStatusID(Path<?> parent) { return Expressions.numberPath(Integer.class, parent,"PaymentStatusID");}

	}

	@Override
	public Page<OrderHistory> up_wa_GetOrderHistoryMaster(int pageNum, int pageSize, Integer retailerID, String wholeCompanyName, Integer paymentStatusID, Integer orderStatusID, LocalDateTime dateFrom, LocalDateTime dateTo, String poNumber, String productName, String orderBy) {
		long offset = (pageNum - 1) * pageSize;
		long limit = pageSize;
		JPASQLQuery<OrderHistory> jpasqlQuery = new JPASQLQuery(entityManager,new MSSQLServer2012Templates());
		SimplePath<Object> temp = Expressions.path(Object.class, "TEMP");
		NumberPath<Integer> path_orderID = VW_ORDER_LIST_PATH.orderID(temp);
		DateTimePath<Timestamp> path_checkoutDate = VW_ORDER_LIST_PATH.checkoutDate(temp);
		StringPath path_wholeCompanyName = VW_ORDER_LIST_PATH.wholeCompanyName(temp);
		StringPath path_poNumber = VW_ORDER_LIST_PATH.poNumber(temp);
		NumberPath<BigDecimal> path_totalAmountWOSC = VW_ORDER_LIST_PATH.totalAmountWOSC(temp);
		StringPath path_createdBy = VW_ORDER_LIST_PATH.createdBy(temp);
		StringPath path_orderStatusName = VW_ORDER_LIST_PATH.orderStatusName(temp);
		NumberPath<Integer> path_happenedAt = VW_ORDER_LIST_PATH.happenedAt(temp);
		NumberPath<Integer> path_orderStatusID = VW_ORDER_LIST_PATH.orderStatusID(temp);
		NumberPath<Integer> path_retailerID = VW_ORDER_LIST_PATH.retailerID(temp);
		NumberPath<Integer> path_referenceTypeID = VW_ORDER_LIST_PATH.referenceTypeID(temp);
		StringPath path_paymentMethod = VW_ORDER_LIST_PATH.paymentMethod(temp);
		NumberPath<Integer> path_paymentStatusID = VW_ORDER_LIST_PATH.paymentStatusID(temp);
		NumberPath<Integer> path_transactionType = VW_ORDER_LIST_PATH.transactionType(temp);
		StringPath path_productName = VW_ORDER_LIST_PATH.productName(temp);
		BooleanExpression whereExpression = path_orderStatusID.gt(0);

		SubQueryExpression source = null;
		if(productName != null) {
			source = vw_wa_OrderListForVendor2();
		} else {
			source = vw_wa_OrderListForVendor();
		}

		if(retailerID != null) {
			whereExpression = whereExpression.and(path_retailerID.eq(retailerID));
		}

		if(wholeCompanyName != null) {
			whereExpression = whereExpression.and(path_wholeCompanyName.contains(wholeCompanyName));
		}

		if(paymentStatusID != null) {
			whereExpression = whereExpression.and(path_paymentStatusID.eq(paymentStatusID));
		}

		if(orderStatusID != null) {
			whereExpression = whereExpression.and(path_orderStatusID.eq(orderStatusID));
		}

		if(dateFrom != null) {
			dateFrom = dateFrom.withNano(0);
			whereExpression = whereExpression.and(path_checkoutDate.goe(Timestamp.valueOf(dateFrom)));
		}

		if(dateTo != null) {
			dateTo = dateTo.withNano(0);
			whereExpression = whereExpression.and(path_checkoutDate.lt(Timestamp.valueOf(dateTo)));
		}

		if(poNumber != null) {
			whereExpression = whereExpression.and(path_poNumber.startsWith(poNumber));
		}

		if(productName != null) {
			whereExpression = whereExpression.and((path_productName.startsWith(productName)));
		}

		List<OrderSpecifier> orderSpecifierList = new ArrayList<>();
		orderSpecifierList.add(path_checkoutDate.desc());
		if(orderBy != null) {
			orderSpecifierList.clear();
			String[] s = orderBy.split(" ");

			BooleanPath booleanPath = Expressions.booleanPath(s[0]);
			if(s[1].equalsIgnoreCase("asc")) {
				orderSpecifierList.add(booleanPath.asc());
			}else {
				orderSpecifierList.add(booleanPath.desc());
			}
		}
		OrderSpecifier[] orderSpecifiers = orderSpecifierList.toArray(new OrderSpecifier[orderSpecifierList.size()]);

		jpasqlQuery.select(
					Projections.constructor(
							OrderHistory.class
							, path_checkoutDate
							, path_createdBy
							, path_happenedAt
							, path_orderID
							, path_orderStatusName
							, path_poNumber
							, path_paymentMethod
							, path_paymentStatusID
							, path_referenceTypeID
							, path_totalAmountWOSC
							, path_transactionType
							, path_wholeCompanyName
					)
				)
				.from(source,temp)
				.where(whereExpression)
				.offset(offset)
				.limit(limit)
				.orderBy(orderSpecifiers)
		;

		QueryResults<OrderHistory> retailerRatingQueryResults = jpasqlQuery.fetchResults();
		List<OrderHistory> results = retailerRatingQueryResults.getResults();
		long resultsTotal = retailerRatingQueryResults.getTotal();

		PageRequest pageRequest = PageRequest.of(pageNum-1, pageSize);
		return PageableExecutionUtils.getPage(results,pageRequest,()-> resultsTotal);
	}

	private SubQueryExpression vw_wa_OrderListForVendor() {
		JPASQLQuery orderQuery = new JPASQLQuery(entityManager,new MSSQLServer2012Templates());
		JPASQLQuery mergeOrderQuery = new JPASQLQuery(entityManager,new MSSQLServer2012Templates());
		QOrdersEntity ORDER = QOrdersEntity.ordersEntity;
		QCodeOrderStatusEntity COS = QCodeOrderStatusEntity.codeOrderStatusEntity;
		QChannelLogEntity CL = QChannelLogEntity.channelLogEntity;
		QOrderPaymentStatusEntity OP = QOrderPaymentStatusEntity.orderPaymentStatusEntity;
		QMergeOrdersEntity MERGE_ORDER = QMergeOrdersEntity.mergeOrdersEntity;
		QPaymentTransactionEntity PT = QPaymentTransactionEntity.paymentTransactionEntity;

		orderQuery.select(
				ORDER.orderID.as("OrderID")
				, ORDER.checkOutDate.as("CheckoutDate")
				, ORDER.wholeCompanyName.as("WholeCompanyName")
				, ORDER.poNumber.as("PONumber")
				, ORDER.totalAmountWOSC.as("TotalAmountWOSC")
				, ORDER.createdBy.as("CreatedBy")
				, ORDER.orderStatusID.as("OrderStatusID")
				, ORDER.retailerID.as("RetailerID")
				, ORDER.paymentMethod.as("PaymentMethod")
				, queryDSLSQLFunctions.isnull(String.class,COS.name2Vendor, "Temp Order").as("OrderStatusName")
				, OP.paymentStatusID.as("PaymentStatusID")
				, CL.happenedAt.as("HappenedAt")
				, SQLExpressions.select(PT.transactionType)
						.from(PT)
						.where(PT.referenceID.eq(ORDER.orderID).and(PT.referenceTypeID.eq(1)).and(PT.success.eq(true)))
						.limit(1)
						.orderBy(PT.transactionID.desc()).as("TransactionType")
				, Expressions.ONE.as("ReferenceTypeID")
		).from(ORDER)
				.leftJoin(COS).on(ORDER.orderStatusID.eq(COS.orderStatusID))
				.leftJoin(CL).on(ORDER.orderID.eq(CL.referenceID))
				.leftJoin(OP).on(ORDER.orderID.eq(OP.referenceID).and(OP.isOrder.eq(1)))
				.where(ORDER.orderStatusID.notIn(0).or(ORDER.orderStatusID.eq(0).and(ORDER.createdByVendor.isNotNull())));

		mergeOrderQuery.select(
				MERGE_ORDER.mergeID.as("OrderID")
				, MERGE_ORDER.mergeDate.as("CheckoutDate")
				, MERGE_ORDER.wholeCompanyName.as("WholeCompanyName")
				, MERGE_ORDER.mergePONumber.as("PONumber")
				, MERGE_ORDER.totalAmountWSC.as("TotalAmountWOSC")
				, MERGE_ORDER.createdBy.as("CreatedBy")
				, MERGE_ORDER.orderStatusID.as("OrderStatusID")
				, MERGE_ORDER.retailerID.as("RetailerID")
				, MERGE_ORDER.paymentMethod.as("PaymentMethod")
				, queryDSLSQLFunctions.isnull(String.class,COS.name2Vendor, "Temp Order").as("OrderStatusName")
				, OP.paymentStatusID
				, Expressions.ONE.as("HappenedAt")
				, SQLExpressions.select(PT.transactionType)
						.from(PT)
						.where(PT.referenceID.eq(MERGE_ORDER.mergeID).and(PT.referenceTypeID.eq(2)).and(PT.success.eq(true)))
						.limit(1)
						.orderBy(PT.transactionID.desc()).as("TransactionType")
				, Expressions.TWO.as("ReferenceTypeID")
		).from(MERGE_ORDER)
				.leftJoin(COS).on(MERGE_ORDER.orderStatusID.eq(COS.orderStatusID))
				.leftJoin(OP).on(MERGE_ORDER.mergeID.eq(OP.referenceID).and(OP.isOrder.eq(2)))
				.where(MERGE_ORDER.orderStatusID.notIn(0).or(MERGE_ORDER.orderStatusID.eq(0)));

		return SQLExpressions.unionAll(
				orderQuery,mergeOrderQuery
		);
	}

	private SubQueryExpression vw_wa_OrderListForVendor2() {
		JPASQLQuery jpasqlQuery = new JPASQLQuery(entityManager,new MSSQLServer2012Templates());
		SubQueryExpression vw_wa_orderListForVendor = vw_wa_OrderListForVendor();
		SimplePath<Object> v = Expressions.path(Object.class, "v");
		NumberPath<Integer> orderID = VW_ORDER_LIST_PATH.orderID(v);
		DateTimePath<Timestamp> checkoutDate = VW_ORDER_LIST_PATH.checkoutDate(v);
		StringPath wholeCompanyName = VW_ORDER_LIST_PATH.wholeCompanyName(v);
		StringPath poNumber = VW_ORDER_LIST_PATH.poNumber(v);
		NumberPath<BigDecimal> totalAmountWOSC = VW_ORDER_LIST_PATH.totalAmountWOSC(v);
		StringPath createdBy = VW_ORDER_LIST_PATH.createdBy(v);
		StringPath orderStatusName = VW_ORDER_LIST_PATH.orderStatusName(v);
		NumberPath<Integer> happenedAt = VW_ORDER_LIST_PATH.happenedAt(v);
		NumberPath<Integer> orderStatusID = VW_ORDER_LIST_PATH.orderStatusID(v);
		NumberPath<Integer> retailerID = VW_ORDER_LIST_PATH.retailerID(v);
		NumberPath<Integer> referenceTypeID = VW_ORDER_LIST_PATH.referenceTypeID(v);
		StringPath paymentMethod = VW_ORDER_LIST_PATH.paymentMethod(v);
		NumberPath<Integer> paymentStatusID = VW_ORDER_LIST_PATH.paymentStatusID(v);
		NumberPath<Integer> transactionType = VW_ORDER_LIST_PATH.transactionType(v);
		QProductsEntity P = QProductsEntity.productsEntity;
		QOrderDetailsEntity OD = QOrderDetailsEntity.orderDetailsEntity;

		jpasqlQuery.select(orderID,checkoutDate,wholeCompanyName,poNumber,totalAmountWOSC,createdBy,orderStatusName,happenedAt,orderStatusID,retailerID,referenceTypeID,paymentMethod,paymentStatusID,transactionType,P.productName.as("ProductName"))
				.from(vw_wa_orderListForVendor,v)
				.innerJoin(OD).on(orderID.eq(OD.orderID))
				.leftJoin(P).on(OD.productID.eq(P.productID))
				.distinct()
		;

		return jpasqlQuery;
	}
}
