package net.fashiongo.webadmin.service.vendor;

import net.fashiongo.webadmin.data.model.vendor.SetVendorContractDocumentParameter;
import net.fashiongo.webadmin.data.model.vendor.SetVendorContractParameter;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

/**
 * Created by jinwoo on 2019-12-12.
 */
public interface VendorContractNewService {

    @Async
    void modifyVendorContractDocument(SetVendorContractDocumentParameter request);

    @Async
    void createVendorContractDocument(SetVendorContractDocumentParameter request);

    @Async
    void deleteVendorContractDocument(List<Long> documentIds);

    @Async
    void createAndModifyVendorContractHistory(SetVendorContractParameter request);
}
