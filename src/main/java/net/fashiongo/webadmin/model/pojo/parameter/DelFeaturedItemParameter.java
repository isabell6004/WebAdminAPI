package net.fashiongo.webadmin.model.pojo.parameter;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
* @author Sanghyup Kim
*/
public class DelFeaturedItemParameter {
	@ApiModelProperty(required = false, example = "1")
	@JsonProperty("FeaturedItemID")
	private Integer featuredItemID;

	public Integer getFeaturedItemID() {
		return featuredItemID;
	}

	public void setFeaturedItemID(Integer featuredItemID) {
		this.featuredItemID = featuredItemID;
	}

}
