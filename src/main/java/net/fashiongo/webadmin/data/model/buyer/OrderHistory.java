package net.fashiongo.webadmin.data.model.buyer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Getter
public class OrderHistory {

	@JsonProperty("CheckoutDate")
	private LocalDateTime checkoutDate;

	@JsonProperty("CreatedBy")
	private String createdBy;

	@JsonProperty("HappenedAt")
	private Integer happenedAt;

	@JsonProperty("OrderID")
	private Integer orderID;

	@JsonProperty("OrderStatusName")
	private String orderStatusName;

	@JsonProperty("PONumber")
	private String poNumber;

	@JsonProperty("PaymentMethod")
	private String paymentMethod;

	@JsonProperty("PaymentStatusID")
	private Integer paymentStatusID;

	@JsonProperty("ReferenceTypeID")
	private Integer referenceTypeID;

	@JsonProperty("TotalAmountWOSC")
	private BigDecimal totalAmountWOSC;

	@JsonProperty("TransactionType")
	private Integer transactionType;

	@JsonProperty("WholeCompanyName")
	private String wholeCompanyName;

	public OrderHistory(Timestamp checkoutDate, String createdBy, Integer happenedAt, Integer orderID, String orderStatusName, String poNumber, String paymentMethod, Integer paymentStatusID, Integer referenceTypeID, BigDecimal totalAmountWOSC, Integer transactionType, String wholeCompanyName) {
		this.checkoutDate = Optional.ofNullable(checkoutDate).map(Timestamp::toLocalDateTime).orElse(null);
		this.createdBy = createdBy;
		this.happenedAt = happenedAt;
		this.orderID = orderID;
		this.orderStatusName = orderStatusName;
		this.poNumber = poNumber;
		this.paymentMethod = paymentMethod;
		this.paymentStatusID = paymentStatusID;
		this.referenceTypeID = referenceTypeID;
		this.totalAmountWOSC = totalAmountWOSC;
		this.transactionType = transactionType;
		this.wholeCompanyName = wholeCompanyName;
	}
}
