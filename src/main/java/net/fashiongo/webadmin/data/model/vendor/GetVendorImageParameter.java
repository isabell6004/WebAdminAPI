package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class GetVendorImageParameter {
    @JsonProperty(value = "wid")
    private Integer wid;
}
