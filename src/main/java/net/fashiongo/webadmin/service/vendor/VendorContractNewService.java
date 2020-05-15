package net.fashiongo.webadmin.service.vendor;

import net.fashiongo.webadmin.data.model.vendor.ContractPlansResponse;
import net.fashiongo.webadmin.data.model.vendor.SetVendorContractDocumentParameter;
import net.fashiongo.webadmin.data.model.vendor.SetVendorContractParameter;
import net.fashiongo.webadmin.data.model.vendor.VendorContractResponse;
import net.fashiongo.webadmin.data.model.vendor.response.GetVendorContractResponse;
import net.fashiongo.webadmin.data.model.vendor.response.VendorContractDocumentHistoryResponse;
import net.fashiongo.webadmin.data.model.vendor.response.VendorContractHistoryListResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface VendorContractNewService {

    void modifyVendorContractDocument(Long vendorId, SetVendorContractDocumentParameter request);

    void createVendorContractDocument(Long vendorId, SetVendorContractDocumentParameter request);

    void deleteVendorContractDocument(Long vendorId, Long contractId, List<Long> documentIds);

    void reviseContract(Long originalVendorContractHistoryId, SetVendorContractParameter request);

    void createContract(SetVendorContractParameter request);

    void modifyContract(Long originalVendorContractHistoryId, SetVendorContractParameter request);

    VendorContractResponse inquiryVendorContract(Integer vendorId);

    List<ContractPlansResponse> inquiryContractPlans();

    VendorContractDocumentHistoryResponse getContractDocumentHistory(Long contractHistoryId);

    VendorContractHistoryListResponse getContractHistoryList(Long vendorId);

    GetVendorContractResponse getVendorContractIncludedOpenDate(Integer vendorId, LocalDateTime actualOpenDate);
}
