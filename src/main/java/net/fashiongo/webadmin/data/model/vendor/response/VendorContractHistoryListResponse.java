package net.fashiongo.webadmin.data.model.vendor.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.vendor.VendorContractHistoryList;

import java.util.List;

@Getter
public class VendorContractHistoryListResponse {

    @JsonProperty("content")
    private List<VendorContractHistoryList> contractHistories;

    public VendorContractHistoryListResponse() {
    }

    @Builder
    public VendorContractHistoryListResponse(List<VendorContractHistoryList> contractHistories) {
        this.contractHistories = contractHistories;
    }
}
