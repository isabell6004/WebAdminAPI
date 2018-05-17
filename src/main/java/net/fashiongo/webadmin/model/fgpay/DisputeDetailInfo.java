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
public class DisputeDetailInfo {
	@Column(name = "DisputeID")
	private String disputeId;

	public String getDisputeId() {
		return disputeId;
	}

	public void setDisputeId(String disputeId) {
		this.disputeId = disputeId;
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
	
	@Column(name = "Amount")
	private BigDecimal amount;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	@Column(name = "Status")
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "CreditCardType")
	private String creditCardType;

	public String getCreditCardType() {
		return creditCardType;
	}

	public void setCreditCardType(String creditCardType) {
		this.creditCardType = creditCardType;
	}
	
	@Column(name = "Last4Digit")
	private String last4Digit;

	public String getLast4Digit() {
		return last4Digit;
	}

	public void setLast4Digit(String last4Digit) {
		this.last4Digit = last4Digit;
	}
	
	@Column(name = "NameOnCard")
	private String nameOnCard;

	public String getNameOnCard() {
		return nameOnCard;
	}

	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}
	
	@Column(name = "Reason")
	private String reason;

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
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
	
	@Column(name = "Submission")
	private Boolean submission;

	public Boolean getSubmission() {
		return submission;
	}

	public void setSubmission(Boolean submission) {
		this.submission = submission;
	}
	
	@Column(name = "Street")
	private String street;
	
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}
	
	@Column(name = "city")
	private String city;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	@Column(name = "State")
	private String state;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Column(name = "zipcode")
	private String zipcode;

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	@Column(name = "Country")
	private String country;
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name = "ExpirationMonth")
	private Integer expMonth;

	public Integer getExpMonth() {
		return expMonth;
	}

	public void setExpMonth(Integer expMonth) {
		this.expMonth = expMonth;
	}
	
	@Column(name = "ExpirationYear")
	private Integer expYear;

	public Integer getExpYear() {
		return expYear;
	}

	public void setExpYear(Integer expYear) {
		this.expYear = expYear;
	}
}
