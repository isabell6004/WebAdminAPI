package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class GetVendorSettingParameter {
    @JsonProperty(value = "wid")
    private Integer wid;
}
