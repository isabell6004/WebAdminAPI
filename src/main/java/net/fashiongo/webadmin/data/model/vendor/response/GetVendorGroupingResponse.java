package net.fashiongo.webadmin.data.model.vendor.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.vendor.VendorGroupingSelete;
import net.fashiongo.webadmin.data.model.vendor.VendorGroupingUnSelete;

import java.util.List;

@Getter
@Builder
public class GetVendorGroupingResponse {
    @JsonProperty(value = "Table")
    List<VendorGroupingSelete> vendorGroupingSelete;
    @JsonProperty(value = "Table1")
    List<VendorGroupingUnSelete> vendorGroupingUnSelete;
}
