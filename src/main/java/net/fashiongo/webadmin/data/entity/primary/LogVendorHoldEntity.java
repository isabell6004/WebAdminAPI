package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Entity
@Table(name = "Log_VendorHold")
public class LogVendorHoldEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
