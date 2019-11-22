package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class SetVendorBlockParameter {
    @JsonProperty(value = "WholeSalerID")
    private Integer wholeSalerID;

    @JsonProperty(value = "BlockReasonID")
    private Integer blockReasonID;

    @JsonProperty(value = "Reason")
    private String reason;
}
