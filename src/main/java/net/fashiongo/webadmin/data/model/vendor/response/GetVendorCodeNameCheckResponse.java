package net.fashiongo.webadmin.data.model.vendor.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public class GetVendorCodeNameCheckResponse {
    @JsonProperty(value = "CodeNameCount")
    Long codeNameCount;
}
