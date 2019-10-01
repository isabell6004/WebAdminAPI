package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Entity
@Table(name = "VendorCatalog_SendRequest")
public class VendorCatalogSendRequestEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CatalogSendRequestID")
	private Integer catalogSendRequestID;

	@Column(name = "RequestedCatalogID")
	private int requestedCatalogID;

	@Column(name = "CatalogSendQueueID")
	private Integer catalogSendQueueID;

	@Column(name = "FGCatalogID")
	private Integer fgCatalogID;

	@Column(name = "CatalogSortNo")
	private int catalogSortNo = 4;

	@Column(name = "CreatedOn")
	private Timestamp createdOn;

	@Column(name = "ModifiedOn")
	private Timestamp modifiedOn;

	@Column(name = "Active")
	private boolean active = true;
}
