package net.fashiongo.webadmin.data.model.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class GetPaymentAccountInfoParameter {
    @JsonProperty(value = "wid")
    private Integer wid;
}
