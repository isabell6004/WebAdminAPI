package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Entity
@Table(name = "XPack")
public class XPackEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PackID")
	private Integer packID;

	@Column(name = "WholeSalerID")
	private Integer wholeSalerID;

	@Column(name = "PackName")
	private String packName;

	@Column(name = "PackQtyTotal")
	private Integer packQtyTotal;

	@Column(name = "PackQty1")
	private Integer packQty1;

	@Column(name = "PackQty2")
	private Integer packQty2;

	@Column(name = "PackQty3")
	private Integer packQty3;

	@Column(name = "PackQty4")
	private Integer packQty4;

	@Column(name = "PackQty5")
	private Integer packQty5;

	@Column(name = "PackQty6")
	private Integer packQty6;

	@Column(name = "PackQty7")
	private Integer packQty7;

	@Column(name = "PackQty8")
	private Integer packQty8;

	@Column(name = "PackQty9")
	private Integer packQty9;

	@Column(name = "PackQty10")
	private Integer packQty10;

	@Column(name = "PackQty11")
	private Integer packQty11;

	@Column(name = "Active")
	private boolean active;

	@Column(name = "StartingDate")
	private Timestamp startingDate;

	@Column(name = "LastUser")
	private String lastUser;

	@Column(name = "LastModifiedDateTime")
	private Timestamp lastModifiedDateTime;

}
