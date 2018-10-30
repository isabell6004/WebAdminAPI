/**
 * 
 */
package net.fashiongo.webadmin.model.pojo.parameter.show;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * Set ShowSchedule Parameters
 * 
 * @author Sanghyup Kim
 */
public class SetShowScheduleParameters {
	@ApiModelProperty(required = true, example = "1")
	@JsonProperty("showScheduleId")
	private Integer showScheduleId;

	@ApiModelProperty(required = true, example = "1")
	@JsonProperty("showId")
	private Integer showId;

/*	
    @ApiModelProperty(required = true, example = "")
	@JsonProperty("dateFrom")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime dateFrom;
*/
	@ApiModelProperty(required = true, example = "10/28/2010")
	@JsonProperty("dateFrom")
	private String dateFrom;
/*
	@ApiModelProperty(required = true, example = "")
	@JsonProperty("dateTo")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime dateTo;
*/
    @ApiModelProperty(required = true, example = "10/28/2018")
	@JsonProperty("dateTo")	
	private String dateTo;
	
	@ApiModelProperty(required = true, example = "false")
	@JsonProperty("active")
	private Boolean active;

	@ApiModelProperty(required = true, example = "0")
	@JsonProperty("listOrder")
	private Integer listOrder;

	@ApiModelProperty(required = true, example = "")
	@JsonProperty("bannerImage")
	private String bannerImage;

	@ApiModelProperty(required = true, example = "")
	@JsonProperty("titleImage")
	private String titleImage;

	@ApiModelProperty(required = true, example = "")
	@JsonProperty("mobileImage")
	private String mobileImage;

	public Integer getShowScheduleId() {
		return showScheduleId;
	}

	public void setShowScheduleId(Integer showScheduleId) {
		this.showScheduleId = showScheduleId;
	}

	public Integer getShowId() {
		return showId;
	}

	public void setShowId(Integer showId) {
		this.showId = showId;
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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Integer getListOrder() {
		return listOrder;
	}

	public void setListOrder(Integer listOrder) {
		this.listOrder = listOrder;
	}

	public String getBannerImage() {
		return bannerImage;
	}

	public void setBannerImage(String bannerImage) {
		this.bannerImage = bannerImage;
	}

	public String getTitleImage() {
		return titleImage;
	}

	public void setTitleImage(String titleImage) {
		this.titleImage = titleImage;
	}

	public String getMobileImage() {
		return mobileImage;
	}

	public void setMobileImage(String mobileImage) {
		this.mobileImage = mobileImage;
	}

}
