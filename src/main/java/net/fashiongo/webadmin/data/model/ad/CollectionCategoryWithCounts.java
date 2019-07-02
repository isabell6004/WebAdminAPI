package net.fashiongo.webadmin.data.model.ad;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class CollectionCategoryWithCounts {

	@JsonProperty("CollectionCategoryID")
	private Integer collectionCategoryID;

	@JsonProperty("CollectionCategoryName")
	private String collectionCategoryName;

	@JsonProperty("ParentCollectionCategoryID")
	private Integer parentCollectionCategoryID;

	@JsonProperty("SpotID")
	private Integer spotID;

	@JsonProperty("Lvl")
	private Integer lvl;

	@JsonProperty("BidCount")
	private Integer bidCount;

	@JsonProperty("CuratedCount")
	private Integer curatedCount;

	@JsonProperty("BestCount")
	private Integer bestCount;

	@JsonProperty("NotCount")
	private Integer notCount;
}
