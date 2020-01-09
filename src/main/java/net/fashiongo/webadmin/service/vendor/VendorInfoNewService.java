package net.fashiongo.webadmin.service.vendor;

import net.fashiongo.webadmin.data.model.vendor.SetVendorBasicInfoParameter;
import net.fashiongo.webadmin.data.model.vendor.VendorDetailInfo;
import org.springframework.scheduling.annotation.Async;

/**
 * Created by jinwoo on 2020-01-06.
 */
public interface VendorInfoNewService {

    @Async
    void update(VendorDetailInfo request);
}
