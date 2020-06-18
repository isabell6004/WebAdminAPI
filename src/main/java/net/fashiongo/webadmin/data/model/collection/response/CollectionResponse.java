package net.fashiongo.webadmin.data.model.collection.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CollectionResponse {
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

    @JsonProperty(value = "isDisplayPromotion")
    private boolean isDisplayPromotion;

    @JsonProperty(value = "vendorList")
    private List<VendorCollectionResponse> vendorList;

}
