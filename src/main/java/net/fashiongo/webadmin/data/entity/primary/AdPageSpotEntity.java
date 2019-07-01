package net.fashiongo.webadmin.data.entity.primary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Ad_PageSpot")
public class AdPageSpotEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "SpotID")
    private Integer spotId;

    @Column(name = "PageID")
    private Integer pageId;

    @Column(name = "CategoryID")
    private Integer categoryId;

    @Column(name = "BodySizeID")
    private Integer bodySizeId;

    @Column(name = "SpotName")
    private String spotName;

    @Column(name = "SpotDescription")
    private String spotDescription;

    @Column(name = "Price1")
    private BigDecimal price1;

    @Column(name = "Price2")
    private BigDecimal price2;

    @Column(name = "Price3")
    private BigDecimal price3;

    @Column(name = "Price4")
    private BigDecimal price4;

    @Column(name = "Price5")
    private BigDecimal price5;

    @Column(name = "Price6")
    private BigDecimal price6;

    @Column(name = "Price7")
    private BigDecimal price7;

    @Column(name = "Active")
    private boolean active;

    @Column(name = "IncludeVendorCategory")
    private boolean includeVendorCategory;

    @Column(name = "SpotInstanceCount")
    private int spotInstanceCount;

    @Column(name = "BannerImage")
    private String bannerImage;

    @Column(name = "CreatedOn")
    private LocalDateTime createdOn;

    @Column(name = "CreatedBy")
    private String createdBy;

    @Column(name = "ModifiedOn")
    private LocalDateTime modifiedOn;

    @Column(name = "ModifiedBy")
    private String modifiedBy;

    @Column(name = "BidEffectiveOn")
    private LocalDateTime bidEffectiveOn;

    @Column(name = "SpotItemCount")
    private Integer spotItemCount;

    @Column(name = "MaxPurchasable")
    private Integer maxPurchasable;

    @Column(name = "IsBannerAd")
    private boolean isBannerAd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PageID", updatable = false, insertable = false)
    private AdPageEntity adPageEntity;
}
