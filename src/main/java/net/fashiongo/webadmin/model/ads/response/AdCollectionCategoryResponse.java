package net.fashiongo.webadmin.model.ads.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import net.fashiongo.webadmin.model.primary.CollectionCategory;

@Getter
public class AdCollectionCategoryResponse {

    @JsonProperty("id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer collectionCategoryId;

    @JsonProperty("name")
    private String collectionCategoryName;

    @JsonSerialize(using = ToStringSerializer.class)
    private Integer parentCollectionCategoryId;

    @JsonSerialize(using = ToStringSerializer.class)
    @JsonProperty("referenceId")
    private Integer spotId;

    public AdCollectionCategoryResponse() {
    }

    public static AdCollectionCategoryResponse from(CollectionCategory collectionCategory) {

        AdCollectionCategoryResponse response = new AdCollectionCategoryResponse();

        response.collectionCategoryId = collectionCategory.getCollectionCategoryID();
        response.collectionCategoryName = collectionCategory.getCollectionCategoryName();
        response.parentCollectionCategoryId = collectionCategory.getParentCollectionCategoryID() == 0 ?
                null : collectionCategory.getParentCollectionCategoryID();
        response.spotId = collectionCategory.getSpotID();

        return response;
    }
}
