package net.fashiongo.webadmin.model.pojo.parameter;

import io.swagger.annotations.ApiModelProperty;

public class SetSecurityResourceParameter {
	@ApiModelProperty(required = false, example="113")
	private Integer ResourceID;
	@ApiModelProperty(required = false, example="User")
	private String ResourceName;
	@ApiModelProperty(required = false, example="1")
	private Integer Applicationid;
	@ApiModelProperty(required = false, example="Page")
	private String ResourceType;
	@ApiModelProperty(required = false, example="url")
	private String ResourceUrl;
	@ApiModelProperty(required = false, example="true")
	private Boolean Active;
	public Integer getResourceID() {
		return ResourceID;
	}
	public void setResourceID(Integer resourceID) {
		ResourceID = resourceID;
	}
	public String getResourceName() {
		return ResourceName;
	}
	public void setResourceName(String resourceName) {
		ResourceName = resourceName;
	}
	public Integer getApplicationid() {
		return Applicationid;
	}
	public void setApplicationid(Integer applicationid) {
		Applicationid = applicationid;
	}
	public String getResourceType() {
		return ResourceType;
	}
	public void setResourceType(String resourceType) {
		ResourceType = resourceType;
	}
	public String getResourceUrl() {
		return ResourceUrl;
	}
	public void setResourceUrl(String resourceUrl) {
		ResourceUrl = resourceUrl;
	}
	public Boolean getActive() {
		return Active;
	}
	public void setActive(Boolean active) {
		Active = active;
	}
	
}
