package net.fashiongo.webadmin.data.model.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SetPaymentAccountBankParameter {
    @JsonProperty(value = "wholeSalerId")
    private Integer wholeSalerId;

    @JsonProperty(value = "accountHolderName")
    private String accountHolderName;

    @JsonProperty(value = "accountNo")
    private String accountNo;

    @JsonProperty(value = "routingNo")
    private String routingNo;

    @JsonProperty(value = "modifiedBy")
    private String modifiedBy;
}
