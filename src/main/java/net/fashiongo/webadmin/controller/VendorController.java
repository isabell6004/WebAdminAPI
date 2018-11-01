package net.fashiongo.webadmin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.fashiongo.webadmin.model.pojo.ProductColor;
import net.fashiongo.webadmin.model.pojo.parameter.GetProductColorParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetProductListParameter;
import net.fashiongo.webadmin.model.pojo.response.GetProductListResponse;
import net.fashiongo.webadmin.model.primary.VendorCompany;
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
}
