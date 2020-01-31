package net.fashiongo.webadmin.model.ads.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import net.fashiongo.webadmin.model.primary.MapCollectionCategory;

@Getter
public class AdCollectionCategoryMapResponse {

    @JsonSerialize(using = ToStringSerializer.class)
    private Integer collectionCategoryId;

    @JsonSerialize(using = ToStringSerializer.class)
    private Integer categoryId;

    private AdCollectionCategoryMapResponse() {
    }

    public static AdCollectionCategoryMapResponse from(MapCollectionCategory mapCollectionCategory) {

        AdCollectionCategoryMapResponse response = new AdCollectionCategoryMapResponse();

        response.collectionCategoryId = mapCollectionCategory.getCollectionCategoryID();
        response.categoryId = mapCollectionCategory.getCategoryID();

        return response;
    }
}
