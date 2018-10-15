package net.fashiongo.webadmin.model.pojo;

import java.util.Date;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SecurityUserCreate {
	private Integer ID;
	private String userName;
	private String fullName;
	private String password;
	private String role;
	private String group;
	private Date createdOn;
	private String createdBy;
	private Date modifiedOn;
	private String modifiedBy;
	private Boolean active;
	private Boolean exempt;
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getModifiedOn() {
		return modifiedOn;
	}
	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public Boolean getExempt() {
		return exempt;
	}
	public void setExempt(Boolean exempt) {
		this.exempt = exempt;
	}
	
}
