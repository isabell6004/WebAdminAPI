package net.fashiongo.webadmin.dao.primary.custom;

import static net.fashiongo.webadmin.model.primary.QVendor.vendor;
import static net.fashiongo.webadmin.model.primary.QVendorContent.vendorContent;
import static net.fashiongo.webadmin.model.primary.QVendorImageRequest.vendorImageRequest;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import net.fashiongo.webadmin.model.primary.Vendor;

/**
 * @author Kenny/Kyungwoo
 * @since 2019-05-02
 */
public class VendorRepositoryImpl extends QuerydslRepositorySupport implements VendorRepositoryCustom {

    public VendorRepositoryImpl() {
        super(Vendor.class);
    }
	
    @Override
    @PersistenceContext(unitName = "primaryEntityManager")
    public void setEntityManager(EntityManager entityManager) {
        super.setEntityManager(entityManager);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
	public List<Vendor> getEditorPickVendors() {
		return from(vendor)
				.where(vendor.active.eq(true),
						vendor.shopActive.eq(true),
//						vendor.orderActive.eq(true), //WebAdmins should see orderActive=false vendors too
						vendor.vendorType.eq(2), //Premium vendor
						vendor.wholeSalerId.in(
								from(vendorContent)
								.select(vendorContent.wholeSalerId)
								.where(vendorContent.statusId.eq(2), //Approved
										vendorContent.isActive.eq(true),
										vendorContent.isDeleted.ne(true))
								.fetch())
						.or(vendor.wholeSalerId.in(
								from(vendorImageRequest)
								.select(vendorImageRequest.wholeSalerID)
								.where(vendorImageRequest.isApproved.eq(true),
										vendorImageRequest.active.eq(true),
										vendorImageRequest.vendorImageTypeID.in(Arrays.asList(8,9)/*8=Image,9=Video*/))
								.fetch())))
				.fetch();
    }

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
	public String getCompanyNameByWholeSalerId(Integer wholeSalerId) {
		List<String> result = new JPAQuery<>(getEntityManager()).select(vendor.companyName)
				.from(vendor).where(vendor.wholeSalerId.eq(wholeSalerId)).fetch();

		if(result.isEmpty()) {
			return null;
		}

		return result.get(0);
	}
}
