package net.fashiongo.webadmin.model.primary.coupon.command;

import lombok.Data;
import net.fashiongo.webadmin.exception.coupon.InvalidInputCouponException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
public class CouponUpdateInput extends CouponCommonInput {

    @NotNull
    private Long id;
    @NotNull
    private @Valid CouponConditionUpdateInput couponCondition;
    private @Valid CouponCodeUpdateInput couponCode;

    public void validateCouponRequest() {

        if (this.getStartDate().isAfter(this.getEndDate())) {
            throw new InvalidInputCouponException("Invalid period");
        }
    }
}
