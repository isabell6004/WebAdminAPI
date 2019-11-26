package net.fashiongo.webadmin.data.model.buyer.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.Total;
import net.fashiongo.webadmin.data.model.buyer.AdminRetailerCSV;

import java.util.List;

@Getter
@Builder
public class AdminRetailerCSVResponse {

	@JsonProperty("Table")
	private List<AdminRetailerCSV> table;
}
