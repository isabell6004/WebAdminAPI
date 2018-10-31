/**
 * 
 */
package net.fashiongo.webadmin.model.pojo.parameter.show;

import java.time.LocalDateTime;

import javax.persistence.Convert;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import net.fashiongo.common.conversion.LocalDateTimeConverter;

/**
 * Set ShowList Parameters
 * 
 * @author Sanghyup Kim
 */
public class SetShowInfoParameters {
	@ApiModelProperty(required = true, example = "21")
	@JsonProperty("showid")
	private Integer showId;

	@ApiModelProperty(required = true, example = "0")
	@JsonProperty("showscheduleid")
	private Integer showScheduleId;

	@ApiModelProperty(required = true, example = "0")
	@JsonProperty("listorder")
	private Integer listOrder;

	@ApiModelProperty(required = true, example = "")
	@JsonProperty("showname")
	private String showName;

	@ApiModelProperty(required = true, example = "")
	@JsonProperty("location")
	private String location;

	@ApiModelProperty(required = true, example = "")
	@JsonProperty("showurl")
	private String showUrl;

	@ApiModelProperty(required = true, example = "")
	@JsonProperty("datefrom")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime dateFrom;

	@ApiModelProperty(required = true, example = "")
	@JsonProperty("dateto")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime dateTo;

	@ApiModelProperty(required = true, example = "false")
	@JsonProperty("active")
	private Boolean active;

	@ApiModelProperty(required = true, example = "show")
	@JsonProperty("stype")
	private String sType;

	@ApiModelProperty(required = true, example = "A")
	@JsonProperty("crud")
	private String crud;

	public Integer getShowId() {
		return showId;
	}

	public void setShowd(Integer showId) {
		this.showId = showId;
	}

	public Integer getShowScheduleId() {
		return showScheduleId;
	}

	public void setShowScheduleId(Integer showScheduleId) {
		this.showScheduleId = showScheduleId;
	}

	public Integer getListOrder() {
		return listOrder;
	}

	public void setListOrder(Integer listOrder) {
		this.listOrder = listOrder;
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

	public String getShowUrl() {
		return showUrl;
	}

	public void setShowUrl(String showUrl) {
		this.showUrl = showUrl;
	}

	public LocalDateTime getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(LocalDateTime dateFrom) {
		this.dateFrom = dateFrom;
	}

	public LocalDateTime getDateTo() {
		return dateTo;
	}

	public void setDateTo(LocalDateTime dateTo) {
		this.dateTo = dateTo;
	}

	public Boolean getActive() {
//		return active;
		return (active == null ? false : active);
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getSType() {
		return sType;
	}

	public void setSType(String sType) {
		this.sType = sType;
	}

	public String getCrud() {
		return crud;
	}

	public void setCrud(String crud) {
		this.crud = crud;
	}

}
