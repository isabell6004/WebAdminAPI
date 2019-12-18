package net.fashiongo.webadmin.data.model.statistics.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.statistics.HotSearch;

import java.util.List;

@Builder
@Getter
public class GetHotSearchResponse {

	@JsonProperty("Table")
	private List<HotSearch> hotSearchList;
}
