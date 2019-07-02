package net.fashiongo.webadmin.data.model.ad;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class Curated {

	@JsonProperty("CollectionCategoryItemID")
	private Integer collectionCategoryItemID;

	@JsonProperty("SpotID")
	private Integer spotID;

	@JsonProperty("FromDate")
	private LocalDateTime fromDate;

	@JsonProperty("CollectionCategoryID")
	private Integer collectionCategoryID;

	@JsonProperty("ProductID")
	private Integer productID;

	@JsonProperty("CollectionCategoryType")
	private Integer collectionCategoryType;

	@JsonProperty("DateDay")
	private Integer dateDay;

	@JsonProperty("MonthType")
	private String monthType;

	@JsonProperty("Active")
	private Integer active;
}
