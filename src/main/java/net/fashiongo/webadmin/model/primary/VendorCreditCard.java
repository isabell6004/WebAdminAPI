package net.fashiongo.webadmin.model.primary;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "Vendor_CreditCard")
@Data
public class VendorCreditCard {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("VendorCreditCardID")
	private Integer vendorCreditCardID;

	@JsonProperty("WholeSalerID")
	private Integer wholeSalerID;

	@JsonProperty("CardTypeID")
	private Integer cardTypeID;

	@JsonProperty("Last4Digit")
	private String last4Digit;
	
	@JsonProperty("StreetNo")
	private String streetNo;

	@JsonProperty("City")
	private String city;

	@JsonProperty("State")
	private String state;

	@JsonProperty("zipcode")
	private String Zipcode;

	@JsonProperty("CreatedOn")
	private LocalDateTime createdOn;

	@JsonProperty("CreatedBy")
	private String createdBy;

	@JsonProperty("ModifiedOn")
	private LocalDateTime modifiedOn;

	@JsonProperty("ModifiedBy")
	private String modifiedBy;

	@JsonProperty("Attachment")
	private String attachment;

	@JsonProperty("IsRecurring")
	private Boolean isRecurring;
	
	

}
