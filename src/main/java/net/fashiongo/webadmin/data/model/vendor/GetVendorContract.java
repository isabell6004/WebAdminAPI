package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class GetVendorContract {
    private Long id;
    private Long vendorId;
    private Integer typeCode;
    private Long planId;
    private BigDecimal setupFee;
    private BigDecimal lastMonthServiceFee;
    private BigDecimal monthlyFee;
    private Boolean isSetupFeeWaived;
    private Boolean isLastMonthServiceFeeWaived;
    private BigDecimal commissionRate;
    private Integer commissionBaseDateCode;
    private Integer monthlyItemCap;
    private String memo;
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;
    private LocalDateTime createdOn;
    private String createdBy;
    private LocalDateTime modifiedOn;
    private String modifiedBy;
    private Integer accountExecutiveId;
    private String accountExecutiveBy;

    public GetVendorContract() {}

    @Builder
    public GetVendorContract(Long id, Long vendorId, Integer typeCode, Long planId, BigDecimal setupFee, BigDecimal lastMonthServiceFee,
                             BigDecimal monthlyFee, Boolean isSetupFeeWaived, Boolean isLastMonthServiceFeeWaived, BigDecimal commissionRate,
                             Integer commissionBaseDateCode, Integer monthlyItemCap, String memo, LocalDateTime dateFrom, LocalDateTime dateTo,
                             LocalDateTime createdOn, String createdBy, LocalDateTime modifiedOn, String modifiedBy, Integer accountExecutiveId, String accountExecutiveBy) {
        this.id = id;
        this.vendorId = vendorId;
        this.typeCode = typeCode;
        this.planId = planId;
        this.setupFee = setupFee;
        this.lastMonthServiceFee = lastMonthServiceFee;
        this.monthlyFee = monthlyFee;
        this.isSetupFeeWaived = isSetupFeeWaived;
        this.isLastMonthServiceFeeWaived = isLastMonthServiceFeeWaived;
        this.commissionRate = commissionRate;
        this.commissionBaseDateCode = commissionBaseDateCode;
        this.monthlyItemCap = monthlyItemCap;
        this.memo = memo;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.modifiedOn = modifiedOn;
        this.modifiedBy = modifiedBy;
        this.accountExecutiveId = accountExecutiveId;
        this.accountExecutiveBy = accountExecutiveBy;
    }
}
