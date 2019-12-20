package net.fashiongo.webadmin.model.pojo.payment.parameter;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import net.fashiongo.webadmin.model.pojo.payment.PaymentStatusID;

import java.util.List;

@Data
public class SetRestorePendingPaymentTransactionParameter extends GetPendingPaymentTransactionParameter {

    @JsonProperty("obj")
    private List<PaymentStatusID> paymentStatusID;

}
