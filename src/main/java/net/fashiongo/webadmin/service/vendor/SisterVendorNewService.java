package net.fashiongo.webadmin.service.vendor;

import org.springframework.scheduling.annotation.Async;

public interface SisterVendorNewService {
    @Async("fashionGoApiThreadPoolTaskExecutor")
    void createSisterVendor(Integer vendorId,  Integer originalId, Integer sisterVendorId, Integer requestedUserId, String requestUserName);

    @Async("fashionGoApiThreadPoolTaskExecutor")
    void deleteSisterVendor(Integer vendorId, Integer mapId, Integer requestedUserId, String requestUserName);
}
