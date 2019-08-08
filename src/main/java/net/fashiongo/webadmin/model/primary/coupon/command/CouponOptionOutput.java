package net.fashiongo.webadmin.model.primary.coupon.command;

import lombok.Builder;
import lombok.Data;
import net.fashiongo.webadmin.model.primary.coupon.dto.CouponBuyerGroupDto;
import net.fashiongo.webadmin.model.primary.coupon.dto.CouponCommonCodeDto;
import net.fashiongo.webadmin.model.primary.coupon.dto.CouponVendorGroupDto;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class CouponOptionOutput {

    Map<String, List<CouponCommonCodeDto>> commonCodes;
    List<CouponBuyerGroupDto> buyerGroups;
    List<CouponVendorGroupDto> vendorGroups;

    public static CouponOptionOutput build(Map<String, List<CouponCommonCodeDto>> commonCodeMap, List<CouponBuyerGroupDto> buyerGroupList, List<CouponVendorGroupDto> vendorGroupList) {

        return builder()
                .commonCodes(commonCodeMap)
                .buyerGroups(buyerGroupList)
                .vendorGroups(vendorGroupList)
                .build();
    }
}
