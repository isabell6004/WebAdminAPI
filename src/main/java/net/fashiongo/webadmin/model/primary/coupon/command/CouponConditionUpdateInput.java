package net.fashiongo.webadmin.model.primary.coupon.command;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CouponConditionUpdateInput extends CouponConditionCommonInput {

    @NotNull
    private Long id;
}
