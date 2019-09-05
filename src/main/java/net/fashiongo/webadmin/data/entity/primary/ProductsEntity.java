package net.fashiongo.webadmin.data.entity.primary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
	private Integer wholeSalerID;

	@Column(name = "SizeID")
	private Integer sizeID;

	@Column(name = "SortNo")
	private Integer sortNo;

	@Column(name = "PackID")
	private Integer packID;

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
	private Integer packQtyTotal;

	@Column(name = "PackQty1")
	private Integer packQty1;

	@Column(name = "PackQty2")
	private Integer packQty2;

	@Column(name = "PackQty3")
	private Integer packQty3;

	@Column(name = "PackQty4")
	private Integer packQty4;

	@Column(name = "PackQty5")
	private Integer packQty5;

	@Column(name = "PackQty6")
	private Integer packQty6;

	@Column(name = "PackQty7")
	private Integer packQty7;

	@Column(name = "PackQty8")
	private Integer packQty8;

	@Column(name = "PackQty9")
	private Integer packQty9;

	@Column(name = "PackQty10")
	private Integer packQty10;

	@Column(name = "PackQty11")
	private Integer packQty11;

	@Column(name = "PackQty12")
	private Integer packQty12;

	@Column(name = "PackQty13")
	private Integer packQty13;

	@Column(name = "PrePackYN")
	private Character prePackYN = 'N';

	@Column(name = "SalesItem")
	private Boolean salesItem;

	@Column(name = "HotItems")
	private boolean hotItems = true;

	@Column(name = "PromotionalItems")
	private Boolean promotionalItems;

	@Column(name = "ItemLocation")
	private String itemLocation;

	@Column(name = "MinTQYNStyle")
	private String minTQYNStyle;

	@Column(name = "MinTQStyle")
	private Integer minTQStyle;

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

	@Column(name = "timestamp", insertable = false, updatable = false)
	private Byte[] timestamp;

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

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "CategoryID", insertable = false, updatable = false)
	private CategoryEntity category;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "WholeSalerID", insertable = false, updatable = false)
	private SimpleWholeSalerEntity wholeSaler;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "ProductID")
	private List<ProductImageEntity> productImageEntity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VendorCategoryID", insertable = false, updatable = false)
	private VendorCategoryEntity vendorCategoryEntity;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "ProductID")
	private List<ProductVideoEntity> productVideoEntity;

}
