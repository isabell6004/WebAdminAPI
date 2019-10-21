package net.fashiongo.webadmin.data.model.message.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.Total;
import net.fashiongo.webadmin.data.model.message.RetailerRating;

import java.util.List;

@Builder
@Getter
public class GetRetailerRatingResponse {

	@JsonProperty("Table")
	private List<Total> recCnt;

	@JsonProperty("Table1")
	private List<RetailerRating> retailerRatingList;
}
