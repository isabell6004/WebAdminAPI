package net.fashiongo.webadmin.data.model.payment.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.payment.OrderPayment;
import net.fashiongo.webadmin.data.model.payment.PaymentCreditCardInfo;

import java.util.List;

@Getter
@Builder
public class GetPendingPaymentTransactionResponse {
    @JsonProperty("Table")
    private List<PaymentCreditCardInfo> creditCardStatusList;

    @JsonProperty("Table1")
    private List<OrderPayment> orderPaymentStatusList;
}
