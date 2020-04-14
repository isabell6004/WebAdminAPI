package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.VendorContractEntity;
import net.fashiongo.webadmin.data.model.vendor.VendorContractHistoryList;

import java.time.LocalDateTime;
import java.util.List;

public interface VendorContractEntityRepositoryCustom {

	List<VendorContractEntity> findAllByWholeSalerId(Integer wholeSalerID);

	List<VendorContractHistoryList> findContractHistoryListByWholeSalerID(Integer wholeSalerID);

	VendorContractEntity findOneByWholeSalerID(Integer wholeSalerID);

	VendorContractEntity findOneByVendorContractID(Integer vendorContractID);

    VendorContractEntity findVendorContractByVendorIdAndOpenDate(Integer veondorId, LocalDateTime openDate);
}
