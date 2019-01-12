package net.fashiongo.webadmin.model.pojo.admin.parameter;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import net.fashiongo.webadmin.model.primary.SecurityUser;

public class DelSecurityUserParameter {
	@ApiModelProperty(required = true, example="1")
	@JsonProperty("data")
	private List<Integer> userList;

	public List<Integer> getUserList() {
		return userList;
	}

	public void setUserList(List<Integer> userList) {
		this.userList = userList;
	}


	
}
