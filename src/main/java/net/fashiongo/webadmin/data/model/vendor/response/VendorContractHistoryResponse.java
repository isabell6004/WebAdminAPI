package net.fashiongo.webadmin.data.model.vendor.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.vendor.VendorContractHistory;

import java.util.List;

@Getter
public class VendorContractHistoryResponse {
    @JsonProperty("content")
    private VendorContractHistory contractHistories;

    public VendorContractHistoryResponse() {
    }

    @Builder
    public VendorContractHistoryResponse(VendorContractHistory contractHistories) {
        this.contractHistories = contractHistories;
    }
}
