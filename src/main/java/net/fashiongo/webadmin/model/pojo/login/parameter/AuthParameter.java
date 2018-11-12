package net.fashiongo.webadmin.model.pojo.login.parameter;

import io.swagger.annotations.ApiModelProperty;

public class AuthParameter {
	@ApiModelProperty(required = false, example="krdev")
	private String username;
	@ApiModelProperty(required = false, example="1234")
	private String pwd;
	@ApiModelProperty(required = false, example="")
	private String accesscode;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getAccesscode() {
		return accesscode;
	}
	public void setAccesscode(String accesscode) {
		this.accesscode = accesscode;
	}
}