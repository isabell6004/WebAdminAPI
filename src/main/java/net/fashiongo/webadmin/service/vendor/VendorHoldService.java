package net.fashiongo.webadmin.service.vendor;

import java.sql.Timestamp;

/**
 * Created by jinwoo on 2020-01-24.
 */
public interface VendorHoldService {

    Boolean setHoldVendor(Integer wholeSalerID, Integer holdType, Boolean active, Timestamp holdFrom, Timestamp holdTo);

    Integer setHoldVendorUpdate(Integer wholeSalerID, Integer logID, Boolean active, Timestamp holdFrom, Timestamp holdTo);
}
