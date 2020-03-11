package net.fashiongo.webadmin.model.pojo.payment;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 
 * @author DAHYE
 *
 */
@Data
public class PaymentStatusList {
	@JsonProperty("rowno") 
	private long rowno;
	
	@JsonProperty("TransactionID") 
	private Integer transactionID;
	
	@JsonProperty("CreatedOn")
	private LocalDateTime createdOn;
	
	@JsonProperty("WholeCompanyName") 
	private String wholeCompanyName;
	
	@JsonProperty("WholeSalerID") 
	private Integer wholeSalerID;
	
	@JsonProperty("PONumber") 
	private String poNumber;
	
	@JsonProperty("OrderSessionGUID") 
	private String orderSessionGUID;
	
	@JsonProperty("RetailerCompanyName") 
	private String retailerCompanyName;
	
	@JsonProperty("RetailerID") 
	private Integer retailerID;
	
	@JsonProperty("FirstName") 
	private String firstName;
	
	@JsonProperty("LastName") 
	private String lastName;
	
	@JsonProperty("TotalAmount")
	private BigDecimal totalAmount;
	
	@JsonProperty("CardNumber")
	private String cardNumber;
	
	@JsonProperty("PaymentMethod") 
	private String paymentMethod;
	
	@JsonProperty("PaymentStatus") 
	private String paymentStatus;
	
	@JsonProperty("PaymentStatusID") 
	private Integer paymentStatusID;
	
	@JsonProperty("OrderID") 
	private Integer orderID;
	
	@JsonProperty("ConsolidationID") 
	private Integer consolidationID;
	
	@JsonProperty("MergeID") 
	private Integer mergeID;
	
	@JsonProperty("Success")
	private Boolean success;
	
	@JsonProperty("Detail") 
	private String detail;
	
	@JsonProperty("CreditCardID") 
	private Integer creditCardID;
	
	@JsonProperty("DeclineCode")
	private String declineCode;
	
	@JsonProperty("TransactionType") 
	private Integer transactionType;

	public PaymentStatusList(long rowno, Integer transactionID, Timestamp createdOn, String wholeCompanyName, Integer wholeSalerID, String poNumber, String orderSessionGUID, String retailerCompanyName, Integer retailerID, String firstName, String lastName, BigDecimal totalAmount, String cardNumber, String paymentMethod, String paymentStatus, Integer paymentStatusID, Integer orderID, Integer consolidationID, Integer mergeID, Boolean success, String detail, Integer creditCardID, String declineCode, Integer transactionType) {
		this.rowno = rowno;
		this.transactionID = transactionID;
		this.createdOn = createdOn == null ? null : createdOn.toLocalDateTime();
		this.wholeCompanyName = wholeCompanyName;
		this.wholeSalerID = wholeSalerID;
		this.poNumber = poNumber;
		this.orderSessionGUID = orderSessionGUID;
		this.retailerCompanyName = retailerCompanyName;
		this.retailerID = retailerID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.totalAmount = totalAmount;
		this.cardNumber = cardNumber;
		this.paymentMethod = paymentMethod;
		this.paymentStatus = paymentStatus;
		this.paymentStatusID = paymentStatusID;
		this.orderID = orderID;
		this.consolidationID = consolidationID;
		this.mergeID = mergeID;
		this.success = success;
		this.detail = detail;
		this.creditCardID = creditCardID;
		this.declineCode = declineCode;
		this.transactionType = transactionType;
	}
}

