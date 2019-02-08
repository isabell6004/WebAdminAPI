package net.fashiongo.webadmin.model.pojo.payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 
 * @author DAHYE
 *
 */
@Data
public class OrderPayment {
	@JsonProperty("OrderPaymentStatusID") 
	private Integer orderPaymentStatusID;
	
	@JsonProperty("ReferenceID") 
	private Integer referenceID;
	
	@JsonProperty("IsOrder")
	@Column(name="IsOrder")
	private Integer order;
	
	@JsonProperty("PaymentStatusID") 
	private Integer paymentStatusID;
	
	@JsonProperty("PrePaymentStatusID") 
	private Integer prePaymentStatusID;
	
	@JsonProperty("RetailerID") 
	private Integer retailerID;
	
	@JsonProperty("CreatedOn") 
	private LocalDateTime createdOn;
	
	@JsonProperty("CreatedBy") 
	private String createdBy;
	
	@JsonProperty("ModifiedOn") 
	private Integer modifiedOn;
	
	@JsonProperty("ModifiedBy") 
	private String modifiedBy;   
	
	@JsonProperty("PONumber") 
	private String poNumber;
	
	@JsonProperty("CompanyName") 
	private String companyName;
	
	@JsonProperty("FirstName") 
	private String firstName;
	
	@JsonProperty("LastName") 
	private String lastName;
	
	@JsonProperty("TotAmt") 
	private BigDecimal totAmt;
	
	@JsonProperty("WholeSalerID") 
	private Integer wholeSalerID;
	
	@JsonProperty("WholeCompanyName") 
	private String wholeCompanyName;
	
	@JsonProperty("OrderID") 
	private Integer orderID;

	@JsonProperty("OrderSessionGUID") 
	private String orderSessionGUID;
	
	@JsonProperty("OrderStatus")
	private String orderStatus;
}
