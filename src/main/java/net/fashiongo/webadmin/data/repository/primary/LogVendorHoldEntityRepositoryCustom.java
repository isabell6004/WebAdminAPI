package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.LogVendorHoldEntity;

import java.util.List;

public interface LogVendorHoldEntityRepositoryCustom {
    List<LogVendorHoldEntity> findByWholeSalerIDAndActiveAndHoldTo(Integer wid);
    LogVendorHoldEntity findByWholeSalerIDAndActive(Integer wid);    
}
