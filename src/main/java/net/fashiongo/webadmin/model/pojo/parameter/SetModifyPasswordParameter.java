package net.fashiongo.webadmin.model.pojo.parameter;

import io.swagger.annotations.ApiModelProperty;

public class SetModifyPasswordParameter {
	@ApiModelProperty(required = false, example="Alesha.bax911@gmail.com")
	private String userName;
	@ApiModelProperty(required = false, example="1234")
	private String newPassword;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
}
