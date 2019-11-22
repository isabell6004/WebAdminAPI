package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Entity
@Table(name = "StoreCreditUse")
public class StoreCreditUseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CreditUseID")
	private Integer creditUseID;

	@Column(name = "RetailerID")
	private int retailerID;

	@Column(name = "WholeSalerID")
	private int wholeSalerID;

	@Column(name = "UsedAmount")
	private BigDecimal usedAmount;

	@Column(name = "OrderSessionID")
	private String orderSessionID;

	@Column(name = "PONumber")
	private String poNumber;

	@Column(name = "Active")
	private boolean active;

	@Column(name = "UsedOn")
	private Timestamp usedOn;
}
