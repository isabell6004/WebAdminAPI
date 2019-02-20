package net.fashiongo.webadmin.model.pojo.payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
/**
 * 
 * @author DAHYE
 *
 */
@Data
public class PayoutHistory {

	@JsonProperty("Amount")
	private BigDecimal amount;

	@JsonProperty("CompanyName")
	private String companyName;

	@JsonProperty("CreatedBy")
	private String createdBy;

	@JsonProperty("CreatedOn")
	private LocalDateTime createdOn;

	@JsonProperty("ETAon")
	private LocalDateTime eTAon;

	@JsonProperty("FailReason")
	private String failReason;

	@JsonProperty("modifiedBy")
	private String ModifiedBy;

	@JsonProperty("ModifiedOn")
	private LocalDateTime modifiedOn;

	@JsonProperty("PayoutSchedule")
	private String payoutSchedule;

	@JsonProperty("PayoutStatus")
	private String payoutStatus;

	@JsonProperty("ReferenceID")
	private String referenceID;

	@JsonProperty("Success")
	private Boolean success;

	@JsonProperty("VendorPayoutID")
	private Integer vendorPayoutID;

	@JsonProperty("WholeSalerID")
	private Integer wholeSalerID;

	@JsonProperty("row")
	private long row;
}
