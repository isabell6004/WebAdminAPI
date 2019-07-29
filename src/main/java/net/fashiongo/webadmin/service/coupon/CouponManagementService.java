package net.fashiongo.webadmin.service.coupon;

import net.fashiongo.webadmin.model.pojo.common.PagedResult;
import net.fashiongo.webadmin.model.primary.coupon.command.CouponCreateInput;
import net.fashiongo.webadmin.model.primary.coupon.command.CouponNotificationCommonInput;
import net.fashiongo.webadmin.model.primary.coupon.command.CouponOptionOutput;
import net.fashiongo.webadmin.model.primary.coupon.command.CouponUpdateInput;
import net.fashiongo.webadmin.model.primary.coupon.dto.CouponDto;
import net.fashiongo.webadmin.model.primary.coupon.dto.CouponNotificationDto;

public interface CouponManagementService {

    PagedResult<CouponDto> getCoupons(int pn, int ps);

    CouponDto createCoupon(CouponCreateInput couponCreateInput);

    CouponDto updateCoupon(Long couponId, CouponUpdateInput couponUpdateInput);

    boolean activateCoupon(Long couponId);

    boolean deactivateCoupon(Long couponId);

    boolean deleteCoupon(Long couponId);

    CouponNotificationDto getCouponNotifications(Long couponId);

    CouponNotificationDto createCouponNotification(Long couponId, CouponNotificationCommonInput couponNotificationCommonInput);

    CouponNotificationDto updateCouponNotification(Long couponId, Long couponNotificationId, CouponNotificationCommonInput couponNotificationCommonInput);

    boolean deleteCouponNotification(Long couponId, Long couponNotificationId);

    CouponOptionOutput getCouponOptions();

    void hasCouponActionAuthority(String action);

    boolean checkCouponCodeUnique(String couponCode, Long couponCodeId);
}
