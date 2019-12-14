package net.fashiongo.webadmin.service.renewal;

import net.fashiongo.webadmin.data.model.vendor.SetVendorContractDocumentParameter;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

/**
 * Created by jinwoo on 2019-12-12.
 */
public interface VendorContractNewService {

    @Async
    Boolean createAndModifyVendorContractDocument(SetVendorContractDocumentParameter request);

    @Async
    Boolean deleteVendorContractDocument(List<Long> documentIds);
}
