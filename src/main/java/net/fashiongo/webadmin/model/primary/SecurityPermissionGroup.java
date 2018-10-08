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
@Table(name = "[security.Permission_Group]")
public class SecurityPermissionGroup {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PermissionID")
	@JsonProperty("PermissionID")
	private int permissionID;
	
	@Column(name = "GroupID")
	@JsonProperty("GroupID")
	private Integer groupID;
	
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

	public int getPermissionID() {
		return permissionID;
	}

	public void setPermissionID(int permissionID) {
		this.permissionID = permissionID;
	}

	public Integer getGroupID() {
		return groupID;
	}

	public void setGroupID(Integer groupID) {
		this.groupID = groupID;
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
}