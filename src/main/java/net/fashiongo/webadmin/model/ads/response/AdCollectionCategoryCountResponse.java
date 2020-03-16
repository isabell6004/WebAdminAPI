package net.fashiongo.webadmin.model.ads.response;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AdCollectionCategoryCountResponse {

    private LocalDateTime displayDate;

    private Integer collectionCategoryId;

    private Long counts;

    private AdCollectionCategoryCountResponse() {
    }

    public static AdCollectionCategoryCountResponse of(LocalDateTime displayDate, Integer collectionCategoryId, Long count) {

        AdCollectionCategoryCountResponse response = new AdCollectionCategoryCountResponse();

        response.displayDate = displayDate;
        response.collectionCategoryId = collectionCategoryId;
        response.counts = count;

        return response;
    }
}
