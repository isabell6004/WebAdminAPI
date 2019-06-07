package net.fashiongo.webadmin.model.photostudio;

import lombok.*;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jinwoo on 2019. 3. 5..
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetailOrderQuantity {

    private Integer orderDetailID;

    private Integer orderID;

    private String styleName;

    // OLD
    private BigDecimal styleUnitPrice;

    private Integer styleQty;

    private BigDecimal styleAmount;

    private BigDecimal colorSetUnitPrice;

    private Integer colorSetQty;

    private BigDecimal colorSetAmount;

    private BigDecimal colorUnitPrice;

    private Integer colorQty;

    private BigDecimal colorAmount;

    private BigDecimal movieUnitPrice;

    private Integer movieQty;

    private BigDecimal movieAmount;

    // NEW
    private BigDecimal baseColorSetUnitPrice;

    private Integer baseColorSetQty;

    private BigDecimal baseColorSetAmount;

    private BigDecimal colorSwatchUnitPrice;

    private Integer colorSwatchQty;

    private BigDecimal colorSwatchAmount;

    private BigDecimal modelSwatchUnitPrice;

    private Integer modelSwatchQty;

    private BigDecimal modelSwatchAmount;

    private BigDecimal movieClipUnitPrice;

    private Integer movieClipQty;

    private BigDecimal movieClipAmount;

    public static List<DetailOrderQuantity> build(List<PhotoOrderDetail> photoOrderDetails) {

        if(CollectionUtils.isEmpty(photoOrderDetails)) {
            return new ArrayList<>();
        }

        List<DetailOrderQuantity> results = new ArrayList<>();
        for(PhotoOrderDetail photoOrderDetail : photoOrderDetails) {
            results.add(
                builder().orderDetailID(photoOrderDetail.getOrderDetailID()).orderID(photoOrderDetail.getOrderID())
                        .styleName(photoOrderDetail.getStyleName())
                        .styleUnitPrice(photoOrderDetail.getStyleUnitPrice()).styleQty(photoOrderDetail.getStyleQty()).styleAmount(photoOrderDetail.getStyleAmount())
                        .colorUnitPrice(photoOrderDetail.getColorUnitPrice()).colorQty(photoOrderDetail.getColorQty()).colorAmount(photoOrderDetail.getColorAmount())
                        .colorSetUnitPrice(photoOrderDetail.getColorSetUnitPrice()).colorSetQty(photoOrderDetail.getColorSetQty()).colorSetAmount(photoOrderDetail.getColorSetAmount())
                        .movieUnitPrice(photoOrderDetail.getMovieUnitPrice()).movieQty(photoOrderDetail.getMovieQty()).movieAmount(photoOrderDetail.getMovieAmount())
                        .colorSwatchUnitPrice(photoOrderDetail.getColorSwatchUnitPrice()).colorSwatchQty(photoOrderDetail.getColorSwatchQty()).colorSwatchAmount(photoOrderDetail.getColorSwatchAmount())
                        .modelSwatchUnitPrice(photoOrderDetail.getModelSwatchUnitPrice()).modelSwatchQty(photoOrderDetail.getModelSwatchQty()).modelSwatchAmount(photoOrderDetail.getModelSwatchAmount())
                        .baseColorSetUnitPrice(photoOrderDetail.getBaseColorSetUnitPrice()).baseColorSetQty(photoOrderDetail.getBaseColorSetQty()).baseColorSetAmount(photoOrderDetail.getBaseColorSetAmount())
                        .movieClipUnitPrice(photoOrderDetail.getMovieClipUnitPrice()).movieClipQty(photoOrderDetail.getMovieClipQty()).movieClipAmount(photoOrderDetail.getMovieClipAmount())
                        .build()
            );
        }
        return results;
    }
}
