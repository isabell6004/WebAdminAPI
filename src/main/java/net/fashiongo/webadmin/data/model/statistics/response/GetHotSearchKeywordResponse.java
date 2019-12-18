package net.fashiongo.webadmin.data.model.statistics.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.statistics.HotSearchKeyword;

import java.util.List;

@Getter
@Builder
public class GetHotSearchKeywordResponse {
	@JsonProperty("Table")
	private List<HotSearchKeyword> hotSearchKeywordList;
}
