package net.fashiongo.webadmin.model.pojo.parameter;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import io.swagger.annotations.ApiModelProperty;

public class SetModifyPasswordParameter implements Serializable {
	@ApiModelProperty(required = false, example="admingo")
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