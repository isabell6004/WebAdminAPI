package net.fashiongo.webadmin.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import net.fashiongo.webadmin.dao.primary.*;
import net.fashiongo.webadmin.data.model.vendor.ContractPlansResponse;
import net.fashiongo.webadmin.data.model.vendor.GetVendorContract;
import net.fashiongo.webadmin.data.model.vendor.VendorContractResponse;
import net.fashiongo.webadmin.data.model.vendor.response.GetContractPlansResponse;
import net.fashiongo.webadmin.data.model.vendor.response.GetVendorContractResponse;
import net.fashiongo.webadmin.model.pojo.common.PagedResult;
import net.fashiongo.webadmin.model.pojo.common.Result;
import net.fashiongo.webadmin.model.pojo.common.ResultCode;
import net.fashiongo.webadmin.model.pojo.login.WebAdminLoginUser;
import net.fashiongo.webadmin.model.pojo.message.Total;
import net.fashiongo.webadmin.model.pojo.parameter.DelVendorBlockParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetVendorBlockListParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetVendorFormsListParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetVendorFormsParameter;
import net.fashiongo.webadmin.model.pojo.response.GetVendorFormsListResponse;
import net.fashiongo.webadmin.model.pojo.vendor.*;
import net.fashiongo.webadmin.model.pojo.vendor.parameter.*;
import net.fashiongo.webadmin.model.pojo.vendor.response.GetProductListResponse;
import net.fashiongo.webadmin.model.pojo.vendor.response.GetVendorContractDocumentHistoryResponse;
import net.fashiongo.webadmin.model.pojo.vendor.response.GetVendorCreditCardListResponse;
import net.fashiongo.webadmin.model.pojo.vendor.response.GetVendorDetailInfoDataResponse;
import net.fashiongo.webadmin.model.primary.*;
import net.fashiongo.webadmin.service.externalutil.FashionGoApiConfig;
import net.fashiongo.webadmin.service.externalutil.FashionGoApiHeader;
import net.fashiongo.webadmin.service.externalutil.HttpClientWrapper;
import net.fashiongo.webadmin.service.externalutil.response.FashionGoApiResponse;
import net.fashiongo.webadmin.utility.Utility;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author roy
 */
@Service
public class VendorService extends ApiService {
	@Autowired
	private VendorRepository vendorRepository;

    @Autowired
    private VendorAutocompleteRepository vendorAutocompleteRepository;

	@Autowired
	private CreditCardTypeRepository creditCardTypeRepository;

	@Autowired
	private VwVendorBlockedRepository vwVendorBlockedRepository;
	
	@Autowired
	private EntityActionLogRepository entityActionLogRepository;
	
	@Autowired
	private VendorBlockedRepository vendorBlockedRepository;
	
	@Autowired
	private VendorAdminAccountRepository vendorAdminAccountRepository;
	
	@Autowired
	private AspnetMembershipRepository aspnetMembershipRepository;
	
	@Autowired
	private FashiongoFormRepository fashiongoFormRepository;
	
	@Autowired
	private WholeSalerRatingRepository wholeSalerRatingRepository;
	
	@Autowired
	private BuyerRatingActiveRepository buyerRatingActiveRepository;
	
	@Autowired
	private VendorCreditCardRepository vendorCreditCardRepository;
	
	@Autowired
	private VendorCompanyCardRepository vendorCompanyCardRepository;
	
	@Autowired
	private LogCommunicationRepository logCommunicationRepository;
	
	@Autowired
	private VendorContentRepository vendorContentRepository;
	
	@Autowired
	private SecurityUserRepository securityUserRepository;
	
	@Autowired
	private ContractPlanRepository contractPlanRepository;

	@Autowired
	private HttpClientWrapper httpCaller;

	private final ObjectMapper mapper = new ObjectMapper();
	/**
	 * Get vendor list
	 * @since 2018. 10. 15.
	 * @author roy
	 * @return vendor list
	 */
	public List<Vendor> getVendorList() {
		return vendorRepository.findAllByActiveTrueAndShopActiveTrueOrderByCompanyName();
	}

    /**
     * Get autocomplete search results in company name prefix
     * created by Andy Min on 11/01/2018
     * @param prefix
     * @return
     */
    public List<VendorAutocomplete> getVendorsAutoomplete(String prefix) {
        return vendorAutocompleteRepository.findByCompanyNameStartingWithOrEmailStartingWithAllIgnoreCase(prefix, prefix);
    }

    /**
	 * 
	 * Get ProductList
	 * 
	 * @since 2018. 10. 29.
	 * @author Incheol Jung
	 * @param parameters
	 * @return
	 * @deprecated remove SP
	 */
    @Deprecated
	public GetProductListResponse getProductList(GetProductListParameter parameters) {
		GetProductListResponse result = new GetProductListResponse();
		String spName = "up_wa_GetProductsList";
		List<Object> params = new ArrayList<Object>();

		params.add(parameters.getWholesalerid());
		params.add(parameters.getVendorcategoryid());
		params.add(parameters.getProductname());

		List<Object> _result = jdbcHelper.executeSP(spName, params, ProductSummary.class);
		result.setProductList((List<ProductSummary>) _result.get(0));

		return result;
	}

	@Deprecated
	public List<ProductColor> getProductColor(Integer productId) {
		String spName = "up_wa_GetProductColors";
		List<Object> params = new ArrayList<Object>();
		
		params.add(productId);

		List<Object> _result = jdbcHelper.executeSP(spName, params, ProductColor.class);
		
		return (List<ProductColor>) _result.get(0);
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 11. 12.
	 * @author Reo
	 * @param parameters
	 * @return
	 * @throws ParseException 
	 */
	public List<VwVendorBlocked> getVendorBlockList(GetVendorBlockListParameter parameters) throws ParseException {
		List<VwVendorBlocked> result = null;
		if(parameters.getSearchType().equals("ID")) {
		    result = vwVendorBlockedRepository.findByBlockID(Integer.parseInt(parameters.getSearchKeyword()));
		} else if(parameters.getSearchType().equals("Company")) {
			result = vwVendorBlockedRepository.findByCompanyNameContainingIgnoreCase(parameters.getSearchKeyword());
		} else if(parameters.getSearchType().equals("Date")) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDateTime fromDate = LocalDateTime.parse(parameters.getSearchKeyword(), DateTimeFormatter.ISO_DATE_TIME);
			LocalDateTime toDate = LocalDateTime.parse(parameters.getSearchKeyword(), DateTimeFormatter.ISO_DATE_TIME).plusDays(1).minusSeconds(1);
			result = vwVendorBlockedRepository.findByBlockedOnBetween(fromDate, toDate);
		} else if(parameters.getSearchType().equals("Reason")) {
			result = vwVendorBlockedRepository.findByBlockReasonTitle(parameters.getSearchKeyword());
		} else {
			result = (List<VwVendorBlocked>) vwVendorBlockedRepository.findAll();
		}
		
		return result;
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 11. 12.
	 * @author Reo
	 * @param wholeSalerID
	 * @return
	 */
	public List<EntityActionLog> getVendorBlockHistoryList(Integer wholeSalerID) {
		List<EntityActionLog> result = entityActionLogRepository.findByEntityTypeIDAndEntityIDOrderByLogIDDesc(9, wholeSalerID);
		
        return result;
	}
	
	/**
	 * getVendorCreditCardList
	 * 
	 * @since 2018. 11. 12.
	 * @author Dahye
	 * @param 
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	public GetVendorCreditCardListResponse getVendorCreditCardList(String orderby) {
		String spName = "up_wa_GetVendorCreditCard";
		List<Object> params = new ArrayList<Object>();
		GetVendorCreditCardListResponse result = new GetVendorCreditCardListResponse();
		params.add(orderby);

		List<Object> _result = jdbcHelper.executeSP(spName, params, VendorCreditCardList.class);
		
		result.setVendorCreditCardList((List<VendorCreditCardList>) _result.get(0));
		return result;
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 11. 12.
	 * @author Reo
	 * @param parameters
	 * @return
	 */
	@Transactional(value = "primaryTransactionManager")
	public ResultCode delVendorBlock(DelVendorBlockParameter parameters) {
		ResultCode result = new ResultCode(false, 0, null);
		
		vendorBlockedRepository.deleteByBlockID(parameters.getBlockID());
		EntityActionLog eal = new EntityActionLog();
		eal.setEntityTypeID(9);
		eal.setEntityID(parameters.getWholeSalerID());
		eal.setActionID(9002);
		eal.setActedOn(LocalDateTime.now());
		eal.setActedBy(Utility.getUsername());
		entityActionLogRepository.save(eal);
		
		List<VendorAdminAccount> vendorAdminAccountList = vendorAdminAccountRepository.findByWholeSalerIDIn(parameters.getWholeSalerID());
		List<AspnetMembership> aspnetMembershipList = new ArrayList<AspnetMembership>();
		for(VendorAdminAccount vaa: vendorAdminAccountList) {
			AspnetMembership ams = aspnetMembershipRepository.findOneByUserId(vaa.getUserGUID());
			ams.setIsApproved(true);
			ams.setIsLockedOut(false);
			aspnetMembershipList.add(ams);
		}
		aspnetMembershipRepository.saveAll(aspnetMembershipList);
		
		result.setResultCode(1);
		result.setSuccess(true);
		result.setResultMsg(MSG_DELETE_SUCCESS);
		return result;
	}
	
	/**
	 * getCreditCardType
	 * 
	 * @since 2018. 11. 12.
	 * @author Dahye
	 * @param 
	 * @return 
	 */
	public List<CreditCardType> getCreditCardType() {
		
		return creditCardTypeRepository.findAllByActiveTrueOrderByCreditCardTypeID();
	}
	
	/**
	 * DelVendorCreditCard
	 * 
	 * @since 2018. 11. 19.
	 * @author Dahye
	 * @param DelVendorCreditcardParameter
	 * @return 
	 */
	public void delVendorCreditCard(DelVendorCreditcardParameter parameters) {
		String spName = "up_wa_DeleteVendorCreditCard";
		List<Object> params = new ArrayList<Object>();
		params.add(parameters.getVendorCreditCardIDs());
		jdbcHelper.executeSP(spName, params);
	}
	
	/**
	 * SetVendorRatingActive
	 * 
	 * @since 2018. 11. 19.
	 * @author Dahye
	 * @param SetVendorRatingActiveParameter
	 * @return DeleteCommunicationReasonResponse
	 */
	public Integer setVendorRatingActive(SetVendorRatingActiveParameter parameters) {
		WholeSalerRating rating = wholeSalerRatingRepository.findOneByWholeSalerRatingID(parameters.getWid());
		rating.setActive(parameters.getActive());
		wholeSalerRatingRepository.save(rating);
		return 1;
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 11. 13.
	 * @author Reo
	 * @param parameters
	 * @return
	 * @deprecated remove SP
	 */
	@Deprecated
	public GetVendorFormsListResponse getVendorFormsList(GetVendorFormsListParameter parameters) {
		GetVendorFormsListResponse result = new GetVendorFormsListResponse();
		String spName = "up_wa_GetVendorFormList";
		List<Object> params = new ArrayList<Object>();

		
		List<Object> _result = jdbcHelper.executeSP(spName, params, FashiongoForm.class, Total.class);
		result.setFashiongoFormList((List<FashiongoForm>) _result.get(0));
		return result;
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 11. 13.
	 * @author Reo
	 * @return
	 */
	@Transactional(value = "primaryTransactionManager")
	public ResultCode SetVendorForms(SetVendorFormsParameter parameters) {
		ResultCode result = new ResultCode(false, 0, null);
		FashiongoForm fgForm = new FashiongoForm();
		switch(parameters.getType()) {
			case "Add":
				fgForm.setFormName(parameters.getFormName());
				fgForm.setMemo(parameters.getMemo());
				fgForm.setAttachment(parameters.getAttachment());
				fgForm.setCreatedBy(Utility.getUsername());
				fgForm.setCreatedOn(LocalDateTime.now());
				fgForm.setModifiedBy(Utility.getUsername());
				fgForm.setModifiedOn(LocalDateTime.now());
				fashiongoFormRepository.save(fgForm);
				
				result.setResultCode(1);
				result.setSuccess(true);
				result.setResultMsg(MSG_INSERT_SUCCESS);
				break;
			default:
				fgForm = fashiongoFormRepository.findOneByFashionGoFormID(parameters.getFashionGoFormID());
				fgForm.setFormName(parameters.getFormName());
				fgForm.setMemo(parameters.getMemo());
				fgForm.setAttachment(parameters.getAttachment());
				fgForm.setModifiedBy(Utility.getUsername());
				fgForm.setModifiedOn(LocalDateTime.now());
				fashiongoFormRepository.save(fgForm);
				
				result.setResultCode(1);
				result.setSuccess(true);
				result.setResultMsg(MSG_UPDATE_SUCCESS);
				break;
		}
		return result;
	}
	
	/**
	 * SetBuyerRatingActive
	 * 
	 * @since 2018. 11. 19.
	 * @author Dahye
	 * @param SetBuyerRatingActiveParameter
	 * @return Integer
	 */
	public Integer setBuyerRatingActive(SetBuyerRatingActiveParameter parameters) {
		RetailerRating rating = buyerRatingActiveRepository.findOneByRetailerRatingID(parameters.getWid());
		rating.setActive(parameters.getActive());
		buyerRatingActiveRepository.save(rating);
		return 1;
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 11. 14.
	 * @author Reo
	 * @param parameters
	 * @return
	 */
	public ResultCode delVendorForm(DelVendorFormParameter parameters) {
		ResultCode result = new ResultCode(false, 0, null);
		String spName = "up_wa_DeleteFashionGoForm";
		List<Object> params = new ArrayList<Object>();
		params.add(parameters.getFashionGoFormIDs());
		
		List<Object> _result = jdbcHelper.executeSP(spName, params, Result.class);
		result.setResultCode(1);
		result.setResultMsg(MSG_DELETE_SUCCESS);
		result.setSuccess(true);
		return result;
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 11. 23.
	 * @author Reo
	 * @param parameters
	 * @return
	 */
	@Transactional(value = "primaryTransactionManager")
	public ResultCode setVendorCreditCard(SetVendorCreditCardParameter parameters) {
		ResultCode result = new ResultCode(false, 0, null);
		
		VendorCreditCard vcc = new VendorCreditCard();
		VendorCompanyCard vccard = new VendorCompanyCard();
		String guid = null;
		
		switch(parameters.getType()) {
			case "Add":
				vcc.setWholeSalerID(parameters.getVendorID());
				vcc.setCardTypeID(parameters.getVendorCreditCardID());
				vcc.setLast4Digit(parameters.getCreditCard());
				vcc.setAttachment(parameters.getAttachment());
				vcc.setIsRecurring(parameters.getRecurring());
				vcc.setZipcode(parameters.getZipcode());
				vcc.setState(parameters.getState());
				vcc.setCity(parameters.getCity());
				vcc.setStreetNo(parameters.getStreet());
				vcc.setCreatedOn(LocalDateTime.now());
				vcc.setCreatedBy(Utility.getUsername());
				vcc.setModifiedOn(LocalDateTime.now());
				vcc.setModifiedBy(Utility.getUsername());
				vendorCreditCardRepository.save(vcc);
				
				vccard = vendorCompanyCardRepository.findOneBywholeSalerId(parameters.getVendorID());
				if(vccard != null) {
				    guid = vccard.getWholeSalerGUID();
				}
				
				vccard.setChargedByCreditCard(true);
				vendorCompanyCardRepository.save(vccard);
				
				if(!StringUtils.isEmpty(guid)) {
					result.setResultCode(1);
					result.setResultMsg(guid);
				} else {
					result.setResultCode(0);
					result.setResultMsg(null);
				}
			    break;
			default:
				vcc = vendorCreditCardRepository.findOneByVendorCreditCardID(parameters.getVendorCreditCardID());
				vcc.setWholeSalerID(parameters.getVendorID());
				vcc.setCardTypeID(parameters.getVendorCreditCardID());
				vcc.setLast4Digit(parameters.getCreditCard());
				vcc.setAttachment(parameters.getAttachment());
				vcc.setIsRecurring(parameters.getRecurring());
				vcc.setZipcode(parameters.getZipcode());
				vcc.setState(parameters.getState());
				vcc.setCity(parameters.getCity());
				vcc.setStreetNo(parameters.getStreet());
				vcc.setModifiedOn(LocalDateTime.now());
				vcc.setModifiedBy(Utility.getUsername());
				vendorCreditCardRepository.save(vcc);
				
				vccard = vendorCompanyCardRepository.findOneBywholeSalerId(parameters.getVendorID());
				if(vccard != null) {
				    guid = vccard.getWholeSalerGUID();
				}
				
				vccard.setChargedByCreditCard(true);
				vendorCompanyCardRepository.save(vccard);
				
				if(!StringUtils.isEmpty(guid)) {
					result.setResultCode(1);
					result.setResultMsg(guid);
				} else {
					result.setResultCode(0);
					result.setResultMsg(null);
				}
				break;
		}
		return result;
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 12. 14.
	 * @author Reo
	 * @param wholeSalerID
	 * @return
	 */
	public List<LogCommunication> getVendorCommunicationList(Integer wholeSalerID) {
		List<LogCommunication> result =  logCommunicationRepository.findByRetailerIDAndIsForVendorOrderByCommunicationIDDesc(wholeSalerID, true);
		
		return result;
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 12. 14.
	 * @author Reo
	 * @param wholeSalerID
	 * @return
	 */
	public VendorContractResponse getVendorContract(Integer wholeSalerID) {
		final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendors/" + wholeSalerID + "/contracts/recently";

		WebAdminLoginUser userInfo = Utility.getUserInfo();
		String responseBody = httpCaller.get(endpoint, FashionGoApiHeader.getHeader(userInfo.getUserId(), userInfo.getUsername()));

		try {
			mapper.registerModule(new JavaTimeModule())
					.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
			FashionGoApiResponse<GetVendorContractResponse> fashionGoApiResponse = mapper.readValue(responseBody, new TypeReference<FashionGoApiResponse<GetVendorContractResponse>>() {});
			if (fashionGoApiResponse.getHeader().isSuccessful()) {
				GetVendorContract vendorContract = fashionGoApiResponse.getData().getVendorContract();
				return VendorContractResponse.create(vendorContract);
			} else {
				throw new RuntimeException("fail to get vendor contract list in new fashiongo api");
			}
		} catch (IOException e) {
			throw new RuntimeException("fail to get vendor contract list in new fashiongo api");
		}
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 12. 14.
	 * @author Reo
	 * @param vendorContractID
	 * @return
	 */
	public GetVendorContractDocumentHistoryResponse getVendorContractDocumentHistory(Integer vendorContractID) {
		GetVendorContractDocumentHistoryResponse result = new GetVendorContractDocumentHistoryResponse();
		String spName = "up_wa_GetVendorContractDocumentHistory";
		List<Object> params = new ArrayList<Object>();
		params.add(vendorContractID);

		List<Object> _result = jdbcHelper.executeSP(spName, params, VendorContractDocumentHistory.class);
		result.setVendorContractDocumentHistoryList((List<VendorContractDocumentHistory>) _result.get(0));
		return result;
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 12. 14.
	 * @author Reo
	 * @param wholeSalerID
	 * @return
	 */
	public GetVendorDetailInfoDataResponse getVendorDetailInfoData(Integer wholeSalerID) {
		GetVendorDetailInfoDataResponse result = new GetVendorDetailInfoDataResponse();
		String spName = "up_wa_GetVendorDetailInfoData";
		List<Object> params = new ArrayList<Object>();
		params.add(wholeSalerID);

		List<Object> _result = jdbcHelper.executeSP(spName, params, VendorDetailDate.class, SecurityUserName.class, VendorCompanyType.class, Country.class);
		result.setSessionUsrID(Utility.getUsername());
		result.setVendorDetailDateList((List<VendorDetailDate>) _result.get(0));
		result.setUserName((List<SecurityUserName>) _result.get(1));
		result.setVendorCompanyTypeList((List<VendorCompanyType>) _result.get(2));
		result.setCountryList((List<Country>) _result.get(3));
		return result;
	}

    public Vendor getVendorInfo(Integer wholeSalerID) {
        Optional<Vendor> vendor = vendorRepository.findById(wholeSalerID);
        return vendor.get();
    }

    /**
     * @author Kenny/Kyungwoo
     * @since 2019-04-15
     */
	public PagedResult<VendorContent> getVendorContents(Integer pagenum, Integer pagesize, String company, LocalDateTime datefrom, LocalDateTime dateto, Integer type, Integer status) {
		return vendorContentRepository.getVendorContents(pagenum, pagesize, company, datefrom, dateto, type, status);
	}

	/**
     * @author Kenny/Kyungwoo
     * @since 2019-04-16
     */
	public void approveVendorContent(int id) throws Exception {
		Optional<VendorContent> vendorContent = vendorContentRepository.findById(id);
		if(!vendorContent.isPresent()) throw new Exception("It does not exist.");
		if(vendorContent.get().getStatusId()==2) throw new Exception("It is already approved.");
		
		vendorContent.get().setStatusId(2);
		vendorContent.get().setApprovedOn(LocalDateTime.now());
		vendorContent.get().setApprovedBy(Utility.getUsername());
		
		vendorContentRepository.save(vendorContent.get());
	}

	/**
     * @author Kenny/Kyungwoo
     * @since 2019-04-16
     */
	public void denyVendorContent(int id, String reason) throws Exception {
		Optional<VendorContent> vendorContent = vendorContentRepository.findById(id);
		if(!vendorContent.isPresent()) throw new Exception("It does not exist.");
		if(vendorContent.get().getStatusId()==3) throw new Exception("It is already denied.");
		
		vendorContent.get().setStatusId(3);
		vendorContent.get().setRejectedOn(LocalDateTime.now());
		vendorContent.get().setRejectedBy(Utility.getUsername());
		
		vendorContent.get().setRejectedReason(reason);
		vendorContentRepository.save(vendorContent.get());
	}

	/**
     * @author Kenny/Kyungwoo
     * @since 2019-04-18
     */
	public List<SecurityUser> getAssignedUsers() {
		return securityUserRepository.findAllMappedByVendor();
	}

	/**
     * @author Kenny/Kyungwoo
     * @since 2019-05-02
     */
	public List<Vendor> getEditorsPickVendors() {
		return vendorRepository.getEditorPickVendors();
	}

	public List<ContractPlansResponse> getContractPlans() {
		final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendors/contractplans";

		WebAdminLoginUser userInfo = Utility.getUserInfo();
		String responseBody = httpCaller.get(endpoint, FashionGoApiHeader.getHeader(userInfo.getUserId(), userInfo.getUsername()));

		try {
			mapper.registerModule(new JavaTimeModule())
					.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

			FashionGoApiResponse<GetContractPlansResponse> fashionGoApiResponse = mapper.readValue(responseBody, new TypeReference<FashionGoApiResponse<GetContractPlansResponse>>() {});
			if (fashionGoApiResponse.getHeader().isSuccessful()) {
				return fashionGoApiResponse.getData().getContractPlansResponseList();
			} else {
				throw new RuntimeException("fail to get vendor contract list in new fashiongo api");
			}
		} catch (IOException e) {
			throw new RuntimeException("fail to get vendor contract list in new fashiongo api");
		}
	}
}
