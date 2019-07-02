package net.fashiongo.webadmin.model.photostudio;

import lombok.*;
import org.apache.commons.collections4.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by jinwoo on 2019. 2. 6..
 */
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter(AccessLevel.PRIVATE)
public class PageViewDailyReport {

    private LocalDateTime orderSubmitDate;

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

    private LocalDateTime pickDate;

    private String modelName;

    private String promotionCode;

    private Boolean orderComplete = false;

    public String getOrderComplete() {
        return this.orderComplete ? "Y" : "N";
    }

    public static List<PageViewDailyReport> build(List<PhotoCart> photoCarts, Map<Integer, PhotoOrder> photoOrderOfCartId) {

        List<PageViewDailyReport> buildedObjects = new ArrayList<>();
        if (CollectionUtils.isEmpty(photoCarts)) {
            return buildedObjects;
        }

        for (PhotoCart photoCart : photoCarts) {

            PageViewDailyReport report = new PageViewDailyReport().toBuilder()
                    .orderSubmitDate(photoCart.getCreatedOn())
                    .vendorName(photoCart.getWholeSalerCompanyName())
                    .categoryName((photoCart.getCategory() == null) ? "" : photoCart.getCategory().getCategoryName())
                    .packageName((photoCart.getPackageInfo() == null) ? "" : photoCart.getPackageInfo().getName())
                    .inputQuantityType(photoCart.getPhotoStudioOrderType())
                    .pickDate(photoCart.getPhotoshootDate())
                    .modelName((photoCart.getModel() == null) ? "" : photoCart.getModel().getModelName())
                    .promotionCode((photoCart.getDiscount() == null) ? "" : photoCart.getDiscount().getDiscountName())
                    .orderComplete(photoOrderOfCartId.containsKey(photoCart.getId()))
                    .build();

            if (!CollectionUtils.isEmpty(photoCart.getCartDetails())) {
                photoCart.getCartDetails().stream().forEach((detail) -> {
                    report.setTotalStyleCount(report.getTotalStyleCount() + Optional.ofNullable(detail.getStyleQty()).orElse(0));
                    report.setTotalAdditionalColorCount(report.getTotalAdditionalColorCount() + Optional.ofNullable(detail.getColorQty()).orElse(0));
                    report.setTotalAdditionalColorSetCount(report.getTotalAdditionalColorSetCount() + Optional.ofNullable(detail.getColorSetQty()).orElse(0));
                    report.setTotalMovieClipCount(report.getTotalMovieClipCount() + Optional.ofNullable(detail.getMovieQty()).orElse(0));
                    report.setTotalBaseColorSetCount(report.getTotalBaseColorSetCount() + Optional.ofNullable(detail.getBaseColorSetQty()).orElse(0));
                    report.setTotalModelSwatchCount(report.getTotalModelSwatchCount() + Optional.ofNullable(detail.getModelSwatchQty()).orElse(0));
                    report.setTotalNewMovieClipCount(report.getTotalNewMovieClipCount() + Optional.ofNullable(detail.getMovieClipQty()).orElse(0));
                    report.setTotalColorSwatchCount(report.getTotalColorSwatchCount() + Optional.ofNullable(detail.getColorSwatchQty()).orElse(0));
                });
            }

            buildedObjects.add(report);
        }

        return buildedObjects;
    }

}
