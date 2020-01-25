package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class SetHoldVendorUpdateParameter {
    @JsonProperty(value = "WholeSalerID")
    private Integer wholeSalerID;
    
	@JsonProperty(value = "LogID")
    private Integer logID;

    @JsonProperty(value = "Active")
    private Boolean active;

    @JsonProperty(value = "HoldFrom")
    private String holdFrom;

    @JsonProperty(value = "HoldTo")
    private String holdTo;
}
