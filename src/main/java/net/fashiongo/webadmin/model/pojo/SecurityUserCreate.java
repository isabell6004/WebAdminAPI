package net.fashiongo.webadmin.model.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SecurityUserCreate  implements Serializable {
	@JsonProperty("ID")
	private Integer ID;
	@JsonProperty("userName")
	private String userName;
	@JsonProperty("fullName")
	private String fullName;
	@JsonProperty("userGUID")
	private String userGUID;
	@JsonProperty("password")
	private String password;
	@JsonProperty("role")
	private String role;
	@JsonProperty("group")
	private String group;
	@JsonProperty("createdOn")
	private LocalDateTime createdOn;
	@JsonProperty("createdBy")
	private String createdBy;
	@JsonProperty("modifiedOn")
	private LocalDateTime modifiedOn;
	@JsonProperty("modifiedBy")
	private String modifiedBy;
	@JsonProperty("active")
	private Boolean active;
	@JsonProperty("exempt")
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
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	public LocalDateTime getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}
	public LocalDateTime getModifiedOn() {
		return modifiedOn;
	}
	public void setModifiedOn(LocalDateTime modifiedOn) {
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
	public String getUserGUID() {
		return userGUID;
	}
	public void setUserGUID(String userGUID) {
		this.userGUID = userGUID;
	}
	
}
