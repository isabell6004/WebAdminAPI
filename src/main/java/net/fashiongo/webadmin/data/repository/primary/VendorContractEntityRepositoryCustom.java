package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.VendorContractEntity;

import java.util.List;

public interface VendorContractEntityRepositoryCustom {

	List<VendorContractEntity> findAllByWholeSalerId(Integer wholeSalerID);
}
