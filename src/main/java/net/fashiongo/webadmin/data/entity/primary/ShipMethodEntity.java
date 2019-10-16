package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Entity
@Table(name = "tblShipMethod")
public class ShipMethodEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ShipMethodID")
	private Integer shipMethodID;

	@Column(name = "CourierID",nullable = true)
	private Integer courierID;

	@Column(name = "ShipMethodName",nullable = true)
	private String shipMethodName;

	@Column(name = "StartingDate",nullable = true)
	private Timestamp startingDate;

	@Column(name = "Active",nullable = true)
	private Boolean active;

	@Column(name = "LastUser",nullable = true)
	private String lastUser;

	@Column(name = "LastModifiedDateTime",nullable = true)
	private Timestamp lastModifiedDateTime;
}
