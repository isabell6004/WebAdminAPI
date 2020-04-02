package net.fashiongo.webadmin.model.pojo.ad.response;

import lombok.Getter;
import net.fashiongo.webadmin.data.entity.primary.VendorCategoryEntity;

import java.util.Objects;

@Getter
public class AdVendorCategoryResponse {

    private Integer wholesalerId;

    private Integer vendorCategoryId;

    private String categoryName;

    private Long productCount;

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
