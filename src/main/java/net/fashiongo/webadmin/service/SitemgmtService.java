package net.fashiongo.webadmin.service;

import java.text.ParseException;
import java.util.ArrayList;
//import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

//import org.apache.commons.lang3.time.FastDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import net.fashiongo.webadmin.dao.fgem.EmConfigurationRepository;
import net.fashiongo.webadmin.dao.primary.CategoryRepository;
import net.fashiongo.webadmin.dao.primary.CodeFabricRepository;
import net.fashiongo.webadmin.dao.primary.CodeLengthRepository;
import net.fashiongo.webadmin.dao.primary.CodePatternRepository;
import net.fashiongo.webadmin.dao.primary.CodeStyleRepository;
import net.fashiongo.webadmin.model.fgem.EmConfiguration;
import net.fashiongo.webadmin.model.pojo.ActiveTodayDealDetail;
import net.fashiongo.webadmin.model.pojo.BodySizeInfo;
import net.fashiongo.webadmin.model.pojo.CategoryCount;
import net.fashiongo.webadmin.model.pojo.CategoryListOrder;
import net.fashiongo.webadmin.model.pojo.CategoryReport;
import net.fashiongo.webadmin.model.pojo.CategoryVendor;
import net.fashiongo.webadmin.model.pojo.CategoryVendorInfo;
import net.fashiongo.webadmin.model.pojo.CodeData;
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
import net.fashiongo.webadmin.model.pojo.VendorSummary;
import net.fashiongo.webadmin.model.pojo.VendorSummaryDetail;
//import net.fashiongo.webadmin.model.pojo.Total;
import net.fashiongo.webadmin.model.pojo.parameter.GetCategoryListParameters;
import net.fashiongo.webadmin.model.pojo.parameter.GetCategoryVendorListParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetProductAttributesParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetTodayDealCalendarListParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetTodayDealCanlendarParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetTodaydealParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetCategoryListOrderParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetCategoryParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetPaidCampaignParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetProductAttributesParameter;
import net.fashiongo.webadmin.model.pojo.response.GetCategoryListResponse;
import net.fashiongo.webadmin.model.pojo.response.GetCategoryVendorListResponse;
import net.fashiongo.webadmin.model.pojo.response.GetFeaturedItemCountResponse;
import net.fashiongo.webadmin.model.pojo.response.GetPaidCampaignResponse;
import net.fashiongo.webadmin.model.pojo.response.GetProductAttributesResponse;
import net.fashiongo.webadmin.model.pojo.response.GetProductAttributesTotalResponse;
import net.fashiongo.webadmin.model.pojo.response.GetTodayDealCalendarListResponse;
import net.fashiongo.webadmin.model.pojo.response.GetTodayDealCalendarResponse;
import net.fashiongo.webadmin.model.pojo.response.GetTodaydealResponse;
import net.fashiongo.webadmin.model.pojo.response.GetTrendReportCategoryResponse;
import net.fashiongo.webadmin.model.pojo.response.GetVendorListResponse;
import net.fashiongo.webadmin.model.primary.Category;
import net.fashiongo.webadmin.model.primary.CodeFabric;
import net.fashiongo.webadmin.model.primary.CodeLength;
import net.fashiongo.webadmin.model.primary.CodePattern;
import net.fashiongo.webadmin.model.primary.CodeStyle;
import net.fashiongo.webadmin.model.primary.CollectionCategory;
import net.fashiongo.webadmin.utility.Utility;

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
	private CodePatternRepository codePatternRepository;
	
	@Autowired
	private CodeLengthRepository codeLengthRepository;
	
	@Autowired
	private CodeStyleRepository codeStyleRepository;
	
	@Autowired
	private CodeFabricRepository codeFabricRepository;

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
				
				@SuppressWarnings("unused")
				List<Object> _result = jdbcHelper.executeSP(spName, params, Integer.class);
				
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
	 * @return GetFeaturedItemCountParameter
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
	 * @since 2018. 10. 29.
	 * @author Reo
	 * @param parameter
	 * @return
	 */
	@SuppressWarnings({ "unused", "unchecked" })
	public GetProductAttributesResponse getProductAttributes(GetProductAttributesParameter parameter) {
		GetProductAttributesResponse result = new GetProductAttributesResponse();
		String DataSrc = null;
		String ColumnList = null;
		String Filter = " 1=1 ";
		String OrderBy = null;
        Boolean rActive = null;
		
        switch (parameter.getTabNo())
        {
            case 1://"Pattern":
                DataSrc = "Code_Pattern";
                ColumnList = "PatternID As CodeID,PatternName As CodeName,Active";
                if (!Utility.isNullOrEmpty(parameter.getAttrName()))
                {
                    Filter = Filter + " and PatternName like '%" + parameter.getAttrName() + "%'";
                }
                if (parameter.getActive() != null)
                {
                    rActive = parameter.getActive();
                    Filter = Filter + " and Active = '" + rActive + "'";
                }
                OrderBy = "PatternName";
                break;
            case 2:// "Length":
                DataSrc = "Code_Length";
                ColumnList = "LengthID As CodeID,LengthName As CodeName,Active";
                if (!Utility.isNullOrEmpty(parameter.getAttrName()))
                {
                    Filter = Filter + " and LengthName like '%" + parameter.getAttrName() + "%'";
                }
                if (parameter.getActive() != null)
                {
                    rActive = parameter.getActive();
                    Filter = Filter + " and Active = '" + rActive + "'";
                }
                OrderBy = "LengthName";
                break;
            case 3:// "Style":
                DataSrc = "Code_Style";
                ColumnList = "StyleID As CodeID,StyleName As CodeName,Active";
                if (!Utility.isNullOrEmpty(parameter.getAttrName()))
                {
                    Filter = Filter + " and StyleName like '%" + parameter.getAttrName() + "%'";
                }
                if (parameter.getActive() != null)
                {
                    rActive = parameter.getActive();
                    Filter = Filter + " and Active = '" + rActive + "'";
                }
                OrderBy = "StyleName";
                break;
            case 4:// "Fabric":
                DataSrc = "Code_Fabric";
                ColumnList = "FabricID As CodeID,FabricName As CodeName,Active";
                if (!Utility.isNullOrEmpty(parameter.getAttrName()))
                {
                    Filter = Filter + "and FabricName like '%" + parameter.getAttrName() + "%'";
                }
                if (parameter.getActive() != null)
                {
                    rActive = parameter.getActive();
                    Filter = Filter + " and Active = '" + rActive + "'";
                }
                OrderBy = "FabricName";
                break;
            case 5:// "Fabric":
                switch (parameter.getPrevTab())
                {
                    case 1:
                        DataSrc = "vwPatternCategory";
                        ColumnList = "MapID,PatternID As CodeID,PatternName As CodeName,Case When MapID > 0 Then 1 Else 0 End As Active";
                        Filter = Filter + " and CategoryID = " + parameter.getCategoryID() + "";
                        OrderBy = "PatternName";
                        break;
                    case 2:
                        DataSrc = "vwLengthCategory";
                        ColumnList = "MapID,LengthID As CodeID,LengthName As CodeName,Case When MapID > 0 Then 1 Else 0 End As Active";
                        Filter = Filter + " and CategoryID = " + parameter.getCategoryID() + "";
                        OrderBy = "LengthName";
                        break;
                    case 3:
                        DataSrc = "vwStyleCategory";
                        ColumnList = "MapID,StyleID As CodeID,StyleName As CodeName,Case When MapID > 0 Then 1 Else 0 End As Active";
                        Filter = Filter + " and CategoryID = " + parameter.getCategoryID() + "";
                        OrderBy = "StyleName";
                        break;
                    case 4:
                        DataSrc = "vwFabricCategory";
                        ColumnList = "MapID,FabricID As CodeID,FabricName As CodeName,Case When MapID > 0 Then 1 Else 0 End As Active";
                        Filter = Filter + " and CategoryID = " + parameter.getCategoryID() + "";
                        OrderBy = "FabricName";
                        break;
                }
                break;
        }
        
        String spName = "up_GetPage";
        List<Object> params = new ArrayList<Object>();
        params.add(1);
        params.add(1000);
        params.add(DataSrc);
        params.add(ColumnList);
        params.add(Filter);
        params.add(OrderBy);
        params.add(true);
        params.add(null);
        List<Object> _result = jdbcHelper.executeSP(spName, params, Total.class, CodeData.class);
        result.setRecCnt((List<Total>) _result.get(0)); 
        result.setCodeDataList((List<CodeData>) _result.get(1));
		return result;
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 10. 29.
	 * @author Reo
	 * @return
	 */
	@Transactional(value = "primaryTransactionManager")
	public ResultCode setProductAttributes(SetProductAttributesParameter parameter)
	{
		ResultCode result = new ResultCode(false, 0, null);
		
		switch (parameter.getTabNo())
        {
            case 1:  //Pattern
            	List<CodePattern> newCodePatternList = new ArrayList<CodePattern>();
            	if (parameter.getbType().equals("ADel")) {
	            	for (CodeData cd: parameter.getCodeDataList()) {
	            		CodePattern newCp = new CodePattern();
		            	newCp.setPatternID(cd.getCodeID());
	            		newCodePatternList.add(newCp);
	            	}
	            	codePatternRepository.deleteAll(newCodePatternList);
	            	
            		result.setResultCode(1);
            		result.setSuccess(true);
            		result.setResultMsg(MSG_DELETE_SUCCESS);
            	} else {
            		CodePattern newCp = new CodePattern();
            		if (parameter.getbType().equals("Del")) {
		            	newCp.setPatternID(parameter.getCodeID());
	            		newCodePatternList.add(newCp);
	            		codePatternRepository.delete(newCp);
	            		
	            		result.setResultCode(1);
	            		result.setSuccess(true);
	            		result.setResultMsg(MSG_DELETE_SUCCESS);
	            	} else if (parameter.getCodeID() > 0) {
	            		newCp = codePatternRepository.findOneByPatternID(parameter.getCodeID());
	    				newCp.setPatternName(parameter.getAttrName());
	            		newCp.setActive(parameter.getActive());
	            		codePatternRepository.save(newCp);
	            		
	            		result.setResultCode(1);
	            		result.setSuccess(true);
	            		result.setResultMsg(MSG_UPDATE_SUCCESS);
	            	} else {
	            		newCp.setPatternID(parameter.getCodeID());
	    				newCp.setPatternName(parameter.getAttrName());
	            		newCp.setActive(parameter.getActive());
	            		codePatternRepository.save(newCp);
	            		
	            		result.setResultCode(1);
	            		result.setSuccess(true);
	            		result.setResultMsg(MSG_INSERT_SUCCESS);
	            	}
            	}
            	break;
            case 2:  //Length
            	List<CodeLength> newCodeLengthList = new ArrayList<CodeLength>();
            	if (parameter.getbType().equals("ADel")) {
	            	for (CodeData cd: parameter.getCodeDataList()) {
	            		CodeLength newCl = new CodeLength();
	            		newCl.setLengthID(cd.getCodeID());
	            		newCodeLengthList.add(newCl);
	            	}
	            	codeLengthRepository.deleteAll(newCodeLengthList);
	            	
            		result.setResultCode(1);
            		result.setSuccess(true);
            		result.setResultMsg(MSG_DELETE_SUCCESS);
            	} else {
            		CodeLength newCl = new CodeLength();
            		if (parameter.getbType().equals("Del")) {
            			newCl.setLengthID(parameter.getCodeID());
	            		newCodeLengthList.add(newCl);
	            		codeLengthRepository.delete(newCl);
	            		
	            		result.setResultCode(1);
	            		result.setSuccess(true);
	            		result.setResultMsg(MSG_DELETE_SUCCESS);
	            	} else if (parameter.getCodeID() > 0) {
	            		newCl = codeLengthRepository.findOneByLengthID(parameter.getCodeID());
	            		newCl.setLengthName(parameter.getAttrName());
	            		newCl.setActive(parameter.getActive());
	            		codeLengthRepository.save(newCl);
	            		
	            		result.setResultCode(1);
	            		result.setSuccess(true);
	            		result.setResultMsg(MSG_UPDATE_SUCCESS);
	            	} else {
	            		newCl.setLengthID(parameter.getCodeID());
	            		newCl.setLengthName(parameter.getAttrName());
	            		newCl.setActive(parameter.getActive());
	            		codeLengthRepository.save(newCl);
	            		
	            		result.setResultCode(1);
	            		result.setSuccess(true);
	            		result.setResultMsg(MSG_INSERT_SUCCESS);
	            	}
            	}
            	break;
            case 3:  //Style
            	List<CodeStyle> newCodeStyleList = new ArrayList<CodeStyle>();
            	if (parameter.getbType().equals("ADel")) {
	            	for (CodeData cd: parameter.getCodeDataList()) {
	            		CodeStyle newCs = new CodeStyle();
	            		newCs.setStyleID(cd.getCodeID());
	            		newCodeStyleList.add(newCs);
	            	}
	            	codeStyleRepository.deleteAll(newCodeStyleList);
	            	
            		result.setResultCode(1);
            		result.setSuccess(true);
            		result.setResultMsg(MSG_DELETE_SUCCESS);
            	} else {
            		CodeStyle newCs = new CodeStyle();
            		if (parameter.getbType().equals("Del")) {
            			newCs.setStyleID(parameter.getCodeID());
	            		newCodeStyleList.add(newCs);
	            		codeStyleRepository.delete(newCs);
	            		
	            		result.setResultCode(1);
	            		result.setSuccess(true);
	            		result.setResultMsg(MSG_DELETE_SUCCESS);
	            	} else if (parameter.getCodeID() > 0) {
	            		newCs = codeStyleRepository.findOneByStyleID(parameter.getCodeID());
	            		newCs.setStyleName(parameter.getAttrName());
	            		newCs.setActive(parameter.getActive());
	            		codeStyleRepository.save(newCs);
	            		
	            		result.setResultCode(1);
	            		result.setSuccess(true);
	            		result.setResultMsg(MSG_UPDATE_SUCCESS);
	            	} else {
	            		newCs.setStyleID(parameter.getCodeID());
	            		newCs.setStyleName(parameter.getAttrName());
	            		newCs.setActive(parameter.getActive());
	            		codeStyleRepository.save(newCs);
	            		
	            		result.setResultCode(1);
	            		result.setSuccess(true);
	            		result.setResultMsg(MSG_INSERT_SUCCESS);
	            	}
            	}
            	break;
            case 4:  //Fabric
            	List<CodeFabric> newCodeFabricList = new ArrayList<CodeFabric>();
            	if (parameter.getbType().equals("ADel")) {
	            	for (CodeData cd: parameter.getCodeDataList()) {
	            		CodeFabric newCf = new CodeFabric();
	            		newCf.setFabricID(cd.getCodeID());
	            		newCodeFabricList.add(newCf);
	            	}
	            	codeFabricRepository.deleteAll(newCodeFabricList);
	            	
            		result.setResultCode(1);
            		result.setSuccess(true);
            		result.setResultMsg(MSG_DELETE_SUCCESS);
            	} else {
            		CodeFabric newCf = new CodeFabric();
            		if (parameter.getbType().equals("Del")) {
            			newCf.setFabricID(parameter.getCodeID());
	            		newCodeFabricList.add(newCf);
	            		codeFabricRepository.delete(newCf);
	            		
	            		result.setResultCode(1);
	            		result.setSuccess(true);
	            		result.setResultMsg(MSG_DELETE_SUCCESS);
	            	} else if (parameter.getCodeID() > 0) {
	            		newCf = codeFabricRepository.findOneByFabricID(parameter.getCodeID());
	            		newCf.setFabricName(parameter.getAttrName());
	            		newCf.setActive(parameter.getActive());
	            		codeFabricRepository.save(newCf);
	            		
	            		result.setResultCode(1);
	            		result.setSuccess(true);
	            		result.setResultMsg(MSG_UPDATE_SUCCESS);
	            	} else {
	            		newCf.setFabricID(parameter.getCodeID());
	            		newCf.setFabricName(parameter.getAttrName());
	            		newCf.setActive(parameter.getActive());
	            		codeFabricRepository.save(newCf);
	            		
	            		result.setResultCode(1);
	            		result.setSuccess(true);
	            		result.setResultMsg(MSG_INSERT_SUCCESS);
	            	}
            	}
            	break;
        }
		return result;
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 10. 29.
	 * @author Reo
	 * @param parameter
	 * @return
	 */
	@Transactional(value = "primaryTransactionManager")
	public ResultCode setProductAttributesActive(SetProductAttributesParameter parameter)
	{
		ResultCode result = new ResultCode(false, 0, null);
		
		//1: Pattern, 2: Length, 3: Style, 4: Fabric
	    if (parameter.getTabNo().equals(1)) {
	    	CodePattern codePattern = codePatternRepository.findOneByPatternID(parameter.getCodeID());
	    	codePattern.setActive(parameter.getActive());
	    	codePatternRepository.save(codePattern);
	    } else if (parameter.getTabNo().equals(2)) {
	    	CodeLength codeLength = codeLengthRepository.findOneByLengthID(parameter.getCodeID());
	    	codeLength.setActive(parameter.getActive());
	    	codeLengthRepository.save(codeLength);
	    } else if (parameter.getTabNo().equals(3)) {
	    	CodeStyle codeStyle = codeStyleRepository.findOneByStyleID(parameter.getCodeID());
	    	codeStyle.setActive(parameter.getActive());
	    	codeStyleRepository.save(codeStyle);
	    } else if (parameter.getTabNo().equals(4)) {
	    	CodeFabric codeFabric = codeFabricRepository.findOneByFabricID(parameter.getCodeID());
	    	codeFabric.setActive(parameter.getActive());
	    	codeFabricRepository.save(codeFabric);
	    }
	    
	    result.setResultCode(1);
		result.setSuccess(true);
		result.setResultMsg(MSG_CHANGE_SUCCESS);
		return result;
	}
}
