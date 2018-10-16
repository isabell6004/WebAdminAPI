package net.fashiongo.webadmin.model.pojo.parameter;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class GetBidSettingLastWeekParameter {
	@ApiModelProperty(required = false, example="120")
	@JsonProperty("top")
	private Integer top;

	public Integer getTop() {
		return top;
	}

	public void setTop(Integer top) {
		this.top = top;
	}
	
	
}
