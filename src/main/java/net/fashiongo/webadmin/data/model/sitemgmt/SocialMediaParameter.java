package net.fashiongo.webadmin.data.model.sitemgmt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SocialMediaParameter {

    @JsonProperty("SocialMediaID")
    private Integer socialMediaId;

    @JsonProperty("SocialMedia")
    private String socialMedia;

    @JsonProperty("Icon")
    private String icon;

}
