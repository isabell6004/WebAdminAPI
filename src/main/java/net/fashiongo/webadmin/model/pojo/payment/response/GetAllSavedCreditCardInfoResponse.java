package net.fashiongo.webadmin.model.pojo.payment.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import net.fashiongo.webadmin.model.pojo.payment.CreditCardInfo;
import net.fashiongo.webadmin.model.pojo.payment.TotalCount;
@Data
@Deprecated
public class GetAllSavedCreditCardInfoResponse {
	@JsonProperty("Table")
	private List<CreditCardInfo> creditCardInfo;

	@JsonProperty("Table1")
	private List<TotalCount> totalList;
}
