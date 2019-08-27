package net.fashiongo.webadmin.data.model.sitemgmt.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.sitemgmt.CategoryCount;
import net.fashiongo.webadmin.data.model.sitemgmt.VendorSummary;

import java.util.List;

@Getter
@Builder
public class GetVendorListResponse {
    @JsonProperty("Table")
    private List<CategoryCount> categoryCountlist;

    @JsonProperty("Table1")
    private List<VendorSummary> vendorSummarylist;
}
