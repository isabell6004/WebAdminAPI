package net.fashiongo.webadmin.model.pojo.sitemgmt.parameter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
/**
 * 
 * @author DAHYE
 *
 */
public class SetCommunicationReasonActiveParameter {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(required = false, example="45")
	@JsonProperty("reasonid")
	private Integer reasonID;
	
	@ApiModelProperty(required = false, example="false")
	@JsonProperty("active")
	private Boolean active;

	public Integer getReasonID() {
		return reasonID == null ? 0 : reasonID;
	}

	public void setReasonID(Integer reasonID) {
		this.reasonID = reasonID;
	}

	public Boolean getActive() {
		return active == null ? false: active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
	
}
