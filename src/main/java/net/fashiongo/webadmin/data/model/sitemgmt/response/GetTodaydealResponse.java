package net.fashiongo.webadmin.data.model.sitemgmt.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.Total;
import net.fashiongo.webadmin.data.model.sitemgmt.TodayDealDetail;

import java.util.List;

@Builder
@Getter
public class GetTodaydealResponse {
	@JsonProperty("Table")
	private Total total;
	@JsonProperty("Table1")
	private List<TodayDealDetail> todayDealDetail;
}
