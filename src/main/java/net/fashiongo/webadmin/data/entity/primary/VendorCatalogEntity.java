package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Entity
@Table(name = "VendorCatalog")
public class VendorCatalogEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CatalogID")
	private Integer catalogID;

	@Column(name = "VendorID")
	private int vendorID;

	@Column(name = "CatalogName")
	private String catalogName;

	@Column(name = "CreatedOn")
	private Timestamp createdOn;

	@Column(name = "ModifiedOn")
	private Timestamp modifiedOn;
}
