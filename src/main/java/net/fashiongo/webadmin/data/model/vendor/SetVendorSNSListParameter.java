package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class SetVendorSNSListParameter {
    @JsonProperty(value = "MapID")
    private Integer mapID;

    @JsonProperty(value = "WholeSalerID")
    private Integer wholeSalerID;

    @JsonProperty(value = "SocialMediaID")
    private Integer socialMediaID;

    @JsonProperty(value = "SocialMediaUsername")
    private String socialMediaUsername;
}
