package net.fashiongo.webadmin.service.renewal;

import lombok.RequiredArgsConstructor;
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
import net.fashiongo.webadmin.data.repository.stats.SearchEntityRepository;
import net.fashiongo.webadmin.model.product.Product;
import net.fashiongo.webadmin.model.product.ProductSearchCondition;
import net.fashiongo.webadmin.service.externalutil.response.CollectionObject;
import net.fashiongo.webadmin.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RenewalStaticService {

	protected final JdbcHelper jdbcHelper;

	private final JdbcTemplate jdbcTemplate;

	private final SystemImageServersEntityRepository systemImageServersEntityRepository;

	private final SystemVendorAdminWebServerEntityRepository systemVendorAdminWebServerEntityRepository;

	private final ProductsEntityRepository productsEntityRepository;

	private final StatisticsWaBestItemPerDayEntityRepository statisticsWaBestItemPerDayEntityRepository;

	private final QuerySearchRepository querySearchRepository;

	private final SearchEntityRepository searchEntityRepository;

	private final ProductService productService;

	public GetHotSearchResponse getHotSearch(Integer top, LocalDateTime fromDate, LocalDateTime toDate, String orderBy, String searchfield, String searchkeyword) {
		List<HotSearch> hotSearches = querySearchRepository.getHotSearch(top, fromDate, toDate, orderBy, searchfield, searchkeyword);

		return GetHotSearchResponse.builder()
				.hotSearchList(hotSearches)
				.build();
	}

	public GetHotSearchKeywordResponse getHotSearchKeyword(Integer periodType, LocalDateTime fromDate, LocalDateTime toDate, String keyword) {
//		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//		List<Object> param = new ArrayList<>();
//		param.add(periodType);
//		param.add(Optional.ofNullable(fromDate).map(dateTime -> dateTime.format(dateTimeFormatter)).orElse(null));
//		param.add(Optional.ofNullable(toDate).map(dateTime -> dateTime.format(dateTimeFormatter)).orElse(null));
//		param.add(keyword);
//
//		List<Object> up_wa_getSearchQuery = jdbcHelper.executeSP("up_wa_GetHotSearchKeyword", param, HotSearchKeyword.class);
//		List<HotSearchKeyword> hotSearchKeywords = (List<HotSearchKeyword>) up_wa_getSearchQuery.get(0);


		List<HotSearchKeyword> hotSearchKeywords1 = searchEntityRepository.getHotSearchKeyword(periodType, fromDate, toDate, keyword);

		return GetHotSearchKeywordResponse.builder()
				.hotSearchKeywordList(hotSearchKeywords1)
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

	/**
	 * 기존 BestItem 쿼리에서 Product 관련 부분을 api로 대체함.
	 * @param pageNo page no
	 * @param pageSize page size
	 * @param fromDate Period
	 * @param toDate Period
	 * @param statisticsType default sorting condition. 1 = totalAmount Desc, 2 = Total qty Desc.
	 * @param lastCategoryID Category
	 * @param wholeSalerId vendor
	 * @param sortBy Sorting condition
	 * @return BestItems List
	 */
	public GetBestItemsResponse selectBestItems(Integer pageNo, Integer pageSize, LocalDateTime fromDate, LocalDateTime toDate,
											 Integer statisticsType, Integer lastCategoryID, Integer wholeSalerId, String sortBy) {

		// get best item
		List<BestItems> bestItems = statisticsWaBestItemPerDayEntityRepository.selectBestItems(pageNo, pageSize, fromDate, toDate, statisticsType, lastCategoryID, wholeSalerId);

		List<Integer> productIds = bestItems.stream().map(BestItems::getProductID).collect(Collectors.toList());

		// get Product
		CollectionObject<Product> collectionObject = productService.find(ProductSearchCondition.builder()
				.productIds(productIds)
				.pageNumber(pageNo)
				.pageSize(pageSize)
				.include(Collections.singletonList(ProductSearchCondition.Include.VENDOR))
				.build());
		List<Product> products = collectionObject.getContents();

		Map<Integer, Product> productMap = products.stream().collect(Collectors.toMap(Product::getProductId, p -> p));

		// merge
		bestItems.forEach(b -> {
			Product product = productMap.get(b.getProductID());
			if (Objects.nonNull(product)) {
				b.setUnitPrice(product.getUnitPrice().doubleValue());
				b.setCompanyName(product.getVendor().getName());
				b.setProductName(product.getStyleNo());
				b.setImage(product.getImageUrl());
				b.setActivatedOn(product.getActivatedOn());
			}
		});

		// sorting
		Comparator<BestItems> comparator = null;

		switch (sortBy) {
			case "Oldest" :
				//ActivatedOn ASC null first
				comparator = Comparator.comparing(BestItems::getActivatedOn, Comparator.nullsFirst(Comparator.naturalOrder()));
				comparator = comparator.thenComparing(BestItems::getRowNo);
				break;
			case "Newest" :
				//ActivatedOn DESC null last
				comparator = Comparator.comparing(BestItems::getActivatedOn, Comparator.nullsLast(Comparator.reverseOrder()));
				comparator = comparator.thenComparing(BestItems::getRowNo);
				break;
			case "UnitPriceAsc":
				//UnitPrice ASC null first
				comparator = Comparator.comparing(BestItems::getUnitPrice, Comparator.nullsFirst(Comparator.naturalOrder()));
				comparator = comparator.thenComparing(BestItems::getRowNo);
				break;
			case "UnitPriceDesc":
				//UnitPrice DESC null last
				comparator = Comparator.comparing(BestItems::getUnitPrice, Comparator.nullsLast(Comparator.reverseOrder()));
				comparator = comparator.thenComparing(BestItems::getRowNo);
				break;
			default:
				comparator = Comparator.comparing(BestItems::getRowNo);
		}

		bestItems.sort(comparator);

		// Redefined rowNo.
		long index = 1;
		for (BestItems b : bestItems) {
			b.setRowNo(index);
			index++;
		}

		return GetBestItemsResponse.builder().bestItems(bestItems).build();
	}
}
