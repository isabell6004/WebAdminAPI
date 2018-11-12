package net.fashiongo.webadmin.model.pojo.bid.parameter;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class GetBidSettingParameter {
	@ApiModelProperty(required = false, example="1")
	@JsonProperty("pageid")
	private Integer pageId;
	
	@ApiModelProperty(required = false, example="0")
	@JsonProperty("spotid")
	private Integer spotId;
	
	@ApiModelProperty(required = false, example="201612")
	@JsonProperty("yearmonth")
	private String yearMonth;
	
	@ApiModelProperty(required = false, example="0")
	@JsonProperty("weekday")
	private Integer weekDay;
	
	@ApiModelProperty(required = false, example="10/28/2018")
	@JsonProperty("fromdate")
	private String fromDate;
	
	@ApiModelProperty(required = false, example="11/03/2018")
	@JsonProperty("todate")
	private String toDate;
	
	public Integer getPageId() {
		return pageId;
	}

	public void setPageId(Integer pageId) {
		this.pageId = pageId;
	}

	public Integer getSpotId() {
		return spotId;
	}

	public void setSpotId(Integer spotId) {
		this.spotId = spotId;
	}

	public String getYearMonth() {
		return yearMonth;
	}

	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}

	public Integer getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(Integer weekDay) {
		this.weekDay = weekDay;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
}
