package net.fashiongo.webadmin.data.model.vendor.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
public class VendorContentVendorResponse {
    @JsonProperty("WholeSalerId")
    private Integer wholeSalerId;
    @JsonProperty("CompanyName")
    private String companyName;

    public VendorContentVendorResponse() {
    }

    @Builder
    public VendorContentVendorResponse(Integer wholeSalerId, String companyName) {
        this.wholeSalerId = wholeSalerId;
        this.companyName = companyName;
    }
}
