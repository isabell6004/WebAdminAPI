package net.fashiongo.webadmin.data.model.buyer.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.Total;
import net.fashiongo.webadmin.data.model.buyer.InaccessibleVendor;
import net.fashiongo.webadmin.data.model.buyer.SavedSearch;

import java.util.List;

@Builder
@Getter
public class SavedListResponse {

	@JsonProperty("Table")
	private List<Total> table;

	@JsonProperty("Table1")
	List<SavedSearch> table1;
}
