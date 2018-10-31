package net.fashiongo.webadmin.model.pojo.parameter;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Reo
 *
 */
public class GetRetailerNewsParameter implements Serializable {
	@JsonProperty("pagenum")
	private Integer pageNum;
	
	@JsonProperty("pagesize")
	private Integer pageSize;
	
	@JsonProperty("newstitle")
	private String newsTitle;
	
	@JsonProperty("active")
	private String active;
	
	@JsonProperty("period")
	private String period;
	
	@JsonProperty("fromdate")
	private LocalDateTime fromDate;
	
	@JsonProperty("todate")
	private LocalDateTime toDate;
	
	@JsonProperty("orderby")
	private String orderBy;

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

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public LocalDateTime getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDateTime fromDate) {
		this.fromDate = fromDate;
	}

	public LocalDateTime getToDate() {
		return toDate;
	}

	public void setToDate(LocalDateTime toDate) {
		this.toDate = toDate;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	
}
