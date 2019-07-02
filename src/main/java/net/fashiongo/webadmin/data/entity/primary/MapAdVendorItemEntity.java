package net.fashiongo.webadmin.data.entity.primary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "Map_AdVendor_Item")
public class MapAdVendorItemEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MapID")
	@Setter(AccessLevel.NONE)
	private Integer mapID;

	@Column(name = "AdID")
	private Integer adID;

	@Column(name = "ProductID")
	private Integer productID;

	@Column(name = "ListOrder")
	private Integer listOrder;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ProductID",insertable = false,updatable = false)
	private ProductsEntity products;
}
