package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Entity
@Table(name = "StoreCredit")
public class StoreCreditEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CreditID")
	private Integer creditID;

	@Column(name = "WholeSalerID")
	private int wholeSalerID;

	@Column(name = "RetailerID")
	private int retailerID;

	@Column(name = "CreditedAmount")
	private BigDecimal creditedAmount;

	@Column(name = "CreditReason")
	private String creditReason;

	@Column(name = "CreditedOn")
	private Timestamp creditedOn;

	@Column(name = "CreditedBy")
	private String creditedBy;

	@Column(name = "IsUsed")
	private Boolean isUsed;

	@Column(name = "ModifiedOn")
	private Timestamp modifiedOn;

	@Column(name = "ModifiedBy")
	private String modifiedBy;

	@Column(name = "IsDeleted")
	private Boolean isDeleted;
}
