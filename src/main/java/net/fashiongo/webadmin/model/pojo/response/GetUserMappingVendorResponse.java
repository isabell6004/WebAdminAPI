package net.fashiongo.webadmin.model.pojo.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.pojo.UserMappingVendor;
import net.fashiongo.webadmin.model.pojo.UserMappingVendorAssigned;

public class GetUserMappingVendorResponse {
	@JsonProperty("Table")
	private List<UserMappingVendor> userMappingVendorList;
	
	@JsonProperty("Table1")
	private List<UserMappingVendorAssigned> userMappingVendorAssigned;

	public List<UserMappingVendor> getUserMappingVendorList() {
		return userMappingVendorList;
	}

	public void setUserMappingVendorList(List<UserMappingVendor> userMappingVendorList) {
		this.userMappingVendorList = userMappingVendorList;
	}

	public List<UserMappingVendorAssigned> getUserMappingVendorAssigned() {
		return userMappingVendorAssigned;
	}

	public void setUserMappingVendorAssigned(List<UserMappingVendorAssigned> userMappingVendorAssigned) {
		this.userMappingVendorAssigned = userMappingVendorAssigned;
	}
	
	
}
