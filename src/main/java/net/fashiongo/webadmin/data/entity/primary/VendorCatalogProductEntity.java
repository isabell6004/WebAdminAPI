package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "VendorCatalog_Product")
public class VendorCatalogProductEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CatalogProductID")
	private Integer catalogProductID;

	@Column(name = "CatalogID")
	private int catalogID;

	@Column(name = "ProductID")
	private int productID;

	@Column(name = "SortNo")
	private int sortNo = 12;

	@Column(name = "SendRequestID")
	private Integer sendRequestID;
}
