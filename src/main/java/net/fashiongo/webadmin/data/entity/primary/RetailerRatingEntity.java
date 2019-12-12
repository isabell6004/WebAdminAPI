package net.fashiongo.webadmin.data.entity.primary;

import lombok.AccessLevel;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "tblRetailerRating")
@Setter
public class RetailerRatingEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RetailerRatingID")
	@Setter(AccessLevel.NONE)
	private Integer retailerRatingID;

	@Column(name = "OrderID")
	private Integer orderID;

	@Column(name = "WholeSalerID")
	private Integer wholeSalerID;

	@Column(name = "RetailerID")
	private Integer retailerID;

	@Column(name = "Comment")
	private String comment;

	@Column(name = "Score")
	private Integer score;

	@Column(name = "Active")
	private boolean active=true;

	@Column(name = "CreatedOn")
	private Timestamp createdOn;
}
