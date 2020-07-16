package net.fashiongo.webadmin.data.model.collection;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class CollectionSaveParameter {
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

    @JsonProperty(value = "isDisplayPromotion")
    private Boolean isDisplayPromotion;

    @JsonProperty(value = "vendorList")
    private List<Integer> vendorList;

    @JsonProperty(value = "productList")
    private List<Integer> productList;
}
