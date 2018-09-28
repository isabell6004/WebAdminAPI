package net.fashiongo.webadmin.model.pojo.parameter;

import io.swagger.annotations.ApiModelProperty;

public class SetResourceParameter {
	@ApiModelProperty(required = false, example="161")
	private String resourceId;
	@ApiModelProperty(required = false, example="false")
	private Boolean active;
	public String getResourceId() {
		return resourceId;
	}
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	
}
