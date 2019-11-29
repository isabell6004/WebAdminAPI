package net.fashiongo.webadmin.data.model.buyer.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.buyer.ModifiedByBuyer;

import java.util.List;

@Getter
@Builder
public class GetModifiedByBuyerResponse {

	@JsonProperty("Table")
	List<ModifiedByBuyer> modifiedByBuyerList;
}
