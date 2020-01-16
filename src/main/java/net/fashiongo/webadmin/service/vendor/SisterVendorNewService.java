package net.fashiongo.webadmin.service.vendor;

import org.springframework.scheduling.annotation.Async;

public interface SisterVendorNewService {
    @Async("fashionGoApiThreadPoolTaskExecutor")
    void createSisterVendor(Integer vendorId, Integer sisterVendorId);

    @Async("fashionGoApiThreadPoolTaskExecutor")
    void deleteSisterVendor(Integer vendorId, Integer mapId);
}
