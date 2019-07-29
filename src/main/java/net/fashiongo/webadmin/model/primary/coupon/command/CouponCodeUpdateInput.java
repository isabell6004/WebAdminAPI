package net.fashiongo.webadmin.model.primary.coupon.command;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CouponCodeUpdateInput extends CouponCodeCommonInput {

    @NotNull
    private Long id;
    private Boolean isDeleted;
}
