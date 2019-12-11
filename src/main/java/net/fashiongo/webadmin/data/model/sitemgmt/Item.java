package net.fashiongo.webadmin.data.model.sitemgmt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class Item {

	@JsonProperty("ActivatedOn")
	private LocalDateTime activatedOn;

	@JsonProperty("AdVendorItemID")
	private Integer adVendorItemID;

	@JsonProperty("AvailableOn")
	private LocalDateTime availableOn;

	@JsonProperty("CategoryID")
	private Integer categoryID;

	@JsonProperty("ColorCount")
	private Integer colorCount;

	@JsonProperty("CompanyName")
	private String companyName;

	@JsonProperty("DirName")
	private String dirName;

	@JsonProperty("ImageUrlRoot")
	private String imageUrlRoot;

	@JsonProperty("PictureGeneral")
	private String pictureGeneral;

	@JsonProperty("Price")
	private BigDecimal price;

	@JsonProperty("ProductID")
	private Integer productID;

	@JsonProperty("ProductName")
	private String productName;

	@JsonProperty("SelectItem")
	private Integer selectItem;

	@JsonProperty("UnitPrice1")
	private BigDecimal unitPrice1;

	@JsonProperty("WholeSalerID")
	private Integer wholeSalerID;

	@JsonProperty("rowno")
	private Integer rowno;
}
