package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.VendorContractEntity;
import net.fashiongo.webadmin.data.model.vendor.VendorContractHistory;

import java.util.List;

public interface VendorContractEntityRepositoryCustom {

	List<VendorContractEntity> findAllByWholeSalerId(Integer wholeSalerID);

	List<VendorContractHistory> findContractHistoryListByWholeSalerID(Integer wholeSalerID);
}
