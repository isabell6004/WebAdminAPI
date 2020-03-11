package net.fashiongo.webadmin.data.model.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class CodeCreditCardType {
    @JsonProperty("CreditCardTypeID")
    private Integer creditCardTypeID;

    @JsonProperty("CreditCardType")
    private String creditCardType;

    public CodeCreditCardType() {
    }

    public CodeCreditCardType(Integer creditCardTypeID, String creditCardType) {
        this.creditCardTypeID = creditCardTypeID;
        this.creditCardType = creditCardType;
    }
}
