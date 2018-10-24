package net.fashiongo.webadmin.model.pojo.parameter;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class SetDeleteSecurityMenusParameter {
	
	@ApiModelProperty(required = false, example="[3,4]")
	@JsonProperty("data")
	private List<Integer> idList;

	public List<Integer> getIdList() {
		return idList;
	}

	public void setIdList(List<Integer> idList) {
		this.idList = idList;
	}
	
	
}
