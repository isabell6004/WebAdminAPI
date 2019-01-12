package net.fashiongo.webadmin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fashiongo.webadmin.dao.primary.AdPageRepository;
import net.fashiongo.webadmin.dao.primary.VendorAutocompleteRepository;
import net.fashiongo.webadmin.dao.primary.VendorListRepository;
import net.fashiongo.webadmin.model.primary.VendorAutocomplete;
import net.fashiongo.webadmin.dao.primary.VendorListRepository;
import net.fashiongo.webadmin.model.pojo.vendor.ProductColor;
import net.fashiongo.webadmin.model.pojo.vendor.ProductSummary;
import net.fashiongo.webadmin.model.pojo.vendor.parameter.GetProductListParameter;
import net.fashiongo.webadmin.model.pojo.vendor.response.GetProductListResponse;
import net.fashiongo.webadmin.model.primary.VendorCompany;

/**
 * @author roy
 */
@Service
public class VendorService extends ApiService {
	@Autowired
	private VendorListRepository vendorListRepository;
	@Autowired
	private VendorAutocompleteRepository vendorAutocompleteRepository;
	
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
}

