package net.fashiongo.webadmin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fashiongo.webadmin.dao.primary.VendorListRepository;
import net.fashiongo.webadmin.model.pojo.ProductColor;
import net.fashiongo.webadmin.model.pojo.ProductSummary;
import net.fashiongo.webadmin.model.pojo.parameter.GetProductListParameter;
import net.fashiongo.webadmin.model.pojo.response.GetProductColorResponse;
import net.fashiongo.webadmin.model.pojo.response.GetProductListResponse;
import net.fashiongo.webadmin.model.primary.VendorCompany;

/**
 * @author roy
 */
@Service
public class VendorService extends ApiService {
	@Autowired
	private VendorListRepository vendorListRepository;
	
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
	
	public GetProductColorResponse getProductColor(Integer productId) {
		GetProductColorResponse result = new GetProductColorResponse();
		String spName = "up_wa_GetProductColors";
		List<Object> params = new ArrayList<Object>();
		
		params.add(productId);

		List<Object> _result = jdbcHelper.executeSP(spName, params, ProductColor.class);
		result.setColors((List<ProductColor>) _result.get(0));
		
		return result;
	}
}
