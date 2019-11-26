package net.fashiongo.webadmin.data.model.buyer;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import java.time.LocalDateTime;

public class AdminRetailerCSV {

	@Column(name = "ID")
	@JsonProperty("ID")
	private Integer id;

	@Column(name = "Company Name")
	@JsonProperty("Company Name")
	private String companyName;

	@Column(name = "First Name")
	@JsonProperty("First Name")
	private String firstName;

	@Column(name = "Last Name")
	@JsonProperty("Last Name")
	private String LastName;

	@Column(name = "Email")
	@JsonProperty("Email")
	private String email;

	@Column(name = "Phone")
	@JsonProperty("Phone")
	private String Phone;

	@Column(name = "Billing Street")
	@JsonProperty("Billing Street")
	private String billingStreet;

	@Column(name = "Billing City")
	@JsonProperty("Billing City")
	private String billingCity;

	@Column(name = "Billing State Address")
	@JsonProperty("Billing State Address")
	private String billingStateAddress;

	@Column(name = "Billing ZipCode")
	@JsonProperty("Billing ZipCode")
	private String billingZipCode;

	@Column(name = "Billing Country")
	@JsonProperty("Billing Country")
	private String billingCountry;

	@Column(name = "Shipping Street")
	@JsonProperty("Shipping Street")
	private String ShippingStreet;

	@Column(name = "Shipping City")
	@JsonProperty("Shipping City")
	private String ShippingCity;

	@Column(name = "Shipping State")
	@JsonProperty("Shipping State")
	private String ShippingState;

	@Column(name = "Shipping Zipcode")
	@JsonProperty("Shipping Zipcode")
	private String ShippingZipcode;

	@Column(name = "Shipping Country")
	@JsonProperty("Shipping Country")
	private String ShippingCountry;

	@Column(name = "Active")
	@JsonProperty("Active")
	private String active;

	@Column(name = "Current Status")
	@JsonProperty("Current Status")
	private Integer currentStatus;

	@Column(name = "Created On")
	@JsonProperty("Created On")
	private LocalDateTime createdOn;

	@Column(name = "Registration Device")
	@JsonProperty("Registration Device")
	private String RegistrationDevice;





}
