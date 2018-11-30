package net.fashiongo.webadmin.model.pojo.message;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@Data
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
}
