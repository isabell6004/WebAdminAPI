package net.fashiongo.webadmin.model.pojo.parameter;

public class SetSecurityResourceParameter {
	private Integer ResourceID;
	private String ResourceName;
	private String Manager;
	private Integer Applicationid;
	private String ResourceType;
	private String ResourceUrl;
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
	public String getManager() {
		return Manager;
	}
	public void setManager(String manager) {
		Manager = manager;
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
