package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class VendorContractResponse {
    @JsonProperty("VendorContractId")
    private Long vendorContractId;

    @JsonProperty("wholeSalerID")
    private Long wholeSalerID;

    @JsonProperty("contractTypeID")
    private Integer contractTypeID;

    @JsonProperty("vendorContractPlanId")
    private Long vendorContractPlanId;

    @JsonProperty("setupFee")
    private BigDecimal setupFee;

    @JsonProperty("lastMonthServiceFee")
    private BigDecimal lastMonthServiceFee;

    @JsonProperty("monthlyFee")
    private BigDecimal monthlyFee;

    @JsonProperty("isSetupFeeWaived")
    private Boolean isSetupFeeWaived;

    @JsonProperty("isLastMonthServiceFeeWaived")
    private Boolean isLastMonthServiceFeeWaived;

    @JsonProperty("commissionRate")
    private BigDecimal commissionRate;

    @JsonProperty("commissionBaseDateCode")
    private Integer commissionBaseDateCode;

    @JsonProperty("monthlyItems")
    private Integer monthlyItems;

    @JsonProperty("memo")
    private String memo;

    @JsonProperty("fromContractDate")
//    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime fromContractDate;

    @JsonProperty("toContractDate")
//    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime toContractDate;

    @JsonProperty("createdOn")
    private LocalDateTime createdOn;

    @JsonProperty("createdBy")
    private String createdBy;

    @JsonProperty("modifiedOn")
    private LocalDateTime modifiedOn;

    @JsonProperty("modifiedBy")
    private String modifiedBy;

    @JsonProperty("repID")
    private Integer repID;

    @JsonProperty("repBy")
    private String repBy;

    public VendorContractResponse() {}

    @Builder
    public VendorContractResponse(Long vendorContractId, Long wholeSalerID, Integer contractTypeID, Long vendorContractPlanId,
                                  BigDecimal setupFee, BigDecimal lastMonthServiceFee, BigDecimal monthlyFee, Boolean isSetupFeeWaived,
                                  Boolean isLastMonthServiceFeeWaived, BigDecimal commissionRate, Integer commissionBaseDateCode,
                                  Integer monthlyItems, String memo, LocalDateTime fromContractDate,LocalDateTime toContractDate,
                                  LocalDateTime createdOn, String createdBy, LocalDateTime modifiedOn, String modifiedBy, Integer repID, String repBy) {
        this.vendorContractId = vendorContractId;
        this.wholeSalerID = wholeSalerID;
        this.contractTypeID = contractTypeID;
        this.vendorContractPlanId = vendorContractPlanId;
        this.setupFee = setupFee;
        this.lastMonthServiceFee = lastMonthServiceFee;
        this.monthlyFee = monthlyFee;
        this.isSetupFeeWaived = isSetupFeeWaived;
        this.isLastMonthServiceFeeWaived = isLastMonthServiceFeeWaived;
        this.commissionRate = commissionRate;
        this.commissionBaseDateCode = commissionBaseDateCode;
        this.monthlyItems = monthlyItems;
        this.memo = memo;
        this.fromContractDate = fromContractDate;
        this.toContractDate = toContractDate;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.modifiedOn = modifiedOn;
        this.modifiedBy = modifiedBy;
        this.repID = repID;
        this.repBy = repBy;
    }

    public static VendorContractResponse create(GetVendorContract vendorContract) {
        return VendorContractResponse.builder()
                .vendorContractId(vendorContract.getId())
                .wholeSalerID(vendorContract.getVendorId())
                .contractTypeID(vendorContract.getTypeCode())
                .vendorContractPlanId(vendorContract.getPlanId())
                .setupFee(vendorContract.getSetupFee())
                .lastMonthServiceFee(vendorContract.getLastMonthServiceFee())
                .monthlyFee(vendorContract.getMonthlyFee())
                .isSetupFeeWaived(vendorContract.getIsSetupFeeWaived())
                .isLastMonthServiceFeeWaived(vendorContract.getIsLastMonthServiceFeeWaived())
                .commissionRate(vendorContract.getCommissionRate())
                .commissionBaseDateCode(vendorContract.getCommissionBaseDateCode())
                .monthlyItems(vendorContract.getMonthlyItemCap())
                .memo(vendorContract.getMemo())
                .fromContractDate(vendorContract.getDateFrom())
                .toContractDate(vendorContract.getDateTo())
                .createdOn(vendorContract.getCreatedOn())
                .createdBy(vendorContract.getCreatedBy())
                .modifiedOn(vendorContract.getModifiedOn())
                .modifiedBy(vendorContract.getModifiedBy())
                .repID(vendorContract.getAccountExecutiveId())
                .repBy(vendorContract.getAccountExecutiveBy())
                .build();
    }
}
