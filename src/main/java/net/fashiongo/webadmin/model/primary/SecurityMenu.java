package net.fashiongo.webadmin.model.primary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author DAHYE
 *
 */
@Entity
@Table(name = "[security.Menu]")
public class SecurityMenu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("MenuID")
	@Column(name = "MenuID")
	private Integer menuID;
	
	@JsonProperty("Name")
	@Column(name = "Name")
	private String name;
	
	@JsonProperty("ParentID")
	@Column(name = "ParentID")
	private Integer parentID;
	
	@JsonProperty("ApplicationID")
	@Column(name = "ApplicationID")
	private Integer applicationID;
	
	@JsonProperty("ResourceID")
	@Column(name = "ResourceID")
	private Integer resourceID;
	
	@JsonProperty("Visible")
	@Column(name = "Visible")
	private Boolean visible;
	
	@JsonProperty("Active")
	@Column(name = "Active")
	private Boolean active;
	
	@JsonProperty("ListOrder")
	@Column(name = "ListOrder")
	private Integer listOrder;
	
	@JsonProperty("RoutePath")
	@Column(name = "RoutePath")
	private String routePath;
	
	@JsonProperty("MenuIcon")
	@Column(name = "MenuIcon")
	private String menuIcon;

	public Integer getMenuID() {
		return menuID;
	}

	public void setMenuID(Integer menuID) {
		this.menuID = menuID;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParentID() {
		return parentID;
	}

	public void setParentID(Integer parentID) {
		this.parentID = parentID;
	}

	public Integer getApplicationID() {
		return applicationID;
	}

	public void setApplicationID(Integer applicationID) {
		this.applicationID = applicationID;
	}

	public Integer getResourceID() {
		return resourceID;
	}

	public void setResourceID(Integer resourceID) {
		this.resourceID = resourceID;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Integer getListOrder() {
		return listOrder;
	}

	public void setListOrder(Integer listOrder) {
		this.listOrder = listOrder;
	}

	public String getRoutePath() {
		return routePath;
	}

	public void setRoutePath(String routePath) {
		this.routePath = routePath;
	}

	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}
	
	
}
