package net.fashiongo.webadmin.data.model.buyer.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.Total;
import net.fashiongo.webadmin.data.model.buyer.AdminRetailer;

import java.util.List;

@Getter
@Builder
public class AdminRetailerResponse {

	@JsonProperty("Table")
	private List<Total> table;

	@JsonProperty("Table1")
	private List<AdminRetailer> table1;
}
