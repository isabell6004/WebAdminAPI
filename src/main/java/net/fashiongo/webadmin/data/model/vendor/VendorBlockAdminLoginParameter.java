package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
public class VendorBlockAdminLoginParameter {
    @JsonProperty(value = "VendorId")
    private Long vendorId;

    @JsonProperty(value = "IsBlock")
    private Boolean isBlock;

    @JsonProperty(value = "BlockReasonId")
    private Long blockReasonId;

    @JsonProperty(value = "BlockChanged")
    private Boolean blockChanged;

    public VendorBlockAdminLoginParameter() {
    }

    @Builder
    public VendorBlockAdminLoginParameter(Long vendorId, Boolean isBlock, Long blockReasonId, Boolean blockChanged) {
        this.vendorId = vendorId;
        this.isBlock = isBlock;
        this.blockReasonId = blockReasonId;
        this.blockChanged = blockChanged;
    }
}
