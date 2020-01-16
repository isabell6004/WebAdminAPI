package net.fashiongo.webadmin.service.vendor;

import org.springframework.scheduling.annotation.Async;

/**
 * Created by jinwoo on 2020-01-09.
 */
public interface VendorSisterAccountNewService {

    @Async("fashionGoApiThreadPoolTaskExecutor")
    void regist(Integer vendorId, Integer sisterVendorId);

    @Async("fashionGoApiThreadPoolTaskExecutor")
    void delete(Integer vendorId, Integer sisterVendorId);
}
