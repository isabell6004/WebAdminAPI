package net.fashiongo.webadmin.data.model.ad.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.ad.Bidding;
import net.fashiongo.webadmin.data.model.ad.CollectionCategoryWithCounts;
import net.fashiongo.webadmin.data.model.ad.Curated;

import java.util.List;

@Getter
@Builder
public class GetCategoryAdCalendarResponse {

	@JsonProperty("Table")
	private List<CollectionCategoryWithCounts> collectionCategoryWithCounts;

	@JsonProperty("Table1")
	private List<Bidding> biddingList;

	@JsonProperty("Table2")
	private List<Curated> curatedList;
}
