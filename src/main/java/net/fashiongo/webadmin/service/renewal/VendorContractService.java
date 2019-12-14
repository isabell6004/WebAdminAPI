package net.fashiongo.webadmin.service.renewal;

import net.fashiongo.webadmin.data.model.vendor.DelVendorContractDocumentParameter;
import net.fashiongo.webadmin.data.model.vendor.SetVendorContractDocumentParameter;

/**
 * Created by jinwoo on 2019-12-12.
 */
public interface VendorContractService {
    Boolean setVendorContractDocument(SetVendorContractDocumentParameter request);

    Boolean delVendorContractDocument(DelVendorContractDocumentParameter request);
}
