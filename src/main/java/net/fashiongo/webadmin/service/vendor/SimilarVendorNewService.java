package net.fashiongo.webadmin.service.vendor;

import org.springframework.scheduling.annotation.Async;

import java.util.List;

public interface SimilarVendorNewService {
    @Async("fashionGoApiThreadPoolTaskExecutor")
    void addSimilarVendor(Integer vendorId, List<Integer> similarVendorIdList, Integer requestedUserId, String requestUserName);

    @Async("fashionGoApiThreadPoolTaskExecutor")
    void deleteSimilarVendor(Integer vendorId, List<Integer> mapIdList, Integer requestedUserId, String requestUserName);
}
