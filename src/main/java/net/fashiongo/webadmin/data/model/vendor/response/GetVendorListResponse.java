package net.fashiongo.webadmin.data.model.vendor.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.Total;
import net.fashiongo.webadmin.data.model.vendor.VendorList;

import java.util.List;

@Getter
@Builder
public class GetVendorListResponse {
    @JsonProperty(value = "Table")
    List<Total> recCnt;

    @JsonProperty(value = "Table1")
    List<VendorList> vendorList;
}
