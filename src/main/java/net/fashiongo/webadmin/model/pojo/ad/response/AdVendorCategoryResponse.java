package net.fashiongo.webadmin.model.pojo.ad.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import net.fashiongo.webadmin.data.entity.primary.VendorCategoryEntity;

import java.util.Objects;

@Getter
public class AdVendorCategoryResponse {

    @JsonProperty("WholeSalerID")
    private Integer wholesalerId;

    @JsonProperty("VendorCategoryID")
    private Integer vendorCategoryId;

    @JsonProperty("CategoryName")
    private String categoryName;

    @JsonProperty("ProductCount")
    private Long productCount;

    @JsonProperty("CategoryNameProductCount")
    private String categoryNameProductCount;

    private AdVendorCategoryResponse(Integer wholesalerId, Integer vendorCategoryId, String categoryName, Long productCount) {

        this.wholesalerId = wholesalerId;
        this.vendorCategoryId = vendorCategoryId;
        this.categoryName = categoryName;
        this.productCount = productCount;
        this.categoryNameProductCount = categoryName + " (" + productCount.toString() + ")";
    }

    public static AdVendorCategoryResponse of(VendorCategoryEntity vendorCategory, Long productCount) {

        if (Objects.isNull(vendorCategory))
            throw new IllegalArgumentException("invalid argument");

        if (Objects.isNull(productCount))
            productCount = 0L;

        return new AdVendorCategoryResponse(
                vendorCategory.getWholeSalerID(),
                vendorCategory.getVendorCategoryID(),
                vendorCategory.getCategoryName(),
                productCount
        );
    }
}
