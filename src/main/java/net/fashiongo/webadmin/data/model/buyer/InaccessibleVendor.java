package net.fashiongo.webadmin.data.model.buyer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Getter
public class InaccessibleVendor {

	@JsonProperty("Active")
	private boolean active;

	@JsonProperty("CompanyName")
	private String companyName;

	@JsonProperty("OrderActive")
	private boolean orderActive;

	@JsonProperty("RetailerID")
	private Integer retailerID;

	@JsonProperty("ShopActive")
	private boolean shopActive;

	@JsonProperty("StartingDate")
	private LocalDateTime startingDate;

	@JsonProperty("WholeRetailerBlockID")
	private Integer wholeRetailerBlockID;

	@JsonProperty("WholeSalerID")
	private Integer wholeSalerID;

	@JsonProperty("blockpolicy")
	private boolean blockpolicy;

	@JsonProperty("row")
	private Long row;

	public InaccessibleVendor(boolean active, String companyName, boolean orderActive, Integer retailerID, boolean shopActive, Timestamp startingDate, Integer wholeRetailerBlockID, Integer wholeSalerID, boolean blockpolicy, Long row) {
		this.active = active;
		this.companyName = companyName;
		this.orderActive = orderActive;
		this.retailerID = retailerID;
		this.shopActive = shopActive;
		this.startingDate = Optional.ofNullable(startingDate).map(Timestamp::toLocalDateTime).orElse(null);
		this.wholeRetailerBlockID = wholeRetailerBlockID;
		this.wholeSalerID = wholeSalerID;
		this.blockpolicy = blockpolicy;
		this.row = row;
	}
}
