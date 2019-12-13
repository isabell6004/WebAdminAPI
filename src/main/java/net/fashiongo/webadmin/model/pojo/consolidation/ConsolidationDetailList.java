package net.fashiongo.webadmin.model.pojo.consolidation;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ConsolidationDetailList implements Serializable {

	private static final long serialVersionUID = -6444629237787284061L;

	@JsonProperty("OrderID")
	@Column(name = "OrderID")
	private Integer orderId;

	@JsonProperty("OrderDate")
	@Column(name = "OrderDate")
	private LocalDateTime orderDate;

	@JsonProperty("CheckOutDate")
	@Column(name = "CheckOutDate")
	private LocalDateTime checkOutDate;

	@JsonProperty("WholeSalerID")
	@Column(name = "WholeSalerID")
	private Integer wholeSalerId;

	@JsonProperty("WholeCompanyName")
	@Column(name = "WholeCompanyName")
	private String wholeCompanyName;

	@JsonProperty("WFirstName")
	@Column(name = "WFirstName")
	private String wFirstName;
	
	@JsonProperty("WLastName")
	@Column(name = "WLastName")
	private String wLastName;

	@JsonProperty("WBillStreetNo")
	@Column(name = "WBillStreetNo")
	private String wBillStreetNo;

	@JsonProperty("WBillCity")
	@Column(name = "WBillCity")
	private String wBillCity;
	
	@JsonProperty("WBillSTATE")
	@Column(name = "WBillSTATE")
	private String wBillState;

	@JsonProperty("WBillZipcode")
	@Column(name = "WBillZipcode")
	private String wBillZipcode;

	@JsonProperty("WBillCountry")
	@Column(name = "WBillCountry")
	private String wBillCountry;

	@JsonProperty("WBillPhone")
	@Column(name = "WBillPhone")
	private String wBillPhone;

	@JsonProperty("WBillFax")
	@Column(name = "WBillFax")
	private String wBillFax;
	
	@JsonProperty("WStreetNo")
	@Column(name = "WStreetNo")
	private String wStreetNo;

	@JsonProperty("WCity")
	@Column(name = "WCity")
	private String wCity;

	@JsonProperty("WSTATE")
	@Column(name = "WSTATE")
	private String wState;

	@JsonProperty("WZipcode")
	@Column(name = "WZipcode")
	private String wZipcode;

	@JsonProperty("WCountry")
	@Column(name = "WCountry")
	private String wCountry;

	@JsonProperty("WPhone")
	@Column(name = "WPhone")	
	private String wPhone;

	@JsonProperty("WFax")
	@Column(name = "WFax")
	private String wFax;

	@JsonProperty("Email")
	@Column(name = "Email")
	private String email;
	
	@JsonProperty("RetailerID")
	@Column(name = "RetailerID")
	private Integer retailerId;
	
	@JsonProperty("RetailerCompanyName")
	@Column(name = "RetailerCompanyName")
	private String retailerCompanyName;

	@JsonProperty("FirstName")
	@Column(name = "FirstName")
	private String FirstName;

	@JsonProperty("LastName")
	@Column(name = "LastName")
	private String lastName;

	@JsonProperty("BillStreetNo")
	@Column(name = "BillStreetNo")
	private String billStreetNo;

	@JsonProperty("BillCity")
	@Column(name = "BillCity")
	private String billCity;

	@JsonProperty("BillSTATE")
	@Column(name = "BillSTATE")
	private String billState;

	@JsonProperty("BillZipcode")
	@Column(name = "BillZipcode")
	private String billZipcode;

	@JsonProperty("BillCountry")
	@Column(name = "BillCountry")
	private String billCountry;

	@JsonProperty("BillPhone")
	@Column(name = "BillPhone")
	private String billPhone;
	
	@JsonProperty("BillFax")
	@Column(name = "BillFax")
	private String billFax;

	@JsonProperty("StreetNo")
	@Column(name = "StreetNo")
	private String streetNo;

	@JsonProperty("City")
	@Column(name = "City")
	private String city;
	
	@JsonProperty("STATE")
	@Column(name = "STATE")	
	private String state;
	
	@JsonProperty("Zipcode")
	@Column(name = "Zipcode")	
	private Integer zipcode;

	@JsonProperty("Country")
	@Column(name = "Country")	
	private String country;
	
	@JsonProperty("Phone")
	@Column(name = "Phone")
	private String phone;

	@JsonProperty("Fax")
	@Column(name = "Fax")
	private String fax;
	
	@JsonProperty("REmail")
	@Column(name = "REmail")
	private String rEmail;
	
	@JsonProperty("TotalQty")
	@Column(name = "TotalQty")
	private Integer totalQty;
	
	@JsonProperty("TotalAmount")
	@Column(name = "TotalAmount")
	private BigDecimal totalAmount;
	
	@JsonProperty("Discount")
	@Column(name = "Discount")
	private BigDecimal discount;

	@JsonProperty("ShippingCharge")
	@Column(name = "ShippingCharge")
	private BigDecimal shippingCharge;

	@JsonProperty("CardNumber")
	@Column(name = "CardNumber")
	private String cardNumber;

	@JsonProperty("CardExpireMonth")
	@Column(name = "CardExpireMonth")
	private String cardExpireMonth;

	@JsonProperty("CardExpireYear")
	@Column(name = "CardExpireYear")
	private String cardExpireYear;

	@JsonProperty("PaymentMethodID")
	@Column(name = "PaymentMethodID")
	private Integer paymentMethodId;

	@JsonProperty("PaymentMethod")
	@Column(name = "PaymentMethod")
	private String paymentMethod;

	@JsonProperty("ShipMethodID")
	@Column(name = "ShipMethodID")
	private Integer shipMethodId;

	@JsonProperty("ShipMethod")
	@Column(name = "ShipMethod")
	private String shipMethod;
	
	@JsonProperty("InvoiceNo")
	@Column(name = "InvoiceNo")
	private String invoiceNo;

	@JsonProperty("PONumber")
	@Column(name = "PONumber")
	private String poNumber;

	@JsonProperty("ShipTrackNo")
	@Column(name = "ShipTrackNo")
	private String shipTrackNo;

	@JsonProperty("OrderConfirm")
	@Column(name = "OrderConfirm")
	private String orderConfirm;

	@JsonProperty("ConfirmDate")
	@Column(name = "ConfirmDate")
	private LocalDateTime confirmDate;

	@JsonProperty("ShipConfirm")
	@Column(name = "ShipConfirm")
	private String shipConfirm;

	@JsonProperty("ShipDate")
	@Column(name = "ShipDate")
	private LocalDateTime shipDate;

	@JsonProperty("Comments")
	@Column(name = "Comments")
	private String comments;

	@JsonProperty("BillStreetNo2")
	@Column(name = "BillStreetNo2")
	private String billStreetNo2;

	@JsonProperty("StreetNo2")
	@Column(name = "StreetNo2")
	private String streetNo2;

	@JsonProperty("WBillStreetNo2")
	@Column(name = "WBillStreetNo2")
	private String wBillStreetNo2;
	
	@JsonProperty("MinOrderAmountYN")
	@Column(name = "MinOrderAmountYN")
	private String minOrderAmountYn;

	@JsonProperty("MinTQYN")
	@Column(name = "MinTQYN")
	private String minTqYn;

	@JsonProperty("MinTQ")
	@Column(name = "MinTQ")
	private Integer minTq;

	@JsonProperty("Remark")
	@Column(name = "Remark")
	private String remark;

	@JsonProperty("Promotion")
	@Column(name = "Promotion")
	private String promotion;

	@JsonProperty("InsertDate")
	@Column(name = "InsertDate")
	private LocalDateTime InsertDate;

	@JsonProperty("CreditCardID")
	@Column(name = "CreditCardID")
	private Integer creditCardId;

	@JsonProperty("BuyerPONumber")
	@Column(name = "BuyerPONumber")
	private String buyerPONumber;

	@JsonProperty("CancelTypeID")
	@Column(name = "CancelTypeID")
	private Integer cancelTypeId;
	
	@JsonProperty("CancelNote")
	@Column(name = "CancelNote")
	private String cancelNote;

	@JsonProperty("CancelDate")
	@Column(name = "CancelDate")
	private LocalDateTime cancelDate;

	@JsonProperty("OrderSessionGUID")
	@Column(name = "OrderSessionGUID")
	private String orderSessionGUID;

	@JsonProperty("ShipName")
	@Column(name = "ShipName")
	private String shipName;
	
	@JsonProperty("StoreNo")
	@Column(name = "StoreNo")
	private String storeNo;

	@JsonProperty("ShipAttention")
	@Column(name = "ShipAttention")
	private String shipAttention;

	@JsonProperty("ShipAddressID")
	@Column(name = "ShipAddressID")
	private Integer shipAddressId;

	@JsonProperty("OrderStatusID")
	@Column(name = "OrderStatusID")
	private Integer orderStatusId;

	@JsonProperty("IsCancelledByBuyer")
	@Column(name = "IsCancelledByBuyer")
	private Boolean isCancelledByBuyer;

	@JsonProperty("ModifiedOn")
	@Column(name = "ModifiedOn")
	private LocalDateTime modifiedOn;

	@JsonProperty("IsCCFormSent")
	@Column(name = "IsCCFormSent")
	private Boolean isCCFormSent;

	@JsonProperty("AdditionalDiscount")
	@Column(name = "AdditionalDiscount")
	private BigDecimal additionalDiscount;

	@JsonProperty("IsCouponVerified")
	@Column(name = "IsCouponVerified")
	private Boolean isCouponVerified;

	@JsonProperty("IsFreeShipping")
	@Column(name = "IsFreeShipping")
	private Boolean isFreeShipping;

	@JsonProperty("DiscountQualified")
	@Column(name = "DiscountQualified")
	private Boolean discountQualified;

	@JsonProperty("DiscountID")
	@Column(name = "DiscountID")
	private Integer discountId;

	@JsonProperty("HandlingFee")
	@Column(name = "HandlingFee")
	private BigDecimal handlingFee;
	
	@JsonProperty("CurrentState")
	@Column(name = "CurrentState")
	private String currentState;

	@JsonProperty("NameOnCard")
	@Column(name = "NameOnCard")
	private String nameOnCard;

	@JsonProperty("CCName")
	@Column(name = "CCName")
	private String ccName;

	@JsonProperty("CardTypeID")
	@Column(name = "CardTypeID")
	private Integer cardTypeId;

	@JsonProperty("Last4Digit")
	@Column(name = "Last4Digit")
	private String last4Digit;

	@JsonProperty("CVV")
	@Column(name = "CVV")	
	private String cvv;

	@JsonProperty("CreatedByVendor")
	@Column(name = "CreatedByVendor")
	private String createdByVendor;

	@JsonProperty("IsConsolidated")
	@Column(name = "IsConsolidated")	
	private Boolean isConsolidated;

	@JsonProperty("ConsolidationID")
	@Column(name = "ConsolidationID")	
	private Integer consolidationId;

	@JsonProperty("ConsolidationShipCharge")
	@Column(name = "ConsolidationShipCharge")	
	private BigDecimal consolidationShipCharge;

	@JsonProperty("MAPISImportedOn")
	@Column(name = "MAPISImportedOn")	
	private LocalDateTime mapisImportedOn;

	@JsonProperty("CreditUsed")
	@Column(name = "CreditUsed")	
	private BigDecimal creditUsed;

	@JsonProperty("TotalAmountWOSC")
	@Column(name = "TotalAmountWOSC")	
	private BigDecimal totalAmountWOSC;

	@JsonProperty("TotalAmountWSC")
	@Column(name = "TotalAmountWSC")	
	private BigDecimal totalAmountWSC;

	@JsonProperty("ReferenceOrderID")
	@Column(name = "ReferenceOrderID")	
	private String referenceOrderId;

	@JsonProperty("BackOrderOn")
	@Column(name = "BackOrderOn")	
	private LocalDateTime backOrderOn;

	@JsonProperty("SubOrderStatusID")
	@Column(name = "SubOrderStatusID")	
	private Integer subOrderStatusId;

	@JsonProperty("ReturnOn")
	@Column(name = "ReturnOn")	
	private LocalDateTime returnOn;

	@JsonProperty("CreatedBy")
	@Column(name = "CreatedBy")	
	private Integer createdBy;

	@JsonProperty("ModifiedBy")
	@Column(name = "ModifiedBy")	
	private Integer modifiedBy;

	@JsonProperty("OrderStatusName")
	@Column(name = "OrderStatusName")	
	private Integer orderStatusName;
	
	@JsonProperty("DroppedBy")
	@Column(name = "DroppedBy")	
	private String droppedBy;

	@JsonProperty("ReceivedBy")
	@Column(name = "ReceivedBy")	
	private String receivedBy;

	@JsonProperty("ReceivedOn")
	@Column(name = "ReceivedOn")	
	private LocalDateTime receivedOn;

	@JsonProperty("NotifiedBy")
	@Column(name = "NotifiedBy")	
	private String notifiedBy;

	@JsonProperty("NotifiedOn")
	@Column(name = "NotifiedOn")	
	private LocalDateTime notifiedOn;

	@JsonProperty("PaymentStatusID")
	@Column(name = "PaymentStatusID")	
	private Integer paymentStatusId;

	@JsonProperty("PaymentStatus")
	@Column(name = "PaymentStatus")	
	private String paymentStatus;
	
}
