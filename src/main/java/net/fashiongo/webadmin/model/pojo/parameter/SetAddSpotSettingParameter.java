package net.fashiongo.webadmin.model.pojo.parameter;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author Nayeon Kim
 */
public class SetAddSpotSettingParameter {
	@ApiModelProperty(required = false, example="82")
	private Integer spotID;
	
	@ApiModelProperty(required = false, example="1")
	private Integer pageID;
	
	@ApiModelProperty(required = false, example="7")
	private Integer categoryID;
	
	@ApiModelProperty(required = false, example="")
	private Integer bodySizeID;
	
	@ApiModelProperty(required = false, example="")
	private String spotName;
	
	@ApiModelProperty(required = false, example="30")
	private Integer price1;
	
	@ApiModelProperty(required = false, example="30")
	private Integer price2;
	
	@ApiModelProperty(required = false, example="30")
	private Integer price3;
	
	@ApiModelProperty(required = false, example="30")
	private Integer price4;
	
	@ApiModelProperty(required = false, example="30")
	private Integer price5;
	
	@ApiModelProperty(required = false, example="30")
	private Integer price6;
	
	@ApiModelProperty(required = false, example="30")
	private Integer price7;
	
	@ApiModelProperty(required = false, example="true")
	private Boolean active;
	
	@ApiModelProperty(required = false, example="true")
	private Boolean includeVendorCategory;
	
	@ApiModelProperty(required = false, example="10")
	private Integer spotInstanceCount;
	
	@ApiModelProperty(required = false, example="2018-05-01")
	private String bidEffectiveOn2;
	
	@ApiModelProperty(required = false, example="0")
	private Integer maxPurchasable;
	
	@ApiModelProperty(required = false, example="3")
	private Integer spotItemCount;

	public Integer getSpotID() {
		return spotID == null ? 0 : spotID;
	}

	public void setSpotID(Integer spotID) {
		this.spotID = spotID;
	}

	public Integer getPageID() {
		return pageID == null ? 0 : pageID;
	}

	public void setPageID(Integer pageID) {
		this.pageID = pageID;
	}

	public Integer getCategoryID() {
		return categoryID == null ? null : categoryID;
	}

	public void setCategoryID(Integer categoryID) {
		this.categoryID = categoryID;
	}

	public Integer getBodySizeID() {
		return bodySizeID == null ? null : bodySizeID;
	}

	public void setBodySizeID(Integer bodySizeID) {
		this.bodySizeID = bodySizeID;
	}

	public String getSpotName() {
		return StringUtils.isEmpty(spotName) ? "" : spotName;
	}

	public void setSpotName(String spotName) {
		this.spotName = spotName;
	}

	public BigDecimal getPrice1() {
		//return BigDecimal.valueOf(price1);
		return (price1 == null) ? BigDecimal.valueOf(0) : BigDecimal.valueOf(price1);
	}

	public BigDecimal getPrice2() {
		//return BigDecimal.valueOf(price2);
		return (price2 == null) ? BigDecimal.valueOf(0) : BigDecimal.valueOf(price2);
	}

	public BigDecimal getPrice3() {
		//return BigDecimal.valueOf(price3);
		return (price3 == null) ? BigDecimal.valueOf(0) : BigDecimal.valueOf(price3);
	}

	public BigDecimal getPrice4() {
		//return BigDecimal.valueOf(price4);
		return (price4 == null) ? BigDecimal.valueOf(0) : BigDecimal.valueOf(price4);
	}

	public BigDecimal getPrice5() {
		//return BigDecimal.valueOf(price5);
		return (price5 == null) ? BigDecimal.valueOf(0) : BigDecimal.valueOf(price5);
	}

	public BigDecimal getPrice6() {
		//return BigDecimal.valueOf(price6);
		return (price6 == null) ? BigDecimal.valueOf(0) : BigDecimal.valueOf(price6);
	}

	public BigDecimal getPrice7() {
		//return BigDecimal.valueOf(price7);
		return (price7 == null) ? BigDecimal.valueOf(0) : BigDecimal.valueOf(price7);
	}

	public Boolean getActive() {
		return active == null ? false : active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getIncludeVendorCategory() {
		return includeVendorCategory == null ? false : active;
	}

	public void setIncludeVendorCategory(Boolean includeVendorCategory) {
		this.includeVendorCategory = includeVendorCategory;
	}

	public Integer getSpotInstanceCount() {
		return spotInstanceCount == null ? 0 : spotInstanceCount;
	}

	public void setSpotInstanceCount(Integer spotInstanceCount) {
		this.spotInstanceCount = spotInstanceCount;
	}

	public String getBidEffectiveOn2() {
		return StringUtils.isEmpty(bidEffectiveOn2) ? "" : bidEffectiveOn2;
	}

	public void setBidEffectiveOn2(String bidEffectiveOn2) {
		this.bidEffectiveOn2 = bidEffectiveOn2;
	}
	
	@JsonIgnore
	public Date getBidEffectiveOn() {
		Date bidEffectiveOn = new Date();
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			bidEffectiveOn = dt.parse(bidEffectiveOn2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return bidEffectiveOn;
	}

	public Integer getMaxPurchasable() {
		return maxPurchasable == null ? 0 : maxPurchasable;
	}

	public void setMaxPurchasable(Integer maxPurchasable) {
		this.maxPurchasable = maxPurchasable;
	}

	public Integer getSpotItemCount() {
		return spotItemCount == null ? 0 : spotItemCount;
	}

	public void setSpotItemCount(Integer spotItemCount) {
		this.spotItemCount = spotItemCount;
	}
}
