package net.fashiongo.webadmin.data.model.statistics.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.statistics.StatReport;

import java.util.List;

@Getter
@Builder
public class GetStatReportResponse {
	@JsonProperty("Table")
	private List<StatReport> statReportList;
}
