package net.fashiongo.webadmin.model.pojo.admin;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Reo
 *
 */
public class SecurityUserManager {
	@JsonProperty("data")
	private List<SecurityUserData> securityUserDataList;
	@JsonProperty("updata")
	private List<SecurityUserPermission> securityUserPermissionList;
	public List<SecurityUserData> getSecurityUserDataList() {
		return securityUserDataList;
	}
	public void setSecurityUserDataList(List<SecurityUserData> securityUserDataList) {
		this.securityUserDataList = securityUserDataList;
	}
	public List<SecurityUserPermission> getSecurityUserPermissionList() {
		return securityUserPermissionList;
	}
	public void setSecurityUserPermissionList(List<SecurityUserPermission> securityUserPermissionList) {
		this.securityUserPermissionList = securityUserPermissionList;
	}
	
	

}
