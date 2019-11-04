package net.fashiongo.webadmin.model.primary.coupon.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import net.fashiongo.common.data.model.entity.coupon.CCoupon;
import net.fashiongo.common.data.model.entity.coupon.CCouponCode;
import net.fashiongo.common.data.model.entity.coupon.CCouponCondition;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
public class CouponDto {

    private Long id;
    private String couponName;
    private Integer couponType;
    private Integer saleType;
    private Integer discountBaseType;
    private String description;
    private Boolean isActive;
    private LocalDateTime activatedOn;
    private String activatedBy;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Boolean isCodeUsed;
    private Boolean isNotified;
    private Long couponVendorGroupId;
    private Long couponBuyerGroupId;
    private Integer issueMethod;
    private LocalDateTime issueStartDate;
    private LocalDateTime issueEndDate;    
    private Integer validDurationDays;    
    private LocalDateTime createdOn;
    private String createdBy;
    private LocalDateTime modifiedOn;
    private String modifiedBy;
    private CouponConditionDto couponCondition;
    private CouponCodeDto couponCode;
    private CouponBuyerGroupDto targetBuyerGroup;
    private CouponVendorGroupDto applicableVendorGroup;

    public static CouponDto build(CCoupon coupon, CCouponCondition couponCondition, CCouponCode couponCode) {

        if (coupon == null) {
            return null;
        }

        return builder()
                .id(coupon.getId())
                .couponName(coupon.getCouponName())
                .couponType(coupon.getCouponType())
                .saleType(coupon.getSaleType())
                .discountBaseType(coupon.getDiscountBaseType())
                .description(coupon.getDescription())
                .isActive(coupon.getIsActive())
                .activatedOn(coupon.getActivatedOn())
                .activatedBy(coupon.getActivatedBy())
                .startDate(coupon.getStartDate())
                .endDate(coupon.getEndDate())
                .isCodeUsed(coupon.getIsCodeUsed())
                .isNotified(coupon.getIsNotified())
                .couponVendorGroupId(coupon.getCouponVendorGroupId())
                .couponBuyerGroupId(coupon.getCouponBuyerGroupId())
                .issueMethod(coupon.getIssueMethod())
                .issueStartDate(coupon.getIssueStartDate())
                .issueEndDate(coupon.getIssueEndDate())
                .validDurationDays(coupon.getValidDurationDays())
                .createdOn(coupon.getCreatedOn())
                .createdBy(coupon.getCreatedBy())
                .modifiedOn(coupon.getModifiedOn())
                .modifiedBy(coupon.getModifiedBy())
                .couponCondition(CouponConditionDto.build(couponCondition))
                .couponCode(CouponCodeDto.build(couponCode))
                .targetBuyerGroup(CouponBuyerGroupDto.build(coupon.getCouponBuyerGroup()))
                .applicableVendorGroup(CouponVendorGroupDto.build(coupon.getCouponVendorGroup()))
                .build();
    }

    public static List<CouponDto> build(List<CCoupon> couponEntityList, List<CCouponCondition> couponConditionEntityList, List<CCouponCode> couponCodeEntityList) {

        Map<Long, CCouponCondition> couponConditionMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(couponConditionEntityList)) {
            couponConditionEntityList.forEach(c -> couponConditionMap.put(c.getCouponId(), c));
        }

        Map<Long, CCouponCode> couponCodeMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(couponCodeEntityList)) {
            couponCodeEntityList.forEach(c -> couponCodeMap.put(c.getCouponId(), c));
        }

        return couponEntityList.stream()
                .map(c -> CouponDto.build(c, couponConditionMap.get(c.getId()), couponCodeMap.get(c.getId())))
                .collect(Collectors.toList());
    }
}
