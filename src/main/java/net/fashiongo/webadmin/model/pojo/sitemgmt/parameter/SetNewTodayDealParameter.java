package net.fashiongo.webadmin.model.pojo.sitemgmt.parameter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author Incheol Jung
 */
public class SetNewTodayDealParameter implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonProperty("ProductID")
	@ApiModelProperty(required = false, example="4544308")
	private Integer productID;
	@JsonProperty("FromDate")
	@ApiModelProperty(required = false, example="2018-09-22 00:00:00")
	private String fromDate;
	@JsonProperty("ToDate")
	@ApiModelProperty(required = false, example="2018-09-22 23:59:59")
	private String toDate;
	@JsonProperty("TodayDealPrice")
	@ApiModelProperty(required = false, example="9.34")
	private BigDecimal todayDealPrice;
	
	public Integer getProductID() {
		return productID;
	}
	public void setProductID(Integer productID) {
		this.productID = productID;
	}
	public LocalDateTime getFromDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return StringUtils.isEmpty(this.fromDate) ? null : LocalDateTime.parse(this.fromDate, formatter);
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public LocalDateTime getToDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return StringUtils.isEmpty(this.toDate) ? null : LocalDateTime.parse(this.toDate, formatter);
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public BigDecimal getTodayDealPrice() {
		return todayDealPrice;
	}
	public void setTodayDealPrice(BigDecimal todayDealPrice) {
		this.todayDealPrice = todayDealPrice;
	}
	
}
