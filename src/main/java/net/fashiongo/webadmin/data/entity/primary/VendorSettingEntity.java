package net.fashiongo.webadmin.data.entity.primary;

import lombok.Builder;
import lombok.Getter;
import net.fashiongo.common.conversion.LocalDateTimeConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "vendor_setting")
public class VendorSettingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vendor_setting_id")
    private Long id;

    @Column(name = "vendor_id")
    private Long vendorId;

    @Column(name = "status_code")
    private Integer statusCode;

    @Column(name = "is_block")
    private Boolean isBlock;

    @Column(name = "is_ad_block")
    private Boolean isAdBlock;

    @Column(name = "open_date")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime openDate;

    @Column(name = "closed_date")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime closedDate;

    @Column(name = "order_notice")
    private String orderNotice;

    @Column(name = "buyer_notice")
    private String buyerNotice;

    @Column(name = "sizechart")
    private String sizeChart;

    @Column(name = "sizechart_image_file_name")
    private String sizeChartImageFileName;

    @Column(name = "is_allow_vendormain_anony")
    private Boolean isAllowVendorMainAnony;

    @Column(name = "is_allow_immediate_shopping")
    private Boolean isAllowImmediateShopping;

    @Column(name = "is_allow_access_unverified_retailer")
    private Boolean isAllowAccessUnverifiedBuyer;

    @Column(name = "is_product_sort_by_lastupdate")
    private Boolean isProductSortByLastUpdate;

    @Column(name = "min_order_by_amount")
    private BigDecimal minOrderByAmount;

    @Column(name = "min_order_by_qty")
    private Integer minOrderByQty;

    @Column(name = "extra_charge")
    private BigDecimal extraCharge;

    @Column(name = "extra_charge_amount_from")
    private BigDecimal extraChargeAmountFrom;

    @Column(name = "extra_charge_amount_to")
    private BigDecimal extraChargeAmountTo;

    @Column(name = "is_show_feedback")
    private Boolean isShowFeedback;

    @Column(name = "is_show_estimate_shippingcharge")
    private Boolean isShowEstimateShippingCharge;

    @Column(name = "is_paperless")
    private Boolean isPaperless;

    @Column(name = "is_consolidation")
    private Boolean isConsolidation;

    @Column(name = "is_new")
    private Boolean isNew;

    @Column(name = "is_exclusive")
    private Boolean isExclusive;

    @Column(name = "cap_vendor_account")
    private Integer capAccount;

    @Column(name = "cap_vendor_category")
    private Integer capCategory;

    @Column(name = "cap_fraud_report")
    private Integer capFraudReport;

    @Column(name = "cap_item")
    private Integer capItem;

    @Column(name = "inhouse_memo")
    private String inHouseMemo;

    @Column(name = "memo")
    private String memo;

    @Column(name = "is_use_pg_service")
    private Boolean isUsePgService;

    @Column(name = "transaction_fee_rate1", precision = 18, scale = 4)
    private BigDecimal transactionFeeRate1;

    @Column(name = "transaction_fee_rate2", precision = 18, scale = 4)
    private BigDecimal transactionFeeRate2;

    @Column(name = "transaction_fee_rate1_intl", precision = 18, scale = 4)
    private BigDecimal transactionFeeRate1Intl;

    @Column(name = "transaction_fee_rate2_intl", precision = 18, scale = 4)
    private BigDecimal transactionFeeRate2Intl;

    @Column(name = "transaction_fee_fixed")
    private BigDecimal transactionFeeFixed;

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

    @Transient
    private Boolean isStatusCodeUpdated = false;

    @Transient
    private boolean isOpenNow = false;

    @Transient
    private Boolean isIsBlockUpdated = false;

    @Transient
    private Boolean isIsAdBlockUpdated = false;

//    @NotFound(action = NotFoundAction.IGNORE)
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "vendor_id", updatable = false, insertable = false)
//    private VendorEntity vendorEntity;

    public VendorSettingEntity() {
    }

    @Builder
    public VendorSettingEntity(Long vendorId, Integer statusCode, Boolean isBlock, Boolean isAdBlock, LocalDateTime openDate, LocalDateTime closedDate, String orderNotice, String buyerNotice, String sizeChart, String sizeChartImageFileName, Boolean isAllowVendorMainAnony, Boolean isAllowImmediateShopping, Boolean isAllowAccessUnverifiedBuyer, Boolean isProductSortByLastUpdate, BigDecimal minOrderByAmount, Integer minOrderByQty, BigDecimal extraCharge, BigDecimal extraChargeAmountFrom, BigDecimal extraChargeAmountTo, Boolean isShowFeedback, Boolean isShowEstimateShippingCharge, Boolean isPaperless, Boolean isConsolidation, Boolean isNew, Boolean isExclusive, Integer capAccount, Integer capCategory, Integer capFraudReport, Integer capItem, String inHouseMemo, String memo, Boolean isUsePgService, BigDecimal transactionFeeRate1, BigDecimal transactionFeeRate2, BigDecimal transactionFeeRate1Intl, BigDecimal transactionFeeRate2Intl, BigDecimal transactionFeeFixed, LocalDateTime createdOn, String createdBy, LocalDateTime modifiedOn, String modifiedBy, Boolean isStatusCodeUpdated, boolean isOpenNow, Boolean isIsBlockUpdated, Boolean isIsAdBlockUpdated/*, VendorEntity vendorEntity*/) {
        this.vendorId = vendorId;
        this.statusCode = statusCode;
        this.isBlock = isBlock;
        this.isAdBlock = isAdBlock;
        this.openDate = openDate;
        this.closedDate = closedDate;
        this.orderNotice = orderNotice;
        this.buyerNotice = buyerNotice;
        this.sizeChart = sizeChart;
        this.sizeChartImageFileName = sizeChartImageFileName;
        this.isAllowVendorMainAnony = isAllowVendorMainAnony;
        this.isAllowImmediateShopping = isAllowImmediateShopping;
        this.isAllowAccessUnverifiedBuyer = isAllowAccessUnverifiedBuyer;
        this.isProductSortByLastUpdate = isProductSortByLastUpdate;
        this.minOrderByAmount = minOrderByAmount;
        this.minOrderByQty = minOrderByQty;
        this.extraCharge = extraCharge;
        this.extraChargeAmountFrom = extraChargeAmountFrom;
        this.extraChargeAmountTo = extraChargeAmountTo;
        this.isShowFeedback = isShowFeedback;
        this.isShowEstimateShippingCharge = isShowEstimateShippingCharge;
        this.isPaperless = isPaperless;
        this.isConsolidation = isConsolidation;
        this.isNew = isNew;
        this.isExclusive = isExclusive;
        this.capAccount = capAccount;
        this.capCategory = capCategory;
        this.capFraudReport = capFraudReport;
        this.capItem = capItem;
        this.inHouseMemo = inHouseMemo;
        this.memo = memo;
        this.isUsePgService = isUsePgService;
        this.transactionFeeRate1 = transactionFeeRate1;
        this.transactionFeeRate2 = transactionFeeRate2;
        this.transactionFeeRate1Intl = transactionFeeRate1Intl;
        this.transactionFeeRate2Intl = transactionFeeRate2Intl;
        this.transactionFeeFixed = transactionFeeFixed;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.modifiedOn = modifiedOn;
        this.modifiedBy = modifiedBy;
        this.isStatusCodeUpdated = isStatusCodeUpdated;
        this.isOpenNow = isOpenNow;
        this.isIsBlockUpdated = isIsBlockUpdated;
        this.isIsAdBlockUpdated = isIsAdBlockUpdated;
//        this.vendorEntity = vendorEntity;
    }
}
