package net.fashiongo.webadmin.model.pojo.payment;

import java.math.BigDecimal;
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
	
}

