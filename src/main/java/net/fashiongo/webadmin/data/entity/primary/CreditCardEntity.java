package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Entity
@Table(name = "CreditCard")
public class CreditCardEntity {

	@Id
	@Column(name = "CreditCardID")
	private Integer creditCardID;

	@Column(name = "CCName")
	private String ccName;

	@Column(name = "retailerID")
	private int retailerID;

	@Column(name = "CardTypeID")
	private int cardTypeID;

	@Column(name = "CardNumber")
	private String cardNumber;

	@Column(name = "Last4Digit")
	private String last4Digit;

	@Column(name = "CVV")
	private String cvv;

	@Column(name = "ExpirationMonth")
	private int expirationMonth;

	@Column(name = "ExpirationYear")
	private int expirationYear;

	@Column(name = "NameOnCard")
	private String nameOnCard;

	@Column(name = "Active")
	private boolean active;

	@Column(name = "CreatedOn")
	private Timestamp createdOn;

	@Column(name = "CreatedBy")
	private String createdBy;

	@Column(name = "ModifiedOn")
	private Timestamp modifiedOn;

	@Column(name = "ModifiedBy")
	private String modifiedBy;

	@Column(name = "StreetNo")
	private String streetNo;

	@Column(name = "StreetNo2")
	private String streetNo2;

	@Column(name = "City")
	private String city;

	@Column(name = "State")
	private String state;

	@Column(name = "Zipcode")
	private String zipcode;

	@Column(name = "Country")
	private String country;

	@Column(name = "CountryID")
	private Integer countryID;

	@Column(name = "Phone")
	private String phone;

	@Column(name = "IsDefaultCard")
	private boolean isDefaultCard;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CardTypeID", referencedColumnName = "CreditCardTypeID", insertable = false, updatable = false)
	private CodeCreditCardTypeEntity codeCreditCardType;

}
