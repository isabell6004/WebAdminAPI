package net.fashiongo.webadmin.service.vendor;

import org.springframework.scheduling.annotation.Async;

public interface SisterVendorNewService {
    @Async
    void createSisterVendor(Integer vendorId, Integer sisterVendorId);

    @Async
    void deleteSisterVendor(Integer vendorId, Integer mapId);
}
