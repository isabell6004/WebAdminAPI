package net.fashiongo.webadmin.model.pojo.parameter;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author Nayeon Kim
 */
public class GetSpotCheckParameter {
	@ApiModelProperty(required = false, example="10")
	private Integer spotID;

	public Integer getSpotID() {
		return spotID;
	}

	public void setSpotID(Integer spotID) {
		this.spotID = spotID;
	}
}
