package net.fashiongo.webadmin.model.pojo.common.parameter;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class GetBidAdSpotCategory {
	@ApiModelProperty(required = false, example="1")
	@JsonProperty("parentCategoryId")
	private Integer parentCategoryId;
	
	@ApiModelProperty(required = false, example="1")
	@JsonProperty("categoryLevel")
	private Integer categoryLevel;

	public Integer getParentCategoryId() {
		return parentCategoryId;
	}

	public void setParentCategoryId(Integer parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}
	
	public Integer getCategoryLevel() {
		return categoryLevel;
	}

	public void setCategoryLevel(Integer categoryLevel) {
		this.categoryLevel = categoryLevel;
	}
	
}
