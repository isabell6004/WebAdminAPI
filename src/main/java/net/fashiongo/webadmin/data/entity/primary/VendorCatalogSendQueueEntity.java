package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Entity
@Table(name = "VendorCatalog_SendQueue")
public class VendorCatalogSendQueueEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CatalogSendQueueID")
	private Integer catalogSendQueueID;

	@Column(name = "CatalogID")
	private int catalogID;

	@Column(name = "Subject")
	private String subject;

	@Column(name = "Contents")
	private String contents;

	@Column(name = "CreatedOn")
	private Timestamp createdOn;

	@Column(name = "ModifiedOn")
	private Timestamp modifiedOn;

	@Column(name = "ScheduledSendOn")
	private Timestamp scheduledSendOn;

	@Column(name = "SentOn")
	private Timestamp sentOn;

	@Column(name = "Active")
	private boolean active = true;

	@Column(name = "MailItemID")
	private Integer mailItemID;

	@Column(name = "IsThisToAllRetailer")
	private boolean isThisToAllRetailer;

	@Column(name = "IsTestEmail")
	private boolean isTestEmail;

	@Column(name = "IncludedVendors")
	private String includedVendors;
}
