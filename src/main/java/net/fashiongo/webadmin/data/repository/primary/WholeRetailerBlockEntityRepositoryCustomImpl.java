package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.sql.JPASQLQuery;
import com.querydsl.sql.SQLExpressions;
import net.fashiongo.webadmin.data.entity.primary.QVendorEntity;
import net.fashiongo.webadmin.data.entity.primary.QVendorSettingEntity;
import net.fashiongo.webadmin.data.entity.primary.QWholeRetailerBlockEntity;
import net.fashiongo.webadmin.data.model.buyer.InaccessibleVendor;
import net.fashiongo.webadmin.utility.MSSQLServer2012Templates;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class WholeRetailerBlockEntityRepositoryCustomImpl implements WholeRetailerBlockEntityRepositoryCustom {

	@PersistenceContext(unitName = "primaryEntityManager")
	private EntityManager entityManager;

	@Override
	public Page<InaccessibleVendor> findAllByRetailerIdOrderByStartingDateDesc(int pageNumber, int pageSize, int retailerId) {
		long offset = (pageNumber - 1) * pageSize;
		long limit = pageSize;

		JPASQLQuery<InaccessibleVendor> jpasqlQuery = new JPASQLQuery<InaccessibleVendor>(entityManager,new MSSQLServer2012Templates());
		QWholeRetailerBlockEntity B = QWholeRetailerBlockEntity.wholeRetailerBlockEntity;
		QVendorEntity vendorEntity = QVendorEntity.vendorEntity;
		QVendorSettingEntity settingEntity = QVendorSettingEntity.vendorSettingEntity;

//		boolean active, String companyName, boolean orderActive, Integer retailerID, boolean shopActive, Timestamp startingDate, Integer wholeRetailerBlockID, Integer wholeSalerID, boolean blockpolicy, Integer row
		jpasqlQuery.select(
					Projections.constructor(
							InaccessibleVendor.class
							, Expressions.cases().when(settingEntity.statusCode.in(1,2,3)).then(true).otherwise(false)
							, vendorEntity.name
							, Expressions.cases().when(settingEntity.statusCode.in(3)).then(true).otherwise(false)
							, B.retailerID
							, Expressions.cases().when(settingEntity.statusCode.in(2,3)).then(true).otherwise(false)
							, B.startingDate
							, B.wholeRetailerBlockID
							, B.wholeSalerID
							, vendorEntity.isVirtualVendor
							, SQLExpressions.rowNumber().over().orderBy(B.startingDate.desc())
					)
				)
				.from(B)
				.innerJoin(vendorEntity).on(B.wholeSalerID.eq(vendorEntity.vendor_id.intValue()))
				.leftJoin(settingEntity).on(vendorEntity.vendor_id.eq(settingEntity.vendorId))
				.where(
						B.retailerID.eq(retailerId)
				).orderBy(B.startingDate.desc())
				.offset(offset)
				.limit(limit);

		QueryResults<InaccessibleVendor> inaccessibleVendorQueryResults = jpasqlQuery.fetchResults();

		List<InaccessibleVendor> results = inaccessibleVendorQueryResults.getResults();
		long resultsTotal = inaccessibleVendorQueryResults.getTotal();

		PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
		return PageableExecutionUtils.getPage(results,pageRequest,()-> resultsTotal);
	}

	@Override
	@Transactional(transactionManager = "primaryTransactionManager")
	public long deleteByWholeRetailerBlockID(Integer wholeRetailerBlockID) {
		QWholeRetailerBlockEntity T = QWholeRetailerBlockEntity.wholeRetailerBlockEntity;
		JPADeleteClause jpaDeleteClause = new JPADeleteClause(entityManager,T);

		return jpaDeleteClause.where(T.wholeRetailerBlockID.eq(wholeRetailerBlockID)).execute();
	}

	@Override
	@Transactional(transactionManager = "primaryTransactionManager")
	public boolean existByWholesalerIdAndRetailerId(Integer wholesalerId, Integer retailerId) {
		JPAQuery jpaQuery = new JPAQuery(entityManager);
		QWholeRetailerBlockEntity T = QWholeRetailerBlockEntity.wholeRetailerBlockEntity;

		jpaQuery.select(T)
				.from(T)
				.where(
						T.wholeSalerID.eq(wholesalerId).and(T.retailerID.eq(retailerId))
				);

		return jpaQuery.fetchCount() > 0;
	}
}
