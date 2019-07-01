package net.fashiongo.webadmin.data.model.ad;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class AdPageSpot {
    @JsonProperty("SpotID")
    private Integer spotID;
    @JsonProperty("PageID")
    private Integer pageID;
    @JsonProperty("PageName")
    private String pageName;
    @JsonProperty("CategoryID")
    private Integer categoryID;
    @JsonProperty("BodySizeID")
    private Integer bodySizeID;
    @JsonProperty("SpotName")
    private String spotName;
    @JsonProperty("SpotDescription")
    private String spotDescription;
    @JsonProperty("Price1")
    private BigDecimal price1;
    @JsonProperty("Price2")
    private BigDecimal price2;
    @JsonProperty("Price3")
    private BigDecimal price3;
    @JsonProperty("Price4")
    private BigDecimal price4;
    @JsonProperty("Price5")
    private BigDecimal price5;
    @JsonProperty("Price6")
    private BigDecimal price6;
    @JsonProperty("Price7")
    private BigDecimal price7;
    @JsonProperty("Active")
    private Boolean active;
    @JsonProperty("IncludeVendorCategory")
    private Boolean includeVendorCategory;
    @JsonProperty("SpotInstanceCount")
    private Integer spotInstanceCount;
    @JsonProperty("BannerImage")
    private String bannerImage;
    @JsonProperty("CreatedOn")
    private LocalDateTime createdOn;
    @JsonProperty("CreatedBy")
    private String createdBy;
    @JsonProperty("ModifiedOn")
    private LocalDateTime modifiedOn;
    @JsonProperty("ModifiedBy")
    private String modifiedBy;
    @JsonProperty("BidEffectiveOn")
    private LocalDateTime bidEffectiveOn;
    @JsonProperty("MaxPurchasable")
    private Integer maxPurchasable;
    @JsonProperty("SpotItemCount")
    private Integer spotItemCount;
    @JsonProperty("IsBannerAd")
    private Boolean isBannerAd;
}
