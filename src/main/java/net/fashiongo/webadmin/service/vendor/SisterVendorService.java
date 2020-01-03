package net.fashiongo.webadmin.service.vendor;

import net.fashiongo.webadmin.data.model.vendor.VendorSister;
import net.fashiongo.webadmin.model.pojo.common.ResultCode;

import java.util.List;

public interface SisterVendorService {

    List<VendorSister> getSisterVendors(Integer vendorId);

    List<Integer> getSisterVendorChecks(Integer vendorId, Integer sisterVendorId);

    ResultCode setSisterVendor(Integer vendorId, Integer sisterVendorId);

    ResultCode deleteSisterVendor(Integer mapId);
}
