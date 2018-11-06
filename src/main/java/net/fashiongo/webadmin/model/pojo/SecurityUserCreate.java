package net.fashiongo.webadmin.model.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SecurityUserCreate  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("ID")
	private Integer id;
	@JsonProperty("userName")
	private String userName;
	@JsonProperty("fullName")
	private String fullName;
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
	@JsonProperty("access")
	private String access;
	@JsonProperty("active")
	private Boolean active;
	@JsonProperty("assignedVendors")
	private Integer assignedVendors;
	@JsonProperty("chk")
	private Boolean chk;
	@JsonProperty("assignedVendorList")
	private String assignedVendorList;
	@JsonProperty("exempt")
	private Boolean exempt;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public LocalDateTime getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
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
	public String getAccess() {
		return access;
	}
	public void setAccess(String access) {
		this.access = access;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public Integer getAssignedVendors() {
		return assignedVendors;
	}
	public void setAssignedVendors(Integer assignedVendors) {
		this.assignedVendors = assignedVendors;
	}
	public Boolean getChk() {
		return chk;
	}
	public void setChk(Boolean chk) {
		this.chk = chk;
	}
	public String getAssignedVendorList() {
		return assignedVendorList;
	}
	public void setAssignedVendorList(String assignedVendorList) {
		this.assignedVendorList = assignedVendorList;
	}
	public Boolean getExempt() {
		return exempt;
	}
	public void setExempt(Boolean exempt) {
		this.exempt = exempt;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
