package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class DelVendorSisterParameter {
    @JsonProperty(value = "MapID")
    private Integer MapID;
}
