package net.fashiongo.webadmin.data.model.vendor.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class VendorContentVendorResponse {
    private Integer wholeSalerId;
    private String companyName;

    public VendorContentVendorResponse() {
    }

    @Builder
    public VendorContentVendorResponse(Integer wholeSalerId, String companyName) {
        this.wholeSalerId = wholeSalerId;
        this.companyName = companyName;
    }
}
