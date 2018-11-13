package net.fashiongo.webadmin.model.pojo.vendor;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
/**
 * 
 * @author DAHYE
 *
 */
@Data
public class VendorCreditCard implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("VendorCreditCardID")
	private String vendorCreditCardID;

	@JsonProperty("WholeSalerID")
	private String wholeSalerID;

	@JsonProperty("CompanyName")
	private String companyName;

	@JsonProperty("WholeSalerIDGuid")
	private String wholeSalerIDGuid;

	@JsonProperty("CardTypeID")
	private String cardTypeID;

	@JsonProperty("CreditCardName")
	private String creditCardName;

	@JsonProperty("Last4Digit")
	private String last4Digit;

	@JsonProperty("Zipcode")
	private String zipcode;

	@JsonProperty("State")
	private String state;

	@JsonProperty("City")
	private String city;

	@JsonProperty("StreetNo")
	private String streetNo;

	@JsonProperty("ModifiedOn")
	private String modifiedOn;

	@JsonProperty("ModifiedBy")
	private String modifiedBy;

	@JsonProperty("Attachment")
	private String attachment;

	@JsonProperty("IsRecurring")
	private Boolean recurring;
	
}
