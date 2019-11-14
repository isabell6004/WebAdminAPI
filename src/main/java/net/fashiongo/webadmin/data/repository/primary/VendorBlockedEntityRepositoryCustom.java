package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.VendorBlockedEntity;
import net.fashiongo.webadmin.data.model.vendor.VendorBlock;

import java.time.LocalDateTime;
import java.util.List;

public interface VendorBlockedEntityRepositoryCustom {
    List<VendorBlockedEntity> findAllList();
    List<VendorBlockedEntity> findByBlockID(Integer searchKeyword);
    List<VendorBlockedEntity> findByCompanyNameContainingIgnoreCase(String searchKeyword);
    List<VendorBlockedEntity> findByBlockedOnBetween(LocalDateTime fromDate, LocalDateTime toDate);
    List<VendorBlockedEntity> findByBlockReasonTitle(String searchKeyword);
    List<VendorBlock> findByWholeSalerID(Integer wid);
    VendorBlockedEntity findOneByWholeSalerID(Integer wid);
}
