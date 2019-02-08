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
@Table(name = "Log_Communication")
public class LogCommunication {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("CommunicationID")
	@Column(name = "CommunicationID")
	private Integer communicationID;
	
	@Column(name = "RetailerID")
	private Integer retailerID;
	
	@Column(name = "CompanyName")
	private String companyName;
	
	@Column(name = "ContactedBy")
	private String contactedBy;
	
	@Column(name = "ReasonID")
	private Integer reasonID;
	
	@Column(name = "Notes")
	private String notes;
	
	@Column(name = "CommunicatedOn")
	private LocalDateTime communicatedOn;

	@Column(name = "CheckedBy")
	private String checkedBy;
	
	@Column(name = "CheckedOn")
	private LocalDateTime checkedOn;
	
	@Column(name = "CheckedBy2")
	private String checkedBy2;
	
	@Column(name = "CheckedOn2")
	private LocalDateTime checkedOn2;
	
	@Column(name = "CreatedOn")
	private LocalDateTime createdOn;
	
	@Column(name = "ModifiedOn")
	private LocalDateTime modifiedOn;
	
	@Column(name = "ModifiedBy")
	private String modifiedBy;
	
	@Column(name = "IsForVendor")
	private Boolean isForVendor;

	public Integer getCommunicationID() {
		return communicationID;
	}

	public void setCommunicationID(Integer communicationID) {
		this.communicationID = communicationID;
	}

	public Integer getRetailerID() {
		return retailerID;
	}

	public void setRetailerID(Integer retailerID) {
		this.retailerID = retailerID;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getContactedBy() {
		return contactedBy;
	}

	public void setContactedBy(String contactedBy) {
		this.contactedBy = contactedBy;
	}

	public Integer getReasonID() {
		return reasonID;
	}

	public void setReasonID(Integer reasonID) {
		this.reasonID = reasonID;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public LocalDateTime getCommunicatedOn() {
		return communicatedOn;
	}

	public void setCommunicatedOn(LocalDateTime communicatedOn) {
		this.communicatedOn = communicatedOn;
	}

	public String getCheckedBy() {
		return checkedBy;
	}

	public void setCheckedBy(String checkedBy) {
		this.checkedBy = checkedBy;
	}

	public LocalDateTime getCheckedOn() {
		return checkedOn;
	}

	public void setCheckedOn(LocalDateTime checkedOn) {
		this.checkedOn = checkedOn;
	}

	public String getCheckedBy2() {
		return checkedBy2;
	}

	public void setCheckedBy2(String checkedBy2) {
		this.checkedBy2 = checkedBy2;
	}

	public LocalDateTime getCheckedOn2() {
		return checkedOn2;
	}

	public void setCheckedOn2(LocalDateTime checkedOn2) {
		this.checkedOn2 = checkedOn2;
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

	public Boolean getIsForVendor() {
		return isForVendor;
	}

	public void setIsForVendor(Boolean isForVendor) {
		this.isForVendor = isForVendor;
	}
	
	
}
