package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.model.vendor.VendorHistory;

import java.util.List;

public interface EntityActionLogEntityRepositoryCustom {
    List<VendorHistory> findByEntityIDAndEntityTypeID(Integer wid);
}
