package net.fashiongo.webadmin.data.entity.primary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Setter
@Getter
@Entity
@Table(name = "Log_Communication")
@DynamicUpdate
public class LogCommunicationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CommunicationID")
	@Setter(AccessLevel.NONE)
	private Integer communicationID;

	@Column(name = "RetailerID")
	private Integer retailerID;

	@Column(name = "CompanyName")
	private String companyName;

	@Column(name = "ContactedBy")
	private String contactedBy;

	@Column(name = "ReasonID")
	private Integer reasonID;

	@Column(name = "Notes")
	private String notes;

	@Column(name = "CommunicatedOn")
	private Timestamp communicatedOn;

	@Column(name = "CheckedBy")
	private String checkedBy;

	@Column(name = "CheckedOn")
	private Timestamp checkedOn;

	@Column(name = "CheckedBy2")
	private String checkedBy2;

	@Column(name = "CheckedOn2")
	private Timestamp checkedOn2;

	@Column(name = "CreatedOn")
	private Timestamp createdOn;

	@Column(name = "CreatedBy")
	private String createdBy;

	@Column(name = "ModifiedOn")
	private Timestamp modifiedOn;

	@Column(name = "ModifiedBy")
	private String modifiedBy;

	@Column(name = "IsForVendor")
	private Boolean isForVendor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ReasonID", referencedColumnName = "ReasonID", insertable = false, updatable = false)
	private ListCommunicationReasonEntity listCommunicationReason;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RetailerID", referencedColumnName = "RetailerID", insertable = false, updatable = false)
	private RetailerEntity retailer;

	public LocalDateTime getCommunicatedOn() {
		return Optional.ofNullable(communicatedOn).map(Timestamp::toLocalDateTime).orElse(null);
	}

	public LocalDateTime getCheckedOn() {
		return Optional.ofNullable(checkedOn).map(Timestamp::toLocalDateTime).orElse(null);
	}

	public LocalDateTime getCheckedOn2() {
		return Optional.ofNullable(checkedOn2).map(Timestamp::toLocalDateTime).orElse(null);
	}

	public LocalDateTime getCreatedOn() {
		return Optional.ofNullable(createdOn).map(Timestamp::toLocalDateTime).orElse(null);
	}

	public LocalDateTime getModifiedOn() {
		return Optional.ofNullable(modifiedOn).map(Timestamp::toLocalDateTime).orElse(null);
	}
}
