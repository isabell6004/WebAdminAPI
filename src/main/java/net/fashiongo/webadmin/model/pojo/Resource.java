package net.fashiongo.webadmin.model.pojo;

/**
 * @author Dahye Jeong
 */

public class Resource {
	private Integer ResourceID;
	private Integer ApplicationID;
	private String ApplicationName;
	private String ResourceName;
	private String DispName;
	private String ResourceParent;
	private String ResourceType;
	private String ResourceUrl;
	private Boolean Active;
	public Integer getResourceID() {
		return ResourceID;
	}
	public void setResourceID(Integer resourceID) {
		ResourceID = resourceID;
	}
	public Integer getApplicationID() {
		return ApplicationID;
	}
	public void setApplicationID(Integer applicationID) {
		ApplicationID = applicationID;
	}
	public String getApplicationName() {
		return ApplicationName;
	}
	public void setApplicationName(String applicationName) {
		ApplicationName = applicationName;
	}
	public String getResourceName() {
		return ResourceName;
	}
	public void setResourceName(String resourceName) {
		ResourceName = resourceName;
	}
	public String getResourceParent() {
		return ResourceParent;
	}
	public void setResourceParent(String resourceParent) {
		ResourceParent = resourceParent;
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
	
	public String getDispName() {
		return DispName;
	}
	public void setDispName(String dispname) {
		DispName = dispname;
	}
	
	
}
