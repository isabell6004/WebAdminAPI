package net.fashiongo.webadmin.data.model.vendor.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class GetVendorAutocompleteResponse {
    @JsonProperty("contents")
    private List<VendorAutocompleteResponse> vendor;

    @JsonProperty("totalCount")
    private Long total;

    public GetVendorAutocompleteResponse() {
    }

    @Builder
    public GetVendorAutocompleteResponse(List<VendorAutocompleteResponse> vendor, Long total) {
        this.vendor = vendor;
        this.total = total;
    }
}
