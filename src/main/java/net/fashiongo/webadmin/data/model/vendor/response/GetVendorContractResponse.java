package net.fashiongo.webadmin.data.model.vendor.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.vendor.GetVendorContract;

@Getter
public class GetVendorContractResponse {
    @JsonProperty("content")
    private GetVendorContract vendorContract;

    public GetVendorContractResponse() {
    }

    @Builder
    public GetVendorContractResponse(GetVendorContract vendorContract) {
        this.vendorContract = vendorContract;
    }
}
