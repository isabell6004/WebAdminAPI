package net.fashiongo.webadmin.model.pojo.common.parameter;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class GetBidAdPageSpotsParameter {
	@ApiModelProperty(required = false, example="1")
	@JsonProperty("pageid")
	private Integer pageId;

	public Integer getPageId() {
		return pageId;
	}

	public void setPageId(Integer pageId) {
		this.pageId = pageId;
	}
	
	
}
