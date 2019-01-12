package net.fashiongo.webadmin.model.pojo.admin.parameter;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.pojo.admin.SecurityUserData;
import net.fashiongo.webadmin.model.pojo.admin.SecurityUserPermission;

public class SetSecurityUserParameter {
	@JsonProperty("data")
	private SecurityUserData data;
	@JsonProperty("updata")
	private List<SecurityUserPermission> updata;

	public SecurityUserData getData() {
		return data;
	}
	public void setData(SecurityUserData data) {
		this.data = data;
	}
	public List<SecurityUserPermission> getUpdata() {
		return updata;
	}
	public void setUpdata(List<SecurityUserPermission> updata) {
		this.updata = updata;
	}

}
