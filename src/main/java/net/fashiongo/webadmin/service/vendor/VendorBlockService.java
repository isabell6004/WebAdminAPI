package net.fashiongo.webadmin.service.vendor;

import net.fashiongo.webadmin.data.model.vendor.SetVendorBlockParameter;
import net.fashiongo.webadmin.data.model.vendor.SetVendorBlockUpdate;
import net.fashiongo.webadmin.model.pojo.parameter.DelVendorBlockParameter;

/**
 * Created by jinwoo on 2020-01-02.
 */
public interface VendorBlockService {

    Boolean block(SetVendorBlockParameter param);

    Integer modifyBlockReason(SetVendorBlockUpdate param);

    Boolean unblock(DelVendorBlockParameter param);
}
