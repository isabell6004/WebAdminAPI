package net.fashiongo.webadmin.data.model.vendor.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.vendor.VendorListCSVResponse;

import java.util.List;

@Getter
@Builder
public class GetVendorListCSVResponse {
    @JsonProperty(value = "Table")
    List<VendorListCSVResponse> vendorListCSVResponse;
}
