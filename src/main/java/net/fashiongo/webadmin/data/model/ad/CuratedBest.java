package net.fashiongo.webadmin.data.model.ad;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CuratedBest {

	@JsonProperty("CollectionCategoryItemID")
	private Integer collectionCategoryItemID;

	@JsonProperty("SpotID")
	private Integer spotID;

	@JsonProperty("WholeSalerID")
	private Integer wholeSalerID;

	@JsonProperty("CompanyName")
	private String companyName;

	@JsonProperty("CollectionCategoryID")
	private Integer collectionCategoryID;

	@JsonProperty("CollectionCategoryType")
	private Integer collectionCategoryType;

	@JsonProperty("ProductID")
	private Integer productID;

	@JsonProperty("ProductName")
	private String productName;

	@JsonProperty("ImageUrlRoot")
	private String imageUrlRoot;

	@JsonProperty("DirName")
	private String dirName;

	@JsonProperty("PictureGeneral")
	private String pictureGeneral;

	@JsonProperty("Active")
	private Integer active;
}
