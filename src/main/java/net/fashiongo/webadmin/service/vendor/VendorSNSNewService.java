package net.fashiongo.webadmin.service.vendor;

import net.fashiongo.webadmin.data.model.vendor.SetVendorSNSListParameter;
import org.springframework.scheduling.annotation.Async;

/**
 * Created by jinwoo on 2020-01-08.
 */
public interface VendorSNSNewService {

    @Async
    void create(SetVendorSNSListParameter request);

    @Async
    void delete(SetVendorSNSListParameter request);

    @Async
    void modify(SetVendorSNSListParameter request);
}
