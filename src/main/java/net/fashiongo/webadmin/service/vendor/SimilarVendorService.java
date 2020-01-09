package net.fashiongo.webadmin.service.vendor;

import net.fashiongo.webadmin.data.model.vendor.response.GetVendorGroupingResponse;

public interface SimilarVendorService {

    GetVendorGroupingResponse getVendorGroupings(Integer vendorId, String companyType, String vendorType, String keyword, String categorys, String alphabet);

    Integer setVendorGrouping(Integer vendorId, String saveIds, String deleteIds);
}
