package net.fashiongo.webadmin.service.vendor;

import net.fashiongo.webadmin.data.model.vendor.SetVendorBasicInfoParameter;
import net.fashiongo.webadmin.data.model.vendor.VendorDetailInfo;

/**
 * Created by jinwoo on 2020-01-03.
 */
public interface VendorInfoService {
    Integer update(SetVendorBasicInfoParameter request);

    Integer setVendorBasicInfo(VendorDetailInfo requestVendorDetailInfo, Integer saveType, Integer payoutSchedule, Integer payoutScheduleWM, Integer maxPayoutPerDay, Integer payoutCount);

    int setEntityActionLog(Integer entityTypeID, Integer wholeSalerID, Integer actionID);
}
