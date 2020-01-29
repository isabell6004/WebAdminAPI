package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
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
    private LocalDateTime fromContractDate;

    @JsonProperty(value = "ToContractDate")
    private LocalDateTime toContractDate;

    @JsonProperty(value = "CreatedBy")
    private String createdBy;

    public VendorContractHistory() {
    }

    public VendorContractHistory(Integer contractTypeID, Integer vendorContractID, BigDecimal setupFee, Boolean isSetupFeeWaived, BigDecimal lastMonthServiceFee, Boolean isLastMonthServiceFeeWaived, BigDecimal monthlyFee, Integer photoPlanID, String useModelStyle, BigDecimal commissionRate, boolean perorder, Timestamp fromContractDate, Timestamp toContractDate, String createdBy) {
        this.contractTypeID = contractTypeID;
        this.vendorContractID = vendorContractID;
        this.setupFee = setupFee;
        this.isSetupFeeWaived = isSetupFeeWaived;
        this.lastMonthServiceFee = lastMonthServiceFee;
        this.isLastMonthServiceFeeWaived = isLastMonthServiceFeeWaived;
        this.monthlyFee = monthlyFee;
        this.photoPlanID = photoPlanID;
        this.useModelStyle = useModelStyle;
        this.commissionRate = commissionRate;
        this.perorder = perorder;
        this.fromContractDate = fromContractDate == null ? null : fromContractDate.toLocalDateTime();
        this.toContractDate = toContractDate == null ? null : toContractDate.toLocalDateTime();
        this.createdBy = createdBy;
    }
}
