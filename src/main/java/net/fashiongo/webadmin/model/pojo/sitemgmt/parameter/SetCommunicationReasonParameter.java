package net.fashiongo.webadmin.model.pojo.sitemgmt.parameter;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author DAHYE
 *
 */
public class SetCommunicationReasonParameter extends SetCommunicationReasonActiveParameter {
	@ApiModelProperty(required = false, example="41")
	@JsonProperty("parentid")
	private String parentID;
	
	@ApiModelProperty(required = false, example="test")
	@JsonProperty("reason")
	private String reason;

	public Integer getParentID() {
		return StringUtils.isEmpty(parentID) ? 0 : Integer.parseInt(parentID);
	}

	public void setParentID(String parentID) {
		this.parentID = parentID;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
}
