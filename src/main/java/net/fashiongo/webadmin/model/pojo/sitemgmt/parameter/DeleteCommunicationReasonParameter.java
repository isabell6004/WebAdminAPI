package net.fashiongo.webadmin.model.pojo.sitemgmt.parameter;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
/**
 * 
 * @author DAHYE
 *
 */
public class DeleteCommunicationReasonParameter {
	@ApiModelProperty(required = false, example="45")
	@JsonProperty("reasonids")
	private String reasonIDs;

	public String getReasonIDs() {
		return StringUtils.isEmpty(reasonIDs) ? "" : reasonIDs;
	}

	public void setReasonIDs(String reasonIDs) {
		this.reasonIDs = reasonIDs;
	}
	
	
}
