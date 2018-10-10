package net.fashiongo.webadmin.model.pojo.parameter;

import io.swagger.annotations.ApiModelProperty;

public class GetSecurityUserParameter {
    @ApiModelProperty(required = false, example="Vicky")
	private String userName;
    @ApiModelProperty(required = false, example="Call Center")
	private String group;
    @ApiModelProperty(required = false, example="S")
	private String role;
    @ApiModelProperty(required = false, example="2858")
	private Integer vendorID;
	
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
	
}
