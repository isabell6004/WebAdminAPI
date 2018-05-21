/**
 * 
 */
package net.fashiongo.webadmin.model.fgpay;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.fashiongo.common.conversion.LocalDateTimeConverter;

/**
 * @author Brian
 *
 */
public class Dispute {
	@Column(name = "DisputeID")
	private String disputeId;

	public String getDisputeId() {
		return disputeId;
	}

	public void setDisputeId(String disputeId) {
		this.disputeId = disputeId;
	}
	
	@Column(name = "PONumber")
	private String poNumber;

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}
	
	@Column(name = "RetailerCompanyName")
	private String companyName;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	@Column(name = "Reason")
	private String reason;

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	@Column(name = "Amount")
	private BigDecimal amount;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	@JsonIgnore
	@Column(name = "PaymentDate")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime paymentDate;

	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDateTime paymentDate) {
		this.paymentDate = paymentDate;
	}
	
	@Transient
	private String paymentOn;
	public String getPaymentOn() {
		return (this.paymentDate != null) ? this.paymentDate.toString() : null;
	}
	
	@JsonIgnore
	@Column(name = "CreatedOn")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime createdOn;

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}
	
	@Transient
	private String createdDate;
	public String getCreatedDate() {
		return (this.createdOn != null) ? this.createdOn.toString() : null;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}
	
	@Column(name = "EvidenceDetailsDueBy")
	private LocalDateTime evidenceDetailDueBy;

	public LocalDateTime getEvidenceDetailDueBy() {
		return evidenceDetailDueBy;
	}

	public void setEvidenceDetailDueBy(LocalDateTime evidenceDetailDueBy) {
		this.evidenceDetailDueBy = evidenceDetailDueBy;
	}
	
	@Transient
	private String evidenceDueBy;
	public String getEvidenceDueBy() {
		return (this.evidenceDetailDueBy != null) ? this.evidenceDetailDueBy.toString() : null;
	}
	
	@Column(name = "Status")
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "OrderType")
	private Integer orderType;

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	
	@Column(name = "OrderID")
	private Integer orderId;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	@Column(name = "WholeSalerID")
	private Integer wholesalerId;

	public Integer getWholesalerId() {
		return wholesalerId;
	}

	public void setWholesalerId(Integer wholesalerId) {
		this.wholesalerId = wholesalerId;
	}
	
	@Column(name = "WholeCompanyName")
	private String vendorName;

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
}
