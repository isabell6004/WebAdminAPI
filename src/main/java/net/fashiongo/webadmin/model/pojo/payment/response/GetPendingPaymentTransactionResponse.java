package net.fashiongo.webadmin.model.pojo.payment.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import net.fashiongo.webadmin.model.pojo.payment.CreditCardStatus;
import net.fashiongo.webadmin.model.pojo.payment.OrderPayment;

/**
 * 
 * @author DAHYE
 *
 */
@Data
public class GetPendingPaymentTransactionResponse {
	@JsonProperty("Table")
	private List<CreditCardStatus> creditCardStatusList;
	
	@JsonProperty("Table1")
	private List<OrderPayment> orderPaymentStatusList;
}
