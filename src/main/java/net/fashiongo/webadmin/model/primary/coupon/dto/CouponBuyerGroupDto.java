package net.fashiongo.webadmin.model.primary.coupon.dto;

import lombok.Builder;
import lombok.Data;
import net.fashiongo.common.data.model.entity.coupon.CCouponBuyerGroup;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class CouponBuyerGroupDto {

    private Long id;
    private String groupName;

    public static CouponBuyerGroupDto build(CCouponBuyerGroup couponBuyerGroup) {

        if (couponBuyerGroup == null) {
            return null;
        }

        return builder()
                .id(couponBuyerGroup.getId())
                .groupName(couponBuyerGroup.getCouponBuyerGroupName())
                .build();
    }

    public static List<CouponBuyerGroupDto> build(List<CCouponBuyerGroup> couponBuyerGroupList) {

        if (CollectionUtils.isEmpty(couponBuyerGroupList)) {
            return Collections.emptyList();
        }

        return couponBuyerGroupList.stream()
                .map(g -> build(g))
                .collect(Collectors.toList());
    }
}
