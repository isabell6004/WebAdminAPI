package net.fashiongo.webadmin.data.model.sitemgmt.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.TotalCount;
import net.fashiongo.webadmin.data.model.sitemgmt.Item;

import java.util.List;

@Getter
@Builder
public class GetItemsResponse {

	@JsonProperty("Table")
	private List<TotalCount> totals;

	@JsonProperty("Table1")
	private List<Item> itemList;
}
