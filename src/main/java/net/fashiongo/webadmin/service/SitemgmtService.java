package net.fashiongo.webadmin.service;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;

import io.netty.util.internal.StringUtil;
import net.fashiongo.webadmin.utility.Utility;
import net.fashiongo.webadmin.dao.fgem.EmConfigurationRepository;
import net.fashiongo.webadmin.dao.primary.CategoryRepository;
import net.fashiongo.webadmin.dao.primary.CodeFabricRepository;
import net.fashiongo.webadmin.dao.primary.CodeLengthRepository;
import net.fashiongo.webadmin.dao.primary.CodePatternRepository;
import net.fashiongo.webadmin.dao.primary.CodeStyleRepository;
import net.fashiongo.webadmin.dao.primary.CommunicationReasonRepository;
import net.fashiongo.webadmin.dao.primary.FeaturedItemRepository;
import net.fashiongo.webadmin.dao.primary.MapFabricCategoryRepository;
import net.fashiongo.webadmin.dao.primary.MapLengthCategoryRepository;
import net.fashiongo.webadmin.dao.primary.MapPatternCategoryRepository;
import net.fashiongo.webadmin.dao.primary.MapStyleCategoryRepository;
import net.fashiongo.webadmin.dao.primary.PolicyRepository;
import net.fashiongo.webadmin.dao.primary.TodayDealRepository;
import net.fashiongo.webadmin.dao.primary.TrendReportContentsRepository;
import net.fashiongo.webadmin.dao.primary.TrendReportRepository;
import net.fashiongo.webadmin.dao.primary.VendorCatalogRepository;
import net.fashiongo.webadmin.dao.primary.VendorCatalogSendQueueRepository;
import net.fashiongo.webadmin.dao.primary.VendorCatalogSendRequestRepository;
import net.fashiongo.webadmin.dao.primary.VendorCategoryRepository;
import net.fashiongo.webadmin.model.fgem.EmConfiguration;
import net.fashiongo.webadmin.model.pojo.sitemgmt.ActiveTodayDealDetail;
import net.fashiongo.webadmin.model.pojo.sitemgmt.CodeData;
import net.fashiongo.webadmin.model.pojo.sitemgmt.DMRequest;
import net.fashiongo.webadmin.model.pojo.sitemgmt.DMRequestDetail;
import net.fashiongo.webadmin.model.pojo.sitemgmt.FeaturedVendorDaily;
import net.fashiongo.webadmin.model.pojo.sitemgmt.InactiveTodayDealDetail;
import net.fashiongo.webadmin.model.pojo.sitemgmt.ProductAttribute;
import net.fashiongo.webadmin.model.pojo.sitemgmt.ProductSelectCheck;
import net.fashiongo.webadmin.model.pojo.sitemgmt.TrendReportDefault;
import net.fashiongo.webadmin.model.pojo.sitemgmt.TrendReportItem;
import net.fashiongo.webadmin.model.pojo.sitemgmt.TrendReportList;
import net.fashiongo.webadmin.model.pojo.sitemgmt.VendorCategorySummary;
import net.fashiongo.webadmin.model.pojo.ad.CategoryAdCount;
import net.fashiongo.webadmin.model.pojo.ad.SelectData;
import net.fashiongo.webadmin.model.pojo.ad.VendorCount;
import net.fashiongo.webadmin.model.pojo.ad.VendorData1;
import net.fashiongo.webadmin.model.pojo.common.PagedResult;
import net.fashiongo.webadmin.model.pojo.common.Result;
import net.fashiongo.webadmin.model.pojo.common.ResultCode;
import net.fashiongo.webadmin.model.pojo.common.ResultResponse;
import net.fashiongo.webadmin.model.pojo.common.SingleValueResult;
import net.fashiongo.webadmin.model.pojo.message.Total;
import net.fashiongo.webadmin.model.pojo.sitemgmt.BodySizeInfo;
import net.fashiongo.webadmin.model.pojo.sitemgmt.CategoryCount;
import net.fashiongo.webadmin.model.pojo.sitemgmt.CategoryListOrder;
import net.fashiongo.webadmin.model.pojo.sitemgmt.CategoryReport;
import net.fashiongo.webadmin.model.pojo.sitemgmt.CategoryVendor;
import net.fashiongo.webadmin.model.pojo.sitemgmt.CategoryVendorInfo;
import net.fashiongo.webadmin.model.pojo.sitemgmt.ColorListInfo;
import net.fashiongo.webadmin.model.pojo.sitemgmt.FabricInfo;
import net.fashiongo.webadmin.model.pojo.sitemgmt.FeaturedItemCount;
import net.fashiongo.webadmin.model.pojo.sitemgmt.FeaturedItemList;
import net.fashiongo.webadmin.model.pojo.sitemgmt.LengthInfo;
import net.fashiongo.webadmin.model.pojo.sitemgmt.PatternInfo;
import net.fashiongo.webadmin.model.pojo.sitemgmt.ProductColors;
import net.fashiongo.webadmin.model.pojo.sitemgmt.ProductImage;
import net.fashiongo.webadmin.model.pojo.sitemgmt.ProductInfo;
import net.fashiongo.webadmin.model.pojo.sitemgmt.ProductSize;
import net.fashiongo.webadmin.model.pojo.sitemgmt.StyleInfo;
import net.fashiongo.webadmin.model.pojo.sitemgmt.TodayDealCalendarDetail;
import net.fashiongo.webadmin.model.pojo.sitemgmt.TodayDealDetail;
import net.fashiongo.webadmin.model.pojo.sitemgmt.TrendReportKmmImage;
import net.fashiongo.webadmin.model.pojo.sitemgmt.VendorSummary;
import net.fashiongo.webadmin.model.pojo.sitemgmt.VendorSummaryDetail;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.DelFeaturedItemParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.DeleteCommunicationReasonParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.GetCategoryListParameters;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.GetCategoryVendorListParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.GetDMRequestParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.GetDMRequestSendListParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.GetFeaturedItemSearchParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.GetFeaturedItemSearchVendorParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.GetItemsParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.GetPolicyDetailParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.GetPolicyManagementDetailParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.GetProductAttributesParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.GetProductDetailParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.GetTodayDealCalendarListParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.GetTodayDealCanlendarParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.GetTodaydealParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.GetTrendReport2Parameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.GetTrendReportDefaultParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.GetTrendReportItemParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.PageSizeParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.SetCategoryListOrderParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.SetCategoryParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.SetCommunicationReasonActiveParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.SetCommunicationReasonParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.SetFGCatalogParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.SetFeaturedItemParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.SetNewTodayDealParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.SetPaidCampaignParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.SetProductAttributesMappingParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.SetProductAttributesParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.SetTodayDealCalendarParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.SetTrendReportMapParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.SetTrendReportParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.response.DeleteCommunicationReasonResponse;
import net.fashiongo.webadmin.model.pojo.sitemgmt.response.GetCategoryListResponse;
import net.fashiongo.webadmin.model.pojo.sitemgmt.response.GetCategoryVendorListResponse;
import net.fashiongo.webadmin.model.pojo.sitemgmt.response.GetDMRequestResponse;
import net.fashiongo.webadmin.model.pojo.sitemgmt.response.GetFeaturedItemCountResponse;
import net.fashiongo.webadmin.model.pojo.sitemgmt.response.GetFeaturedItemListDayResponse;
import net.fashiongo.webadmin.model.pojo.sitemgmt.response.GetFeaturedItemSearchResponse;
import net.fashiongo.webadmin.model.pojo.sitemgmt.response.GetFeaturedItemSearchVendorResponse;
import net.fashiongo.webadmin.model.pojo.sitemgmt.response.GetItemsResponse;
import net.fashiongo.webadmin.model.pojo.sitemgmt.response.GetPaidCampaignResponse;
import net.fashiongo.webadmin.model.pojo.sitemgmt.response.GetPolicyDetailResponse;
import net.fashiongo.webadmin.model.pojo.sitemgmt.response.GetPolicyManagementDetailResponse;
import net.fashiongo.webadmin.model.pojo.sitemgmt.response.GetPolicyManagementResponse;
import net.fashiongo.webadmin.model.pojo.sitemgmt.response.GetProductAttributesResponse;
import net.fashiongo.webadmin.model.pojo.sitemgmt.response.GetProductAttributesTotalResponse;
import net.fashiongo.webadmin.model.pojo.sitemgmt.response.GetProductDetailResponse;
import net.fashiongo.webadmin.model.pojo.sitemgmt.response.GetTodayDealCalendarListResponse;
import net.fashiongo.webadmin.model.pojo.sitemgmt.response.GetTodayDealCalendarResponse;
import net.fashiongo.webadmin.model.pojo.sitemgmt.response.GetTodaydealResponse;
import net.fashiongo.webadmin.model.pojo.sitemgmt.response.GetTrendReport2Response;
import net.fashiongo.webadmin.model.pojo.sitemgmt.response.GetTrendReportCategoryResponse;
import net.fashiongo.webadmin.model.pojo.sitemgmt.response.GetTrendReportDefaultResponse;
import net.fashiongo.webadmin.model.pojo.sitemgmt.response.GetTrendReportItemResponse;
import net.fashiongo.webadmin.model.pojo.sitemgmt.response.GetVendorCategoryResponse;
import net.fashiongo.webadmin.model.pojo.sitemgmt.response.GetVendorListResponse;
import net.fashiongo.webadmin.model.primary.Category;
import net.fashiongo.webadmin.model.primary.CodeFabric;
import net.fashiongo.webadmin.model.primary.CodeLength;
import net.fashiongo.webadmin.model.primary.CodePattern;
import net.fashiongo.webadmin.model.primary.CodeStyle;
import net.fashiongo.webadmin.model.primary.CollectionCategory;
import net.fashiongo.webadmin.model.primary.CommunicationReason;
import net.fashiongo.webadmin.model.primary.EditorPickVendorContent;
import net.fashiongo.webadmin.model.primary.FeaturedItem;
import net.fashiongo.webadmin.model.primary.MapFabricCategory;
import net.fashiongo.webadmin.model.primary.MapLengthCategory;
import net.fashiongo.webadmin.model.primary.MapPatternCategory;
import net.fashiongo.webadmin.model.primary.MapStyleCategory;
import net.fashiongo.webadmin.model.primary.Policy;
import net.fashiongo.webadmin.model.primary.PolicyAgreement;
import net.fashiongo.webadmin.model.primary.QEditorPickVendorContent;
import net.fashiongo.webadmin.model.primary.TodayDeal;
import net.fashiongo.webadmin.model.primary.TrendReport;
import net.fashiongo.webadmin.model.primary.TrendReportContents;
import net.fashiongo.webadmin.model.primary.VendorCatalog;
import net.fashiongo.webadmin.model.primary.VendorCatalogSendQueue;
import net.fashiongo.webadmin.model.primary.VendorCatalogSendRequest;
import net.fashiongo.webadmin.model.primary.VendorCategory;
import net.fashiongo.webadmin.utility.JsonResponse;

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

	@Autowired
	private FeaturedItemRepository featuredItemRepository;
	
	@Autowired
	private TrendReportContentsRepository trendReportContentRepository;
	
	private net.fashiongo.webadmin.utility.Utility uUtility;
	
	@PersistenceContext(unitName = "primaryEntityManager")
	private EntityManager entityManager;

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
		if (!CollectionUtils.isEmpty(emConfigurationList)) {
			for (EmConfiguration emConfiguration2 : emConfigurationList) {
				EmConfiguration emConfiguration = new EmConfiguration();
				emConfiguration.setConfigID(emConfiguration2.getConfigID());
				emConfiguration.setConfigType(emConfiguration2.getConfigType());
				emConfiguration.setConfigValue(emConfiguration2.getConfigValue());
			}
			emConfigurationRepository.saveAll(emConfigurationList);
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
		ResultCode result = new ResultCode(false, 0, null);
		Policy pc = new Policy();
		String sessionUserID = Utility.getUsername();
		switch(type) {
		case "Upd":
			Integer policyID = objPolicy.getPolicyID() == null ? 0 : objPolicy.getPolicyID();
			if(policyID < 1) {
				pc.setPolicyTitle(objPolicy.getPolicyTitle());
				pc.setPolicyContents(objPolicy.getPolicyContents());
				pc.setForVendor(objPolicy.getForVendor());
				pc.setForRetailer(objPolicy.getForRetailer());
				pc.setEffectiveOn(objPolicy.getEffectiveOn());
				pc.setCreatedBy(sessionUserID);
				pc.setCreatedOn(objPolicy.getCreatedOn());
				pc.setModifiedBy(sessionUserID);
				pc.setModifiedOn(objPolicy.getModifiedOn());
				pc.setActive(objPolicy.getActive());
				
				result.setResultCode(1);
				result.setSuccess(true);
				result.setResultMsg(MSG_INSERT_SUCCESS);
			} else {
				pc = policyRepository.findOneByPolicyID(objPolicy.getPolicyID());
				pc.setPolicyID(objPolicy.getPolicyID());
				pc.setPolicyTitle(objPolicy.getPolicyTitle());
				if(StringUtils.isEmpty(objPolicy.getPolicyContents())) {
				    pc.setPolicyContents(objPolicy.getPolicyContents());
				}
				pc.setForVendor(objPolicy.getForVendor());
				pc.setForRetailer(objPolicy.getForRetailer());
				pc.setEffectiveOn(objPolicy.getEffectiveOn());
				pc.setModifiedBy(sessionUserID);
				pc.setModifiedOn(objPolicy.getModifiedOn());
				pc.setActive(objPolicy.getActive());
				
				result.setResultCode(1);
				result.setSuccess(true);
				result.setResultMsg(MSG_UPDATE_SUCCESS);
			}
			break;
		case "Act":
			pc = policyRepository.findOneByPolicyID(objPolicy.getPolicyID());
			pc.setModifiedBy(sessionUserID);
			pc.setModifiedOn(objPolicy.getModifiedOn());
			pc.setActive(objPolicy.getActive());
			policyRepository.save(pc);
			
			result.setResultCode(1);
			result.setSuccess(true);
			result.setResultMsg(MSG_UPDATE_SUCCESS);
			break;
		}
		policyRepository.save(pc);
		
		return result;
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
		List<Object> _result = jdbcHelper.executeSP(spName, params, Total.class, PolicyAgreement.class);
		result.setTotal((List<Total>) _result.get(0));
		result.setPolicyDetail((List<PolicyAgreement>)_result.get(1));
				
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
			result.setPk(this.saveCategory(category, objCategory));
			result.setResultWrapper(true, 1, category.getCategoryID(), MSG_SAVE_SUCCESS, null);

			break;

		case "Upd":
			category = categoryRepository.findOneByCategoryID(categoryID);
			result.setPk(this.saveCategory(category, objCategory));
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
	
	private Integer saveCategory(Category category, Category objCategory) {
		category.setCategoryName(objCategory.getCategoryName());
		category.setCategoryDescription(objCategory.getCategoryDescription());
		category.setParentCategoryID(objCategory.getParentCategoryID());
		category.setParentParentCategoryID(objCategory.getParentParentCategoryID());
		category.setLvl(objCategory.getLvl());
		category.setTitleImage(objCategory.getTitleImage());
		category.setIsLandingPage(objCategory.getIsLandingPage());
		category.setIsFeatured(objCategory.getIsFeatured());
		category.setListOrder(objCategory.getListOrder());
		category.setActive(objCategory.getActive());
		categoryRepository.save(category); // Save, Update
		
		return category.getCategoryID();
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
			
			if(!CollectionUtils.isEmpty(CategoryList)) {
				for (Category cs : CategoryList) {
					if (cs.getListOrder() >= listOrder) {
						newListOrder++;
						cs.setListOrder(newListOrder);
					}
				}
				categoryRepository.saveAll(CategoryList);
			}
			
			category.setParentCategoryID(parentCategoryID);
			category.setListOrder(listOrder);
			categoryRepository.save(category);
			
			if (lvl == 2) {
				final List<Category> CategoryList2 = categoryRepository.findByParentCategoryIDAndLvlAndCategoryIDNot(parentCategoryID, 3, categoryID);
				
				if(!CollectionUtils.isEmpty(CategoryList2)) {
					for (Category cc : CategoryList2) {
						cc.setParentParentCategoryID(parentCategoryID);
					}
					categoryRepository.saveAll(CategoryList2);
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

		List<Object> _result = jdbcHelper.executeSP(spName, params, FeaturedItemCount.class, FeaturedItemList.class);
		result.setFeaturedItemCount((List<FeaturedItemCount>) _result.get(0));
		result.setFeaturedItemList((List<FeaturedItemList>) _result.get(1));
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

		List<Object> _result = jdbcHelper.executeSP(spName, params, FeaturedItemList.class);
		result.setFeaturedItemList((List<FeaturedItemList>) _result.get(0));
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
    * Get TrendReport Item
    *
    * @since 2018. 11. 05.
    * @author Nayeon Kim
    * @param GetTrendReportItemParameter
    * @return GetTrendReportItemResponse
    */
   @SuppressWarnings("unchecked")
   public GetTrendReportItemResponse getTrendReportItem(GetTrendReportItemParameter prameters) {
       GetTrendReportItemResponse result = new GetTrendReportItemResponse();
       String spName = "up_wa_GetTrendReportItem";
       List<Object> params = new ArrayList<Object>();

       params.add(prameters.getPagenum());
       params.add(prameters.getPagesize());
       params.add(prameters.getTrendreportid());
       params.add(null);

       List<Object> _result = jdbcHelper.executeSP(spName, params, Total.class, TrendReportItem.class);
       result.setTotal((List<Total>) _result.get(0));
       result.setTrendReportItem((List<TrendReportItem>) _result.get(1));
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
	
	@Transactional(value = "primaryTransactionManager")
	public JsonResponse<String> setTrendReport(SetTrendReportParameter parameters) {
		JsonResponse<String> result = new JsonResponse<String>(false, null, 0, 0, null);
		TrendReport param = parameters.getTrendreport();

		TrendReport tr = trendReportRepository.findOneByTrendReportID(param.getTrendReportID());

		if (parameters.getType().equals("Del")) {
			trendReportRepository.delete(tr);
			result.setSuccess(true);
			result.setCode(1);
			result.setMessage("Deleted successfully!");
		} else if (parameters.getType().equals("Act")) {
			tr.setActive(param.getActive());
			trendReportRepository.save(tr);
			result.setSuccess(true);
			result.setCode(1);
			result.setMessage("Updated successfully!");
		} else if (parameters.getType().equals("Ins")) {
			if (param.getTrendReportID() < 1) {
				tr = new TrendReport();
				result.setMessage("Saved successfully!");
			} else {
				result.setMessage("Updated successfully!");
			}

			tr.setTitle(param.getTitle());
			tr.setDateFrom(param.getDateFrom());
			tr.setDateTo(param.getDateTo());
			tr.setImage(param.getImage());
			tr.setSquareImage(param.getSquareImage());
			tr.setMiniImage(param.getMiniImage());
			tr.setkMMImage1(param.getkMMImage1());
			tr.setkMMImage2(param.getkMMImage2());
			tr.setSticky(param.getSticky());
			tr.setCuratedType(param.getCuratedType() == null ? 3 : param.getCuratedType());
			tr.setCreatedOn(LocalDateTime.now());
			tr.setCreatedBy(Utility.getUsername());
			tr.setModifiedOn(LocalDateTime.now());
			tr.setModifiedBy(Utility.getUsername());
			tr.setListOrder(param.getListOrder());
			tr.setActive(param.getActive());
			tr.settRDescription(param.gettRDescription());
			trendReportRepository.save(tr);

			result.setSuccess(true);
			result.setPk(tr.getTrendReportID());
			result.setCode(1);

			if (param.getTrendReportID() < 1 && param.getCuratedType() == 4) {
				TrendReportContents trcb = trendReportContentRepository.findOneByTrendReportIDIsNull();
				TrendReportContents trc = new TrendReportContents();

				trc.setTrendReportID(tr.getTrendReportID());
				trcb.getContents().replace("trend_report/kmm-top.jpg", "trend_report/" + param.getkMMImage1());
				trcb.getContents().replace("trend_report/kmm-middle.jpg", "trend_report/" + param.getkMMImage2());
				trc.setContents(trcb.getContents());
				trc.setCreatedOn(LocalDateTime.now());
				trc.setCreatedBy(Utility.getUsername());
				trc.setModifiedOn(LocalDateTime.now());
				trc.setModifieidBy(Utility.getUsername());
				trendReportContentRepository.save(trc);
			}
		}

		return result;
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
		
        switch (parameter.getTabNo())
        {
            case 2:// "Length":
                DataSrc = "Code_Length";
                ColumnList = "LengthID As CodeID,LengthName As CodeName,Active";
                if (!uUtility.isNullOrEmpty(parameter.getAttrName()))
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
                if (!uUtility.isNullOrEmpty(parameter.getAttrName()))
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
                if (!uUtility.isNullOrEmpty(parameter.getAttrName()))
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
                    default:
                    	DataSrc = "vwPatternCategory";
                        ColumnList = "MapID,PatternID As CodeID,PatternName As CodeName,Case When MapID > 0 Then Cast(1 As Bit) Else Cast(0 As Bit) End As Active";
                        Filter = Filter + " and CategoryID = " + parameter.getCategoryID() + "";
                        OrderBy = "PatternName";
                        break;
                }
                break;
            default://"Pattern":
            	DataSrc = "Code_Pattern";
                ColumnList = "PatternID As CodeID,PatternName As CodeName,Active";
                if (!uUtility.isNullOrEmpty(parameter.getAttrName()))
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
				todayDeal.setNotes(parameters.getNotes());
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
	
	@SuppressWarnings("unchecked")
	public GetItemsResponse GetItems2(GetItemsParameter parameters) {
		GetItemsResponse result = new GetItemsResponse();
		String spName = "up_wa_GetItemsSearch2";
		List<Object> params = new ArrayList<Object>();

		params.add(parameters.getPageNum());
		params.add(parameters.getPageSize());
		params.add(parameters.getFgCat());
		params.add(parameters.getVendorID());
		params.add(parameters.getSelectedCategoryID());
		params.add(null);
		params.add(null);
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

		List<Object> _result = jdbcHelper.executeSP(spName, params, CategoryAdCount.class, SelectData.class);
        result.setCategoryAdCount((List<CategoryAdCount>) _result.get(0));
        result.setSelectData((List<SelectData>) _result.get(1));
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
            default:  //Pattern
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
	 * Set FeaturedItem
	 * 
	 * @since 2018. 11. 1.
	 * @author Junghwan Lee
	 * @param parameters
	 * @return
	 */
	@Transactional("primaryTransactionManager")
	public ResultCode setFeaturedItem(SetFeaturedItemParameter parameters) {
		ResultCode result = new ResultCode(false, 0, "Save Failed!");
		
		FeaturedItem featuredItem = new FeaturedItem();
		
		featuredItem.setWholeSalerID(parameters.getWholeSalerID());
		featuredItem.setFeaturedItemDate(parameters.getFromDate());
		featuredItem.setWholeSalerName(parameters.getCompanyName());
		featuredItem.setCreatedBy(Utility.getUsername());
		featuredItem.setCreatedOn(LocalDateTime.now());
		
		if (parameters.getSetType().equals("BestAdd")) {
			featuredItem.setProductID(0);
			featuredItem.setBestItemUse(1);
			featuredItemRepository.save(featuredItem);
			result.setSuccess(true);
			result.setResultCode(1);
			result.setResultMsg("Saved Successfully!");
		} else if (parameters.getSetType().equals("Add")) {
			featuredItem.setBestItemUse(0);
			featuredItem.setProductID(parameters.getProductID());
			featuredItem.setProductName(parameters.getProductName());
			featuredItemRepository.save(featuredItem);
			result.setSuccess(true);
			result.setResultCode(1);
			result.setResultMsg("Saved Successfully!");
		}
		
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
		params.add(parameters.getVendorstatus());
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
	 * Get DMRequestSendListOrigin
	 * 
	 * @since 2018. 10. 29.
	 * @author Incheol Jung
	 * @param parameters
	 * @return
	 */
	public JSONObject getDMRequestSendListOrigin(GetDMRequestSendListParameter parameters) {
		JSONObject result = new JSONObject();
		List<DMRequestDetail> subList = null;
		
		for(Integer catalogId : parameters.getDmIds()) {
			result.put(catalogId.toString(), getDMDetail(catalogId));
		}
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
		String spName = "up_wa_DMSendList_Migration";
		List<Object> params = new ArrayList<Object>();
		params.add(StringUtils.join(parameters.getDmIds(), ","));
		
		List<Object> _result = jdbcHelper.executeSP(spName, params, DMRequestDetail.class);
		List<DMRequestDetail> subList = (List<DMRequestDetail>) _result.get(0);
		
		Map<Integer, List<DMRequestDetail>> HashMapDmList = subList.stream()
				.collect(Collectors.groupingBy(DMRequestDetail::getCatalogID));

		for (Entry<Integer, List<DMRequestDetail>> entry : HashMapDmList.entrySet()) {
			result.put(entry.getKey(), entry.getValue());
		}
		
		return result;
	}
	
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
	

	/**
	 *
	 * delete Featured Item
	 *
	 * @since 2018. 11. 05.
	 * @author Sanghyup Kim
	 * @param 
	 * @return 
	 */
	public ResultResponse<Integer> delFeaturedItem(DelFeaturedItemParameter parameters) {
		ResultResponse<Integer> result = new ResultResponse<Integer>();

		Integer id = parameters.getFeaturedItemID();
		featuredItemRepository.deleteById(id);
		result.setResultWrapper(true, 1, id, MSG_DELETE_SUCCESS, id);
		return result;		
	}


	/**
	 *
	 * Set Trend Report Map
	 *
	 * @since 2018. 11. 05.
	 * @author Sanghyup Kim
	 * @param SetTrendReportMapParameter
	 * @return ResultCode
	 */
	public ResultCode setTrendReportMap(SetTrendReportMapParameter parameters) {
		String spName = "up_wa_AddDelTrendReportItem";
		List<Object> params = new ArrayList<Object>();

		params.add(parameters.getSetType());
		params.add(parameters.getMapId());
		params.add(parameters.getTrendreportId());
		params.add(parameters.getProductId());
		params.add(parameters.getModifiedBy());

		jdbcHelper.executeSP(spName, params);
		return new ResultCode(true, 1, MSG_UPDATE_SUCCESS);
	}
	
	
	/**
	 * @author Kenny/Kyungwoo
	 * @since 2019-04-29
	 */
	public PagedResult<EditorPickVendorContent> getEditorPickVendorContents(Integer pagenum, Integer pagesize,
			String title, String vendorName, LocalDateTime startDate, LocalDateTime endDate, String orderBy) {
		//0. Prepare Query types
		QEditorPickVendorContent epvc = QEditorPickVendorContent.editorPickVendorContent;
		PagedResult<EditorPickVendorContent> result = new PagedResult<>();
		
		//1. Build query
		JPAQuery<EditorPickVendorContent> query = new JPAQuery<>(entityManager);
		query
		.select(epvc)
		.from(epvc)
		.where(epvc.vendor.isNotNull()
				.and(epvc.vendorContent.isNotNull()));
        
		//2. Fill where conditions
        if(!StringUtil.isNullOrEmpty(title)) query.where(epvc.editorTitle.likeIgnoreCase(Expressions.asString("%").concat(title).concat("%")));
        if(!StringUtil.isNullOrEmpty(vendorName)) query.where(epvc.vendor.companyName.likeIgnoreCase(Expressions.asString("%").concat(vendorName).concat("%")));
        if(startDate!=null) query.where(epvc.startDate.goe(startDate));
        if(endDate!=null) query.where(epvc.startDate.loe(endDate));
        
        //3. Get the count first
        int totalCount = (int)query.fetchCount();
        
        //4. Set the page
        if(pagenum!=null && pagesize!=null) {
        	query.offset(pagesize*(pagenum-1));
        	query.limit(pagesize);
        }
        //4-1. Set orderBy
        if(orderBy!=null) {
        	if(orderBy.equals("vendorAsc")) query.orderBy(epvc.vendor.companyName.asc());
        	else if(orderBy.equals("vendorDesc")) query.orderBy(epvc.vendor.companyName.desc());
        	else if(orderBy.equals("titleAsc")) query.orderBy(epvc.editorTitle.asc());
        	else if(orderBy.equals("titleDesc")) query.orderBy(epvc.editorTitle.desc());
        	else if(orderBy.equals("startDateAsc")) query.orderBy(epvc.startDate.asc());
        	else if(orderBy.equals("startDateDesc")) query.orderBy(epvc.startDate.desc());
        	else if(orderBy.equals("endDateAsc")) query.orderBy(epvc.endDate.asc());
        	else if(orderBy.equals("endDateDesc")) query.orderBy(epvc.endDate.desc());
        	else if(orderBy.equals("createdByAsc")) query.orderBy(epvc.createdBy.asc());
        	else if(orderBy.equals("createdByDesc")) query.orderBy(epvc.createdBy.desc());
        }
        
        //5. Get the page
        List<EditorPickVendorContent> list = query.fetch();

        //6. Return
        SingleValueResult total = new SingleValueResult();
        total.setTotalCount(totalCount);
        result.setTotal(total);
        result.setRecords(list==null ? new ArrayList<EditorPickVendorContent>() : list);
        return result;
	}
}