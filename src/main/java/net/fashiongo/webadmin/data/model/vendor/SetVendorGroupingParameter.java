package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class SetVendorGroupingParameter {
    @JsonProperty(value = "wid")
    private Integer wid;

    @JsonProperty(value = "SaveIDs")
    private String saveIds;

    @JsonProperty(value = "DeleteIDs")
    private String deleteIds;
}
