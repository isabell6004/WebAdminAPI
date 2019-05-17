package net.fashiongo.webadmin.model.photostudio;

import lombok.*;
import org.apache.commons.collections4.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by jinwoo on 2019. 2. 6..
 */
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
public class PageViewDailyReport {

    private Date orderSubmitDate;

    private String vendorName;

    private String categoryName;

    private String packageName;

    private String inputQuantityType;

    private Integer totalStyleCount = 0;

    private Integer totalAdditionalColorCount = 0;

    private Integer totalAdditionalColorSetCount = 0;

    private Integer totalMovieClipCount = 0;

    private Integer totalBaseColorSetCount = 0;

    private Integer totalModelSwatchCount = 0;

    private Integer totalNewMovieClipCount = 0;

    private Integer totalColorSwatchCount = 0;

    private Date pickDate;

    private String modelName;

    private String promotionCode;

    public static List<PageViewDailyReport> build(List<PhotoCart> photoCarts) {

        List<PageViewDailyReport> buildedObjects = new ArrayList<>();
        if (CollectionUtils.isEmpty(photoCarts)) {
            return buildedObjects;
        }

        for (PhotoCart photoCart : photoCarts) {
            if (CollectionUtils.isEmpty(photoCart.getCartDetails())) {

                PageViewDailyReport report = new PageViewDailyReport().toBuilder()
                        .orderSubmitDate(photoCart.getCreatedOn())
                        .vendorName(photoCart.getWholeSalerCompanyName())
                        .categoryName((photoCart.getCategory() == null) ? "" : photoCart.getCategory().getCategoryName())
                        .packageName((photoCart.getPackageInfo() == null) ? "" : photoCart.getPackageInfo().getName())
                        .inputQuantityType(photoCart.getPhotoStudioOrderType())
                        .pickDate(photoCart.getPhotoshootDate())
                        .modelName((photoCart.getModel() == null) ? "" : photoCart.getModel().getModelName())
                        .promotionCode((photoCart.getDiscount() == null) ? "" : photoCart.getDiscount().getDiscountName())
                        .build();
                buildedObjects.add(report);
            } else {
                buildedObjects = photoCart.getCartDetails().stream().map((detail) -> {
                    PageViewDailyReport report = new PageViewDailyReport().toBuilder()
                            .orderSubmitDate(photoCart.getCreatedOn())
                            .vendorName(photoCart.getWholeSalerCompanyName())
                            .categoryName((photoCart.getCategory() == null) ? "" : photoCart.getCategory().getCategoryName())
                            .packageName((photoCart.getPackageInfo() == null) ? "" : photoCart.getPackageInfo().getName())
                            .inputQuantityType(photoCart.getPhotoStudioOrderType())
                            .totalStyleCount(Optional.ofNullable(detail.getStyleQty()).orElse(0))
                            .totalAdditionalColorCount(Optional.ofNullable(detail.getColorQty()).orElse(0))
                            .totalAdditionalColorSetCount(Optional.ofNullable(detail.getColorSetQty()).orElse(0))
                            .totalMovieClipCount(Optional.ofNullable(detail.getMovieQty()).orElse(0))
                            .totalBaseColorSetCount(Optional.ofNullable(detail.getBaseColorSetQty()).orElse(0))
                            .totalModelSwatchCount(Optional.ofNullable(detail.getModelSwatchQty()).orElse(0))
                            .totalNewMovieClipCount(Optional.ofNullable(detail.getMovieClipQty()).orElse(0))
                            .totalColorSwatchCount(Optional.ofNullable(detail.getColorSwatchQty()).orElse(0))
                            .pickDate(photoCart.getPhotoshootDate())
                            .modelName((photoCart.getModel() == null) ? "" : photoCart.getModel().getModelName())
                            .promotionCode((photoCart.getDiscount() == null) ? "" : photoCart.getDiscount().getDiscountName())
                            .build();
                    return report;
                }).collect(Collectors.toList());
            }
        }

        return buildedObjects;
    }
}
