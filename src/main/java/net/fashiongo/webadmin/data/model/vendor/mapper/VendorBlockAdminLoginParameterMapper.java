package net.fashiongo.webadmin.data.model.vendor.mapper;

import net.fashiongo.webadmin.data.model.vendor.SetVendorSettingParameter;
import net.fashiongo.webadmin.data.model.vendor.VendorBlockAdminLoginParameter;
import net.fashiongo.webadmin.model.pojo.vendor.parameter.SetVendorUnBlockParameter;

public class VendorBlockAdminLoginParameterMapper {

    public static VendorBlockAdminLoginParameter convert(SetVendorSettingParameter setting) {
        return VendorBlockAdminLoginParameter.builder()
                .vendorId((long)setting.getWid())
                .isBlock(setting.getIsBlock())
                .blockReasonId(setting.getBlockReasonId())
                .blockChanged(setting.getBlockChanged())
                .build();
    }

    public static VendorBlockAdminLoginParameter convertUnblock(SetVendorUnBlockParameter request) {
        return VendorBlockAdminLoginParameter.builder()
                .vendorId((long)request.getWholeSalerID())
                .isBlock(Boolean.FALSE)
                .blockChanged(Boolean.TRUE)
                .build();
    }
}
