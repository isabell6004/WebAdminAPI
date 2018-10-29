package net.fashiongo.webadmin.service;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
//import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

//import org.apache.commons.lang3.time.FastDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import net.fashiongo.webadmin.common.Utility;
import net.fashiongo.webadmin.dao.fgem.EmConfigurationRepository;
import net.fashiongo.webadmin.dao.primary.CategoryRepository;
import net.fashiongo.webadmin.dao.primary.TodayDealRepository;
import net.fashiongo.webadmin.dao.primary.TrendReportRepository;
import net.fashiongo.webadmin.dao.primary.VendorCategoryRepository;
import net.fashiongo.webadmin.model.fgem.EmConfiguration;
import net.fashiongo.webadmin.model.pojo.ActiveTodayDealDetail;
import net.fashiongo.webadmin.model.pojo.BodySizeInfo;
import net.fashiongo.webadmin.model.pojo.CategoryCount;
import net.fashiongo.webadmin.model.pojo.CategoryListOrder;
import net.fashiongo.webadmin.model.pojo.CategoryReport;
import net.fashiongo.webadmin.model.pojo.CategoryVendor;
import net.fashiongo.webadmin.model.pojo.CategoryVendorInfo;
import net.fashiongo.webadmin.model.pojo.ColorListInfo;
import net.fashiongo.webadmin.model.pojo.FabricInfo;
import net.fashiongo.webadmin.model.pojo.FeaturedItem;
import net.fashiongo.webadmin.model.pojo.FeaturedItemCount;
import net.fashiongo.webadmin.model.pojo.InactiveTodayDealDetail;
import net.fashiongo.webadmin.model.pojo.LengthInfo;
import net.fashiongo.webadmin.model.pojo.PatternInfo;
import net.fashiongo.webadmin.model.pojo.ResultCode;
import net.fashiongo.webadmin.model.pojo.ResultResponse;
import net.fashiongo.webadmin.model.pojo.StyleInfo;
import net.fashiongo.webadmin.model.pojo.TodayDealCalendarDetail;
import net.fashiongo.webadmin.model.pojo.TodayDealDetail;
import net.fashiongo.webadmin.model.pojo.Total;
import net.fashiongo.webadmin.model.pojo.TrendReportKmmImage;
import net.fashiongo.webadmin.model.pojo.VendorCategorySummary;
import net.fashiongo.webadmin.model.pojo.VendorSummary;
import net.fashiongo.webadmin.model.pojo.VendorSummaryDetail;
//import net.fashiongo.webadmin.model.pojo.Total;
import net.fashiongo.webadmin.model.pojo.parameter.GetCategoryListParameters;
import net.fashiongo.webadmin.model.pojo.parameter.GetCategoryVendorListParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetTodayDealCalendarListParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetTodayDealCanlendarParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetTodaydealParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetCategoryListOrderParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetCategoryParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetNewTodayDealParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetPaidCampaignParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetTodayDealCalendarParameter;
import net.fashiongo.webadmin.model.pojo.response.GetCategoryListResponse;
import net.fashiongo.webadmin.model.pojo.response.GetCategoryVendorListResponse;
import net.fashiongo.webadmin.model.pojo.response.GetFeaturedItemCountResponse;
import net.fashiongo.webadmin.model.pojo.response.GetPaidCampaignResponse;
import net.fashiongo.webadmin.model.pojo.response.GetProductAttributesTotalResponse;
import net.fashiongo.webadmin.model.pojo.response.GetTodayDealCalendarListResponse;
import net.fashiongo.webadmin.model.pojo.response.GetTodayDealCalendarResponse;
import net.fashiongo.webadmin.model.pojo.response.GetTodaydealResponse;
import net.fashiongo.webadmin.model.pojo.response.GetTrendReportCategoryResponse;
import net.fashiongo.webadmin.model.pojo.response.GetVendorCategoryResponse;
import net.fashiongo.webadmin.model.pojo.response.GetVendorListResponse;
import net.fashiongo.webadmin.model.primary.Category;
import net.fashiongo.webadmin.model.primary.CollectionCategory;
import net.fashiongo.webadmin.model.primary.TodayDeal;
import net.fashiongo.webadmin.model.primary.TrendReport;
import net.fashiongo.webadmin.model.primary.VendorCategory;

/**
 *
 * @author Sanghyup Kim
 */
@Service
public class SitemgmtService extends ApiService {

	@Autowired
	private EmConfigurationRepository emConfigurationRepository;

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private TodayDealRepository todayDealRepository;
	
	@Autowired
	private VendorCategoryRepository vendorCategoryRepository;
	
	@Autowired
	private TrendReportRepository trendReportRepository;

	/**
	 *
	 * Get Category List
	 *
	 * @since 2018. 9. 28.
	 * @author Sanghyup Kim
	 * @param GetCategoryListParameters
	 * @return GetCategoryListResponse
	 */
	@SuppressWarnings("unchecked")
//	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED, transactionManager = "primaryTransactionManager")
//	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS, transactionManager = "primaryTransactionManager")
	public GetCategoryListResponse getCategoryList(GetCategoryListParameters parameters) {

		List<Object> params = new ArrayList<Object>();

		params.add(parameters.getCategoryId());
		params.add(parameters.getExpandAll());
		final String spName = "up_wa_GetCategoryList";

		final List<Object> _result = jdbcHelper.executeSP(spName, params, CollectionCategory.class);
		final List<CollectionCategory> collectionCategoryList = (List<CollectionCategory>) _result.get(0);

		GetCategoryListResponse resultSet = new GetCategoryListResponse();
		resultSet.setCategoryLst(collectionCategoryList);

		return resultSet;
	}

	/**
	 *
	 * Get Paid Campaign
	 *
	 * @since 2018. 10. 08.
	 * @author Nayeon Kim
	 * @return GetPaidCampaignResponse
	 */
	public GetPaidCampaignResponse getPaidCampaign() {
		GetPaidCampaignResponse result = new GetPaidCampaignResponse();
		result.setConfigurationsList(emConfigurationRepository.findAll());
		return result;
	}

	/**
	 *
	 * Set Paid Campaign
	 *
	 * @since 2018. 10. 11.
	 * @author Nayeon Kim
	 * @param SetPaidCampaignParameter
	 * @return
	 */
	@Transactional(value = "primaryTransactionManager")
	public ResultCode setPaidCampaign(SetPaidCampaignParameter parameters) {
		List<EmConfiguration> emConfigurationList = parameters.getObjList();
		
		for (EmConfiguration emConfiguration2 : emConfigurationList) {
			EmConfiguration emConfiguration = new EmConfiguration();
			emConfiguration.setConfigID(emConfiguration2.getConfigID());
			emConfiguration.setConfigType(emConfiguration2.getConfigType());
			emConfiguration.setConfigValue(emConfiguration2.getConfigValue());
			emConfigurationRepository.save(emConfiguration);
		}
		return new ResultCode(true, 1, MSG_SAVE_SUCCESS);
	}

	/**
	 *
	 *
	 *
	 * @since 2018. 10. 22.
	 * @author Dahye
	 * @param
	 * @return
	 */
	public void getPolicyManagement () {

	}

	/**
	 *
	 *
	 *
	 * @since 2018. 10. 22.
	 * @author Dahye
	 * @param
	 * @return
	 */
	public void setAddDelPolicyManagement () {

	}

	/**
	 *
	 *
	 *
	 * @since 2018. 10. 22.
	 * @author Dahye
	 * @param
	 * @return
	 */
	public void getPolicyDetail () {

	}

	/**
	 *
	 *
	 *
	 * @since 2018. 10. 22.
	 * @author Dahye
	 * @param
	 * @return
	 */
	public void getPolicyManagementDetail () {

	}

	/**
	 *
	 *
	 *
	 * @since 2018. 10. 22.
	 * @author Dahye
	 * @param
	 * @return
	 */
	public void getCommunicationReasonAll () {

	}

	/**
	 *
	 *
	 *
	 * @since 2018. 10. 22.
	 * @author Dahye
	 * @param
	 * @return
	 */
	public void deleteCommunicationReason () {

	}

	/**
	 *
	 *
	 *
	 * @since 2018. 10. 22.
	 * @author Dahye
	 * @param
	 * @return
	 */
	public void setCommunicationReasonActive () {

	}

	/**
	 *
	 *
	 *
	 * @since 2018. 10. 22.
	 * @author Dahye
	 * @param
	 * @return
	 */
	public void setCommunicationReason () {

	}

	/**
	 *
	 * Get VendorList
	 *
	 * @since 2018. 10. 22.
	 * @author Incheol Jung
	 * @return
	 */
	public GetVendorListResponse getVendorList() {
		GetVendorListResponse resultSet = new GetVendorListResponse();
		String spName = "up_GetVendorList";

		List<Object> params = new ArrayList<Object>();
		params.add(null);
		params.add(null);
		params.add(null);

		List<Object> _result = jdbcHelper.executeSP(spName, params, CategoryCount.class, VendorSummary.class);

		resultSet.setCategoryCountlist((List<CategoryCount>) _result.get(0));
		resultSet.setVendorSummarylist((List<VendorSummary>) _result.get(1));

		return resultSet;
	}

	/**
	 *
	 * Description Example
	 *
	 * @since 2018. 10. 23.
	 * @author Incheol Jung
	 * @param parameters
	 * @return
	 * @throws ParseException
	 */
	public GetTodaydealResponse getTodaydeal(GetTodaydealParameter parameters) throws ParseException {
		GetTodaydealResponse resultSet = new GetTodaydealResponse();
		String spName = "up_wa_GetAdminTodayDeal";

		List<Object> params = new ArrayList<Object>();
		params.add(parameters.getPagenum());
		params.add(parameters.getPagesize());
		params.add(parameters.getWholesalerid());
		params.add(parameters.getCheckedCompanyNo());
		params.add(parameters.getCategoryid());
		params.add(null);
		params.add(null);
		params.add(parameters.getFromdate());
		params.add(parameters.getTodate());
		params.add(parameters.getActive());
		params.add(parameters.getOrderby());

		List<Object> _result = jdbcHelper.executeSP(spName, params, Total.class, TodayDealDetail.class);

		resultSet.setTotal(((List<Total>) _result.get(0)).get(0));
		resultSet.setTodayDealDetail((List<TodayDealDetail>) _result.get(1));

		return resultSet;
	}

	/**
	 * 
	 * Get TrendReportCategory
	 * 
	 * @since 2018. 10. 24.
	 * @author Incheol Jung
	 * @return
	 */
	public GetTrendReportCategoryResponse getTrendReportCategory() {
		GetTrendReportCategoryResponse result = new GetTrendReportCategoryResponse();

		List<Category> categories = this.categoryRepository.findByActiveTrue();
		if(!CollectionUtils.isEmpty(categories)) {
			result.setCategoryList(
					categories.stream()
					.map(c -> new CategoryReport(c.getParentCategoryID(), c.getCategoryID(), c.getCategoryName(), c.getLvl()))
					.collect(Collectors.toList()));
		}
		
		return result;
	}
	
	/**
	 * 
	 * Get TodayDealCalendar
	 * 
	 * @since 2018. 10. 26.
	 * @author Incheol Jung
	 * @param parameters
	 * @return
	 */
	public GetTodayDealCalendarResponse getTodayDealCalendar(GetTodayDealCanlendarParameter parameters) {
		GetTodayDealCalendarResponse resultSet = new GetTodayDealCalendarResponse();
		String spName = "up_wa_GetAdminTodayDealCalendar";

		List<Object> params = new ArrayList<Object>();
		params.add(parameters.getFromdate());
		params.add(parameters.getTodate());

		List<Object> _result = jdbcHelper.executeSP(spName, params, TodayDealCalendarDetail.class, VendorSummaryDetail.class);
		
		resultSet.setCalendarDetails((List<TodayDealCalendarDetail>) _result.get(0));
		resultSet.setVendors((List<VendorSummaryDetail>) _result.get(1));

		return resultSet;
	}

	
	/**
	 * 
	 * Set Category
	 * 
	 * @since 2018. 10. 23.
	 * @author Nayeon Kim
	 * @param SetCategoryParameter
	 * @return
	 */
	@Transactional(value = "primaryTransactionManager")
	public ResultResponse<Integer> setCategory(SetCategoryParameter parameters) {
		ResultResponse<Integer> result = new ResultResponse<Integer>();

		Category category = new Category();
		Category objCategory = parameters.getObjCategory();
		String setType = parameters.getSettype();
		Integer categoryID = objCategory.getCategoryID();

		switch (setType) {
		case "Add":
			this.saveCategory(category, objCategory);
			result.setResultWrapper(true, 1, category.getCategoryID(), MSG_SAVE_SUCCESS, null);

			break;

		case "Upd":
			category = categoryRepository.findOneByCategoryID(categoryID);
			this.saveCategory(category, objCategory);
			result.setResultWrapper(true, 1, null, MSG_UPDATE_SUCCESS, null);

			break;

		case "Act":
			if (objCategory.getActive()) {
				category = categoryRepository.findOneByCategoryID(categoryID);
				category.setActive(objCategory.getActive());
				categoryRepository.save(category); // Update();
				result.setResultWrapper(true, 1, null, MSG_UPDATE_SUCCESS, null);

			} else {
				String spName = "up_wa_SetCategoryInactive";
				List<Object> params = new ArrayList<Object>();
				
				params.add(categoryID);
				
				jdbcHelper.executeSP(spName, params);		
				result.setResultWrapper(true, 1, null, MSG_UPDATE_SUCCESS, null);
			}

			break;

		case "Del":
			category.setCategoryID(categoryID);
			categoryRepository.deleteById(categoryID);
			result.setResultWrapper(true, 1, null, MSG_DELETE_SUCCESS, null);

			break;

		default:
			break;
		}

		return result;
	}
	
	private void saveCategory(Category category, Category objCategory) {
		category.setCategoryName(objCategory.getCategoryName());
		category.setCategoryDescription(objCategory.getCategoryDescription());
		category.setParentCategoryID(objCategory.getParentCategoryID());
		category.setParentParentCategoryID(objCategory.getParentCategoryID());
		category.setLvl(objCategory.getLvl());
		category.setTitleImage(objCategory.getTitleImage());
		category.setIsLandingPage(objCategory.getIsLandingPage());
		category.setIsFeatured(objCategory.getIsFeatured());
		category.setListOrder(objCategory.getListOrder());
		category.setActive(objCategory.getActive());
		categoryRepository.save(category); // Save, Update
	}

	/**
	 * 
	 * Set Category List Order
	 * 
	 * @since 2018. 10. 23.
	 * @author Nayeon Kim
	 * @param SetCategoryParameter
	 * @return
	 */
	@Transactional(value = "primaryTransactionManager")
	public List<CategoryListOrder> setCategoryListOrder(SetCategoryListOrderParameter parameters) {
		List<CategoryListOrder> result = new ArrayList<CategoryListOrder>();
		
		// set parameters
		final int categoryID = parameters.getCategoryid();
		final int parentCategoryID = parameters.getParentcategoryid();
		final int listOrder = parameters.getListorder();
		final int lvl = parameters.getLvl();
		int newListOrder = listOrder;
		
		final Category category = categoryRepository.findOneByCategoryID(categoryID);
		
		if (category != null) {
			final List<Category> CategoryList = categoryRepository.findByParentCategoryIDAndLvlAndCategoryIDNotOrderByListOrderAsc(parentCategoryID, lvl, categoryID);
			
			for (Category cs : CategoryList) {
				if (cs.getListOrder() >= listOrder) {
					newListOrder++;
					cs.setListOrder(newListOrder);
					categoryRepository.save(cs);
				}
			}
			
			category.setParentCategoryID(parentCategoryID);
			category.setListOrder(listOrder);
			categoryRepository.save(category);
			
			if (lvl == 2) {
				final List<Category> CategoryList2 = categoryRepository.findByParentCategoryIDAndLvlAndCategoryIDNot(parentCategoryID, 3, categoryID);
				
				for (Category cc : CategoryList2) {
					cc.setParentParentCategoryID(parentCategoryID);
					categoryRepository.save(cc);
				}
			}
		}
		
		final List<Category> ccc = categoryRepository.findByParentCategoryIDAndLvlOrderByListOrderAsc(parentCategoryID, lvl);
		
		if(!CollectionUtils.isEmpty(ccc)) {
			result = ccc.stream()
					.map(c -> new CategoryListOrder(c.getCategoryID(), c.getParentCategoryID(), c.getCategoryName(), c.getLvl(), c.getListOrder(), c.getActive()))
					.collect(Collectors.toList());
		}
		
		return result;
	}
	
	/**
    *
    * Get Category Vendor List
    *
    * @since 2018. 10. 25.
    * @author Nayeon Kim
    * @param GetCategoryVendorListParameter
    * @return GetCategoryVendorListResponse
    */
	@SuppressWarnings("unchecked")
	public GetCategoryVendorListResponse getCategoryVendorList(GetCategoryVendorListParameter parameters) {
		GetCategoryVendorListResponse result = new GetCategoryVendorListResponse();
		String spName = "up_wa_GetCategoryVendorList";
		List<Object> params = new ArrayList<Object>();
		
		params.add(parameters.getCategoryid());
		params.add(parameters.getVendorname());
		
		List<Object> _result = jdbcHelper.executeSP(spName, params, CategoryCount.class, CategoryVendor.class, CategoryVendorInfo.class);
		result.setCategoryCountlist((List<CategoryCount>) _result.get(0));
		result.setCategoryVendorList((List<CategoryVendor>) _result.get(1));
		result.setCategoryVendorInfoList((List<CategoryVendorInfo>) _result.get(2));
		return result;
	}
	

	/**
	 *
	 * Get Product Attributes Total
	 *
	 * @since 2018. 10. 25.
	 * @author Nayeon Kim
	 * @return GetProductAttributesTotalResponse
	 */
	@SuppressWarnings("unchecked")
	public GetProductAttributesTotalResponse getProductAttributesTotal() {
		GetProductAttributesTotalResponse result = new GetProductAttributesTotalResponse();
		String spName = "up_wa_GetItemFilter";

		List<Object> params = new ArrayList<Object>();
		List<Object> _result = jdbcHelper.executeSP(spName, params, PatternInfo.class, LengthInfo.class, StyleInfo.class, FabricInfo.class, BodySizeInfo.class, ColorListInfo.class);
		result.setPatternInfolist((List<PatternInfo>) _result.get(0));
		result.setLengthInfolist((List<LengthInfo>) _result.get(1));
		result.setStyleInfolist((List<StyleInfo>) _result.get(2));
		result.setFabricInfolist((List<FabricInfo>) _result.get(3));
		result.setBodySizeInfolist((List<BodySizeInfo>) _result.get(4));
		result.setColorListInfolist((List<ColorListInfo>) _result.get(5));
		return result;
	}

	/**
	 *
	 * Get Featured Item Count
	 *
	 * @since 2018. 10. 25.
	 * @author Nayeon Kim
	 * @param GetFeaturedItemCountParameter
	 * @return GetFeaturedItemCountResponse
	 */
	@SuppressWarnings("unchecked")
	public GetFeaturedItemCountResponse getFeaturedItemCount(String sDate) {
		GetFeaturedItemCountResponse result = new GetFeaturedItemCountResponse();
		String spName = "up_wa_GetFeaturedItemCount";
		List<Object> params = new ArrayList<Object>();
		
		params.add(sDate);

		List<Object> _result = jdbcHelper.executeSP(spName, params, FeaturedItemCount.class, FeaturedItem.class);
		result.setFeaturedItemCountlist((List<FeaturedItemCount>) _result.get(0));
		result.setFeaturedItemlist((List<FeaturedItem>) _result.get(1));
		return result;
	}
	
	/**
	 *
	 * Set Trend Report Sort
	 *
	 * @since 2018. 10. 29.
	 * @author Nayeon Kim
	 * @param SetTrendReportSortParameter
	 * @return ResultCode
	 */
	public ResultCode setTrendReportSort(String xMLDatas) {
		String spName = "up_wa_SetTrendReportSort";
		List<Object> params = new ArrayList<Object>();

		params.add(xMLDatas);

		jdbcHelper.executeSP(spName, params);
		return new ResultCode(true, 1, MSG_UPDATE_SUCCESS);
	}
   
	/**
	 *
	 * Get Last KMM Data
	 *
	 * @since 2018. 10. 29.
	 * @author Nayeon Kim
	 * @return List<TrendReportKmmImage>
	 */
	public List<TrendReportKmmImage> getLastKMMData() {
		List<TrendReportKmmImage> result = new ArrayList<TrendReportKmmImage>();
		List<TrendReport> trendReport = trendReportRepository.findAllByCuratedTypeOrderByTrendReportIDDesc(4);
		if (!CollectionUtils.isEmpty(trendReport)) {
			result = trendReport.stream().map(c -> new TrendReportKmmImage(c.getSquareImage(), c.getImage(),
					c.getMiniImage(), c.getkMMImage1(), c.getkMMImage2())).collect(Collectors.toList());
		}
		return result;
	}
	
	/**
	 * 
	 * Get TodayDealCalendarList
	 * 
	 * @since 2018. 10. 26.
	 * @author Incheol Jung
	 * @param parameters
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public GetTodayDealCalendarListResponse getTodayDealCalendarList(GetTodayDealCalendarListParameter parameters) {
		GetTodayDealCalendarListResponse result = new GetTodayDealCalendarListResponse();
		String spName = "up_wa_GetAdminTodayDealCalendarList";

		List<Object> params = new ArrayList<Object>();
		params.add(parameters.getSelectdate());
		params.add(parameters.getWholesalerid());

		List<Object> _result = jdbcHelper.executeSP(spName, params, ActiveTodayDealDetail.class, InactiveTodayDealDetail.class);
		result.setActiveTodayDeals((List<ActiveTodayDealDetail>) _result.get(0));
		result.setInactiveTodayDeals((List<InactiveTodayDealDetail>) _result.get(1));
		
		return result;
	}
	
	/**
	 * 
	 * Description Example
	 * 
	 * @since 2018. 10. 26.
	 * @author Incheol Jung
	 * @param todayDeal
	 * @return
	 */
	@Transactional(value = "primaryTransactionManager")
	public ResultCode setTodayDealCalendar(SetTodayDealCalendarParameter parameters) {
		ResultCode result = new ResultCode(true, 1, MSG_UPDATE_SUCCESS);
		TodayDeal todayDeal = this.todayDealRepository.findOneByTodayDealId(parameters.getTodayDealID());
		
		if(todayDeal != null) {
			todayDeal.setFromDate(parameters.getFromDate());
			todayDeal.setActive(parameters.getActive());
			
			if(parameters.getFromDate() != null) {
				todayDeal.setToDate(parameters.getFromDate().plusDays(1).minusSeconds(1));
			}
			
			if(parameters.getActive() == false) {
				todayDeal.setRevokedOn(LocalDateTime.now());
				todayDeal.setRevokedBy(Utility.getUsername());
			}
			
			this.todayDealRepository.save(todayDeal);
		}else {
			result.setResultCode(-1);
			result.setResultMsg("failure");
		}
		
		return result;
	}
	
	/**
	 * 
	 * Get VendorCategory
	 * 
	 * @since 2018. 10. 29.
	 * @author Incheol Jung
	 * @param wholesalerId
	 * @return
	 */
	public GetVendorCategoryResponse getVendorCategory(Integer wholesalerId) {
		GetVendorCategoryResponse result = new GetVendorCategoryResponse();
		List<VendorCategory> list = this.vendorCategoryRepository.findByWholeSalerIDAndActiveTrue(wholesalerId);
		result.setVendorCategorySummaryList(
				list.stream()
				.map(v -> new VendorCategorySummary(v.getVendorCategoryID(), v.getCategoryName()))
				.collect(Collectors.toList()));
		
		return result;
	}
	
	/**
	 * 
	 * Set NewTodayDeal
	 * 
	 * @since 2018. 10. 29.
	 * @author Incheol Jung
	 * @param parameters
	 * @return
	 */
	public Integer setNewTodayDeal(SetNewTodayDealParameter parameters) {
		TodayDeal todayDeal = new TodayDeal();
		todayDeal.setTitle("");
		todayDeal.setDescription("");
		todayDeal.setProductId(parameters.getProductID());
		todayDeal.setFromDate(parameters.getFromDate());
		todayDeal.setToDate(parameters.getToDate());
		todayDeal.setTodayDealPrice(parameters.getTodayDealPrice());
		todayDeal.setActive(true);
		todayDeal.setAppliedOn(LocalDateTime.now());
		todayDeal.setApprovedOn(LocalDateTime.now());
		todayDeal.setCreatedBy(Utility.getUsername());
		todayDeal.setModifiedBy(Utility.getUsername());
		todayDeal.setCreatedByVendor(false);
		
		this.todayDealRepository.save(todayDeal);
		
		return 1;
	}
}
