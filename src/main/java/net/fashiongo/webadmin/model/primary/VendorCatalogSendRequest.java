package net.fashiongo.webadmin.model.primary;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Incheol Jung
 */
@Entity
@Table(name = "VendorCatalog_SendRequest")
public class VendorCatalogSendRequest implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("CatalogSendRequestID")
	private Integer catalogSendRequestID;
	private Integer requestedCatalogID;
	private Integer catalogSendQueueID;
	private Integer fGCatalogID;
	private Integer catalogSortNo;
	private LocalDateTime createdOn;
	private LocalDateTime modifiedOn;
	private Boolean active;
	
	public Integer getCatalogSendRequestID() {
		return catalogSendRequestID;
	}
	public void setCatalogSendRequestID(Integer catalogSendRequestID) {
		this.catalogSendRequestID = catalogSendRequestID;
	}
	public Integer getRequestedCatalogID() {
		return requestedCatalogID;
	}
	public void setRequestedCatalogID(Integer requestedCatalogID) {
		this.requestedCatalogID = requestedCatalogID;
	}
	public Integer getCatalogSendQueueID() {
		return catalogSendQueueID;
	}
	public void setCatalogSendQueueID(Integer catalogSendQueueID) {
		this.catalogSendQueueID = catalogSendQueueID;
	}
	public Integer getfGCatalogID() {
		return fGCatalogID;
	}
	public void setfGCatalogID(Integer fGCatalogID) {
		this.fGCatalogID = fGCatalogID;
	}
	public Integer getCatalogSortNo() {
		return catalogSortNo;
	}
	public void setCatalogSortNo(Integer catalogSortNo) {
		this.catalogSortNo = catalogSortNo;
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
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
}