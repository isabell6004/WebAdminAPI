package net.fashiongo.webadmin.model.photostudio;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class PhotoPriceResponse {
    private Integer priceId;

    private String categoryName;

    private int packageId;

    private String packageName;

    private int priceTypeId;

    private String priceTypeName;

    private int photoshootType;

    private BigDecimal unitPrice;

    private Integer minQty;

    private LocalDateTime fromEffectiveDate;

    public static PhotoPriceResponse of(PhotoPrice photoPrice) {
        PhotoPackage aPackage = photoPrice.getPhotoPackage();
        PhotoCategory category = aPackage.getPhotoCategory();

        PhotoPriceResponse response = new PhotoPriceResponse();
        response.setPriceId(photoPrice.getPriceID());
        response.setPriceTypeId(photoPrice.getPriceTypeID());
        response.setPriceTypeName(photoPrice.getPriceTypeName());
        response.setPhotoshootType(photoPrice.getPhotoshootType());
        response.setUnitPrice(photoPrice.getUnitPrice());
        response.setMinQty(photoPrice.getMinQty());
        response.setPackageId(aPackage.getId());
        response.setFromEffectiveDate(photoPrice.get_fromEffectiveDate());
        response.setCategoryName(category.getCategoryName());
        response.setPackageName(aPackage.getName());
        return response;
    }
}
