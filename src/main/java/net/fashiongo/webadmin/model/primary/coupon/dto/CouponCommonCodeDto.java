package net.fashiongo.webadmin.model.primary.coupon.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import net.fashiongo.common.data.model.entity.coupon.CCouponCommonCode;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Getter
@Setter
@Builder
public class CouponCommonCodeDto {

    private Integer id;
    private String codeName;

    public static CouponCommonCodeDto build(CCouponCommonCode couponCommonCode) {

        if (couponCommonCode == null) {
            return null;
        }

        return builder()
                .id(Integer.parseInt(couponCommonCode.getId().getCommonCode()))
                .codeName(couponCommonCode.getCodeName())
                .build();
    }

    public static Map<String, List<CouponCommonCodeDto>> build(List<CCouponCommonCode> couponCommonCodeEntityList) {

        if (CollectionUtils.isEmpty(couponCommonCodeEntityList)) {
            return Collections.emptyMap();
        }

        Map<String, List<CouponCommonCodeDto>> commonCodeMap = new HashMap<>();
        couponCommonCodeEntityList.forEach(c -> {
            List<CouponCommonCodeDto> tempCommonCodeList;
            if (commonCodeMap.containsKey(c.getId().getUpperCommonCode())) {
                tempCommonCodeList = commonCodeMap.get(c.getId().getUpperCommonCode());
                tempCommonCodeList.add(CouponCommonCodeDto.build(c));
                commonCodeMap.replace(c.getId().getUpperCommonCode(), tempCommonCodeList);
            } else {
                tempCommonCodeList = new ArrayList<>();
                tempCommonCodeList.add(CouponCommonCodeDto.build(c));
                commonCodeMap.put(c.getId().getUpperCommonCode(), tempCommonCodeList);
            }
        });

        return  commonCodeMap;
    }
}
