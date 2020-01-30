package net.fashiongo.webadmin.service.vendor;

import net.fashiongo.webadmin.data.entity.primary.WholeSalerEntity;
import net.fashiongo.webadmin.data.model.vendor.SetVendorBasicInfoParameter;
import net.fashiongo.webadmin.data.model.vendor.SetVendorSettingParameter;
import net.fashiongo.webadmin.data.model.vendor.VendorDetailInfo;

/**
 * Created by jinwoo on 2020-01-03.
 */
public interface VendorInfoService {
    Integer update(SetVendorBasicInfoParameter request);

    int setEntityActionLog(Integer entityTypeID, Integer wholeSalerID, Integer actionID);

    Integer setVendorSettingInfo(SetVendorSettingParameter request);
}
