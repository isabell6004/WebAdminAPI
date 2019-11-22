package net.fashiongo.webadmin.data.model.vendor.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.vendor.ListSocialMedia;
import net.fashiongo.webadmin.data.model.vendor.VendorDetailInfo;
import net.fashiongo.webadmin.data.model.vendor.VendorNameHistoryLog;
import net.fashiongo.webadmin.data.model.vendor.VendorPayoutInfo;

import java.util.List;

@Getter
@Builder
public class GetVendorBasicInfoResponse {
    @JsonProperty("Table")
    private List<VendorDetailInfo> vendorDetailInfoList;

    @JsonProperty("Table1")
    private List<VendorNameHistoryLog> vendorNameHistoryLogList;

    @JsonProperty("Table2")
    private List<ListSocialMedia> listSocialMediaList;

    @JsonProperty("Table3")
    private List<VendorPayoutInfo> vendorPayoutInfoList;
}
