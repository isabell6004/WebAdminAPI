package net.fashiongo.webadmin.model.pojo.parameter;

import java.math.BigDecimal;
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
	
	@ApiModelProperty(required = false, example="2018-05-01")
	@JsonProperty("bideffectiveon")
	private String bidEffectiveOn;
	
	@ApiModelProperty(required = false, example="0")
	@JsonProperty("maxpurchasable")
	private Integer maxPurchasable;
	
	@ApiModelProperty(required = false, example="3")
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

	public BigDecimal getPrice1() {
		return BigDecimal.valueOf(price1);
	}

	public BigDecimal getPrice2() {
		return BigDecimal.valueOf(price2);
	}

	public BigDecimal getPrice3() {
		return BigDecimal.valueOf(price3);
	}

	public BigDecimal getPrice4() {
		return BigDecimal.valueOf(price4);
	}

	public BigDecimal getPrice5() {
		return BigDecimal.valueOf(price5);
	}

	public BigDecimal getPrice6() {
		return BigDecimal.valueOf(price6);
	}

	public BigDecimal getPrice7() {
		return BigDecimal.valueOf(price7);
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
