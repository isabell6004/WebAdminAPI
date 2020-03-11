package net.fashiongo.webadmin.service.vendor;

import net.fashiongo.webadmin.data.model.vendor.SetVendorSNSListParameter;

/**
 * Created by jinwoo on 2020-01-08.
 */
public interface VendorSNSService {
    boolean modifyVendorSNSInfo(SetVendorSNSListParameter param);
}
