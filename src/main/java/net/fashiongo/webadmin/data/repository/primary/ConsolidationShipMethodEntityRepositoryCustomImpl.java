package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.ConsolidationShipMethodEntity;
import net.fashiongo.webadmin.data.entity.primary.QConsolidationShipMethodEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

public class ConsolidationShipMethodEntityRepositoryCustomImpl implements ConsolidationShipMethodEntityRepositoryCustom {

	@PersistenceContext(unitName = "primaryEntityManager")
	private EntityManager entityManager;

	@Override
	public Optional<ConsolidationShipMethodEntity> findOneByIdAndActive(int consolidationShipMethodId, boolean isActive) {
		JPAQuery<ConsolidationShipMethodEntity> jpaQuery = new JPAQuery<>(entityManager);
		QConsolidationShipMethodEntity CS = QConsolidationShipMethodEntity.consolidationShipMethodEntity;

		jpaQuery.select(CS)
				.from(CS)
				.where(
						CS.consolidationShipMethodID.eq(consolidationShipMethodId)
						.and(CS.active.eq(isActive))
				);

		return Optional.ofNullable(
				jpaQuery.fetchOne()
		);
	}
}
