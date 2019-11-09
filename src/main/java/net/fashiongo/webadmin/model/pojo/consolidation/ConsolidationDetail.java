package net.fashiongo.webadmin.model.pojo.consolidation;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ConsolidationDetail implements Serializable {

	private static final long serialVersionUID = -6444629237787284061L;

	@JsonProperty("ConsolidationID")
	@Column(name = "ConsolidationID")
	private Integer consolidationId;

	@JsonProperty("OrderSessionGUID")
	@Column(name = "OrderSessionGUID")
	private String orderSessionGUID;

	@JsonProperty("TotalAmount")
	@Column(name = "TotalAmount")	
	private BigDecimal totalAmount;

	@JsonProperty("TotalQty")
	@Column(name = "TotalQty")
	private Integer totalQty;

	@JsonProperty("orderCount")
	@Column(name = "orderCount")
	private Integer orderCount;

	@JsonProperty("BuyerName")
	@Column(name = "BuyerName")
	private String buyerName;
	
	@JsonProperty("BuyerCompanyName")
	@Column(name = "BuyerCompanyName")
	private String buyerCompanyName;

	@JsonProperty("ShipAddressID")
	@Column(name = "ShipAddressID")
	private Integer shipAddressId;

	@JsonProperty("StreetNo")
	@Column(name = "StreetNo")
	private String streetNo;
	
	@JsonProperty("City")
	@Column(name = "City")
	private String city;

	@JsonProperty("State")
	@Column(name = "State")
	private String state;

	@JsonProperty("Zipcode")
	@Column(name = "Zipcode")
	private String zipcode;

	@JsonProperty("Country")
	@Column(name = "Country")
	private String country;

	@JsonProperty("Phone")
	@Column(name = "Phone")
	private String phone;
	
	@JsonProperty("Fax")
	@Column(name = "Fax")
	private String fax;

	@JsonProperty("Email")
	@Column(name = "Email")
	private String email;

	@JsonProperty("ShippingCharge")
	@Column(name = "ShippingCharge")
	private BigDecimal shippingCharge;

	@JsonProperty("ActualShippingCharge")
	@Column(name = "ActualShippingCharge")
	private BigDecimal actualShippingCharge;

	@JsonProperty("ShipMethodID")
	@Column(name = "ShipMethodID")	
	private Integer shipMethodId;

	@JsonProperty("ShipMethodName")
	@Column(name = "ShipMethodName")	
	private String shipMethodName;

	@JsonProperty("TrackingNumber")
	@Column(name = "TrackingNumber")	
	private String trackingNumber;

	@JsonProperty("PaymentMethodID")
	@Column(name = "PaymentMethodID")	
	private Integer paymentMethodId;
	
	@JsonProperty("PaymentMethodName")
	@Column(name = "PaymentMethodName")	
	private String paymentMethodName;
	
	@JsonProperty("CreditCardID")
	@Column(name = "CreditCardID")
	private Integer creditCardId;

	@JsonProperty("CardType")
	@Column(name = "CardType")
	private String cardType;

	@JsonProperty("CardNumber")
	@Column(name = "CardNumber")
	private String cardNumber;

	@JsonProperty("CardExpireMonth")
	@Column(name = "CardExpireMonth")
	private Integer cardExpireMonth;

	@JsonProperty("CardExpireYear")
	@Column(name = "CardExpireYear")
	private Integer cardExpireYear;

	@JsonProperty("CVV")
	@Column(name = "CVV")
	private String cvv;

	@JsonProperty("Last4Digit")
	@Column(name = "Last4Digit")
	private String last4Digit;

	@JsonProperty("NameOnCard")
	@Column(name = "NameOnCard")
	private String nameOnCard;

	@JsonProperty("BillStreetNo")
	@Column(name = "BillStreetNo")
	private String billStreetNo;
	
	@JsonProperty("BillCity")
	@Column(name = "BillCity")
	private String billCity;

	@JsonProperty("BillState")
	@Column(name = "BillState")
	private String BillState;

	@JsonProperty("BillZipcode")
	@Column(name = "BillZipcode")
	private String billZipcode;
	
	@JsonProperty("BillCountry")
	@Column(name = "BillCountry")
	private String billCountry;
	
	@JsonProperty("BillCountryID")
	@Column(name = "BillCountryID")
	private Integer billCountryId;

	@JsonProperty("InhouseMemo")
	@Column(name = "InhouseMemo")
	private String inhouseMemo;
	
	@JsonProperty("ShippedOn")
	@Column(name = "ShippedOn")
	private LocalDateTime shippedOn;

	@JsonProperty("CreatedOn")
	@Column(name = "CreatedOn")
	private LocalDateTime createdOn;
	
	@JsonProperty("CreatedBy")
	@Column(name = "CreatedBy")
	private String createdBy;
	
	@JsonProperty("ModifiedOn")
	@Column(name = "ModifiedOn")
	private LocalDateTime modifiedOn;
	
	@JsonProperty("ModifiedBy")
	@Column(name = "ModifiedBy")
	private String modifiedBy;
	
	@JsonProperty("RetailerID")
	@Column(name = "RetailerID")
	private Integer retailerId;

	@JsonProperty("IsCommercialAddress")
	@Column(name = "IsCommercialAddress")
	private Boolean isCommercialAddress;

	@JsonProperty("SeqNo")
	@Column(name = "SeqNo")
	private Integer seqNo;

	@JsonProperty("CountryID")
	@Column(name = "CountryID")
	private Integer countryId;

	@JsonProperty("ShippedStatus")
	@Column(name = "ShippedStatus")
	private String ShippedStatus;

	@JsonProperty("LastConsolidOrderOn")
	@Column(name = "LastConsolidOrderOn")
	private LocalDateTime LastConsolidOrderOn;

	@JsonProperty("CardNum")
	@Column(name = "CardNum")
	private String CardNum;
	
	@JsonProperty("RetailerGUID")
	@Column(name = "RetailerGUID")
	private String RetailerGUID;
	
	@JsonProperty("ShipName")
	@Column(name = "ShipName")
	private String ShipName;

	@JsonProperty("ShipAddressVerifiedOn")
	@Column(name = "ShipAddressVerifiedOn")
	private LocalDateTime ShipAddressVerifiedOn;

	@JsonProperty("PaymentStatus")
	@Column(name = "PaymentStatus")
	private String PaymentStatus;

	@JsonProperty("coupon_amount")
	@Column(name = "coupon_amount")
	private BigDecimal couponAmount;

	@JsonProperty("original_shipping_charge")
	@Column(name = "original_shipping_charge")
	private BigDecimal originalShippingCharge;

	@JsonProperty("applied_coupon_amount")
	@Column(name = "applied_coupon_amount")
	private BigDecimal appliedCouponAmount;
	
	@JsonProperty("waived_amount")
	@Column(name = "waived_amount")
	private BigDecimal waivedAmount;

	@JsonProperty("waived_amount_by_fg")
	@Column(name = "waived_amount_by_fg")
	private BigDecimal waivedAmountByFg;
	
}
