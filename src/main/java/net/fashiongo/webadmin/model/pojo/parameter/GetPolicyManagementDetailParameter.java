package net.fashiongo.webadmin.model.pojo.parameter;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
/**
 * 
 */
@SuppressWarnings("serial")
public class GetPolicyManagementDetailParameter implements Serializable {
	@ApiModelProperty(required = false, example="6")
	@JsonProperty("policyid")
	private Integer policyID;

	public Integer getPolicyID() {
		return policyID == null ? 0 : policyID;
	}

	public void setPolicyID(Integer policyID) {
		this.policyID = policyID;
	}
}
