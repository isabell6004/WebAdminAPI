package net.fashiongo.webadmin.model.pojo.admin;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MenuPermission {
	@JsonProperty("Allow")
	private Boolean allow;
	
	@JsonProperty("AllowAdd")
	private Boolean allowAdd;
	
	@JsonProperty("AllowDelete")
	private Boolean allowDelete;
	
	@JsonProperty("AllowEdit")
	private Boolean allowEdit;
	
	@JsonProperty("ListOrder")
	private Integer listOrder;
	
	@JsonProperty("MenuID")
	private Integer menuID;
	
	@JsonProperty("MenuName")
	private String menuName;
	
	@JsonProperty("ParentID")
	private Integer parentID;
	
	@JsonProperty("ResourceID")
	private Integer resourceID;
	
	@JsonProperty("ResourceName")
	private String resourceName;
	
	@JsonProperty("RoutePath")
	private String routePath;
	
	@JsonProperty("Visible")
	private Boolean visible;
	public Boolean getAllow() {
		return allow;
	}
	public void setAllow(Boolean allow) {
		this.allow = allow;
	}
	public Boolean getAllowAdd() {
		return allowAdd;
	}
	public void setAllowAdd(Boolean allowAdd) {
		this.allowAdd = allowAdd;
	}
	public Boolean getAllowDelete() {
		return allowDelete;
	}
	public void setAllowDelete(Boolean allowDelete) {
		this.allowDelete = allowDelete;
	}
	public Boolean getAllowEdit() {
		return allowEdit;
	}
	public void setAllowEdit(Boolean allowEdit) {
		this.allowEdit = allowEdit;
	}
	public Integer getListOrder() {
		return listOrder;
	}
	public void setListOrder(Integer listOrder) {
		this.listOrder = listOrder;
	}
	public Integer getMenuID() {
		return menuID;
	}
	public void setMenuID(Integer menuID) {
		this.menuID = menuID;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public Integer getParentID() {
		return parentID;
	}
	public void setParentID(Integer parentID) {
		this.parentID = parentID;
	}
	public Integer getResourceID() {
		return resourceID;
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
	public String getRoutePath() {
		return routePath;
	}
	public void setRoutePath(String routePath) {
		this.routePath = routePath;
	}
	public Boolean getVisible() {
		return visible;
	}
	public void setVisible(Boolean visible) {
		this.visible = visible;
	}
}