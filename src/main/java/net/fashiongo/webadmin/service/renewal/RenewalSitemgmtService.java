package net.fashiongo.webadmin.service.renewal;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.common.dal.JdbcHelper;
import net.fashiongo.webadmin.data.entity.primary.*;
import net.fashiongo.webadmin.data.model.Total;
import net.fashiongo.webadmin.data.model.TotalCount;
import net.fashiongo.webadmin.data.model.kmm.GetKmmListParameter;
import net.fashiongo.webadmin.data.model.kmm.GetKmmListResponse;
import net.fashiongo.webadmin.data.model.kmm.KmmListDetail;
import net.fashiongo.webadmin.data.model.sitemgmt.*;
import net.fashiongo.webadmin.data.model.sitemgmt.response.*;
import net.fashiongo.webadmin.data.repository.primary.*;
import net.fashiongo.webadmin.data.repository.primary.procedure.DMSendListMigrationProcedure;
import net.fashiongo.webadmin.data.repository.primary.procedure.GetAdminTodayDealCalendarResult;
import net.fashiongo.webadmin.data.repository.primary.procedure.PrimaryProcedureRepository;
import net.fashiongo.webadmin.data.repository.primary.view.CategoryViewRepository;
import net.fashiongo.webadmin.model.pojo.common.ResultCode;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.*;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RenewalSitemgmtService {

	private final PolicyAgreementEntityRepository policyAgreementEntityRepository;

	private final CodeLengthEntityRepository codeLengthEntityRepository;

	private final CodeStyleEntityRepository codeStyleEntityRepository;

	private final CodeFabricEntityRepository codeFabricEntityRepository;

	private final CategoryViewRepository categoryViewRepository;

	private final CodePatternEntityRepository codePatternEntityRepository;

	private final PrimaryProcedureRepository primaryProcedureRepository;

	private final CodeBodySizeEntityRepository codeBodySizeEntityRepository;

	private final XColorMasterEntityRepository xColorMasterEntityRepository;

	private final FeaturedItemEntityRepository featuredItemEntityRepository;

	private final TrendReportEntityRepository trendReportEntityRepository;

	private final DMSendListMigrationProcedure dmSendListMigrationProcedure;
	
	private final SEOEntiryRepository seoEntiryRepository;

	private final String MSG_UPDATE_SUCCESS = "Updated successfully!";

	private final JdbcHelper jdbcHelper;

	@Autowired
	public RenewalSitemgmtService(PolicyAgreementEntityRepository policyAgreementEntityRepository, CodeLengthEntityRepository codeLengthEntityRepository, CodeStyleEntityRepository codeStyleEntityRepository, CodeFabricEntityRepository codeFabricEntityRepository, CategoryViewRepository categoryViewRepository, CodePatternEntityRepository codePatternEntityRepository, PrimaryProcedureRepository primaryProcedureRepository, CodeBodySizeEntityRepository codeBodySizeEntityRepository, XColorMasterEntityRepository xColorMasterEntityRepository, FeaturedItemEntityRepository featuredItemEntityRepository, TrendReportEntityRepository trendReportEntityRepository, DMSendListMigrationProcedure dmSendListMigrationProcedure, JdbcHelper jdbcHelper,SEOEntiryRepository seoEntiryRepository) {
		this.policyAgreementEntityRepository = policyAgreementEntityRepository;
		this.codeLengthEntityRepository = codeLengthEntityRepository;
		this.codeStyleEntityRepository = codeStyleEntityRepository;
		this.codeFabricEntityRepository = codeFabricEntityRepository;
		this.categoryViewRepository = categoryViewRepository;
		this.codePatternEntityRepository = codePatternEntityRepository;
		this.primaryProcedureRepository = primaryProcedureRepository;
		this.codeBodySizeEntityRepository = codeBodySizeEntityRepository;
		this.xColorMasterEntityRepository = xColorMasterEntityRepository;
		this.featuredItemEntityRepository = featuredItemEntityRepository;
		this.trendReportEntityRepository = trendReportEntityRepository;
		this.dmSendListMigrationProcedure = dmSendListMigrationProcedure;
		this.jdbcHelper = jdbcHelper;
		this.seoEntiryRepository = seoEntiryRepository;
	}

	public GetPolicyDetailResponse getPolicyDetail (GetPolicyDetailParameter parameters) {
		Page<PolicyAgreement> detailPolicyAgreement = policyAgreementEntityRepository.findDetailPolicyAgreement(parameters.getPolicyID(), parameters.getSearchItem(), parameters.getSearchTxt(), parameters.getPageNum(), parameters.getPageSize());
		int totalCnt = Long.valueOf(detailPolicyAgreement.getTotalElements()).intValue();
		return GetPolicyDetailResponse.builder()
				.total(Arrays.asList(new Total(totalCnt)))
				.policyDetail(
						detailPolicyAgreement.getContent()
				)
				.build();
	}

	public GetProductAttributesResponse getProductAttributes(GetProductAttributesParameter parameter) {
		GetProductAttributesResponse.GetProductAttributesResponseBuilder builder = GetProductAttributesResponse.builder();
		long totalElements = 0;
		List<CodeData> codeDataList = null;

		switch (parameter.getTabNo())
		{
			case 2:// "Length":

				Page<CodeLengthEntity> codeLengthEntities = codeLengthEntityRepository.findAllByLengthNameAndActiveOrderByLengthName(parameter.getAttrName(), parameter.getActive(), 1, 1000);
				totalElements = codeLengthEntities.getTotalElements();
				codeDataList = codeLengthEntities.getContent().stream()
						.map(entity -> CodeData.builder()
								.active(entity.isActive())
								.codeID(entity.getLengthId())
								.codeName(entity.getLengthName())
								.build())
						.collect(Collectors.toList());
				break;
			case 3:// "Style":
				Page<CodeStyleEntity> codeStyleEntities = codeStyleEntityRepository.findAllByStyleNameAndActiveOrderByStyleName(parameter.getAttrName(), parameter.getActive(), 1, 1000);
				totalElements = codeStyleEntities.getTotalElements();

				codeDataList = codeStyleEntities.getContent().stream()
						.map(entity -> CodeData.builder()
								.active(entity.isActive())
								.codeID(entity.getStyleId())
								.codeName(entity.getStyleName())
								.build())
						.collect(Collectors.toList());

				break;
			case 4:// "Fabric":
				Page<CodeFabricEntity> codeFabricEntities = codeFabricEntityRepository.findAllByFabricNameAndActiveOrderByFabricName(parameter.getAttrName(), parameter.getActive(), 1, 1000);
				totalElements = codeFabricEntities.getTotalElements();

				codeDataList = codeFabricEntities.getContent().stream()
						.map(entity -> CodeData.builder()
								.active(entity.isActive())
								.codeID(entity.getFabricId())
								.codeName(entity.getFabricName())
								.build())
						.collect(Collectors.toList());
				break;
			case 5:// "Category Mapping":
				Page<CodeData> codeDataPage = null;
				switch (parameter.getPrevTab())
				{
					case 2:
//						DataSrc = "vwLengthCategory";
//						ColumnList = "MapID,LengthID As CodeID,LengthName As CodeName,Case When MapID > 0 Then Cast(1 As Bit) Else Cast(0 As Bit) End As Active";
//						Filter = Filter + " and CategoryID = " + parameter.getCategoryID() + "";
//						OrderBy = "LengthName";

						codeDataPage = categoryViewRepository.findAllvwLengthCategoryByCategoryIdOrderByLengthName(parameter.getCategoryID(),1,1000);
						totalElements = codeDataPage.getTotalElements();
						codeDataList = codeDataPage.getContent();
						break;
					case 3:
//						DataSrc = "vwStyleCategory";
//						ColumnList = "MapID,StyleID As CodeID,StyleName As CodeName,Case When MapID > 0 Then Cast(1 As Bit) Else Cast(0 As Bit) End As Active";
//						Filter = Filter + " and CategoryID = " + parameter.getCategoryID() + "";
//						OrderBy = "StyleName";

						codeDataPage = categoryViewRepository.findAllvwStyleCategoryByCategoryIdOrderByStyleName(parameter.getCategoryID(),1,1000);
						totalElements = codeDataPage.getTotalElements();
						codeDataList = codeDataPage.getContent();
						break;
					case 4:
//						DataSrc = "vwFabricCategory";
//						ColumnList = "MapID,FabricID As CodeID,FabricName As CodeName,Case When MapID > 0 Then Cast(1 As Bit) Else Cast(0 As Bit) End As Active";
//						Filter = Filter + " and CategoryID = " + parameter.getCategoryID() + "";
//						OrderBy = "FabricName";
						codeDataPage = categoryViewRepository.findAllvwFabricCategoryByCategoryIdOrderByfabricName(parameter.getCategoryID(),1,1000);
						totalElements = codeDataPage.getTotalElements();
						codeDataList = codeDataPage.getContent();
						break;
					default:
//						DataSrc = "vwPatternCategory";
//						ColumnList = "MapID,PatternID As CodeID,PatternName As CodeName,Case When MapID > 0 Then Cast(1 As Bit) Else Cast(0 As Bit) End As Active";
//						Filter = Filter + " and CategoryID = " + parameter.getCategoryID() + "";
//						OrderBy = "PatternName";
						codeDataPage = categoryViewRepository.findAllvwPatternCategoryByCategoryIdOrderByPatternName(parameter.getCategoryID(),1,1000);
						totalElements = codeDataPage.getTotalElements();
						codeDataList = codeDataPage.getContent();
						break;
				}
				break;
			default://"Pattern":
				Page<CodePatternEntity> codePatternEntities = codePatternEntityRepository.findAllByPattenNameAndActiveOrderByPattenName(parameter.getAttrName(), parameter.getActive(), 1, 1000);
				totalElements = codePatternEntities.getTotalElements();

				codeDataList = codePatternEntities.getContent().stream()
						.map(entity -> CodeData.builder()
								.active(entity.isActive())
								.codeID(entity.getPatternId())
								.codeName(entity.getPatternName())
								.build())
						.collect(Collectors.toList());

				return builder.codeDataList(codeDataList)
						.recCnt(Arrays.asList(Total.builder().recCnt((int) totalElements).build()))
						.build();
		}

		return builder.codeDataList(codeDataList)
				.recCnt(Arrays.asList(Total.builder().recCnt((int) totalElements).build()))
				.build();
	}

	public GetCategoryListResponse getCategoryList(GetCategoryListParameters parameters) {
		Integer categoryID = parameters.getCategoryId();
		Integer expandAll = parameters.getExpandAll();

		List<CategoryList> results = primaryProcedureRepository.up_wa_GetCategoryList(categoryID, expandAll);

		return GetCategoryListResponse.builder()
				.categoryList(results)
				.build();
	}

	public GetTodaydealResponse getTodaydeal(GetTodaydealParameter parameters) throws ParseException {
		Integer pageNumber = parameters.getPagenum();
		Integer pagesize = parameters.getPagesize();
		Integer wholesalerid = parameters.getWholesalerid();
		String checkedCompanyNo = parameters.getCheckedCompanyNo();
		Integer categoryid = parameters.getCategoryid();
		BigDecimal priceFrom = null;
		BigDecimal priceTo = null;
		Date fromdate = parameters.getFromdate();
		Date todate = parameters.getTodate();
		Boolean active = parameters.getActive();
		String orderby = parameters.getOrderby();

		Page<TodayDealDetail> todayDealDetails = primaryProcedureRepository.up_wa_GetAdminTodayDeal(pageNumber, pagesize, wholesalerid, checkedCompanyNo, categoryid, priceFrom, priceTo, fromdate, todate, active, orderby);

		return GetTodaydealResponse.builder()
				.todayDealDetail(todayDealDetails.getContent())
				.total(Total.builder().recCnt((int) todayDealDetails.getTotalElements()).build())
				.build();
	}

	public GetVendorListResponse getVendorList() {
		ResultGetVendorList result = primaryProcedureRepository.up_GetVendorList();

		return GetVendorListResponse.builder()
				.categoryCountlist(result.getCategoryCountlist())
				.vendorSummarylist(result.getVendorSummarylist())
				.build();
	}

	public GetTodayDealCalendarListResponse getTodayDealCalendarList(GetTodayDealCalendarListParameter parameter) {
		Date sDate = parameter.getSelectdate();
		Integer wholeSalerID = parameter.getWholesalerid();

		ResultGetAdminTodayDealCalendarList result = primaryProcedureRepository.up_wa_GetAdminTodayDealCalendarList(sDate, wholeSalerID);

		return GetTodayDealCalendarListResponse.builder()
				.activeTodayDeals(result.getActiveTodayDealDetails())
				.inactiveTodayDeals(result.getInactiveTodayDealDetails())
				.build();
	}

	public GetCategoryVendorListResponse getCategoryVendorList(Integer categoryID, String vendorName) {
		ResultGetCategoryVendorList result = primaryProcedureRepository.up_wa_GetCategoryVendorList(categoryID, vendorName);

		return GetCategoryVendorListResponse.builder()
				.categoryCountlist(result.getCategoryCountlist())
				.categoryVendorList(result.getCategoryVendorList())
				.categoryVendorInfoList(result.getCategoryVendorInfoList())
				.build();
	}

	public GetProductAttributesTotalResponse getProductAttributesTotal() {
	    List<PatternInfo> patternInfoList = codePatternEntityRepository.findAllOrderByPatternName();
        List<LengthInfo> lengthInfolist = codeLengthEntityRepository.findAllOrderByLengthName();
        List<StyleInfo> styleInfolist = codeStyleEntityRepository.findAllOrderByStyleName();
        List<FabricInfo> fabricInfolist = codeFabricEntityRepository.findAllOrderByFabricName();
        List<BodySizeInfo> bodySizeInfolist = codeBodySizeEntityRepository.findAllWhereActiveTrue();
        List<ColorListInfo> colorListInfolist = xColorMasterEntityRepository.findAllColors();

        return GetProductAttributesTotalResponse.builder()
				.patternInfolist(patternInfoList)
				.lengthInfolist(lengthInfolist)
				.styleInfolist(styleInfolist)
				.fabricInfolist(fabricInfolist)
				.bodySizeInfolist(bodySizeInfolist)
				.colorListInfolist(colorListInfolist)
				.build();
    }

	public GetTodayDealCalendarResponse getTodayDealCalendar(GetTodayDealCanlendarParameter parameters) {
		GetAdminTodayDealCalendarResult getAdminTodayDealCalendarResult = primaryProcedureRepository.up_wa_GetAdminTodayDealCalendar(parameters.getFromdate(), parameters.getTodate());

		return GetTodayDealCalendarResponse.builder()
				.calendarDetails(getAdminTodayDealCalendarResult.getCalendarDetails())
				.vendors(getAdminTodayDealCalendarResult.getVendors())
				.build();
	}

	public GetFeaturedItemCountResponse getFeaturedItemCount(String sDate) {
		GetFeaturedItemCountResponse result = new GetFeaturedItemCountResponse();

		result.setFeaturedItemCount(featuredItemEntityRepository.getFeaturedItemCount(sDate));
		result.setFeaturedItemList(featuredItemEntityRepository.getFeaturedItemList(sDate));

		return result;
	}

	public GetDMRequestResponse getDMRequest(GetDMRequestParameter parameters) {
		Integer pagenum = parameters.getPagenum();
		Integer pagesize = parameters.getPagesize();
		String status = parameters.getStatus();
		Integer vendorstatus = parameters.getVendorstatus();
		Integer wholesalerid = parameters.getWholesalerid();
		String companytypecd = parameters.getCompanytypecd();
		Date datefrom = parameters.getDatefrom();
		Date dateto = parameters.getDateto();
		String orderby = parameters.getOrderby();

		List<DMRequest> dmRequests = primaryProcedureRepository.up_wa_GetFGCatalog(
				pagenum
				, pagesize
				, status
				, vendorstatus
				, wholesalerid
				, companytypecd
				, datefrom
				, dateto
				, orderby);

		return GetDMRequestResponse.builder()
				.dmList(dmRequests)
				.build();
	}

	public GetFeaturedItemListDayResponse getFeaturedItemListDay(String sDate) {
		List<FeaturedItemList> featuredItemLists = featuredItemEntityRepository.getFeaturedItemListDay(sDate);

		return GetFeaturedItemListDayResponse.builder()
				.featuredItemList(featuredItemLists)
				.build();
	}

	public GetTrendReportDefaultResponse getTrendReportDefault(GetTrendReportDefaultParameter parameters) {
		return GetTrendReportDefaultResponse.builder()
				.total(trendReportEntityRepository.getRecCnt())
				.trendReportDefault(trendReportEntityRepository.getTrendReportDefault(parameters.orderby, parameters.orderbygubn))
				.build();
	}

	public JSONObject getDMRequestSendList(GetDMRequestSendListParameter parameters) {
		JSONObject result = new JSONObject();

		List<DMRequestDetail> dmRequestDetails = dmSendListMigrationProcedure.up_wa_DMSendList_Migration(parameters.getDmIds());

		Map<Integer, List<DMRequestDetail>> HashMapDmList = dmRequestDetails.stream()
				.collect(Collectors.groupingBy(DMRequestDetail::getCatalogID));

		for (Map.Entry<Integer, List<DMRequestDetail>> entry : HashMapDmList.entrySet()) {
			result.put(entry.getKey(), entry.getValue());
		}

		return result;
	}

	public GetTrendReportResponse getTrendReport(GetTrendReportParameter parameter) {
		Integer pagenum = Optional.ofNullable(parameter.getPagenum()).orElse(1);
		Integer pagesize = Optional.ofNullable(parameter.getPagesize()).orElse(10);
		String searchtxt = Optional.ofNullable(parameter.getSearchtxt()).filter(s -> StringUtils.hasLength(s)).orElse(null);
		LocalDateTime fromdate = Optional.ofNullable(parameter.getFromdate()).filter(s -> StringUtils.hasLength(s)).map(s -> new Date(s).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()).orElse(null);
		LocalDateTime todate = Optional.ofNullable(parameter.getTodate()).filter(s -> StringUtils.hasLength(s)).map(s -> new Date(s).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()).orElse(null);
		Boolean active = Optional.ofNullable(parameter.getActive()).filter(s -> StringUtils.hasLength(s)).map(s -> Boolean.valueOf(s)).orElse(null);
		String orderby = Optional.ofNullable(parameter.getOrderby()).filter(s -> StringUtils.hasLength(s)).orElse(null);
		String orderbygubn = Optional.ofNullable(parameter.getOrderbygubn()).filter(s -> StringUtils.hasLength(s)).orElse(null);

		Page<TrendReport> trendReports = trendReportEntityRepository.up_wa_GetAdminTrendReport(pagenum, pagesize, searchtxt, fromdate, todate, orderby, orderbygubn, active);

		return GetTrendReportResponse.builder()
				.recCnt(Arrays.asList(Total.builder().recCnt((int) trendReports.getTotalElements()).build()))
				.trendReports(trendReports.getContent())
				.build();
	}

	public ResultCode setTrendReportMap(SetTrendReportMapParameter parameters) {
		String setType = parameters.getSetType();
		int mapId = parameters.getMapId();
		int trendreportId = parameters.getTrendreportId();
		int productId = parameters.getProductId();
		String modifiedBy = parameters.getModifiedBy();

		trendReportEntityRepository.up_wa_AddDelTrendReportItem(setType,mapId,trendreportId,productId,modifiedBy);

		return new ResultCode(true, 1, MSG_UPDATE_SUCCESS);
	}

	public GetItemsResponse getItems(SitemgmtGetItemsParameter parameters) {

		Integer pagenum = Optional.ofNullable(parameters.getPagenum()).orElse(1);
		Integer pagesize = Optional.ofNullable(parameters.getPagesize()).orElse(10);
		String fgcat = Optional.ofNullable(parameters.getFgcat()).filter(s -> StringUtils.hasLength(s)).orElse(null);
		String vendorid = Optional.ofNullable(parameters.getVendorid()).filter(s -> StringUtils.hasLength(s)).orElse(null);
		String selectedcategoryid = Optional.ofNullable(parameters.getSelectedcategoryid()).filter(s -> StringUtils.hasLength(s)).orElse(null);
		String searchitemtxt = Optional.ofNullable(parameters.getSearchitemtxt()).filter(s -> StringUtils.hasLength(s)).orElse(null);
		Boolean companytypeid1 = Optional.ofNullable(parameters.getCompanytypeid1()).orElse(false);
		Boolean companytypeid2 = Optional.ofNullable(parameters.getCompanytypeid2()).orElse(false);
		Boolean companytypeid3 = Optional.ofNullable(parameters.getCompanytypeid3()).orElse(false);
		String inventory = Optional.ofNullable(parameters.getInventory()).filter(s -> StringUtils.hasLength(s)).orElse("");
		Boolean isPreOrder = null;
		Boolean inStockOnly = null;

		if(inventory.equals("")) {
			isPreOrder = null;
			inStockOnly = null;
		} else if (inventory.equals("PO")){
			isPreOrder = true;
			inStockOnly = null;
		} else if (inventory.equals("IS")){
			isPreOrder = null;
			inStockOnly = true;
		}

		String checkedCompanyNo = "";

		if (companytypeid1 == true)
		{
			checkedCompanyNo = "2,";
		}
		if (companytypeid2 == true)
		{
			checkedCompanyNo = checkedCompanyNo + "1,";
		}
		if (companytypeid3 == true)
		{
			checkedCompanyNo = checkedCompanyNo + "3,";
		}
		if (companytypeid1 == true || companytypeid2 == true || companytypeid3 == true)
		{
			checkedCompanyNo = checkedCompanyNo.substring(0,checkedCompanyNo.length() -1);
		}
		else
		{
			checkedCompanyNo = null;
		}

		List<Object> param = new ArrayList<>();
		param.add(pagenum);
		param.add(pagesize);
		param.add(fgcat);
		param.add(vendorid);
		param.add(selectedcategoryid);
		param.add(null);
		param.add(null);
		param.add(null);
		param.add(null);
		param.add(null);
		param.add(null);
		param.add(null);
		param.add(null);
		param.add(null);
		param.add(null);
		param.add(searchitemtxt);
		param.add("ProductName");
		param.add(isPreOrder);
		param.add(null);
		param.add(null);
		param.add(null);
		param.add(null);
		param.add(null);
		param.add(inStockOnly);
		param.add(checkedCompanyNo);

		List<Object> up_wa_getItemsSearch = jdbcHelper.executeSP("up_wa_GetItemsSearch", param, TotalCount.class, Item.class);
		List<TotalCount> totals = (List<TotalCount>) up_wa_getItemsSearch.get(0);
		List<Item> items = (List<Item>) up_wa_getItemsSearch.get(1);

		return GetItemsResponse.builder()
				.totals(totals)
				.itemList(items)
				.build();
	}

	public GetKmmListResponse getTrendReport2(GetKmmListParameter parameter) {
		Integer pagenum = Optional.ofNullable(parameter.getPagenum()).orElse(1);
		Integer pagesize = Optional.ofNullable(parameter.getPagesize()).orElse(10);
		String searchtxt = Optional.ofNullable(parameter.getSearchtxt()).filter(s -> StringUtils.hasLength(s)).orElse(null);
		LocalDateTime fromdate = Optional.ofNullable(parameter.getFromdate()).filter(s -> StringUtils.hasLength(s)).map(s -> new Date(s).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()).orElse(null);
		LocalDateTime todate = Optional.ofNullable(parameter.getTodate()).filter(s -> StringUtils.hasLength(s)).map(s -> new Date(s).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()).orElse(null);
		Boolean active = Optional.ofNullable(parameter.getActive()).filter(s -> StringUtils.hasLength(s)).map(s -> Boolean.valueOf(s)).orElse(null);
		String orderby = null;
		String orderbygubn = null;
		Integer curatedType = 4;

		Page<KmmListDetail> kmmList = trendReportEntityRepository.up_wa_GetAdminTrendReport2(pagenum, pagesize, searchtxt, fromdate, todate, orderby, orderbygubn, active, curatedType);

		return GetKmmListResponse.builder()
				.recCnt(Arrays.asList(Total.builder().recCnt((int) kmmList.getTotalElements()).build()))
				.kmmList(kmmList.getContent())
				.build();
	}
	
	public GetSEOResponse getSEO(GetSEOParameter parameters) {
		Integer pagenum = Optional.ofNullable(parameters.getPagenum()).orElse(1);
		Integer pagesize = Optional.ofNullable(parameters.getPagesize()).orElse(50);
		//String orderby = Optional.ofNullable(parameter.getOrderby()).filter(s -> StringUtils.hasLength(s)).orElse(null);
		//String orderbygubn = Optional.ofNullable(parameter.getOrderbygubn()).filter(s -> StringUtils.hasLength(s)).orElse(null);
	
		Page<SEO> seo = seoEntiryRepository.findAllBySeo(pagenum, pagesize);

		GetSEOResponse respone = GetSEOResponse.builder()
		.recCnt(Arrays.asList(Total.builder().recCnt((int) seo.getTotalElements()).build()))
		.seo(seo.getContent())
		.build();	
		
		return respone;		

	}	
	
	@Transactional(transactionManager = "primaryTransactionManager")
	public Integer setSEO( String pageName, String url, String title, String metaKeyword, String metaDescription, String username) {
		try {

			Timestamp NOW = Timestamp.valueOf(LocalDateTime.now());

			SEOEntity sEOEntity = new SEOEntity();
				
			//sEOEntity.setSiteSeoId(seo.getSiteSEOId());
			sEOEntity.setPageName(pageName);
			sEOEntity.setUrl(url);
			sEOEntity.setTitle(title);
			sEOEntity.setMetaKeyword(metaKeyword);
			sEOEntity.setMetaDescription(metaDescription);
			sEOEntity.setActive(true);
			//sEOEntity.setIsActive(true);
			sEOEntity.setCreatedOn(NOW);
			sEOEntity.setCreatedBy(username);
			
			seoEntiryRepository.save(sEOEntity);

			return 1;
		} catch (Exception e) {
			log.warn(e.getMessage(),e);
			return -99;
		}
	}

	public Integer setSEOupdate(Integer siteSeoId, String pageName, String url, String title, String metaKeyword, String metaDescription, String username) {
		try {		
			Timestamp NOW = Timestamp.valueOf(LocalDateTime.now());
			
			SEOEntity sEOEntity = seoEntiryRepository.findOneByID(siteSeoId);
			
			sEOEntity.setPageName(pageName);
			sEOEntity.setUrl(url);
			sEOEntity.setTitle(title);
			sEOEntity.setMetaKeyword(metaKeyword);
			sEOEntity.setMetaDescription(metaDescription);
			sEOEntity.setModifiedOn(NOW);
			sEOEntity.setModifiedBy(username);
			
			seoEntiryRepository.save(sEOEntity);
		
			return 1;
		} catch (Exception e) {
			log.warn(e.getMessage(),e);
			return -99;
		}
	}

	public Integer deleteSEO(List<Integer> siteseoids, String username) {
		try {	
			Timestamp NOW = Timestamp.valueOf(LocalDateTime.now());
		
			List<SEOEntity> sEOEntity = seoEntiryRepository.findOneByIDAll(siteseoids);
			
			for (int i = 0; i < sEOEntity.size(); i++) {
				SEOEntity _sEOEntity = seoEntiryRepository.findOneByID(sEOEntity.get(i).getSiteSeoId());
				
				_sEOEntity.setDeleted(true);
				_sEOEntity.setModifiedOn(NOW);
				_sEOEntity.setModifiedBy(username);
				seoEntiryRepository.save(_sEOEntity);	
			}
					
			return 1;
			
		} catch (Exception e) {
			log.warn(e.getMessage(),e);
			return -99;
		}
	}
}


