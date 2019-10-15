package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.sql.JPASQLQuery;
import com.querydsl.sql.SQLExpressions;
import net.fashiongo.webadmin.data.entity.primary.QSimpleWholeSalerEntity;
import net.fashiongo.webadmin.data.entity.primary.QWholeRetailerBlockEntity;
import net.fashiongo.webadmin.data.model.buyer.InaccessibleVendor;
import net.fashiongo.webadmin.utility.MSSQLServer2012Templates;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.support.PageableExecutionUtils;

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
		QSimpleWholeSalerEntity W = QSimpleWholeSalerEntity.simpleWholeSalerEntity;

//		boolean active, String companyName, boolean orderActive, Integer retailerID, boolean shopActive, Timestamp startingDate, Integer wholeRetailerBlockID, Integer wholeSalerID, boolean blockpolicy, Integer row
		jpasqlQuery.select(
					Projections.constructor(
							InaccessibleVendor.class
							, W.active
							, W.companyName
							, W.orderActive
							, B.retailerID
							, W.shopActive
							, B.startingDate
							, B.wholeRetailerBlockID
							, B.wholeSalerID
							, W.blockPolicy
							, SQLExpressions.rowNumber().over().orderBy(B.startingDate.desc())
					)
				)
				.from(B)
				.innerJoin(W).on(B.wholeSalerID.eq(W.wholeSalerId))
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
}
