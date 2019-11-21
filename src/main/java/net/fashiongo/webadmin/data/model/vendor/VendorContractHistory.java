package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class VendorContractHistory {
    @JsonProperty(value = "ContractTypeID")
    private Integer contractTypeID;

    @JsonProperty(value = "VendorContractID")
    private Integer vendorContractID;

    @JsonProperty(value = "SetupFee")
    private BigDecimal setupFee;

    @JsonProperty(value = "IsSetupFeeWaived")
    private Boolean isSetupFeeWaived;

    @JsonProperty(value = "LastMonthServiceFee")
    private BigDecimal lastMonthServiceFee;

    @JsonProperty(value = "IsLastMonthServiceFeeWaived")
    private Boolean isLastMonthServiceFeeWaived;

    @JsonProperty(value = "MonthlyFee")
    private BigDecimal monthlyFee;

    @JsonProperty(value = "PhotoPlanID")
    private Integer photoPlanID;

    @JsonProperty(value = "UseModelStyle")
    private String useModelStyle;

    @JsonProperty(value = "CommissionRate")
    private BigDecimal commissionRate;

    @JsonProperty(value = "Perorder")
    private boolean perorder;

    @JsonProperty(value = "FromContractDate")
    private Timestamp fromContractDate;

    @JsonProperty(value = "ToContractDate")
    private Timestamp toContractDate;

    @JsonProperty(value = "CreatedBy")
    private String createdBy;
}
