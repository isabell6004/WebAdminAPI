package net.fashiongo.webadmin.model.pojo.parameter;

import java.time.LocalDateTime;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Reo
 *
 */
public class GetBannerRequestParameter {
	@JsonProperty("pagenum")
	private Integer pageNum;
	
	@JsonProperty("pagesize")
	private Integer pageSize;
	
	@JsonProperty("searchkeyword")
	private String searchKeyword;
	
	@JsonProperty("FromDate")
	private String fromDate;
	
	@JsonProperty("ToDate")
	private String toDate;
	
	@JsonProperty("searchstatus")
	private String searchStatus;
	
	@JsonProperty("searchtype")
	private Integer searchType;
	
	@JsonProperty("additional")
	private Integer additional;
	
	@JsonProperty("orderby")
	private String orderby;

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getSearchKeyword() {
		return StringUtils.isEmpty(searchKeyword) ? null : searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}


	public String getFromDate() {
		return StringUtils.isEmpty(fromDate) ? null : fromDate + " 00:00:00";
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return StringUtils.isEmpty(toDate) ? null : toDate + " 23:59:59";
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getSearchStatus() {
		return searchStatus;
	}

	public void setSearchStatus(String searchStatus) {
		this.searchStatus = searchStatus;
	}

	public Integer getSearchType() {
		return searchType;
	}

	public void setSearchType(Integer searchType) {
		this.searchType = searchType;
	}

	public Integer getAdditional() {
		return additional;
	}

	public void setAdditional(Integer additional) {
		this.additional = additional;
	}

	public String getOrderby() {
		return StringUtils.isEmpty(orderby) ? null : orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}
	
	
}
