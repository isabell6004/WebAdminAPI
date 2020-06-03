package net.fashiongo.webadmin.data.model.vendor;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class VendorContractHistoryList {

    private Long id;
    private Integer contractTypeCode;
    private BigDecimal setupFee;
    private boolean setupFeeWaived;
    private BigDecimal lastMonthServiceFee;
    private boolean lastMonthServiceFeeWaived;
    private BigDecimal monthlyFee;
    private BigDecimal commissionRate;
    private LocalDateTime contractDateFrom;
    private LocalDateTime contractDateTo;
    private String createdBy;
    private LocalDateTime expiredDate;
    private String accountExecutiveBy;

    public VendorContractHistoryList() {
    }

    @Builder
    public VendorContractHistoryList(Long id, Integer contractTypeCode, BigDecimal setupFee, boolean setupFeeWaived, BigDecimal lastMonthServiceFee, boolean lastMonthServiceFeeWaived, BigDecimal monthlyFee, BigDecimal commissionRate, LocalDateTime contractDateFrom, LocalDateTime contractDateTo, String createdBy, LocalDateTime expiredDate, String accountExecutiveBy) {
        this.id = id;
        this.contractTypeCode = contractTypeCode;
        this.setupFee = setupFee;
        this.setupFeeWaived = setupFeeWaived;
        this.lastMonthServiceFee = lastMonthServiceFee;
        this.lastMonthServiceFeeWaived = lastMonthServiceFeeWaived;
        this.monthlyFee = monthlyFee;
        this.commissionRate = commissionRate;
        this.contractDateFrom = contractDateFrom;
        this.contractDateTo = contractDateTo;
        this.createdBy = createdBy;
        this.expiredDate = expiredDate;
        this.accountExecutiveBy = accountExecutiveBy;
    }
}
