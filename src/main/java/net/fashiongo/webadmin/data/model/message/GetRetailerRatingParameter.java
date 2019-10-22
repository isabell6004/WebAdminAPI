package net.fashiongo.webadmin.data.model.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public class GetRetailerRatingParameter {

	@JsonProperty("wholesalerid")
	private Integer wholesalerId;

	@JsonProperty("retailerid")
	private Integer retailerId;

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
		return wholesalerId;
	}

	public Integer getRetailerID() {
		return retailerId;
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
		return StringUtils.isEmpty(active) ? null : active.equals("1");
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
		this.wholesalerId = wholeSalerID;
	}

	public void setRetailerID(Integer retailerID) {
		this.retailerId = retailerID;
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
