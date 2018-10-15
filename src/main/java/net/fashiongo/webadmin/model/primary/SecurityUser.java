package net.fashiongo.webadmin.model.primary;

import java.util.Date;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonProperty;
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
	
	@JsonProperty("UserGUID")
	@Column(name = "UserGUID")
	private String userGUID;
	
	@JsonProperty("Role")
	@Column(name = "Role")
	private String role;
	
	@JsonProperty("Active")
	@Column(name = "Active")
	private Boolean active;
	
	@JsonProperty("IPTimeExempt")
	private Boolean ipTimeExempt;
	
	@JsonProperty("CreatedOn")
	@Column(name = "CreatedOn")
	private Date createdOn;
	
	@JsonProperty("CreatedBy")
	@Column(name = "CreatedBy")
	private String createdBy;
	
	@JsonProperty("ModifiedOn")
	@Column(name = "ModifiedOn")
	private Date modifiedOn;
	
	@JsonProperty("ModifiedBy")
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

	public String getUserGUID() {
		return userGUID;
	}

	public void setUserGUID(String userGUID) {
		this.userGUID = userGUID;
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

	public Boolean getIpTimeExempt() {
		return ipTimeExempt;
	}

	public void setIpTimeExempt(Boolean ipTimeExempt) {
		this.ipTimeExempt = ipTimeExempt;
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

	public String getDispName() {
		dispName = (this.active) ? "" + this.userName : "[x] " + this.userName;
        return dispName;
	}

	public void setDispName(String dispName) {
		this.dispName = dispName;
	}
}
