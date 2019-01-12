package net.fashiongo.webadmin.model.pojo.admin.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.pojo.admin.LoginControl;
import net.fashiongo.webadmin.model.pojo.admin.MapUserGroup;

public class GetSecurityUserGroupAccesstimeResponse {
	@JsonProperty("Table")
	private List<MapUserGroup> mapUserGroupList;
	
	@JsonProperty("Table1")
	private List<LoginControl> loginControlList;
	
	private boolean success;

	public List<MapUserGroup> getMapUserGroupList() {
		return mapUserGroupList;
	}

	public void setMapUserGroupList(List<MapUserGroup> mapUserGroupList) {
		this.mapUserGroupList = mapUserGroupList;
	}

	public List<LoginControl> getLoginControlList() {
		return loginControlList;
	}

	public void setLoginControlList(List<LoginControl> loginControlList) {
		this.loginControlList = loginControlList;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	
}
