package net.fashiongo.webadmin.data.model.ad.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.ad.Bidding2;
import net.fashiongo.webadmin.data.model.ad.CollectionCategoryWithCounts;
import net.fashiongo.webadmin.data.model.ad.CuratedBest;

import java.util.List;

@Getter
@Builder
public class GetCategoryAdListResponse {
    @JsonProperty("Table")
    private List<CollectionCategoryWithCounts> categoryList;

    @JsonProperty("Table1")
    private List<Bidding2> biddingList;

    @JsonProperty("Table2")
    private List<CuratedBest> curatedBestList;
}
