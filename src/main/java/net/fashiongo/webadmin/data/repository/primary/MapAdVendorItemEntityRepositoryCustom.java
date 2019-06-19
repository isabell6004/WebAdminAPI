package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.model.ad.CategoryAdItem;

import java.util.List;

public interface MapAdVendorItemEntityRepositoryCustom {
    List<CategoryAdItem> findCategoryAdItemOrderByListOrder(Integer adID);
}
