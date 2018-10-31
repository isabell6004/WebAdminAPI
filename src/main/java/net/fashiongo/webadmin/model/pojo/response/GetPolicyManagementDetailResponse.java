package net.fashiongo.webadmin.model.pojo.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.primary.Policy;
/**
 * 
 * @author DAHYE
 *
 */
@SuppressWarnings("serial")
public class GetPolicyManagementDetailResponse implements Serializable {
	@JsonProperty("VPolicyDetailList")
	private Policy policy;

	public Policy getPolicy() {
		return policy;
	}

	public void setPolicy(Policy policy) {
		this.policy = policy;
	}

}
