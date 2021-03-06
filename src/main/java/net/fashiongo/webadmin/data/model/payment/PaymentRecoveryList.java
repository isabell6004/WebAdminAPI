package net.fashiongo.webadmin.data.model.payment;

import java.math.BigDecimal;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class PaymentRecoveryList {

    @JsonProperty("TransactionFailureID")
    private Integer transactionFailureID;
    
    @JsonProperty("TransactionType")
    private Integer transactionType;
    
    @JsonProperty("ReferenceID")
    private Integer referenceID;
    
    @JsonProperty("ReferenceTypeID")
    private Integer referenceTypeID;
    
    @JsonProperty("PGReferenceID")
    private String pGReferenceID;
    
    @JsonProperty("PaymentPGReferenceID")
    private String paymentPGReferenceID;
    
    @JsonProperty("CreditCardID")
    private Integer creditCardID;
    
    @JsonProperty("CreditCardReferenceID")
    private String creditCardReferenceID;
    
    @JsonProperty("NetAmount")
    private BigDecimal netAmount;
    
    @JsonProperty("TransferAmount")
    private BigDecimal transferAmount; 

    @JsonProperty("PaymentDate")
    private LocalDateTime paymentDate;
   
    @JsonProperty("AppliedDate")
    private LocalDateTime appliedDate;
    
    @JsonProperty("BillingDate")
    private LocalDateTime billingDate;
    
    @JsonProperty("NeedtoBill")
    private Boolean needtoBill;
    
    @JsonProperty("CreatedOn")
    private LocalDateTime createdOn;
    
    @JsonProperty("CreatedBy")
    private String createdBy;   
    
    @JsonProperty("ModifiedOn")
    private LocalDateTime modifiedOn;
    
    @JsonProperty("ModifiedBy")
    private String modifiedBy;    
	
    @JsonProperty("rowno")
    private Integer rowno;    
    
    public PaymentRecoveryList(Integer transactionFailureID,Integer transactionType,Integer referenceID,Integer referenceTypeID,String pGReferenceID,String paymentPGReferenceID,Integer creditCardID,String creditCardReferenceID,BigDecimal netAmount,BigDecimal transferAmount,Timestamp paymentDate,Timestamp appliedDate,Timestamp billingDate,Boolean needtoBill,Timestamp createdOn,String createdBy,Timestamp modifiedOn,String modifiedBy,Long rowno) {
    	
    	this.transactionFailureID = transactionFailureID;
    	this.transactionType = transactionType;
    	this.referenceID = referenceID;
    	this.referenceTypeID = referenceTypeID;
    	this.pGReferenceID = pGReferenceID;
    	this.paymentPGReferenceID = paymentPGReferenceID;
    	this.creditCardID = creditCardID;
    	this.creditCardReferenceID = creditCardReferenceID;
    	this.netAmount = netAmount;
    	this.transferAmount = transferAmount;
    	this.paymentDate = paymentDate == null ? null : paymentDate.toLocalDateTime();//Optional.ofNullable(paymentDate).map(Timestamp::toLocalDateTime).orElse(null);;
    	this.appliedDate = appliedDate == null ? null : appliedDate.toLocalDateTime();//Optional.ofNullable(appliedDate).map(Timestamp::toLocalDateTime).orElse(null);;
    	this.billingDate = billingDate == null ? null : billingDate.toLocalDateTime();//Optional.ofNullable(billingDate).map(Timestamp::toLocalDateTime).orElse(null);;
    	this.needtoBill = needtoBill;
    	this.createdOn = createdOn == null ? null : createdOn.toLocalDateTime();//Optional.ofNullable(createdOn).map(Timestamp::toLocalDateTime).orElse(null);
    	this.createdBy = createdBy;
    	this.modifiedOn = modifiedOn == null ? null : modifiedOn.toLocalDateTime();//Optional.ofNullable(modifiedOn).map(Timestamp::toLocalDateTime).orElse(null);
    	this.modifiedBy = modifiedBy;
    	this.rowno = Optional.ofNullable(rowno).map(Long::intValue).orElse(null);
    }
}
