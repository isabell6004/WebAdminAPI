package net.fashiongo.webadmin.data.entity.primary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Entity
@Setter
@Table(name = "Log_VendorHold")
public class LogVendorHoldEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)
	@Column(name = "LogID")
	private Integer logID;

	@Column(name = "WholeSalerID")
	private int wholeSalerID;

	@Column(name = "HoldFrom")
	private Timestamp holdFrom;

	@Column(name = "HoldTo")
	private Timestamp holdTo;

	@Column(name = "CreatedOn")
	private Timestamp createdOn;

	@Column(name = "CreatedBy")
	private String createdBy;

	@Column(name = "Active")
	private boolean active = false;
}
