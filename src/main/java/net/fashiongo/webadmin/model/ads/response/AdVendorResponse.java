package net.fashiongo.webadmin.model.ads.response;

import lombok.Getter;
import net.fashiongo.webadmin.data.entity.primary.SimpleWholeSalerEntity;

@Getter
public class AdVendorResponse {

    private Integer vendorId;

    private String vendorName;

    public static AdVendorResponse from(SimpleWholeSalerEntity wholeSalerEntity) {

        AdVendorResponse response = new AdVendorResponse();

        response.vendorId = wholeSalerEntity.getWholeSalerId();
        response.vendorName = wholeSalerEntity.getCompanyName();

        return response;
    }
}
