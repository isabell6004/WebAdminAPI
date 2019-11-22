package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.QShipMethodEntity;
import net.fashiongo.webadmin.data.entity.primary.QWholeShipMethodEntity;
import net.fashiongo.webadmin.data.entity.primary.WholeShipMethodEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class WholeShipMethodEntityRepositoryCustomImpl implements WholeShipMethodEntityRepositoryCustom {

	@PersistenceContext(unitName = "primaryEntityManager")
	private EntityManager entityManager;

	@Override
	public List<WholeShipMethodEntity> findAllByWholeSalerIdWithShipMethod(int wholeSalerId) {
		QWholeShipMethodEntity WSM = QWholeShipMethodEntity.wholeShipMethodEntity;
		QShipMethodEntity SM = QShipMethodEntity.shipMethodEntity;
		JPAQuery<WholeShipMethodEntity> jpaQuery = new JPAQuery<>(entityManager);

		jpaQuery.select(WSM)
				.from(WSM)
				.leftJoin(WSM.shipMethod,SM).fetchJoin()
				.where(
						WSM.wholeSalerID.eq(wholeSalerId).and(WSM.active.eq(true))
				);
		return jpaQuery.fetch();
	}
}
