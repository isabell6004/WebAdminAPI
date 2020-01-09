package net.fashiongo.webadmin.data.model.vendor.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.vendor.VendorGroupingSelected;
import net.fashiongo.webadmin.data.model.vendor.VendorGroupingUnselect;

import java.util.List;

@Getter
@Builder
public class GetVendorGroupingResponse {
    @JsonProperty(value = "Table")
    List<VendorGroupingSelected> vendorGroupingSelete;
    @JsonProperty(value = "Table1")
    List<VendorGroupingUnselect> vendorGroupingUnSelete;
}
