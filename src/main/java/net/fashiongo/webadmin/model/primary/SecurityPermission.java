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
 * @author Reo
 *
 */
@Entity
@Table(name = "[security.Permission]")
public class SecurityPermission {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PermissionID")
	@JsonProperty("PermissionID")
	private Integer permissionID;
	
	@Column(name = "UserID")
	@JsonProperty("UserID")
	private Integer userID;
	
	@Column(name = "ResourceID")
	@JsonProperty("ResourceID")
	private Integer resourceID;
	
	@Column(name = "Allow")
	@JsonProperty("Allow")
	private Boolean allow;
	
	@Column(name = "AllowEdit")
	@JsonProperty("AllowEdit")
	private Boolean allowEdit;
	
	@Column(name = "AllowDelete")
	@JsonProperty("AllowDelete")
	private Boolean allowDelete;
	
	@Column(name = "AllowAdd")
	@JsonProperty("AllowAdd")
	private Boolean allowAdd;
	
	@Column(name = "ApplicationID")
	@JsonProperty("ApplicationID")
	private Integer applicationID;

	public Integer getPermissionID() {
		return permissionID;
	}

	public void setPermissionID(Integer permissionID) {
		this.permissionID = permissionID;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public Integer getResourceID() {
		return resourceID;
	}

	public void setResourceID(Integer resourceID) {
		this.resourceID = resourceID;
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

	public Integer getApplicationID() {
		return applicationID;
	}

	public void setApplicationID(Integer applicationID) {
		this.applicationID = applicationID;
	}
	
	
}
