package net.fashiongo.webadmin.model.pojo.payment.parameter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
public class PaymentRecovery {

	@JsonProperty("ReferenceID")
	private Integer referenceID;
	
	@JsonProperty("TransactionType")
	private Integer transactionType;
	
	@JsonProperty("ReferenceTypeID")
	private Integer referenceTypeID;
	
	@JsonProperty("CreditCardReferenceID")
	private String creditCardReferenceID;
	
	@JsonProperty("PGReferenceID")
	private String pGReferenceID;	
	
	@JsonProperty("NetAmount")
	private BigDecimal netAmount;
	
	@JsonProperty("TransferAmount")
	private BigDecimal transferAmount;
	
	@JsonProperty("PaymentDate")
	private LocalDateTime paymentDate;
	
	@JsonProperty("Username")
	private String username;
	
}
