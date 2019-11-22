package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.BooleanPath;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.sql.JPASQLQuery;
import net.fashiongo.webadmin.data.entity.primary.FranchiseMasterAccountEntity;
import net.fashiongo.webadmin.data.entity.primary.QFranchiseMasterAccountEntity;
import net.fashiongo.webadmin.data.entity.primary.QMapFranchiseSubAccountEntity;
import net.fashiongo.webadmin.data.entity.primary.QRetailerEntity;
import net.fashiongo.webadmin.data.model.franchise.FranchiseSubAccount;
import net.fashiongo.webadmin.utility.MSSQLServer2012Templates;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class FranchiseMasterAccountEntityRepositoryCustomImpl implements FranchiseMasterAccountEntityRepositoryCustom{

	@PersistenceContext(unitName = "primaryEntityManager")
	private EntityManager entityManager;

	@Override
	@Transactional(transactionManager = "primaryTransactionManager")
	public List<FranchiseMasterAccountEntity> findAllByRetailerId(int retailerId) {
		QFranchiseMasterAccountEntity FMA = QFranchiseMasterAccountEntity.franchiseMasterAccountEntity;
		JPAQuery<FranchiseMasterAccountEntity> jpaQuery = new JPAQuery<>(entityManager);

		return jpaQuery.select(FMA)
				.from(FMA)
				.where(
						FMA.retailerId.eq(retailerId)
				)
				.fetch();
	}

	@Override
	@Transactional(transactionManager = "primaryTransactionManager")
	public Page<FranchiseSubAccount> findAllSubAccount(Integer retailerId,Integer pageNumber, Integer pageSize,  Integer masterAccountId, Integer searchTypeId, String searchTxt, Integer countryCode, String state, LocalDateTime fromDate, LocalDateTime toDate, String orderBy) {
		long offset = (pageNumber - 1) * pageSize;
		long limit = pageSize;

		JPASQLQuery<FranchiseSubAccount> jpasqlQuery = new JPASQLQuery<>(entityManager, new MSSQLServer2012Templates());

		QFranchiseMasterAccountEntity FMA = QFranchiseMasterAccountEntity.franchiseMasterAccountEntity;
		QMapFranchiseSubAccountEntity MFSA = QMapFranchiseSubAccountEntity.mapFranchiseSubAccountEntity;
		QRetailerEntity R = new QRetailerEntity("R");

		BooleanExpression expression = FMA.retailerId.eq(retailerId);

		if(searchTypeId != null) {
			switch (searchTypeId) {
				case 1:
					expression = expression.and(R.companyName.startsWith(searchTxt));
					break;
				case 2:
					expression = expression.and(R.firstName.startsWith(searchTxt));
					break;
				case 3:
					expression = expression.and(R.email.startsWith(searchTxt));
					break;
			}
		}

		if(countryCode != null && countryCode != 0) {
			expression = expression.and(R.countryID.eq(countryCode));
		}

		if(countryCode != null && countryCode == 0) {
			expression = expression.and(R.countryID.notIn(229,38));
		}

		if(state != null) {
			expression = expression.and(R.state.eq(state));
		}

		if(fromDate != null) {
			expression = expression.and(R.startingDate.goe(Timestamp.valueOf(fromDate)));
		}

		if(toDate != null) {
			LocalDateTime addToDate = toDate.plusDays(1);
			expression = expression.and(R.startingDate.lt(Timestamp.valueOf(addToDate)));
		}

		OrderSpecifier orderSpecifiers = R.retailerID.desc();
		if(orderBy != null) {
			String[] s = orderBy.split(" ");

			BooleanPath booleanPath = Expressions.booleanPath(R,s[0]);

			if(s[1].equalsIgnoreCase("asc")) {
				orderSpecifiers = booleanPath.asc();
			}else {
				orderSpecifiers = booleanPath.desc();
			}
		}

		jpasqlQuery.select(
				Projections.constructor(
						FranchiseSubAccount.class
						,R.city
						,R.companyName
						,R.country
						,R.email
						,R.firstName
						,R.lastName
						,R.retailerID
						,R.startingDate
						,R.state
				)
		)
				.from(FMA)
				.innerJoin(MFSA).on(FMA.franchiseMasterAccountId.eq(MFSA.franchiseMasterAccountId))
				.innerJoin(R).on(MFSA.retailerId.eq(R.retailerID))
				.where(expression)
				.orderBy(orderSpecifiers)
				.offset(offset)
				.limit(limit);

		QueryResults<FranchiseSubAccount> franchiseMasterAccountEntityQueryResults = jpasqlQuery.fetchResults();
		long total = franchiseMasterAccountEntityQueryResults.getTotal();
		List<FranchiseSubAccount> results = franchiseMasterAccountEntityQueryResults.getResults();
		PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
		return PageableExecutionUtils.getPage(results, pageRequest, () -> total);
	}
}
