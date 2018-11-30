package net.fashiongo.webadmin.model.pojo.payment.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import net.fashiongo.webadmin.model.pojo.message.Total;
import net.fashiongo.webadmin.model.pojo.payment.PaymentStatusList;

/**
 * 
 * @author DAHYE
 *
 */
@Data
public class GetPaymentStatusListResponse {
	@JsonProperty("Table")
	private List<PaymentStatusList> paymentStatusList;
	
	@JsonProperty("Table1")
	private List<Total> total;
}
