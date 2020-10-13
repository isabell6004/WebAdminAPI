package net.fashiongo.webadmin.data.model.vendor.response.mapper;

import net.fashiongo.webadmin.data.entity.primary.VendorEntity;
import net.fashiongo.webadmin.data.model.vendor.response.VendorContentVendorResponse;

public class VendorContentVendorResponseMapper {
    public static VendorContentVendorResponse create(VendorEntity vendor) {
        return VendorContentVendorResponse.builder()
                .wholeSalerId(vendor.getVendor_id().intValue())
                .companyName(vendor.getName())
                .build();
    }
}
