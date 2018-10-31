/**
 * 
 */
package net.fashiongo.webadmin.model.pojo.parameter.show;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * Set ShowList Parameters
 * 
 * @author Sanghyup Kim
 */
public class SetShowParameters {
	@ApiModelProperty(required = true, example = "21")
	@JsonProperty("showId")
	private Integer showId;

	@ApiModelProperty(required = true, example = "")
	@JsonProperty("showName")
	private String showName;

	@ApiModelProperty(required = true, example = "")
	@JsonProperty("location")
	private String location;

	@ApiModelProperty(required = true, example = "")
	@JsonProperty("url")
	private String url;

	@ApiModelProperty(required = true, example = "false")
	@JsonProperty("active")
	private Boolean active;

	public Integer getShowId() {
		return showId;
	}

	public void setShowId(Integer showId) {
		this.showId = showId;
	}

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Boolean getActive() {
//		return active;
		return (active == null ? false : active);
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

}
