package net.fashiongo.webadmin.data.entity.primary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;

@Setter
@Getter
@Entity
@Table(name = "XShipAddress")
@DynamicUpdate
public class XShipAddressEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ShipAddID")
	@Setter(AccessLevel.NONE)
	private Integer ShipAddID;

	@Column(name = "CustID2")
	private int custID2;

	@Column(name = "ShipName2")
	private String shipName2;

	@Column(name = "ShipAttention")
	private String shipAttention;

	@Column(name = "ShipAddress2")
	private String shipAddress2;

	@Column(name = "ShipAddress2B")
	private String shipAddress2B;

	@Column(name = "ShipCity2")
	private String shipCity2;

	@Column(name = "ShipState2")
	private String shipState2;

	@Column(name = "ShipZip2")
	private String shipZip2;

	@Column(name = "ShipCountry2")
	private String shipCountry2;

	@Column(name = "ShipPhone")
	private String shipPhone;

	@Column(name = "ShipFax")
	private String shipFax;

	@Column(name = "StoreNo")
	private String storeNo;

	@Column(name = "DefaultYN")
	private boolean defaultYN;

	@Column(name = "Active")
	private boolean active;

	@Column(name = "StartingDate")
	private Timestamp startingDate;

	@Column(name = "LastUser")
	private String lastUser;

	@Column(name = "LastModifiedDateTime")
	private Timestamp lastModifiedDateTime;

	@Column(name = "ShipPhoneAlternate")
	private String shipPhoneAlternate;

	@Column(name = "ShipPhoneEvening")
	private String shipPhoneEvening;

	@Column(name = "IsCommercialAddress")
	private Boolean isCommercialAddress;

	@Column(name = "ShipCountry2ID")
	private Integer shipCountry2ID;

	@Column(name = "Latitude")
	private String latitude;

	@Column(name = "Longitude")
	private String longitude;

	@Column(name = "VerifiedOn")
	private Timestamp verifiedOn;
}
