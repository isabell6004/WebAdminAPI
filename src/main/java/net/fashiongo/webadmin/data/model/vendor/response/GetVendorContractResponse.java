package net.fashiongo.webadmin.data.model.vendor.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.vendor.VendorContractResponse;

@Getter
public class GetVendorContractResponse {
    @JsonProperty("content")
    private VendorContractResponse vendorContract;

    public GetVendorContractResponse() {
    }

    @Builder
    public GetVendorContractResponse(VendorContractResponse vendorContract) {
        this.vendorContract = vendorContract;
    }
}
