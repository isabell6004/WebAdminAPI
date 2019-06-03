package net.fashiongo.webadmin.model.photostudio;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@Setter
@Builder
public class PhotoOrderResponse {
    private Integer orderID;

    private String poNumber;

    private Integer orderStatusID;

    private Integer categoryID;

    private String categoryName;

    private Integer packageID;

    private String packageName;

    private String wholeSalerCompanyName;

    private Integer wholeSalerID;

    private BigDecimal totalAmount;

    private BigDecimal totalUnit;

    private String typeOfPhotoshoot;

    private LocalDateTime checkOutDate;

    private LocalDateTime dropOffDate;

    private LocalDateTime photoshootDate;

    private String inHouseNote;

    private String specialRequest;

    private Integer isCancelledBy;

    private String cancelNote;

    private Boolean isDelayed;

    public static PhotoOrderResponse of(PhotoOrderEntity photoOrderEntity) {
        if (photoOrderEntity == null) {
            return null;
        }

        return builder()
                .orderID(photoOrderEntity.getOrderID())
                .poNumber(photoOrderEntity.getPoNumber())
                .orderStatusID(photoOrderEntity.getOrderStatusID())
                .categoryID(photoOrderEntity.getCategoryID())
                .categoryName(photoOrderEntity.getPhotoCategory().getCategoryName())
                .packageID(photoOrderEntity.getPackageID())
                .packageName(photoOrderEntity.getPhotoPackage().getName())
                .wholeSalerCompanyName(photoOrderEntity.getWholeSalerCompanyName())
                .wholeSalerID(photoOrderEntity.getWholeSalerID())
                .totalAmount(photoOrderEntity.getTotalAmount())
                .totalUnit(photoOrderEntity.getTotalUnit())
                .typeOfPhotoshoot(photoOrderEntity.getPhotoCategory().getTypeOfPhotoshoot())
                .checkOutDate(photoOrderEntity.get_checkOutDate())
                .dropOffDate(photoOrderEntity.get_dropOffDate())
                .photoshootDate(photoOrderEntity.get_photoshootDate())
                .inHouseNote(photoOrderEntity.getInHouseNote())
                .specialRequest(photoOrderEntity.getSpecialRequest())
                .isCancelledBy(Optional.ofNullable(photoOrderEntity.getIsCancelledBy())
                        .orElse(0))
                .cancelNote(photoOrderEntity.getCancelNote())
                .isDelayed(Optional.of(photoOrderEntity)
                        .map(photoOrder -> {
                            LocalDateTime now = LocalDateTime.now();

                            if (photoOrder.getOrderStatusID() == 1) {
                                return photoOrder.get_dropOffDate().isBefore(now);
                            } else if (photoOrder.getOrderStatusID() == 2) {
                                return photoOrder.get_prepDate().isBefore(now);
                            } else if (photoOrder.getOrderStatusID() == 3) {
                                return photoOrder.get_photoshootDate().isBefore(now);
                            } else if (photoOrder.getOrderStatusID() == 4) {
                                return photoOrder.get_retouchDate().isBefore(now);
                            } else if (photoOrder.getOrderStatusID() == 5) {
                                return photoOrder.get_uploadDate().isBefore(now);
                            } else {
                                return false;
                            }
                        })
                        .orElse(null))
                .build();
    }
}
