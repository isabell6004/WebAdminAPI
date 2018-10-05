package net.fashiongo.webadmin.model.pojo.parameter;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author Nayeon Kim
 */
public class SetAddSpotSettingParameter {
	@ApiModelProperty(required = false, example="82")
	@JsonProperty("spotid")
	private Integer spotID;
	
	@ApiModelProperty(required = false, example="1")
	@JsonProperty("pageid")
	private Integer pageID;
	
	@ApiModelProperty(required = false, example="7")
	@JsonProperty("categoryid")
	private Integer categoryID;
	
	@ApiModelProperty(required = false, example="")
	@JsonProperty("bodySizeid")
	private Integer bodySizeID;
	
	@ApiModelProperty(required = false, example="")
	@JsonProperty("spotname")
	private String spotName;
	
	@ApiModelProperty(required = false, example="30")
	@JsonProperty("price1")
	private Integer price1;
	
	@ApiModelProperty(required = false, example="30")
	@JsonProperty("price2")
	private Integer price2;
	
	@ApiModelProperty(required = false, example="30")
	@JsonProperty("price3")
	private Integer price3;
	
	@ApiModelProperty(required = false, example="30")
	@JsonProperty("price4")
	private Integer price4;
	
	@ApiModelProperty(required = false, example="30")
	@JsonProperty("price5")
	private Integer price5;
	
	@ApiModelProperty(required = false, example="30")
	@JsonProperty("price6")
	private Integer price6;
	
	@ApiModelProperty(required = false, example="30")
	@JsonProperty("price7")
	private Integer price7;
	
	@ApiModelProperty(required = false, example="true")
	@JsonProperty("active")
	private Boolean active;
	
	@ApiModelProperty(required = false, example="true")
	@JsonProperty("includevendorcategory")
	private Boolean includeVendorCategory;
	
	@ApiModelProperty(required = false, example="10")
	@JsonProperty("spotinstancecount")
	private Integer spotInstanceCount;
	
	@ApiModelProperty(required = false, example="0")
	@JsonProperty("bideffectiveon")
	private String bidEffectiveOn;
	
	@ApiModelProperty(required = false, example="3")
	@JsonProperty("maxpurchasable")
	private Integer maxPurchasable;
	
	@ApiModelProperty(required = false, example="2018-05-01")
	@JsonProperty("spotitemcount")
	private Integer spotItemCount;

	public Integer getSpotID() {
		return spotID;
	}

	public void setSpotID(Integer spotID) {
		this.spotID = spotID;
	}

	public Integer getPageID() {
		return pageID;
	}

	public void setPageID(Integer pageID) {
		this.pageID = pageID;
	}

	public Integer getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(Integer categoryID) {
		this.categoryID = categoryID;
	}

	public Integer getBodySizeID() {
		return bodySizeID;
	}

	public void setBodySizeID(Integer bodySizeID) {
		this.bodySizeID = bodySizeID;
	}

	public String getSpotName() {
		return spotName;
	}

	public void setSpotName(String spotName) {
		this.spotName = spotName;
	}

	public Integer getPrice1() {
		return price1;
	}

	public void setPrice1(Integer price1) {
		this.price1 = price1;
	}

	public Integer getPrice2() {
		return price2;
	}

	public void setPrice2(Integer price2) {
		this.price2 = price2;
	}

	public Integer getPrice3() {
		return price3;
	}

	public void setPrice3(Integer price3) {
		this.price3 = price3;
	}

	public Integer getPrice4() {
		return price4;
	}

	public void setPrice4(Integer price4) {
		this.price4 = price4;
	}

	public Integer getPrice5() {
		return price5;
	}

	public void setPrice5(Integer price5) {
		this.price5 = price5;
	}

	public Integer getPrice6() {
		return price6;
	}

	public void setPrice6(Integer price6) {
		this.price6 = price6;
	}

	public Integer getPrice7() {
		return price7;
	}

	public void setPrice7(Integer price7) {
		this.price7 = price7;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getIncludeVendorCategory() {
		return includeVendorCategory;
	}

	public void setIncludeVendorCategory(Boolean includeVendorCategory) {
		this.includeVendorCategory = includeVendorCategory;
	}

	public Integer getSpotInstanceCount() {
		return spotInstanceCount;
	}

	public void setSpotInstanceCount(Integer spotInstanceCount) {
		this.spotInstanceCount = spotInstanceCount;
	}

	public String getBidEffectiveOn() {
		return bidEffectiveOn;
	}

	public void setBidEffectiveOn(String bidEffectiveOn) {
		this.bidEffectiveOn = bidEffectiveOn;
	}

	public Integer getMaxPurchasable() {
		return maxPurchasable;
	}

	public void setMaxPurchasable(Integer maxPurchasable) {
		this.maxPurchasable = maxPurchasable;
	}

	public Integer getSpotItemCount() {
		return spotItemCount;
	}

	public void setSpotItemCount(Integer spotItemCount) {
		this.spotItemCount = spotItemCount;
	}
}
