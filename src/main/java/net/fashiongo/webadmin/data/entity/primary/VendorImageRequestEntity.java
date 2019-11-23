package net.fashiongo.webadmin.data.entity.primary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import net.fashiongo.webadmin.data.entity.primary.vendor.WholesalerCompanyEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Vendor_ImageRequest")
public class VendorImageRequestEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ImageRequestID")
	@Setter(AccessLevel.NONE)
	private Integer imageRequestId;

	@Column(name = "WholeSalerID")
	private int wholesalerId;

	@Column(name = "VendorImageTypeID")
	private int vendorImageTypeId;

	@Column(name = "RequestedOn")
	private LocalDateTime requestedOn;

	@Column(name = "RequestedBy")
	private String requestedBy;

	@Column(name = "IsApproved")
	private Boolean isApproved;

	@Column(name = "RejectReason")
	private String rejectReason;

	@Column(name = "DecidedOn")
	private LocalDateTime decidedOn;

	@Column(name = "DecidedBy")
	private String decidedBy;

	@Column(name = "DeletedOn")
	private LocalDateTime deletedOn;

	@Column(name = "DeletedBy")
	private String deletedBy;

	@Column(name = "Active")
	private boolean active;

	@Column(name = "OriginalFileName")
	private String originalFileName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "WholeSalerID", referencedColumnName = "WholeSalerID", insertable = false, updatable = false)
	private WholesalerCompanyEntity wholesaler;
}
