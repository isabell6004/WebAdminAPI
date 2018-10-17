package net.fashiongo.webadmin.model.pojo.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.pojo.SecurityUsers;
import net.fashiongo.webadmin.model.pojo.UserMappingVendorResult;

public class SetUserMappingVendorResponse {
	@JsonProperty("Table")
	private List<UserMappingVendorResult> userMappingVendorResultList;
	private boolean success;

	public List<UserMappingVendorResult> getUserMappingVendorResultList() {
		return userMappingVendorResultList;
	}

	public void setUserMappingVendorResultList(List<UserMappingVendorResult> userMappingVendorResultList) {
		this.userMappingVendorResultList = userMappingVendorResultList;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
}
