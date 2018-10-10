package net.fashiongo.webadmin.model.pojo.parameter;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class SetBidSettingParameter {
	@ApiModelProperty(required = false, example=" ")
	@JsonProperty("dataflag")
	private String dataFlag;
	
	@ApiModelProperty(required = false, example="11/02/2018,11/03/2018")
	@JsonProperty("fromdate")
	private String fromData;
	
	@ApiModelProperty(required = false, example="82,82")
	@JsonProperty("spotid")
	private String spotId;
	
	@ApiModelProperty(required = false, example="08/13/2018 09:00,08/06/2018 09:00")
	@JsonProperty("startedon")
	private String startedOn;
	
	@ApiModelProperty(required = false, example="08/17/2018 14:20,08/10/2018 14:25")
	@JsonProperty("endedon")
	private String endedOn;
	
	@ApiModelProperty(required = false, example="40,40")
	@JsonProperty("startingprice")
	private String startingPrice;
	
	@ApiModelProperty(required = false, example="3,3")
	@JsonProperty("priceunit")
	private String priceUnit;
	
	@ApiModelProperty(required = false, example="0")
	@JsonProperty("buyitnowprice")
	private Integer buyItNowPrice;
	
	public String getDataFlag() {
		return dataFlag;
	}
	public void setDataFlag(String dataFlag) {
		this.dataFlag = dataFlag;
	}
	public String getFromData() {
		return fromData;
	}
	public void setFromData(String fromData) {
		this.fromData = fromData;
	}
	public String getSpotId() {
		return spotId;
	}
	public void setSpotId(String spotId) {
		this.spotId = spotId;
	}
	public String getStartedOn() {
		return startedOn;
	}
	public void setStartedOn(String startedOn) {
		this.startedOn = startedOn;
	}
	public String getEndedOn() {
		return endedOn;
	}
	public void setEndedOn(String endedOn) {
		this.endedOn = endedOn;
	}
	public String getStartingPrice() {
		return startingPrice;
	}
	public void setStartingPrice(String startingPrice) {
		this.startingPrice = startingPrice;
	}
	public String getPriceUnit() {
		return priceUnit;
	}
	public void setPriceUnit(String priceUnit) {
		this.priceUnit = priceUnit;
	}
	public Integer getBuyItNowPrice() {
		return buyItNowPrice;
	}
	public void setBuyItNowPrice(Integer buyItNowPrice) {
		this.buyItNowPrice = buyItNowPrice;
	}
	
	
}
