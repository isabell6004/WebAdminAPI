package net.fashiongo.webadmin.model.primary;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 
 * @author Reo
 *
 */
@Data
@Entity
@Table(name = "Vendor_ImageRequest")
public class VendorImageRequest {
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
}
