package net.fashiongo.webadmin.dao.primary;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 
 * @author DAHYE
 *
 */
@Entity
@Data
@Table(name = "PaymentCreditCard")
public class PaymentCreditCard {
	@Id
	@Column(name="CreditCardID") 
	@JsonProperty("CreditCardID") 
	private Integer creditCardID;
	
	@Column(name="RetailerID") 
	@JsonProperty("RetailerID") 
	private Integer retailerID;
	
	@Column(name="ReferenceID")
	@JsonProperty("ReferenceID")
	private String referenceID;
	
	@Column(name="CardTypeID") 
	@JsonProperty("CardTypeID")
	private Integer cardTypeID;
	
	@Column(name="Last4Digit")
	@JsonProperty("Last4Digit")
	private String last4Digit;
	
	@Column(name="CVV") 
	@JsonProperty("CVV") 
	private String cvv;
	
	@Column(name="ExpirationMonth") 
	@JsonProperty("ExpirationMonth") 
	private Integer expirationMonth;
	
	@Column(name="ExpirationYear")
	@JsonProperty("ExpirationYear")
	private Integer expirationYear;
	
	@Column(name="NameOnCard") 
	@JsonProperty("NameOnCard")
	private String nameOnCard;
	
	@Column(name="Fingerprint") 
	@JsonProperty("Fingerprint") 
	private String fingerprint;
	
	@Column(name="Email") 
	@JsonProperty("Email") 
	private String email;
	
	@Column(name="Street") 
	@JsonProperty("Street") 
	private String street;
	
	@Column(name="City") 
	@JsonProperty("City") 
	private String city;
	
	@Column(name="State")
	@JsonProperty("State") 
	private String state;
	
	@Column(name="Zipcode") 
	@JsonProperty("Zipcode") 
	private String zipcode;
	
	@Column(name="Country") 
	@JsonProperty("Country") 
	private String country;
	
	@Column(name="CountryID")
	@JsonProperty("CountryID") 
	private Integer countryID;
	
	@Column(name="IsDefaultCard")
	@JsonProperty("IsDefaultCard")
	private Boolean defaultCard;
	
	@Column(name="CardStatusID")
	@JsonProperty("CardStatusID") 
	private Integer cardStatusID;
	
	@Column(name="IsDeleted")
	@JsonProperty("IsDeleted") 
	private Boolean deleted;
	
	@Column(name="CreatedOn") 
	@JsonProperty("CreatedOn") 
	private LocalDateTime createdOn;
	
	@Column(name="CreatedBy")
	@JsonProperty("CreatedBy")
	private String createdBy;
	
	@Column(name="ModifiedOn")
	@JsonProperty("ModifiedOn")
	private LocalDateTime modifiedOn;
	
	@Column(name="ModifiedBy") 
	@JsonProperty("ModifiedBy") 
	private String modifiedBy;
	
	@Column(name="ReferenceID2") 
	@JsonProperty("ReferenceID2") 
	private String referenceID2;
}


