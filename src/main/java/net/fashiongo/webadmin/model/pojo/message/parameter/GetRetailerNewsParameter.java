package net.fashiongo.webadmin.model.pojo.message.parameter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.github.rkpunjal.sqlsafe.SQLInjectionSafe;
import net.fashiongo.webadmin.controller.validator.SQLInjectionSafeWithKeywordsFilter;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Pattern;

/**
 * 
 * @author Reo
 *
 */
public class GetRetailerNewsParameter implements Serializable {

	private static final String ALLOW_PATTERN = "^$|[a-zA-Z0-9\\s /:!&,-.?_\']+$";
	private static final String ALLOW_PATTERN_MESSAGE = "Special character not allowed";

	@JsonProperty("pagenum")
	private Integer pageNum;
	
	@JsonProperty("pagesize")
	private Integer pageSize;
	
	@JsonProperty("newstitle")
	@SQLInjectionSafe
	@Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
	private String newsTitle;
	
	@JsonProperty("active")
	@SQLInjectionSafeWithKeywordsFilter
	@Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
	private String active;
	
	@JsonProperty("period")
	@SQLInjectionSafeWithKeywordsFilter
	@Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
	private String period;
	
	@JsonProperty("fromdate")
	private Date fromDate;
	
	@JsonProperty("todate")
	private Date toDate;
	
	@JsonProperty("orderby")
	@SQLInjectionSafeWithKeywordsFilter
	@Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
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


	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

}
