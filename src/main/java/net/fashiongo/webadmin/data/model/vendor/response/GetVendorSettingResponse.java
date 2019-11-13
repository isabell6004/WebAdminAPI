package net.fashiongo.webadmin.data.model.vendor.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.entity.primary.CodeVendorCapTypeEntity;
import net.fashiongo.webadmin.data.entity.primary.ListVendorBlockReasonEntity;
import net.fashiongo.webadmin.data.entity.primary.LogVendorHoldEntity;
import net.fashiongo.webadmin.data.entity.primary.VendorBlockedEntity;
import net.fashiongo.webadmin.data.entity.primary.VendorCapEntity;
import net.fashiongo.webadmin.data.entity.primary.vendor.WholesalerCompanyEntity;
import net.fashiongo.webadmin.data.model.vendor.VendorHistory;
import net.fashiongo.webadmin.data.model.vendor.VendorSister;

import java.util.List;

@Getter
@Builder
public class GetVendorSettingResponse {
    @JsonProperty(value = "VendorCap")
    List<VendorCapEntity> vendorCap;

    @JsonProperty(value = "VendorCapDefault")
    List<CodeVendorCapTypeEntity> vendorCapDefault;

    @JsonProperty(value = "VendorBlock")
    List<VendorBlockedEntity> vendorBlock;

    @JsonProperty(value = "VendorBlockReason")
    List<ListVendorBlockReasonEntity> vendorBlockReason;

    @JsonProperty(value = "VendorSelect")
    List<WholesalerCompanyEntity> vendor;

    @JsonProperty(value = "VendorSister")
    List<VendorSister> vendorSister;

    @JsonProperty(value = "HoldVendor")
    List<LogVendorHoldEntity> holdVendor;

    @JsonProperty(value = "VendorHistory")
    List<VendorHistory> vendorHistory;
}
