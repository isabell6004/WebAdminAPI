package net.fashiongo.webadmin.model.pojo.admin.parameter;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class GetSecurityUserGroupParameter {
	@ApiModelProperty(required = true, example="1")
	@JsonProperty("usrid")
	private Integer usrId;

	public Integer getUsrId() {
		return usrId;
	}

	public void setUsrId(Integer usrId) {
		this.usrId = usrId;
	}

	
}
