package net.fashiongo.webadmin.model.primary.coupon.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import net.fashiongo.common.data.model.entity.coupon.CCouponCode;

@Getter
@Setter
@Builder
public class CouponCodeDto {

    private Long id;
    private String couponCode;
    private Integer generateType;
    private Long couponId;
    private Boolean isUsed;

    public static CouponCodeDto build(CCouponCode couponCode) {

        if (couponCode == null) {
            return null;
        }

        return builder()
                .id(couponCode.getId())
                .couponCode(couponCode.getCouponCode())
                .generateType(couponCode.getGenerateType())
                .couponId(couponCode.getCouponId())
                .isUsed(couponCode.getIsUsed())
                .build();
    }
}
