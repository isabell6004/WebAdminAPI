package net.fashiongo.webadmin.data.model.payment;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendorPayoutInfoOwner {
	private Integer ownerId;
	private String firstName;
	private String lastName;
	private String last4SSN;
	private String dateOfBirth;
	private String street;
	private String city;
	private String state;
	private String country;
	private String zipcode;
	private String phoneNumber;
	private String email;
	private BigDecimal ownershipPercent;
	private String jobTitle;
	private Boolean representative;
}
