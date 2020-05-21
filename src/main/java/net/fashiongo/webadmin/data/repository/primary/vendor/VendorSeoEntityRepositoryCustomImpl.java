package net.fashiongo.webadmin.data.repository.primary.vendor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.querydsl.jpa.impl.JPAQuery;

import net.fashiongo.webadmin.data.entity.primary.QVendorSeoEntity;
import net.fashiongo.webadmin.data.entity.primary.VendorSeoEntity;


public class VendorSeoEntityRepositoryCustomImpl implements VendorSeoEntityRepositoryCustom {

    @PersistenceContext(unitName = "primaryEntityManager")
    private EntityManager entityManager;
    
	@Override
    public VendorSeoEntity findOneByWholesalerID(Integer vendorId) {
        QVendorSeoEntity W = QVendorSeoEntity.vendorSeoEntity;

        JPAQuery<VendorSeoEntity> query = new JPAQuery<>(entityManager);

        VendorSeoEntity result = new VendorSeoEntity();
        VendorSeoEntity result_null = new VendorSeoEntity();
        
        result =  query.select(W).from(W).where(W.vendorId.eq(vendorId))
        		 .fetchFirst();
        
		if(result == null) {
			
			result_null.setVendorSeoId(0);
			
			return result_null;
		} else {
			return result;
		}        
    }
}
