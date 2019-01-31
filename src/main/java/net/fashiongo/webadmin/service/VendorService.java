package net.fashiongo.webadmin.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import net.fashiongo.webadmin.dao.primary.AspnetMembershipRepository;
import net.fashiongo.webadmin.dao.primary.BuyerRatingActiveRepository;
import net.fashiongo.webadmin.dao.primary.CreditCardTypeRepository;
import net.fashiongo.webadmin.dao.primary.EntityActionLogRepository;
import net.fashiongo.webadmin.dao.primary.FashiongoFormRepository;
import net.fashiongo.webadmin.dao.primary.ListVendorImageTypeRepository;
import net.fashiongo.webadmin.dao.primary.LogCommunicationRepository;
import net.fashiongo.webadmin.dao.primary.VendorAdminAccountRepository;
import net.fashiongo.webadmin.dao.primary.VendorBlockedRepository;
import net.fashiongo.webadmin.dao.primary.VendorCompanyCardRepository;
import net.fashiongo.webadmin.dao.primary.VendorContractRepository;
import net.fashiongo.webadmin.dao.primary.VendorCreditCardRepository;
import net.fashiongo.webadmin.dao.primary.VendorImageRequestRepository;
import net.fashiongo.webadmin.dao.primary.VendorListRepository;
import net.fashiongo.webadmin.dao.primary.VwVendorBlockedRepository;
import net.fashiongo.webadmin.dao.primary.WholeSalerRatingRepository;
import net.fashiongo.webadmin.model.pojo.common.Result;
import net.fashiongo.webadmin.model.pojo.common.ResultCode;
import net.fashiongo.webadmin.model.pojo.message.Total;
import net.fashiongo.webadmin.model.pojo.message.parameter.GetContactUsParameter;
import net.fashiongo.webadmin.model.pojo.parameter.DelVendorBlockParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetBannerRequestParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetVendorBlockListParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetVendorFormsListParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetDenyBannerParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetVendorFormsParameter;
import net.fashiongo.webadmin.model.pojo.response.GetBannerRequestResponse;

import net.fashiongo.webadmin.model.pojo.vendor.response.GetContactUsResponse;
import net.fashiongo.webadmin.model.pojo.vendor.response.GetProductListResponse;
import net.fashiongo.webadmin.model.pojo.vendor.response.GetVendorContractDocumentHistoryResponse;
import net.fashiongo.webadmin.model.pojo.response.GetVendorFormsListResponse;
import net.fashiongo.webadmin.model.pojo.vendor.Country;
import net.fashiongo.webadmin.model.pojo.vendor.ProductColor;
import net.fashiongo.webadmin.model.pojo.vendor.ProductSummary;
import net.fashiongo.webadmin.model.pojo.vendor.SecurityUserName;
import net.fashiongo.webadmin.model.pojo.vendor.TotalCount;
import net.fashiongo.webadmin.model.pojo.vendor.VendorContractDocumentHistory;
import net.fashiongo.webadmin.model.pojo.vendor.VendorCreditCardList;
import net.fashiongo.webadmin.model.pojo.vendor.parameter.DelVendorCreditcardParameter;
import net.fashiongo.webadmin.model.pojo.vendor.parameter.DelVendorFormParameter;
import net.fashiongo.webadmin.model.pojo.vendor.parameter.GetProductListParameter;
import net.fashiongo.webadmin.model.pojo.vendor.parameter.SetBuyerRatingActiveParameter;
import net.fashiongo.webadmin.model.pojo.vendor.parameter.SetVendorCreditCardParameter;
import net.fashiongo.webadmin.model.pojo.vendor.parameter.SetVendorRatingActiveParameter;
import net.fashiongo.webadmin.model.pojo.vendor.response.GetProductListResponse;
import net.fashiongo.webadmin.model.pojo.vendor.response.GetVendorCreditCardListResponse;
import net.fashiongo.webadmin.model.pojo.vendor.response.GetVendorDetailInfoDataResponse;
import net.fashiongo.webadmin.model.primary.AspnetMembership;

import net.fashiongo.webadmin.model.primary.ContactUs;
import net.fashiongo.webadmin.model.primary.CreditCardType;

import net.fashiongo.webadmin.model.primary.EntityActionLog;
import net.fashiongo.webadmin.model.primary.FashiongoForm;
import net.fashiongo.webadmin.model.primary.ListVendorImageType;
import net.fashiongo.webadmin.model.primary.LogCommunication;
import net.fashiongo.webadmin.model.primary.RetailerRating;
import net.fashiongo.webadmin.model.primary.VendorAdminAccount;
import net.fashiongo.webadmin.model.primary.VendorCompany;
import net.fashiongo.webadmin.model.primary.VendorCompanyCard;
import net.fashiongo.webadmin.model.primary.VendorCompanyType;
import net.fashiongo.webadmin.model.primary.VendorContract;
import net.fashiongo.webadmin.model.primary.VendorCreditCard;
import net.fashiongo.webadmin.model.primary.VendorDetailDate;
import net.fashiongo.webadmin.model.primary.VendorImageRequest;
import net.fashiongo.webadmin.model.primary.VwVendorBlocked;
import net.fashiongo.webadmin.model.primary.WholeSalerRating;
import net.fashiongo.webadmin.utility.Utility;

/**
 * @author roy
 */
@Service
public class VendorService extends ApiService {
	@Autowired
	private VendorListRepository vendorListRepository;
	
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
	private ListVendorImageTypeRepository listVendorImageTypeRepository;
	
	@Autowired
	private VendorImageRequestRepository vendorImageRequestRepository;
	
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
	private VendorContractRepository vendorContractRepository;
	
	/**
	 * Get vendor list
	 * @since 2018. 10. 15.
	 * @author roy
	 * @return vendor list
	 */
	public List<VendorCompany> getVendorList() {
		return vendorListRepository.findAllByActiveTrueAndShopActiveTrueOrderByCompanyName();
	}
	
	/**
	 * 
	 * Get ProductList
	 * 
	 * @since 2018. 10. 29.
	 * @author Incheol Jung
	 * @param parameters
	 * @return
	 */
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
			result = vwVendorBlockedRepository.findByCompanyName(parameters.getSearchKeyword());
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
	 * 
	 * Description Example
	 * @since 2018. 11. 13.
	 * @author Reo
	 * @return
	 */
	public List<ListVendorImageType> getVendorImageType() {
		List<ListVendorImageType> result =  listVendorImageTypeRepository.findAllByOrderByVendorImageTypeID();
		
		return result;
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 11. 13.
	 * @author Reo
	 * @param parameters
	 * @return
	 */
	public GetBannerRequestResponse getBannerRequest(GetBannerRequestParameter parameters) {
		GetBannerRequestResponse result = new GetBannerRequestResponse();
		String spName = "up_wa_GetBannerImage";
		List<Object> params = new ArrayList<Object>();
		params.add(parameters.getPageNum());
		params.add(parameters.getPageSize());
		params.add(null);
		params.add(parameters.getSearchKeyword());
		params.add(parameters.getSearchType());
		params.add(parameters.getSearchStatus());
		params.add(null);
		params.add(parameters.getFromDate());
		params.add(parameters.getToDate());
		params.add(parameters.getOrderby());
		params.add(null);
		
		List<Object> _result = jdbcHelper.executeSP(spName, params, VendorImageRequest.class, TotalCount.class);
		result.setBannerImageList((List<VendorImageRequest>) _result.get(0));
		result.setTotalCount((List<TotalCount>) _result.get(1));
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
	public ResultCode setDenyBanner(SetDenyBannerParameter parameters) {
		ResultCode result = new ResultCode(false, 0, null);
		VendorImageRequest vir = vendorImageRequestRepository.findOneByImageRequestID(parameters.getImageRequestId());
		vir.setRejectReason(parameters.getDenialReason());
		vir.setDecidedOn(LocalDateTime.now());
		vir.setDecidedBy(Utility.getUsername());
		vir.setActive(false);
		vir.setIsApproved(false);
		vendorImageRequestRepository.save(vir);
		
		result.setResultCode(1);
		result.setSuccess(true);
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
	public ResultCode setApproveBanner(SetDenyBannerParameter parameters) {
		ResultCode result = new ResultCode(false, 0, null);
		VendorImageRequest vir = vendorImageRequestRepository.findOneByImageRequestID(parameters.getImageRequestId());
		vir.setDecidedOn(LocalDateTime.now());
		vir.setDecidedBy(Utility.getUsername());
		vir.setActive(false);
		vir.setIsApproved(true);
		vendorImageRequestRepository.save(vir);
		
		result.setResultCode(1);
		result.setSuccess(true);
		return result;
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
	 * 
	 * Description Example
	 * @since 2018. 11. 13.
	 * @author Reo
	 * @param parameters
	 * @return
	 */
	@Transactional(value = "primaryTransactionManager")
	public ResultCode setRestoreBanner(SetDenyBannerParameter parameters) {
		ResultCode result = new ResultCode(false, 0, null);
		VendorImageRequest vir = vendorImageRequestRepository.findOneByImageRequestID(parameters.getImageRequestId());
		vir.setDecidedOn(null);
		vir.setDecidedBy(null);
        vendorImageRequestRepository.save(vir);
		
		result.setResultCode(1);
		result.setSuccess(true);
		return result;
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 11. 13.
	 * @author Reo
	 * @param parameters
	 * @return
	 */
	@Transactional(value = "primaryTransactionManager")
	public ResultCode delBannerRequest(SetDenyBannerParameter parameters) {
		ResultCode result = new ResultCode(false, 0, null);
		VendorImageRequest vir = new VendorImageRequest();
		vir.setImageRequestID(parameters.getImageRequestId());
		vendorImageRequestRepository.delete(vir);
		
		result.setResultCode(1);
		result.setSuccess(true);
		result.setResultMsg(MSG_DELETE_SUCCESS);
		return result;
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
	 */
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
	public List<VendorContract> getVendorContract(Integer wholeSalerID) {
		List<VendorContract> result = vendorContractRepository.findByWholeSalerIDOrderByVendorContractIDDesc(wholeSalerID);
		
		return result;
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
}
