package net.fashiongo.webadmin.data.model.payment.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.TotalCount;
import net.fashiongo.webadmin.data.model.payment.CreditCardInfo;

import java.util.List;

@Getter
@Builder
public class GetAllSavedCreditCardInfoResponse {
    @JsonProperty("Table")
    private List<CreditCardInfo> creditCardInfo;

    @JsonProperty("Table1")
    private List<TotalCount> totalList;
}
