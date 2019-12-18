package net.fashiongo.webadmin.data.entity.primary;

import lombok.*;
import net.fashiongo.webadmin.data.model.vendor.SetVendorContractParameter;
import net.fashiongo.webadmin.utility.Utility;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.asn1.cms.TimeStampAndCRL;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "Vendor_Contract")
public class VendorContractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "VendorContractID")
    private Integer vendorContractID;

    @Column(name = "WholeSalerID")
    private int wholeSalerID;

    @Column(name = "ContractTypeID")
    private int contractTypeID;

    @Column(name = "SetupFee")
    private BigDecimal setupFee;

    @Column(name = "LastMonthServiceFee")
    private BigDecimal lastMonthServiceFee;

    @Column(name = "PhotoPlanID")
    private int photoPlanID;

    @Column(name = "UseModel")
    private Boolean useModel;

    @Column(name = "UseModelStyle")
    private String useModelStyle;

    @Column(name = "MonthlyItems")
    private Integer monthlyItems;

    @Column(name = "CommissionRate")
    private BigDecimal commissionRate;

    @Column(name = "RepID")
    private Integer repID;

    @Column(name = "Perorder")
    private boolean perorder;

    @Column(name = "Memo")
    private String memo;

    @Column(name = "CreatedOn")
    private Timestamp createdOn;

    @Column(name = "CreatedBy")
    private String createdBy;

    @Column(name = "ModifiedOn")
    private Timestamp modifiedOn;

    @Column(name = "ModifiedBy")
    private String modifiedBy;

    @Column(name = "MonthlyFee")
    private BigDecimal monthlyFee;

    @Column(name = "FromContractDate")
    private Timestamp fromContractDate;

    @Column(name = "ToContractDate")
    private Timestamp toContractDate;

    @Column(name = "IsSetupFeeWaived")
    private Boolean isSetupFeeWaived;

    @Column(name = "IsLastMonthServiceFeeWaived")
    private Boolean isLastMonthServiceFeeWaived;

    @Column(name = "IsMonthlyFee")
    private Boolean isMonthlyFee = true;

    @Column(name = "vendor_contract_plan_id")
    private Integer vendorContractPlanId;

    @Column(name = "commission_base_date_code")
    private Integer commissionBaseDateCode;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "VendorContractID", referencedColumnName = "VendorContractID", insertable = false, updatable = false)
    private List<VendorContractDocumentEntity> vendorContractDocumentList;

    public static VendorContractEntity create(SetVendorContractParameter request) {
        return builder()
                .wholeSalerID(request.getWholeSalerID())
                .contractTypeID(request.getContractTypeID() == null ? 0 : request.getContractTypeID())
                .setupFee(request.getSetupFee())
                .lastMonthServiceFee(request.getLastMonthServiceFee())
                .monthlyFee(request.getMonthlyFee())
                // below three attributes is not deprecated
                .photoPlanID(request.getPhotoPlanID() == null ? 0 : request.getPhotoPlanID())
                .useModel(request.getUseModel() == null ? false : request.getUseModel())
                .useModelStyle(StringUtils.isEmpty(request.getUseModelStyle()) ? "" : request.getUseModelStyle())

                .monthlyItems(StringUtils.isEmpty(request.getUseModelStyle()) ? 0 : Integer.valueOf(request.getUseModelStyle()))
                .commissionRate(request.getCommissionRate() == null ? BigDecimal.valueOf(0) : request.getCommissionRate())
                .repID(request.getRepID())
                .perorder(request.getPerorder() == null ? false : request.getPerorder())
                .memo(StringUtils.isEmpty(request.getMemo()) ? "" : request.getMemo())
                .createdBy(Utility.getUsername())
                .createdOn(Timestamp.valueOf(LocalDateTime.now()))
                .fromContractDate(SetVendorContractParameter.getVendorContractFrom(request.getVendorContractFrom()))
                .isSetupFeeWaived(Optional.ofNullable(request.getIsSetupFeeWaived()).orElse("").equalsIgnoreCase("1"))
                .isLastMonthServiceFeeWaived(Optional.ofNullable(request.getIsLastMonthServiceFeeWaived()).orElse("").equalsIgnoreCase("1"))
                .vendorContractPlanId(request.getVendorContractPlanID() == null ? 0 : request.getVendorContractPlanID())
                .commissionBaseDateCode(request.getCommissionBaseDateCode() == null ? 0 : request.getCommissionBaseDateCode())

                .modifiedBy(Utility.getUsername())
                .modifiedOn(Timestamp.valueOf(LocalDateTime.now()))

                .build();
    }

    public VendorContractEntity updateEntity(SetVendorContractParameter request) {

        this.setWholeSalerID(request.getWholeSalerID());
        this.setContractTypeID(request.getContractTypeID() == null ? 0 : request.getContractTypeID());
        this.setSetupFee(request.getSetupFee());
        this.setLastMonthServiceFee(request.getLastMonthServiceFee());
        this.setMonthlyFee(request.getMonthlyFee());
        // below three attributes is not deprecated
        this.setPhotoPlanID(request.getPhotoPlanID() == null ? 0 : request.getPhotoPlanID());
        this.setUseModel(request.getUseModel() == null ? false : request.getUseModel());
        this.setUseModelStyle(StringUtils.isEmpty(request.getUseModelStyle()) ? "" : request.getUseModelStyle());

        this.setMonthlyItems(StringUtils.isEmpty(request.getUseModelStyle()) ? 0 : Integer.valueOf(request.getUseModelStyle()));
        this.setCommissionRate(request.getCommissionRate() == null ? BigDecimal.valueOf(0) : request.getCommissionRate());
        this.setRepID(request.getRepID());
        this.setPerorder(request.getPerorder() == null ? false : request.getPerorder());
        this.setMemo(StringUtils.isEmpty(request.getMemo()) ? "" : request.getMemo());
        this.setCreatedBy(Utility.getUsername());
        this.setCreatedOn(Timestamp.valueOf(LocalDateTime.now()));
        this.setFromContractDate(SetVendorContractParameter.getVendorContractFrom(request.getVendorContractFrom()));
        this.setIsSetupFeeWaived(Optional.ofNullable(request.getIsSetupFeeWaived()).orElse("").equalsIgnoreCase("1"));
        this.setIsLastMonthServiceFeeWaived(Optional.ofNullable(request.getIsLastMonthServiceFeeWaived()).orElse("").equalsIgnoreCase("1"));
        this.setVendorContractPlanId(request.getVendorContractPlanID() == null ? 0 : request.getVendorContractPlanID());
        this.setCommissionBaseDateCode(request.getCommissionBaseDateCode() == null ? 0 : request.getCommissionBaseDateCode());

        this.setModifiedBy(Utility.getUsername());
        this.setModifiedOn(Timestamp.valueOf(LocalDateTime.now()));

        return this;
    }

    public void terminate(String newFromDate) {
        this.setToContractDate(Timestamp.valueOf(SetVendorContractParameter.getVendorContractFrom(newFromDate).toLocalDateTime().minusDays(1)));
    }
}
