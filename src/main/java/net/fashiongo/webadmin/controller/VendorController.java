package net.fashiongo.webadmin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.entity.primary.CodeVendorIndustryEntity;
import net.fashiongo.webadmin.data.entity.primary.ListVendorDocumentTypeEntity;
import net.fashiongo.webadmin.data.entity.primary.vendor.ProductColorRow;
import net.fashiongo.webadmin.data.model.buyer.SetAccountLockOutParameter;
import net.fashiongo.webadmin.data.model.vendor.*;
import net.fashiongo.webadmin.data.model.vendor.Vendor;
import net.fashiongo.webadmin.data.model.vendor.response.GetVendorBasicInfoResponse;
import net.fashiongo.webadmin.data.model.vendor.response.GetVendorCodeNameCheckResponse;
import net.fashiongo.webadmin.data.model.vendor.response.GetVendorCommunicationListResponse;
import net.fashiongo.webadmin.data.model.vendor.response.GetVendorSettingResponse;
import net.fashiongo.webadmin.model.pojo.buyer.parameter.SetModifyPasswordParameter;
import net.fashiongo.webadmin.model.pojo.common.PagedResult;
import net.fashiongo.webadmin.model.pojo.common.ResultCode;
import net.fashiongo.webadmin.model.pojo.parameter.*;
import net.fashiongo.webadmin.model.pojo.sitemgmt.response.DeleteCommunicationReasonResponse;
import net.fashiongo.webadmin.model.pojo.vendor.parameter.*;
import net.fashiongo.webadmin.model.pojo.vendor.response.GetVendorCreditCardListResponse;
import net.fashiongo.webadmin.model.primary.*;
import net.fashiongo.webadmin.service.CacheService;
import net.fashiongo.webadmin.service.UserService;
import net.fashiongo.webadmin.service.VendorService;
import net.fashiongo.webadmin.service.renewal.RenewalVendorService;
import net.fashiongo.webadmin.utility.JsonResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	
	/**
	 * Get vendor list
	 * @since 2018. 10. 15.
	 * @author roy
	 * @return vendor list
	 */
	@RequestMapping(value="getvendorlistall", method=RequestMethod.POST)
	public JsonResponse<List<Vendor>> getVendorListAll() {
		
		List<Vendor> vendors = renewalVendorService.getVendorList();
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
	 * 
	 * Description Example
	 * @since 2018. 11. 12.
	 * @author Reo
	 * @param parameters
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value="getvendorblockList", method=RequestMethod.POST)
	public JsonResponse<List<VwVendorBlocked>> getVendorBlockList(@RequestBody GetVendorBlockListParameter parameters) throws ParseException {
		JsonResponse<List<VwVendorBlocked>> results = new JsonResponse<List<VwVendorBlocked>>(false, null, 0, null);
		List<VwVendorBlocked> result = vendorService.getVendorBlockList(parameters);
		
		results.setData(result);
		results.setSuccess(true);
		return results;
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 11. 12.
	 * @author Reo
	 * @param wholeSalerID
	 * @return
	 */
	@RequestMapping(value="getvendorblockhistoryList", method=RequestMethod.GET)
	public JsonResponse<List<EntityActionLog>> getVendorBlockHistoryList(@RequestParam(value="WholeSalerID") Integer wholeSalerID) {
		JsonResponse<List<EntityActionLog>> results = new JsonResponse<List<EntityActionLog>>(false, null, 0, null);
		List<EntityActionLog> result = vendorService.getVendorBlockHistoryList(wholeSalerID);
		
		results.setData(result);
		results.setSuccess(true);
		return results;
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
	 * 
	 * Description Example
	 * @since 2018. 11. 13.
	 * @author Reo
	 * @return
	 */
	@RequestMapping(value="getvendorimagetype", method=RequestMethod.POST)
	public JsonResponse<List<ListVendorImageType>> getVendorImageType() {
		JsonResponse<List<ListVendorImageType>> results = new JsonResponse<List<ListVendorImageType>>(false, null, 0, null);
		List<ListVendorImageType> result = vendorService.getVendorImageType();
		
		results.setData(result);
		results.setSuccess(true);
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
	@RequestMapping(value="getbannerrequest", method=RequestMethod.POST)
	public JsonResponse<BannerRequestResponse> getBannerRequest(@RequestBody GetBannerRequestParameter parameters) {
		JsonResponse<BannerRequestResponse> results = new JsonResponse<>(true, null, null);
		results.setData(renewalVendorService.getBannerRequest(parameters));
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
	@RequestMapping(value="setdenybanner", method=RequestMethod.POST)
	public JsonResponse<ResultCode> setDenyBanner(@RequestBody SetDenyBannerParameter parameters) {
		JsonResponse<ResultCode> results = new JsonResponse<ResultCode>(true, null, 0, null);
		
		ResultCode result = vendorService.setDenyBanner(parameters);
		
		results.setData(result);
		cacheService.GetRedisCacheEvict("vendorActivated", null);
		cacheService.GetRedisCacheEvict("vendorDeactivated", null);
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
	@RequestMapping(value="setapprovebanner", method=RequestMethod.POST)
	public JsonResponse<ResultCode> setApproveBanner(@RequestBody SetDenyBannerParameter parameters) {
        JsonResponse<ResultCode> results = new JsonResponse<ResultCode>(true, null, 0, null);
		
		ResultCode result = vendorService.setApproveBanner(parameters);
		
		results.setData(result);
		cacheService.GetRedisCacheEvict("vendorActivated", null);
		cacheService.GetRedisCacheEvict("vendorDeactivated", null);
		return results;
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
	 * 
	 * Description Example
	 * @since 2018. 11. 13.
	 * @author Reo
	 * @param parameters
	 * @return
	 */
	@RequestMapping(value="setrestorebanner", method=RequestMethod.POST)
	public JsonResponse<ResultCode> setRestoreBanner(@RequestBody SetDenyBannerParameter parameters) {
        JsonResponse<ResultCode> results = new JsonResponse<ResultCode>(true, null, 0, null);
		
		ResultCode result = vendorService.setRestoreBanner(parameters);
		
		results.setData(result);
		cacheService.GetRedisCacheEvict("vendorActivated", null);
		cacheService.GetRedisCacheEvict("vendorDeactivated", null);
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
	@RequestMapping(value="delbannerbanner", method=RequestMethod.POST)
	public JsonResponse<ResultCode> delBannerRequest(@RequestBody SetDenyBannerParameter parameters) {
        JsonResponse<ResultCode> results = new JsonResponse<ResultCode>(true, null, 0, null);
		
		ResultCode result = vendorService.delBannerRequest(parameters);
		
		results.setData(result);
		return results;
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
	public JsonResponse<DeleteCommunicationReasonResponse> setVendorRatingActive(@RequestBody SetVendorRatingActiveParameter parameters) {
		Integer result = vendorService.setVendorRatingActive(parameters);
		return new JsonResponse<DeleteCommunicationReasonResponse>(true, null, result, null);
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
	@RequestMapping(value="getvendorcontract", method=RequestMethod.GET)
	public JsonResponse<List<VendorContract>> getVendorContract(@RequestParam(value="WholeSalerID") Integer wholeSalerID) {
		JsonResponse<List<VendorContract>> results = new JsonResponse<List<VendorContract>>(false, null, 0, null);
		List<VendorContract> result = vendorService.getVendorContract(wholeSalerID);
		
		results.setData(result);
		results.setSuccess(true);
		return results;
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 12. 14.
	 * @author Reo
	 * @param vendorContractID
	 * @return
	 */
	@RequestMapping(value="getvendorcontractdocumenthistory", method=RequestMethod.GET)
	public JsonResponse<net.fashiongo.webadmin.data.model.vendor.response.GetVendorContractDocumentHistoryResponse> getVendorContractDocumentHistory(@RequestParam(value="VendorContractID") Integer vendorContractID) {
		JsonResponse<net.fashiongo.webadmin.data.model.vendor.response.GetVendorContractDocumentHistoryResponse> results = new JsonResponse<net.fashiongo.webadmin.data.model.vendor.response.GetVendorContractDocumentHistoryResponse>(true, null, null);

		net.fashiongo.webadmin.data.model.vendor.response.GetVendorContractDocumentHistoryResponse vendorContractDocumentHistory = renewalVendorService.getVendorContractDocumentHistory(vendorContractID);
		results.setData(vendorContractDocumentHistory);
		
		return results; 
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

    @PostMapping(value = "getvendordocumenttypelist")
	public JsonResponse<List<ListVendorDocumentTypeEntity>> getvendordocumenttypelist() {
    	JsonResponse<List<ListVendorDocumentTypeEntity>> response = new JsonResponse<>(false, null, null);

    	try {
    		response.setData(renewalVendorService.getListVendorDocumentType());
    		response.setSuccess(true);
		} catch (Exception ex) {
    		log.error("Exception Error: {}", ex);
    		response.setMessage(ex.getMessage());
		}

    	return response;
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

	@PostMapping(value = "getvendorimage")
	public JsonResponse<List<VendorImage>> getvendorimage(@RequestBody GetVendorImageParameter param) {
    	JsonResponse<List<VendorImage>> response = new JsonResponse<>(false, null, null);
		Integer wid = param.getWid();

		try {
			response.setData(renewalVendorService.getVendorImage(wid));
			response.setSuccess(true);
		} catch (Exception ex) {
			log.error("Exception Error: {}", ex);
			response.setMessage(ex.getMessage());
		}

		return response;
	}

	@PostMapping(value = "setvendorbasicinfo")
	public Integer setvendorbasicinfo(@RequestBody SetVendorBasicInfoParameter param) {
    	Integer wid = param.getWid();
		ObjectMapper mapper = new ObjectMapper();
		VendorDetailInfo r;
		try {
			r = mapper.readValue(param.getVendorBasicInfo(), VendorDetailInfo.class);
		} catch (IOException e) {
			log.debug("object mapper parse error");
			return null;
		}

		Integer result = renewalVendorService.setVendorBasicInfo(r, 1, null, null, null, 0);

	 	if (result.toString().equals("1")) {
	 		String companyNameTemp = "";
	 		if (StringUtils.isNotEmpty(param.getCompanyNameTemp())) {
	 			companyNameTemp = param.getCompanyNameTemp();
			}

	 		if (!companyNameTemp.equals("") && companyNameTemp != null && !companyNameTemp.equals(r.getCompanyName()) && !r.getCompanyName().equals("") && r.getCompanyName() != null) {
	 			if (r.getDirName() == null) r.setDirName("");
	 			renewalVendorService.setDirCompanyNameChangeHistory(wid, r.getDirName(), r.getDirName(), companyNameTemp, r.getCompanyName());
			}

		 	cacheService.cacheEvictVendor(wid);
		}

		return result;
	}

	@PostMapping(value = "setvendorimage")
	public Integer setvendorimage(@RequestBody SetVendorImageParameter param) {
    	if (param.getWid() == null|| param.getType() == null || StringUtils.isEmpty(param.getFilename())) {
    		return -1;
		}

    	Integer wid = param.getWid();
    	Integer type = param.getType();
    	String fileName = param.getFilename();
    	String userID = StringUtils.isEmpty(param.getUserid()) ? "admin" : param.getUserid();

    	Integer result = renewalVendorService.setVendorImage(wid, type, fileName, userID);

		cacheService.GetRedisCacheEvict("VendorPictureLogo", String.valueOf(wid));

		cacheService.cacheEvictVendor(wid);

    	return result;
	}

	@PostMapping(value = "delvendorimage")
	public Integer delvendorimage(@RequestBody DelVendorImageParameter param) {
    	if(param.getWid() == null | param.getType() == null) {
    		return -1;
		}

    	Integer wid = param.getWid();
    	Integer type = param.getType();

    	Integer result = renewalVendorService.delVendorImage(wid, type);

		cacheService.GetRedisCacheEvict("VendorPictureLogo", String.valueOf(wid));

		return result;
	}

	@PostMapping(value = "setvendorsnslist")
	public ResultCode setvendorsnslist(@RequestBody SetVendorSNSListParameter param) {
    	Integer mapID = param.getMapID() == null ? 0 : param.getMapID();
    	Integer wholeSalerID = param.getWholeSalerID() == null ? 0 : param.getWholeSalerID();
    	Integer socialMediaID = param.getSocialMediaID() == null ? 0 : param.getSocialMediaID();
    	String socialMediaUsername = StringUtils.isEmpty(param.getSocialMediaUsername()) ? "" : param.getSocialMediaUsername();

    	ResultCode result = renewalVendorService.setVendorSNSList(mapID, wholeSalerID, socialMediaID, socialMediaUsername);

    	cacheService.cacheEvictVendor(wholeSalerID);

    	return result;
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

	@PostMapping(value = "getvendorsetting")
	public JsonResponse<GetVendorSettingResponse> getvendorsetting(@RequestBody GetVendorSettingParameter param) {
    	JsonResponse<GetVendorSettingResponse> response = new JsonResponse<>(false, null, null);

    	try {
			GetVendorSettingResponse result = renewalVendorService.getVendorSetting(param.getWid());

			response.setSuccess(true);
			response.setData(result);
    	} catch (Exception ex) {
			log.error("Exception Error: {}", ex);
			response.setMessage(ex.getMessage());
		}

    	return response;
	}

	@PostMapping(value = "setvendorsetting")
	public Integer setvendorsetting(@RequestBody SetVendorSettingParameter param) {
    	Integer wid = param.getWid();
    	Integer adminAccount = param.getAdminAccount() == null ? 0 : param.getAdminAccount();
    	Integer vendorCategory = param.getVendorCategory() == null ? 0 : param.getVendorCategory();
    	Integer fraudReport = param.getFraudReport() == null ? 0 : param.getFraudReport();
    	Integer item = param.getItem() == null ? 0 : param.getItem();
    	Integer adminAccountID = param.getAdminAccountID() == null ? 0 : param.getAdminAccountID();
    	Integer vendorCategoryID = param.getVendorCategoryID() == null ? 0 : param.getVendorCategoryID();
    	Integer fraudReportID = param.getFraudReportID() == null ? 0 : param.getFraudReportID();
    	Integer itemID = param.getItemID() == null ? 0 : param.getItemID();
    	LocalDateTime actualOpenDateTemp = StringUtils.isEmpty(param.getActualOpenDateTemp()) ? null : LocalDateTime.parse(param.getActualOpenDateTemp());

		renewalVendorService.setVendorSetting(wid, adminAccountID, 1, adminAccount);
		renewalVendorService.setVendorSetting(wid, vendorCategoryID, 2, vendorCategory);
		renewalVendorService.setVendorSetting(wid, fraudReportID, 3, fraudReport);
		renewalVendorService.setVendorSetting(wid, itemID, 4, item);

		Integer payoutSchedule = param.getPayoutSchedule();
		Integer payoutScheduleWM = param.getPayoutScheduleWM();
		Integer maxPayoutPerDay = param.getMaxPayoutPerDay();
		Integer payoutCount = param.getPayoutCount();

		ObjectMapper mapper = new ObjectMapper();
		VendorDetailInfo r;
		try {
			r = mapper.readValue(param.getVendorBasicInfo(), VendorDetailInfo.class);
		} catch (IOException e) {
			log.debug("object mapper parse error");
			return null;
		}

		Integer result = renewalVendorService.setVendorBasicInfo(r, 2, payoutSchedule, payoutScheduleWM, maxPayoutPerDay, payoutCount);

		if(result == -1) {
			return result;
		}

		String dirNameTemp = StringUtils.isEmpty(param.getDirNameTemp()) ? "" : param.getDirNameTemp();

		cacheService.cacheEvictVendor(wid);

    	return result;
	}

	@PostMapping(value = "setvendorblock")
	public ResultCode setvendorblock(@RequestBody SetVendorBlockParameter param) {
		Integer wholeSalerID = param.getWholeSalerID() == null ? 0 : param.getWholeSalerID();
		Integer blockReasonID = param.getBlockReasonID() == null ? 0 : param.getBlockReasonID();
		String reason = param.getReason();

		ResultCode result = renewalVendorService.setVendorBlock(wholeSalerID, blockReasonID, reason);

		cacheService.cacheEvictVendor(wholeSalerID);

		return result;
	}

	@PostMapping(value = "setvendorblockupdate")
	public Integer setvendorblockupdate(@RequestBody SetVendorBlockUpdate param) {
		Integer wholeSalerID = param.getWholeSalerID() == null ? 0 : param.getWholeSalerID();
		Integer blockReasonID = param.getBlockReasonID() == null ? 0 : param.getBlockReasonID();

		Integer result = renewalVendorService.setBlockVendorUpdate(wholeSalerID, blockReasonID);

		return result;
	}

	@PostMapping(value = "delvendorblock")
	public ResultCode delvendorblock(@RequestBody DelVendorBlockParameter param) {
        Integer blockID = param.getBlockID() == null ? 0 : param.getBlockID();
        Integer wholeSalerID = param.getWholeSalerID() == null ? 0 : param.getWholeSalerID();

        ResultCode result = renewalVendorService.delVendorBlock(blockID, wholeSalerID);

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

		Integer result = renewalVendorService.setHoldVendorUpdate(logID, active,holdFrom, holdTo);

		cacheService.cacheEvictVendor(null);

    	return result;
	}

	@PostMapping(value = "setentityactionlog")
	public Integer setentityactionlog(@RequestBody SetEntityActionLogParameter param) {
    	Integer entityTypeID = param.getEntityTypeID() == null ? 0 : param.getEntityTypeID();
    	Integer wholeSalerID = param.getWholeSalerID() == null ? 0 : param.getWholeSalerID();
    	Integer actionID = param.getActionID() == null ? 0 : param.getActionID();

    	Integer result = renewalVendorService.setEntityActionLog(entityTypeID, wholeSalerID, actionID);

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

    @PostMapping(value = "getvendorsister")
    public JsonResponse<List<VendorSister>> getvendorsister(@RequestBody GetVendorSisterParameter param) {
		JsonResponse<List<VendorSister>> response = new JsonResponse<>(false, null, null);

		try {
			List<VendorSister> result = renewalVendorService.getVendorSister(param.getWid());

			response.setSuccess(true);
			response.setData(result);
			response.setMessage("success");
		} catch (Exception ex) {
			log.warn(ex.getMessage(), ex);
			response.setMessage("fail");
		}
		return response;
	}
}
	
