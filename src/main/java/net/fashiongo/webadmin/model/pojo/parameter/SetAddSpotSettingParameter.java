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
	private String spotID;
	
	@ApiModelProperty(required = false, example="1")
	private String pageID;
	
	@ApiModelProperty(required = false, example="7")
	private String categoryID;
	
	@ApiModelProperty(required = false, example="")
	private String bodySizeID;
	
	@ApiModelProperty(required = false, example="")
	private String spotName;
	
	@ApiModelProperty(required = false, example="30")
	private String price1;
	
	@ApiModelProperty(required = false, example="30")
	private String price2;
	
	@ApiModelProperty(required = false, example="30")
	private String price3;
	
	@ApiModelProperty(required = false, example="30")
	private String price4;
	
	@ApiModelProperty(required = false, example="30")
	private String price5;
	
	@ApiModelProperty(required = false, example="30")
	private String price6;
	
	@ApiModelProperty(required = false, example="30")
	private String price7;
	
	@ApiModelProperty(required = false, example="true")
	private Boolean active;
	
	@ApiModelProperty(required = false, example="true")
	private Boolean includeVendorCategory;
	
	@ApiModelProperty(required = false, example="10")
	private String spotInstanceCount;
	
	@ApiModelProperty(required = false, example="2018-05-01")
	private String bidEffectiveOn2;
	
	@ApiModelProperty(required = false, example="0")
	private String maxPurchasable;
	
	@ApiModelProperty(required = false, example="3")
	private String spotItemCount;

	public Integer getSpotID() {
		return StringUtils.isEmpty(spotID) ? 0 : Integer.parseInt(spotID);
	}

	public Integer getPageID() {
		return StringUtils.isEmpty(pageID) ? 0 : Integer.parseInt(pageID);
	}

	public Integer getCategoryID() {
		return StringUtils.isEmpty(categoryID) ? null : Integer.parseInt(categoryID);
	}

	public Integer getBodySizeID() {
		return StringUtils.isEmpty(bodySizeID) ? null : Integer.parseInt(bodySizeID);
	}

	public String getSpotName() {
		return StringUtils.isEmpty(spotName) ? "" : spotName;
	}

	public BigDecimal getPrice1() {
		//return BigDecimal.valueOf(price1);
		//return (price1 == null) ? BigDecimal.valueOf(0) : BigDecimal.valueOf(price1);
		BigDecimal bigDecimalprice1 = new BigDecimal(price1);
		return (bigDecimalprice1 == null) ? BigDecimal.valueOf(0) : bigDecimalprice1;
	}

	public BigDecimal getPrice2() {
		//return BigDecimal.valueOf(price2);
		//return (price2 == null) ? BigDecimal.valueOf(0) : BigDecimal.valueOf(price2);
		BigDecimal bigDecimalprice2 = new BigDecimal(price2);
		return (bigDecimalprice2 == null) ? BigDecimal.valueOf(0) : bigDecimalprice2;
	}

	public BigDecimal getPrice3() {
		//return BigDecimal.valueOf(price3);
		//return (price3 == null) ? BigDecimal.valueOf(0) : BigDecimal.valueOf(price3);
		BigDecimal bigDecimalprice3 = new BigDecimal(price3);
		return (bigDecimalprice3 == null) ? BigDecimal.valueOf(0) : bigDecimalprice3;
	}

	public BigDecimal getPrice4() {
		//return BigDecimal.valueOf(price4);
		//return (price4 == null) ? BigDecimal.valueOf(0) : BigDecimal.valueOf(price4);
		BigDecimal bigDecimalprice4 = new BigDecimal(price4);
		return (bigDecimalprice4 == null) ? BigDecimal.valueOf(0) : bigDecimalprice4;
	}

	public BigDecimal getPrice5() {
		//return BigDecimal.valueOf(price5);
		//return (price5 == null) ? BigDecimal.valueOf(0) : BigDecimal.valueOf(price5);
		BigDecimal bigDecimalprice5 = new BigDecimal(price5);
		return (bigDecimalprice5 == null) ? BigDecimal.valueOf(0) : bigDecimalprice5;
	}

	public BigDecimal getPrice6() {
		//return BigDecimal.valueOf(price6);
		//return (price6 == null) ? BigDecimal.valueOf(0) : BigDecimal.valueOf(price6);
		BigDecimal bigDecimalprice6 = new BigDecimal(price6);
		return (bigDecimalprice6 == null) ? BigDecimal.valueOf(0) : bigDecimalprice6;
	}

	public BigDecimal getPrice7() {
		//return BigDecimal.valueOf(price7);
		//return (price7 == null) ? BigDecimal.valueOf(0) : BigDecimal.valueOf(price7);
		BigDecimal bigDecimalprice7 = new BigDecimal(price7);
		return (bigDecimalprice7 == null) ? BigDecimal.valueOf(0) : bigDecimalprice7;
	}

	public Boolean getActive() {
		return active == null ? false : active;
	}

	public Boolean getIncludeVendorCategory() {
		return includeVendorCategory == null ? false : active;
	}

	public Integer getSpotInstanceCount() {
		return StringUtils.isEmpty(spotInstanceCount) ? 0 : Integer.parseInt(spotInstanceCount);
	}

	public String getBidEffectiveOn2() {
		return StringUtils.isEmpty(bidEffectiveOn2) ? "" : bidEffectiveOn2;
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
		return StringUtils.isEmpty(maxPurchasable) ? 0 : Integer.parseInt(maxPurchasable);
	}

	public Integer getSpotItemCount() {
		return StringUtils.isEmpty(spotItemCount) ? 0 : Integer.parseInt(spotItemCount);
	}
}
