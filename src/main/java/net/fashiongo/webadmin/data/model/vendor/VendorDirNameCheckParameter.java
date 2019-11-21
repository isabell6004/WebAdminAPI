package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class VendorDirNameCheckParameter {
    @JsonProperty(value = "WholeSalerID")
    private Integer wholeSalerID;

    @JsonProperty(value = "DirName")
    private String dirName;
}
