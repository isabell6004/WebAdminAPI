package net.fashiongo.webadmin.model.pojo.message.parameter;

import java.time.LocalDateTime;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * 
 * @author DAHYE
 *
 */
public class GetVendorRatingParameter {
	@JsonProperty("WholeSalerID")
	private Integer wholeSalerID;
	
	@JsonProperty("RetailerID")
	private Integer retailerID;
	
	@JsonProperty("pagenum")
	private Integer pageNum;
	
	@JsonProperty("pagesize")
	private Integer pageSize;
	
	@JsonProperty("from")
	private LocalDateTime fromDate;
	
	@JsonProperty("to")
	private LocalDateTime toDate;

	@JsonProperty("active")
	private Boolean active;
	
	@JsonProperty("additional")
	private String additional;
	
	@JsonProperty("type")
	private String type;
	
	@JsonProperty("orderby")
	private String orderby;

	public Integer getWholeSalerID() {
		return wholeSalerID;
	}

	public Integer getRetailerID() {
		return retailerID;
	}

	public Integer getPageNum() {
		return pageNum == null ? 0 : pageNum;
	}

	public Integer getPageSize() {
		return pageSize == null ? 0 : pageSize;
	}

	public LocalDateTime getFromDate() {
		return fromDate;
	}

	public LocalDateTime getToDate() {
		return toDate;
	}

	public Boolean getActive() {
		return active == null ? true : active;
	}

	public String getAdditional() {
		return StringUtils.isEmpty(additional) ? "1=1" : additional;
	}

	public String getType() {
		return type;
	}

	public String getOrderby() {
		return StringUtils.isEmpty(orderby) ? null : orderby;
	}

	public void setWholeSalerID(Integer wholeSalerID) {
		this.wholeSalerID = wholeSalerID;
	}

	public void setRetailerID(Integer retailerID) {
		this.retailerID = retailerID;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public void setFromDate(LocalDateTime fromDate) {
		this.fromDate = fromDate;
	}

	public void setToDate(LocalDateTime toDate) {
		this.toDate = toDate;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public void setAdditional(String additional) {
		this.additional = additional;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}
	
}
