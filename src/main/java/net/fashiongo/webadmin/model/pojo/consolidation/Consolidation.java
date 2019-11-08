package net.fashiongo.webadmin.model.pojo.consolidation;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Consolidation implements Serializable {

	private static final long serialVersionUID = 1810083216052623996L;

	@JsonProperty("row")
	private Integer row;

	@JsonProperty("ConsolidationID")
	private Integer consolidationId;

	@JsonProperty("OrderSessionGUID")
	private String orderSessionGUID;

	@JsonProperty("TotalAmount")
	private Double totalAmount;

	@JsonProperty("TotalQty")
	private Integer totalQty;

	@JsonProperty("orderCount")
	private Integer orderCount;

	@JsonProperty("BuyerName")
	private String buyerName;
	
	@JsonProperty("BuyerCompanyName")
	private String buyerCompanyName;

	@JsonProperty("ShipAddressID")
	private Integer shipAddressId;

	@JsonProperty("StreetNo")
	private String streetNo;
	
	@JsonProperty("City")
	private String city;

	@JsonProperty("State")
	private String state;

	@JsonProperty("Zipcode")
	private String zipcode;

	@JsonProperty("Country")
	private String country;

	@JsonProperty("Phone")
	private String phone;
	
	@JsonProperty("Fax")
	private String fax;

	@JsonProperty("Email")
	private String email;

	@JsonProperty("ShippingCharge")
	private Double shippingCharge;

	@JsonProperty("ActualShippingCharge")
	private Double actualShippingCharge;

	@JsonProperty("ShipMethodID")
	private Integer shipMethodId;

	@JsonProperty("ShipMethodName")
	private String shipMethodName;

	@JsonProperty("TrackingNumber")
	private String trackingNumber;

	@JsonProperty("PaymentMethodID")
	private Integer paymentMethodId;
	
	@JsonProperty("PaymentMethodName")
	private String paymentMethodName;
	
	@JsonProperty("CreditCardID")
	private Integer creditCardId;

	@JsonProperty("CardType")
	private String cardType;

	@JsonProperty("CardNumber")
	private String cardNumber;

	@JsonProperty("CardExpireMonth")
	private String cardExpireMonth;

	@JsonProperty("CardExpireYear")
	private String cardExpireYear;

	@JsonProperty("CVV")
	private String cvv;

	@JsonProperty("Last4Digit")
	private String last4Digit;

	@JsonProperty("NameOnCard")
	private String nameOnCard;

	@JsonProperty("BillStreetNo")
	private String billStreetNo;
	
	@JsonProperty("BillCity")
	private String billCity;

	@JsonProperty("BillState")
	private String BillState;

	@JsonProperty("BillZipcode")
	private String billZipcode;
	
	@JsonProperty("BillCountry")
	private String billCountry;
	
	@JsonProperty("BillCountryID")
	private Integer billCountryId;

	@JsonProperty("InhouseMemo")
	private String inhouseMemo;
	
	@JsonProperty("ShippedOn")
	private LocalDateTime shippedOn;

	@JsonProperty("CreatedOn")
	private LocalDateTime createdOn;
	
	@JsonProperty("CreatedBy")
	private String createdBy;
	
	@JsonProperty("ModifiedOn")
	private LocalDateTime modifiedOn;
	
	@JsonProperty("ModifiedBy")
	private String modifiedBy;
	
	@JsonProperty("RetailerID")
	private Integer retailerId;

	@JsonProperty("IsCommercialAddress")
	private Boolean isCommercialAddress;

	@JsonProperty("SeqNo")
	private Integer seqNo;

	@JsonProperty("CountryID")
	private Integer countryId;

	@JsonProperty("CardTypeID")
	private Integer cardTypeId;

	@JsonProperty("coupon_issue_id")
	private BigInteger couponIssueId;

	@JsonProperty("coupon_amount")
	private Double couponAmount;

	@JsonProperty("applied_coupon_amount")
	private Double appliedCouponAmount;

	@JsonProperty("original_shipping_charge")
	private Double originalShippingCharge;
	
	@JsonProperty("waived_amount")
	private Double waivedAmount;

	@JsonProperty("waived_amount_by_fg")
	private Double waivedAmountByFg;

	@JsonProperty("histCnt")
	private Integer histCnt;
	
}
