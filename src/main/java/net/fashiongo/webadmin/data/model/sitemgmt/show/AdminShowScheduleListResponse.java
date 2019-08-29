package net.fashiongo.webadmin.data.model.sitemgmt.show;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class AdminShowScheduleListResponse {
	@JsonProperty("Table")
	private List<ShowListCountResponse> countResponses;

	@JsonProperty("Table1")
	private List<ShowScheduleResponse> scheduleResponses;
}
