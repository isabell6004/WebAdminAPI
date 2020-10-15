package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
public class VendorBlockPayoutParameter {
    @JsonProperty(value = "VendorId")
    private Long vendorId;

    @JsonProperty(value = "IsPayoutBlock")
    private Boolean isPayoutBlock;

    @JsonProperty(value = "PayoutBlockReasonId")
    private Long payoutBlockReasonId;

    @JsonProperty(value = "PayoutBlockChanged")
    private Boolean payoutBlockChanged;

    public VendorBlockPayoutParameter() {
    }

    @Builder
    public VendorBlockPayoutParameter(Long vendorId, Boolean isPayoutBlock, Long payoutBlockReasonId, Boolean payoutBlockChanged) {
        this.vendorId = vendorId;
        this.isPayoutBlock = isPayoutBlock;
        this.payoutBlockReasonId = payoutBlockReasonId;
        this.payoutBlockChanged = payoutBlockChanged;
    }


}
