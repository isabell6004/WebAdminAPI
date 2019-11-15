package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class SetHoldVendorParameter {
    @JsonProperty(value = "WholeSalerID")
    private Integer wholeSalerID;

    @JsonProperty(value = "HoldType")
    private Integer holdType;

    @JsonProperty(value = "Active")
    private Boolean active;

    @JsonProperty(value = "HoldFrom")
    private String holdFrom;

    @JsonProperty(value = "HoldTo")
    private String holdTo;
}
