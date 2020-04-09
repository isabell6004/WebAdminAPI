package net.fashiongo.webadmin.model.pojo.payment.parameter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import net.fashiongo.webadmin.utility.LocalDateTimeDeserializer;
import net.fashiongo.webadmin.utility.LocalDateTimeSerializer;
import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
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


//    public LocalDateTime getPaymentDate() {
//        if (StringUtils.isNotEmpty(paymentDate)) {
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy HH:mm:ss");
//            return LocalDateTime.parse(paymentDate, formatter);
//        }
//        return null;
//    }

	@JsonProperty("Username")
	private String username;    
    
}
