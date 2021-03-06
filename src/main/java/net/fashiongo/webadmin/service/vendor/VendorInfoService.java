package net.fashiongo.webadmin.service.vendor;

import net.fashiongo.webadmin.data.model.vendor.SetVendorBasicInfoParameter;
import net.fashiongo.webadmin.data.model.vendor.SetVendorSettingParameter;
import net.fashiongo.webadmin.model.pojo.common.ResultCode;


/**
 * Created by jinwoo on 2020-01-03.
 */
public interface VendorInfoService {
    Integer update(SetVendorBasicInfoParameter request);

    int setEntityActionLog(Integer entityTypeID, Integer wholeSalerID, Integer actionID);

    ResultCode setVendorSettingInfo(SetVendorSettingParameter request);
}
