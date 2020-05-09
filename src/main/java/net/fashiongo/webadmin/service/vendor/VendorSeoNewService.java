package net.fashiongo.webadmin.service.vendor;

import net.fashiongo.webadmin.data.model.vendor.SetVendorSeoParameter;
import net.fashiongo.webadmin.data.model.vendor.VendorSeoInfoResponse;

public interface VendorSeoNewService {

	VendorSeoInfoResponse inquiryVendorSeo(Long vendorId);
	
	void createVendorSeo(Long vendorId, SetVendorSeoParameter request);

    void modifyVendorSeo(Long vendorId, SetVendorSeoParameter request);
	
}
