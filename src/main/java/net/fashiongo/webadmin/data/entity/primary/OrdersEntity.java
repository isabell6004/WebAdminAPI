package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Setter
@Getter
@Entity
@Table(name = "tblOrders")
@DynamicUpdate
public class OrdersEntity {

	@Id
	@Column(name = "OrderID")
	private Integer orderID;

	@Column(name = "OrderDate")
	private Timestamp orderDate;

	@Column(name = "CheckOutDate")
	private Timestamp checkOutDate;

	@Column(name = "WholeSalerID")
	private Integer wholeSalerID;

	@Column(name = "WholeCompanyName")
	private String wholeCompanyName;

	@Column(name = "WFirstName")
	private String wFirstName;

	@Column(name = "WLastName")
	private String wLastName;

	@Column(name = "WBillStreetNo")
	private String wBillStreetNo;

	@Column(name = "WBillCity")
	private String wBillCity;

	@Column(name = "WBillSTATE")
	private String wBillSTATE;

	@Column(name = "WBillZipcode")
	private String wBillZipcode;

	@Column(name = "WBillCountry")
	private String wBillCountry;

	@Column(name = "WBillPhone")
	private String wBillPhone;

	@Column(name = "WBillFax")
	private String wBillFax;

	@Column(name = "WStreetNo")
	private String wStreetNo;

	@Column(name = "WCity")
	private String wCity;

	@Column(name = "WSTATE")
	private String wSTATE;

	@Column(name = "WZipcode")
	private String wZipcode;

	@Column(name = "WCountry")
	private String wCountry;

	@Column(name = "WPhone")
	private String wPhone;

	@Column(name = "WFax")
	private String wFax;

	@Column(name = "WEmail")
	private String wEmail;

	@Column(name = "RetailerID")
	private Integer retailerID;

	@Column(name = "RetailerCompanyName")
	private String retailerCompanyName;

	@Column(name = "FirstName")
	private String firstName;

	@Column(name = "LastName")
	private String lastName;

	@Column(name = "BillStreetNo")
	private String billStreetNo;

	@Column(name = "BillCity")
	private String billCity;

	@Column(name = "BillSTATE")
	private String billSTATE;

	@Column(name = "BillZipcode")
	private String billZipcode;

	@Column(name = "BillCountry")
	private String billCountry;

	@Column(name = "BillPhone")
	private String billPhone;

	@Column(name = "BillFax")
	private String billFax;

	@Column(name = "StreetNo")
	private String streetNo;

	@Column(name = "City")
	private String city;

	@Column(name = "STATE")
	private String sTATE;

	@Column(name = "Zipcode")
	private String zipcode;

	@Column(name = "Country")
	private String country;

	@Column(name = "Phone")
	private String phone;

	@Column(name = "Fax")
	private String fax;

	@Column(name = "Email")
	private String email;

	@Column(name = "TotalQty")
	private Integer totalQty;

	@Column(name = "TotalAmount")
	private BigDecimal totalAmount;

	@Column(name = "Discount")
	private BigDecimal discount;

	@Column(name = "ShippingCharge")
	private BigDecimal shippingCharge;

	@Column(name = "CardNumber")
	private String cardNumber;

	@Column(name = "CardExpireMonth")
	private Integer cardExpireMonth;

	@Column(name = "CardExpireYear")
	private Integer cardExpireYear;

	@Column(name = "PaymentMethodID")
	private Integer paymentMethodID;

	@Column(name = "PaymentMethod")
	private String paymentMethod;

	@Column(name = "ShipMethodID")
	private Integer shipMethodID;

	@Column(name = "ShipMethod")
	private String shipMethod;

	@Column(name = "InvoiceNo")
	private String invoiceNo;

	@Column(name = "PONumber")
	private String poNumber;

	@Column(name = "ShipTrackNo")
	private String shipTrackNo;

	@Column(name = "OrderConfirm")
	private String orderConfirm;

	@Column(name = "ConfirmDate")
	private Timestamp confirmDate;

	@Column(name = "ShipConfirm")
	private String shipConfirm;

	@Column(name = "ShipDate")
	private Timestamp shipDate;

	@Column(name = "Comments")
	private String comments;

	@Column(name = "BillStreetNo2")
	private String billStreetNo2;

	@Column(name = "StreetNo2")
	private String streetNo2;

	@Column(name = "WBillStreetNo2")
	private String wBillStreetNo2;

	@Column(name = "MinOrderAmount")
	private Double minOrderAmount;

	@Column(name = "MinOrderAmountYN")
	private String minOrderAmountYN;

	@Column(name = "MinTQYN")
	private String minTQYN;

	@Column(name = "MinTQ")
	private Integer minTQ;

	@Column(name = "Remark")
	private String remark;

	@Column(name = "Promotion")
	private String promotion;

	@Column(name = "InsertDate")
	private Timestamp insertDate;

	@Column(name = "CreditCardID")
	private Integer creditCardID;

	@Column(name = "BuyerPONumber")
	private String buyerPONumber;

	@Column(name = "CancelTypeID")
	private Integer cancelTypeID;

	@Column(name = "CancelNote")
	private String cancelNote;

	@Column(name = "CancelDate")
	private Timestamp cancelDate;

	@Column(name = "OrderSessionGUID")
	private String orderSessionGUID;

	@Column(name = "ShipName")
	private String shipName;

	@Column(name = "StoreNo")
	private String storeNo;

	@Column(name = "ShipAttention")
	private String shipAttention;

	@Column(name = "ShipAddressID")
	private Integer shipAddressID;

	@Column(name = "OrderStatusID")
	private Integer orderStatusID;

	@Column(name = "IsCancelledByBuyer")
	private Boolean isCancelledByBuyer;

	@Column(name = "ModifiedOn")
	private Timestamp modifiedOn;

	@Column(name = "IsCCFormSent")
	private boolean isCCFormSent;

	@Column(name = "AdditionalDiscount")
	private BigDecimal additionalDiscount;

	@Column(name = "IsCouponVerified")
	private Boolean isCouponVerified;

	@Column(name = "IsFreeShipping")
	private boolean isFreeShipping;

	@Column(name = "DiscountQualified")
	private boolean discountQualified;

	@Column(name = "DiscountID")
	private Integer discountID;

	@Column(name = "HandlingFee")
	private BigDecimal handlingFee;

	@Column(name = "CurrentState")
	private String currentState;

	@Column(name = "NameOnCard")
	private String nameOnCard;

	@Column(name = "CCName")
	private String ccName;

	@Column(name = "CardTypeID")
	private Integer cardTypeID;

	@Column(name = "Last4Digit")
	private String last4Digit;

	@Column(name = "CVV")
	private String cvv;

	@Column(name = "CreatedByVendor")
	private Boolean createdByVendor;

	@Column(name = "IsConsolidated")
	private Boolean isConsolidated;

	@Column(name = "ConsolidationID")
	private Integer consolidationID;

	@Column(name = "ConsolidationShipCharge")
	private BigDecimal consolidationShipCharge;

	@Column(name = "MAPISImportedOn")
	private Timestamp mapISImportedOn;

	@Column(name = "CreditUsed")
	private BigDecimal creditUsed;

	@Column(name = "ReferenceOrderID")
	private Integer referenceOrderID;

	@Column(name = "BackOrderOn")
	private Timestamp backOrderOn;

	@Column(name = "SubOrderStatusID")
	private Integer subOrderStatusID;

	@Column(name = "ReturnOn")
	private Timestamp returnOn;

	@Column(name = "CreatedBy")
	private String createdBy;

	@Column(name = "ModifiedBy")
	private String modifiedBy;

	@Column(name = "OriginalOrderDate")
	private Timestamp originalOrderDate;

	@Column(name = "SessionID")
	private String sessionID;

	@Column(name = "StatusChange")
	private boolean statusChange=true;

	@Column(name = "NeedBackOrderConfirm")
	private Boolean needBackOrderConfirm;

	@Column(name = "ShowID")
	private Integer showID;

	@Column(name = "ShowScheduleID")
	private Integer showScheduleID;

	@Column(name = "isInsurance")
	private Boolean isInsurance;

	@Column(name = "isSignature")
	private Boolean isSignature;

	@Column(name = "coupon_issue_id")
	private Long coupon_issue_id;

	@Column(name = "coupon_amount")
	private BigDecimal coupon_amount;

	@Column(name = "TotalAmountWSC")
	private BigDecimal totalAmountWSC;

	@Column(name = "TotalAmountWOSC")
	private BigDecimal totalAmountWOSC;
}
