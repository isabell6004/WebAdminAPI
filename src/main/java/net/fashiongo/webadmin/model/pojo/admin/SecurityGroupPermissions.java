package net.fashiongo.webadmin.model.pojo.admin;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SecurityGroupPermissions {
	@JsonProperty("MenuID")
	private Integer menuID;
	@JsonProperty("ResourceID")
	private Integer resourceID;
	@JsonProperty("Resource")
	private String resource;
	@JsonProperty("ParentMenuID")
	private Integer parentMenuID;
	@JsonProperty("Parent")
	private String parent;
	@JsonProperty("Type")
	private String type;
	@JsonProperty("Allow")
	private Boolean allow;
	@JsonProperty("AllowEdit")
	private Boolean allowEdit;
	@JsonProperty("AllowDelete")
	private Boolean allowDelete;
	@JsonProperty("AllowAdd")
	private Boolean allowAdd;
	@JsonProperty("Visible")
	private Boolean visible;
	public Integer getMenuID() {
		return menuID;
	}
	public void setMenuID(Integer menuID) {
		this.menuID = menuID;
	}
	public Integer getResourceID() {
		return resourceID;
	}
	public void setResourceID(Integer resourceID) {
		this.resourceID = resourceID;
	}
	public String getResource() {
		return resource;
	}
	public void setResource(String resource) {
		this.resource = resource;
	}
	public Integer getParentMenuID() {
		return parentMenuID;
	}
	public void setParentMenuID(Integer parentMenuID) {
		this.parentMenuID = parentMenuID;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Boolean getAllow() {
		return allow;
	}
	public void setAllow(Boolean allow) {
		this.allow = allow;
	}
	public Boolean getAllowEdit() {
		return allowEdit;
	}
	public void setAllowEdit(Boolean allowEdit) {
		this.allowEdit = allowEdit;
	}
	public Boolean getAllowDelete() {
		return allowDelete;
	}
	public void setAllowDelete(Boolean allowDelete) {
		this.allowDelete = allowDelete;
	}
	public Boolean getAllowAdd() {
		return allowAdd;
	}
	public void setAllowAdd(Boolean allowAdd) {
		this.allowAdd = allowAdd;
	}
	public Boolean getVisible() {
		return visible;
	}
	public void setVisible(Boolean visible) {
		this.visible = visible;
	}
	
	
}
