package net.fashiongo.webadmin.model.pojo.admin.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.primary.SecurityMenu;

public class GetSecurityParentMenusResponse {
	@JsonProperty("Table")
	private List<SecurityMenu> securityMenu;

	public List<SecurityMenu> getSecurityMenu() {
		return securityMenu;
	}

	public void setSecurityMenu(List<SecurityMenu> securityMenu) {
		this.securityMenu = securityMenu;
	}

}
