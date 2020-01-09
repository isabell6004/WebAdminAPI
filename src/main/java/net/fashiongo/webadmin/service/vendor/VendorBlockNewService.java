package net.fashiongo.webadmin.service.vendor;

import net.fashiongo.webadmin.data.model.vendor.SetVendorBlockParameter;
import net.fashiongo.webadmin.data.model.vendor.SetVendorBlockUpdate;
import net.fashiongo.webadmin.model.pojo.parameter.DelVendorBlockParameter;
import org.springframework.scheduling.annotation.Async;

/**
 * Created by jinwoo on 2020-01-02.
 */
public interface VendorBlockNewService {

    @Async
    void blockVendor(SetVendorBlockParameter request);

    @Async
    void modifyBlockReason(SetVendorBlockUpdate request);

    @Async
    void unblockVendor(DelVendorBlockParameter request);
}
