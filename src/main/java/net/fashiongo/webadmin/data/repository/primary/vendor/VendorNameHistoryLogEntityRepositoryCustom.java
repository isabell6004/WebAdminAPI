package net.fashiongo.webadmin.data.repository.primary.vendor;

import net.fashiongo.webadmin.data.model.vendor.VendorNameHistoryLog;

import java.util.List;

public interface VendorNameHistoryLogEntityRepositoryCustom {
    List<VendorNameHistoryLog> findAllByIDOrderByCreatedOn(Integer wholeSalerID);
}
