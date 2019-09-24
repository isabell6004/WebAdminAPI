package net.fashiongo.webadmin.data.repository.primary.vendor;

import net.fashiongo.webadmin.data.entity.primary.VendorContractDocumentEntity;

import java.util.List;

public interface VendorContractDocumentEntityRepositoryCustom {

	List<VendorContractDocumentEntity> findAllByVendorContractID(Integer vendorContractID);
}
