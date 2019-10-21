package net.fashiongo.webadmin.data.model.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Getter
public class RetailerRating {

	@JsonProperty("row")
	private long row;

	@JsonProperty("RetailerRatingID")
	private Integer retailerRatingID;

	@JsonProperty("OrderID")
	private Integer orderID;

	@JsonProperty("WholeSalerID")
	private Integer wholeSalerID;

	@JsonProperty("retailerid")
	private Integer retailerid;

	@JsonProperty("Comment")
	private String comment;

	@JsonProperty("Score")
	private Integer score;

	@JsonProperty("Active")
	private Boolean active;

	@JsonProperty("CreatedOn")
	private LocalDateTime createdOn;

	@JsonProperty("WholeSalerCompanyName")
	private String wholeSalerCompanyName;

	@JsonProperty("RetailerCompanyName")
	private String retailerCompanyName;

	@JsonProperty("RetailerFullName")
	private String retailerFullName;

	@JsonProperty("PONumber")
	private String poNumber;

	@JsonProperty("CheckOutDate")
	private LocalDateTime checkOutDate;

	@JsonProperty("Response")
	private String response;

	@JsonProperty("RatingCommentID")
	private Integer ratingCommentID;

	public RetailerRating(long row, Integer retailerRatingID, Integer orderID, Integer wholeSalerID, Integer retailerid, String comment, Integer score, Boolean active, Timestamp createdOn, String wholeSalerCompanyName, String retailerCompanyName, String retailerFullName, String poNumber, Timestamp checkOutDate, String response, Integer ratingCommentID) {
		this.row = row;
		this.retailerRatingID = retailerRatingID;
		this.orderID = orderID;
		this.wholeSalerID = wholeSalerID;
		this.retailerid = retailerid;
		this.comment = comment;
		this.score = score;
		this.active = active;
		this.createdOn = Optional.ofNullable(createdOn).map(Timestamp::toLocalDateTime).orElse(null);
		this.wholeSalerCompanyName = wholeSalerCompanyName;
		this.retailerCompanyName = retailerCompanyName;
		this.retailerFullName = retailerFullName;
		this.poNumber = poNumber;
		this.checkOutDate = Optional.ofNullable(checkOutDate).map(Timestamp::toLocalDateTime).orElse(null);
		this.response = response;
		this.ratingCommentID = ratingCommentID;
	}
}
