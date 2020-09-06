package net.fashiongo.webadmin.model.product;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * The type Product.
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product {

    private int productId;

    private int vendorId;

    private String styleNo;

    private boolean active;

    private String stockAvailability;

    private int vendorCategoryId;

    private int categoryId;

    private int parentCategoryId;

    private int parentParentCategoryId;

    private BigDecimal unitPrice; // 단가

    @JsonProperty("unitPrice1")
    private BigDecimal listingPrice; // 권장 소비자 가격

    private String itemName;

    private String imageUrl;    // not yet implemented

    private int sizeId;

    private int packId;

    private int sortNo;

    private int minQty;

    private boolean eventColor;

    private String description;

    private String madeIn;

    private String fabricDescription;

    private String howToUse;

    private String ingredients;

    private int fabricId;

    private String keywords;

    private int bodySizeId;

    private int patternId;

    private int lengthId;

    private int styleId;

    private String inActive;

    private int cateId; //supplying vendor id

    private String productMasterColors;

    private int colorCount;

    private boolean fashionGoExclusive;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime activatedOn;

    private String activatedBy;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime createdOn;

    private String createdBy;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime modifiedOn;

    private String modifiedBy;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime availableOn;

    private Vendor vendor;

    private Category category;

    private VendorCategory vendorCategory;

    private Size size;

    private Pack pack;

    private Fabric fabric;

    private Label label;

    private Style style;

    private Pattern pattern;

    private Length length;

    private BodySize bodySize;

    private List<Image> images;

    private List<CrossSelling> crossSells;

    private Memo memo;

    private List<Inventory> inventories;

    /**
     * The type Vendor.
     */
    @Getter
    public static class Vendor {
        private String name;
    }

    /**
     * The type Category.
     */
    @Getter
    public static class Category {
        private String categoryName;
        private String parentCategoryName;
        private String parentParentCategoryName;
    }

    /**
     * The type Vendor category.
     */
    @Getter
    public static class VendorCategory {
        private String vendorCategoryName;
    }

    /**
     * The type Size.
     */
    @Getter
    public static class Size {
        private String sizes;
    }

    /**
     * The type Pack.
     */
    @Getter
    public static class Pack {
        private String packs;
    }

    /**
     * The type Fabric.
     */
    @Getter
    public static class Fabric {
        private String fabricName;
    }

    /**
     * The type Label.
     */
    @Getter
    public static class Label {
        private String labelTypeName;
    }

    /**
     * The type Style.
     */
    @Getter
    public static class Style {
        private String styleName;
    }

    /**
     * The type Pattern.
     */
    @Getter
    public static class Pattern {
        private String patternName;
    }

    /**
     * The type Length.
     */
    @Getter
    public static class Length {
        private String lengthName;
    }

    /**
     * The type Body size.
     */
    @Getter
    public static class BodySize {
        private String bodySizeName;
    }

    /**
     * The type Image.
     */
    @Getter
    public static class Image {
        private String imageUrl;
        private int sortNo;
        private int colorId;
    }

    /**
     * The type Cross selling.
     */
    @Getter
    public static class CrossSelling {
        private int crossProductId;
        private int sortNo;
    }

    /**
     * The type Memo.
     */
    @Getter
    public static class Memo {
        private String memo;
    }

    /**
     * The type Inventory.
     */
    @Getter
    public static class Inventory {
        private int colorId;
        private String sizeName;
        private boolean active;
        private boolean available;
        private int availableQty;
        private int statusCode; // change enum?
        private int threshold;
        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate availableOn;

    }
}
