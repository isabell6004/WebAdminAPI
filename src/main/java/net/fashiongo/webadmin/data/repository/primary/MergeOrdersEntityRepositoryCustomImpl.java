package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.MergeOrdersEntity;
import net.fashiongo.webadmin.data.entity.primary.QMergeOrdersEntity;
import net.fashiongo.webadmin.data.entity.primary.QSimpleWholeSalerEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

public class MergeOrdersEntityRepositoryCustomImpl implements MergeOrdersEntityRepositoryCustom {

	@PersistenceContext(unitName = "primaryEntityManager")
	private EntityManager entityManager;

	@Override
	public Optional<String> getMergeOrderWholesalerGuid(int orderId) {
		JPAQuery<MergeOrdersEntity> jpaQuery = new JPAQuery<>(entityManager);
		QMergeOrdersEntity MO = QMergeOrdersEntity.mergeOrdersEntity;
		QSimpleWholeSalerEntity W = QSimpleWholeSalerEntity.simpleWholeSalerEntity;

		jpaQuery.select(MO)
				.from(MO)
				.innerJoin(MO.wholeSaler,W).fetchJoin()
				.where(MO.mergeID.eq(orderId));

		return Optional.ofNullable(jpaQuery.fetchOne()).map(mergeOrdersEntity -> mergeOrdersEntity.getWholeSaler().getWholeSalerGUID());
	}
}
