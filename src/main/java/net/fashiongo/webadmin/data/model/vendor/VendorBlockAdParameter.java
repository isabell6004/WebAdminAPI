package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
public class VendorBlockAdParameter {

    @JsonProperty(value = "VendorId")
    private Long vendorId;

    @JsonProperty(value = "IsAdBlock")
    private Boolean isAdBlock;

    @JsonProperty(value = "AdBlockReasonId")
    private Long adBlockReasonId;

    @JsonProperty(value = "AdBlockChanged")
    private Boolean adBlockChanged;

    public VendorBlockAdParameter() {
    }

    @Builder
    public VendorBlockAdParameter(Long vendorId, Boolean isAdBlock, Long adBlockReasonId, Boolean adBlockChanged) {
        this.vendorId = vendorId;
        this.isAdBlock = isAdBlock;
        this.adBlockReasonId = adBlockReasonId;
        this.adBlockChanged = adBlockChanged;
    }
}
