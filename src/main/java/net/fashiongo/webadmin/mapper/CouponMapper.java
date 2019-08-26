package net.fashiongo.webadmin.mapper;

import net.fashiongo.common.data.model.entity.coupon.CCoupon;
import net.fashiongo.common.data.model.entity.coupon.CCouponCode;
import net.fashiongo.common.data.model.entity.coupon.CCouponCondition;
import net.fashiongo.common.data.model.entity.coupon.CCouponNotification;
import net.fashiongo.webadmin.model.primary.coupon.command.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = CentralMapStructMapperConfig.class)
public interface CouponMapper {

    @Mapping(target = "couponType", defaultValue = "1")
    @Mapping(target = "saleType", defaultValue = "1")
    @Mapping(target = "discountBaseType", defaultValue = "1")
    @Mapping(target = "isActive", constant = "false")
    @Mapping(target = "isCodeUsed", defaultValue = "false")
    @Mapping(target = "isNotified", defaultValue = "false")
    @Mapping(target = "maxCouponCount", constant = "9999999")
    @Mapping(target = "issuedCouponCount", constant = "0")
    @Mapping(target = "isDeleted", constant = "false")
    @Mapping(target = "registerType", constant = "1")
    CCoupon toCoupon(CouponCreateInput input);

    void updateCoupon(CouponUpdateInput input, @MappingTarget CCoupon target);

    @Mapping(target = "isDeleted", constant = "false")
    CCouponCondition toCouponCondition(CouponConditionCreateInput input);

    void updateCouponCondition(CouponConditionUpdateInput input, @MappingTarget CCouponCondition target);

    @Mapping(target = "generateType", constant = "1")
    @Mapping(target = "isUsed", constant = "false")
    @Mapping(target = "isDeleted", constant = "false")
    CCouponCode toCouponCode(CouponCodeCreateInput input);

    void updateCouponCode(CouponCodeUpdateInput input, @MappingTarget CCouponCode target);

    CCouponNotification toCouponNotification(CouponNotificationInput input);

    void updateCouponNotification(CouponNotificationInput input, @MappingTarget CCouponNotification target);

    CouponCodeCreateInput toCouponCodeCreateInput(CouponCodeUpdateInput input);
}
