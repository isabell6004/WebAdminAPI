package net.fashiongo.webadmin.model.primary;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Nayeon Kim
 */
@Entity
@Table(name = "[Security.User]")
public class SecurityUser {
	@Id
	@JsonProperty("UserID")
	@Column(name = "UserID")
	private Integer userID;
	
	@JsonProperty("UserName")
	@Column(name = "UserName")
	private String userName;
	
	@JsonProperty("FullName")
	@Column(name = "FullName")
	private String fullName;
	
	@JsonIgnore
	@Column(name = "Role")
	private String role;
	
	@JsonIgnore
	@Column(name = "Active")
	private Boolean active;
	
	@JsonIgnore
	@Column(name = "IPTimeExempt")
	private Boolean iPTimeExempt;
	
	@JsonIgnore
	@Column(name = "CreatedOn")
	private LocalDateTime createdOn;
	
	@JsonIgnore
	@Column(name = "CreatedBy")
	private String createdBy;
	
	@JsonIgnore
	@Column(name = "ModifiedOn")
	private LocalDateTime  modifiedOn;
	
	@JsonIgnore
	@Column(name = "ModifiedBy")
	private String modifiedBy;
	
	@Transient
	@JsonProperty("DispName")
	@Column(name = "DispName")
	private String dispName;

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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getiPTimeExempt() {
		return iPTimeExempt;
	}

	public void setiPTimeExempt(Boolean iPTimeExempt) {
		this.iPTimeExempt = iPTimeExempt;
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

	public String getDispName() {
		dispName = (this.active) ? "" + this.userName : "[x] " + this.userName;
        return dispName;
	}

	public void setDispName(String dispName) {
		this.dispName = dispName;
	}
}
