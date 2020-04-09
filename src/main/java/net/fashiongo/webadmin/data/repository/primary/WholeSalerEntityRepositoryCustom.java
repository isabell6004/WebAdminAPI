package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.SimpleWholeSalerEntity;
import net.fashiongo.webadmin.data.model.vendor.Vendor;
import net.fashiongo.webadmin.data.model.vendor.VendorAdminAccount;

import java.util.Collection;
import java.util.List;

public interface WholeSalerEntityRepositoryCustom {

	List<Vendor> findAllByActiveTrueAndShopActiveTrueOrderByCompanyName();

	List<Vendor> findAllByOrderActiveOrderByCompanyNameAsc(boolean isOrderActive);

	long countByDirNameAndNotWholeSalerID(Integer wholeSalerID, String dirName);

	List<VendorAdminAccount> findVendorAdminAccountList(Integer wholeSalerID);

	List<SimpleWholeSalerEntity> findAllActiveWholesalers(Boolean shopActive, Boolean orderActive, Collection<Integer> wholesalerIds);
}
