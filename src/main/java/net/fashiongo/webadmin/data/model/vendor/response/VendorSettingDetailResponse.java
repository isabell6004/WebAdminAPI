package net.fashiongo.webadmin.data.model.vendor.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class VendorSettingDetailResponse {
    private Long id;
    private Long vendorId;
    private Integer statusCode;
    private Boolean isBlock;
    private Boolean isAdBlock;
    private Boolean isPayoutBlock;
    private Boolean isPayoutScheduleLock;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime openDate;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime closedDate;
    private String orderNotice;
    private String buyerNotice;
    private String sizeChart;
    private String sizeChartImageFileName;
    private Boolean isAllowVendorMainAnony;
    private Boolean isAllowImmediateShopping;
    private Boolean isAllowAccessUnverifiedBuyer;
    private Boolean isProductSortByLastUpdate;
    private BigDecimal minOrderByAmount;
    private Integer minOrderByQty;
    private BigDecimal extraCharge;
    private BigDecimal extraChargeAmountFrom;
    private BigDecimal extraChargeAmountTo;
    private Boolean isShowFeedback;
    private Boolean isShowEstimateShippingCharge;
    private Boolean isPaperless;
    private Boolean isConsolidation;
    private Boolean isNew;
    private Boolean isExclusive;
    private Integer capAccount;
    private Integer capCategory;
    private Integer capFraudReport;
    private Integer capItem;
    private String inHouseMemo;
    private String memo;
    private Boolean isUsePgService;
    private BigDecimal transactionFeeRate1;
    private BigDecimal transactionFeeRate2;
    private BigDecimal transactionFeeRate1Intl;
    private BigDecimal transactionFeeRate2Intl;
    private BigDecimal transactionFeeFixed;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdOn;
    private String createdBy;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime modifiedOn;
    private String modifiedBy;
}
