package net.fashiongo.webadmin.model.photostudio;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class PhotoUnitResponse {
    private Integer unitId;

    private String categoryName;

    private int packageId;

    private String packageName;

    private int priceTypeId;

    private String priceTypeName;

    private int photoshootType;

    private BigDecimal unit;

    private LocalDateTime fromEffectiveDate;

    public static PhotoUnitResponse of(PhotoUnit photoUnit) {
        PhotoPackage aPackage = photoUnit.getPhotoPackage();
        PhotoCategory category = aPackage.getPhotoCategory();

        PhotoUnitResponse response = new PhotoUnitResponse();
        response.setUnitId(photoUnit.getUnitID());
        response.setPriceTypeId(photoUnit.getPriceTypeID());
        response.setPriceTypeName(photoUnit.getPriceTypeName());
        response.setPhotoshootType(photoUnit.getPhotoshootType());
        response.setUnit(photoUnit.getUnit());
        response.setPackageId(aPackage.getId());
        response.setFromEffectiveDate(photoUnit.get_fromEffectiveDate());
        response.setCategoryName(category.getCategoryName());
        response.setPackageName(aPackage.getName());
        return response;
    }
}
