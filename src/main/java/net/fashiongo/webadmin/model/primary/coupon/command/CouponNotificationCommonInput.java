package net.fashiongo.webadmin.model.primary.coupon.command;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CouponNotificationCommonInput {

    private CouponNotificationInput notification;
}
