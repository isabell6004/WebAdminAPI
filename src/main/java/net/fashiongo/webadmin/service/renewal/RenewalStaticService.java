package net.fashiongo.webadmin.service.renewal;

import net.fashiongo.common.dal.JdbcHelper;
import net.fashiongo.webadmin.data.model.statistics.*;
import net.fashiongo.webadmin.data.model.statistics.response.GetBestItemsResponse;
import net.fashiongo.webadmin.data.model.statistics.response.GetHotSearchKeywordResponse;
import net.fashiongo.webadmin.data.model.statistics.response.GetHotSearchResponse;
import net.fashiongo.webadmin.data.model.statistics.response.GetStatWholeSalerItemResponse;
import net.fashiongo.webadmin.data.repository.primary.ProductsEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.QuerySearchRepository;
import net.fashiongo.webadmin.data.repository.primary.StatisticsWaBestItemPerDayEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.SystemImageServersEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.SystemVendorAdminWebServerEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class RenewalStaticService {

	@Autowired
	protected JdbcHelper jdbcHelper;

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	private SystemImageServersEntityRepository systemImageServersEntityRepository;

	@Autowired
	private SystemVendorAdminWebServerEntityRepository systemVendorAdminWebServerEntityRepository;

	@Autowired
	private ProductsEntityRepository productsEntityRepository;

	@Autowired
	private StatisticsWaBestItemPerDayEntityRepository statisticsWaBestItemPerDayEntityRepository;

	@Autowired
	private QuerySearchRepository querySearchRepository;

	public RenewalStaticService(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public GetHotSearchResponse getHotSearch(Integer top, LocalDateTime fromDate, LocalDateTime toDate, String orderBy, String searchfield, String searchkeyword) {
		List<HotSearch> hotSearches = querySearchRepository.getHotSearch(top, fromDate, toDate, orderBy, searchfield, searchkeyword);

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

	public GetStatWholeSalerItemResponse getStatWholeSalerItem(Integer adminWebServerID, Integer imageServerID, String vendorName, LocalDateTime df, LocalDateTime dt) {

		List<VendorAdminWebServerUrl> table = systemVendorAdminWebServerEntityRepository.findURLGroupByWebServerIDAndAdminWebServerID();
		List<ImageServerUrl> table1 = systemImageServersEntityRepository.findImageServerURlGroupByImageServerID();
		List<AdminServerProducts> table2 = productsEntityRepository.getAdminServerProducts(adminWebServerID, imageServerID, vendorName);
		List<Long> table3 = Collections.singletonList(productsEntityRepository.getTotalItemCount(adminWebServerID, imageServerID));


		return GetStatWholeSalerItemResponse.builder()
				.vendorAdminWebServerUrl(table)
				.imageServerUrl(table1)
				.adminServerProducts(table2)
				.totalItemCount(table3)
				.build();
	}

	public Map<String, Object> getStatReport(Integer intervalType, Boolean samepoint, Integer reporttype, LocalDateTime dtStart, LocalDateTime dtEnd) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		Map<String, Object> result = new HashMap<>();

		String sql = new StringBuilder("exec up_wa_GetStatReport")
				.append(" ")
				.append(intervalType)
				.append(",")
				.append(samepoint)
				.append(",")
				.append("'")
				.append(Optional.ofNullable(dtStart).map(dateTime -> dateTime.format(dateTimeFormatter)).orElse(null))
				.append("'")
				.append(",")
				.append("'")
				.append(Optional.ofNullable(dtEnd).map(dateTime -> dateTime.format(dateTimeFormatter)).orElse(null))
				.append("'")
				.append(",")
				.append(reporttype)
				.toString();

		List<List<Map<String, Object>>> lists = executeProcedure(sql);

		final int[] tableCount = {0};

		lists.stream().forEach(maps -> {
			if(tableCount[0] == 0) {
				result.put("Table",maps);
			} else {
				result.put("Table" + tableCount[0],maps);
			}
			tableCount[0]++;
		});

		return result;
	}

	public List<List<Map<String, Object>>> executeProcedure(final String sql) {
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con) throws SQLException {
				return con.prepareCall(sql);
			}
		}, new CallableStatementCallback<List<List<Map<String, Object>>>>() {
			@Override
			public List<List<Map<String, Object>>> doInCallableStatement(CallableStatement cs) throws SQLException {
				boolean resultsAvailable = cs.execute();
				List<List<Map<String, Object>>> list = new ArrayList<List<Map<String, Object>>>();
				while (resultsAvailable) {
					ResultSet resultSet = cs.getResultSet();
					List<Map<String, Object>> subList = new ArrayList<Map<String, Object>>();
					while (resultSet.next()) {
						ResultSetMetaData meta = resultSet.getMetaData();
						int colcount = meta.getColumnCount();
						Map<String, Object> map = new HashMap<String, Object>();
						for (int i = 1; i <= colcount; i++) {
							String name = meta.getColumnLabel(i);
							map.put(name, resultSet.getString(i));
						}
						subList.add(map);
					}
					list.add(subList);
					resultsAvailable = cs.getMoreResults();
				}
				return list;
			}
		});
	}

	public GetBestItemsResponse getBestItems(Integer pageNo, Integer pageSize, LocalDateTime fromDate, LocalDateTime toDate,
											 Integer statisticsType, Integer lastCategoryID, Integer wholeSalerId, String orderBy) {
		List<BestItems> bestItems = statisticsWaBestItemPerDayEntityRepository.getBestItems(pageNo, pageSize, fromDate, toDate, statisticsType, lastCategoryID, wholeSalerId, orderBy);

		return GetBestItemsResponse.builder().bestItems(bestItems).build();
	}
}
