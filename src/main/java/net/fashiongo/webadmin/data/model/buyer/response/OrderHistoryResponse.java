package net.fashiongo.webadmin.data.model.buyer.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import net.fashiongo.webadmin.data.model.Total;
import net.fashiongo.webadmin.data.model.buyer.OrderHistory;

import java.util.List;

@Builder
public class OrderHistoryResponse {

	@JsonProperty("Table")
	private List<Total> table;

	@JsonProperty("Table1")
	private List<OrderHistory> table1;
}
