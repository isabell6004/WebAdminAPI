package net.fashiongo.webadmin.data.repository.primary.vendor;

import net.fashiongo.webadmin.data.entity.primary.WholeSalerEntity;
import net.fashiongo.webadmin.data.entity.primary.vendor.WholesalerCompanyEntity;
import net.fashiongo.webadmin.data.model.common.VendorsCompanyName;
import net.fashiongo.webadmin.data.model.vendor.GetVendorListParameter;
import net.fashiongo.webadmin.data.model.vendor.VendorDetailInfo;
import net.fashiongo.webadmin.data.model.vendor.VendorGroupingSelete;
import net.fashiongo.webadmin.data.model.vendor.VendorGroupingUnSelete;
import net.fashiongo.webadmin.data.model.vendor.VendorList;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public interface VendorWholeSalerEntityRepositoryCustom {
    List<VendorDetailInfo> findAllByID(Integer wholeSalerID);

    WholeSalerEntity findOneByID(Integer wholeSalerID);

    List<WholesalerCompanyEntity> findAllActive();

    Long countByCodeNameAndNotWholeSalerID(Integer wholeSalerID, String codeName);

    List<VendorGroupingSelete> findListVendorGroupingSelect(Integer wholeSalerID, Integer[] companyType, String keyword, ArrayList<Integer> categorys, String alphabet, String vendorType);

    List<VendorGroupingUnSelete> findListVendorGroupingUnSelect(Integer wholeSalerID, Integer[] companyType, String keyword, ArrayList<Integer> categorys, String alphabet, String vendorType);

    List<VendorsCompanyName> findVendors();

    Page<VendorList> getVendorListWithCount(GetVendorListParameter param);
}
