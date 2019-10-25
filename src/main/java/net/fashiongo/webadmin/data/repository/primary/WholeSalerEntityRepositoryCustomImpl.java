package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.QSimpleWholeSalerEntity;
import net.fashiongo.webadmin.data.model.vendor.Vendor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class WholeSalerEntityRepositoryCustomImpl implements WholeSalerEntityRepositoryCustom {

	@PersistenceContext(unitName = "primaryEntityManager")
	private EntityManager entityManager;

	@Override
	public List<Vendor> findAllByActiveTrueAndShopActiveTrueOrderByCompanyName() {
		JPAQuery<Vendor> jpaQuery = new JPAQuery<>(entityManager);
		QSimpleWholeSalerEntity W = QSimpleWholeSalerEntity.simpleWholeSalerEntity;

		jpaQuery.select(
					Projections.constructor(
							Vendor.class
							, W.wholeSalerId
							, W.companyName
					)
				).from(W)
				.where(W.active.eq(true).and(W.shopActive.eq(true)))
				.orderBy(W.companyName.asc());

		return jpaQuery.fetch();
	}

	@Override
	public List<Vendor> findAllByOrderActiveOrderByCompanyNameAsc(boolean isOrderActive) {
		JPAQuery<Vendor> jpaQuery = new JPAQuery<>(entityManager);
		QSimpleWholeSalerEntity W = QSimpleWholeSalerEntity.simpleWholeSalerEntity;

		jpaQuery.select(
				Projections.constructor(
						Vendor.class
						, W.wholeSalerId
						, W.companyName
				)
		).from(W)
				.where(W.orderActive.eq(isOrderActive))
				.orderBy(W.companyName.asc());

		return jpaQuery.fetch();
	}
}
