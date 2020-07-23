package net.fashiongo.webadmin.model.pojo.message.parameter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.github.rkpunjal.sqlsafe.SQLInjectionSafe;
import net.fashiongo.webadmin.controller.validator.SQLInjectionSafeWithKeywordsFilter;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Pattern;

/**
 * 
 * @author DAHYE
 *
 */
@SuppressWarnings("serial")
public class GetVendorNewsParameter implements Serializable {

	private static final String ALLOW_PATTERN = "^$|[a-zA-Z0-9\\s /:!&,-.?_+\']+$";
	private static final String ALLOW_PATTERN_MESSAGE = "Special character not allowed";

	@ApiModelProperty(required = false, example = "1")
	@JsonProperty("pagenum")
	private Integer pageNum;

	@ApiModelProperty(required = false, example = "10")
	@JsonProperty("pagesize")
	private Integer pageSize;
	
	@ApiModelProperty(required = false, example = "FashionGo")
	@JsonProperty("vendor")
	@SQLInjectionSafe
	@Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
	private String vendor;
	
	@ApiModelProperty(required = false, example = "")
	@JsonProperty("newstitle")
	@SQLInjectionSafe
	@Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
	private String newsTitle;
	
	@ApiModelProperty(required = false, example = "1")
	@JsonProperty("active")
	@SQLInjectionSafeWithKeywordsFilter
	@Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
	private String active;

	@ApiModelProperty(required = false, example = "")
	@JsonProperty("orderby")
	@SQLInjectionSafeWithKeywordsFilter
	@Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
	private String orderBy;
	
	@ApiModelProperty(required = false, example = "false")
	@JsonProperty("isdropoffnotice")
	private Boolean dropOffNotice;
	
	@ApiModelProperty(required = false, example = "")
	@JsonProperty("period")
	@SQLInjectionSafeWithKeywordsFilter
	@Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
	private String period;
	
	@ApiModelProperty(required = false, example = "10/1/2018 00:00:00")
	@JsonProperty("fromdate")
	@SQLInjectionSafeWithKeywordsFilter
	@Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
	private String fromDate;
	
	@ApiModelProperty(required = false, example = "10/28/2018 23:59:59")
	@JsonProperty("todate")
	@SQLInjectionSafeWithKeywordsFilter
	@Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
	private String toDate;

	public Integer getPageNum() {
		return pageNum == null ? 0 : pageNum;
	}

	public Integer getPageSize() {
		return pageSize == null ? 0: pageSize;
	}

	public String getVendor() {
		return StringUtils.isEmpty(vendor) ? null : vendor.replace("'", "''");

	}

	public String getNewsTitle() {
		return StringUtils.isEmpty(newsTitle) ? null : newsTitle.replace("'", "''");
	}

	public String getActive() {
		return StringUtils.isEmpty(active) ? null : active;
	}

	public String getOrderBy() {
		return StringUtils.isEmpty(orderBy) ? null : orderBy;
	}

	public Boolean getDropOffNotice() {
		return dropOffNotice == null ? false : dropOffNotice;
	}

	public String getPeriod() {
		return StringUtils.isEmpty(period) ? null : period;
	}

	public LocalDateTime getFromDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy HH:mm:ss");
		return StringUtils.isEmpty(fromDate) ? null : LocalDateTime.parse(fromDate, formatter);
	}

	public LocalDateTime getToDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy HH:mm:ss");
		return StringUtils.isEmpty(toDate) ? null : LocalDateTime.parse(toDate, formatter);
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public void setDropOffNotice(Boolean dropOffNotice) {
		this.dropOffNotice = dropOffNotice;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}


}
