package net.fashiongo.webadmin.data.repository.primary.vendor;

import net.fashiongo.webadmin.data.entity.primary.VendorSeoEntity;

public interface VendorSeoEntityRepositoryCustom {

	VendorSeoEntity findOneByWholesalerID(Integer wholeSalerID);
	
}
