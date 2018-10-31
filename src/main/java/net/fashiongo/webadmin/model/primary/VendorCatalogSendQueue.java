package net.fashiongo.webadmin.model.primary;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Incheol Jung
 */
@Entity
@Table(name = "VendorCatalog_SendQueue")
public class VendorCatalogSendQueue implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer catalogSendQueueID;
	private Integer catalogID;
	private String subject;
	private String contents;
	private LocalDateTime createdOn;
	private LocalDateTime modifiedOn;
	private LocalDateTime scheduledSendOn;
	private LocalDateTime sentOn;
	private Boolean active;
	private Integer mailItemID;
	private Boolean isThisToAllRetailer;
	private Boolean isTestEmail;
	private String includedVendors;
	
	public Integer getCatalogSendQueueID() {
		return catalogSendQueueID;
	}
	public void setCatalogSendQueueID(Integer catalogSendQueueID) {
		this.catalogSendQueueID = catalogSendQueueID;
	}
	public Integer getCatalogID() {
		return catalogID;
	}
	public void setCatalogID(Integer catalogID) {
		this.catalogID = catalogID;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
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
	public LocalDateTime getScheduledSendOn() {
		return scheduledSendOn;
	}
	public void setScheduledSendOn(LocalDateTime scheduledSendOn) {
		this.scheduledSendOn = scheduledSendOn;
	}
	public LocalDateTime getSentOn() {
		return sentOn;
	}
	public void setSentOn(LocalDateTime sentOn) {
		this.sentOn = sentOn;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public Integer getMailItemID() {
		return mailItemID;
	}
	public void setMailItemID(Integer mailItemID) {
		this.mailItemID = mailItemID;
	}
	public Boolean getIsThisToAllRetailer() {
		return isThisToAllRetailer;
	}
	public void setIsThisToAllRetailer(Boolean isThisToAllRetailer) {
		this.isThisToAllRetailer = isThisToAllRetailer;
	}
	public Boolean getIsTestEmail() {
		return isTestEmail;
	}
	public void setIsTestEmail(Boolean isTestEmail) {
		this.isTestEmail = isTestEmail;
	}
	public String getIncludedVendors() {
		return includedVendors;
	}
	public void setIncludedVendors(String includedVendors) {
		this.includedVendors = includedVendors;
	}
}