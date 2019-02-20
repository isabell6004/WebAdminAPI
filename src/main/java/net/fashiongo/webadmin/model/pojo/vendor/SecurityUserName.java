package net.fashiongo.webadmin.model.pojo.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Reo
 *
 */
public class SecurityUserName {
	@JsonProperty("UserName")
	private String UserName;

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

}
