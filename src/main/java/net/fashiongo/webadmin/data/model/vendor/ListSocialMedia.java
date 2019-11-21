package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ListSocialMedia {
    @JsonProperty(value = "MapID")
    private Integer mapID;

    @JsonProperty(value = "ReferenceID")
    private Integer referenceID;

    @JsonProperty(value = "SocialMediaID")
    private Integer socialMediaID;

    @JsonProperty(value = "SocialMedia")
    private String socialMedia;

    @JsonProperty(value = "URL")
    private String url;

    @JsonProperty(value = "SocialMediaUsername")
    private String socialMediaUserName;

    @JsonProperty(value = "Icon")
    private String icon;

    @JsonProperty(value = "ListOrder")
    private Integer listOrder;
}
