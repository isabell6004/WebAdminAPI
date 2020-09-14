package net.fashiongo.webadmin.data.entity.primary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "CollectionCategoryItem")
public class CollectionCategoryItemEntity {

	@Id
	@Column(name = "CollectionCategoryItemID")
	@Setter(AccessLevel.NONE)
	private Integer collectionCategoryItemID;

	@Column(name = "SpotID")
	private Integer spotID;

	@Column(name = "FromDate")
	private LocalDateTime fromDate;

	@Column(name = "CollectionCategoryID")
	private Integer collectionCategoryID;

	@Column(name = "ProductID")
	private Integer productID;

	@Column(name = "CreatedOn")
	private LocalDateTime createdOn;

	@Column(name = "CreatedBy")
	private String createdBy;

	@Column(name = "CollectionCategoryType")
	private Integer collectionCategoryType;

	@Column(name = "WholeSalerID")
	private Integer wholeSalerID;

	@OneToOne
	@JoinColumn(name = "ProductID",insertable = false,updatable = false)
	private ProductsEntity products;

	@OneToOne
	@JoinColumn(name = "WholeSalerID",referencedColumnName = "vendor_id", insertable = false,updatable = false)
	private VendorEntity wholeSaler;
}
