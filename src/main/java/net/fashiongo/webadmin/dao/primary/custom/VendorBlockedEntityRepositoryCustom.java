package net.fashiongo.webadmin.dao.primary.custom;

import net.fashiongo.webadmin.model.primary.VendorBlockReason;

import java.time.LocalDateTime;
import java.util.List;

public interface VendorBlockedEntityRepositoryCustom {
    List<VendorBlockReason> findAllList();
    List<VendorBlockReason> findByBlockID(Integer searchKeyword);
    List<VendorBlockReason> findByCompanyNameContainingIgnoreCase(String searchKeyword);
    List<VendorBlockReason> findByBlockedOnBetween(LocalDateTime fromDate, LocalDateTime toDate);
    List<VendorBlockReason> findByBlockReasonTitle(String searchKeyword);
}
