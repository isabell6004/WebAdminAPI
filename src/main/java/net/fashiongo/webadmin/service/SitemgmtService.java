package net.fashiongo.webadmin.service;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
//import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
//import org.apache.commons.lang3.time.FastDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import net.fashiongo.webadmin.common.Utility;
import net.fashiongo.webadmin.dao.fgem.EmConfigurationRepository;
import net.fashiongo.webadmin.dao.primary.CategoryRepository;
import net.fashiongo.webadmin.dao.primary.CodeFabricRepository;
import net.fashiongo.webadmin.dao.primary.CodeLengthRepository;
import net.fashiongo.webadmin.dao.primary.CodePatternRepository;
import net.fashiongo.webadmin.dao.primary.CodeStyleRepository;
import net.fashiongo.webadmin.dao.primary.CommunicationReasonRepository;
import net.fashiongo.webadmin.dao.primary.MapFabricCategoryRepository;
import net.fashiongo.webadmin.dao.primary.MapLengthCategoryRepository;
import net.fashiongo.webadmin.dao.primary.MapPatternCategoryRepository;
import net.fashiongo.webadmin.dao.primary.MapStyleCategoryRepository;
import net.fashiongo.webadmin.dao.primary.PolicyRepository;
import net.fashiongo.webadmin.dao.primary.TodayDealRepository;
import net.fashiongo.webadmin.dao.primary.TrendReportRepository;
import net.fashiongo.webadmin.dao.primary.VendorCatalogRepository;
import net.fashiongo.webadmin.dao.primary.VendorCatalogSendQueueRepository;
import net.fashiongo.webadmin.dao.primary.VendorCatalogSendRequestRepository;
import net.fashiongo.webadmin.dao.primary.VendorCategoryRepository;
import net.fashiongo.webadmin.model.fgem.EmConfiguration;
import net.fashiongo.webadmin.model.pojo.ActiveTodayDealDetail;
import net.fashiongo.webadmin.model.pojo.BodySizeInfo;
import net.fashiongo.webadmin.model.pojo.CategoryAdCount;
import net.fashiongo.webadmin.model.pojo.CategoryCount;
import net.fashiongo.webadmin.model.pojo.CategoryListOrder;
import net.fashiongo.webadmin.model.pojo.CategoryReport;
import net.fashiongo.webadmin.model.pojo.CategoryVendor;
import net.fashiongo.webadmin.model.pojo.CategoryVendorInfo;
import net.fashiongo.webadmin.model.pojo.CodeData;
import net.fashiongo.webadmin.model.pojo.ColorListInfo;
import net.fashiongo.webadmin.model.pojo.DMRequest;
import net.fashiongo.webadmin.model.pojo.DMRequestDetail;
import net.fashiongo.webadmin.model.pojo.FabricInfo;
import net.fashiongo.webadmin.model.pojo.FeaturedItem;
import net.fashiongo.webadmin.model.pojo.FeaturedItemCount;
import net.fashiongo.webadmin.model.pojo.FeaturedVendorDaily;
import net.fashiongo.webadmin.model.pojo.InactiveTodayDealDetail;
import net.fashiongo.webadmin.model.pojo.LengthInfo;
import net.fashiongo.webadmin.model.pojo.PatternInfo;
import net.fashiongo.webadmin.model.pojo.PolicyDetail;
import net.fashiongo.webadmin.model.pojo.ProductAttribute;
import net.fashiongo.webadmin.model.pojo.ProductColors;
import net.fashiongo.webadmin.model.pojo.ProductImage;
import net.fashiongo.webadmin.model.pojo.ProductInfo;
import net.fashiongo.webadmin.model.pojo.ProductSelectCheck;
import net.fashiongo.webadmin.model.pojo.ProductSize;
import net.fashiongo.webadmin.model.pojo.Result;
import net.fashiongo.webadmin.model.pojo.ResultCode;
import net.fashiongo.webadmin.model.pojo.ResultResponse;
import net.fashiongo.webadmin.model.pojo.SelectData;
import net.fashiongo.webadmin.model.pojo.StyleInfo;
import net.fashiongo.webadmin.model.pojo.TodayDealCalendarDetail;
import net.fashiongo.webadmin.model.pojo.TodayDealDetail;
import net.fashiongo.webadmin.model.pojo.Total;
import net.fashiongo.webadmin.model.pojo.TrendReportDefault;
import net.fashiongo.webadmin.model.pojo.TrendReportKmmImage;
import net.fashiongo.webadmin.model.pojo.TrendReportList;
import net.fashiongo.webadmin.model.pojo.VendorCategorySummary;
import net.fashiongo.webadmin.model.pojo.VendorCount;
import net.fashiongo.webadmin.model.pojo.VendorData1;
import net.fashiongo.webadmin.model.pojo.VendorSummary;
import net.fashiongo.webadmin.model.pojo.VendorSummaryDetail;
import net.fashiongo.webadmin.model.pojo.parameter.DeleteCommunicationReasonParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetCategoryListParameters;
import net.fashiongo.webadmin.model.pojo.parameter.GetCategoryVendorListParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetDMRequestParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetDMRequestSendListParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetFeaturedItemSearchParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetFeaturedItemSearchVendorParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetPolicyDetailParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetPolicyManagementDetailParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetProductAttributesParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetProductDetailParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetTodayDealCalendarListParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetTodayDealCanlendarParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetTodaydealParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetTrendReport2Parameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetTrendReportDefaultParameter;
import net.fashiongo.webadmin.model.pojo.parameter.PageSizeParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetCategoryListOrderParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetCategoryParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetCommunicationReasonActiveParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetCommunicationReasonParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetFGCatalogParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetNewTodayDealParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetPaidCampaignParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetProductAttributesMappingParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetProductAttributesParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetTodayDealCalendarParameter;
import net.fashiongo.webadmin.model.pojo.response.DeleteCommunicationReasonResponse;
import net.fashiongo.webadmin.model.pojo.response.GetCategoryListResponse;
import net.fashiongo.webadmin.model.pojo.response.GetCategoryVendorListResponse;
import net.fashiongo.webadmin.model.pojo.response.GetDMRequestResponse;
import net.fashiongo.webadmin.model.pojo.response.GetFeaturedItemCountResponse;
import net.fashiongo.webadmin.model.pojo.response.GetFeaturedItemListDayResponse;
import net.fashiongo.webadmin.model.pojo.response.GetFeaturedItemSearchResponse;
import net.fashiongo.webadmin.model.pojo.response.GetFeaturedItemSearchVendorResponse;
import net.fashiongo.webadmin.model.pojo.response.GetPaidCampaignResponse;
import net.fashiongo.webadmin.model.pojo.response.GetPolicyDetailResponse;
import net.fashiongo.webadmin.model.pojo.response.GetPolicyManagementDetailResponse;
import net.fashiongo.webadmin.model.pojo.response.GetPolicyManagementResponse;
import net.fashiongo.webadmin.model.pojo.response.GetProductAttributesResponse;
import net.fashiongo.webadmin.model.pojo.response.GetProductAttributesTotalResponse;
import net.fashiongo.webadmin.model.pojo.response.GetProductDetailResponse;
import net.fashiongo.webadmin.model.pojo.response.GetTodayDealCalendarListResponse;
import net.fashiongo.webadmin.model.pojo.response.GetTodayDealCalendarResponse;
import net.fashiongo.webadmin.model.pojo.response.GetTodaydealResponse;
import net.fashiongo.webadmin.model.pojo.response.GetTrendReport2Response;
import net.fashiongo.webadmin.model.pojo.response.GetTrendReportCategoryResponse;
import net.fashiongo.webadmin.model.pojo.response.GetTrendReportDefaultResponse;
import net.fashiongo.webadmin.model.pojo.response.GetVendorCategoryResponse;
import net.fashiongo.webadmin.model.pojo.response.GetVendorListResponse;
import net.fashiongo.webadmin.model.primary.Category;
import net.fashiongo.webadmin.model.primary.CodeFabric;
import net.fashiongo.webadmin.model.primary.CodeLength;
import net.fashiongo.webadmin.model.primary.CodePattern;
import net.fashiongo.webadmin.model.primary.CodeStyle;
import net.fashiongo.webadmin.model.primary.CollectionCategory;
import net.fashiongo.webadmin.model.primary.CommunicationReason;
import net.fashiongo.webadmin.model.primary.MapFabricCategory;
import net.fashiongo.webadmin.model.primary.MapLengthCategory;
import net.fashiongo.webadmin.model.primary.MapPatternCategory;
import net.fashiongo.webadmin.model.primary.MapStyleCategory;
import net.fashiongo.webadmin.model.primary.Policy;
import net.fashiongo.webadmin.model.primary.TodayDeal;
import net.fashiongo.webadmin.model.primary.TrendReport;
import net.fashiongo.webadmin.model.primary.VendorCatalog;
import net.fashiongo.webadmin.model.primary.VendorCatalogSendQueue;
import net.fashiongo.webadmin.model.primary.VendorCatalogSendRequest;
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
	private CodePatternRepository codePatternRepository;
	
	@Autowired
	private CodeLengthRepository codeLengthRepository;
	
	@Autowired
	private CodeStyleRepository codeStyleRepository;
	
	@Autowired
	private CodeFabricRepository codeFabricRepository;
	
	@Autowired
	private TodayDealRepository todayDealRepository;
	
	@Autowired
	private VendorCategoryRepository vendorCategoryRepository;
	
	@Autowired
	private TrendReportRepository trendReportRepository;
	
	@Autowired
	private VendorCatalogRepository vendorCatalogRepository;
	
	@Autowired
	private VendorCatalogSendQueueRepository vendorCatalogSendQueueRepository;
	
	@Autowired
	private VendorCatalogSendRequestRepository vendorCatalogSendRequestRepository;

	@Autowired
	private PolicyRepository policyRepository;
	
	@Autowired
	private CommunicationReasonRepository communicationReasonRepository;

	@Autowired
	private MapPatternCategoryRepository mapPatternCategoryRepository;
	
	@Autowired
	private MapLengthCategoryRepository mapLengthCategoryRepository;
	
	@Autowired
	private MapStyleCategoryRepository mapStyleCategoryRepository;
	
	@Autowired
	private MapFabricCategoryRepository mapFabricCategoryRepository;
	
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
	 * getPolicyManagement
	 *
	 * @since 2018. 10. 29.
	 * @author Dahye
	 * @param PageSizeParameter
	 * @return GetPolicyManagementResponse
	 */
	public GetPolicyManagementResponse getPolicyManagement (PageSizeParameter parameters) {
		GetPolicyManagementResponse results = new GetPolicyManagementResponse();
		Pageable req = PageRequest.of(parameters.getPageNum()-1, parameters.getPageSize(), new Sort(Direction.DESC, "EffectiveOn"));
		Page<Policy> result = policyRepository.findAll(req);
		results.setRecCnt(result.getTotalElements());
		results.setVpolicyList(result.getContent());
		return results;
	}

	/**
	 *
	 * setAddDelPolicyManagement
	 *
	 * @since 2018. 10. 30.
	 * @author Dahye
	 * @param SetAddDelPolicyManagementParameter
	 * @return ResultCode
	 */
	@Transactional("primaryTransactionManager")
	public ResultCode setAddDelPolicyManagement (String type, Policy objPolicy) {
		Policy pc = new Policy();
		String sessionUserID = Utility.getUsername();
		switch(type) {
		case "Upd":
			if(objPolicy.getPolicyID() > 0) pc = policyRepository.findOneByPolicyID(objPolicy.getPolicyID());
			pc.setPolicyTitle(objPolicy.getPolicyTitle());
			pc.setPolicyContents(objPolicy.getPolicyContents());
			pc.setForVendor(objPolicy.getForVendor());
			pc.setForRetailer(objPolicy.getForRetailer());
			pc.setEffectiveOn(objPolicy.getEffectiveOn());
			if (objPolicy.getPolicyID() < 1) {
				pc.setCreatedBy(sessionUserID);
				pc.setCreatedOn(objPolicy.getCreatedOn());
			}
			pc.setModifiedBy(sessionUserID);
			pc.setModifiedOn(objPolicy.getModifiedOn());
			pc.setActive(objPolicy.getActive());
			policyRepository.save(pc);
			break;
		case "Act":
			pc = policyRepository.findOneByPolicyID(objPolicy.getPolicyID());
			pc.setModifiedBy(sessionUserID);
			pc.setModifiedOn(objPolicy.getModifiedOn());
			pc.setActive(objPolicy.getActive());
			policyRepository.save(pc);
			break;
		}
		return new ResultCode(true, 1, MSG_SAVE_SUCCESS);
	}

	/**
	 *
	 * getPolicyDetail
	 *
	 * @since 2018. 10. 30.
	 * @author Dahye
	 * @param GetPolicyDetailParameter
	 * @return GetPolicyDetailResponse
	 */
	@SuppressWarnings("unchecked")
	public GetPolicyDetailResponse getPolicyDetail (GetPolicyDetailParameter parameters) {
		GetPolicyDetailResponse result = new GetPolicyDetailResponse();
		String spName = "up_GetPage";
		String filter = " 1=1";
		
		if(StringUtils.isNotEmpty(parameters.getSearchItem()) && StringUtils.isNotEmpty(parameters.getSearchTxt())) {
			filter += " And " + parameters.getSearchItem() + " like '%" + parameters.getSearchTxt() + "%'";
		}
		if(parameters.getPolicyID() > 0) {
			filter += " And PolicyID="+parameters.getPolicyID();
		}
		
		List<Object> params = new ArrayList<Object>();
		params.add(parameters.getPageNum());
		params.add(parameters.getPageSize());
		params.add("PolicyAgreement");
		params.add("PolicyAgreementID, PolicyID, WholeSalerID, CompanyName, RetailerID, AgreedOn, AgreedByName, AgreedByID, IPAddress, Agreed");
		params.add(filter);
		params.add("PolicyAgreementID desc");
		params.add(true);
		params.add(null);
		List<Object> _result = jdbcHelper.executeSP(spName, params, Total.class, PolicyDetail.class);
		result.setTotal((List<Total>) _result.get(0));
		result.setPolicyDetail((List<PolicyDetail>)_result.get(1));
				
		return result;
	}

	/**
	 *
	 *getPolicyManagementDetail
	 *
	 * @since 2018. 10. 31.
	 * @author Dahye
	 * @param GetPolicyManagementDetailParameter
	 * @return GetPolicyManagementDetailResponse
	 */
	public GetPolicyManagementDetailResponse getPolicyManagementDetail (GetPolicyManagementDetailParameter parameters) {
		GetPolicyManagementDetailResponse result = new GetPolicyManagementDetailResponse();
		Policy policy = policyRepository.findOneByPolicyIDOrderByEffectiveOn(parameters.getPolicyID());
		result.setPolicy(policy);
		return result;
	}

	/**
	 *
	 * getCommunicationReasonAll
	 *
	 * @since 2018. 10. 31.
	 * @author Dahye
	 * @param GetcommunicationreasonallParameter
	 * @return CommunicationReason
	 */
	public List<CommunicationReason> getCommunicationReasonAll () {
		List<CommunicationReason> result = communicationReasonRepository.findAll();
		return result;
	}

	/**
	 *
	 * deleteCommunicationReason
	 *
	 * @since 2018. 10. 31.
	 * @author Dahye
	 * @param DeleteCommunicationReasonParameter
	 * @return DeleteCommunicationReasonResponse
	 */
	@SuppressWarnings("unchecked")
	public DeleteCommunicationReasonResponse deleteCommunicationReason (DeleteCommunicationReasonParameter parameters) {
		DeleteCommunicationReasonResponse result = new DeleteCommunicationReasonResponse();
		String spName = "up_wa_DelCommunicationReason";
		List<Object> params = new ArrayList<Object>();
		params.add(parameters.getReasonIDs());
		List<Object> _result = jdbcHelper.executeSP(spName, params, Result.class);
		result.setResult((List<Result>) _result.get(0));
		return result;
	}

	/**
	 *
	 * setCommunicationReasonActive
	 *
	 * @since 2018. 10. 22.
	 * @author Dahye
	 * @param SetCommunicationReasonActiveParameter
	 * @return Integer
	 */
	public Integer setCommunicationReasonActive (SetCommunicationReasonActiveParameter parameters) {
		if(parameters.getReasonID() < 1) return -1;
		CommunicationReason result = communicationReasonRepository.findOneByReasonID(parameters.getReasonID());
		result.setActive(parameters.getActive());
		communicationReasonRepository.save(result);		
		return 1;
	}

	/**
	 *
	 * setCommunicationReason
	 *
	 * @since 2018. 10. 22.
	 * @author Dahye
	 * @param SetCommunicationReasonParameter
	 * @return Integer
	 */
	public Integer setCommunicationReason (SetCommunicationReasonParameter parameters) {
		CommunicationReason result = new CommunicationReason();
		if(parameters.getReasonID() > 0) result = communicationReasonRepository.findOneByReasonID(parameters.getReasonID());
		else result.setReasonID(0);
		result.setReason(parameters.getReason());
		result.setParentID(parameters.getParentID());
		result.setActive(parameters.getActive());
		communicationReasonRepository.save(result);
		return 1;
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
	 * @param StartDateParameter
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
	 * Get Featured Item List Day
	 *
	 * @since 2018. 10. 30.
	 * @author Nayeon Kim
	 * @param StartDateParameter
	 * @return GetFeaturedItemListDayResponse
	 */
	@SuppressWarnings("unchecked")
	public GetFeaturedItemListDayResponse getFeaturedItemListDay(String sDate) {
		GetFeaturedItemListDayResponse result = new GetFeaturedItemListDayResponse();
		String spName = "up_wa_GetFeaturedItemListDay";
		List<Object> params = new ArrayList<Object>();
		
		params.add(sDate);

		List<Object> _result = jdbcHelper.executeSP(spName, params, FeaturedItem.class);
		result.setFeaturedItemlist((List<FeaturedItem>) _result.get(0));
		return result;
	}
	
	/**
	 *
	 * Get Product Detail
	 *
	 * @since 2018. 10. 31.
	 * @author Nayeon Kim
	 * @param GetProductDetailParameter
	 * @return GetProductDetailResponse
	 */
	@SuppressWarnings("unchecked")
	public GetProductDetailResponse getProductDetail(GetProductDetailParameter prameters) {
		GetProductDetailResponse result = new GetProductDetailResponse();
		String spName = "up_wa_GetProductDetail";
		List<Object> params = new ArrayList<Object>();

		params.add(prameters.getProductID());
		params.add(prameters.getTrendReportID());
		
		List<Object> _result = jdbcHelper.executeSP(spName, params, ProductInfo.class, ProductImage.class, ProductColors.class, ProductSize.class, ProductSelectCheck.class);
		result.setProductInfolist((List<ProductInfo>) _result.get(0));
		result.setProductImagelist((List<ProductImage>) _result.get(1));
		result.setProductColorslist((List<ProductColors>) _result.get(2));
		result.setProductSizelist((List<ProductSize>) _result.get(3));
		result.setProductSelectChecklist((List<ProductSelectCheck>) _result.get(4));
		return result;
	}
   
	/**
	 *
	 * Get TrendReport Default
	 *
	 * @since 2018. 10. 31.
	 * @author Nayeon Kim
	 * @param GetTrendReportDefaultParameter
	 * @return GetTrendReportDefaultResponse
	 */
	@SuppressWarnings("unchecked")
	public GetTrendReportDefaultResponse getTrendReportDefault(GetTrendReportDefaultParameter prameters) {
		GetTrendReportDefaultResponse result = new GetTrendReportDefaultResponse();
		String spName = "up_wa_GetAdminTrendReportDefault";
		List<Object> params = new ArrayList<Object>();

		params.add(prameters.getPagenum());
		params.add(prameters.getPagesize());
		params.add(prameters.getOrderby());
		params.add(prameters.getOrderbygubn());

		List<Object> _result = jdbcHelper.executeSP(spName, params, Total.class, TrendReportDefault.class);
        result.setTotal((List<Total>) _result.get(0));
        result.setTrendReportDefault((List<TrendReportDefault>) _result.get(1));
        return result;
	}
	
	/**
	 *
	 * Get TrendReport V2
	 *
	 * @since 2018. 10. 31.
	 * @author Nayeon Kim
	 * @param GetTrendReport2Parameter
	 * @return GetTrendReport2Response
	 */
	@SuppressWarnings("unchecked")
	public GetTrendReport2Response getTrendReport2(GetTrendReport2Parameter prameters) {
		GetTrendReport2Response result = new GetTrendReport2Response();
		String spName = "up_wa_GetAdminTrendReport2";
		List<Object> params = new ArrayList<Object>();
		
		params.add(prameters.getPagenum());
		params.add(prameters.getPagesize());
		params.add(prameters.getSearchtxt());
		params.add(prameters.getFromdate());
		params.add(prameters.getTodate());
		params.add(prameters.getOrderby());
		params.add(prameters.getOrderbygubn());
		params.add(prameters.getActive());
		params.add(prameters.getCuratedType());
		
		List<Object> _result = jdbcHelper.executeSP(spName, params, Total.class, TrendReportList.class);
		result.setTotal((List<Total>) _result.get(0));
		result.setTrendReportList((List<TrendReportList>) _result.get(1));
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
	public TrendReportKmmImage getLastKMMData() {
		TrendReportKmmImage result = new TrendReportKmmImage();
		TrendReport trendReport = trendReportRepository.findTopByCuratedTypeOrderByTrendReportIDDesc(4);
		
		result.setSquareImage(trendReport.getSquareImage());
		result.setImage(trendReport.getImage());
		result.setMiniImage(trendReport.getMiniImage());
		result.setkMMImage1(trendReport.getkMMImage1());
		result.setkMMImage2(trendReport.getkMMImage2());
		
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
        net.fashiongo.webadmin.utility.Utility utl = new net.fashiongo.webadmin.utility.Utility();
		
        switch (parameter.getTabNo())
        {
            case 2:// "Length":
                DataSrc = "Code_Length";
                ColumnList = "LengthID As CodeID,LengthName As CodeName,Active";
                if (!utl.isNullOrEmpty(parameter.getAttrName()))
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
                if (!utl.isNullOrEmpty(parameter.getAttrName()))
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
                if (!utl.isNullOrEmpty(parameter.getAttrName()))
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
            case 5:// "Category Mapping":
                switch (parameter.getPrevTab())
                {
                    case 1:
                        DataSrc = "vwPatternCategory";
                        ColumnList = "MapID,PatternID As CodeID,PatternName As CodeName,Case When MapID > 0 Then Cast(1 As Bit) Else Cast(0 As Bit) End As Active";
                        Filter = Filter + " and CategoryID = " + parameter.getCategoryID() + "";
                        OrderBy = "PatternName";
                        break;
                    case 2:
                        DataSrc = "vwLengthCategory";
                        ColumnList = "MapID,LengthID As CodeID,LengthName As CodeName,Case When MapID > 0 Then Cast(1 As Bit) Else Cast(0 As Bit) End As Active";
                        Filter = Filter + " and CategoryID = " + parameter.getCategoryID() + "";
                        OrderBy = "LengthName";
                        break;
                    case 3:
                        DataSrc = "vwStyleCategory";
                        ColumnList = "MapID,StyleID As CodeID,StyleName As CodeName,Case When MapID > 0 Then Cast(1 As Bit) Else Cast(0 As Bit) End As Active";
                        Filter = Filter + " and CategoryID = " + parameter.getCategoryID() + "";
                        OrderBy = "StyleName";
                        break;
                    case 4:
                        DataSrc = "vwFabricCategory";
                        ColumnList = "MapID,FabricID As CodeID,FabricName As CodeName,Case When MapID > 0 Then Cast(1 As Bit) Else Cast(0 As Bit) End As Active";
                        Filter = Filter + " and CategoryID = " + parameter.getCategoryID() + "";
                        OrderBy = "FabricName";
                        break;
                }
                break;
            default://"Pattern":
            	DataSrc = "Code_Pattern";
                ColumnList = "PatternID As CodeID,PatternName As CodeName,Active";
                if (!utl.isNullOrEmpty(parameter.getAttrName()))
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
	 * Set NewTodayDeal
	 * 
	 * @since 2018. 10. 29.
	 * @author Incheol Jung
	 * @param parameters
	 * @return
	 */
	@Transactional("primaryTransactionManager")
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
	
	/**
	 * 
	 * Get FeaturedItem Search
	 * 
	 * @since 2018. 10. 30.
	 * @author Junghwan Lee
	 * @param parameters
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public GetFeaturedItemSearchResponse getFeaturedItemSearch(GetFeaturedItemSearchParameter parameters) {
		GetFeaturedItemSearchResponse result = new GetFeaturedItemSearchResponse();
		String spName = "up_wa_GetFeaturedItemsSearch";

		List<Object> params = new ArrayList<Object>();
		params.add(parameters.getPageNum());
		params.add(parameters.getPageSize());
		params.add(parameters.getFgCat());
		params.add(parameters.getVendorID());
		params.add(parameters.getSelectedCategoryID());
		params.add(parameters.getFromDate());
		params.add(parameters.getToDate());
		params.add(null);
		params.add(null);
		params.add(parameters.getBodySizeIDs());
		params.add(parameters.getPatternIDs());
		params.add(parameters.getLengthIDs());
		params.add(parameters.getStyleIDs());
		params.add(parameters.getFabricIDs());
		params.add(parameters.getColorNames());
		params.add(parameters.getSearchItemText());
		params.add("ProductDescription");
		params.add(null);
		params.add(null);
		params.add(null);
		params.add(null);
		params.add(null);
		params.add(parameters.getOrderBy());
		params.add(parameters.getVendorOrderBy());
		params.add(null);
		params.add(null);
		params.add(parameters.getSearchAndOr());
		params.add(parameters.getKeyword());
		params.add(parameters.getStyleNo());
		params.add(parameters.getVendorDateFrom());
		params.add(parameters.getVendorDateTo());
		params.add(parameters.getNeverUsed());
		
		List<Object> _result = jdbcHelper.executeSP(spName, params, CategoryAdCount.class, SelectData.class,
				VendorCount.class, VendorData1.class, FeaturedVendorDaily.class);
		result.setCategoryAdCount((List<CategoryAdCount>) _result.get(0));
		result.setSelectData((List<SelectData>) _result.get(1));
		result.setVendorCount((List<VendorCount>) _result.get(2));
		result.setVendorData1((List<VendorData1>) _result.get(3));
		result.setFeaturedVendorDaily((List<FeaturedVendorDaily>) _result.get(4));
		
		return result;
	}
	
	/**
	 * 
	 * Get FeaturedItem Search Vendor
	 * 
	 * @since 2018. 11. 1.
	 * @author Junghwan Lee
	 * @param parameters
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public GetFeaturedItemSearchVendorResponse getFeaturedItemSearchVendor(GetFeaturedItemSearchVendorParameter parameters) {
		GetFeaturedItemSearchVendorResponse result = new GetFeaturedItemSearchVendorResponse();
		String spName = "up_wa_GetFeaturedItemsSearchVendor";

		List<Object> params = new ArrayList<Object>();
		params.add(parameters.getPageNum());
		params.add(parameters.getPageSize());
		params.add(parameters.getFgCat());
		params.add(parameters.getVendorID());
		params.add(parameters.getSelectedCategoryID());
		params.add(parameters.getFromDate());
		params.add(parameters.getToDate());
		params.add(null);
		params.add(null);
		params.add(parameters.getBodySizeIDs());
		params.add(parameters.getPatternIDs());
		params.add(parameters.getLengthIDs());
		params.add(parameters.getStyleIDs());
		params.add(parameters.getFabricIDs());
		params.add(parameters.getColorNames());
		params.add(parameters.getSearchItemText());
		params.add("ProductDescription");
		params.add(null);
		params.add(null);
		params.add(null);
		params.add(null);
		params.add(null);
		params.add(null);
		params.add(null);
		params.add(null);
		params.add(parameters.getSearchAndOr());
		params.add(parameters.getKeyword());
		params.add(parameters.getStyleNo());
		params.add(parameters.getNeverUsed());
		
		List<Object> _result = jdbcHelper.executeSP(spName, params, CategoryAdCount.class, SelectData.class);
		result.setCategoryAdCount((List<CategoryAdCount>) _result.get(0));
		result.setSelectData((List<SelectData>) _result.get(1));
		return result;
	}	
	/**
	 * 
	 * Get DMRequest
	 * 
	 * @since 2018. 10. 29.
	 * @author Incheol Jung
	 * @param parameters
	 * @return
	 */
	public GetDMRequestResponse getDMRequest(GetDMRequestParameter parameters) {
		GetDMRequestResponse result = new GetDMRequestResponse();
		String spName = "up_wa_GetFGCatalog";

		List<Object> params = new ArrayList<Object>();
		params.add(parameters.getPagenum());
		params.add(parameters.getPagesize());
		params.add(parameters.getStatus());
		params.add(parameters.getWholesalerid());
		params.add(parameters.getCompanytypecd());
		params.add(parameters.getDatefrom());
		params.add(parameters.getDateto());
		params.add(parameters.getOrderby());

		List<Object> _result = jdbcHelper.executeSP(spName, params, DMRequest.class);
		result.setDmList((List<DMRequest>) _result.get(0));
		
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

    /**
	 * Get DMRequestSendList
	 * 
	 * @since 2018. 10. 29.
	 * @author Incheol Jung
	 * @param parameters
	 * @return
	 */
	public JSONObject getDMRequestSendList(GetDMRequestSendListParameter parameters) {
		JSONObject result = new JSONObject();
		List<DMRequestDetail> subList = null;
		
		for(Integer catalogId : parameters.getDmIds()) {
			subList = getDMDetail(catalogId);
			if(!CollectionUtils.isEmpty(subList)) {
				result.put(catalogId.toString(), getDMDetail(catalogId));
			}
		}
		return result;
	}
	
	/**
	 * Get DMRequestSendList2
	 * 
	 * @since 2018. 10. 29.
	 * @author Incheol Jung
	 * @param parameters
	 * @return
	 */
//	public JSONObject getDMRequestSendList2(GetDMRequestSendListParameter parameters) {
//		JSONObject result = new JSONObject();
//		List<DMRequestDetail> subList = null;
//		
//		String spName = "up_wa_DMSendList_Migration";
//		List<Object> params = new ArrayList<Object>();
//		params.add(parameters.getDmIds().toString());
//		
//		List<Object> _result = jdbcHelper.executeSP(spName, params, DMRequestDetail.class);
//		return result;
//	}
	
	/**
	 * 
	 * Get DMDetail
	 * 
	 * @since 2018. 10. 29.
	 * @author Incheol Jung
	 * @param catalogId
	 * @return
	 */
	private List<DMRequestDetail> getDMDetail(Integer catalogId) {
		String spName = "up_wa_DMSendList";
		List<Object> params = new ArrayList<Object>();
		params.add(catalogId);
		
		List<Object> _result = jdbcHelper.executeSP(spName, params, DMRequestDetail.class);
		return CollectionUtils.isEmpty(_result) ? null : (List<DMRequestDetail>) _result.get(0);
	}
	
	/**
	 * 
	 * Set FGCatalog
	 * 
	 * @since 2018. 10. 29.
	 * @author Incheol Jung
	 * @param parameters
	 * @return
	 */
	@Transactional("primaryTransactionManager")
	public ResultCode setFGCatalog(SetFGCatalogParameter parameters) {
		ResultCode result = new ResultCode(true, 1, "Sent Successfully!");
		VendorCatalogSendQueue vcsq = new VendorCatalogSendQueue();
		Integer fgCatalogId = null;
		
		if(parameters.getCatalogsendqueueid() <= 0) {
			VendorCatalog vc = this.saveVendorCatalog(parameters);
			
			fgCatalogId = vc.getCatalogID();
			vcsq.setCatalogID(vc.getCatalogID());
			vcsq.setIsThisToAllRetailer(true);
		}else {
			vcsq = this.vendorCatalogSendQueueRepository.findOneByCatalogSendQueueID(parameters.getCatalogsendqueueid());
			if(vcsq != null) {
				fgCatalogId = vcsq.getCatalogID();
			}
		}
		this.saveVendorCatalogSendQueue(vcsq, parameters);
		this.saveCatalogRequests(parameters, fgCatalogId);
		
		return result;
	}
	
	/**
	 * 
	 * Save CatalogRequests
	 * 
	 * @since 2018. 10. 30.
	 * @author Incheol Jung
	 * @param parameters
	 * @param fgCatalogId
	 */
	private void saveCatalogRequests(SetFGCatalogParameter parameters, Integer fgCatalogId) {
		VendorCatalogSendQueue vcsq = this.vendorCatalogSendQueueRepository.findFirstByOrderByCatalogSendQueueIDDesc();
		
		if(parameters.getVendorcode() != null) {
			List<VendorCatalogSendRequest> requests = this.vendorCatalogSendRequestRepository.findByCatalogSendRequestIDIn(parameters.getVendorcode());
			if(!CollectionUtils.isEmpty(requests)) {
				for(VendorCatalogSendRequest request: requests) {
					request.setCatalogSendQueueID(vcsq.getCatalogSendQueueID());
					request.setfGCatalogID(fgCatalogId);
				}
				this.vendorCatalogSendRequestRepository.saveAll(requests);
			}
		}else {
			VendorCatalogSendRequest request = new VendorCatalogSendRequest();
			request.setfGCatalogID(null);
			request.setCatalogSendQueueID(null);
			
			this.vendorCatalogSendRequestRepository.save(request);
		}
	}
	
	/**
	 * 
	 * Save VendorCatalog
	 * 
	 * @since 2018. 10. 30.
	 * @author Incheol Jung
	 * @param parameters
	 * @return
	 */
	private VendorCatalog saveVendorCatalog(SetFGCatalogParameter parameters) {
		VendorCatalog vc = new VendorCatalog();
		vc.setVendorID(0);
		vc.setCatalogName(parameters.getSubject());
		vc.setCreatedOn(LocalDateTime.now());
		vc.setModifiedOn(LocalDateTime.now());
		
		this.vendorCatalogRepository.save(vc);
		
		return vc;
	}
	
	/**
	 * 
	 * Save VendorCatalogSendQueue
	 * 
	 * @since 2018. 10. 30.
	 * @author Incheol Jung
	 * @param vcsq
	 * @param parameters
	 */
	private void saveVendorCatalogSendQueue(VendorCatalogSendQueue vcsq, SetFGCatalogParameter parameters) {
		vcsq.setSubject(parameters.getSubject());
		vcsq.setContents(parameters.getContents());
		vcsq.setCreatedOn(LocalDateTime.now());
		vcsq.setModifiedOn(LocalDateTime.now());
		vcsq.setScheduledSendOn(null);
		vcsq.setSentOn(LocalDateTime.now());
		vcsq.setActive(true);
		vcsq.setIsThisToAllRetailer(true);
		vcsq.setIsTestEmail(false);
		vcsq.setIncludedVendors(parameters.getIncludedvendors());
		this.vendorCatalogSendQueueRepository.save(vcsq);
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 10. 31.
	 * @author Reo
	 * @param parameter
	 * @return
	 */
	@Transactional(value = "primaryTransactionManager")
	public ResultCode setProductAttributesMapping(SetProductAttributesMappingParameter parameter) {
		ResultCode result = new ResultCode(false, 0, null);
		
		if(parameter.getTabNo().equals(2)) {  //MapLengthCategory Delete
			List<MapLengthCategory> lmlc = mapLengthCategoryRepository.findByCategoryIDIn(parameter.getCategoryID());
			List<MapLengthCategory> newLmlc = new ArrayList<MapLengthCategory>();
			for(MapLengthCategory mlc: lmlc) {
				MapLengthCategory newMlc = new MapLengthCategory();
				newMlc.setMapID(mlc.getMapID());
				newLmlc.add(newMlc);
			}
			mapLengthCategoryRepository.deleteAll(newLmlc);
		} else if(parameter.getTabNo().equals(3)) {  //MapStyleCategory Delete
			List<MapStyleCategory> lmsc = mapStyleCategoryRepository.findByCategoryIDIn(parameter.getCategoryID());
			List<MapStyleCategory> newLmsc = new ArrayList<MapStyleCategory>();
			for(MapStyleCategory msc: lmsc) {
				MapStyleCategory newMsc = new MapStyleCategory();
				newMsc.setMapID(msc.getMapID());
				newLmsc.add(newMsc);
			}
			mapStyleCategoryRepository.deleteAll(newLmsc);
		} else if(parameter.getTabNo().equals(4)) {  //MapFabricCategory Delete
			List<MapFabricCategory> lmfc = mapFabricCategoryRepository.findByCategoryIDIn(parameter.getCategoryID());
			List<MapFabricCategory> newLmfc = new ArrayList<MapFabricCategory>();
			for(MapFabricCategory mfc: lmfc) {
				MapFabricCategory newMfc = new MapFabricCategory();
				newMfc.setMapID(mfc.getMapID());
				newLmfc.add(newMfc);
			}
			mapFabricCategoryRepository.deleteAll(newLmfc);
		} else {  //MapPatternCategory Delete
			List<MapPatternCategory> lmpc = mapPatternCategoryRepository.findByCategoryIDIn(parameter.getCategoryID());
			List<MapPatternCategory> newLmpc = new ArrayList<MapPatternCategory>();
			for(MapPatternCategory mpc: lmpc) {
				MapPatternCategory newMpc = new MapPatternCategory();
				newMpc.setMapID(mpc.getMapID());
				newLmpc.add(newMpc);
			}
			mapPatternCategoryRepository.deleteAll(newLmpc);
		}
		
		List<MapPatternCategory> lmpc = new ArrayList<MapPatternCategory>();
		List<MapLengthCategory> lmlc = new ArrayList<MapLengthCategory>();
		List<MapStyleCategory> lmsc = new ArrayList<MapStyleCategory>();
		List<MapFabricCategory> lmfc = new ArrayList<MapFabricCategory>();
		for(ProductAttribute pa: parameter.getProductAttributeList()) {
			switch(parameter.getTabNo()) {
				case 2:  //MapLengthCategory Insert
					MapLengthCategory newMlc = new MapLengthCategory();
					newMlc.setLengthID(pa.getCodeID());
					newMlc.setCategoryID(pa.getCategoryID());
					lmlc.add(newMlc);
					break;
				case 3:  //MapStyleCategory Insert
					MapStyleCategory newMsc = new MapStyleCategory();
					newMsc.setStyleID(pa.getCodeID());
					newMsc.setCategoryID(pa.getCategoryID());
					lmsc.add(newMsc);
					break;
				case 4:  //MapFabricCategory Insert
					MapFabricCategory newMfc = new MapFabricCategory();
					newMfc.setFabricID(pa.getCodeID());
					newMfc.setCategoryID(pa.getCategoryID());
					lmfc.add(newMfc);
					break;
				default:  //MapPatternCategory Insert
					MapPatternCategory newMpc = new MapPatternCategory();
					newMpc.setPatternID(pa.getCodeID());
					newMpc.setCategoryID(pa.getCategoryID());
					lmpc.add(newMpc);
					break;
			}
		}
		if (parameter.getTabNo().equals(2)) {
			mapLengthCategoryRepository.saveAll(lmlc);
		} else if (parameter.getTabNo().equals(3)) {
			mapStyleCategoryRepository.saveAll(lmsc);
		} else if (parameter.getTabNo().equals(4)) {
			mapFabricCategoryRepository.saveAll(lmfc);
		} else {
			mapPatternCategoryRepository.saveAll(lmpc);
		}
		
		result.setResultCode(1);
		result.setResultMsg(MSG_INSERT_SUCCESS);
		result.setSuccess(true);
		return result;
	}
}
