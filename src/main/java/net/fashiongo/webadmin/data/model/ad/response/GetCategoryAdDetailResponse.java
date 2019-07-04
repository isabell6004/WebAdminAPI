package net.fashiongo.webadmin.data.model.ad.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.ad.Bidding2;
import net.fashiongo.webadmin.data.model.ad.CuratedBest;

import java.util.List;

@Getter
@Builder
public class GetCategoryAdDetailResponse {

	@JsonProperty("Table")
	private List<Bidding2> biddingList;

	@JsonProperty("Table1")
	private List<CuratedBest> curatedBestList;
}
