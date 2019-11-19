package net.fashiongo.webadmin.data.repository.primary.vendor;

import net.fashiongo.webadmin.data.model.vendor.VendorDetailInfo;

import java.util.List;

public interface VendorWholeSalerEntityRepositoryCustom {
    List<VendorDetailInfo> findAllByID(Integer wholeSalerID);
}
