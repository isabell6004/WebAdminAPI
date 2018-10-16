package net.fashiongo.webadmin.model.pojo;

/**
 * @author Jiwon Kim
 */

public class SecurityMenus2 {
	private Integer MenuID;
	private String MenuName;
	private Integer ParentID;
	private String ParentMenuName;
	private Integer ResourceID;
	private String ResourceName;
	
	private String RoutePath;
	private String MenuIcon;
	private Integer ApplicationID;
	private Integer ListOrder;
	private Boolean Visible;
	private Boolean Active;
	
	public Integer getMenuID() {
		return MenuID;
	}
	public void setMenuID(Integer menuID) {
		MenuID = menuID;
	}
	public String getMenuName() {
		return MenuName;
	}
	public void setMenuName(String menuName) {
		MenuName = menuName;
	}
	public Integer getParentID() {
		return ParentID;
	}
	public void setParentID(Integer parentID) {
		ParentID = parentID;
	}
	public String getParentMenuName() {
		return ParentMenuName;
	}
	public void setParentMenuName(String parentMenuName) {
		ParentMenuName = parentMenuName;
	}
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
	public String getRoutePath() {
		return RoutePath;
	}
	public void setRoutePath(String routePath) {
		RoutePath = routePath;
	}
	public String getMenuIcon() {
		return MenuIcon;
	}
	public void setMenuIcon(String menuIcon) {
		MenuIcon = menuIcon;
	}
	public Integer getApplicationID() {
		return ApplicationID;
	}
	public void setApplicationID(Integer applicationID) {
		ApplicationID = applicationID;
	}
	public Integer getListOrder() {
		return ListOrder;
	}
	public void setListOrder(Integer listOrder) {
		ListOrder = listOrder;
	}
	public Boolean getVisible() {
		return Visible;
	}
	public void setVisible(Boolean visible) {
		Visible = visible;
	}
	public Boolean getActive() {
		return Active;
	}
	public void setActive(Boolean active) {
		Active = active;
	}
	
	
}
