package net.fashiongo.webadmin.data.model.buyer.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.buyer.ShoppingBag;
import net.fashiongo.webadmin.data.model.buyer.WholeSalerId;

import java.util.List;

@Getter
@Builder
public class GetShoppingBagResponse {

	@JsonProperty("Table")
	private List<WholeSalerId> table;

	@JsonProperty("Table1")
	List<ShoppingBag> table1;
}
