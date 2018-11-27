package net.fashiongo.webadmin.model.pojo.payment.parameter;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import net.fashiongo.webadmin.model.pojo.payment.PaymentStatusID;
/**
 * 
 * @author DAHYE
 *
 */
@Data
public class SetRestorePendingPaymentTransactionParameter extends GetPendingPaymentTransactionParameter {
	@JsonProperty("obj")
	private List<PaymentStatusID> paymentStatusID;
}
