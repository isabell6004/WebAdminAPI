package net.fashiongo.webadmin.service.vendor;

import org.springframework.scheduling.annotation.Async;

import java.util.List;

public interface SimilarVendorNewService {
    @Async
    void addSimilarVendor(Integer vendorId, List<Integer> similarVendorIdList);

    @Async
    void deleteSimilarVendor(Integer vendorId, List<Integer> mapIdList);
}
