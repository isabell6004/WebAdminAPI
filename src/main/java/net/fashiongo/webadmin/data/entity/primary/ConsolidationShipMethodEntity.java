package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "ConsolidationShipMethod")
public class ConsolidationShipMethodEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ConsolidationShipMethodID")
	private Integer consolidationShipMethodID;

	@Column(name = "ShipMethodID")
	private int shipMethodID;

	@Column(name = "ShipMethodName")
	private String shipMethodName;

	@Column(name = "CoutierID")
	private Integer coutierID;

	@Column(name = "Active")
	private boolean active;
}
