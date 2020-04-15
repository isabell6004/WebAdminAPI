package net.fashiongo.webadmin.model.ads.response;

import lombok.Getter;

import java.util.List;

@Getter
public class CollectionCategoryItemByDateResponse {

    List<AdCollectionCategoryItemResponse> collectionCategoryItems;

    List<AdCollectionCategoryResponse> collectionCategories;

    List<AdCollectionCategoryMapResponse> mapCollectionCategories;

    private CollectionCategoryItemByDateResponse() {
    }

    public static CollectionCategoryItemByDateResponse of(List<AdCollectionCategoryItemResponse> collectionCategoryItems,
                                                          List<AdCollectionCategoryResponse> collectionCategories,
                                                          List<AdCollectionCategoryMapResponse> mapCollectionCategories) {

        CollectionCategoryItemByDateResponse response = new CollectionCategoryItemByDateResponse();

        response.collectionCategoryItems = collectionCategoryItems;
        response.collectionCategories = collectionCategories;
        response.mapCollectionCategories = mapCollectionCategories;

        return response;
    }
}
