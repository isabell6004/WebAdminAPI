package net.fashiongo.webadmin.data.model.vendor.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class GetVendorResponse {
    @JsonProperty("content")
    private VendorResponse vendor;

    public GetVendorResponse() {
    }

    @Builder
    public GetVendorResponse(VendorResponse vendor) {
        this.vendor = vendor;
    }
}
