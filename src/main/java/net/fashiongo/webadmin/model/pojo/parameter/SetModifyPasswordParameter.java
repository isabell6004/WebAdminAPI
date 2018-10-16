package net.fashiongo.webadmin.model.pojo.parameter;

import org.apache.commons.lang3.StringUtils;

import io.swagger.annotations.ApiModelProperty;

public class SetModifyPasswordParameter {
	@ApiModelProperty(required = false, example="Alesha.bax911@gmail.com")
	private String userName;
	@ApiModelProperty(required = false, example="1234")
	private String newPassword;
	public String getUserName() {
		return StringUtils.isEmpty(userName) ? "-1" : userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getNewPassword() {
		return StringUtils.isEmpty(newPassword) ? "-2" : newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
}