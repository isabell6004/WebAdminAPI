package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class SetVendorSettingParameter {
    @JsonProperty(value = "wid")
    private Integer wid;

    @JsonProperty(value = "AdminAccount")
    private Integer adminAccount;

    @JsonProperty(value = "VendorCategory")
    private Integer vendorCategory;

    @JsonProperty(value = "FraudReport")
    private Integer fraudReport;

    @JsonProperty(value = "Item")
    private Integer item;

    @JsonProperty(value = "AdminAccountID")
    private Integer adminAccountID;

    @JsonProperty(value = "VendorCategoryID")
    private Integer vendorCategoryID;

    @JsonProperty(value = "ActualOpenDateTemp")
    private String actualOpenDateTemp;

    @JsonProperty(value = "FraudReportID")
    private Integer fraudReportID;

    @JsonProperty(value = "ItemID")
    private Integer itemID;

    @JsonProperty(value = "vendorBasicInfo")
    private String vendorBasicInfo;

    @JsonProperty(value = "DirNameTemp")
    private String dirNameTemp;

    @JsonProperty(value = "PayoutSchedule")
    private Integer payoutSchedule;

    @JsonProperty(value = "PayoutScheduleWM")
    private Integer payoutScheduleWM;

    @JsonProperty(value = "MaxPayoutPerDay")
    private Integer maxPayoutPerDay;

    @JsonProperty(value = "PayoutCount")
    private Integer payoutCount;

    @JsonProperty(value = "IsBlock")
    private Boolean isBlock;

    @JsonProperty(value = "BlockReasonId")
    private Long blockReasonId;

    @JsonProperty(value = "IsAdBlock")
    private Boolean isAdBlock;

    @JsonProperty(value = "AdBlockReasonId")
    private Long adBlockReasonId;

    @JsonProperty(value = "IsPayoutBlock")
    private Boolean isPayoutBlock;

    @JsonProperty(value = "PayoutBlockReasonId")
    private Long PayoutBlockReasonId;

    @JsonProperty(value = "IsPayoutScheduleLock")
    private Boolean isPayoutScheduleLock;
}
