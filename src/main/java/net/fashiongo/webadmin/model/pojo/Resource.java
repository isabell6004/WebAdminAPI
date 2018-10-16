package net.fashiongo.webadmin.model.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Dahye Jeong
 */

public class Resource {
	@JsonProperty("ResourceID")
	private Integer resourceID;
	@JsonProperty("ApplicationID")
	private Integer applicationID;
	@JsonProperty("ApplicationName")
	private String applicationName;
	@JsonProperty("ResourceName")
	private String resourceName;
	@JsonProperty("DispName")
	private String dispName;
	@JsonProperty("ResourceParent")
	private String resourceParent;
	@JsonProperty("ResourceType")
	private String resourceType;
	@JsonProperty("ResourceUrl")
	private String resourceUrl;
	@JsonProperty("Active")
	private Boolean active;
	public Integer getResourceID() {
		return resourceID;
	}
	public void setResourceID(Integer resourceID) {
		this.resourceID = resourceID;
	}
	public Integer getApplicationID() {
		return applicationID;
	}
	public void setApplicationID(Integer applicationID) {
		this.applicationID = applicationID;
	}
	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getDispName() {
		return dispName;
	}
	public void setDispName(String dispName) {
		this.dispName = dispName;
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
	public String getResourceUrl() {
		return resourceUrl;
	}
	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	
}
