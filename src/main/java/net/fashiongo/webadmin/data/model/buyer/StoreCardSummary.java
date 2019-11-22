package net.fashiongo.webadmin.data.model.buyer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Getter
public class StoreCardSummary {

	@JsonProperty("WholeSalerID")
	private Integer wholeSalerID;

	@JsonProperty("CreditedAmount")
	private BigDecimal creditedAmount;

	@JsonProperty("WholeCompanyName")
	private String wholeCompanyName;

	@JsonProperty("CreatedOn")
	private LocalDateTime createdOn;

	@JsonProperty("UsedAmount")
	private BigDecimal usedAmount;

	@JsonProperty("DeletedAmount")
	private BigDecimal deletedAmount;

	public StoreCardSummary(Integer wholeSalerID, BigDecimal creditedAmount, String wholeCompanyName, Timestamp createdOn, BigDecimal usedAmount, BigDecimal deletedAmount) {
		this.wholeSalerID = wholeSalerID;
		this.creditedAmount = creditedAmount;
		this.wholeCompanyName = wholeCompanyName;
		this.createdOn = Optional.ofNullable(createdOn).map(Timestamp::toLocalDateTime).orElse(null);
		this.usedAmount = usedAmount;
		this.deletedAmount = deletedAmount;
	}
}
