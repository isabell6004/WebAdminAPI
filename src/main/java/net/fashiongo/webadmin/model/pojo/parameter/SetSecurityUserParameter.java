package net.fashiongo.webadmin.model.pojo.parameter;

import java.util.List;

import net.fashiongo.webadmin.model.pojo.SecurityUserData;
import net.fashiongo.webadmin.model.pojo.SecurityUserPermission;

public class SetSecurityUserParameter {
	private SecurityUserData data;
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
