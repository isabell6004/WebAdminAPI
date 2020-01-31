package net.fashiongo.webadmin.model.ads.response;

import lombok.Getter;

import java.util.List;

@Getter
public class CollectionCategoryItemCountsByDateResponse {

    private List<AdCollectionCategoryCountResponse> collectionCategoryCounts;

    private List<AdCollectionCategoryResponse> collectionCategories;

    private CollectionCategoryItemCountsByDateResponse() {
    }

    public static CollectionCategoryItemCountsByDateResponse of(List<AdCollectionCategoryCountResponse> collectionCategoryCounts,
                                                                List<AdCollectionCategoryResponse> collectionCategories) {

        CollectionCategoryItemCountsByDateResponse response = new CollectionCategoryItemCountsByDateResponse();

        response.collectionCategoryCounts = collectionCategoryCounts;
        response.collectionCategories = collectionCategories;

        return response;
    }
}
