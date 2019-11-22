package net.fashiongo.webadmin.data.entity.primary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Entity
@Setter
@Table(name = "Vendor_Contract_Document")
public class VendorContractDocumentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)
	@Column(name = "VendorContractDocumentID")
	private Integer vendorContractDocumentID;

	@Column(name = "VendorContractID")
	private int vendorContractID;

	@Column(name = "DocumentTypeID")
	private Integer documentTypeID;

	@Column(name = "FileName")
	private String fileName;

	@Column(name = "Note")
	private String note;

	@Column(name = "ReceivedBy")
	private String receivedBy;

	@Column(name = "CreatedOn")
	private Timestamp createdOn;

	@Column(name = "CreatedBy")
	private String createdBy;

	@Column(name = "FileName2")
	private String fileName2;

	@Column(name = "FileName3")
	private String fileName3;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VendorContractID", referencedColumnName = "VendorContractID", insertable = false, updatable = false)
	private VendorContractEntity vendorContract;
}
