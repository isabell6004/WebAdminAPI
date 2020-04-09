package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.common.conversion.LocalDateTimeConverter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class ContractPlansResponse {
    private Integer vendorContractPlanId;
    private String contractPlanName;
    private Integer contractTypeCode;
    private BigDecimal setupFee;
    private BigDecimal lastMonthServiceFee;
    private BigDecimal monthlyFee;
    private Integer monthlyItemCap;
    private BigDecimal commissionRate;
    private Integer commissionBaseDateCode;
    private String memo;
    private Boolean isEditable;
    private Boolean isActive;
    private LocalDateTime createdOn;
    private String createdBy;
    private LocalDateTime modifiedOn;
    private String modifiedBy;

    public ContractPlansResponse() {}

    @Builder
    public ContractPlansResponse(Integer vendorContractPlanId, String contractPlanName, Integer contractTypeCode, BigDecimal setupFee,
                                 BigDecimal lastMonthServiceFee, BigDecimal monthlyFee, Integer monthlyItemCap, BigDecimal commissionRate,
                                 Integer commissionBaseDateCode, String memo, Boolean isEditable, Boolean isActive, LocalDateTime createdOn,
                                 String createdBy, LocalDateTime modifiedOn, String modifiedBy) {
        this.vendorContractPlanId = vendorContractPlanId;
        this.contractPlanName = contractPlanName;
        this.contractTypeCode = contractTypeCode;
        this.setupFee = setupFee;
        this.lastMonthServiceFee = lastMonthServiceFee;
        this.monthlyFee = monthlyFee;
        this.monthlyItemCap = monthlyItemCap;
        this.commissionRate = commissionRate;
        this.commissionBaseDateCode = commissionBaseDateCode;
        this.memo = memo;
        this.isEditable = isEditable;
        this.isActive = isActive;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.modifiedOn = modifiedOn;
        this.modifiedBy = modifiedBy;
    }
}
