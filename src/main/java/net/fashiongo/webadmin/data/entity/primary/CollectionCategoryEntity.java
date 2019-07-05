package net.fashiongo.webadmin.data.entity.primary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "CollectionCategory")
public class CollectionCategoryEntity {

	@Id
	@Column(name = "CollectionCategoryID")
	@Setter(AccessLevel.NONE)
	private Integer collectionCategoryID;

	@Column(name = "CollectionCategoryName")
	private String collectionCategoryName;

	@Column(name = "ParentCollectionCategoryID")
	private Integer parentCollectionCategoryID;

	@Column(name = "SpotID")
	private Integer spotID;

	@Column(name = "Lvl")
	private Integer lvl;

	@Column(name = "ListOrder")
	private Integer listOrder;

	@Column(name = "Active")
	private Boolean active;

	@Column(name = "ServiceInUse")
	private String serviceInUse;

	@Column(name = "VendorType")
	private String vendorType;

	@Column(name = "VendorTierGroup")
	private String vendorTierGroup;

	@Column(name = "OrderBy")
	private String orderBy;

	@Column(name = "ModifiedBy")
	private String modifiedBy;

	@Column(name = "ModifiedOn")
	private LocalDateTime modifiedOn;

	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SpotID", insertable = false, updatable = false)
	private AdPageSpotEntity pageSpotEntity;
}
