package net.fashiongo.webadmin.data.model.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Getter
public class VendorRating {

	@JsonProperty("row")
	private long row;

	@JsonProperty("PONumber")
	private String poNumber;

	@JsonProperty("WholeSalerCompanyName")
	private String wholeSalerCompanyName;

	@JsonProperty("RetailerCompanyName")
	private String retailerCompanyName;

	@JsonProperty("RetailerFullName")
	private String retailerFullName;

	@JsonProperty("Response")
	private String response;

	@JsonProperty("WholeSalerRatingID")
	private Integer wholeSalerRatingID;

	@JsonProperty("OrderID")
	private Integer orderID;

	@JsonProperty("WholeSalerID")
	private Integer wholeSalerID;

	@JsonProperty("RetailerID")
	private Integer retailerID;

	@JsonProperty("Comment")
	private String comment;

	@JsonProperty("Score")
	private Integer score;

	@JsonProperty("Active")
	private Boolean active;

	@JsonProperty("CreatedOn")
	private LocalDateTime createdOn;

	public VendorRating(long row, String poNumber, String wholeSalerCompanyName, String retailerCompanyName, String retailerFullName, String response, Integer wholeSalerRatingID, Integer orderID, Integer wholeSalerID, Integer retailerID, String comment, Integer score, Boolean active, Timestamp createdOn) {
		this.row = row;
		this.poNumber = poNumber;
		this.wholeSalerCompanyName = wholeSalerCompanyName;
		this.retailerCompanyName = retailerCompanyName;
		this.retailerFullName = retailerFullName;
		this.response = response;
		this.wholeSalerRatingID = wholeSalerRatingID;
		this.orderID = orderID;
		this.wholeSalerID = wholeSalerID;
		this.retailerID = retailerID;
		this.comment = comment;
		this.score = score;
		this.active = active;
		this.createdOn = Optional.ofNullable(createdOn).map(Timestamp::toLocalDateTime).orElse(null);
	}
}
