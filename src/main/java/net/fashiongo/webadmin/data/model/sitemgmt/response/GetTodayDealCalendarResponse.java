package net.fashiongo.webadmin.data.model.sitemgmt.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.sitemgmt.TodayDealCalendarDetail;
import net.fashiongo.webadmin.data.model.sitemgmt.VendorSummaryDetail;

import java.util.List;

@Getter
@Builder
public class GetTodayDealCalendarResponse {

	@JsonProperty("Table")
	private List<TodayDealCalendarDetail> calendarDetails;
	@JsonProperty("Table1")
	private List<VendorSummaryDetail> vendors;
}
