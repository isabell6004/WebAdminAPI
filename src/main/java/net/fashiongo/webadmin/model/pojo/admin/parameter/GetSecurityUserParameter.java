package net.fashiongo.webadmin.model.pojo.admin.parameter;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public class GetSecurityUserParameter {
	@JsonProperty("userid")
	private Integer userID;
	@ApiModelProperty(required = false, example="Vicky")
	@JsonProperty("username")
	private String userName;
	@ApiModelProperty(required = false, example="Call Center")
	@JsonProperty("group")
	private String group;
	@ApiModelProperty(required = false, example="S")
	@JsonProperty("role")
	private String role;
	@ApiModelProperty(required = false, example="2858")
	@JsonProperty("assignedvendor")
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
