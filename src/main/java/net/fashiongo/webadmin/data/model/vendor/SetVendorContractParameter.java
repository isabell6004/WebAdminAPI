package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class SetVendorContractParameter {

    @JsonProperty(value = "VendorContractID")
    private Integer vendorContractID;

    @JsonProperty(value = "WholeSalerID")
    private Integer wholeSalerID;

    @JsonProperty(value = "ContractTypeID")
    private Integer contractTypeID;

    @JsonProperty(value = "SetupFee")
    private BigDecimal setupFee;

    @JsonProperty(value = "LastMonthServiceFee")
    private BigDecimal lastMonthServiceFee;

    @JsonProperty(value = "MonthlyFee")
    private BigDecimal monthlyFee;

    @JsonProperty(value = "PhotoPlanID")
    private Integer photoPlanID;

    @JsonProperty(value = "UseModel")
    private Boolean useModel;

    @JsonProperty(value = "UseModelStyle")
    private String useModelStyle;

    @JsonProperty(value = "MonthlyItems")
    private Integer monthlyItems;

    @JsonProperty(value = "CommissionRate")
    private BigDecimal commissionRate;

    @JsonProperty(value = "RepID")
    private Integer repID;

    @JsonProperty(value = "Perorder")
    private Boolean perorder;

    @JsonProperty(value = "VendorContractFrom")
    private String vendorContractFrom;

    @JsonProperty(value = "VendorContractRowAdd")
    private Boolean vendorContractRowAdd;

    @JsonProperty(value = "Memo")
    private String memo;

    @JsonProperty(value = "IsSetupFeeWaived")
    private String isSetupFeeWaived;

    @JsonProperty(value = "IsLastMonthServiceFeeWaived")
    private String isLastMonthServiceFeeWaived;

    @JsonProperty(value = "VendorContractPlanID")
    private Integer vendorContractPlanID;

    @JsonProperty(value = "CommissionBaseDateCode")
    private Integer commissionBaseDateCode;
}
