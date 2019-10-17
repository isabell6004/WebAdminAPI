package net.fashiongo.webadmin.data.model.buyer.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.buyer.StoreCardDetail;
import net.fashiongo.webadmin.data.model.buyer.StoreCardSummary;

import java.util.List;

@Getter
@Builder
public class GetStoreCreditResponse {

	@JsonProperty("table")
	List<StoreCardSummary> table;

	@JsonProperty("table1")
	List<StoreCardDetail> table1;
}
