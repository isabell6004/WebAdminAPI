package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.SubQueryExpression;
import com.querydsl.core.types.dsl.BooleanPath;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.SimplePath;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.sql.JPASQLQuery;
import com.querydsl.sql.SQLExpressions;
import net.fashiongo.webadmin.data.entity.primary.OrderPaymentStatusEntity;
import net.fashiongo.webadmin.data.entity.primary.QCodeOrderStatusEntity;
import net.fashiongo.webadmin.data.entity.primary.QConsolidationEntity;
import net.fashiongo.webadmin.data.entity.primary.QMapMergeOrdersEntity;
import net.fashiongo.webadmin.data.entity.primary.QMergeOrdersEntity;
import net.fashiongo.webadmin.data.entity.primary.QOrderPaymentStatusEntity;
import net.fashiongo.webadmin.data.entity.primary.QOrdersEntity;
import net.fashiongo.webadmin.data.entity.primary.QRetailerEntity;
import net.fashiongo.webadmin.data.model.payment.OrderPayment;
import net.fashiongo.webadmin.data.repository.QueryDSLSQLFunctions;
import net.fashiongo.webadmin.utility.MSSQLServer2012Templates;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public class OrderPaymentStatusEntityRepositoryCustomImpl implements OrderPaymentStatusEntityRepositoryCustom {

	@PersistenceContext(unitName = "primaryEntityManager")
	private EntityManager entityManager;

	@Autowired
	private QueryDSLSQLFunctions queryDSLSQLFunctions;

	@Override
	public Optional<OrderPaymentStatusEntity> findFirst(int referenceId, int isOrder, int paymentStatusId) {
		JPAQuery<OrderPaymentStatusEntity> jpaQuery = new JPAQuery<>(entityManager);
		QOrderPaymentStatusEntity OPS = QOrderPaymentStatusEntity.orderPaymentStatusEntity;

		return Optional.ofNullable(
				jpaQuery.select(OPS)
				.from(OPS)
				.where(
						OPS.referenceID.eq(referenceId)
								.and(OPS.isOrder.eq(isOrder))
								.and(OPS.paymentStatusID.eq(paymentStatusId))
				).limit(1)
				.fetchOne()
		);
	}

	@Override
	public long deleteByReferenceIdAndIsOrder(int referenceId, int isOrder) {
		QOrderPaymentStatusEntity OPS = QOrderPaymentStatusEntity.orderPaymentStatusEntity;
		JPADeleteClause jpaDeleteClause = new JPADeleteClause(entityManager,OPS);

		return jpaDeleteClause.where(
				OPS.referenceID.eq(referenceId)
				.and(OPS.isOrder.eq(isOrder))
		).execute();
	}

	@Override
	public Optional<OrderPaymentStatusEntity> findOneByReferenceIDAndIsOrder(int referenceId, int isOrder) {
		JPAQuery<OrderPaymentStatusEntity> jpaQuery = new JPAQuery<>(entityManager);
		QOrderPaymentStatusEntity OPS = QOrderPaymentStatusEntity.orderPaymentStatusEntity;

		return Optional.ofNullable(
				jpaQuery.select(OPS)
						.from(OPS)
						.where(
								OPS.referenceID.eq(referenceId)
										.and(OPS.isOrder.eq(isOrder))
						).limit(1)
						.fetchOne()
		);
	}

	@Override
	public List<OrderPayment> getOrderPayment(Integer creditCardID) {
		QCodeOrderStatusEntity COS = new QCodeOrderStatusEntity("COS");
		QOrderPaymentStatusEntity OPS = new QOrderPaymentStatusEntity("OPS");
		QOrdersEntity O = new QOrdersEntity("O");
		QRetailerEntity R = new QRetailerEntity("R");
		QConsolidationEntity CON = new QConsolidationEntity("CON");
		QMapMergeOrdersEntity MMO = new QMapMergeOrdersEntity("MMO");
		QMergeOrdersEntity MO = new QMergeOrdersEntity("MO");

		MSSQLServer2012Templates mssqlServer2012Templates = new MSSQLServer2012Templates();

		JPASQLQuery query1 = new JPASQLQuery(entityManager, mssqlServer2012Templates);
		JPASQLQuery query2 = new JPASQLQuery(entityManager, mssqlServer2012Templates);
		JPASQLQuery query3 = new JPASQLQuery(entityManager, mssqlServer2012Templates);

		query1.select(
				OPS.orderPaymentStatusID,
				OPS.referenceID,
				OPS.isOrder,
				OPS.paymentStatusID,
				OPS.prePaymentStatusID,
				OPS.retailerID,
				OPS.createdOn,
				OPS.createdBy,
				queryDSLSQLFunctions.isnull(Timestamp.class, OPS.modifiedOn, OPS.createdOn).as("ModifiedOn"),
				OPS.modifiedBy,
				O.poNumber,
				R.companyName,
				R.firstName,
				R.lastName,
				O.totalAmount.as("TotAmt"),
				O.wholeSalerID,
				O.wholeCompanyName,
				O.orderID,
				O.orderSessionGUID,
				Expressions.as(Expressions.nullExpression(), "isConsolidation"),
				SQLExpressions.select(COS.orderStatusName).from(COS).where(COS.orderStatusID.eq(O.orderStatusID)).offset(0).limit(1).as("OrderStatus")
		).from(OPS)
		.innerJoin(O).on(OPS.referenceID.eq(O.orderID).and(O.creditCardID.eq(creditCardID)))
		.innerJoin(R).on(OPS.retailerID.eq(R.retailerID))
		.where(OPS.isOrder.eq(1).and(OPS.paymentStatusID.eq(99)));


		query2.select(
				OPS.orderPaymentStatusID,
				OPS.referenceID,
				OPS.isOrder,
				OPS.paymentStatusID,
				OPS.prePaymentStatusID,
				OPS.retailerID,
				OPS.createdOn,
				OPS.createdBy,
				queryDSLSQLFunctions.isnull(Timestamp.class, OPS.modifiedOn, OPS.createdOn).as("ModifiedOn"),
				OPS.modifiedBy,
				Expressions.asString("").as("PONumber"),
				R.companyName,
				R.firstName,
				R.lastName,
				queryDSLSQLFunctions.isnull(BigDecimal.class, CON.shippingCharge, 0.00).as("TotAmt"),
				Expressions.as(Expressions.nullExpression(),"WholeSalerID"),
				Expressions.asString("").as("WholeCompanyName"),
				Expressions.as(Expressions.nullExpression(),"OrderID"),
				Expressions.as(Expressions.nullExpression(),"OrderSessionGUID"),
				Expressions.as(Expressions.nullExpression(), "isConsolidation"),
				Expressions.asString("").as("OrderStatus")
		).from(OPS)
		.innerJoin(CON).on(OPS.referenceID.eq(CON.id).and(CON.creditCardId.eq(creditCardID)))
		.innerJoin(R).on(OPS.retailerID.eq(R.retailerID))
		.where(OPS.isOrder.eq(0).and(OPS.paymentStatusID.eq(99)));


		query3.select(
				OPS.orderPaymentStatusID,
				OPS.referenceID,
				OPS.isOrder,
				OPS.paymentStatusID,
				OPS.prePaymentStatusID,
				OPS.retailerID,
				OPS.createdOn,
				OPS.createdBy,
				queryDSLSQLFunctions.isnull(Timestamp.class, OPS.modifiedOn, OPS.createdOn).as("ModifiedOn"),
				OPS.modifiedBy,
				MO.mergePONumber.as("PONumber"),
				R.companyName,
				R.firstName,
				R.lastName,
				MO.totalAmount.as("TotAmt"),
				MO.wholeSalerID,
				MO.wholeCompanyName,
				Expressions.as(Expressions.nullExpression(),"OrderID"),
				Expressions.as(Expressions.nullExpression(),"OrderSessionGUID"),
				SQLExpressions.select(Expressions.cases().when(O.shipMethodID.eq(1001)).then(true).otherwise(false)).from(MMO).innerJoin(O).on(MMO.orderID.eq(O.orderID)).offset(0).limit(1).as("isConsolidation"),
				SQLExpressions.select(COS.orderStatusName).from(COS).where(COS.orderStatusID.eq(MO.orderStatusID)).offset(0).limit(1).as("OrderStatus")
		).from(OPS)
		.innerJoin(MO).on(OPS.referenceID.eq(MO.mergeID).and(MO.creditCardID.eq(creditCardID)))
		.innerJoin(R).on(OPS.retailerID.eq(R.retailerID))
		.where(OPS.isOrder.eq(2).and(OPS.paymentStatusID.eq(99)));


		SubQueryExpression subquery = SQLExpressions.unionAll(query1, query2, query3);
		SimplePath<Object> X = Expressions.simplePath(Object.class, "X");
		NumberPath<Integer> pathX_OrderPaymentStatusID = Expressions.numberPath(Integer.class, "OrderPaymentStatusID");
		NumberPath<Integer> pathX_ReferenceID = Expressions.numberPath(Integer.class, "ReferenceID");
		NumberPath<Integer> pathX_IsOrder = Expressions.numberPath(Integer.class, "IsOrder");
		NumberPath<Integer> pathX_PaymentStatusID = Expressions.numberPath(Integer.class, "PaymentStatusID");
		NumberPath<Integer> pathX_PrePaymentStatusID = Expressions.numberPath(Integer.class, "PrePaymentStatusID");
		NumberPath<Integer> pathX_RetailerID = Expressions.numberPath(Integer.class, "RetailerID");
		DateTimePath<Timestamp> pathX_CreatedOn = Expressions.dateTimePath(Timestamp.class, "CreatedOn");
		StringPath pathX_CreatedBy = Expressions.stringPath("CreatedBy");
		DateTimePath<Timestamp> pathX_ModifiedOn = Expressions.dateTimePath(Timestamp.class, "ModifiedOn");
		StringPath pathX_ModifiedBy = Expressions.stringPath("ModifiedBy");
		StringPath pathX_PONumber = Expressions.stringPath("PONumber");
		StringPath pathX_CompanyName = Expressions.stringPath("CompanyName");
		StringPath pathX_FirstName = Expressions.stringPath("FirstName");
		StringPath pathX_LastName = Expressions.stringPath("LastName");
		NumberPath<BigDecimal> pathX_TotAmt = Expressions.numberPath(BigDecimal.class, "TotAmt");
		NumberPath<Integer> pathX_WholeSalerID = Expressions.numberPath(Integer.class, "WholeSalerID");
		StringPath pathX_WholeCompanyName = Expressions.stringPath("WholeCompanyName");
		NumberPath<Integer> pathX_OrderID = Expressions.numberPath(Integer.class, "OrderID");
		StringPath pathX_OrderSessionGUID = Expressions.stringPath("OrderSessionGUID");
		BooleanPath pathX_isConsolidation = Expressions.booleanPath("isConsolidation");
		StringPath pathX_OrderStatusName = Expressions.stringPath("OrderStatus");

		JPASQLQuery<OrderPayment> query = new JPASQLQuery<OrderPayment>(entityManager, mssqlServer2012Templates);

		query.select(Projections.constructor(OrderPayment.class,
				pathX_OrderPaymentStatusID,
				pathX_ReferenceID,
				pathX_IsOrder,
				pathX_PaymentStatusID,
				pathX_PrePaymentStatusID,
				pathX_RetailerID,
				pathX_CreatedOn,
				pathX_CreatedBy,
				pathX_ModifiedOn,
				pathX_ModifiedBy,
				pathX_PONumber,
				pathX_CompanyName,
				pathX_FirstName,
				pathX_LastName,
				pathX_TotAmt,
				pathX_WholeSalerID,
				pathX_WholeCompanyName,
				pathX_OrderID,
				pathX_OrderSessionGUID,
				pathX_isConsolidation,
				pathX_OrderStatusName)
		).from(subquery, X)
		.orderBy(pathX_ModifiedOn.desc());

		return query.fetch();
	}
}
