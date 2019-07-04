package net.fashiongo.webadmin.data.model.ad;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class Bidding2 {

	@JsonProperty("AdID")
	private Integer adID;

	@JsonProperty("SpotID")
	private Integer spotID;

	@JsonProperty("WholeSalerID")
	private Integer wholeSalerID;

	@JsonProperty("CollectionCategoryID")
	private Integer collectionCategoryID;

	@JsonProperty("CollectionCategoryType")
	private Integer collectionCategoryType;

	@JsonProperty("ActualPrice")
	private BigDecimal actualPrice;

	@JsonProperty("ProductID")
	private Integer productID;

	@JsonProperty("ItemCount")
	private Integer itemCount;

	@JsonProperty("ProductName")
	private String productName;

	@JsonProperty("CompanyName")
	private String companyName;

	@JsonProperty("ImageUrlRoot")
	private String imageUrlRoot;

	@JsonProperty("DirName")
	private String dirName;

	@JsonProperty("PictureGeneral")
	private String pictureGeneral;

	@JsonProperty("Active")
	private Integer active;
}
