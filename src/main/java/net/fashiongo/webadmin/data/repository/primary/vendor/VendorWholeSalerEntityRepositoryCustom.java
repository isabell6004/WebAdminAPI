package net.fashiongo.webadmin.data.repository.primary.vendor;

import net.fashiongo.webadmin.data.entity.primary.WholeSalerEntity;
import net.fashiongo.webadmin.data.entity.primary.vendor.WholesalerCompanyEntity;
import net.fashiongo.webadmin.data.model.common.VendorsCompanyName;
import net.fashiongo.webadmin.data.model.vendor.*;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public interface VendorWholeSalerEntityRepositoryCustom {
    List<VendorDetailInfo> findAllByID(Integer wholeSalerID);

    WholeSalerEntity findOneByID(Integer wholeSalerID);

    List<WholesalerCompanyEntity> findAllActive();

    Long countByCodeNameAndNotWholeSalerID(Integer wholeSalerID, String codeName);

    List<VendorGroupingSelected> findListVendorGroupingSelect(Integer wholeSalerID, Integer[] companyType, String keyword, ArrayList<Integer> categorys, String alphabet, String vendorType);

    List<VendorGroupingUnselect> findListVendorGroupingUnSelect(Integer wholeSalerID, Integer[] companyType, String keyword, ArrayList<Integer> categorys, String alphabet, String vendorType);

    List<VendorsCompanyName> findVendors();

    Page<VendorList> getVendorListWithCount(GetVendorListParameter param);

    List<VendorListCSV> getVendorListCSVWithCount(GetVendorListParameter param);
}
