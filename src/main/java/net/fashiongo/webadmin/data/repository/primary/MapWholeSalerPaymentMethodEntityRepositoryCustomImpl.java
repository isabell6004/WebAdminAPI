package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.MapWholeSalerPaymentMethodEntity;
import net.fashiongo.webadmin.data.entity.primary.QCodePaymentMethodEntity;
import net.fashiongo.webadmin.data.entity.primary.QMapWholeSalerPaymentMethodEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class MapWholeSalerPaymentMethodEntityRepositoryCustomImpl implements MapWholeSalerPaymentMethodEntityRepositoryCustom{

	@PersistenceContext(unitName = "primaryEntityManager")
	private EntityManager entityManager;

	@Override
	public List<MapWholeSalerPaymentMethodEntity> findAllByWholeSalerIdWithCodePayment(int wholesalerId) {
		QMapWholeSalerPaymentMethodEntity MAP = QMapWholeSalerPaymentMethodEntity.mapWholeSalerPaymentMethodEntity;
		QCodePaymentMethodEntity P = QCodePaymentMethodEntity.codePaymentMethodEntity;
		JPAQuery<MapWholeSalerPaymentMethodEntity> jpaQuery = new JPAQuery<>(entityManager);

		jpaQuery.select(MAP)
				.from(MAP)
				.leftJoin(MAP.codePaymentMethod,P).fetchJoin()
				.where(MAP.wholeSalerID.eq(wholesalerId).and(P.active.eq(true)));

		return jpaQuery.fetch();
	}
}
