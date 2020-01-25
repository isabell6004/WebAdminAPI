package net.fashiongo.webadmin.service.vendor;

import net.fashiongo.webadmin.data.model.vendor.SetVendorContractDocumentParameter;
import net.fashiongo.webadmin.data.model.vendor.SetVendorContractParameter;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

/**
 * Created by jinwoo on 2019-12-12.
 */
public interface VendorContractNewService {

    @Async("fashionGoApiThreadPoolTaskExecutor")
    void modifyVendorContractDocument(Integer vendorId, SetVendorContractDocumentParameter request, Integer requestedUserId, String requestUserName);

    @Async("fashionGoApiThreadPoolTaskExecutor")
    void createVendorContractDocument(Integer vendorId, SetVendorContractDocumentParameter request, Integer requestedUserId, String requestUserName);

    @Async("fashionGoApiThreadPoolTaskExecutor")
    void deleteVendorContractDocument(Integer vendorId, List<Long> documentIds, Integer requestedUserId, String requestUserName);

    @Async("fashionGoApiThreadPoolTaskExecutor")
    void reviseContract(Long originalVendorContractHistoryId, Long revisedVendorContractHistoryId, SetVendorContractParameter request, Integer userId, String username);

    @Async("fashionGoApiThreadPoolTaskExecutor")
    void createContract(Long originalVendorContractHistoryId, SetVendorContractParameter request, Integer userId, String username);

    @Async("fashionGoApiThreadPoolTaskExecutor")
    void modifyContract(Long originalVendorContractHistoryId, SetVendorContractParameter request, Integer userId, String username);
}
