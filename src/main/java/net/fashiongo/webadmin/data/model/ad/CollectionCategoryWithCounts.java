package net.fashiongo.webadmin.data.model.ad;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class CollectionCategoryWithCounts {

	public CollectionCategoryWithCounts() {

	}

	public CollectionCategoryWithCounts(Integer collectionCategoryID, String collectionCategoryName, Integer parentCollectionCategoryID, Integer spotID,
										Integer lvl, Integer bidCount, Integer curatedCount, Integer bestCount, Integer notCount) {
		this.collectionCategoryID = collectionCategoryID;
		this.collectionCategoryName = collectionCategoryName;
		this.parentCollectionCategoryID = parentCollectionCategoryID;
		this.spotID = spotID;
		this.lvl = lvl;
		this.bidCount = bidCount;
		this.curatedCount = curatedCount;
		this.bestCount = bestCount;
		this.notCount = notCount;
	}

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

	@JsonProperty("CategoryID")
	private Integer categoryID;

	@JsonProperty("ChildCount")
	private Long childCount;

	@JsonProperty("BidCount")
	private Integer bidCount;

	@JsonProperty("CuratedCount")
	private Integer curatedCount;

	@JsonProperty("BestCount")
	private Integer bestCount;

	@JsonProperty("NotCount")
	private Integer notCount;
}
