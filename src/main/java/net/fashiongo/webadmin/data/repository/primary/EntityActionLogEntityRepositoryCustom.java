package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.model.buyer.ModifiedByBuyer;
import net.fashiongo.webadmin.data.model.vendor.VendorHistory;

import java.time.LocalDateTime;
import java.util.List;

public interface EntityActionLogEntityRepositoryCustom {
    List<VendorHistory> findByEntityIDAndEntityTypeID(Integer wid);

    List<ModifiedByBuyer> up_wa_GetModifiedByBuyer(LocalDateTime fromDateTime,LocalDateTime toDateTime);
}
