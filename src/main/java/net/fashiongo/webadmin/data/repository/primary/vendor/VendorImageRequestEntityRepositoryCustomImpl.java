package net.fashiongo.webadmin.data.repository.primary.vendor;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.QVendorImageRequestEntity;
import net.fashiongo.webadmin.data.entity.primary.VendorImageRequestEntity;
import net.fashiongo.webadmin.data.model.vendor.VendorImage;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class VendorImageRequestEntityRepositoryCustomImpl implements VendorImageRequestEntityRepositoryCustom {

	@PersistenceContext(unitName = "primaryEntityManager")
	private EntityManager entityManager;

	@Override
	public List<VendorImage> findByWholeSalerID(Integer wid) {
		QVendorImageRequestEntity qRequest = QVendorImageRequestEntity.vendorImageRequestEntity;
		QVendorImageRequestEntity qRequest_SUB = QVendorImageRequestEntity.vendorImageRequestEntity;

		JPAQuery<VendorImage> query = new JPAQuery<>(entityManager);
		JPAQuery<Integer> query2 = new JPAQuery<>(entityManager);

		List<Integer> imageRequestIDs= query2.select(qRequest_SUB.imageRequestId.max()).from(qRequest_SUB).where(qRequest_SUB.wholesalerId.eq(wid).and(qRequest_SUB.deletedOn.isNull())).groupBy(qRequest_SUB.vendorImageTypeId).fetch();

		query.select(Projections.constructor(VendorImage.class,
				qRequest.vendorImageTypeId,
				qRequest.originalFileName))
				.from(qRequest)
				.where(qRequest.imageRequestId.in(imageRequestIDs))
				.orderBy(qRequest.vendorImageTypeId.asc());

		return query.fetch();
	}

	@Override
	public VendorImageRequestEntity findOneByWholeSalerIDAndVendorImageTypeID(Integer wid, Integer type) {
		QVendorImageRequestEntity qRequest = QVendorImageRequestEntity.vendorImageRequestEntity;

		JPAQuery<VendorImageRequestEntity> query = new JPAQuery<>(entityManager);

		query.select(qRequest)
				.from(qRequest)
				.where(qRequest.wholesalerId.eq(wid).and(qRequest.vendorImageTypeId.eq(type)));

		return query.fetchFirst();
	}

	@Override
	public VendorImageRequestEntity findOneByWholeSalerIDAndVendorImageTypeIDAndActiveTrue(Integer wid, Integer type) {
		QVendorImageRequestEntity qRequest = QVendorImageRequestEntity.vendorImageRequestEntity;

		JPAQuery<VendorImageRequestEntity> query = new JPAQuery<>(entityManager);

		query.select(qRequest)
				.from(qRequest)
				.where(qRequest.wholesalerId.eq(wid).and(qRequest.vendorImageTypeId.eq(type)).and(qRequest.active.eq(true)));

		return query.fetchFirst();
	}
}
