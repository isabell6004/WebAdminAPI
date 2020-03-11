package net.fashiongo.webadmin.service.vendor;

import net.fashiongo.webadmin.data.model.vendor.SetVendorSNSListParameter;
import org.springframework.scheduling.annotation.Async;

/**
 * Created by jinwoo on 2020-01-08.
 */
public interface VendorSNSNewService {

    @Async("fashionGoApiThreadPoolTaskExecutor")
    void create(SetVendorSNSListParameter request, Integer requestUserId, String requestUserName);

    @Async("fashionGoApiThreadPoolTaskExecutor")
    void delete(SetVendorSNSListParameter request, Integer requestUserId, String requestUserName);

    @Async("fashionGoApiThreadPoolTaskExecutor")
    void modify(SetVendorSNSListParameter request, Integer requestUserId, String requestUserName);
}
