package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
public class SetVendorBlockUpdate {
    @JsonProperty(value = "WholeSalerID")
    private Integer wholeSalerID;

    @JsonProperty(value = "BlockReasonID")
    private Integer blockReasonID;

    @JsonIgnore
    @Setter
    private Boolean isBlock;
}
