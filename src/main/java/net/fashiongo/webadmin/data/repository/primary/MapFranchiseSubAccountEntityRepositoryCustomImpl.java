package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.MapFranchiseSubAccountEntity;
import net.fashiongo.webadmin.data.entity.primary.QFranchiseMasterAccountEntity;
import net.fashiongo.webadmin.data.entity.primary.QMapFranchiseSubAccountEntity;
import net.fashiongo.webadmin.data.entity.primary.QRetailerEntity;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class MapFranchiseSubAccountEntityRepositoryCustomImpl implements MapFranchiseSubAccountEntityRepositoryCustom {

	@PersistenceContext(unitName = "primaryEntityManager")
	private EntityManager entityManager;

	@Override
	@Transactional(transactionManager = "primaryTransactionManager")
	public long countByFranchiseMasterAccountId(Integer franchiseMasterAccountId) {
		JPAQuery<Long> jpaQuery = new JPAQuery<>(entityManager);
		QMapFranchiseSubAccountEntity MFSA = QMapFranchiseSubAccountEntity.mapFranchiseSubAccountEntity;

		return jpaQuery.select(MFSA)
				.from(MFSA)
				.where(
						MFSA.franchiseMasterAccountId.eq(franchiseMasterAccountId)
				).fetchCount();
	}

	@Override
	@Transactional(transactionManager = "primaryTransactionManager")
	public List<MapFranchiseSubAccountEntity> findAllByRetailerId(int retailerId) {
		JPAQuery<MapFranchiseSubAccountEntity> jpaQuery = new JPAQuery<>(entityManager);
		QMapFranchiseSubAccountEntity MFS = QMapFranchiseSubAccountEntity.mapFranchiseSubAccountEntity;
		QFranchiseMasterAccountEntity FMA = QFranchiseMasterAccountEntity.franchiseMasterAccountEntity;
		QRetailerEntity R = QRetailerEntity.retailerEntity;

		jpaQuery.select(MFS)
				.from(MFS)
				.innerJoin(MFS.franchiseMasterAccount,FMA).fetchJoin()
				.innerJoin(FMA.retailer,R).fetchJoin()
				.where(
						MFS.retailerId.eq(retailerId)
				);

		return jpaQuery.fetch();
	}

	@Override
	@Transactional(transactionManager = "primaryTransactionManager")
	public long countByRetailerId(int retailerId) {
		JPAQuery<Long> jpaQuery = new JPAQuery<>(entityManager);
		QMapFranchiseSubAccountEntity MFSA = QMapFranchiseSubAccountEntity.mapFranchiseSubAccountEntity;

		return jpaQuery.select(MFSA)
				.from(MFSA)
				.where(
						MFSA.retailerId.eq(retailerId)
				).fetchCount();
	}
}
