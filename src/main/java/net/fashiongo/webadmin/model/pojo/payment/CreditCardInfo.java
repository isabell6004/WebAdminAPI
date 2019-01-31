package net.fashiongo.webadmin.model.pojo.payment;

import java.time.LocalDateTime;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CreditCardInfo {
	@JsonProperty("CreditCardID")
	private Integer creditCardID;

	@JsonProperty("RetailerID")
	private Integer retailerID;

	@JsonProperty("ReferenceID")
	private String referenceID;

	@JsonProperty("Last4Digit")
	private Integer last4Digit;

	@JsonProperty("CVV")
	private String cvv;

	@JsonProperty("NameOnCard")
	private String nameOnCard;

	@JsonProperty("Street")
	private String street;

	@JsonProperty("City")
	private String city;

	@JsonProperty("State")
	private String state;

	@JsonProperty("Zipcode")
	private Integer zipcode;

	@JsonProperty("Country")
	private String country;

	@JsonProperty("CountryID")
	private Integer countryID;

	@JsonProperty("IsDefaultCard")
	@Column(name="IsDefaultCard")
	private Boolean defaultCard;

	@JsonProperty("CardStatusID")
	private Integer cardStatusID;

	@JsonProperty("IsDeleted")
	@Column(name="IsDeleted")
	private Boolean deleted;

	@JsonProperty("CreatedOn")
	private LocalDateTime createdOn;

	@JsonProperty("CreatedBy")
	private String createdBy;

	@JsonProperty("ModifiedOn")
	private LocalDateTime modifiedOn;

	@JsonProperty("ModifiedBy")
	private String modifiedBy;

	@JsonProperty("CardTypeID")
	private Integer cardTypeID;

	@JsonProperty("CreditCardType")
	private String creditCardType;

	@JsonProperty("Buyer")
	private String buyer;

	@JsonProperty("CreditCardInfo")
	private String creditCardInfo;

	@JsonProperty("CardStatusName")
	private String cardStatusName;

	@JsonProperty("BillingAddress")
	private String billingAddress;

	@JsonProperty("ExpirationMonth")
	private Integer expirationMonth;

	@JsonProperty("ExpirationYear")
	private Integer expirationYear;

	@JsonProperty("ExpDate")
	private String expDate;

	@JsonProperty("PendingCount")
	private Integer pendingCount;

	@JsonProperty("Reason")
	private String reason;
}
