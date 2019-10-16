package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.model.vendor.Vendor;

import java.util.List;

public interface WholeSalerEntityRepositoryCustom {

	List<Vendor> findAllByActiveTrueAndShopActiveTrueOrderByCompanyName();
}
