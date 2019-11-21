package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class SetVendorImageParameter {
    @JsonProperty(value = "wid")
    private Integer wid;

    @JsonProperty(value = "type")
    private Integer type;

    @JsonProperty(value = "filename")
    private String filename;

    @JsonProperty(value = "userid")
    private String userid;
}
