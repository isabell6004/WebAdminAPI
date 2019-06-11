/**
 *
 */
package net.fashiongo.webadmin.model.photostudio;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import net.fashiongo.common.conversion.LocalDateTimeConverter;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author XiaoChuan.Gao
 *         SimplePhotoOrder: Order info for Order Listing page
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimplePhotoOrder {

    private static final String DATE_PATTERN_YYYYMMDD = "yyyy-MM-dd";

    @Id
    @Column(name = "OrderID")
    private Integer orderID;

    @JsonIgnore
    @Column(name = "PhotoshootDate")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime _photoshootDate;

    @Transient
    private String photoshootDate;

    public String getPhotoshootDate() {
        return _photoshootDate != null ? _photoshootDate.format(DateTimeFormatter.ofPattern(DATE_PATTERN_YYYYMMDD)) : null;
    }

    @JsonIgnore
    @Column(name = "CheckOutDate")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime _checkOutDate;

    @Transient
    private String checkOutDate;

    public String getCheckOutDate() {
        return _checkOutDate != null ? _checkOutDate.format(DateTimeFormatter.ofPattern(DATE_PATTERN_YYYYMMDD)) : null;
    }

    @Column(name = "PONumber")
    private String poNumber;

    @Column(name = "InHouseNote")
    private String inHouseNote;

    @Column(name = "SpecialRequest")
    private String specialRequest;

    @Column(name = "WholeSalerID")
    private Integer wholeSalerID;

    @Column(name = "WholeSalerCompanyName")
    private String wholeSalerCompanyName;

    @Column(name = "CategoryID")
    private Integer categoryID;

    @Column(name = "CategoryName")
    private String categoryName;

    @Column(name = "PackageID")
    private Integer packageID;

    @Column(name = "PackageName")
    private String packageName;

    @Column(name = "TypeOfPhotoshoot")
    private String typeOfPhotoshoot;

    @Column(name = "TotalQty")
    private Integer totalQty;

    @JsonIgnore
    @Column(name = "DropOffDate")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime _dropOffDate;

    @Transient
    private String dropOffDate;

    public String getDropOffDate() {
        return _dropOffDate != null ? _dropOffDate.format(DateTimeFormatter.ofPattern(DATE_PATTERN_YYYYMMDD)) : null;
    }

    @Column(name = "TotalAmount")
    private BigDecimal totalAmount;

    @Column(name = "OrderStatusID")
    private Integer orderStatusID;

    @Column(name = "IsCancelledBy")
    private Integer isCancelledBy;

    @Column(name = "CancelNote")
    private String cancelNote;

    @Column(name = "IsDelayed")
    private Boolean isDelayed;

    @Column(name = "ModelID")
    private Integer modelID;

    @Column(name = "ModelName")
    private String modelName;

    @Column(name = "TotalUnit")
    private BigDecimal totalUnit;

    @Transient
    private Integer colorId;

    @Transient
    private Integer styles;

    @Transient
    private Integer swatches;

    @Transient
    private Integer movies;

    public static List<SimplePhotoOrder> makeOrders(List<PhotoOrderEntity> photoOrders) {

        List<SimplePhotoOrder> simpleOrders = photoOrders.stream().map((order) -> {
            SimplePhotoOrder simplePhotoOrder = SimplePhotoOrder.builder()
                    .orderID(order.getOrderID())
                    .orderStatusID(order.getOrderStatusID())
                    .colorId(order.getColorID())
                    .poNumber(order.getPoNumber())
                    .categoryID(order.getCategoryID())
                    .packageID(order.getPackageID())
                    .packageName(
                            Optional.ofNullable(order.getPhotoPackage()).map(x -> x.getName()).orElse("")
                    )
                    .wholeSalerID(order.getWholeSalerID())
                    .wholeSalerCompanyName(order.getWholeSalerCompanyName())
                    .modelID(
                            Optional.ofNullable(order.getPhotoBooking().getMapPhotoCalendarModel().getModelID()).orElse(null)
                    )
                    .modelName(
                            Optional.ofNullable(order.getPhotoBooking().getMapPhotoCalendarModel().getPhotoModel()).map(
                                    model -> (model.getModelName())
                            ).orElse(null)
                    )
                    .totalUnit(order.getTotalUnit())
                    .styles(order.getOrderDetails().stream().mapToInt(x -> (Optional.ofNullable(x.getColorSetQty()).orElse(0) + Optional.ofNullable(x.getBaseColorSetQty()).orElse(0))).sum())
                    .swatches(order.getOrderDetails().stream().mapToInt(x -> (Optional.ofNullable(x.getModelSwatchQty()).orElse(0) + Optional.ofNullable(x.getColorSwatchQty()).orElse(0))).sum())
                    .movies(order.getOrderDetails().stream().mapToInt(x -> (Optional.ofNullable(x.getMovieQty()).orElse(0) + Optional.ofNullable(x.getMovieClipQty()).orElse(0))).sum())
                    .isDelayed(
                            checkDelayedStatus(order)
                    )
                    .build();
            return simplePhotoOrder;
        }).collect(Collectors.toList());

        return simpleOrders;
    }

    private static Boolean checkDelayedStatus(PhotoOrderEntity order) {
        LocalDateTime now = LocalDateTime.now();
        if ((order.getOrderStatusID() == 1 && order.get_dropOffDate().isBefore(now))
                || (order.getOrderStatusID() == 2 && order.get_prepDate().isBefore(now))
                || (order.getOrderStatusID() == 3 && order.get_retouchDate().isBefore(now))
                || (order.getOrderStatusID() == 4 && order.get_retouchDate().isBefore(now))
                || (order.getOrderStatusID() == 5 && order.get_uploadDate().isBefore(now))) {

            return true;
        } else {
            return false;
        }
    }
}
