package net.fashiongo.webadmin.data.entity.primary;

import lombok.Builder;
import lombok.Getter;
import net.fashiongo.common.conversion.LocalDateTimeConverter;
import net.fashiongo.webadmin.data.model.vendor.SetVendorContractParameter;
import net.fashiongo.webadmin.utility.Utility;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Getter
@Entity
@Table(name = "vendor_contract_history")
public class VendorContractHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vendor_contract_history_id")
    private Long id;

    @Column(name = "vendor_id")
    private Long vendorId;

    @Column(name = "contract_type_code")
    private Integer typeCode;

    @Column(name = "vendor_contract_plan_id")
    private Long planId;

    @Column(name = "setup_fee")
    private BigDecimal setupFee;

    @Column(name = "last_month_service_fee")
    private BigDecimal lastMonthServiceFee;

    @Column(name = "monthly_fee")
    private BigDecimal monthlyFee;

    @Column(name = "is_setup_fee_waived")
    private Boolean isSetupFeeWaived;

    @Column(name = "is_last_month_service_fee_waived")
    private Boolean isLastMonthServiceFeeWaived;

    @Column(name = "commission_rate", precision = 18, scale = 4)
    private BigDecimal commissionRate;

    @Column(name = "commission_base_date_code")
    private Integer commissionBaseDateCode;

    @Column(name = "monthly_item_cap")
    private Integer monthlyItemCap;

    @Column(name = "memo")
    private String memo;

    @Column(name = "contract_date_from")
    private LocalDateTime dateFrom;

    @Column(name = "contract_date_to")
    private LocalDateTime dateTo;

    @Column(name = "created_on", updatable = false)
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime createdOn;

    @Column(name = "created_by", updatable = false)
    private String createdBy;

    @Column(name = "modified_on")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime modifiedOn;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "account_executive_id")
    private Integer accountExecutiveId;

    @Column(name = "account_executive_by")
    private String accountExecutiveBy;

    public static VendorContractHistoryEntity create(SetVendorContractParameter request) {
        return builder()
                .vendorId(Long.valueOf(request.getWholeSalerID()))
                .typeCode(request.getContractTypeID() == null ? 0 : request.getContractTypeID())
                .setupFee(request.getSetupFee())
                .lastMonthServiceFee(request.getLastMonthServiceFee())
                .monthlyFee(request.getMonthlyFee())
                .monthlyItemCap(StringUtils.isEmpty(request.getUseModelStyle()) ? 0 : Integer.valueOf(request.getUseModelStyle()))
                .commissionRate(request.getCommissionRate() == null ? BigDecimal.valueOf(0) : request.getCommissionRate())
                .memo(StringUtils.isEmpty(request.getMemo()) ? "" : request.getMemo())
                .createdBy(Utility.getUsername())
                .createdOn(LocalDateTime.now())
                .dateFrom(LocalDate.parse(request.getVendorContractFrom(), DateTimeFormatter.ofPattern("M/d/yyyy")).atTime(0,0,0,0))
                .isSetupFeeWaived(Optional.ofNullable(request.getIsSetupFeeWaived()).orElse("").equalsIgnoreCase("1"))
                .isLastMonthServiceFeeWaived(Optional.ofNullable(request.getIsLastMonthServiceFeeWaived()).orElse("").equalsIgnoreCase("1"))
                .planId((long) (request.getVendorContractPlanID() == null ? 0 : request.getVendorContractPlanID()))
                .commissionBaseDateCode(request.getCommissionBaseDateCode() == null ? 0 : request.getCommissionBaseDateCode())
                .modifiedBy(Utility.getUsername())
                .modifiedOn((LocalDateTime.now()))

                .build();
    }

    public VendorContractHistoryEntity() {
    }

    @Builder
    public VendorContractHistoryEntity(Long id, Long vendorId, Integer typeCode, Long planId, BigDecimal setupFee, BigDecimal lastMonthServiceFee, BigDecimal monthlyFee, Boolean isSetupFeeWaived, Boolean isLastMonthServiceFeeWaived, BigDecimal commissionRate, Integer commissionBaseDateCode, Integer monthlyItemCap, String memo, LocalDateTime dateFrom, LocalDateTime dateTo, LocalDateTime createdOn, String createdBy, LocalDateTime modifiedOn, String modifiedBy, Integer accountExecutiveId, String accountExecutiveBy) {
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
