/**
 * 
 */
package net.fashiongo.webadmin.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import net.fashiongo.webadmin.model.pojo.vendor.ProductColor;
import net.fashiongo.webadmin.model.pojo.vendor.parameter.GetProductListParameter;
import net.fashiongo.webadmin.model.pojo.vendor.response.GetProductListResponse;
import net.fashiongo.webadmin.model.primary.VendorCompany;

/**
 * @author roy
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class VendorServiceTest {
	
	@Autowired
	VendorService vendorService;

	/**
	 * 
	 * Test GetVendorList
	 * 
	 * @since 2018. 11. 5.
	 * @author roy
	 */
	@Test
	public void testGetVendorList() {
		List<VendorCompany> vendorList = vendorService.getVendorList();
		
		assertTrue(vendorList.size() > 0);
		
		VendorCompany vendorCompany = vendorList.get(0);
		
		assertNotNull(vendorCompany);
		assertTrue(vendorCompany.getWholeSalerId() > 0);
		assertTrue(vendorCompany.getCompanyName().length() > 0);
	}
	
	/**
	 * 
	 * Test GetProductList
	 * 
	 * @since 2018. 11. 5.
	 * @author Incheol Jung
	 */
	@Test 
	public void testGetProductList() {
		GetProductListParameter parameters = new GetProductListParameter();
		parameters.setWholesalerid("2858");
		parameters.setVendorcategoryid("0");
		parameters.setProductname("t");
		
		GetProductListResponse result = vendorService.getProductList(parameters);
		if(!CollectionUtils.isEmpty(result.getProductList())) {
			assertNotNull(result.getProductList().get(0).getProductID());
		}
	}
	
	/**
	 * 
	 * Test GetProductColor
	 * 
	 * @since 2018. 11. 5.
	 * @author Incheol Jung
	 */
	@Test
	public void testGetProductColor() {
		List<ProductColor> result = vendorService.getProductColor(8487416);
		if(!CollectionUtils.isEmpty(result)) {
			assertNotNull(result.get(0).getColorID());
		}
	}

}
