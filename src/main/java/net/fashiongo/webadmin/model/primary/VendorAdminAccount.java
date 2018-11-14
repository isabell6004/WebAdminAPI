package net.fashiongo.webadmin.model.primary;

import java.time.LocalDateTime;

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
@Table(name = "VendorAdminAccount")
public class VendorAdminAccount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "VendorAdminAccountID")
	@JsonProperty("VendorAdminAccountID")
	private Integer vendorAdminAccountID;
	
	@Column(name = "WholeSalerID")
	@JsonProperty("WholeSalerID")
	private Integer wholeSalerID;
	
	@Column(name = "FirstName")
	@JsonProperty("FirstName")
	private String firstName;
	
	@Column(name = "LastName")
	@JsonProperty("LastName")
	private String lastName;
	
	@Column(name = "UserName")
	@JsonProperty("UserName")
	private String userName;
	
	@Column(name = "UserGUID")
	@JsonProperty("UserGUID")
	private String userGUID;
	
	@Column(name = "Active")
	@JsonProperty("Active")
	private Boolean active;
	
	@Column(name = "CreatedOn")
	@JsonProperty("CreatedOn")
	private LocalDateTime createdOn;
	
	@Column(name = "CreatedBy")
	@JsonProperty("CreatedBy")
	private String createdBy;
	
	@Column(name = "ModifiedOn")
	@JsonProperty("ModifiedOn")
	private LocalDateTime modifiedOn;
	
	@Column(name = "ModifiedBy")
	@JsonProperty("ModifiedBy")
	private String modifiedBy;
	
	@Column(name = "CreditCardAccess")
	@JsonProperty("CreditCardAccess")
	private Integer creditCardAccess;

	public Integer getVendorAdminAccountID() {
		return vendorAdminAccountID;
	}

	public void setVendorAdminAccountID(Integer vendorAdminAccountID) {
		this.vendorAdminAccountID = vendorAdminAccountID;
	}

	public Integer getWholeSalerID() {
		return wholeSalerID;
	}

	public void setWholeSalerID(Integer wholeSalerID) {
		this.wholeSalerID = wholeSalerID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserGUID() {
		return userGUID;
	}

	public void setUserGUID(String userGUID) {
		this.userGUID = userGUID;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
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

	public Integer getCreditCardAccess() {
		return creditCardAccess;
	}

	public void setCreditCardAccess(Integer creditCardAccess) {
		this.creditCardAccess = creditCardAccess;
	}
	
	
}
