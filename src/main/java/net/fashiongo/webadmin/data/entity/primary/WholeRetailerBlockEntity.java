package net.fashiongo.webadmin.data.entity.primary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Setter
@Getter
@Entity
@Table(name = "tblWholeRetailerBlock")
public class WholeRetailerBlockEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "WholeRetailerBlockID")
	@Setter(AccessLevel.NONE)
	private Integer wholeRetailerBlockID;

	@Column(name = "WholeSalerID")
	private Integer wholeSalerID;

	@Column(name = "RetailerID")
	private Integer retailerID;

	@Column(name = "StartingDate")
	private Timestamp startingDate;

	@Column(name = "LastUser")
	private String lastUser;

	@Column(name = "LastModifiedDateTime")
	private Timestamp lastModifiedDateTime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "WholeSalerID", referencedColumnName = "WholeSalerID", insertable = false, updatable = false)
	private SimpleWholeSalerEntity wholeSaler;

}
