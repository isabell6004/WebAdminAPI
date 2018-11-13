package net.fashiongo.webadmin.model.pojo.sitemgmt.parameter;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import net.fashiongo.webadmin.model.primary.Policy;
/**
 * 
 * @author DAHYE
 *
 */
public class SetAddDelPolicyManagementParameter {
	@JsonProperty("obj")
	private Policy policy;
	
	@ApiModelProperty(required = false, example="Upd")
	@JsonProperty("settype")
	private String setType;

	public Policy getPolicy() {
		return policy;
	}

	public String getSetType() {
		return StringUtils.isEmpty(setType) ? null : setType;
	}

	public void setPolicy(Policy policy) {
		this.policy = policy;
	}

	public void setSetType(String setType) {
		this.setType = setType;
	}
	
	
	
}
