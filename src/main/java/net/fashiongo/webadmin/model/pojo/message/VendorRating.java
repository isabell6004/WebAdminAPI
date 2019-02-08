package net.fashiongo.webadmin.model.pojo.message;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@Data
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
}
