package net.fashiongo.webadmin.model.pojo.vendor;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
/**
 * 
 * @author DAHYE
 *
 */
@Data
public class VendorCreditCardList implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("VendorCreditCardID")
	private Integer vendorCreditCardID;

	@JsonProperty("WholeSalerID")
	private Integer wholeSalerID;

	@JsonProperty("CompanyName")
	private String companyName;

	@JsonProperty("WholeSalerIDGuid")
	private String wholeSalerIDGuid;

	@JsonProperty("CardTypeID")
	private Integer cardTypeID;

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
	private LocalDateTime modifiedOn;

	@JsonProperty("ModifiedBy")
	private String modifiedBy;

	@JsonProperty("Attachment")
	private String attachment;

	@JsonProperty("IsRecurring")
	private Boolean IsRecurring;
	
}
