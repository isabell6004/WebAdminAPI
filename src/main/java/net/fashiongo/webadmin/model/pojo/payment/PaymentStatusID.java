package net.fashiongo.webadmin.model.pojo.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author DAHYE
 */
@Data
public class PaymentStatusID {

    @JsonProperty("OrderPaymentStatusID")
    private Integer orderPaymentStatusID;

    @JsonProperty("PaymentStatusID")
    private Integer paymentStatusID;

    @JsonProperty("PrePaymentStatusID")
    private Integer prePaymentStatusID;

    public Integer getPaymentStatusID() {
        return this.paymentStatusID != null ? this.paymentStatusID : 1;
    }
}