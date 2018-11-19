package net.fashiongo.webadmin.model.pojo.vendor.parameter;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author DAHYE
 *
 */
public class SetBuyerRatingActiveParameter implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(required = false, example="3755")
	@JsonProperty("RetailerRatingID")
	private Integer wid;
	
	@ApiModelProperty(required = false, example="1")
	@JsonProperty("active")
	private Boolean active;

	public Integer getWid() {
		return wid == null ? 0 : wid;
	}

	public Boolean getActive() {
		return active == null ? true : active;
	}

	public void setWid(Integer wid) {
		this.wid = wid;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
}