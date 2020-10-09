package net.fashiongo.webadmin.data.model.vendor.mapper;

import net.fashiongo.webadmin.data.model.vendor.SetVendorSettingParameter;
import net.fashiongo.webadmin.data.model.vendor.VendorBlockPayoutParameter;
import net.fashiongo.webadmin.model.pojo.vendor.parameter.SetVendorUnBlockParameter;

public class VendorBlockPayoutParameterMapper {

    public static VendorBlockPayoutParameter convert(SetVendorSettingParameter setting) {
        return VendorBlockPayoutParameter.builder()
                .vendorId((long)setting.getWid())
                .isPayoutBlock(setting.getIsPayoutBlock())
                .payoutBlockReasonId(setting.getPayoutBlockReasonId())
                .payoutBlockChanged(setting.getPayoutBlockChanged())
                .build();
    }

    public static VendorBlockPayoutParameter convertUnblock(SetVendorUnBlockParameter request) {
        return VendorBlockPayoutParameter.builder()
                .vendorId((long)request.getWholeSalerID())
                .isPayoutBlock(Boolean.FALSE)
                .payoutBlockChanged(Boolean.TRUE)
                .build();
    }
}
