package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Entity
@Table(name = "CartItem")
public class CartItemEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CartID")
	private Integer cartID;

	@Column(name = "WholeSalerID")
	private Integer wholeSalerID;

	@Column(name = "RetailerID")
	private Integer retailerID;

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

	@Column(name = "PackID")
	private Integer packID;

	@Column(name = "NoOfPack")
	private Integer noOfPack;

	@Column(name = "BQ1")
	private Integer bQ1;

	@Column(name = "BQ2")
	private Integer bQ2;

	@Column(name = "BQ3")
	private Integer bQ3;

	@Column(name = "BQ4")
	private Integer bQ4;

	@Column(name = "BQ5")
	private Integer bQ5;

	@Column(name = "BQ6")
	private Integer bQ6;

	@Column(name = "BQ7")
	private Integer bQ7;

	@Column(name = "BQ8")
	private Integer bQ8;

	@Column(name = "BQ9")
	private Integer bQ9;

	@Column(name = "BQ10")
	private Integer bQ10;

	@Column(name = "BQ11")
	private Integer bQ11;

	@Column(name = "TotalQty")
	private Integer totalQty;

	@Column(name = "UnitPrice")
	private BigDecimal unitPrice;

	@Column(name = "SubTotal")
	private BigDecimal subTotal;

	@Column(name = "TodayDealID")
	private Integer todayDealID;

	@Column(name = "StatusID")
	private int statusID;

	@Column(name = "Available")
	private boolean available;

	@Column(name = "SavedOn")
	private Timestamp savedOn;

	@Column(name = "CreatedOn")
	private Timestamp createdOn;

	@Column(name = "ModifiedOn")
	private Timestamp modifiedOn;

	@Column(name = "OrderID")
	private Integer orderID;

	@Column(name = "SessionID")
	private String sessionID;

	@Column(name = "OrderSessionGUID")
	private String orderSessionGUID;

	@Column(name = "NclickSessionID")
	private String nclickSessionID;

	@Column(name = "AvailableOn")
	private Timestamp availableOn;

	@Column(name = "InventoryStatus")
	private Integer inventoryStatus;
}
