package net.fashiongo.webadmin.data.model.buyer;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

public class StoreCardDetail {

	@JsonProperty("CreditID")
	private Integer creditID;

	@JsonProperty("WholeSalerID")
	private Integer wholeSalerID;

	@JsonProperty("WholeCompanyName")
	private String wholeCompanyName;

	@JsonProperty("RetailerID")
	private Integer rholeSalerID;

	@JsonProperty("CreditedAmount")
	private BigDecimal creditedAmount;

	@JsonProperty("CreditReason")
	private String creditReason;

	@JsonProperty("CreatedOn")
	private LocalDateTime createdOn;

	@JsonProperty("CreditedBy")
	private String creditedBy;

	@JsonProperty("UsedAmount")
	private BigDecimal usedAmount;

	@JsonProperty("IsUsed")
	private Boolean isUsed;

	@JsonProperty("IsDeleted")
	private Boolean isDeleted;

	public StoreCardDetail(Integer creditID, Integer wholeSalerID, String wholeCompanyName, Integer rholeSalerID, BigDecimal creditedAmount, String creditReason, Timestamp createdOn, String creditedBy, BigDecimal usedAmount, Boolean isUsed, Boolean isDeleted) {
		this.creditID = creditID;
		this.wholeSalerID = wholeSalerID;
		this.wholeCompanyName = wholeCompanyName;
		this.rholeSalerID = rholeSalerID;
		this.creditedAmount = creditedAmount;
		this.creditReason = creditReason;
		this.createdOn = Optional.ofNullable(createdOn).map(Timestamp::toLocalDateTime).orElse(null);
		this.creditedBy = creditedBy;
		this.usedAmount = usedAmount;
		this.isUsed = isUsed;
		this.isDeleted = isDeleted;
	}

	public Integer getCreditID() {
		return creditID;
	}

	public Integer getWholeSalerID() {
		return wholeSalerID;
	}

	public String getWholeCompanyName() {
		return wholeCompanyName;
	}

	public Integer getRholeSalerID() {
		return rholeSalerID;
	}

	public BigDecimal getCreditedAmount() {
		return creditedAmount;
	}

	public String getCreditReason() {
		return creditReason;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public String getCreditedBy() {
		return creditedBy;
	}

	public BigDecimal getUsedAmount() {
		return usedAmount;
	}

	public Boolean getIsUsed() {
		return isUsed;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}
}
