package net.fashiongo.webadmin.data.entity.primary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "PolicyAgreement")
public class PolicyAgreementEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PolicyAgreementID")
	@Setter(AccessLevel.NONE)
	private Integer policyAgreementID;

	@Column(name = "PolicyID")
	private Integer policyID;

	@Column(name = "WholeSalerID")
	private Integer wholeSalerID;

	@Column(name = "CompanyName")
	private String companyName;

	@Column(name = "RetailerID")
	private Integer retailerID;

	@Column(name = "AgreedOn")
	private Timestamp agreedOn;

	@Column(name = "AgreedByName")
	private String agreedByName;

	@Column(name = "AgreedByID")
	private String agreedByID;

	@Column(name = "IPAddress")
	private String ipAddress;

	@Column(name = "Agreed")
	private boolean agreed;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PolicyID",referencedColumnName = "PolicyID",updatable = false,insertable = false)
	private PolicyEntity policy;

	public LocalDateTime getAgreedOn() {
		return agreedOn != null ? agreedOn.toLocalDateTime() : null;
	}
}
