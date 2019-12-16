package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.ConsolidationOrdersEntity;
import net.fashiongo.webadmin.data.entity.primary.QConsolidationOrdersEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class ConsolidationOrdersEntityRepositoryCustomImpl implements ConsolidationOrdersEntityRepositoryCustom {

	@PersistenceContext(unitName = "primaryEntityManager")
	private EntityManager entityManager;

	@Override
	public List<ConsolidationOrdersEntity> findByConsolidationId(int consolidationId) {
		QConsolidationOrdersEntity C = QConsolidationOrdersEntity.consolidationOrdersEntity;
		JPAQuery<ConsolidationOrdersEntity> jpaQuery = new JPAQuery<>(entityManager);

		return jpaQuery.select(C)
				.from(C)
				.where(
						C.consolidationID.eq(consolidationId)
				).fetch();
	}
}
