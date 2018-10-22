package net.fashiongo.webadmin.model.pojo.parameter;

import org.apache.commons.lang3.StringUtils;

import io.swagger.annotations.ApiModelProperty;

public class SetActiveGroupParameter {
	@ApiModelProperty(required = false, example="17")
	private String groupID;
	@ApiModelProperty(required = false, example="true")
	private String active;
	
	public Integer getGroupID() {
		return StringUtils.isEmpty(groupID) ? 0 : Integer.parseInt(groupID);
	}
	
	public boolean getActive() {
		return StringUtils.isEmpty(active) ? false : Boolean.valueOf(active);
	}

	public void setGroupID(String groupID) {
		this.groupID = groupID;
	}

	public void setActive(String active) {
		this.active = active;
	}
}