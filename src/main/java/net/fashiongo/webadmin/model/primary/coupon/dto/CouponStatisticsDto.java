package net.fashiongo.webadmin.model.primary.coupon.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.util.CollectionUtils;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import net.fashiongo.common.data.model.entity.coupon.CCoupon;
import net.fashiongo.common.data.model.entity.coupon.CCouponCode;
import net.fashiongo.common.data.model.entity.coupon.CCouponCondition;
import net.fashiongo.common.data.model.entity.coupon.CCouponStatistics;

@Getter
@Setter
@Builder
public class CouponStatisticsDto {

    private Long id;
    private LocalDateTime batchDate;
    private Long couponId;
    private Integer issuedCouponCount;
    private Integer usedCouponCount;
    private Integer totalOrderCount;
    private BigDecimal totalOrderAmount;
    private BigDecimal totalVendorDiscountAmount;
    private BigDecimal totalCouponDiscountAmount;
    private Integer totalUserCount;
    private LocalDateTime createdOn;
    private String createdBy;
    private LocalDateTime modifiedOn;
    private String modifiedBy;
    
    private CouponDto coupon;

    public static CouponStatisticsDto build(CCouponStatistics couponStatistics, CCoupon coupon, CCouponCondition couponCondition, CCouponCode couponCode) {

        if (couponStatistics == null) {
            return null;
        }

        return builder()
                .id(couponStatistics.getId())
                .batchDate(couponStatistics.getBatchDate())
                .couponId(couponStatistics.getCouponId())
                .issuedCouponCount(couponStatistics.getIssuedCouponCount())
                .usedCouponCount(couponStatistics.getUsedCouponCount())
                .totalOrderCount(couponStatistics.getTotalOrderCount())
                .totalOrderAmount(couponStatistics.getTotalOrderAmount())
                .totalVendorDiscountAmount(couponStatistics.getTotalVendorDiscountAmount())
                .totalCouponDiscountAmount(couponStatistics.getTotalCouponDiscountAmount())
                .totalUserCount(couponStatistics.getTotalUserCount())
                .coupon(CouponDto.build(coupon, couponCondition, couponCode))
                .build();
    }

    public static List<CouponStatisticsDto> build(List<CCouponStatistics> couponStatisticsEntityList, List<CCoupon> couponEntityList, List<CCouponCondition> couponConditionEntityList, List<CCouponCode> couponCodeEntityList) {

        Map<Long, CCoupon> couponMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(couponEntityList)) {
        	couponEntityList.forEach(c -> couponMap.put(c.getId(), c));
        }

        Map<Long, CCouponCondition> couponConditionMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(couponConditionEntityList)) {
            couponConditionEntityList.forEach(c -> couponConditionMap.put(c.getCouponId(), c));
        }

        Map<Long, CCouponCode> couponCodeMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(couponCodeEntityList)) {
            couponCodeEntityList.forEach(c -> couponCodeMap.put(c.getCouponId(), c));
        }
        
        return couponStatisticsEntityList.stream()
                .map(c -> CouponStatisticsDto.build(c, couponMap.get(c.getCouponId()), couponConditionMap.get(c.getCouponId()), couponCodeMap.get(c.getCouponId())))
                .collect(Collectors.toList());
    }
}
