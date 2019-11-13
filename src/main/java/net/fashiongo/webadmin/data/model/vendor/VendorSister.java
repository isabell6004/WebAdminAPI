package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VendorSister {
    @JsonProperty(value = "MapID")
    private Integer mapID;

    @JsonProperty(value = "WholeSalerID")
    private Integer wholeSalerID;

    @JsonProperty(value = "SisterWholeSalerID")
    private Integer sisterWholeSalerID;

    @JsonProperty(value = "CompanyName")
    private String companyName;
}
