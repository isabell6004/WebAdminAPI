package net.fashiongo.webadmin.data.repository.primary.vendor;

import net.fashiongo.webadmin.data.entity.primary.VendorCapEntity;

import java.util.List;

public interface VendorCapEntityRepositoryCustom {
    List<VendorCapEntity> findByWholeSalerID(Integer wid);
}
