package net.fashiongo.webadmin.model.ads.response;

import lombok.Getter;
import net.fashiongo.webadmin.data.entity.primary.VendorEntity;

@Getter
public class AdVendorResponse {

    private Integer vendorId;

    private String vendorName;

    public static AdVendorResponse from(VendorEntity wholeSalerEntity) {

        AdVendorResponse response = new AdVendorResponse();

        response.vendorId = wholeSalerEntity.getVendor_id().intValue();
        response.vendorName = wholeSalerEntity.getName();

        return response;
    }
}
