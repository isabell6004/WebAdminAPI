package net.fashiongo.webadmin.model.pojo.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.pojo.SecurityGroupPermissions;

public class GetSecurityGroupPermissionsResponse {
	@JsonProperty("Table")
	private List<SecurityGroupPermissions> securityGroupsPermissions;

	public List<SecurityGroupPermissions> getSecurityGroupsPermissions() {
		return securityGroupsPermissions;
	}

	public void setSecurityGroupsPermissions(List<SecurityGroupPermissions> securityGroupsPermissions) {
		this.securityGroupsPermissions = securityGroupsPermissions;
	}
	
}
