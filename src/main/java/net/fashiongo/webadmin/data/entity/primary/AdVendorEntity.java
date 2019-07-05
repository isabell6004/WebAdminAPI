package net.fashiongo.webadmin.data.entity.primary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Ad_Vendor")
public class AdVendorEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AdID")
	@Setter(AccessLevel.NONE)
	private Integer adID;

	@Column(name = "PageID")
	private int pageID;

	@Column(name = "SpotID")
	private Integer spotID;

	@Column(name = "WholeSalerID")
	private Integer wholeSalerID;

	@Column(name = "CategoryID")
	private Integer categoryID;

	@Column(name = "BodySizeID")
	private Integer bodySizeID;

	@Column(name = "VendorCategoryID")
	private Integer vendorCategoryID;

	@Column(name = "ActualPrice")
	private BigDecimal actualPrice;

	@Column(name = "FromDate")
	private Timestamp fromDate;

	@Column(name = "ToDate")
	private Timestamp toDate;

	@Column(name = "Active")
	private boolean active;

	@Column(name = "CreatedOn")
	private Timestamp createdOn;

	@Column(name = "CreatedBy")
	private String createdBy;

	@Column(name = "ModifiedOn")
	private Timestamp modifiedOn;

	@Column(name = "ModifiedBy")
	private String modifiedBy;

	@Column(name = "IsConfirmed")
	private Boolean isConfirmed;

	@Column(name = "IsConfirmedDate")
	private Timestamp isConfirmedDate;

	@Column(name = "HowToInput")
	private Integer howToInput;

	@Column(name = "HowtoSell")
	private Integer howtoSell;

	@Column(name = "AdPageSpotListID")
	private Integer adPageSpotListID;

	@Column(name = "InvoiceOut")
	private boolean invoiceOut;

	@Column(name = "TransferredToCS")
	private Timestamp transferredToCS;

	@OneToMany(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn(name = "AdID",referencedColumnName = "AdID")
	private Set<MapAdVendorItemEntity> mapAdVendorItemEntities;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "SpotID",insertable = false,updatable = false)
	private Set<CollectionCategoryEntity> collectionCategoryEntities;

	@OneToOne
	@JoinColumn(name = "WholeSalerID",insertable = false,updatable = false)
	private SimpleWholeSalerEntity wholeSaler;

	@OneToOne
	@JoinColumn(name = "SpotID", insertable = false, updatable = false)
	private CollectionCategoryEntity collectionCategory;
}
