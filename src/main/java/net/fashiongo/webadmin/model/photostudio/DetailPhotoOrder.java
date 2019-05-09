/**
 *
 */
package net.fashiongo.webadmin.model.photostudio;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import net.fashiongo.webadmin.model.primary.VendorCompany;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * @author XiaoChuan.Gao
 *         SimplePhotoOrder: Order info for Order Detail page
 */
@Setter
@Getter
@Builder
public class DetailPhotoOrder {

    private static final String DATE_PATTERN_YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE_PATTERN_YYYYMMDD = "yyyy-MM-dd";
    private static final String DEFAULT_TIME = " 18:00:00";

    private LocalDateTime checkOutDate;

    public String getCheckOutDate() {
        return checkOutDate != null ? checkOutDate.format(DateTimeFormatter.ofPattern(DATE_PATTERN_YYYYMMDD)) : null;
    }

    private LocalDateTime dropOffDate;

    public String getDropOffDate() {
        return dropOffDate != null ? dropOffDate.format(DateTimeFormatter.ofPattern(DATE_PATTERN_YYYYMMDD)) : null;
    }

    private LocalDateTime prepDate;

    public String getPrepDate() {
        return prepDate != null ? prepDate.format(DateTimeFormatter.ofPattern(DATE_PATTERN_YYYYMMDD)) : null;
    }

    private LocalDateTime photoshootDate;

    public String getPhotoshootDate() {
        return photoshootDate != null ? photoshootDate.format(DateTimeFormatter.ofPattern(DATE_PATTERN_YYYYMMDD)) : null;
    }

    private LocalDateTime retouchDate;

    public String getRetouchDate() {
        return retouchDate != null ? retouchDate.format(DateTimeFormatter.ofPattern(DATE_PATTERN_YYYYMMDD)) : null;
    }

    private LocalDateTime uploadDate;

    public String getUploadDate() {
        return uploadDate != null ? uploadDate.format(DateTimeFormatter.ofPattern(DATE_PATTERN_YYYYMMDD)) : null;
    }

    public String getBillingPeriod() {
        LocalDateTime chekoutDate = this.checkOutDate;
        LocalDateTime billingPeriod = chekoutDate.plusMonths(1);
        return billingPeriod != null ? billingPeriod.format(DateTimeFormatter.ofPattern(DATE_PATTERN_YYYYMMDD)) : null;
    }

    private Integer orderID;

    private String poNumber;

    private Integer wholeSalerID;

    private String wholeSalerCompanyName;

    private String phone;

    private Integer categoryID;

    private String categoryName;

    private String typeOfPhotoshoot;

    private Integer totalQty;

    private BigDecimal totalUnit;

    private BigDecimal subtotalAmount;

    private BigDecimal totalAmount;

    private BigDecimal photoCreditUsedAmount;

    private BigDecimal additionalDiscountAmount;

    private Integer discountID;

    private String discountCode;

    private BigDecimal discountAmount;

    private String discountName;

    private String inHouseNote;

    private String specialRequest;

    private Boolean isByStyle;

    private Integer orderStatusID;

    private Integer modelID;

    private String modelName;

    private String colorName;

    private Integer cancelTypeID;

    private BigDecimal cancellationFee;

    private BigDecimal cancellationFeeRate;

    private String cancelledByUserName;

    private Integer isCancelledBy;

    private String cancelNote;

    private LocalDateTime cancelledOn;

    public String getCancelledOn() {
        return cancelledOn != null ? cancelledOn.format(DateTimeFormatter.ofPattern(DATE_PATTERN_YYYYMMDD)) : null;
    }

    private Boolean isSocialMediaPromotionAgree;

    private String packageName;

    private LocalDateTime pickupDate;

    public String getPickupDate() {
        return pickupDate != null ? pickupDate.format(DateTimeFormatter.ofPattern(DATE_PATTERN_YYYYMMDD)) : null;
    }

    public static DetailPhotoOrder build(PhotoOrder photoOrder, VendorCompany vendor) {

        return builder().orderID(photoOrder.getOrderID())
                .checkOutDate(photoOrder.get_checkOutDate())
                .dropOffDate(photoOrder.get_dropOffDate())
                .prepDate(photoOrder.get_prepDate())
                .photoshootDate(photoOrder.get_photoshootDate())
                .retouchDate(photoOrder.get_retouchDate())
                .uploadDate(photoOrder.get_uploadDate())
                .pickupDate(photoOrder.getPickupDate())
                .poNumber(photoOrder.getPoNumber())
                .wholeSalerID(photoOrder.getWholeSalerID())
                .wholeSalerCompanyName(photoOrder.getWholeSalerCompanyName())
                .categoryID(photoOrder.getCategoryID())
                .categoryName(photoOrder.getPhotoCategory().getCategoryName())
                .typeOfPhotoshoot(photoOrder.getPhotoCategory().getTypeOfPhotoshoot())
                .totalQty(photoOrder.getTotalQty())
                .subtotalAmount(photoOrder.getSubtotalAmount())
                .totalAmount(photoOrder.getTotalAmount())
                .totalUnit(photoOrder.getTotalUnit())
                .photoCreditUsedAmount(photoOrder.getPhotoCreditUsedAmount())
                .additionalDiscountAmount(photoOrder.getAdditionalDiscountAmount())
                .discountID(photoOrder.getDiscountID())
                .discountAmount(photoOrder.getDiscountAmount())
                .discountCode(photoOrder.getDiscountCode())
                .discountName(photoOrder.getPhotoDiscount() != null ? photoOrder.getPhotoDiscount().getDiscountName() : null)
                .inHouseNote(photoOrder.getInHouseNote())
                .specialRequest(photoOrder.getSpecialRequest())
                .isByStyle(photoOrder.getIsByStyle())
                .orderStatusID(photoOrder.getOrderStatusID())

                .modelID(
                        Optional.ofNullable(photoOrder.getPhotoBooking().getMapPhotoCalendarModel()).map(MapPhotoCalendarModel::getModelID).orElse(0)
                )
                .modelName(
                        Optional.ofNullable(photoOrder.getPhotoBooking().getMapPhotoCalendarModel()).map(MapPhotoCalendarModel::getPhotoModel).map(PhotoModel::getModelName).orElse("")
                )
                .packageName(
                        Optional.ofNullable(photoOrder.getPhotoPackage()).map(PhotoPackage::getName).orElse("")
                )
                .colorName(
                        Optional.ofNullable(photoOrder.getCodePhotoBackgroundColor()).map(CodePhotoBackgroundColor::getPhotoColorName).orElse("")
                )
                .phone(vendor.getPhone())
                .cancelTypeID(photoOrder.getCancelTypeID())
                .cancelledByUserName(photoOrder.getCancelledByUserName())
                .isCancelledBy(photoOrder.getIsCancelledBy())
                .cancelNote(photoOrder.getCancelNote())
                .cancelledOn(photoOrder.get_cancelledOn())
                .cancellationFee(photoOrder.getCancellationFee())
                .cancellationFeeRate(photoOrder.getCancellationFeeRate())
                .isSocialMediaPromotionAgree(photoOrder.getIsSocialMediaPromotionAgree()).build();

    }
}
