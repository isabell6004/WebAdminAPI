package net.fashiongo.webadmin.dao.primary.custom;

import static net.fashiongo.webadmin.model.primary.QVendor.vendor;
import static net.fashiongo.webadmin.model.primary.QVendorContent.vendorContent;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.QueryResults;

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
		QueryResults<Vendor> list = from(vendor)
				.where(vendor.active.eq(true),
						vendor.shopActive.eq(true),
						vendor.orderActive.eq(true),
						vendor.vendorType.eq(2), //Premium vendor
						vendor.wholeSalerId.in(
								from(vendorContent)
								.where(vendorContent.statusId.eq(2), //Approved
										vendorContent.isActive.eq(true),
										vendorContent.isDeleted.ne(true))
								.select(vendorContent.wholeSalerId)
								.fetch() ))
				.fetchResults();
        return list.getResults()==null ? new ArrayList<Vendor>() : list.getResults();
    }
}
