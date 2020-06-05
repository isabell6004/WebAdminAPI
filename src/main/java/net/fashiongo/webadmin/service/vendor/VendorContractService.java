package net.fashiongo.webadmin.service.vendor;

import net.fashiongo.webadmin.data.model.vendor.DelVendorContractParameter;
import net.fashiongo.webadmin.data.model.vendor.SetVendorContractParameter;

/**
 * Created by jinwoo on 2019-12-12.
 */
public interface VendorContractService {
    void setVendorContract(SetVendorContractParameter request);

    void delVendorContract(DelVendorContractParameter request);
}
