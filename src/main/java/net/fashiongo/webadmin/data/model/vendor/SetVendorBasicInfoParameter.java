package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class SetVendorBasicInfoParameter {
    @JsonProperty(value = "wid")
    private Integer wid;

    @JsonProperty(value = "vendorBasicInfo")
    private String vendorBasicInfo;

    @JsonProperty(value = "CompanyNameTemp")
    private String companyNameTemp;
}
