package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VendorGroupingSelete {
    @JsonProperty(value = "MapID")
    private Integer mapID;

    @JsonProperty(value = "WholeSalerID")
    private Integer wholeSalerID2;

    @JsonProperty(value = "CompanyName")
    private String companyName;

    @JsonProperty(value = "CompanyTypeID")
    private Integer companyTypeID;

    @JsonProperty(value = "SelectChk")
    private String selectChk;
}
