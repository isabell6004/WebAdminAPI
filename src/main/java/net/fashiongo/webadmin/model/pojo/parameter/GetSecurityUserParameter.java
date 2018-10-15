package net.fashiongo.webadmin.model.pojo.parameter;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetSecurityUserParameter {
	@JsonProperty("userid")
	private Integer userID;
	@JsonProperty("username")
	private String userName;
	@JsonProperty("group")
	private String group;
	@JsonProperty("role")
	private String role;
	@JsonProperty("vendorid")
	private Integer vendorID;
	@JsonProperty("active")
	private Boolean active;
	
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Integer getVendorID() {
		return vendorID;
	}
	public void setVendorID(Integer vendorID) {
		this.vendorID = vendorID;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	
}
