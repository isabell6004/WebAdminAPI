package net.fashiongo.webadmin.service.renewal;

import net.fashiongo.common.dal.JdbcHelper;
import net.fashiongo.webadmin.data.model.statistics.HotSearch;
import net.fashiongo.webadmin.data.model.statistics.HotSearchKeyword;
import net.fashiongo.webadmin.data.model.statistics.response.GetHotSearchKeywordResponse;
import net.fashiongo.webadmin.data.model.statistics.response.GetHotSearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RenewalStaticService {

	@Autowired
	protected JdbcHelper jdbcHelper;

	private final JdbcTemplate jdbcTemplate;

	public RenewalStaticService(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public GetHotSearchResponse getHotSearch(Integer top, LocalDateTime fromDate, LocalDateTime toDate, String orderBy, String searchfield, String searchkeyword) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		List<Object> param = new ArrayList<>();
		param.add(top);
		param.add(Optional.ofNullable(fromDate).map(dateTime -> dateTime.format(dateTimeFormatter)).orElse(null));
		param.add(Optional.ofNullable(toDate).map(dateTime -> dateTime.format(dateTimeFormatter)).orElse(null));
		param.add(orderBy);
		param.add(searchfield);
		param.add(searchkeyword);

		List<Object> up_wa_getSearchQuery = jdbcHelper.executeSP("up_wa_GetSearchQuery", param, HotSearch.class);
		List<HotSearch> hotSearches = (List<HotSearch>) up_wa_getSearchQuery.get(0);

		return GetHotSearchResponse.builder()
				.hotSearchList(hotSearches)
				.build();
	}

	public GetHotSearchKeywordResponse getHotSearchKeyword(Integer periodType, LocalDateTime fromDate, LocalDateTime toDate, String keyword) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		List<Object> param = new ArrayList<>();
		param.add(periodType);
		param.add(Optional.ofNullable(fromDate).map(dateTime -> dateTime.format(dateTimeFormatter)).orElse(null));
		param.add(Optional.ofNullable(toDate).map(dateTime -> dateTime.format(dateTimeFormatter)).orElse(null));
		param.add(keyword);

		List<Object> up_wa_getSearchQuery = jdbcHelper.executeSP("up_wa_GetHotSearchKeyword", param, HotSearchKeyword.class);
		List<HotSearchKeyword> hotSearchKeywords = (List<HotSearchKeyword>) up_wa_getSearchQuery.get(0);

		return GetHotSearchKeywordResponse.builder()
				.hotSearchKeywordList(hotSearchKeywords)
				.build();
	}

	public List<Map<String, Object>> getVendorStat(LocalDateTime fromDate, LocalDateTime toDate, Integer interval, Integer wholesalerid) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		List<Object> param = new ArrayList<>();
		param.add(Optional.ofNullable(fromDate).map(dateTime -> dateTime.format(dateTimeFormatter)).orElse(null));
		param.add(Optional.ofNullable(toDate).map(dateTime -> dateTime.format(dateTimeFormatter)).orElse(null));
		param.add(interval);
		param.add(wholesalerid);

		List<Map<String, Object>> up_wa_GetVendorStatistics = this.jdbcTemplate.queryForList("exec up_wa_GetVendorStatistics ?, ?, ?, ?", param.get(0), param.get(1), param.get(2), param.get(3));

		return up_wa_GetVendorStatistics;
	}
}
