package net.fashiongo.webadmin.data.model.buyer;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

public class ModifiedByBuyer {

	@JsonProperty("RetailerID")
	private Integer retailerID;

	@JsonProperty("LastModifiedOn")
	private LocalDateTime lastModifiedOn;

	@JsonProperty("RetailerName")
	private String retailerName;

	@JsonProperty("FullName")
	private String fullName;

	public ModifiedByBuyer(Integer retailerID, Timestamp lastModifiedOn, String retailerName, String fullName) {
		this.retailerID = retailerID;
		this.lastModifiedOn = Optional.ofNullable(lastModifiedOn).map(Timestamp::toLocalDateTime).orElse(null);
		this.retailerName = retailerName;
		this.fullName = fullName;
	}
}
