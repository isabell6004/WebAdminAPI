package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Entity
@Table(name = "tblWholeShipMethod")
public class WholeShipMethodEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "WholeShipID")
	private Integer wholeShipID;

	@Column(name = "WholeSalerID")
	private Integer wholeSalerID;

	@Column(name = "ShipMethodID")
	private Integer shipMethodID;

	@Column(name = "StartingDate")
	private Timestamp startingDate;

	@Column(name = "SortNo",nullable = false)
	private int sortNo;

	@Column(name = "IsDefault",nullable = false)
	private boolean isDefault;

	@Column(name = "Active",nullable = false)
	private boolean active =true;

	@Column(name = "LastUser")
	private String lastUser;

	@Column(name = "LastModifiedDateTime")
	private Timestamp lastModifiedDateTime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ShipMethodID",referencedColumnName = "ShipMethodID",insertable = false,updatable = false)
	private ShipMethodEntity shipMethod;
}
