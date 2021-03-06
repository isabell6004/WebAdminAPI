package net.fashiongo.webadmin.model.pojo.ad;

import javax.persistence.Column;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FGListADCalendar {

	@JsonProperty("TotalSpotCount")
	private Integer totalSpotCount;
	@JsonProperty("TotalSoldCount")
	private Integer totalSoldCount;
	@JsonProperty("TotalSetCount")
	private Integer totalSetCount;
	@JsonProperty("CategoryID")
	private Integer categoryID;
	@JsonProperty("DateIndex")
	private Integer dateIndex;
	@JsonProperty("CategoryName")
	private String categoryName;
	
	public Integer getTotalSpotCount() {
		return totalSpotCount;
	}
	public Integer getTotalSoldCount() {
		return totalSoldCount;
	}
	public Integer getTotalSetCount() {
		return totalSetCount;
	}
	public Integer getCategoryID() {
		return categoryID;
	}	
	public Integer getDateIndex() {
		return dateIndex;
	}	
	public String getCategoryName() {
		return categoryName;
	}	
}