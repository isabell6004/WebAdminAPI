package net.fashiongo.webadmin.model.pojo.sitemgmt.parameter;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author Nayeon Kim
 */
public class GetProductDetailParameter {
	@ApiModelProperty(required = false, example="9213809")
	@JsonProperty("ProductId")
	private String productID;
	
	@ApiModelProperty(required = false, example="0")
	private String trendReportID;
	
	public Integer getProductID() {
		return StringUtils.isEmpty(productID) ? 0 : Integer.parseInt(productID);
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public Integer getTrendReportID() {
		return StringUtils.isEmpty(trendReportID) ? 0 : Integer.parseInt(trendReportID);
	}
	public void setTrendReportID(String trendReportID) {
		this.trendReportID = trendReportID;
	}
}
