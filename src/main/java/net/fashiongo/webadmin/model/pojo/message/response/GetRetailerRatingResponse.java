package net.fashiongo.webadmin.model.pojo.message.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import net.fashiongo.webadmin.model.pojo.message.RetailerRating;
import net.fashiongo.webadmin.model.pojo.message.Total;

@Data
public class GetRetailerRatingResponse {
	@JsonProperty("Table")
	private List<Total> recCnt;
	
	@JsonProperty("Table1")
	private List<RetailerRating> retailerRatingList;
}
