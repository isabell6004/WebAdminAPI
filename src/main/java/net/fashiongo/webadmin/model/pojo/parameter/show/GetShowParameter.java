package net.fashiongo.webadmin.model.pojo.parameter.show;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author Sanghyup Kim
 */
public class GetShowParameter {
	@ApiModelProperty(required = false, example = "1")
	@JsonProperty("pageNum")
	private Integer pageNum;

	@ApiModelProperty(required = false, example = "10")
	@JsonProperty("pageSize")
	private Integer pageSize;

	@ApiModelProperty(required = false, example = "1")
	@JsonProperty("showId")
	private Integer showID;

	@ApiModelProperty(required = false, example = "1")
	@JsonProperty("showScheduleId")
	private Integer showScheduleID;

	@ApiModelProperty(required = false, example = "1")
	@JsonProperty("planId")
	private Integer planID;

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

	public Integer getShowID() {
		return showID;
	}

	public void setShowID(Integer showID) {
		this.showID = showID;
	}

	public Integer getShowScheduleID() {
		return showScheduleID;
	}

	public void setShowScheduleID(Integer showScheduleID) {
		this.showScheduleID = showScheduleID;
	}

	public Integer getPlanID() {
		return planID;
	}

	public void setPlanID(Integer planID) {
		this.planID = planID;
	}

}
