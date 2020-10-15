package net.fashiongo.webadmin.data.model.vendor.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.entity.primary.CodeVendorCapTypeEntity;
import net.fashiongo.webadmin.data.entity.primary.ListVendorBlockReasonEntity;
import net.fashiongo.webadmin.data.entity.primary.LogVendorHoldEntity;
import net.fashiongo.webadmin.data.entity.primary.VendorCapEntity;
import net.fashiongo.webadmin.data.model.vendor.VendorBlock;
import net.fashiongo.webadmin.data.model.vendor.VendorHistory;
import net.fashiongo.webadmin.data.model.vendor.VendorSister;

import java.util.List;

@Getter
@Builder
public class GetVendorSettingResponse {
    @JsonProperty(value = "VendorCap")
    private List<VendorCapEntity> vendorCap;

    @JsonProperty(value = "VendorCapDefault")
    private List<CodeVendorCapTypeEntity> vendorCapDefault;

    @JsonProperty(value = "VendorSelect")
    List<ActiveVendorResponse>vendor;

    @JsonProperty(value = "VendorSister")
    private List<VendorSister> vendorSister;

    @JsonProperty(value = "HoldVendor")
    private LogVendorHoldEntity holdVendor;

    @JsonProperty(value = "VendorHistory")
    private List<VendorHistory> vendorHistory;

    @JsonProperty("VendorSettingDetail")
    private VendorSettingDetailResponse vendorSettingDetailResponse;

    @JsonProperty("VendorBlockInfo")
    private VendorBlockInfoResponse vendorBlockInfoResponse;

    @JsonProperty("VendorBlockReason")
    private List<CodeVendorBlockReasonResponse> codeVendorBlockReasonList;
}
