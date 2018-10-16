package net.fashiongo.webadmin.model.pojo.parameter;

import org.apache.commons.lang3.StringUtils;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author Dahye Jeong
 */

public class GetSecurityResourcesParameter {
	@ApiModelProperty(required = false, example="Web Admin")
	private String application;
	@ApiModelProperty(required = false, example="")
	private String resourceName;
	@ApiModelProperty(required = false, example="All")
	private String resourceParent;
	@ApiModelProperty(required = false, example="All")
	private String resourceType;
	
	public String getApplication() {
		return StringUtils.isEmpty(application) ? "Web Admin" : application;
	}
	public void setApplication(String application) {
		this.application = application;
	}
	public String getResourceName() {
		return StringUtils.isEmpty(resourceName) ? "" : resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getResourceParent() {
		return StringUtils.isEmpty(resourceParent) ? null : resourceParent ;
	}
	public void setResourceParent(String resourceParent) {
		this.resourceParent = resourceParent;
	}
	public String getResourceType() {
		return StringUtils.isEmpty(resourceType) ? null : resourceType;
	}
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
	
	
}
