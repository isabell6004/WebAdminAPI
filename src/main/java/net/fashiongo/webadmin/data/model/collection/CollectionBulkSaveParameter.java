package net.fashiongo.webadmin.data.model.collection;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class CollectionBulkSaveParameter {
    @JsonProperty(value = "collectionId")
    private Integer collectionId;

    @JsonProperty(value = "title")
    private String title;

    @JsonProperty(value = "description")
    private String description;

    @JsonProperty(value = "status")
    private Integer status;

    @JsonProperty(value = "collectionType")
    private Integer collectionType;

    @JsonProperty(value = "desktopImageBannerFilename")
    private String desktopImageBannerFilename;

    @JsonProperty(value = "mobileImageBannerFilename")
    private String mobileImageBannerFilename;
}
