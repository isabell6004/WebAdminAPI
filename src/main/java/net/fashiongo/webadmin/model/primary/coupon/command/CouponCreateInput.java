package net.fashiongo.webadmin.model.primary.coupon.command;

import lombok.Data;
import net.fashiongo.webadmin.exception.coupon.InvalidInputCouponException;
import org.springframework.util.CollectionUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CouponCreateInput extends CouponCommonInput {

    @NotNull
    private @Valid CouponConditionCreateInput couponCondition;
    private @Valid CouponCodeCreateInput couponCode;

    public void validateCouponCreateInput() {

        if (this.getStartDate().isAfter(this.getEndDate())) {
            throw new InvalidInputCouponException("Invalid period");
        }

        if (this.getIsCodeUsed() && this.couponCode == null) {
            throw new InvalidInputCouponException("No coupon code");
        }
    }
}
