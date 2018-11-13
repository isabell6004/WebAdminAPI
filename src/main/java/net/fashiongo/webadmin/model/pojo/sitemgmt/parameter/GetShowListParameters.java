/**
 * 
 */
package net.fashiongo.webadmin.model.pojo.sitemgmt.parameter;

import java.time.LocalDateTime;

import javax.persistence.Convert;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import net.fashiongo.common.conversion.LocalDateTimeConverter;

/**
 * Set ShowList Parameters
 * 
 * @author Sanghyup Kim
 */
public class GetShowListParameters {
	@ApiModelProperty(required = true, example = "1")
	@JsonProperty("pagenum")
	private Integer pageNum;

	@ApiModelProperty(required = true, example = "10")
	@JsonProperty("pagesize")
	private Integer pageSize;

	@ApiModelProperty(required = true, example = "")
	@JsonProperty("showname")
	private String showName;

	@ApiModelProperty(required = true, example = "")
	@JsonProperty("location")
	private String location;

	@ApiModelProperty(required = true, example = "1")
	@JsonProperty("active")
	private Integer active;

	@ApiModelProperty(required = true, example = "")
	@JsonProperty("orderby")
	private String orderBy;

	@ApiModelProperty(required = true, example = "")
	@JsonProperty("fromdate")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime fromDate;

	@ApiModelProperty(required = true, example = "")
	@JsonProperty("todate")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime toDate;

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

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public String getOrderBy() {
//		return orderBy;
		return (StringUtils.isEmpty(orderBy) ? null : orderBy);
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
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

}
