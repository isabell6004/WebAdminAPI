package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.QMapFranchiseSubAccountEntity;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
}
