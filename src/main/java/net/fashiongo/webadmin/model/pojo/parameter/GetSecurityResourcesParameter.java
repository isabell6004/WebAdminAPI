package net.fashiongo.webadmin.model.pojo.parameter;

import io.swagger.annotations.ApiModelProperty;

public class GetSecurityResourcesParameter {
	@ApiModelProperty(required = false, example="fashiongo")
	private String resourceName;
	@ApiModelProperty(required = false, example="All")
	private String resourceParent;
	@ApiModelProperty(required = false, example="page")
	private String resourceType;
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getResourceParent() {
		return resourceParent;
	}
	public void setResourceParent(String resourceParent) {
		this.resourceParent = resourceParent;
	}
	public String getResourceType() {
		return resourceType;
	}
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
	
	
}
