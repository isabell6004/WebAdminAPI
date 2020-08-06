package net.fashiongo.webadmin.service;

import net.fashiongo.webadmin.dao.fgem.EmConfigurationRepository;
import net.fashiongo.webadmin.dao.primary.*;
import net.fashiongo.webadmin.data.entity.primary.CategoryEntity;
import net.fashiongo.webadmin.data.entity.primary.TrendDailyKeywordEntity;
import net.fashiongo.webadmin.data.repository.primary.TrendDailyKeywordEntityRepository;
import net.fashiongo.webadmin.model.fgem.EmConfiguration;
import net.fashiongo.webadmin.model.pojo.ad.CategoryAdCount;
import net.fashiongo.webadmin.model.pojo.ad.SelectData;
import net.fashiongo.webadmin.model.pojo.ad.VendorCount;
import net.fashiongo.webadmin.model.pojo.ad.VendorData1;
import net.fashiongo.webadmin.model.pojo.common.PagedResult;
import net.fashiongo.webadmin.model.pojo.common.Result;
import net.fashiongo.webadmin.model.pojo.common.ResultCode;
import net.fashiongo.webadmin.model.pojo.common.ResultResponse;
import net.fashiongo.webadmin.model.pojo.login.WebAdminLoginUser;
import net.fashiongo.webadmin.model.pojo.message.Total;
import net.fashiongo.webadmin.model.pojo.sitemgmt.*;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.*;
import net.fashiongo.webadmin.model.pojo.sitemgmt.response.*;
import net.fashiongo.webadmin.model.primary.*;
import net.fashiongo.webadmin.utility.HtmlUtility;
import net.fashiongo.webadmin.utility.JsonResponse;
import net.fashiongo.webadmin.utility.Utility;
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

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

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
	
	@Autowired
	private EditorPickVendorContentRepository editorPickVendorContentRepository;

	@Autowired
	private VendorContentRepository vendorContentRepository;
	
	@Autowired
	private ListVendorImageTypeRepository listVendorImageTypeRepository;
	
	@Autowired
	private VendorImageRequestRepository vendorImageRequestRepository;

	@Autowired
	private TrendDailyKeywordEntityRepository trendDailyKeywordEntityRepository;

	@Autowired
	private CacheService cacheService;

	private net.fashiongo.webadmin.utility.Utility uUtility;

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
			tr.setShowId(param.getShowId());
			tr.setShowScheduleId(param.getShowScheduleId());
			tr.setExternalURL(param.getExternalURL());
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
	 * Description Example
	 * @since 2018. 10. 29.
	 * @author Reo
	 * @param parameter
	 * @return
	 */
	@SuppressWarnings({"static-access","unchecked"})
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
			
			if(!parameters.getActive()) {
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
		params.add(parameters.getShowScheduleId());
		params.add(parameters.getShowItemOnly());
		params.add(parameters.getProductId());

		List<Object> _result = jdbcHelper.executeSP(spName, params, CategoryAdCount.class, SelectData.class);
        result.setCategoryAdCount((List<CategoryAdCount>) _result.get(0));
        result.setSelectData((List<SelectData>) _result.get(1));
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
		params.add(parameters.getProductId());
		
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
	 * @author Kenny/Kyungwoo
	 * @since 2019-04-29
	 */
	public PagedResult<EditorsPick> getEditorPickVendorContents(Integer pagenum, Integer pagesize,
			String title, String vendorName, LocalDateTime startDate, LocalDateTime endDate, String orderBy) {
		//1. Get from DB
		PagedResult<EditorPickVendorContent> resultDb = editorPickVendorContentRepository.getEditorPickVendorContents(pagenum, pagesize, title, vendorName, startDate, endDate, orderBy);
		
		//2. Parse to DTO
		PagedResult<EditorsPick> resultDto = new PagedResult<>();
		resultDto.setTotal(resultDb.getTotal());
		resultDto.setRecords(parseEditorsPicks(resultDb.getRecords()));
		return resultDto;
	}
	
	private List<EditorsPick> parseEditorsPicks(List<EditorPickVendorContent> epvcList) {
		return epvcList.stream()
				.map(this::parseEditorsPick)
				.collect(Collectors.toList());
	}
	
	private EditorsPick parseEditorsPick(EditorPickVendorContent epvc) {
		return EditorsPick.builder()
				.id(epvc.getEditorPickVendorContentId())
				.title(epvc.getEditorTitle())
				.description(epvc.getEditorDescription())
				.startDate(epvc.getStartDate())
				.endDate(epvc.getEndDate())
				.createdOn(epvc.getCreatedOn())
				.createdBy(epvc.getCreatedBy())
				.modifiedOn(epvc.getModifiedOn())
				.modifiedBy(epvc.getModifiedBy())
				.status(epvc.getStatus())
				.statusDescription(epvc.getStatusDescription())
				.vendor(epvc.getVendor())
				.bannerOrMedia(parseBannerOrMedia(epvc.getVendorImageRequest(), epvc.getVendorContent()))
				.build();
	}
	
	private BannerOrMedia parseBannerOrMedia(VendorImageRequest banner, VendorContent media) {
		//1. Banner
		if(banner!=null) {
			Optional<ListVendorImageType> type = listVendorImageTypeRepository.findById(banner.getVendorImageTypeID());
			if(type.isPresent()) {
				int typeId = type.get().getVendorImageTypeID();
				if(typeId==8 || typeId==9) { //8=Home Banner, 9=Home Intro Video
					return BannerOrMedia.builder()
							.typeId(1) //banner
							.id(banner.getImageRequestID())
							.title(type.get().getVendorImageType())
							.files(Collections.singletonList(
									parseBannerOrMediaFiles(banner, typeId)))
							.build();
				}
			}
		}
		
		//2. Media
		if(media!=null) {
			return BannerOrMedia.builder()
					.typeId(2) //media
					.id(media.getVendorContentId())
					.title(media.getTitle())
					.files(parseBannerOrMediaFiles(media.getVendorContentFiles(), media.getWholeSalerId(), media.getVendorContentId()))
					.build();
		}
		
		return null;
	}

	private BannerOrMediaFile parseBannerOrMediaFiles(VendorImageRequest banner, int typeId){
		return BannerOrMediaFile.builder()
				.id(banner.getImageRequestID())
				.fileType(typeId==8 ? 1 : 2) //1=Image, 2=Video
				.fileName(typeId==8 ? banner.getWholeSalerID()+"/main/"+ banner.getOriginalFileName() : banner.getOriginalFileName())
				.build();
	}
	
	private List<BannerOrMediaFile> parseBannerOrMediaFiles(List<VendorContentFile> vcFiles, int wholeSalerId, int vendorContentId) {
		return vcFiles.stream()
				.map(vcFile -> BannerOrMediaFile.builder()
						.id(vcFile.getVendorContentFileId())
						.fileType(vcFile.getFileType()) //1=Image, 2=Video
						.fileName(vcFile.getFileType()==1 ? wholeSalerId+"/premium/"+ vendorContentId +"/"+vcFile.getFileName() : vcFile.getFileName())
						.build())
				.collect(Collectors.toList());
	}

	public EditorsPick getEditorPickVendorContent(Integer id) {
		return parseEditorsPick(editorPickVendorContentRepository.findOneByEditorPickVendorContentId(id));
	}

	public List<BannerOrMedia> getBannerOrMedias(Integer vendorId) {
		List<BannerOrMedia> bannerOrMedias = new ArrayList<>();
		
		//1. Banner
		bannerOrMedias.addAll(
				vendorImageRequestRepository.findByWholeSalerIDAndVendorImageTypeIDInAndActiveOrderByVendorImageTypeIDAscImageRequestIDAsc(
						vendorId, Arrays.asList(8,9)/*8=Image,9=Video*/, true/*Active*/).stream()
				.map(banner -> parseBannerOrMedia(banner, null))
				.filter(Objects::nonNull)
				.collect(Collectors.toList()));
		
		//2. Media
		bannerOrMedias.addAll(
				vendorContentRepository.findByWholeSalerIdAndStatusIdAndTargetTypeIdAndIsActiveAndIsDeleted(
						vendorId, 2/*Approved*/, 1/*PC*/, true, false).stream()
				.map(media -> parseBannerOrMedia(null, media))
				.filter(Objects::nonNull)
				.collect(Collectors.toList()));
		
		return bannerOrMedias;
	}

	public ResultCode saveEditorsPick(EditorsPick editorsPick) {
		try {
			//1. Validate
			if(editorsPick==null) throw new Exception("No information to save!");
			if(editorsPick.getTitle()==null) throw new Exception("Empty title!");
			if(editorsPick.getDescription()==null) throw new Exception("Empty description!");
			if(editorsPick.getVendorId()==null) throw new Exception("Empty vendor!");
			if(editorsPick.getStartDate()==null)  throw new Exception("Empty starting period!");
			if(editorsPick.getEndDate()==null)  throw new Exception("Empty Ending period!");
			if(editorsPick.getBannerOrMediaId()==null || editorsPick.getBannerOrMediaTypeId()==null) throw new Exception("Empty banner or media!");
			
			//2. Post-process
			editorsPick.setStartDate(editorsPick.getStartDate().withHour(0).withMinute(0).withSecond(0));			
			editorsPick.setEndDate(editorsPick.getEndDate().withHour(23).withMinute(59).withSecond(59));
			
			WebAdminLoginUser user = Utility.getUserInfo();
			if(editorsPick.getCreatedOn()==null) {
				editorsPick.setCreatedOn(LocalDateTime.now());
				editorsPick.setCreatedBy(user.getUsername());
			}
			editorsPick.setModifiedOn(LocalDateTime.now());
			editorsPick.setModifiedBy(user.getUsername());
			
			//3. Save
			editorPickVendorContentRepository.save(parseEditorPickVendorContent(editorsPick));
		} catch (Exception e) {
			return new ResultCode(false, -1, e.getMessage());
		}
		return new ResultCode(true, 1, MSG_SAVE_SUCCESS);
	}
	
	private EditorPickVendorContent parseEditorPickVendorContent(EditorsPick ep) {
		EditorPickVendorContent epvc = new EditorPickVendorContent();
		
		epvc.setEditorPickVendorContentId(ep.getId());
		epvc.setEditorTitle(ep.getTitle());
		epvc.setEditorDescription(ep.getDescription());
		epvc.setVendorId(ep.getVendorId());
		epvc.setStartDate(ep.getStartDate());
		epvc.setEndDate(ep.getEndDate());
		epvc.setCreatedOn(ep.getCreatedOn());
		epvc.setCreatedBy(ep.getCreatedBy());
		epvc.setModifiedOn(ep.getModifiedOn());
		epvc.setModifiedBy(ep.getModifiedBy());
		
		if(ep.getBannerOrMediaTypeId()==1) { //1=Banner
			epvc.setImageRequestId(ep.getBannerOrMediaId());
		}else if(ep.getBannerOrMediaTypeId()==2) { //2=Media
			epvc.setVendorContentId(ep.getBannerOrMediaId());
		}
		
		return epvc;
	}
	
	@Transactional
	public ResultCode deleteEditorPickVendorContent(Integer id) {
		try {
			editorPickVendorContentRepository.deleteByEditorPickVendorContentId(id);
		} catch (Exception e) {
			return new ResultCode(false, -1, e.getMessage());
		}
		return new ResultCode(true, 1, MSG_DELETE_SUCCESS);
	}

	@Transactional(transactionManager = "primaryTransactionManager")
	public GetTrendDailyKeywordResponse getTrendDailyKeywords(GetTrendDailyKeywordParameter parameter) {
		String fromDateValue = parameter.getFromDate();
		String toDateValue = parameter.getToDate();

		LocalDateTime fromDate = LocalDate.parse(fromDateValue, DateTimeFormatter.ofPattern("yyyy-MM-dd")).atTime(0, 0, 0, 0);
		LocalDateTime toDate = LocalDate.parse(toDateValue, DateTimeFormatter.ofPattern("yyyy-MM-dd")).atTime(0, 0, 0, 0);

		List<TrendDailyKeywordEntity> result = trendDailyKeywordEntityRepository.findAllBetweenFromTo(fromDate, toDate);

		List<TrendDailyKeywordResponse> _result = result.stream().map(
				t -> new TrendDailyKeywordResponse(
						t.getTrendDailyKeywordID(), t.getExposeDate(), t.getKeywordText(), t.getKeywordType(), t.getSortNo(),
						t.getCategoryID(), Optional.ofNullable(t.getCategory()).map(CategoryEntity::getParentCategoryId).orElse(null),
						Optional.ofNullable(t.getCategory()).map(CategoryEntity::getParentParentCategoryId).orElse(null),
						t.getCreatedOn(), t.getCreatedBy(),t.getModifiedOn(),t.getModifiedBy()))
				.collect(Collectors.toList());

		return GetTrendDailyKeywordResponse.builder()
				.trendDailyKeywords(_result)
				.build();
	}

	@Transactional(transactionManager = "primaryTransactionManager")
	public ResultCode setTrendDailyKeywords(SetTrendDailyKeywordParameter parameter) {
		List<Long> existKeywordIdList = parameter.getKeywordList().stream()
				.filter(param -> param.getTrendDailyKeywordID() != null)
				.map(TrendDailyKeywordParameter::getTrendDailyKeywordID)
				.collect(Collectors.toList());

		List<TrendDailyKeywordEntity> existKeywordList = trendDailyKeywordEntityRepository.findAllById(existKeywordIdList);

		for (TrendDailyKeywordParameter keyword : parameter.getKeywordList()) {
			TrendDailyKeywordEntity existKeyword = existKeywordList.stream()
					.filter(tempExistKeyword -> keyword.getTrendDailyKeywordID() == tempExistKeyword.getTrendDailyKeywordID())
					.findFirst()
					.orElse(null);

			this.saveTrendKeyword(keyword, existKeyword);
		}

		if (parameter.isApplyToAllGivenDays() && parameter.getSrcExposeDate() != null) {
            applyToAllGivenDays(parameter.getSrcExposeDate(), parameter.getDaysToBeApplied());
        }

		return new ResultCode(true, 1, MSG_SAVE_SUCCESS);
	}

	private void saveTrendKeyword(TrendDailyKeywordParameter newKeyword, TrendDailyKeywordEntity existKeyword) {
		String exposeDateValue = newKeyword.getExposeDate();
		LocalDateTime exposeDate = LocalDate.parse(exposeDateValue, DateTimeFormatter.ofPattern("yyyy-MM-dd")).atTime(0, 0, 0, 0);

		String keywordText = newKeyword.getKeywordText();
		String userName = Utility.getUsername();
		LocalDateTime date = LocalDateTime.now();

		int sortNo = newKeyword.getSortNo();

		int keywordType = newKeyword.getKeywordType();

		if (!validateKeyword(newKeyword, existKeyword)) {
			return;
		}

		if (existKeyword == null) {

			existKeyword = new TrendDailyKeywordEntity();

			existKeyword.setExposeDate(exposeDate);
			existKeyword.setSortNo(sortNo);

			existKeyword.setCreatedOn(date);
			existKeyword.setCreatedBy(userName);
		}

		existKeyword.setKeywordText(keywordText);
		existKeyword.setKeywordType(keywordType);
		if (keywordType == 1) {
			existKeyword.setCategoryID(null);
		} else if(keywordType == 2) {
			existKeyword.setCategoryID(newKeyword.getCategoryID());
		}

		existKeyword.setModifiedOn(date);
		existKeyword.setModifiedBy(userName);

		trendDailyKeywordEntityRepository.save(existKeyword);
	}

	private void applyToAllGivenDays(String srcExposeDateValue, List<String> destExposeDateValueList) {
        String userName = Utility.getUsername();
        LocalDateTime date = LocalDateTime.now();

		LocalDateTime srcExposeDate = LocalDate.parse(srcExposeDateValue, DateTimeFormatter.ofPattern("yyyy-MM-dd")).atTime(0, 0, 0, 0);
		List<TrendDailyKeywordEntity> srcKeywordList = trendDailyKeywordEntityRepository.findAllByExposeDate(srcExposeDate);

		List<LocalDateTime> destExposeDateList = destExposeDateValueList.stream()
                .map(exposeDateValue -> LocalDate.parse(exposeDateValue, DateTimeFormatter.ofPattern("yyyy-MM-dd")).atTime(0, 0, 0, 0))
                .collect(Collectors.toList());
		List<TrendDailyKeywordEntity> allDestKeywordList = trendDailyKeywordEntityRepository.findAllByExposeDateIn(destExposeDateList);

		List<TrendDailyKeywordEntity> savedKeywordList = new ArrayList<>();
		List<TrendDailyKeywordEntity> deletedKeywordList = new ArrayList<>();
		for (LocalDateTime exposeDate : destExposeDateList) {
			List<TrendDailyKeywordEntity> destKeywordList = allDestKeywordList.stream()
					.filter(allDestKeyword -> allDestKeyword.getExposeDate().isEqual(exposeDate))
					.collect(Collectors.toList());

			for (int i = 1; i <= 7; i++) {
				final int temp_i = i;

				Optional<TrendDailyKeywordEntity> destKeyword = destKeywordList.stream()
						.filter(tempDestKeyword -> tempDestKeyword.getSortNo() == temp_i)
						.findFirst();

				Optional<TrendDailyKeywordEntity> newKeyword = srcKeywordList.stream()
						.filter(tempNewKeyword -> tempNewKeyword.getSortNo() == temp_i)
						.findFirst();

				if (destKeyword.isPresent() && newKeyword.isPresent()) { // modify
					if (!validateKeywordDuplicate(newKeyword.get(), destKeyword.get())) {
						continue;
					}

					destKeyword.get().setKeywordText(newKeyword.get().getKeywordText());
					destKeyword.get().setKeywordType(newKeyword.get().getKeywordType());
					if (newKeyword.get().getKeywordType() == 1) {
                        destKeyword.get().setCategoryID(null);
                    } else if(newKeyword.get().getKeywordType() == 2) {
						destKeyword.get().setCategoryID(newKeyword.get().getCategoryID());
                    }

					destKeyword.get().setModifiedOn(date);
					destKeyword.get().setModifiedBy(userName);

                    savedKeywordList.add(destKeyword.get());
				} else if (!destKeyword.isPresent() && newKeyword.isPresent()) { // new
					TrendDailyKeywordEntity newKeywordEntity = new TrendDailyKeywordEntity();

					newKeywordEntity.setExposeDate(exposeDate);
					newKeywordEntity.setSortNo(newKeyword.get().getSortNo());
					newKeywordEntity.setKeywordText(newKeyword.get().getKeywordText());
					newKeywordEntity.setKeywordType(newKeyword.get().getKeywordType());
					if (newKeyword.get().getKeywordType() == 1) {
						newKeywordEntity.setCategoryID(null);
					} else if (newKeyword.get().getKeywordType() == 2) {
						newKeywordEntity.setCategoryID(newKeyword.get().getCategoryID());
					}

					newKeywordEntity.setCreatedOn(date);
					newKeywordEntity.setCreatedBy(userName);
					newKeywordEntity.setModifiedOn(date);
					newKeywordEntity.setModifiedBy(userName);

					savedKeywordList.add(newKeywordEntity);
				} else if (destKeyword.isPresent() && !newKeyword.isPresent()) { // delete
					deletedKeywordList.add(destKeyword.get());
				}
			}
		}

        trendDailyKeywordEntityRepository.saveAll(savedKeywordList);
        trendDailyKeywordEntityRepository.deleteAll(deletedKeywordList);
    }

    private boolean validateKeyword(TrendDailyKeywordParameter newKeyword, TrendDailyKeywordEntity existKeyword) {
		// validate empty keyword
		if (newKeyword.getKeywordText() == null || newKeyword.getKeywordText().trim().equals("")) {
			return false;
		}

		// validate empty category
		if (newKeyword.getKeywordType() == 2 && newKeyword.getCategoryID() == null) {
			return false;
		}

		// validate duplicate
		if (existKeyword == null) {
			return true;
		}

		if (newKeyword.getKeywordType() == 1
				&& newKeyword.getKeywordType() == existKeyword.getKeywordType()
				&& newKeyword.getKeywordText().equals(existKeyword.getKeywordText())) {
			return false;
		}

		if (newKeyword.getKeywordType() == 2
				&& newKeyword.getKeywordType() == existKeyword.getKeywordType()
				&& newKeyword.getCategoryID().equals(existKeyword.getCategoryID())) {
			return false;
		}

		return true;
	}

	private boolean validateKeywordDuplicate(TrendDailyKeywordEntity newKeyword, TrendDailyKeywordEntity existKeyword) {
		// validate duplicate
		if (newKeyword.getKeywordType() == 1
				&& newKeyword.getKeywordType() == existKeyword.getKeywordType()
				&& newKeyword.getKeywordText().equals(existKeyword.getKeywordText())) {
			return false;
		}

		if (newKeyword.getKeywordType() == 2
				&& newKeyword.getKeywordType() == existKeyword.getKeywordType()
				&& newKeyword.getCategoryID().equals(existKeyword.getCategoryID())) {
			return false;
		}

		return true;
	}

	@Transactional(transactionManager = "primaryTransactionManager")
	public ResultCode delTrendDailyKeyword(DelTrendDailyKeywordParameter parameter) {
		try {
			trendDailyKeywordEntityRepository.deleteByTrendDailyKeywordID(parameter.getTrendKeywordID());
		} catch(Exception e) {
			return new ResultCode(false, -1, e.getMessage());
		}

		return new ResultCode(true, 1, MSG_DELETE_SUCCESS);
	}
}
