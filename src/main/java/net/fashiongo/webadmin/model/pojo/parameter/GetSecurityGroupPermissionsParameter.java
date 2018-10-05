package net.fashiongo.webadmin.model.pojo.parameter;

import io.swagger.annotations.ApiModelProperty;

public class GetSecurityGroupPermissionsParameter {
	
	@ApiModelProperty(required = true, example="1")
	private Integer appid;
	
	@ApiModelProperty(required = true, example="10")
	private Integer groupid;
	
	public Integer getAppid() {
		return appid;
	}
	public void setAppid(Integer appid) {
		this.appid = appid;
	}
	
	public Integer getGroupid() {
		return groupid;
	}
	public void setGroupid(Integer groupid) {
		this.groupid = groupid;
	}
	
	
}
