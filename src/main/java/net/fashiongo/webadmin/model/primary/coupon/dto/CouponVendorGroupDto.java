package net.fashiongo.webadmin.model.primary.coupon.dto;

import lombok.Builder;
import lombok.Data;
import net.fashiongo.common.data.model.entity.coupon.CCouponVendorGroup;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class CouponVendorGroupDto {

    private Long id;
    private String groupName;
    private Integer vendorGroupType;
    private Integer groupConditionType;

    public static CouponVendorGroupDto build(CCouponVendorGroup couponVendorGroup) {

        if (couponVendorGroup == null) {
            return null;
        }

        return builder()
                .id(couponVendorGroup.getId())
                .groupName(couponVendorGroup.getCouponVendorGroupName())
                .vendorGroupType(couponVendorGroup.getVendorGroupType())
                .groupConditionType(couponVendorGroup.getGroupConditionType())
                .build();
    }

    public static List<CouponVendorGroupDto> build(List<CCouponVendorGroup> couponVendorGroupList) {

        if (CollectionUtils.isEmpty(couponVendorGroupList)) {
            return Collections.emptyList();
        }

        return couponVendorGroupList.stream()
                .map(g -> build(g))
                .collect(Collectors.toList());
    }
}
