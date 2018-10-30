package net.fashiongo.webadmin.model.pojo.parameter;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
* @author Sanghyup Kim
*/
public class DelShowParameter {
    @ApiModelProperty(required = false, example="1")
    @JsonProperty("showId")
    private Integer showID;

    @ApiModelProperty(required = false, example="83")
    @JsonProperty("showScheduleId")
    private Integer showScheduleID;

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

    
}
