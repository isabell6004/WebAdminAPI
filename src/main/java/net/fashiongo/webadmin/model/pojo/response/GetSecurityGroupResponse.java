package net.fashiongo.webadmin.model.pojo.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.primary.SecurityGroup;

public class GetSecurityGroupResponse {

	@JsonProperty("data")
	private List<SecurityGroup> securityGroupList;
	private boolean success;
	
	public List<SecurityGroup> getSecurityGroupList() {
		return securityGroupList;
	}
	public void setSecurityGroupList(List<SecurityGroup> securityGroupList) {
		this.securityGroupList = securityGroupList;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
}
