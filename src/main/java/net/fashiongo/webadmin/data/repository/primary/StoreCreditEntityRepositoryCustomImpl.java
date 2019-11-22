package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.*;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.sql.JPASQLQuery;
import com.querydsl.sql.SQLExpressions;
import com.querydsl.sql.Union;
import net.fashiongo.webadmin.data.entity.primary.QSimpleWholeSalerEntity;
import net.fashiongo.webadmin.data.entity.primary.QStoreCreditEntity;
import net.fashiongo.webadmin.data.entity.primary.QStoreCreditUseEntity;
import net.fashiongo.webadmin.data.entity.primary.StoreCreditEntity;
import net.fashiongo.webadmin.data.model.buyer.StoreCardDetail;
import net.fashiongo.webadmin.data.model.buyer.StoreCardSummary;
import net.fashiongo.webadmin.data.repository.QueryDSLSQLFunctions;
import net.fashiongo.webadmin.utility.MSSQLServer2012Templates;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class StoreCreditEntityRepositoryCustomImpl implements StoreCreditEntityRepositoryCustom {

	@PersistenceContext(unitName = "primaryEntityManager")
	private EntityManager entityManager;

	@Autowired
	private QueryDSLSQLFunctions queryDSLSQLFunctions;

	/**
	 * SELECT	a.WholeSalerID, SUM(a.CreditedAmount) as CreditedAmount, w.CompanyName as WholeCompanyName, MAX(a.CreatedOn) as CreatedOn, SUM(a.UsedAmount) as UsedAmount
	 * 			, SUM(case IsDeleted WHEN 1 THEN a.CreditedAmount ELSE 0 END) as DeletedAmount
	 * FROM		(	select	c.CreditID, c.WholeSalerID, c.RetailerID
	 * 						, c.CreditedAmount, c.CreditReason, c.CreditedOn as CreatedOn
	 * 						, NULL UsedAmount, convert(bit,isnull(c.IsUsed,0)) IsUsed, isnull(c.IsDeleted,0) IsDeleted
	 * 				from	dbo.StoreCredit as c
	 * 				where	c.RetailerID= @RetailerID
	 * 				union
	 * 				select	NULL CreditID, c.WholeSalerID, c.RetailerID
	 * 						, NULL CreditedAmount, null as CreditReason, c.UsedOn as CreatedOn
	 * 						, c.UsedAmount, convert(bit,0) as IsUsed, convert(bit,0) as IsDeleted
	 * 				from	dbo.StoreCreditUse  as c
	 * 				where	c.RetailerID= @RetailerID
	 * 				and	c.Active = 1
	 * 			) as a
	 * 			inner join dbo.tblWholeSaler as w on a.WholeSalerID = w.WholeSalerID
	 * WHERE  (		@Vendor is null
	 * 			or w.CompanyName like '%'+@Vendor+'%'
	 * 			)
	 * and		(	@PO is null
	 * 			or	 a.CreditReason like 'po# %'+@PO+'%'
	 * 			)
	 * and		(	@From is null
	 * 			or a.CreatedOn>=@From
	 * 			)
	 * and		(	@To is null
	 * 			or	a.CreatedOn<=@To
	 * 			)
	 * GROUP BY a.WholeSalerID, w.CompanyName
	 * ORDER BY MAX(a.CreatedOn) DESC
	 * @param retailerId
	 * @param vendorName
	 * @param poNumber
	 * @param from
	 * @param to
	 * @return
	 */
	@Override
	public List<StoreCardSummary> findAllStoreCardSummary(int retailerId, String vendorName, String poNumber, LocalDateTime from, LocalDateTime to) {
		JPASQLQuery<StoreCardSummary> jpasqlQuery = new JPASQLQuery<StoreCardSummary>(entityManager,new MSSQLServer2012Templates());
		QStoreCreditEntity C = QStoreCreditEntity.storeCreditEntity;
		QStoreCreditUseEntity CU = QStoreCreditUseEntity.storeCreditUseEntity;
		QSimpleWholeSalerEntity W = QSimpleWholeSalerEntity.simpleWholeSalerEntity;

		SimpleTemplate<Object> nullTemplate = Expressions.simpleTemplate(Object.class, "NULL");

		Union<Tuple> subQuery = SQLExpressions.union(
				JPAExpressions.select(
						C.creditID.as("CreditID")
						, C.wholeSalerID.as("WholeSalerID")
						, C.retailerID.as("RetailerID")
						, C.creditedAmount.as("CreditedAmount")
						, C.creditReason.as("CreditReason")
						, C.creditedOn.as("CreatedOn")
						, nullTemplate.as("UsedAmount")
						, queryDSLSQLFunctions.isnull(Boolean.class, C.isUsed, false).as("IsUsed")
						, queryDSLSQLFunctions.isnull(Boolean.class, C.isDeleted, false).as("IsDeleted")
				).from(C)
						.where(C.retailerID.eq(retailerId))
				, JPAExpressions.select(
						nullTemplate.as("CreditID")
						, CU.wholeSalerID.as("WholeSalerID")
						, CU.retailerID.as("RetailerID")
						, nullTemplate.as("CreditedAmount")
						, nullTemplate.as("CreditReason")
						, CU.usedOn.as("CreatedOn")
						, CU.usedAmount.as("UsedAmount")
						, Expressions.simpleTemplate(Boolean.class,"0").as("IsUsed")
						, Expressions.simpleTemplate(Boolean.class,"0").as("IsDeleted")
				).from(CU)
						.where(CU.retailerID.eq(retailerId).and(CU.active.eq(true)))
		);

		SimplePath<Object> fromA = Expressions.path(Object.class, "A");
		NumberPath<Integer> wholeSalerID = Expressions.numberPath(Integer.class, fromA, "WholeSalerID");
		DateTimePath<Timestamp> createdOn = Expressions.dateTimePath(Timestamp.class, fromA, "CreatedOn");
		NumberPath<BigDecimal> creditedAmount = Expressions.numberPath(BigDecimal.class, fromA, "CreditedAmount");
		NumberPath<BigDecimal> usedAmount = Expressions.numberPath(BigDecimal.class, fromA, "UsedAmount");
		BooleanPath isDeleted = Expressions.booleanPath(fromA, "IsDeleted");
		StringPath creditReason = Expressions.stringPath(fromA, "CreditReason");
		BooleanExpression booleanExpression = Expressions.ONE.eq(1);

		if(vendorName != null) {
			booleanExpression = booleanExpression.and(W.companyName.contains(vendorName));
		}

		if(poNumber != null) {
			booleanExpression = booleanExpression.and(creditReason.like("po# %" + poNumber + "%"));
		}

		if(from != null) {
			booleanExpression = booleanExpression.and(createdOn.goe(Timestamp.valueOf(from)));
		}

		if(to != null) {
			booleanExpression = booleanExpression.and(createdOn.loe(Timestamp.valueOf(to)));
		}

		jpasqlQuery
				.select(
						Projections.constructor(
								StoreCardSummary.class
								,wholeSalerID
								,creditedAmount.sum()
								,W.companyName
								,createdOn.max()
								,usedAmount.sum()
								,Expressions.cases().when(isDeleted.eq(true)).then(creditedAmount).otherwise(BigDecimal.ZERO).sum()
						)
				)
				.from(subQuery,fromA)
				.innerJoin(W).on(W.wholeSalerId.eq(wholeSalerID))
				.where(booleanExpression)
				.groupBy(wholeSalerID,W.companyName)
				.orderBy(createdOn.max().desc());

		return jpasqlQuery.fetch();
	}

	/**
	 *
	 * SELECT	a.CreditID, a.WholeSalerID,  w.CompanyName as WholeCompanyName, a.RetailerID, a.CreditedAmount, a.CreditReason, a.CreatedOn, a.CreditedBy, a.UsedAmount, a.IsUsed, a.IsDeleted
	 * FROM 	(	select	c.CreditID, c.WholeSalerID, c.RetailerID
	 * 						, c.CreditedAmount, c.CreditReason, c.CreditedOn as CreatedOn, c.CreditedBy
	 * 						, NULL UsedAmount, convert(bit,isnull(c.IsUsed,0)) IsUsed, isnull(c.IsDeleted,0) IsDeleted
	 * 				from	dbo.StoreCredit as c
	 * 				where	c.RetailerID= @RetailerID
	 * 				union
	 * 				select	NULL CreditID, c.WholeSalerID, c.RetailerID
	 * 						, NULL as CreditedAmount, NULL as CreditReason, c.UsedOn as CreatedOn, NULL CreditedBy
	 * 						, c.UsedAmount, convert(bit,0) as IsUsed, convert(bit,0) as IsDeleted
	 * 				from	dbo.StoreCreditUse as c
	 * 				where c.RetailerID=@RetailerID
	 * 				and 	c.Active = 1
	 * 			) as a
	 * 			inner join dbo.tblWholeSaler as w on a.WholeSalerID = w.WholeSalerID
	 * WHERE  ( @Vendor is null
	 * 			or w.CompanyName like '%'+@Vendor+'%'
	 * 			)
	 * and	(	@PO is null
	 * 		or	 a.CreditReason like 'po# %'+@PO+'%'
	 * 		)
	 * and	(	@From is null
	 * 		or a.CreatedOn>=@From
	 * 		)
	 * and	(	@To is null
	 * 		or	a.CreatedOn<=@To
	 * 		)
	 * ORDER BY a.CreatedOn DESC
	 *
	 * @param retailerId
	 * @param vendorName
	 * @param poNumber
	 * @param from
	 * @param to
	 * @return
	 */
	@Override
	public List<StoreCardDetail> findAllStoreCardDetail(int retailerId, String vendorName, String poNumber, LocalDateTime from, LocalDateTime to) {
		JPASQLQuery<StoreCardDetail> jpasqlQuery = new JPASQLQuery<StoreCardDetail>(entityManager,new MSSQLServer2012Templates());
		QStoreCreditEntity C = QStoreCreditEntity.storeCreditEntity;
		QStoreCreditUseEntity CU = QStoreCreditUseEntity.storeCreditUseEntity;
		QSimpleWholeSalerEntity W = QSimpleWholeSalerEntity.simpleWholeSalerEntity;

		SimpleTemplate<Object> nullTemplate = Expressions.simpleTemplate(Object.class, "NULL");

		Union<Tuple> subQuery = SQLExpressions.union(
				JPAExpressions.select(
						C.creditID.as("CreditID")
						, C.wholeSalerID.as("WholeSalerID")
						, C.retailerID.as("RetailerID")
						, C.creditedAmount.as("CreditedAmount")
						, C.creditReason.as("CreditReason")
						, C.creditedOn.as("CreatedOn")
						, C.creditedBy.as("CreditedBy")
						, nullTemplate.as("UsedAmount")
						, queryDSLSQLFunctions.isnull(Boolean.class, C.isUsed, false).as("IsUsed")
						, queryDSLSQLFunctions.isnull(Boolean.class, C.isDeleted, false).as("IsDeleted")
				).from(C)
						.where(C.retailerID.eq(retailerId))
				, JPAExpressions.select(
						nullTemplate.as("CreditID")
						, CU.wholeSalerID.as("WholeSalerID")
						, CU.retailerID.as("RetailerID")
						, nullTemplate.as("CreditedAmount")
						, nullTemplate.as("CreditReason")
						, CU.usedOn.as("CreatedOn")
						, nullTemplate.as("CreditedBy")
						, CU.usedAmount.as("UsedAmount")
						, Expressions.simpleTemplate(Boolean.class,"0").as("IsUsed")
						, Expressions.simpleTemplate(Boolean.class,"0").as("IsDeleted")
				).from(CU)
						.where(CU.retailerID.eq(retailerId).and(CU.active.eq(true)))
		);

		SimplePath<Object> fromA = Expressions.path(Object.class, "A");
		NumberPath<Integer> creditID = Expressions.numberPath(Integer.class, fromA, "CreditID");
		NumberPath<Integer> wholeSalerID = Expressions.numberPath(Integer.class, fromA, "WholeSalerID");
		NumberPath<Integer> retailerID = Expressions.numberPath(Integer.class, fromA, "RetailerID");
		DateTimePath<Timestamp> createdOn = Expressions.dateTimePath(Timestamp.class, fromA, "CreatedOn");
		NumberPath<BigDecimal> creditedAmount = Expressions.numberPath(BigDecimal.class, fromA, "CreditedAmount");
		NumberPath<BigDecimal> usedAmount = Expressions.numberPath(BigDecimal.class, fromA, "UsedAmount");
		BooleanPath isDeleted = Expressions.booleanPath(fromA, "IsDeleted");
		BooleanPath isUsed = Expressions.booleanPath(fromA, "IsUsed");
		StringPath creditReason = Expressions.stringPath(fromA, "CreditReason");
		StringPath creditedBy = Expressions.stringPath(fromA, "CreditedBy");

		BooleanExpression booleanExpression = Expressions.ONE.eq(1);

		if(vendorName != null) {
			booleanExpression = booleanExpression.and(W.companyName.contains(vendorName));
		}

		if(poNumber != null) {
			booleanExpression = booleanExpression.and(creditReason.like("po# %" + poNumber + "%"));
		}

		if(from != null) {
			booleanExpression = booleanExpression.and(createdOn.goe(Timestamp.valueOf(from)));
		}

		if(to != null) {
			booleanExpression = booleanExpression.and(createdOn.loe(Timestamp.valueOf(to)));
		}

		jpasqlQuery
				.select(
						Projections.constructor(
								StoreCardDetail.class
								,creditID
								,wholeSalerID
								,W.companyName
								,retailerID
								,creditedAmount
								,creditReason
								,createdOn
								,creditedBy
								,usedAmount
								,isUsed
								,isDeleted
						)
				)
				.from(subQuery,fromA)
				.innerJoin(W).on(W.wholeSalerId.eq(wholeSalerID))
				.where(booleanExpression)
				.orderBy(createdOn.desc());


		return jpasqlQuery.fetch();
	}

	@Override
	public Optional<StoreCreditEntity> findByRetailerIdAndCreditId(int retailerId, int creditId) {
		JPAQuery<StoreCreditEntity> jpaQuery = new JPAQuery<>(entityManager);
		QStoreCreditEntity T = QStoreCreditEntity.storeCreditEntity;

		return jpaQuery.select(T)
				.from(T)
				.where(
						T.retailerID.eq(retailerId).and(T.creditID.eq(creditId))
				).fetch()
				.stream()
				.findFirst();
	}
}
