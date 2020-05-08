package net.fashiongo.webadmin.data.model.vendor.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.vendor.VendorSeoInfoResponse;

@Getter

public class GetVendorSeoInfoResponse {
	
    @JsonProperty("seo")
    private VendorSeoInfoResponse vendorSeoInfo;

    public GetVendorSeoInfoResponse() {
    }

    @Builder
    public GetVendorSeoInfoResponse(VendorSeoInfoResponse vendorSeoInfo) {
        this.vendorSeoInfo = vendorSeoInfo;
    }
}
