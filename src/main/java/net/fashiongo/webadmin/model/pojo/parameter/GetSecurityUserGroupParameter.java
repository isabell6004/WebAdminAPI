package net.fashiongo.webadmin.model.pojo.parameter;

import io.swagger.annotations.ApiModelProperty;

public class GetSecurityUserGroupParameter {
	@ApiModelProperty(required = true, example="1")
	private Integer usrId;

	public Integer getUsrId() {
		return usrId;
	}

	public void setUsrId(Integer usrId) {
		this.usrId = usrId;
	}
	
}
