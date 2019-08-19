package net.fashiongo.webadmin.data.entity.primary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "Policy")
public class PolicyEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PolicyID")
	@Setter(AccessLevel.NONE)
	private Integer policyID;

	@Column(name = "PolicyTitle")
	private String policyTitle;

	@Column(name = "PolicyContents")
	private String policyContents;

	@Column(name = "IsForVendor")
	private boolean isForVendor;

	@Column(name = "IsForRetailer")
	private boolean isForRetailer;

	@Column(name = "CreatedOn")
	private Timestamp createdOn;

	@Column(name = "EffectiveOn")
	private Timestamp effectiveOn;

	@Column(name = "ModifiedOn")
	private Timestamp modifiedOn;

	@Column(name = "CreatedBy")
	private String createdBy;

	@Column(name = "ModifiedBy")
	private String modifiedBy;

	@Column(name = "Active")
	private boolean active;
}
