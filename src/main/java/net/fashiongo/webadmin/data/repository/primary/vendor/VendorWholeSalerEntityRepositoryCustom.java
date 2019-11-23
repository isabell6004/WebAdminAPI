package net.fashiongo.webadmin.data.repository.primary.vendor;

import net.fashiongo.webadmin.data.entity.primary.SimpleWholeSalerEntity;
import net.fashiongo.webadmin.data.entity.primary.WholeSalerEntity;
import net.fashiongo.webadmin.data.entity.primary.vendor.WholesalerCompanyEntity;
import net.fashiongo.webadmin.data.model.vendor.VendorDetailInfo;

import java.util.List;

public interface VendorWholeSalerEntityRepositoryCustom {
    List<VendorDetailInfo> findAllByID(Integer wholeSalerID);

    WholeSalerEntity findOneByID(Integer wholeSalerID);

    List<WholesalerCompanyEntity> findAllActive();

    Long countByCodeNameAndNotWholeSalerID(Integer wholeSalerID, String codeName);
}