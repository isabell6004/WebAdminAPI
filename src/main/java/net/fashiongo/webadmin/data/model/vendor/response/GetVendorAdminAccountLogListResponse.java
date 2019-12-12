package net.fashiongo.webadmin.data.model.vendor.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.entity.primary.VendorAdminLoginLogEntity;
import net.fashiongo.webadmin.data.model.Total;

import java.util.List;

@Getter
@Builder
public class GetVendorAdminAccountLogListResponse {
    @JsonProperty("Table")
    private Total total;
    @JsonProperty("Table1")
    private List<VendorAdminLoginLogEntity> vendorAdminLoginLogs;
}
