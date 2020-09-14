package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Entity
@Table(name = "Merge_Orders")
public class MergeOrdersEntity {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "MergeID")
	private Integer mergeID;

	@Column(name = "MergePONumber")
	private String mergePONumber;

	@Column(name = "WholeSalerID")
	private Integer wholeSalerID;

	@Column(name = "WholeCompanyName")
	private String wholeCompanyName;

	@Column(name = "RetailerID")
	private Integer retailerID;

	@Column(name = "RetailerCompanyName")
	private String retailerCompanyName;

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

	@Column(name = "ShipTrackNo")
	private String shipTrackNo;

	@Column(name = "MergeDate")
	private Timestamp mergeDate;

	@Column(name = "TotalQty")
	private Integer totalQty;

	@Column(name = "TotalAmount")
	private BigDecimal totalAmount;

	@Column(name = "DiscountAmount")
	private BigDecimal discountAmount;

	@Column(name = "HandlingFeeAmount")
	private BigDecimal handlingFeeAmount;

	@Column(name = "StoreCreditUsedAmount")
	private BigDecimal storeCreditUsedAmount;

	@Column(name = "AdditionalDiscountAmount")
	private BigDecimal additionalDiscountAmount;

	@Column(name = "ShippingChargeAmount")
	private BigDecimal shippingChargeAmount;

	@Column(name = "CreditCardID")
	private Integer creditCardID;

	@Column(name = "NameOnCard")
	private String nameOnCard;

	@Column(name = "CardTypeID")
	private Integer cardTypeID;

	@Column(name = "Last4Digit")
	private String last4Digit;

	@Column(name = "CVV")
	private String cvv;

	@Column(name = "ShipDate")
	private Timestamp shipDate;

	@Column(name = "ShipAddressID")
	private Integer shipAddressID;

	@Column(name = "ShipStreetNo")
	private String shipStreetNo;

	@Column(name = "ShipCity")
	private String shipCity;

	@Column(name = "ShipSTATE")
	private String shipSTATE;

	@Column(name = "ShipZipcode")
	private String shipZipcode;

	@Column(name = "ShipCountry")
	private String shipCountry;

	@Column(name = "ShipCountryID")
	private Integer shipCountryID;

	@Column(name = "Phone")
	private String phone;

	@Column(name = "Fax")
	private String fax;

	@Column(name = "Email")
	private String email;

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

	@Column(name = "BillCountryID")
	private String billCountryID;

	@Column(name = "BillPhone")
	private String billPhone;

	@Column(name = "BillFax")
	private String billFax;

	@Column(name = "OrderStatusID")
	private Integer orderStatusID;

	@Column(name = "IsCCFormSent")
	private Boolean isCCFormSent;

	@Column(name = "BuyerNotes")
	private String buyerNotes;

	@Column(name = "AdditionalInfo")
	private String additionalInfo;

	@Column(name = "StaffNotes")
	private String staffNotes;

	@Column(name = "BuyerPONumber")
	private String buyerPONumber;

	@Column(name = "IsDeleted")
	private Boolean isDeleted;

	@Column(name = "CreatedOn")
	private Timestamp createdOn;

	@Column(name = "CreatedBy")
	private String createdBy;

	@Column(name = "ModifiedOn")
	private Timestamp modifiedOn;

	@Column(name = "ModifiedBy")
	private String modifiedBy;

	@Column(name = "total_coupon_amount")
	private BigDecimal total_coupon_amount;

	@Column(name = "TotalAmountWSC")
	private BigDecimal totalAmountWSC;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "WholeSalerID",referencedColumnName = "vendor_id", insertable = false,updatable = false)
	private VendorEntity wholeSaler;
}
