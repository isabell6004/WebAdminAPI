package net.fashiongo.webadmin.data.repository.primary.procedure;

import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.sitemgmt.SitemgmtAdPageSpot;
import net.fashiongo.webadmin.data.model.sitemgmt.SitemgmtCategory;
import net.fashiongo.webadmin.data.model.sitemgmt.SitemgmtCollectionCategory;
import net.fashiongo.webadmin.data.model.sitemgmt.SitemgmtMapCollectionCategory;

import java.util.List;

@Getter
@Builder
public class ResultGetCollectionCategory {
    List<SitemgmtCollectionCategory> collectionCategories;
    List<SitemgmtMapCollectionCategory> mapCollectionCategories;
    List<SitemgmtAdPageSpot> adPageSpots;
    List<SitemgmtCategory> categories;
}
