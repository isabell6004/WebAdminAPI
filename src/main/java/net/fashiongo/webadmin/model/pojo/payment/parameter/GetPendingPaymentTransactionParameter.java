package net.fashiongo.webadmin.model.pojo.payment.parameter;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class GetPendingPaymentTransactionParameter {

    @ApiModelProperty(required = false, example = "204439")
    @JsonProperty("creditcardid")
    @NotNull
    private Integer creditCardId;

}
