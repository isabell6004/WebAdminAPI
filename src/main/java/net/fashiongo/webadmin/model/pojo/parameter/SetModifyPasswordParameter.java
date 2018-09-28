package net.fashiongo.webadmin.model.pojo.parameter;

import io.swagger.annotations.ApiModelProperty;

public class SetModifyPasswordParameter {
	@ApiModelProperty(required = false, example="Alesha.bax911@gmail.com")
	private String userId;
	@ApiModelProperty(required = false, example="1234")
	private String newPwd;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getNewPwd() {
		return newPwd;
	}
	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
	
}
