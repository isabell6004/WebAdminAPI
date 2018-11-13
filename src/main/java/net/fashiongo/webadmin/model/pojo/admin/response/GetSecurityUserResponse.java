package net.fashiongo.webadmin.model.pojo.admin.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.pojo.admin.SecurityUsers;

public class GetSecurityUserResponse {
	@JsonProperty("Table")
	private List<SecurityUsers> securityUserList;

	public List<SecurityUsers> getSecurityUserList() {
		return securityUserList;
	}

	public void setSecurityUserList(List<SecurityUsers> securityUserList) {
		this.securityUserList = securityUserList;
	}

}
