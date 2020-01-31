package net.fashiongo.webadmin.model.ads.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import net.fashiongo.webadmin.model.primary.CollectionCategoryItem;

@Getter
public class AdCollectionCategoryItemResponse {

    @JsonSerialize(using = ToStringSerializer.class)
    private Integer collectionCategoryId;

    @JsonSerialize(using = ToStringSerializer.class)
    private Integer productId;

    @JsonProperty("type")
    private Integer collectionCategoryType;

    @JsonProperty("id")
    private Integer collectionCategoryItemId;

    private AdCollectionCategoryItemResponse() {
    }

    public static AdCollectionCategoryItemResponse from(CollectionCategoryItem collectionCategoryItem) {

        AdCollectionCategoryItemResponse response = new AdCollectionCategoryItemResponse();

        response.collectionCategoryId = collectionCategoryItem.getCollectionCategoryID();
        response.productId = collectionCategoryItem.getProductID();
        response.collectionCategoryType = collectionCategoryItem.getCollectionCategoryType();
        response.collectionCategoryItemId = collectionCategoryItem.getCollectionCategoryItemID();

        return response;
    }
}
