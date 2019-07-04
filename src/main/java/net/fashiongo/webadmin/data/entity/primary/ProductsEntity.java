package net.fashiongo.webadmin.data.entity.primary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "Products")
@Getter
@Setter
public class ProductsEntity {

	@Setter(AccessLevel.NONE)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ProductID")
	private Integer productID;

	@Column(name = "WholeSalerID")
	private int wholeSalerID;

	@Column(name = "SizeID")
	private int sizeID;

	@Column(name = "SortNo")
	private int sortNo;

	@Column(name = "PackID")
	private int packID;

	@Column(name = "ProductName")
	private String productName;

	@Column(name = "ProductName2")
	private String productName2;

	@Column(name = "ProductDescription")
	private String productDescription;

	@Column(name = "Season")
	private Integer season;

	@Column(name = "UnitPrice")
	private BigDecimal unitPrice;

	@Column(name = "UnitPrice1")
	private BigDecimal unitPrice1;

	@Column(name = "UnitPrice2")
	private BigDecimal unitPrice2;

	@Column(name = "PictureGeneral")
	private String pictureGeneral;

	@Column(name = "PictureEx01")
	private String pictureEx01;

	@Column(name = "PictureEx02")
	private String pictureEx02;

	@Column(name = "PictureEx03")
	private String pictureEx03;

	@Column(name = "PictureEx04")
	private String pictureEx04;

	@Column(name = "PictureEx05")
	private String pictureEx05;

	@Column(name = "PictureEx06")
	private String pictureEx06;

	@Column(name = "PictureEx07")
	private String pictureEx07;

	@Column(name = "PictureEx08")
	private String pictureEx08;

	@Column(name = "PictureEx09")
	private String pictureEx09;

	@Column(name = "PackQtyTotal")
	private int packQtyTotal;

	@Column(name = "PackQty1")
	private int packQty1;

	@Column(name = "PackQty2")
	private int packQty2;

	@Column(name = "PackQty3")
	private int packQty3;

	@Column(name = "PackQty4")
	private int packQty4;

	@Column(name = "PackQty5")
	private int packQty5;

	@Column(name = "PackQty6")
	private int packQty6;

	@Column(name = "PackQty7")
	private int packQty7;

	@Column(name = "PackQty8")
	private int packQty8;

	@Column(name = "PackQty9")
	private int packQty9;

	@Column(name = "PackQty10")
	private int packQty10;

	@Column(name = "PackQty11")
	private int packQty11;

	@Column(name = "PackQty12")
	private int packQty12;

	@Column(name = "PackQty13")
	private int packQty13;

	@Column(name = "PrePackYN")
	private char prePackYN = 'N';

	@Column(name = "SalesItem")
	private Boolean salesItem;

	@Column(name = "HotItems")
	private boolean hotItems = true;

	@Column(name = "PromotionalItems")
	private Boolean promotionalItems;

	@Column(name = "ItemLocation")
	private String itemLocation;

	@Column(name = "MinTQYNStyle")
	private Integer minTQYNStyle;

	@Column(name = "FirstInputDate")
	private Date firstInputDate;

	@Column(name = "EvenColorYN")
	private String evenColorYN = "N";

	@Column(name = "DiscountYN")
	private boolean discountYN = true;

	@Column(name = "PatternID")
	private Integer patternID;

	@Column(name = "LengthID")
	private Integer lengthID;

	@Column(name = "StyleID")
	private Integer styleID;

	@Column(name = "FabricID")
	private Integer fabricID;

	@Column(name = "FabricDescription")
	private String fabricDescription;

	@Column(name = "MadeIn")
	private String madeIn;

	@Column(name = "StockAvailability")
	private String stockAvailability;

	@Column(name = "BodySizeID")
	private Integer bodySizeID;

	@Column(name = "ActivatedOn")
	private Date activatedOn;

	@Column(name = "CreatedOn")
	private Date createdOn;

	@Column(name = "ModifiedOn")
	private Date modifiedOn;

	@Column(name = "Active")
	private Boolean active;

	@Column(name = "CreatedBy")
	private String createdBy;

	@Column(name = "ModifiedBy")
	private String modifiedBy;

	@Column(name = "ActivatedBy")
	private String activatedBy;

	@Column(name = "LabelTypeID")
	private Integer labelTypeID;

	@Column(name = "InventoryStatusID")
	private Integer inventoryStatusID;

	@Column(name = "VendorCategoryID")
	private Integer vendorCategoryID;

	@Column(name = "CategoryID")
	private Integer categoryID;

	@Column(name = "CallforPrice")
	private boolean callforPrice = false;

	@Column(name = "WebActive")
	private String webActive;

	@Column(name = "ActiveOld")
	private String activeOld;

	@Column(name = "StartingDate")
	private Date startingDate;

	@Column(name = "CateMasterSubSubID")
	private Integer cateMasterSubSubID;

	@Column(name = "MinTAStyle")
	private Integer minTAStyle;

	@Column(name = "MinTAYNStyle")
	private String minTAYNStyle;

	@Column(name = "FirstUpdateYN")
	private Boolean firstUpdateYN;

	@Column(name = "FirstUpdateDate")
	private Date firstUpdateDate;

	@Column(name = "MinPolicyUseYN")
	private String minPolicyUseYN;

	@Column(name = "QTYSeqYN")
	private String qTYSeqYN;

	@Column(name = "MinECQ")
	private Integer minECQ;

	@Column(name = "MinECQYN")
	private String minECQYN;

	@Column(name = "MinDollar")
	private Float minDollar;

	@Column(name = "MinDollarYN")
	private String minDollarYN;

	@Column(name = "MinTQ")
	private Integer minTQ;

	@Column(name = "MinTQYN")
	private String minTQYN;

	@Column(name = "StartingDateHo")
	private Date startingDateHo;

	@Column(name = "InActive")
	private String inActive;

	@Column(name = "CateID")
	private Integer cateID;

	@Column(name = "SearchKeyword")
	private String searchKeyword;

	@Column(name = "BrandName")
	private String brandName;

	@Column(name = "timestamp",insertable = false,updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;

	@Column(name = "AvailableOn")
	private Date availableOn;

	@Column(name = "FashionGoExclusive")
	private Boolean fashionGoExclusive;

	@Column(name = "ParentCategoryID")
	private Integer parentCategoryID;

	@Column(name = "ParentParentCategoryID")
	private Integer parentParentCategoryID;

	@Column(name = "ColorCount")
	private int colorCount = 1;

	@Column(name = "ProductMasterColors")
	private String productMasterColors;

	@Column(name = "ItemName")
	private String itemName;

	@OneToOne(optional = false)
	@JoinColumn(name = "CategoryID",insertable = false,updatable = false)
	private CategoryEntity category;

	@OneToOne(optional = false)
	@JoinColumn(name = "WholeSalerID",insertable = false,updatable = false)
	private SimpleWholeSalerEntity wholeSaler;
}
