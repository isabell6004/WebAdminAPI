package net.fashiongo.webadmin.model.pojo.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.pojo.SecurityMenus2;

public class GetSecurityMenus2Response {
	@JsonProperty("Table")
	private List<SecurityMenus2> securityMenus2;

	public List<SecurityMenus2> getSecurityMenu() {
		return securityMenus2;
	}

	public void setSecurityMenu(List<SecurityMenus2> securityMenus2) {
		this.securityMenus2 = securityMenus2;
	}

}
