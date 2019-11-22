package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Entity
@Table(name = "tblWholeSalerRating")
public class WholeSalerRatingEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "WholeSalerRatingID")
	private Integer wholeSalerRatingID;

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
