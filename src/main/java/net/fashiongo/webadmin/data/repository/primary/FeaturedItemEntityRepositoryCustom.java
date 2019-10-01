package net.fashiongo.webadmin.data.repository.primary;


import net.fashiongo.webadmin.data.model.sitemgmt.FeaturedItemCount;
import net.fashiongo.webadmin.data.model.sitemgmt.FeaturedItemList;

import java.util.List;

public interface FeaturedItemEntityRepositoryCustom {
    List<FeaturedItemCount> getFeaturedItemCount(String period);

    List<FeaturedItemList> getFeaturedItemList(String period);

    List<FeaturedItemList> getFeaturedItemListDay(String period);
}
