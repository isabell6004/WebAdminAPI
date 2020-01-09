package net.fashiongo.webadmin.controller.vendor;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.entity.primary.CodeVendorIndustryEntity;
import net.fashiongo.webadmin.data.entity.primary.ListVendorDocumentTypeEntity;
import net.fashiongo.webadmin.data.entity.primary.SecurityUserEntity;
import net.fashiongo.webadmin.data.entity.primary.vendor.ProductColorRow;
import net.fashiongo.webadmin.data.model.buyer.SetAccountLockOutParameter;
import net.fashiongo.webadmin.data.model.vendor.*;
import net.fashiongo.webadmin.data.model.vendor.Vendor;
import net.fashiongo.webadmin.data.model.vendor.response.GetAssignedUserListResponse;
import net.fashiongo.webadmin.data.model.vendor.response.GetVendorAdminAccountLogListResponse;
import net.fashiongo.webadmin.data.model.vendor.response.GetVendorBasicInfoResponse;
import net.fashiongo.webadmin.data.model.vendor.response.GetVendorCodeNameCheckResponse;
import net.fashiongo.webadmin.data.model.vendor.response.GetVendorCommunicationListResponse;
import net.fashiongo.webadmin.data.model.vendor.response.GetVendorGroupingResponse;
import net.fashiongo.webadmin.data.model.vendor.response.GetVendorListCSVResponse;
import net.fashiongo.webadmin.data.model.vendor.response.GetVendorListResponse;
import net.fashiongo.webadmin.data.model.vendor.response.GetVendorSettingResponse;
import net.fashiongo.webadmin.model.pojo.buyer.parameter.SetModifyPasswordParameter;
import net.fashiongo.webadmin.model.pojo.common.PagedResult;
import net.fashiongo.webadmin.model.pojo.common.ResultCode;
import net.fashiongo.webadmin.model.pojo.parameter.*;
import net.fashiongo.webadmin.model.pojo.vendor.parameter.*;
import net.fashiongo.webadmin.model.pojo.vendor.response.GetVendorCreditCardListResponse;
import net.fashiongo.webadmin.model.primary.*;
import net.fashiongo.webadmin.service.CacheService;
import net.fashiongo.webadmin.service.UserService;
import net.fashiongo.webadmin.service.VendorService;
import net.fashiongo.webadmin.service.renewal.RenewalVendorService;
import net.fashiongo.webadmin.service.vendor.VendorInfoService;
import net.fashiongo.webadmin.utility.JsonResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author roy
 */
@RestController
@RequestMapping(value="/vendor", produces = "application/json")
@Slf4j
public class VendorController {
	
	@Autowired
	VendorService vendorService;

	@Autowired
	private RenewalVendorService renewalVendorService;
	
	@Autowired
    private CacheService cacheService;

	@Autowired
	private UserService userService;

	@Autowired
    private VendorInfoService vendorInfoService;

	/**
	 * Get vendor list
	 * @since 2018. 10. 15.
	 * @author roy
	 * @return vendor list
	 */
	@RequestMapping(value="getvendorlistall", method=RequestMethod.POST)
	public JsonResponse<List<Vendor>> getVendorListAll() {
		
		List<Vendor> vendors = renewalVendorService.getVendorListAll();
		return new JsonResponse<List<Vendor>>(true, null, 0, vendors);
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
	@RequestMapping(value="getproductlist", method=RequestMethod.POST)
	public JsonResponse<VendorProductListResponse> getProductList(@RequestBody GetProductListParameter parameters) {
		JsonResponse<VendorProductListResponse> result = new JsonResponse<>(true, null, null);
		
		VendorProductListResponse products = renewalVendorService.getProductList(parameters);
		result.setData(products);
		
		return result; 
	}
	
	@RequestMapping(value="getproductcolor", method=RequestMethod.POST)
	public JsonResponse<List<ProductColorRow>> getProductColor(@RequestBody GetProductColorParameter parameters) {
		JsonResponse<List<ProductColorRow>> result = new JsonResponse<>(true, null, null);
		
		result.setData(renewalVendorService.getProductColor(parameters.getProductid()));
		
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
	@RequestMapping(value="getvendorcreditcardList", method=RequestMethod.POST)
	public JsonResponse<GetVendorCreditCardListResponse> getVendorCreditCardList(@RequestBody GetVendorCreditCardListParameter parameters) {
		GetVendorCreditCardListResponse result = vendorService.getVendorCreditCardList(parameters.getOrderBy());
		return new JsonResponse<GetVendorCreditCardListResponse>(true, null, 0, result);
	}
	
	/**
	 * getCreditCardType
	 * 
	 * @since 2018. 11. 12.
	 * @author Dahye
	 * @param 
	 * @return 
	 */
	@RequestMapping(value="getcreditcardtype", method=RequestMethod.POST)
	public JsonResponse<List<CreditCardType>> getCreditCardType() {
		List<CreditCardType> result = vendorService.getCreditCardType();
		return new JsonResponse<List<CreditCardType>>(true, null, 0, result);
	}
	
	/**
	 * DelVendorCreditCard
	 * 
	 * @since 2018. 11. 19.
	 * @author Dahye
	 * @param DelVendorCreditcardParameter
	 * @return 
	 */
	@RequestMapping(value="delvendorcreditcard", method=RequestMethod.POST)
	public JsonResponse<String> delVendorCreditCard(@RequestBody DelVendorCreditcardParameter parameters) {
		vendorService.delVendorCreditCard(parameters);
		return new JsonResponse<String>();
	}
	
	/**
	 * SetVendorRatingActive
	 * 
	 * @since 2018. 11. 19.
	 * @author Dahye
	 * @param SetVendorRatingActiveParameter
	 * @return DeleteCommunicationReasonResponse
	 */
	@RequestMapping(value="setvendorratingactive", method=RequestMethod.POST)
	public JsonResponse<Integer> setVendorRatingActive(@RequestBody SetVendorRatingActiveParameter parameters) {
		Integer result = vendorService.setVendorRatingActive(parameters);
		return new JsonResponse<Integer>(true, null, result);
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 11. 13.
	 * @author Reo
	 * @param parameters
	 * @return
	 */
	@RequestMapping(value="getvendorformsList", method=RequestMethod.POST)
	public JsonResponse<VendorFormListResponse> getVendorFormsList(@RequestBody GetVendorFormsListParameter parameters) {
		JsonResponse<VendorFormListResponse> results = new JsonResponse<>(true, null, null);
		results.setData(renewalVendorService.getVendorFormsList(parameters));
		return results;
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 11. 13.
	 * @author Reo
	 * @param parameters
	 * @return
	 */
	@RequestMapping(value="setvendorform", method=RequestMethod.POST)
	public JsonResponse<ResultCode> SetVendorForms(@RequestBody SetVendorFormsParameter parameters) {
        JsonResponse<ResultCode> results = new JsonResponse<ResultCode>(true, null, 0, null);
		
		ResultCode result = vendorService.SetVendorForms(parameters);
		
		results.setData(result);
		return results;
	}
    /**
	 * SetBuyerRatingActive
	 * 
	 * @since 2018. 11. 19.
	 * @author Dahye
	 * @param SetBuyerRatingActiveParameter
	 * @return 
	 */
	@RequestMapping(value="setbuyerratingactive", method=RequestMethod.POST)
	public JsonResponse<String> setBuyerRatingActive(@RequestBody SetBuyerRatingActiveParameter parameters) {
		Integer result = vendorService.setBuyerRatingActive(parameters);
		return new JsonResponse<String>(true, null, result, null);
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 11. 14.
	 * @author Reo
	 * @param parameters
	 * @return
	 */
	@RequestMapping(value="delvendorform", method=RequestMethod.POST)
	public JsonResponse<ResultCode> delVendorForm(@RequestBody DelVendorFormParameter parameters) {
        JsonResponse<ResultCode> results = new JsonResponse<ResultCode>(true, null, 0, null);
		
		ResultCode result = vendorService.delVendorForm(parameters);
		
		results.setData(result);
		return results;
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 11. 23.
	 * @author Reo
	 * @param parameters
	 * @return
	 */
	@RequestMapping(value="setvendorcreditcard", method=RequestMethod.POST)
	public JsonResponse<String> setVendorCreditCard(@RequestBody SetVendorCreditCardParameter parameters) {
		ResultCode result = vendorService.setVendorCreditCard(parameters);
		return new JsonResponse<String>(true, result.getResultMsg(), result.getResultCode(), null);
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 12. 14.
	 * @author Reo
	 * @param wholeSalerID
	 * @return
	 */
	@RequestMapping(value="getvendordetailinfodata", method=RequestMethod.GET)
	public JsonResponse<net.fashiongo.webadmin.data.model.vendor.response.GetVendorDetailInfoDataResponse> getVendorDetailInfoData(@RequestParam(value="wid") Integer wholeSalerID) {
		JsonResponse<net.fashiongo.webadmin.data.model.vendor.response.GetVendorDetailInfoDataResponse> results = new JsonResponse<net.fashiongo.webadmin.data.model.vendor.response.GetVendorDetailInfoDataResponse>(true, null, null);

		net.fashiongo.webadmin.data.model.vendor.response.GetVendorDetailInfoDataResponse vendorDetailInfoData = renewalVendorService.getVendorDetailInfoData(wholeSalerID);
		results.setData(vendorDetailInfoData);

		return results; 
	}

    @GetMapping(value = "/autocomplete/{prefix:.+}")
    public JsonResponse<List<VendorAutocomplete>> getVendorsAutoomplete(@PathVariable("prefix") String prefix) {
        JsonResponse<List<VendorAutocomplete>> response = new JsonResponse<>(false, null, null);

        try {
            List<VendorAutocomplete> vendors = vendorService.getVendorsAutoomplete(prefix);
            response.setSuccess(true);
            response.setData(vendors);
        } catch (Exception ex) {
            log.error("Exception Error: ", ex);
            response.setMessage(ex.getMessage());
        }

        return response;
    }
    
    /**
     * @author Kenny/Kyungwoo
     * @since 2019-04-15
     */
    @GetMapping(value = "mediarequests")
    public JsonResponse<PagedResult<VendorContent>> getMediaRequests(
    		@RequestParam(value="pagenum", required=false) String pagenum,
    		@RequestParam(value="pagesize", required=false) String pagesize,
    		@RequestParam(value="company", required=false) String company,
    		@RequestParam(value="datefrom", required=false) String datefrom,
    		@RequestParam(value="dateto", required=false) String dateto,
    		@RequestParam(value="type", required=false) String type,
    		@RequestParam(value="status", required=false) String status) {
        JsonResponse<PagedResult<VendorContent>> response = new JsonResponse<>(false, null, null);
        try {
        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy HH:mm:ss");
            PagedResult<VendorContent> result = vendorService.getVendorContents(
            		StringUtil.isNullOrEmpty(pagenum) ? null : Integer.parseInt(pagenum),
            		StringUtil.isNullOrEmpty(pagesize) ? null : Integer.parseInt(pagesize),
            		company,
            		StringUtil.isNullOrEmpty(datefrom) ? null : LocalDateTime.parse(datefrom+" 00:00:00", formatter),
            		StringUtil.isNullOrEmpty(dateto) ? null : LocalDateTime.parse(dateto+" 23:59:59", formatter),
            		StringUtil.isNullOrEmpty(type)? null : Integer.parseInt(type),
            		StringUtil.isNullOrEmpty(status) ? null : Integer.parseInt(status));
            
            response.setSuccess(true);
            response.setData(result);
        } catch (Exception ex) {
            log.error("Exception Error: ", ex);
            response.setMessage(ex.getMessage());
        }
        return response;
    }
    
    /**
     * @author Kenny/Kyungwoo
     * @since 2019-04-16
     */
    @PostMapping(value = "mediarequest/{vendorContentId}/approve")
    public JsonResponse<String> approveMediaRequest(@PathVariable("vendorContentId") String vendorContentId) {
    	JsonResponse<String> response = new JsonResponse<>(false, null, null);
    	try {
    		vendorService.approveVendorContent(Integer.parseInt(vendorContentId));
    		response.setSuccess(true);
    	} catch (Exception ex) {
            log.error("Exception Error: ", ex);
            response.setMessage(ex.getMessage());
        }
    	return response;
    }
    
    /**
     * @author Kenny/Kyungwoo
     * @since 2019-04-16
     */
    @PostMapping(value = "mediarequest/{vendorContentId}/deny")
    public JsonResponse<String> denyMediaRequest(
    		@PathVariable("vendorContentId") String vendorContentId,
    		@RequestBody Map<String,String> body) {
    	JsonResponse<String> response = new JsonResponse<>(false, null, null);
    	try {
    		vendorService.denyVendorContent(Integer.parseInt(vendorContentId), body.get("reason"));
    		response.setSuccess(true);
    	} catch (Exception ex) {
            log.error("Exception Error: ", ex);
            response.setMessage(ex.getMessage());
        }
    	return response;
    }
    
    /**
     * @author Kenny/Kyungwoo
     * @since 2019-04-18
     */
    @GetMapping(value = "getassignedusers")
    public JsonResponse<List<SecurityUser>> getAssignedUsers() {
    	JsonResponse<List<SecurityUser>> response = new JsonResponse<>(false, null, null);
    	try {
    		response.setData(vendorService.getAssignedUsers());
    		response.setSuccess(true);
    	} catch (Exception ex) {
            log.error("Exception Error: ", ex);
            response.setMessage(ex.getMessage());
        }
    	return response;
    }

    @GetMapping(value = "contractplans")
    public JsonResponse<List<ContractPlan>> getContractPlans() {
	    JsonResponse<List<ContractPlan>> response = new JsonResponse<>(false, null, null);
	    try {
		    response.setData(vendorService.getContractPlans());
		    response.setSuccess(true);
	    } catch (Exception ex) {
		    log.error("Exception Error: {}", ex);
		    response.setMessage(ex.getMessage());
	    }
	    return response;
    }

    @PostMapping(value = "getvwpaymentmethodsforvendor")
	public JsonResponse<List<GetVwPaymentMethodsForVendor>> getvwpaymentmethodsforvendor(@RequestBody GetVwPaymentMethodsForVendorParameter parameter) {
	    JsonResponse<List<GetVwPaymentMethodsForVendor>> response = new JsonResponse<>();

	    List<GetVwPaymentMethodsForVendor> data = renewalVendorService.getVwPaymentMethodsForVendor(parameter);

	    response.setSuccess(true);
	    response.setData(data);

		return response;
    }

	@PostMapping(value = "getvwshipmethodsforvendor")
	public JsonResponse<List<GetVwShipMethodsForVendor>> getvwshipmethodsforvendor(@RequestBody GetVwShipMethodsForVendorParameter parameter) {
		JsonResponse<List<GetVwShipMethodsForVendor>> response = new JsonResponse<>();

		List<GetVwShipMethodsForVendor> data = renewalVendorService.getVwShipMethodsForVendor(parameter);

		response.setSuccess(true);
		response.setData(data);

		return response;
	}

    @PostMapping(value = "setnewpassword")
	public JsonResponse setnewpassword(@RequestBody SetNewPasswordParameter param) {
		SetModifyPasswordParameter parameter = new SetModifyPasswordParameter();
		parameter.setUserName(param.getUserid());
		parameter.setNewPassword(param.getNewpassword());

    	ResultCode result = userService.resetPassword(parameter);

    	return new JsonResponse<String>(result.getSuccess(), result.getResultMsg(), result.getResultCode(), null);
    }

	@PostMapping(value = "getvendorindustry")
	public JsonResponse<List<CodeVendorIndustryEntity>> getvendorindustry() {
		JsonResponse<List<CodeVendorIndustryEntity>> response = new JsonResponse<>(false, null, null);

		try {
			response.setData(renewalVendorService.getCodeVendorIndustryEntity());
			response.setSuccess(true);
		} catch (Exception ex) {
			log.error("Exception Error: {}", ex);
			response.setMessage(ex.getMessage());
		}

		return response;
	}

	@PostMapping(value = "getvendorbasicinfo")
	public JsonResponse<GetVendorBasicInfoResponse> getvendorbasicinfo(@RequestBody GetVendorBasicInfoParameter param) {
    	JsonResponse<GetVendorBasicInfoResponse> response = new JsonResponse<>(false, null, null);
    	Integer wid = param.getWid();

    	try {
    		response.setData(renewalVendorService.getVendorDetailInfo(wid));
    		response.setSuccess(true);
		} catch (Exception ex) {
    		log.error("Exception Error: {}", ex);
    		response.setMessage(ex.getMessage());
		}

    	return response;
	}

	@PostMapping(value = "setaccountlockout")
	public Integer setaccountlockout(@RequestBody SetAccountLockOutParameter param) {
    	Integer wholeSalerID = param.getWholeSalerID() == null ? 0 : param.getWholeSalerID();
    	Boolean active = param.getActive() == null ? false : param.getActive();

		Integer result = renewalVendorService.setAccountLockOut(active, wholeSalerID);

		cacheService.cacheEvictVendor(wholeSalerID);

		return result;
	}

	@PostMapping(value = "setaccountlockoutsubaccount")
	public Integer setaccountlockoutsubaccount(@RequestBody SetAccountLockOutParameter param) {
		Integer wholeSalerID = param.getWholeSalerID() == null ? 0 : param.getWholeSalerID();
		Boolean active = param.getActive() == null ? false : param.getActive();

		Integer result = renewalVendorService.setAccountLockOutSubAccount(active, wholeSalerID);

    	return result;
	}

	@PostMapping(value = "setvendorcommunication")
	public ResultCode setvendorcommunication(@RequestBody SetVendorCommunicationParameter param) {
		Integer communitaionID = param.getCommunicationID() == null ? 0 : param.getCommunicationID();
		String notes = StringUtils.isEmpty(param.getNotes()) ? "" : param.getNotes();
		Integer wid = param.getWid() == null ? 0 : param.getWid();

		ResultCode result = renewalVendorService.setVendorCommunication(communitaionID, notes, wid);

    	cacheService.cacheEvictVendor(wid);

    	return result;
	}

	@PostMapping(value = "getvendorcommunicationlist")
	public JsonResponse<GetVendorCommunicationListResponse> getvendorcommunicationlist(@RequestBody GetVendorCommunicationListParameter param) {
		JsonResponse<GetVendorCommunicationListResponse> response = new JsonResponse<>(false, null, null);
    	Integer wid = param.getWid() == null ? 0 : param.getWid();

    	try {
			List<VendorCommunicationList> vclist = renewalVendorService.getVendorCommunicationList(wid);

			response.setSuccess(true);
			response.setData(GetVendorCommunicationListResponse.builder().vclist(vclist).build());
		} catch (Exception ex) {
			log.error("Exception Error: {}", ex);
			response.setMessage(ex.getMessage());
		}

    	return response;
	}

	@PostMapping(value = "delvendorcommunication")
	public ResultCode delvendorcommunication(@RequestBody DelVendorCommunicationParameter param) {
    	Integer communicationID = param.getCommunicationID() == null ? 0 : param.getCommunicationID();

    	ResultCode result = renewalVendorService.delVendorCommunication(communicationID);

    	return result;
	}

    @PostMapping(value = "setholdvendor")
    public ResultCode setholdvendor(@RequestBody SetHoldVendorParameter param) {
    	Integer wholeSalerID = param.getWholeSalerID() == null ? 0 : param.getWholeSalerID();
    	Integer holdType = param.getHoldType() == null ? 0 : param.getHoldType();
    	Boolean active = param.getActive() == null ? false : param.getActive();

		Date holdFromDate;
		Date holdToDate;
		Timestamp holdFrom = Timestamp.valueOf(LocalDateTime.now());
		Timestamp holdTo = Timestamp.valueOf(LocalDateTime.now());
		try {
			holdFromDate = StringUtils.isEmpty(param.getHoldFrom()) ? new Date() : new SimpleDateFormat("MM/dd/yyyy").parse(param.getHoldFrom());
			holdToDate = StringUtils.isEmpty(param.getHoldTo()) ? new Date() : new SimpleDateFormat("MM/dd/yyyy").parse(param.getHoldTo());

			holdFrom = StringUtils.isEmpty(param.getHoldFrom()) ? Timestamp.valueOf(LocalDateTime.now()) : new Timestamp(holdFromDate.getTime());
			holdTo = StringUtils.isEmpty(param.getHoldTo()) ? Timestamp.valueOf(LocalDateTime.now()) : new Timestamp(holdToDate.getTime());
		} catch (ParseException e) {
			log.warn(e.getMessage(), e);
		}

    	ResultCode result = renewalVendorService.setHoldVendor(wholeSalerID, holdType, active, holdFrom, holdTo);

    	cacheService.cacheEvictVendor(wholeSalerID);

    	return result;
	}

	@PostMapping(value = "setholdvendorupdate")
	public Integer setholdvendorupdate(@RequestBody SetHoldVendorUpdateParameter param) {
		Integer wholeSalerID = param.getWholeSalerID() == null ? 0 : param.getWholeSalerID();
		Integer logID = param.getLogID() == null ? 0 : param.getLogID();
    	Boolean active = param.getActive() == null ? false : param.getActive();
		Date holdFromDate;
		Date holdToDate;
		Timestamp holdFrom = Timestamp.valueOf(LocalDateTime.now());
		Timestamp holdTo = Timestamp.valueOf(LocalDateTime.now());
		try {
			holdFromDate = StringUtils.isEmpty(param.getHoldFrom()) ? new Date() : new SimpleDateFormat("MM/dd/yyyy").parse(param.getHoldFrom());
			holdToDate = StringUtils.isEmpty(param.getHoldTo()) ? new Date() : new SimpleDateFormat("MM/dd/yyyy").parse(param.getHoldTo());

			holdFrom = StringUtils.isEmpty(param.getHoldFrom()) ? Timestamp.valueOf(LocalDateTime.now()) : new Timestamp(holdFromDate.getTime());
			holdTo = StringUtils.isEmpty(param.getHoldTo()) ? Timestamp.valueOf(LocalDateTime.now()) : new Timestamp(holdToDate.getTime());
		} catch (ParseException e) {
			log.warn(e.getMessage(), e);
		}

		Integer result = renewalVendorService.setHoldVendorUpdate(wholeSalerID, logID, active,holdFrom, holdTo);

		cacheService.cacheEvictVendor(null);

    	return result;
	}

	@PostMapping(value = "setentityactionlog")
	public Integer setentityactionlog(@RequestBody SetEntityActionLogParameter param) {
    	Integer entityTypeID = param.getEntityTypeID() == null ? 0 : param.getEntityTypeID();
    	Integer wholeSalerID = param.getWholeSalerID() == null ? 0 : param.getWholeSalerID();
    	Integer actionID = param.getActionID() == null ? 0 : param.getActionID();

    	// TODO
    	Integer result = vendorInfoService.setEntityActionLog(entityTypeID, wholeSalerID, actionID);

    	return result;
	}

	@PostMapping(value = "vendordirnamecheck")
	public Boolean vendordirnamecheck(@RequestBody VendorDirNameCheckParameter param) {
		Integer wholeSalerID = param.getWholeSalerID() == null ? 0 : param.getWholeSalerID();
		String codeName = StringUtils.isEmpty(param.getDirName()) ? "" : param.getDirName();

		Boolean result = renewalVendorService.vendorDirNameCheck(wholeSalerID, codeName);

    	return result;
	}

	@PostMapping(value = "vendorcodenamecheck")
	public JsonResponse<GetVendorCodeNameCheckResponse> vendorcodenamecheck(@RequestBody VendorCodeNameCheckParameter param) {
        JsonResponse<GetVendorCodeNameCheckResponse> response = new JsonResponse<>(false, null, null);

        Integer wholeSalerID = param.getWholeSalerID() == null ? 0 : param.getWholeSalerID();
        String codeName = StringUtils.isEmpty(param.getCodeName()) ? "" : param.getCodeName();

        try {
	        Long result = renewalVendorService.vendorCodeNameCheck(wholeSalerID, codeName);

			response.setSuccess(true);
			response.setData(GetVendorCodeNameCheckResponse.builder().codeNameCount(result).build());
			response.setMessage("success");
		} catch (Exception ex) {
        	log.warn(ex.getMessage(), ex);
        	response.setMessage("fail");
		}

        return response;
    }

	@GetMapping(value = "getvendorsecurityusers")
	public JsonResponse<List<SecurityUserEntity>> getvendorsecurityusers() {
    	JsonResponse<List<SecurityUserEntity>> response = new JsonResponse<>(false, null, null);

    	try {
			List<SecurityUserEntity> result = renewalVendorService.getVendorSecurityUsers();

			response.setSuccess(true);
			response.setData(result);
			response.setMessage("success");
		} catch (Exception ex) {
    		log.warn(ex.getMessage(), ex);
    		response.setMessage("fail");
		}

    	return response;
	}

	@PostMapping(value = "setvendorsettingaccount")
	public ResultCode setvendorsettingaccount(@RequestBody SetVendorSettingAccountParamter param) {
		Integer wid = param.getWid();

		ResultCode result = renewalVendorService.setVendorSettingAccount(wid);

    	return result;
	}

	@GetMapping(value = "getvendoradminaccountlist")
	public JsonResponse getvendoradminaccountlist(@RequestParam(value = "WholeSalerID") Integer wholeSalerID) {
    	Integer wid = wholeSalerID == null ? 0 : wholeSalerID;

    	JsonResponse response = renewalVendorService.getVendorAdminAccountList(wid);

    	return response;
	}

	@GetMapping(value = "getvendoradminaccountloglist")
	public JsonResponse<GetVendorAdminAccountLogListResponse> getvendoradminaccountloglist(
			@RequestParam(value = "pagenum") Integer pagenum,
			@RequestParam(value = "pagesize") Integer pagesize,
			@RequestParam(value = "WholeSalerID") Integer wholeSalerID,
			@RequestParam(value = "UserID") String userID,
			@RequestParam(value = "Date") String dateString,
			@RequestParam(value = "IPAddress") String ipAddress,
			@RequestParam(value = "orderby") String orderBy
	) {
    	Integer pageNum = pagenum == null ? 0 : pagenum;
    	Integer pageSize = pagesize == null ? 0 : pagesize;
    	Integer wid = wholeSalerID == null ? 0 : wholeSalerID;
    	String uID = StringUtils.isEmpty(userID) ? "" : userID;

    	dateString = dateString.replace("%2F", "/");

		LocalDate dateLocalDate = StringUtils.isEmpty(dateString) ? null : LocalDate.parse(dateString, DateTimeFormatter.ofPattern("MM/dd/yyyy"));

		ipAddress = StringUtils.isEmpty(ipAddress) ? "" : ipAddress;

		JsonResponse<GetVendorAdminAccountLogListResponse> response = new JsonResponse<>(false, null, null);

		try {
			GetVendorAdminAccountLogListResponse data = renewalVendorService.getVendorAdminAccountLogList(pageNum, pageSize, wid, uID, dateLocalDate, ipAddress, orderBy);
			response.setSuccess(true);
			response.setData(data);
			response.setMessage("success");
		} catch (Exception e) {
			log.warn(e.getMessage(), e);
			response.setMessage("fail");
		}

		return response;
	}

	@GetMapping(value = "getvendorgrouping")
	public JsonResponse<GetVendorGroupingResponse> getvendorgrouping(
			@RequestParam(value = "wholesalerid") Integer wholeSalerID,
			@RequestParam(value = "CompanyType") String companyType,
			@RequestParam(value = "vendortype") String vendorType,
			@RequestParam(value = "searchkeyword") String keyword,
			@RequestParam(value = "Categorys") String categorys,
			@RequestParam(value = "alphabet") String alphabet) {
		JsonResponse<GetVendorGroupingResponse> response = new JsonResponse<>(false, null, null);

		try {
			GetVendorGroupingResponse data = renewalVendorService.getVendorGrouping(wholeSalerID, companyType, vendorType, keyword, categorys, alphabet);

			response.setSuccess(true);
			response.setData(data);
			response.setMessage("success");
		} catch (Exception e) {
			log.warn(e.getMessage(), e);
			response.setMessage("fail");
		}

		return response;
	}

	@PostMapping(value = "setvendorgrouping")
	public JsonResponse<Integer> setvendorgrouping(@RequestBody SetVendorGroupingParameter param) {
    	JsonResponse<Integer> response = new JsonResponse<Integer>(false, null, null);

    	Integer wid = param.getWid() == null ? 0 : param.getWid();
    	String saveIds = StringUtils.isEmpty(param.getSaveIds()) ? "" : param.getSaveIds();
    	String deleteIds = StringUtils.isEmpty(param.getDeleteIds()) ? "" : param.getDeleteIds();

    	try {
			Integer result = renewalVendorService.setVendorGrouping(wid, saveIds, deleteIds);
			response.setSuccess(true);
			response.setData(result);
			response.setMessage("success");
		} catch (Exception e) {
    		response.setMessage("fail");
		}

    	cacheService.cacheEvictVendor(wid);

    	return response;
	}

	@PostMapping(value = "getassigneduserlist")
	public JsonResponse<GetAssignedUserListResponse> getassigneduserlist() {
		JsonResponse<GetAssignedUserListResponse> response = new JsonResponse<GetAssignedUserListResponse>(false, null, null);

		try {
			GetAssignedUserListResponse data = renewalVendorService.getAssignedUserList();

			response.setSuccess(true);
			response.setData(data);
			response.setMessage("success");
		} catch (Exception e) {
			log.warn(e.getMessage(), e);

			response.setMessage("fail");
		}

    	return response;
	}

	@RequestMapping(value="setretailerratingactive", method=RequestMethod.POST)
	public JsonResponse<Integer> setRetailerRatingActive(@RequestBody SetRetailerRatingActiveParameter parameters) {
    	Integer result = renewalVendorService.setRetailerRatingActive(parameters);
		return new JsonResponse<Integer>(true, null, result);
	}
}
	
