package net.fashiongo.webadmin.model.pojo.parameter;

import io.swagger.annotations.ApiModelProperty;

public class SetActiveGroupParameter {
	@ApiModelProperty(required = false, example="17")
	private Integer groupID;
	@ApiModelProperty(required = false, example="true")
	private boolean Active;
	
	public Integer getGroupID() {
		return groupID;
	}
	public void setGroupID(Integer groupID) {
		this.groupID = groupID;
	}
	public boolean getActive() {
		return Active;
	}
	public void setActive(boolean active) {
		Active = active;
	}
	
}