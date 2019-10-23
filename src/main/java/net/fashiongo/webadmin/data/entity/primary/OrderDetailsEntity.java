package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Entity
@Table(name = "tblOrderDetails")
public class OrderDetailsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OrderDetailsID")
	private Integer orderDetailsID;

	@Column(name = "OrderID")
	private Integer orderID;

	@Column(name = "OrderDate")
	private Timestamp orderDate;

	@Column(name = "ProductID")
	private Integer productID;

	@Column(name = "ProductName")
	private String productName;

	@Column(name = "SizeID")
	private Integer sizeID;

	@Column(name = "ColorID")
	private Integer colorID;

	@Column(name = "ColorName")
	private String colorName;

	@Column(name = "BQ1")
	private Integer bq1;

	@Column(name = "BQ2")
	private Integer bq2;

	@Column(name = "BQ3")
	private Integer bq3;

	@Column(name = "BQ4")
	private Integer bq4;

	@Column(name = "BQ5")
	private Integer bq5;

	@Column(name = "BQ6")
	private Integer bq6;

	@Column(name = "BQ7")
	private Integer bq7;

	@Column(name = "BQ8")
	private Integer bq8;

	@Column(name = "BQ9")
	private Integer bq9;

	@Column(name = "BQ10")
	private Integer bq10;

	@Column(name = "BQ11")
	private Integer bq11;

	@Column(name = "TotalQty")
	private Integer totalQty;

	@Column(name = "UnitPrice")
	private BigDecimal unitPrice;

	@Column(name = "SubTotal")
	private BigDecimal subTotal;

	@Column(name = "ShipDetailConfirm")
	private String shipDetailConfirm;

	@Column(name = "ShipDate")
	private Timestamp shipDate;

	@Column(name = "PackID")
	private Integer packID;

	@Column(name = "NoOfPack")
	private Integer noOfPack;

	@Column(name = "DiscountYN")
	private boolean discountYN;

	@Column(name = "DiscountAmount")
	private BigDecimal discountAmount;

	@Column(name = "DiscountTotal")
	private BigDecimal discountTotal;

	@Column(name = "TodayDealID")
	private Integer todayDealID;

	@Column(name = "OrderItemStatusID")
	private int orderItemStatusID;

	@Column(name = "BackOrderDate")
	private Timestamp backOrderDate;

	@Column(name = "ReturnDate")
	private Timestamp returnDate;

	@Column(name = "ModifiedOn")
	private Timestamp modifiedOn;

	@Column(name = "DiscountedUnitPriceAmount")
	private BigDecimal discountedUnitPriceAmount;

	@Column(name = "Available")
	private boolean available=true;

	@Column(name = "DiscountID")
	private Integer discountID;

	@Column(name = "CurrentState")
	private String currentState;

	@Column(name = "MinTQStyle")
	private Integer minTQStyle;

	@Column(name = "MinTQYNStyle")
	private String minTQYNStyle;

	@Column(name = "CateName")
	private String cateName;

	@Column(name = "CateID")
	private Integer cateID;

	@Column(name = "SavedOn")
	private Timestamp savedOn;

	@Column(name = "CartID")
	private Integer cartID;

	@Column(name = "SessionID")
	private String sessionID;

	@Column(name = "IsDeleted")
	private Boolean isDeleted;

	@Column(name = "CreatedBy")
	private String createdBy;

	@Column(name = "ModifiedBy")
	private String modifiedBy;

	@Column(name = "AvailableOn")
	private Timestamp availableOn;

	@Column(name = "PromotionPlanVendorID")
	private Integer promotionPlanVendorID;

	@Column(name = "CreatedOn")
	private Timestamp createdOn;

	@Column(name = "coupon_issue_id")
	private Long coupon_issue_id;

	@Column(name = "coupon_amount")
	private BigDecimal coupon_amount;
}
