package net.fashiongo.webadmin.data.model.vendor.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.vendor.VendorContractDocumentHistory;

import java.util.List;

@Getter
public class VendorContractDocumentHistoryResponse {

    @JsonProperty("content")
    private List<VendorContractDocumentHistory> contractDocumentHistories;

    public VendorContractDocumentHistoryResponse() {
    }

    @Builder
    public VendorContractDocumentHistoryResponse(List<VendorContractDocumentHistory> contractDocumentHistories) {
        this.contractDocumentHistories = contractDocumentHistories;
    }
}
