package net.fashiongo.webadmin.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.fashiongo.webadmin.model.pojo.ProductColor;
import net.fashiongo.webadmin.model.pojo.ResultCode;
import net.fashiongo.webadmin.model.pojo.parameter.DelVendorBlockParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetBannerRequestParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetProductColorParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetProductListParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetVendorBlockListParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetVendorFormsListParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetDenyBannerParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetVendorFormsParameter;
import net.fashiongo.webadmin.model.pojo.response.GetBannerRequestResponse;
import net.fashiongo.webadmin.model.pojo.response.GetProductListResponse;
import net.fashiongo.webadmin.model.pojo.response.GetVendorFormsListResponse;
import net.fashiongo.webadmin.model.primary.EntityActionLog;
import net.fashiongo.webadmin.model.primary.ListVendorImageType;
import net.fashiongo.webadmin.model.primary.VendorCompany;
import net.fashiongo.webadmin.model.primary.VwVendorBlocked;
import net.fashiongo.webadmin.service.CacheService;
import net.fashiongo.webadmin.service.VendorService;
import net.fashiongo.webadmin.utility.JsonResponse;

/**
 * @author roy
 */
@RestController
@RequestMapping(value="/vendor", produces = "application/json")
public class VendorController {
	
	@Autowired
	VendorService vendorService;
	
	@Autowired
    private CacheService cacheService;
	
	/**
	 * Get vendor list
	 * @since 2018. 10. 15.
	 * @author roy
	 * @return vendor list
	 */
	@RequestMapping(value="getvendorlistall", method=RequestMethod.POST)
	public JsonResponse<List<VendorCompany>> getVendorListAll() {
		
		List<VendorCompany> vendors = vendorService.getVendorList();	
		return new JsonResponse<List<VendorCompany>>(true, null, 0, vendors);
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
	public JsonResponse<GetProductListResponse> getProductList(@RequestBody GetProductListParameter parameters) {
		JsonResponse<GetProductListResponse> result = new JsonResponse<GetProductListResponse>(true, null, null);
		
		GetProductListResponse _result = vendorService.getProductList(parameters);	
		result.setData(_result);
		
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
}
