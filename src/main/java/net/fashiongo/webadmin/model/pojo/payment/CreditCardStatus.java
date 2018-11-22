package net.fashiongo.webadmin.model.pojo.payment;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import net.fashiongo.webadmin.dao.primary.PaymentCreditCard;

/**
 * 
 * @author DAHYE
 *
 */
@Data
public class CreditCardStatus {
	@JsonProperty("CreditCardID") 
	private Integer creditCardID;
	
	@JsonProperty("RetailerID") 
	private Integer retailerID;
	
	@JsonProperty("ReferenceID")
	private String referenceID;
	
	@JsonProperty("CardTypeID")
	private Integer cardTypeID;
	
	@JsonProperty("Last4Digit")
	private String last4Digit;
	
	@JsonProperty("CVV") 
	private String cvv;
	
	@JsonProperty("ExpirationMonth") 
	private Integer expirationMonth;
	
	@JsonProperty("ExpirationYear")
	private Integer expirationYear;
	
	@JsonProperty("NameOnCard")
	private String nameOnCard;
	 
	@JsonProperty("Fingerprint") 
	private String fingerprint;
	
	@JsonProperty("Email") 
	private String email;
	
	@JsonProperty("Street") 
	private String street;
	
	@JsonProperty("City") 
	private String city;
	
	@JsonProperty("State") 
	private String state;
	
	@JsonProperty("Zipcode") 
	private String zipcode;
	
	@JsonProperty("Country") 
	private String country;
	
	@JsonProperty("CountryID") 
	private Integer countryID;
	
	@JsonProperty("IsDefaultCard")
	private Boolean defaultCard;
	
	@JsonProperty("CardStatusID") 
	private Integer cardStatusID;
	
	@JsonProperty("IsDeleted") 
	private Boolean deleted;
	
	@JsonProperty("CreatedOn") 
	private LocalDateTime createdOn;
	
	@JsonProperty("CreatedBy")
	private String createdBy;
	
	@JsonProperty("ModifiedOn")
	private LocalDateTime modifiedOn;
	
	@JsonProperty("ModifiedBy") 
	private String modifiedBy;
	
	@JsonProperty("ReferenceID2") 
	private String referenceID2;
	
	@JsonProperty("CardStatusName") 
	private String cardStatusName;

	@JsonProperty("CreditCardTypeName") 
	private String creditCardTypeName;
}
