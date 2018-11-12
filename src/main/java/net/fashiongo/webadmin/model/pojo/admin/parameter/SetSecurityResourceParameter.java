package net.fashiongo.webadmin.model.pojo.admin.parameter;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
/**
 * 
 * @author DAHYE
 *
 */
@SuppressWarnings("serial")
public class SetSecurityResourceParameter implements Serializable {
	@ApiModelProperty(required = false, example="113")
	private Integer resourceID;
	@ApiModelProperty(required = false, example="User")
	private String resourceName;
	@ApiModelProperty(required = false, example="1")
	private Integer applicationid;
	@ApiModelProperty(required = false, example="Page")
	private String resourceType;
	@ApiModelProperty(required = false, example="url")
	private String resourceUrl;
	@ApiModelProperty(required = false, example="true")
	private Boolean active;

	public Integer getResourceID() {
		return resourceID == null ? 0 : resourceID;
	}
	public void setResourceID(Integer resourceID) {
		this.resourceID = resourceID;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public Integer getApplicationid() {
		return applicationid == null ? 0 : applicationid;
	}
	public void setApplicationid(Integer applicationid) {
		this.applicationid = applicationid;
	}
	public String getResourceType() {
		return resourceType;
	}
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
	public String getResourceUrl() {
		return resourceUrl;
	}
	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}
	public Boolean getActive() {
		return active == null ? false : active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	
}
