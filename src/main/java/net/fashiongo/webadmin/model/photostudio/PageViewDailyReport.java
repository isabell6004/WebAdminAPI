package net.fashiongo.webadmin.model.photostudio;

import org.apache.commons.collections4.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jinwoo on 2019. 2. 6..
 */
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

    private Date pickDate;

    private String modelName;

    private String promotionCode;

    private PageViewDailyReport() {
    }

    public String getCategoryName() {
        return categoryName;
    }

    public PageViewDailyReport setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    public String getInputQuantityType() {
        return inputQuantityType;
    }

    public PageViewDailyReport setInputQuantityType(String inputQuantityType) {
        this.inputQuantityType = inputQuantityType;
        return this;
    }

    public String getModelName() {
        return modelName;
    }

    public PageViewDailyReport setModelName(String modelName) {
        this.modelName = modelName;
        return this;
    }

    public Date getOrderSubmitDate() {
        return orderSubmitDate;
    }

    public PageViewDailyReport setOrderSubmitDate(Date orderSubmitDate) {
        this.orderSubmitDate = orderSubmitDate;
        return this;
    }

    public String getPackageName() {
        return packageName;
    }

    public PageViewDailyReport setPackageName(String packageName) {
        this.packageName = packageName;
        return this;
    }

    public Date getPickDate() {
        return pickDate;
    }

    public PageViewDailyReport setPickDate(Date pickDate) {
        this.pickDate = pickDate;
        return this;
    }

    public String getPromotionCode() {
        return promotionCode;
    }

    public PageViewDailyReport setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
        return this;
    }

    public Integer getTotalAdditionalColorCount() {

        if(this.totalAdditionalColorCount == null || this.totalAdditionalColorCount == 0) {
            return 0;
        }

        return totalAdditionalColorCount;
    }

    public PageViewDailyReport setTotalAdditionalColorCount(Integer totalAdditionalColorCount) {
        this.totalAdditionalColorCount = totalAdditionalColorCount;
        return this;
    }

    public Integer getTotalAdditionalColorSetCount() {
        if(this.totalAdditionalColorSetCount == null || this.totalAdditionalColorSetCount == 0) {
            return 0;
        }
        return totalAdditionalColorSetCount;
    }

    public PageViewDailyReport setTotalAdditionalColorSetCount(Integer totalAdditionalColorSetCount) {
        this.totalAdditionalColorSetCount = totalAdditionalColorSetCount;
        return this;
    }

    public Integer getTotalMovieClipCount() {
        if(this.totalMovieClipCount == null) {
            return 0;
        }
        return totalMovieClipCount;
    }

    public PageViewDailyReport setTotalMovieClipCount(Integer totalMovieClipCount) {
        this.totalMovieClipCount = totalMovieClipCount;
        return this;
    }

    public Integer getTotalStyleCount() {

        if(this.totalStyleCount == null)
            return 0;

        return totalStyleCount;
    }

    public PageViewDailyReport setTotalStyleCount(Integer totalStyleCount) {
        this.totalStyleCount = totalStyleCount;
        return this;
    }

    public String getVendorName() {
        return vendorName;
    }

    public PageViewDailyReport setVendorName(String vendorName) {
        this.vendorName = vendorName;
        return this;
    }

    public static List<PageViewDailyReport> build(List<PhotoCart> photoCarts) {

        List<PageViewDailyReport> buildedObjects = new ArrayList<>();
        if (CollectionUtils.isEmpty(photoCarts)) {
            return buildedObjects;
        }

        for (PhotoCart photoCart : photoCarts) {
            if (CollectionUtils.isEmpty(photoCart.getCartDetails())) {
                PageViewDailyReport report = new PageViewDailyReport();
                report.setOrderSubmitDate(photoCart.getCreatedOn())
                        .setVendorName(photoCart.getWholeSalerCompanyName())
                        .setCategoryName((photoCart.getCategory() == null) ? "" : photoCart.getCategory().getCategoryName())
                        .setPackageName((photoCart.getPackageInfo() == null) ? "" : photoCart.getPackageInfo().getName())
                        .setInputQuantityType(photoCart.getPhotoStudioOrderType())
                        .setPickDate(photoCart.getPhotoshootDate())
                        .setModelName((photoCart.getModel() == null) ? "" : photoCart.getModel().getModelName())
                        .setPromotionCode((photoCart.getDiscount() == null) ? "" : photoCart.getDiscount().getDiscountName());
                buildedObjects.add(report);
            } else {
                for (PhotoCartDetail detail : photoCart.getCartDetails()) {
                    PageViewDailyReport report = new PageViewDailyReport();
                    report.setOrderSubmitDate(photoCart.getCreatedOn())
                            .setVendorName(photoCart.getWholeSalerCompanyName())
                            .setCategoryName((photoCart.getCategory() == null) ? "" : photoCart.getCategory().getCategoryName())
                            .setPackageName((photoCart.getPackageInfo() == null) ? "" : photoCart.getPackageInfo().getName())
                            .setInputQuantityType(photoCart.getPhotoStudioOrderType())
                            .setTotalStyleCount(detail.getStyleQty())
                            .setTotalAdditionalColorCount(detail.getColorQty())
                            .setTotalAdditionalColorSetCount(detail.getColorSetQty())
                            .setTotalMovieClipCount(detail.getMovieQty())
                            .setPickDate(photoCart.getPhotoshootDate())
                            .setModelName((photoCart.getModel() == null) ? "" : photoCart.getModel().getModelName())
                            .setPromotionCode((photoCart.getDiscount() == null) ? "" : photoCart.getDiscount().getDiscountName());
                    buildedObjects.add(report);
                }
            }
        }

        return buildedObjects;
    }
}
