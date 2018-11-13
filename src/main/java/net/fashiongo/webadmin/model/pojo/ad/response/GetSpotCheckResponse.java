package net.fashiongo.webadmin.model.pojo.ad.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Nayeon Kim
 */
public class GetSpotCheckResponse {
	@JsonProperty("SpotID")
	private Integer spotID;

	public Integer getSpotID() {
		return spotID;
	}

	public void setSpotID(Integer spotID) {
		this.spotID = spotID;
	}
}
