package net.fashiongo.webadmin.model.primary;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Reo
 *
 */
@Entity
@Table(name = "Vendor_ImageRequest")
public class VendorImageRequest implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ImageRequestID")
	@Column(name = "ImageRequestID")
	private Integer imageRequestID;
	
	@JsonProperty("WholeSalerID")
	@Column(name = "WholeSalerID")
	private Integer wholeSalerID;
	
	@Transient
	@JsonProperty("CompanyName")
	@Column(name = "CompanyName")
	private String companyName;
	
	@JsonProperty("VendorImageTypeID")
	@Column(name = "VendorImageTypeID")
	private Integer vendorImageTypeID;
	
	@JsonProperty("RequestedOn")
	@Column(name = "RequestedOn")
	private LocalDateTime requestedOn;
	
	@JsonProperty("RequestedBy")
	@Column(name = "RequestedBy")
	private String requestedBy;
	
	@JsonProperty("IsApproved")
	@Column(name = "IsApproved")
	private Boolean isApproved;
	
	@JsonProperty("RejectReason")
	@Column(name = "RejectReason")
	private String rejectReason;
	
	@JsonProperty("DecidedOn")
	@Column(name = "DecidedOn")
	private LocalDateTime decidedOn;
	
	@JsonProperty("DecidedBy")
	@Column(name = "DecidedBy")
	private String decidedBy;
	
	@JsonProperty("Active")
	@Column(name = "Active")
	private Boolean active;
	
	@JsonProperty("OriginalFileName")
	@Column(name = "OriginalFileName")
	private String originalFileName;
	
	@JsonProperty("DeletedOn")
	@Column(name = "DeletedOn")
	private LocalDateTime deletedOn;
	
	@JsonProperty("DeletedBy")
	@Column(name = "DeletedBy")
	private String deletedBy;

	public Integer getImageRequestID() {
		return imageRequestID;
	}

	public void setImageRequestID(Integer imageRequestID) {
		this.imageRequestID = imageRequestID;
	}

	public Integer getWholeSalerID() {
		return wholeSalerID;
	}

	public void setWholeSalerID(Integer wholeSalerID) {
		this.wholeSalerID = wholeSalerID;
	}

	public Integer getVendorImageTypeID() {
		return vendorImageTypeID;
	}

	public void setVendorImageTypeID(Integer vendorImageTypeID) {
		this.vendorImageTypeID = vendorImageTypeID;
	}

	public LocalDateTime getRequestedOn() {
		return requestedOn;
	}

	public void setRequestedOn(LocalDateTime requestedOn) {
		this.requestedOn = requestedOn;
	}

	public String getRequestedBy() {
		return requestedBy;
	}

	public void setRequestedBy(String requestedBy) {
		this.requestedBy = requestedBy;
	}

	public Boolean getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(Boolean isApproved) {
		this.isApproved = isApproved;
	}

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	public LocalDateTime getDecidedOn() {
		return decidedOn;
	}

	public void setDecidedOn(LocalDateTime decidedOn) {
		this.decidedOn = decidedOn;
	}

	public String getDecidedBy() {
		return decidedBy;
	}

	public void setDecidedBy(String decidedBy) {
		this.decidedBy = decidedBy;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public LocalDateTime getDeletedOn() {
		return deletedOn;
	}

	public void setDeletedOn(LocalDateTime deletedOn) {
		this.deletedOn = deletedOn;
	}

	public String getDeletedBy() {
		return deletedBy;
	}

	public void setDeletedBy(String deletedBy) {
		this.deletedBy = deletedBy;
	}

}
