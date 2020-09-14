package net.fashiongo.webadmin.dao.primary.custom;

import net.fashiongo.webadmin.data.entity.primary.VendorEntity;
import net.fashiongo.webadmin.data.model.vendor.Vendor;
import net.fashiongo.webadmin.data.model.vendor.VendorAdminAccount;

import java.util.Collection;
import java.util.List;

public interface VendorEntityRepositoryCustom {
    List<VendorEntity> getActiveVendors();

    VendorEntity findByWholeSalerIdAndActive(Long vendorID, Boolean active);

    List<VendorEntity> getEditorPickVendors();

    String getCompanyNameByWholeSalerId(Integer wholeSalerId);

    List<Vendor> findAllByActiveTrueAndShopActiveTrueOrderByCompanyName();

    List<Vendor> findAllByOrderActiveOrderByCompanyNameAsc(boolean isOrderActive);

    long countByDirNameAndNotWholeSalerID(Integer wholeSalerID, String dirName);

    List<VendorAdminAccount> findVendorAdminAccountList(Integer wholeSalerID);

    List<VendorEntity> findAllActiveWholesalers(Boolean shopActive, Boolean orderActive, Collection<Integer> wholesalerIds);

    VendorEntity findByVendorId(Long vendorId);
}
