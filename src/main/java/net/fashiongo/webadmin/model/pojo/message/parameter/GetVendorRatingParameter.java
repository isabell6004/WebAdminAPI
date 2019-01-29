package net.fashiongo.webadmin.model.pojo.message.parameter;

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
	private String fromDate;
	
	@JsonProperty("to")
	private String toDate;

	@JsonProperty("active")
	private String active;
	
	@JsonProperty("additional")
	private String additional;
	
	@JsonProperty("type")
	private Boolean type;
	
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

	public String getFromDate() {
		return StringUtils.isEmpty(fromDate) ? null : fromDate;
	}

	public String getToDate() {
		return StringUtils.isEmpty(toDate) ? null : toDate;
	}

	public Boolean getActive() {
		return StringUtils.isEmpty(active) ? false : active.equals("1");
	}

	public String getAdditional() {
		return StringUtils.isEmpty(additional) ? "1=1" : additional;
	}

	public Boolean getType() {
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

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public void setAdditional(String additional) {
		this.additional = additional;
	}

	public void setType(Boolean type) {
		this.type = type;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}
	
}
