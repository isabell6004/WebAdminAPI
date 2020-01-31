package net.fashiongo.webadmin.service.ads;

import net.fashiongo.webadmin.model.ads.response.CollectionCategoryItemByDateResponse;
import net.fashiongo.webadmin.model.ads.response.CollectionCategoryItemCountsByDateResponse;

import java.time.LocalDateTime;

public interface AdCollectionCategoryItemService {

    CollectionCategoryItemCountsByDateResponse getCountsByDates(LocalDateTime startDisplayOn, LocalDateTime endDisplayOn);

    CollectionCategoryItemByDateResponse getItemsByDate(LocalDateTime displayOn);
}
