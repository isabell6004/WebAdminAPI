package net.fashiongo.webadmin.model.pojo.parameter;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author Nayeon Kim
 */
public class GetSpotCheckParameter {
	@ApiModelProperty(required = false, example="10")
	@JsonProperty("spotid")
	private Integer spotID;

	public Integer getSpotID() {
		return spotID;
	}

	public void setSpotID(Integer spotID) {
		this.spotID = spotID;
	}
}
