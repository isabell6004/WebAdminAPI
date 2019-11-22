package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.model.vendor.Vendor;

import java.util.List;

public interface WholeSalerEntityRepositoryCustom {

	List<Vendor> findAllByActiveTrueAndShopActiveTrueOrderByCompanyName();

	List<Vendor> findAllByOrderActiveOrderByCompanyNameAsc(boolean isOrderActive);

	long countByDirNameAndNotWholeSalerID(Integer wholeSalerID, String dirName);
}
