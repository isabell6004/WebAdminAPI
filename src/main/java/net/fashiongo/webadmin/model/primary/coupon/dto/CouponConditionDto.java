package net.fashiongo.webadmin.model.primary.coupon.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import net.fashiongo.common.data.model.entity.coupon.CCouponCondition;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class CouponConditionDto {

    private Long id;
    private Long couponId;
    private Integer discountType;
    private BigDecimal minSaleAmount;
    private BigDecimal maxSaleAmount;
    private Integer minSaleQty;
    private Integer maxSaleQty;
    private BigDecimal discountAmount;
    private Double discountRate;
    private BigDecimal maxDiscountAmount;

    public static CouponConditionDto build(CCouponCondition condition) {

        if (condition == null) {
            return null;
        }

        return builder()
                .id(condition.getId())
                .couponId(condition.getCouponId())
                .discountType(condition.getDiscountType())
                .minSaleAmount(condition.getMinSaleAmount())
                .maxSaleAmount(condition.getMaxSaleAmount())
                .minSaleQty(condition.getMinSaleQty())
                .maxSaleQty(condition.getMaxSaleQty())
                .discountAmount(condition.getDiscountAmount())
                .discountRate(condition.getDiscountRate())
                .maxDiscountAmount(condition.getMaxDiscountAmount())
                .build();
    }
}
