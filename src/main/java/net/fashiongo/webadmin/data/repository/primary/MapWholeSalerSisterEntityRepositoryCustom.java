package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.model.vendor.VendorSister;

import java.util.List;

public interface MapWholeSalerSisterEntityRepositoryCustom {
    List<VendorSister> findVendorSister(Integer wid);
}
