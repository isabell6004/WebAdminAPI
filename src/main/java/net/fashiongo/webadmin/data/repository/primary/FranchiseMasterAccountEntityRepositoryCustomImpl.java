package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.FranchiseMasterAccountEntity;
import net.fashiongo.webadmin.data.entity.primary.QFranchiseMasterAccountEntity;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
}
