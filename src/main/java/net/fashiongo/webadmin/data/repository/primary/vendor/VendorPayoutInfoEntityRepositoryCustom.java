package net.fashiongo.webadmin.data.repository.primary.vendor;

import net.fashiongo.webadmin.data.model.vendor.VendorPayoutInfo;

import java.util.List;

public interface VendorPayoutInfoEntityRepositoryCustom {
    List<VendorPayoutInfo> findAllByWholeSalerID(Integer wholeSalerID);
}
