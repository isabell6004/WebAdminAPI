package net.fashiongo.webadmin.data.model.ad;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ResultGetCategoryAdCalendar2 {

	private List<Curated> curatedList;

	private List<Bidding> biddingList;

	private List<CollectionCategoryWithCounts> collectionCategoryWithCounts;

}
