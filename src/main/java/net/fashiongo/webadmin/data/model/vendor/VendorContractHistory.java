package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class VendorContractHistory {
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
    private Integer accountExecutiveId;
    private String accountExecutiveBy;

    public VendorContractHistory() {
    }

    @Builder
    public VendorContractHistory(Long id, Long vendorId, Integer typeCode, Long planId, BigDecimal setupFee, BigDecimal lastMonthServiceFee, BigDecimal monthlyFee, Boolean isSetupFeeWaived, Boolean isLastMonthServiceFeeWaived, BigDecimal commissionRate, Integer commissionBaseDateCode, Integer monthlyItemCap, String memo, LocalDateTime dateFrom, LocalDateTime dateTo, Integer accountExecutiveId, String accountExecutiveBy) {
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
        this.accountExecutiveId = accountExecutiveId;
        this.accountExecutiveBy = accountExecutiveBy;
    }
}
