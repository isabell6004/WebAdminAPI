package net.fashiongo.webadmin.service.vendor;

import net.fashiongo.webadmin.data.model.vendor.SetVendorImageParameter;
import org.springframework.scheduling.annotation.Async;

/**
 * Created by jinwoo on 2020-01-08.
 */
public interface VendorImageNewService {

    @Async("fashionGoApiThreadPoolTaskExecutor")
    void delete(Integer vendorID, Integer bannerId);

    @Async("fashionGoApiThreadPoolTaskExecutor")
    void insert(SetVendorImageParameter request);
}
