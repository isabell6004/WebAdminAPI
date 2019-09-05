package net.fashiongo.webadmin.controller;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.model.vendor.VendorProductListResponse;
import net.fashiongo.webadmin.model.primary.*;
import net.fashiongo.webadmin.service.renewal.RenewalVendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.netty.util.internal.StringUtil;
import net.fashiongo.webadmin.model.pojo.common.PagedResult;
import net.fashiongo.webadmin.model.pojo.common.ResultCode;
import net.fashiongo.webadmin.model.pojo.parameter.DelVendorBlockParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetBannerRequestParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetVendorBlockListParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetVendorFormsListParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetDenyBannerParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetVendorFormsParameter;
import net.fashiongo.webadmin.model.pojo.response.GetBannerRequestResponse;

import net.fashiongo.webadmin.model.pojo.vendor.response.GetProductListResponse;
import net.fashiongo.webadmin.model.pojo.vendor.response.GetVendorContractDocumentHistoryResponse;
import net.fashiongo.webadmin.model.pojo.response.GetVendorFormsListResponse;
import net.fashiongo.webadmin.model.pojo.sitemgmt.response.DeleteCommunicationReasonResponse;
import net.fashiongo.webadmin.model.pojo.vendor.ProductColor;
import net.fashiongo.webadmin.model.pojo.vendor.parameter.DelVendorCreditcardParameter;
import net.fashiongo.webadmin.model.pojo.vendor.parameter.DelVendorFormParameter;
import net.fashiongo.webadmin.model.pojo.vendor.parameter.GetProductColorParameter;
import net.fashiongo.webadmin.model.pojo.vendor.parameter.GetProductListParameter;
import net.fashiongo.webadmin.model.pojo.vendor.parameter.GetVendorCreditCardListParameter;
import net.fashiongo.webadmin.model.pojo.vendor.parameter.SetBuyerRatingActiveParameter;
import net.fashiongo.webadmin.model.pojo.vendor.parameter.SetVendorCreditCardParameter;
import net.fashiongo.webadmin.model.pojo.vendor.parameter.SetVendorRatingActiveParameter;
import net.fashiongo.webadmin.model.pojo.vendor.response.GetVendorCreditCardListResponse;
import net.fashiongo.webadmin.model.pojo.vendor.response.GetVendorDetailInfoDataResponse;
import net.fashiongo.webadmin.service.CacheService;
import net.fashiongo.webadmin.service.VendorService;
import net.fashiongo.webadmin.utility.JsonResponse;

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
	
	/**
	 * Get vendor list
	 * @since 2018. 10. 15.
	 * @author roy
	 * @return vendor list
	 */
	@RequestMapping(value="getvendorlistall", method=RequestMethod.POST)
	public JsonResponse<List<Vendor>> getVendorListAll() {
		
		List<Vendor> vendors = vendorService.getVendorList();	
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
	public JsonResponse<List<ProductColor>> getProductColor(@RequestBody GetProductColorParameter parameters) {
		JsonResponse<List<ProductColor>> result = new JsonResponse<List<ProductColor>>(true, null, null);
		
		List<ProductColor> _result = vendorService.getProductColor(parameters.getProductid());	
		result.setData(_result);
		
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
	 * 
	 * Description Example
	 * @since 2018. 11. 12.
	 * @author Reo
	 * @param parameters
	 * @return
	 */
	@RequestMapping(value="delvendorblock", method=RequestMethod.POST)
	public JsonResponse<ResultCode> delVendorBlock(@RequestBody DelVendorBlockParameter parameters) {
		JsonResponse<ResultCode> results = new JsonResponse<ResultCode>(false, null, 0, null);
		ResultCode result = vendorService.delVendorBlock(parameters);
		
		results.setData(result);
		results.setSuccess(true);
		return results;
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
	public JsonResponse<GetBannerRequestResponse> getBannerRequest(@RequestBody GetBannerRequestParameter parameters) {
		JsonResponse<GetBannerRequestResponse> results = new JsonResponse<GetBannerRequestResponse>(true, null, null);
		
		GetBannerRequestResponse result = vendorService.getBannerRequest(parameters);	
		results.setData(result);
		
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
	public JsonResponse<GetVendorFormsListResponse> getVendorFormsList(@RequestBody GetVendorFormsListParameter parameters) {
		JsonResponse<GetVendorFormsListResponse> results = new JsonResponse<GetVendorFormsListResponse>(true, null, null);
		
		GetVendorFormsListResponse result = vendorService.getVendorFormsList(parameters);	
		results.setData(result);
		
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
	@RequestMapping(value="getvendorcommunicationlist", method=RequestMethod.GET)
	public JsonResponse<List<LogCommunication>> getVendorCommunicationList(@RequestParam(value="WholeSalerID") Integer wholeSalerID) {
		JsonResponse<List<LogCommunication>> results = new JsonResponse<List<LogCommunication>>(false, null, 0, null);
		List<LogCommunication> result = vendorService.getVendorCommunicationList(wholeSalerID);
		
		results.setData(result);
		results.setSuccess(true);
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
	public JsonResponse<GetVendorContractDocumentHistoryResponse> getVendorContractDocumentHistory(@RequestParam(value="VendorContractID") Integer vendorContractID) {
		JsonResponse<GetVendorContractDocumentHistoryResponse> results = new JsonResponse<GetVendorContractDocumentHistoryResponse>(true, null, null);
		
		GetVendorContractDocumentHistoryResponse result = vendorService.getVendorContractDocumentHistory(vendorContractID);	
		results.setData(result);
		
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
	public JsonResponse<GetVendorDetailInfoDataResponse> getVendorDetailInfoData(@RequestParam(value="WholeSalerID") Integer wholeSalerID) {
		JsonResponse<GetVendorDetailInfoDataResponse> results = new JsonResponse<GetVendorDetailInfoDataResponse>(true, null, null);
		
		GetVendorDetailInfoDataResponse result = vendorService.getVendorDetailInfoData(wholeSalerID);	
		results.setData(result);
		
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
}
	
