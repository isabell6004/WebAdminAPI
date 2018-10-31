/**
 * 
 */
package net.fashiongo.webadmin.model.pojo.parameter.show;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * Set GetShowScheduleList Parameters
 * 
 * @author Sanghyup Kim
 */
public class GetShowScheduleListParameters {
	@ApiModelProperty(required = true, example = "1")
	@JsonProperty("showId")
	private Integer showId;

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
/*
	@ApiModelProperty(required = true, example = "")
	@JsonProperty("datefrom")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime dateFrom;
*/
	@ApiModelProperty(required = true, example = "10/1/2018 00:00")
	@JsonProperty("datefrom")
	private String dateFrom;
	
/*	
	@ApiModelProperty(required = true, example = "")
	@JsonProperty("dateto")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime dateTo;
*/
	@ApiModelProperty(required = true, example = "10/28/2018 00:00")
	@JsonProperty("dateto")
	private String dateTo;

	public Integer getShowId() {
		return showId;
	}

	public void setShowId(Integer showId) {
		this.showId = showId;
	}

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
//		return showName;
		return (StringUtils.isEmpty(showName) ? null : showName);
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	public String getLocation() {
//		return location;
		return (StringUtils.isEmpty(location) ? null : location);
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

	public String getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public String getDateTo() {
		return dateTo;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}

}
