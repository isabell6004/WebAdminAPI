package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.QFranchiseMasterAccountEntity;
import net.fashiongo.webadmin.data.entity.primary.QMapFranchiseSubAccountEntity;
import net.fashiongo.webadmin.data.entity.primary.franchise.QFranchiseRetailerEntity;
import net.fashiongo.webadmin.data.model.franchise.FranchiseBuyer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class FranchiseRetailerEntityRepositoryCustomImpl implements FranchiseRetailerEntityRepositoryCustom {

	@PersistenceContext(unitName = "primaryEntityManager")
	private EntityManager entityManager;

	@Override
	public List<FranchiseBuyer> findAllByContainsCompanyName(String companyName, long limit) {
		QFranchiseRetailerEntity R = QFranchiseRetailerEntity.franchiseRetailerEntity;
		QFranchiseMasterAccountEntity FMA = QFranchiseMasterAccountEntity.franchiseMasterAccountEntity;
		QMapFranchiseSubAccountEntity MFS = QMapFranchiseSubAccountEntity.mapFranchiseSubAccountEntity;
		JPAQuery<FranchiseBuyer> jpaQuery = new JPAQuery<>(entityManager);

		jpaQuery.select(
				Projections.constructor(
						FranchiseBuyer.class
						, R.retailerID
						, R.companyName
						, R.firstName
						, R.lastName
						, R.email
						, Expressions.cases().when(FMA.retailerId.isNotNull()).then(true).otherwise(false)
						, Expressions.cases().when(MFS.retailerId.isNotNull()).then(true).otherwise(false)
					)
				)
				.from(R)
				.leftJoin(R.franchiseMasterAccount,FMA)
				.leftJoin(R.mapFranchiseSubAccount,MFS)
				.where(
						R.active.eq("Y").and(R.companyName.contains(companyName))
				).limit(limit);

		return jpaQuery.fetch();
	}
}
