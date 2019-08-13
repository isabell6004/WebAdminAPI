package net.fashiongo.webadmin.data.model.sitemgmt.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import net.fashiongo.webadmin.data.model.sitemgmt.SitemgmtAdPageSpot;
import net.fashiongo.webadmin.data.model.sitemgmt.SitemgmtCategory;
import net.fashiongo.webadmin.data.model.sitemgmt.SitemgmtCollectionCategory;
import net.fashiongo.webadmin.data.model.sitemgmt.SitemgmtMapCollectionCategory;

import java.util.List;

@Getter
@Setter
public class GetCollectionCategoryListResponse {
    @JsonProperty("Table")
    private List<SitemgmtCollectionCategory> collectionCategoryList;

    @JsonProperty("Table1")
    private List<SitemgmtMapCollectionCategory> mapCollectionCategoryList;

    @JsonProperty("Table2")
    private List<SitemgmtAdPageSpot> adPageSpotList;

    @JsonProperty("Table3")
    private List<SitemgmtCategory> categoryList;

}
