package net.fashiongo.webadmin.data.model.collection.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class CollectionVendorResponse {
    private int vendorId;

    private String vendorName;

    private String dirName;

    private String vendorType;

    private BigDecimal salesAmt;

    @Setter
    private BigDecimal adAmt;

    @Getter(AccessLevel.NONE)
    @JsonProperty("isConsolidation")
    private boolean isConsolidation;

    @Getter(AccessLevel.NONE)
    @JsonProperty("isRewardVendor")
    private boolean isRewardVendor;

    private BigDecimal onTimeShippingRate;

    private BigDecimal buyerRate;

    private LocalDateTime memberSince;

    private String specialVendorType;

    @Getter(AccessLevel.NONE)
    @JsonProperty("isFreeShipping")
    private boolean isFreeShipping;

    private BigDecimal minOrderDiscount;

    private BigDecimal minAmt;

    private String bannerImageUrl;
}
