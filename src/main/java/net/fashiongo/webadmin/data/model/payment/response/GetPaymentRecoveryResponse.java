package net.fashiongo.webadmin.data.model.payment.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.Total;
import net.fashiongo.webadmin.data.model.payment.PaymentRecoveryList;

@Getter
@Builder
public class GetPaymentRecoveryResponse {
    @JsonProperty("Table")
    private List<Total> recCnt;;

    @JsonProperty("Table1")
    private List<PaymentRecoveryList> paymentrecoverylist;
}
