/**
 * 
 */
package net.fashiongo.webadmin.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import net.fashiongo.webadmin.model.pojo.common.ResultCode;
import net.fashiongo.webadmin.model.pojo.parameter.DelVendorBlockParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetBannerRequestParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetVendorBlockListParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetVendorFormsListParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetDenyBannerParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetVendorFormsParameter;
import net.fashiongo.webadmin.model.pojo.response.GetBannerRequestResponse;
import net.fashiongo.webadmin.model.pojo.response.GetVendorFormsListResponse;
import net.fashiongo.webadmin.model.pojo.vendor.ProductColor;
import net.fashiongo.webadmin.model.pojo.vendor.parameter.DelVendorFormParameter;
import net.fashiongo.webadmin.model.pojo.vendor.parameter.GetProductListParameter;
import net.fashiongo.webadmin.model.pojo.vendor.response.GetProductListResponse;
import net.fashiongo.webadmin.model.primary.EntityActionLog;
import net.fashiongo.webadmin.model.primary.ListVendorImageType;
import net.fashiongo.webadmin.model.primary.VendorCompany;
import net.fashiongo.webadmin.model.primary.VwVendorBlocked;

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
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 11. 26.
	 * @author Reo
	 * @throws ParseException 
	 */
	@Test
	public void testGetVendorBlockList() throws ParseException {
		GetVendorBlockListParameter parameters = new GetVendorBlockListParameter();
		parameters.setSearchType("");
		parameters.setSearchKeyword("");
		List<VwVendorBlocked> result = vendorService.getVendorBlockList(parameters);
		if(!CollectionUtils.isEmpty(result)) {
			assertNotNull(result.get(0));
		}
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 11. 26.
	 * @author Reo
	 */
	@Test
	public void testGetVendorBlockHistoryList() {
		Integer wholeSalerID = 2858;
		List<EntityActionLog> result = vendorService.getVendorBlockHistoryList(wholeSalerID);
		if(!CollectionUtils.isEmpty(result)) {
			assertNotNull(result.get(0));
		}
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 11. 26.
	 * @author Reo
	 */
	@Ignore
	@Test
	public void testDelVendorBlock() {
		DelVendorBlockParameter parameters = new DelVendorBlockParameter();
		ResultCode result = vendorService.delVendorBlock(parameters);
		assertTrue(result.getResultCode() > 0);
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 11. 26.
	 * @author Reo
	 */
	@Test
	public void testGetVendorImageType() {
		List<ListVendorImageType> result = vendorService.getVendorImageType();
		if(!CollectionUtils.isEmpty(result)) {
			assertNotNull(result.get(0));
		}
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 11. 26.
	 * @author Reo
	 */
	@Test
	public void testGetBannerRequest() {
		GetBannerRequestParameter parameters = new GetBannerRequestParameter();
		parameters.setPageNum(1);
		parameters.setPageSize(15);
		parameters.setFromDate(null);
		parameters.setToDate(null);
		parameters.setSearchStatus("Pending");
		parameters.setSearchType(null);
		parameters.setOrderby(null);
		GetBannerRequestResponse result = vendorService.getBannerRequest(parameters);
		if(!CollectionUtils.isEmpty(result.getBannerImageList())) {
			assertNotNull(result.getBannerImageList());
			assertNotNull(result.getTotal());
		}
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 11. 26.
	 * @author Reo
	 */
	@Ignore
	@Test
	public void testSetDenyBanner() {
		SetDenyBannerParameter parameters = new SetDenyBannerParameter();
		parameters.setImageRequestId(25565);
		parameters.setDenialReason("test");
		ResultCode result = vendorService.setDenyBanner(parameters);
		assertTrue(result.getResultCode() > 0);
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 11. 26.
	 * @author Reo
	 */
	@Ignore
	@Test
	public void testSetApproveBanner() {
		SetDenyBannerParameter parameters = new SetDenyBannerParameter();
		parameters.setImageRequestId(25565);
		ResultCode result = vendorService.setApproveBanner(parameters);
		assertTrue(result.getResultCode() > 0);
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 11. 26.
	 * @author Reo
	 */
	@Ignore
	@Test
	public void testSetRestoreBanner() {
		SetDenyBannerParameter parameters = new SetDenyBannerParameter();
		parameters.setImageRequestId(25565);
		ResultCode result = vendorService.setRestoreBanner(parameters);
		assertTrue(result.getResultCode() > 0);
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 11. 26.
	 * @author Reo
	 */
	@Ignore
	@Test
	public void testDelBannerRequest() {
		SetDenyBannerParameter parameters = new SetDenyBannerParameter();
		ResultCode result = vendorService.delBannerRequest(parameters);
		assertTrue(result.getResultCode() > 0);
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 11. 26.
	 * @author Reo
	 */
	@Test
	public void testGetVendorFormsList() {
		GetVendorFormsListParameter parameters = new GetVendorFormsListParameter();
		GetVendorFormsListResponse result = vendorService.getVendorFormsList(parameters);
		if(!CollectionUtils.isEmpty(result.getFashiongoFormList())) {
			assertNotNull(result.getFashiongoFormList());
		}
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 11. 26.
	 * @author Reo
	 */
	@Ignore
	@Test
	public void testSetVendorForms() {
		SetVendorFormsParameter parameters = new SetVendorFormsParameter();
		parameters.setType("Add");
		parameters.setFormName("testTitle");
		parameters.setMemo("testMemo");
		parameters.setAttachment("testFile.jpg");
		ResultCode result = vendorService.SetVendorForms(parameters);
		assertTrue(result.getResultCode() > 0);
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 11. 26.
	 * @author Reo
	 */
	@Ignore
	@Test
	public void testDelVendorForm() {
		DelVendorFormParameter parameters = new DelVendorFormParameter();
		ResultCode result = vendorService.delVendorForm(parameters);
		assertTrue(result.getResultCode() > 0);
	}

}
