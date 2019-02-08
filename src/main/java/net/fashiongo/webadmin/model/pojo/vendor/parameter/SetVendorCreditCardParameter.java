package net.fashiongo.webadmin.model.pojo.vendor.parameter;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Reo
 *
 */
public class SetVendorCreditCardParameter {
	@JsonProperty("Type")
	private String type;
	
	@JsonProperty("VendorCreditCardID")
	private String vendorCreditCardID;
	
	@JsonProperty("vendorID")
	private Integer vendorID;
	
	@JsonProperty("creditCardType")
	private Integer creditCardType;
	
	@JsonProperty("creditCard")
	private String creditCard;
	
	@JsonProperty("Attachment")
	private String attachment;
	
	@JsonProperty("Recurring")
	private Boolean recurring;
	
	@JsonProperty("Zipcode")
	private String zipcode;
	
	@JsonProperty("State")
	private String state;

	@JsonProperty("City")
	private String city;
	
	@JsonProperty("street")
	private String street;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getVendorCreditCardID() {
		return StringUtils.isEmpty(vendorCreditCardID) ? 0 : Integer.parseInt(vendorCreditCardID);
	}

	public void setVendorCreditCardID(String vendorCreditCardID) {
		this.vendorCreditCardID = vendorCreditCardID;
	}

	public Integer getVendorID() {
		return vendorID;
	}

	public void setVendorID(Integer vendorID) {
		this.vendorID = vendorID;
	}

	public Integer getCreditCardType() {
		return creditCardType;
	}

	public void setCreditCardType(Integer creditCardType) {
		this.creditCardType = creditCardType;
	}

	public String getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public Boolean getRecurring() {
		return recurring;
	}

	public void setRecurring(Boolean recurring) {
		this.recurring = recurring;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}
	
	
}
