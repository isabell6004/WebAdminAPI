package net.fashiongo.webadmin.service.vendor;

import net.fashiongo.webadmin.data.entity.primary.VendorContractEntity;
import net.fashiongo.webadmin.data.model.vendor.DelVendorContractDocumentParameter;
import net.fashiongo.webadmin.data.model.vendor.SetVendorContractDocumentParameter;
import net.fashiongo.webadmin.data.model.vendor.SetVendorContractParameter;
import net.fashiongo.webadmin.model.primary.VendorContract;

import java.time.LocalDateTime;

/**
 * Created by jinwoo on 2019-12-12.
 */
public interface VendorContractService {
    Boolean setVendorContractDocument(SetVendorContractDocumentParameter request);

    Boolean delVendorContractDocument(DelVendorContractDocumentParameter request);

    Boolean setVendorContract(SetVendorContractParameter request);

    VendorContractEntity getVendorContractIncludedOpenDate(Integer vendorId, LocalDateTime actualOpenDate);
}
