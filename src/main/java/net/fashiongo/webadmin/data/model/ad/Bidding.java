package net.fashiongo.webadmin.data.model.ad;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
public class Bidding {

	@JsonProperty("AdID")
	private Integer adID;

	@JsonProperty("PageID")
	private Integer pageID;

	@JsonProperty("SpotID")
	private Integer spotID;

	@JsonProperty("WholeSalerID")
	private Integer wholeSalerID;

	@JsonProperty("VendorCategoryID")
	private Integer vendorCategoryID;

	@JsonProperty("FromDate")
	private LocalDateTime fromDate;

	@JsonProperty("DateDay")
	private Integer dateDay;

	@JsonProperty("MonthType")
	private String monthType;

	@JsonProperty("ProductID")
	private Integer productID;

	@JsonProperty("Active")
	private Integer active;

	public Bidding(Integer adID, Integer pageID, Integer spotID, Integer wholeSalerID, Integer vendorCategoryID, Timestamp fromDate, Integer dateDay, String monthType, Integer productID, Integer active) {
		this.adID = adID;
		this.pageID = pageID;
		this.spotID = spotID;
		this.wholeSalerID = wholeSalerID;
		this.vendorCategoryID = vendorCategoryID;
		this.fromDate = fromDate.toLocalDateTime();
		this.dateDay = dateDay;
		this.monthType = monthType;
		this.productID = productID;
		this.active = active;
	}
}
