package net.fashiongo.webadmin.data.model.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class VendorsCompanyName {
    @JsonProperty(value = "WholeSalerID")
    private Integer wholeSalerID;

    @JsonProperty(value = "CompanyName")
    private String companyName;

    @JsonProperty(value = "DispName")
    private String dispName;

    public VendorsCompanyName(Integer wholeSalerID, String companyName) {
        this.wholeSalerID = wholeSalerID;
        this.companyName = companyName;
        this.dispName = String.valueOf(wholeSalerID).concat("-").concat(companyName);
    }
}
