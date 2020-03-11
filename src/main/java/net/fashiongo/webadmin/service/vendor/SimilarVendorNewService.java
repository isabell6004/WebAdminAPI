package net.fashiongo.webadmin.service.vendor;

import net.fashiongo.webadmin.data.model.vendor.VendorSimilarDto;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

public interface SimilarVendorNewService {
    @Async("fashionGoApiThreadPoolTaskExecutor")
    void addSimilarVendor(Integer vendorId, List<VendorSimilarDto> similarVendorList, Integer requestedUserId, String requestUserName);

    @Async("fashionGoApiThreadPoolTaskExecutor")
    void deleteSimilarVendor(Integer vendorId, List<Integer> mapIdList, Integer requestedUserId, String requestUserName);
}
