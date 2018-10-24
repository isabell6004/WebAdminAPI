package net.fashiongo.webadmin.model.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MenuDS {
	@JsonProperty("Table")
	List<SubMenu> subMenus;
	
	@JsonProperty("Table1")
	List<MenuPermission> menuPermission;
	
	public List<SubMenu> getSubMenus() {
		return subMenus;
	}
	public void setSubMenus(List<SubMenu> subMenus) {
		this.subMenus = subMenus;
	}
	public List<MenuPermission> getMenuPermission() {
		return menuPermission;
	}
	public void setMenuPermission(List<MenuPermission> menuPermission) {
		this.menuPermission = menuPermission;
	}
}