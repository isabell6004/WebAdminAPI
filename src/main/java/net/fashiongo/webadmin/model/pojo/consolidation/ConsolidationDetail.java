package net.fashiongo.webadmin.model.pojo.consolidation;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ConsolidationDetail implements Serializable {

	private static final long serialVersionUID = -6444629237787284061L;

	@JsonProperty("OrderID")
	private Integer orderId;

	@JsonProperty("OrderDate")
	private LocalDateTime orderDate;

	@JsonProperty("CheckOutDate")
	private LocalDateTime checkOutDate;

	@JsonProperty("WholeSalerID")
	private Integer wholeSalerId;

	@JsonProperty("WholeCompanyName")
	private String wholeCompanyName;

	@JsonProperty("WFirstName")
	private String wFirstName;
	
	@JsonProperty("WLastName")
	private String wLastName;

	@JsonProperty("WBillStreetNo")
	private String wBillStreetNo;

	@JsonProperty("WBillCity")
	private String wBillCity;
	
	@JsonProperty("WBillSTATE")
	private String wBillState;

	@JsonProperty("WBillZipcode")
	private String wBillZipcode;

	@JsonProperty("WBillCountry")
	private String wBillCountry;

	@JsonProperty("WBillPhone")
	private String wBillPhone;

	@JsonProperty("WBillFax")
	private String wBillFax;
	
	@JsonProperty("WStreetNo")
	private String wStreetNo;

	@JsonProperty("WCity")
	private String wCity;

	@JsonProperty("WSTATE")
	private String wState;

	@JsonProperty("WZipcode")
	private String wZipcode;

	@JsonProperty("WCountry")
	private String wCountry;

	@JsonProperty("WPhone")
	private String wPhone;

	@JsonProperty("WFax")
	private String wFax;

	@JsonProperty("Email")
	private String email;
	
	@JsonProperty("RetailerID")
	private Integer retailerId;
	
	@JsonProperty("RetailerCompanyName")
	private String retailerCompanyName;

	@JsonProperty("FirstName")
	private String FirstName;

	@JsonProperty("LastName")
	private String lastName;

	@JsonProperty("BillStreetNo")
	private String billStreetNo;

	@JsonProperty("BillCity")
	private String billCity;

	@JsonProperty("BillSTATE")
	private String billState;

	@JsonProperty("BillZipcode")
	private String billZipcode;

	@JsonProperty("BillCountry")
	private String billCountry;

	@JsonProperty("BillPhone")
	private String billPhone;
	
	@JsonProperty("BillFax")
	private String billFax;

	@JsonProperty("StreetNo")
	private String streetNo;

	@JsonProperty("City")
	private String city;
	
	@JsonProperty("STATE")
	private String state;
	
	@JsonProperty("Zipcode")
	private Integer zipcode;

	@JsonProperty("Country")
	private String country;
	
	@JsonProperty("Phone")
	private String phone;

	@JsonProperty("Fax")
	private String fax;
	
	@JsonProperty("REmail")
	private String rEmail;
	
	@JsonProperty("TotalQty")
	private Integer totalQty;
	
	@JsonProperty("TotalAmount")
	private Double totalAmount;
	
	@JsonProperty("Discount")
	private Double discount;

	@JsonProperty("ShippingCharge")
	private Double shippingCharge;

	@JsonProperty("CardNumber")
	private String cardNumber;

	@JsonProperty("CardExpireMonth")
	private String cardExpireMonth;

	@JsonProperty("CardExpireYear")
	private String cardExpireYear;

	@JsonProperty("PaymentMethodID")
	private Integer paymentMethodId;

	@JsonProperty("PaymentMethod")
	private String paymentMethod;

	@JsonProperty("ShipMethodID")
	private Integer shipMethodId;

	@JsonProperty("ShipMethod")
	private String shipMethod;
	
	@JsonProperty("InvoiceNo")
	private String invoiceNo;

	@JsonProperty("PONumber")
	private String poNumber;

	@JsonProperty("ShipTrackNo")
	private String shipTrackNo;

	@JsonProperty("OrderConfirm")
	private String orderConfirm;

	@JsonProperty("ConfirmDate")
	private LocalDateTime confirmDate;

	@JsonProperty("ShipConfirm")
	private String shipConfirm;

	@JsonProperty("ShipDate")
	private LocalDateTime shipDate;

	@JsonProperty("Comments")
	private String comments;

	@JsonProperty("BillStreetNo2")
	private String billStreetNo2;

	@JsonProperty("StreetNo2")
	private String streetNo2;

	@JsonProperty("WBillStreetNo2")
	private String wBillStreetNo2;
	
	@JsonProperty("MinOrderAmountYN")
	private String minOrderAmountYn;

	@JsonProperty("MinTQYN")
	private String minTqYn;

	@JsonProperty("MinTQ")
	private Integer minTq;

	@JsonProperty("Remark")
	private String remark;

	@JsonProperty("Promotion")
	private String promotion;

	@JsonProperty("InsertDate")
	private LocalDateTime InsertDate;

	@JsonProperty("CreditCardID")
	private Integer creditCardId;

	@JsonProperty("BuyerPONumber")
	private String buyerPONumber;

	@JsonProperty("CancelTypeID")
	private Integer cancelTypeId;
	
	@JsonProperty("CancelNote")
	private String cancelNote;

	@JsonProperty("CancelDate")
	private LocalDateTime cancelDate;

	@JsonProperty("OrderSessionGUID")
	private String orderSessionGUID;

	@JsonProperty("ShipName")
	private String shipName;
	
	@JsonProperty("StoreNo")
	private String storeNo;

	@JsonProperty("ShipAttention")
	private String shipAttention;

	@JsonProperty("ShipAddressID")
	private Integer shipAddressId;

	@JsonProperty("OrderStatusID")
	private Integer orderStatusId;

	@JsonProperty("IsCancelledByBuyer")
	private Boolean isCancelledByBuyer;

	@JsonProperty("ModifiedOn")
	private LocalDateTime modifiedOn;

	@JsonProperty("IsCCFormSent")
	private Boolean isCCFormSent;

	@JsonProperty("AdditionalDiscount")
	private Double additionalDiscount;

	@JsonProperty("IsCouponVerified")
	private Boolean isCouponVerified;

	@JsonProperty("IsFreeShipping")
	private Boolean isFreeShipping;

	@JsonProperty("DiscountQualified")
	private Boolean discountQualified;

	@JsonProperty("DiscountID")
	private Integer discountId;

	@JsonProperty("HandlingFee")
	private Double handlingFee;
	
	@JsonProperty("CurrentState")
	private String currentState;

	@JsonProperty("NameOnCard")
	private String nameOnCard;

	@JsonProperty("CCName")
	private String ccName;

	@JsonProperty("CardTypeID")
	private Integer cardTypeId;

	@JsonProperty("Last4Digit")
	private String last4Digit;

	@JsonProperty("CVV")
	private String cvv;

	@JsonProperty("CreatedByVendor")
	private String createdByVendor;

	@JsonProperty("IsConsolidated")
	private Boolean isConsolidated;

	@JsonProperty("ConsolidationID")
	private Integer consolidationId;

	@JsonProperty("ConsolidationShipCharge")
	private Double consolidationShipCharge;

	@JsonProperty("MAPISImportedOn")
	private LocalDateTime mapisImportedOn;

	@JsonProperty("CreditUsed")
	private Double creditUsed;

	@JsonProperty("TotalAmountWOSC")
	private Double totalAmountWOSC;

	@JsonProperty("TotalAmountWSC")
	private Double totalAmountWSC;

	@JsonProperty("ReferenceOrderID")
	private String referenceOrderId;

	@JsonProperty("BackOrderOn")
	private LocalDateTime backOrderOn;

	@JsonProperty("SubOrderStatusID")
	private Integer subOrderStatusId;

	@JsonProperty("ReturnOn")
	private LocalDateTime returnOn;

	@JsonProperty("CreatedBy")
	private Integer createdBy;

	@JsonProperty("ModifiedBy")
	private Integer modifiedBy;

	@JsonProperty("OrderStatusName")
	private Integer orderStatusName;
	
	@JsonProperty("DroppedBy")
	private String droppedBy;

	@JsonProperty("ReceivedBy")
	private String receivedBy;

	@JsonProperty("ReceivedOn")
	private LocalDateTime receivedOn;

	@JsonProperty("NotifiedBy")
	private String notifiedBy;

	@JsonProperty("NotifiedOn")
	private LocalDateTime notifiedOn;

	@JsonProperty("PaymentStatusID")
	private Integer paymentStatusId;

	@JsonProperty("PaymentStatus")
	private String paymentStatus;
	
}
