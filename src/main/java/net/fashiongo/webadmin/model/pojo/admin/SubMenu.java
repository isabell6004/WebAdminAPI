package net.fashiongo.webadmin.model.pojo.admin;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SubMenu {

	@JsonProperty("MenuID")
	private Integer menuID;
	
	@JsonProperty("MenuIcon")
	private String menuIcon;
	
	@JsonProperty("Name")
	private String name;

	public Integer getMenuID() {
		return menuID;
	}

	public void setMenuID(Integer menuID) {
		this.menuID = menuID;
	}

	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}