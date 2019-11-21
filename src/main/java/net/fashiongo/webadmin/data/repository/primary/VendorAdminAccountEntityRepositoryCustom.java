package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.VendorAdminAccountEntity;

import java.util.List;

public interface VendorAdminAccountEntityRepositoryCustom {
    List<VendorAdminAccountEntity> findAllByWholeSalerID(Integer wholeSalerID);

    VendorAdminAccountEntity findOneByVendorAdminAccountID(Integer vendorAdminAccountID);
}
