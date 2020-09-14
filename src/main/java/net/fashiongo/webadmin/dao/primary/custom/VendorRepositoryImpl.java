package net.fashiongo.webadmin.dao.primary.custom;

//import static net.fashiongo.webadmin.model.primary.QVendor.vendor;
import static net.fashiongo.webadmin.data.entity.primary.QVendorEntity.vendorEntity;
import static net.fashiongo.webadmin.data.entity.primary.QVendorSettingEntity.vendorSettingEntity;

import static net.fashiongo.webadmin.model.primary.QVendorContent.vendorContent;
import static net.fashiongo.webadmin.model.primary.QVendorImageRequest.vendorImageRequest;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.VendorEntity;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author Kenny/Kyungwoo
 * @since 2019-05-02
 */
public class VendorRepositoryImpl extends QuerydslRepositorySupport implements VendorRepositoryCustom {

    public VendorRepositoryImpl() {
        super(VendorEntity.class);
    }
	
    @Override
    @PersistenceContext(unitName = "primaryEntityManager")
    public void setEntityManager(EntityManager entityManager) {
        super.setEntityManager(entityManager);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
	public List<VendorEntity> getEditorPickVendors() {
		return from(vendorEntity)
				.where(vendorSettingEntity.statusCode.in(1,2,3),
						vendorSettingEntity.statusCode.in(2,3),
//						vendor.orderActive.eq(true), //WebAdmins should see orderActive=false vendors too
//						vendor.vendorType.eq(2), //Premium vendor
						vendorEntity.vendor_id.intValue().in(
								from(vendorContent)
								.select(vendorContent.wholeSalerId)
								.where(vendorContent.statusId.eq(2), //Approved
										vendorContent.isActive.eq(true),
										vendorContent.isDeleted.ne(true))
								.fetch())
						.or(vendorEntity.vendor_id.intValue().in(
								from(vendorImageRequest)
								.select(vendorImageRequest.wholeSalerID)
								.where(vendorImageRequest.isApproved.eq(true),
										vendorImageRequest.active.eq(true),
										vendorImageRequest.vendorImageTypeID.in(Arrays.asList(8,9)/*8=Image,9=Video*/))
								.fetch())))
				.orderBy(vendorEntity.name.asc())
				.fetch();
    }

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
	public String getCompanyNameByWholeSalerId(Integer wholeSalerId) {
		return new JPAQuery<>(getEntityManager()).select(vendorEntity.name)
				.from(vendorEntity).where(vendorEntity.vendor_id.intValue().eq(wholeSalerId)).fetchOne();
	}
}
