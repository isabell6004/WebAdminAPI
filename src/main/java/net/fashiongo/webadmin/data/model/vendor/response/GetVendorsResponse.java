package net.fashiongo.webadmin.data.model.vendor.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class GetVendorsResponse {
    @JsonProperty("contents")
    private List<VendorResponse> vendor;

    @JsonProperty("totalCount")
    private Long total;

    public GetVendorsResponse() {
    }

    @Builder
    public GetVendorsResponse(List<VendorResponse> vendor, Long total) {
        this.vendor = vendor;
        this.total = total;
    }
}
