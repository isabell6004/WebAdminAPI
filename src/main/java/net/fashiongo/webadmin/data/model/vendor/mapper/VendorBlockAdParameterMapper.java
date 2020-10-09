package net.fashiongo.webadmin.data.model.vendor.mapper;

import net.fashiongo.webadmin.data.model.vendor.VendorBlockAdParameter;
import net.fashiongo.webadmin.model.pojo.vendor.parameter.SetVendorUnBlockParameter;

public class VendorBlockAdParameterMapper {
    public static VendorBlockAdParameter convertUnblock(SetVendorUnBlockParameter request) {
        return VendorBlockAdParameter.builder()
                .vendorId((long)request.getWholeSalerID())
                .isAdBlock(Boolean.FALSE)
                .adBlockChanged(Boolean.TRUE)
                .build();
    }
}
