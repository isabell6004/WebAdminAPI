package net.fashiongo.webadmin.model.photostudio;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
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
public class DetailOrderQuantity {

    private Integer orderDetailID;

    private Integer orderID;

    private Integer styleQty;

    private BigDecimal styleAmount;

    private Integer colorSetQty;

    private BigDecimal colorSetAmount;

    private Integer colorQty;

    private BigDecimal colorAmount;

    private Integer movieQty;

    private BigDecimal movieAmount;

    public static List<DetailOrderQuantity> build(List<PhotoOrderDetail> photoOrderDetails) {

        if(CollectionUtils.isEmpty(photoOrderDetails)) {
            return new ArrayList<>();
        }

        List<DetailOrderQuantity> results = new ArrayList<>();
        for(PhotoOrderDetail photoOrderDetail : photoOrderDetails) {
            results.add(
                builder().orderDetailID(photoOrderDetail.getOrderDetailID()).orderID(photoOrderDetail.getOrderID())
                        .styleQty(photoOrderDetail.getStyleQty()).styleAmount(photoOrderDetail.getStyleAmount())
                        .colorQty(photoOrderDetail.getColorQty()).colorAmount(photoOrderDetail.getColorAmount())
                        .colorSetQty(photoOrderDetail.getColorSetQty()).colorSetAmount(photoOrderDetail.getColorSetAmount())
                        .movieQty(photoOrderDetail.getMovieQty()).movieAmount(photoOrderDetail.getMovieAmount()).build()
            );
        }
        return results;
    }
}
