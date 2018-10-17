package net.fashiongo.webadmin.model.pojo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SecurityUsers {
	@JsonProperty("UserID")
	private Integer userID;
	@JsonProperty("UserName")
	private String userName;
	@JsonProperty("FullName")
	private String fullName;
	@JsonProperty("Role")
	private String role;
	@JsonProperty("GroupName")
	private String groupName;
	@JsonProperty("CreatedOn")
	private LocalDateTime createdOn;
	@JsonProperty("CreatedBy")
	private String createdBy;
	@JsonProperty("ModifiedOn")
	private LocalDateTime modifiedOn;
	@JsonProperty("ModifiedBy")
	private String modifiedBy;
	@JsonProperty("AccessTime")
	private String accessTime;
	@JsonProperty("IPTimeExempt")
	private Boolean ipTimeExempt;
	@JsonProperty("Active")
	private Boolean active;
	@JsonProperty("AssignedVendors")
	private String assignedVendors;
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
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
	public String getAccessTime() {
		return accessTime;
	}
	public void setAccessTime(String accessTime) {
		this.accessTime = accessTime;
	}
	public Boolean getIpTimeExempt() {
		return ipTimeExempt;
	}
	public void setIpTimeExempt(Boolean ipTimeExempt) {
		this.ipTimeExempt = ipTimeExempt;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public String getAssignedVendors() {
		return assignedVendors;
	}
	public void setAssignedVendors(String assignedVendors) {
		this.assignedVendors = assignedVendors;
	}
	
}
