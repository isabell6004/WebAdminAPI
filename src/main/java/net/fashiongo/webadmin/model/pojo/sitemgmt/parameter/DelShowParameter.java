package net.fashiongo.webadmin.model.pojo.sitemgmt.parameter;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author Sanghyup Kim
 */
public class DelShowParameter {
	@ApiModelProperty(required = false, example = "1")
	@JsonProperty("showId")
	private Integer showID;

	@ApiModelProperty(required = false, example = "1")
	@JsonProperty("showScheduleId")
	private Integer showScheduleID;

	@ApiModelProperty(required = false, example = "1")
	@JsonProperty("planId")
	private Integer planId;

	@ApiModelProperty(required = false, example = "1")
	@JsonProperty("mapId")
	private Integer mapId;

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

	public Integer getPlanId() {
		return planId;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
	}

	public Integer getMapId() {
		return mapId;
	}

	public void setMapId(Integer mapId) {
		this.mapId = mapId;
	}

}
