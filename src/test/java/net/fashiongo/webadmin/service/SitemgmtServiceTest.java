/**
 * 
 */
package net.fashiongo.webadmin.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import net.fashiongo.webadmin.model.pojo.CodeData;
import net.fashiongo.webadmin.model.pojo.ProductAttribute;
import net.fashiongo.webadmin.model.pojo.ResultCode;
import net.fashiongo.webadmin.model.pojo.ResultResponse;
import net.fashiongo.webadmin.model.pojo.parameter.GetCategoryListParameters;
import net.fashiongo.webadmin.model.pojo.parameter.GetCollectionCategoryListParameters;
import net.fashiongo.webadmin.model.pojo.parameter.GetDMRequestParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetDMRequestSendListParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetProductAttributesParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetTodayDealCalendarListParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetTodayDealCanlendarParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetTodaydealParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetCollectionCategoryListorderParameters;
import net.fashiongo.webadmin.model.pojo.parameter.SetCollectionCategoryParameters;
import net.fashiongo.webadmin.model.pojo.parameter.SetFGCatalogParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetNewTodayDealParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetProductAttributesMappingParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetProductAttributesParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetTodayDealCalendarParameter;
import net.fashiongo.webadmin.model.pojo.response.GetCategoryListResponse;
import net.fashiongo.webadmin.model.pojo.response.GetCollectionCategoryListResponse;
import net.fashiongo.webadmin.model.pojo.response.GetDMRequestResponse;
import net.fashiongo.webadmin.model.pojo.response.GetPaidCampaignResponse;
import net.fashiongo.webadmin.model.pojo.response.GetProductAttributesResponse;
import net.fashiongo.webadmin.model.pojo.response.GetTodayDealCalendarListResponse;
import net.fashiongo.webadmin.model.pojo.response.GetTodayDealCalendarResponse;
import net.fashiongo.webadmin.model.pojo.response.GetTodaydealResponse;
import net.fashiongo.webadmin.model.pojo.response.GetTrendReportCategoryResponse;
import net.fashiongo.webadmin.model.pojo.response.GetVendorCategoryResponse;
import net.fashiongo.webadmin.model.pojo.response.GetVendorListResponse;
import net.fashiongo.webadmin.model.pojo.response.SetCollectionCategoryListorderResponse;
import net.fashiongo.webadmin.model.primary.CollectionCategory;

/**
 * @author sanghyup
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SitemgmtServiceTest {
	

	@Autowired
	SitemgmtService sitemgmtService;
	
	@Autowired
	SitemgmtCollectionCategoryService collectionCategorService;
	
	/**
	 * @title Description Example
	 * @since 2018. 10. 1.
	 * @author sanghyup
	 * @return void
	 * @tags @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("SitemgmtServiceTest.setUpBeforeClass");
	}

	/**
	 * @title Description Example
	 * @since 2018. 10. 1.
	 * @author sanghyup
	 * @return void
	 * @tags @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("SitemgmtServiceTest.tearDownAfterClass");
	}

	/**
	 * @title Description Example
	 * @since 2018. 10. 1.
	 * @author sanghyup
	 * @return void
	 * @tags @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		System.out.println("SitemgmtServiceTest.setUp");
	}

	/**
	 * @title Description Example
	 * @since 2018. 10. 1.
	 * @author sanghyup
	 * @return void
	 * @tags @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		System.out.println("SitemgmtServiceTest.tearDown");
	}

	/**
	 * Test method for {@link net.fashiongo.webadmin.service.SitemgmtService#getCollectionCategoryList(net.fashiongo.webadmin.model.pojo.parameter.GetCollectionCategoryListParameters)}.
	 */
	@Test
	public void testGetCollectionCategoryList() {
//		fail("Not yet implemented");
		System.out.println("SitemgmtServiceTest.testGetCollectionCategoryList");

		// check collection category list
		GetCollectionCategoryListParameters parameters = new GetCollectionCategoryListParameters();
		parameters.setCategoryId(0);
		parameters.setExpandAll(1);
		GetCollectionCategoryListResponse result = collectionCategorService.getCollectionCategoryList(parameters);

		assertNotNull(result.getCollectionCategoryList());

		// check collection category detail
		parameters = new GetCollectionCategoryListParameters();
		parameters.setCategoryId(8);
		result = collectionCategorService.getCollectionCategoryList(parameters);

		assertNotNull(result.getAdPageSpotList());
}

	/**
	 * Test method for {@link net.fashiongo.webadmin.service.SitemgmtService#getCategoryList(net.fashiongo.webadmin.model.pojo.parameter.GetCategoryListParameters)}.
	 */
	@Test
//	@Ignore
	public void testGetCategoryList() {
//		fail("Not yet implemented");
		System.out.println("SitemgmtServiceTest.testGetCategoryList");

		// check category list
		GetCategoryListParameters parameters = new GetCategoryListParameters();
		parameters.setCategoryId(0);
		parameters.setExpandAll(0);
		GetCategoryListResponse result = sitemgmtService.getCategoryList(parameters);

		assertNotNull(result.getCategoryLst());
	}

	/**
	 * Test method for {@link net.fashiongo.webadmin.service.SitemgmtService#setCollectionCategoryListorder(net.fashiongo.webadmin.model.pojo.parameter.SetCollectionCategoryListorderParameters)}.
	 */
	@Test
	public void testSetCollectionCategoryListorder() {
//		fail("Not yet implemented");
		System.out.println("SitemgmtServiceTest.testSetCollectionCategoryListorder");

		// check category list
		SetCollectionCategoryListorderParameters parameters = new SetCollectionCategoryListorderParameters();
		parameters.setCategoryId(9);
		parameters.setListOrder(1);;
		parameters.setLvl(2);
		parameters.setParentCategoryId(1);
		SetCollectionCategoryListorderResponse result = collectionCategorService.setCollectionCategoryListorder(parameters);

		assertNotNull(result.getCategoryCollectionlist());
	}

	@Test
	public void testSetCollectionCategoryActive() {

		SetCollectionCategoryParameters parameters = new SetCollectionCategoryParameters();
		// 1st test -active
		parameters.setSetType("Act");
		
		CollectionCategory collectionCategory = new CollectionCategory();
		collectionCategory.setCollectionCategoryID(8);
		collectionCategory.setActive(true);
		parameters.setCollectionCategory(collectionCategory);

		ResultResponse<Integer> result = collectionCategorService.setCollectionCategoryActive(parameters);
		assertTrue(result.getCode() > 0);

		// 2nd test -inactive
		collectionCategory.setActive(false);
		result = collectionCategorService.setCollectionCategoryActive(parameters);
		assertTrue(result.getCode() > 0);
	}

	@Test
	@Ignore("Not yet implemented")
	public void testSetCollectionCategoryDelete() {

		SetCollectionCategoryParameters parameters = new SetCollectionCategoryParameters();
		parameters.setSetType("Del");
		
		CollectionCategory collectionCategory = new CollectionCategory();
		collectionCategory.setCollectionCategoryID(9999);
		parameters.setCollectionCategory(collectionCategory);

//		ResultResponse<Object> result = sitemgmtService.setCollectionCategoryDelete(parameters);
//		assertNotNull(result.getData());
		fail("Not yet implemented");

	}

	@Test
	@Ignore("Not yet implemented")
	public void testSetCollectionCategory() {

		SetCollectionCategoryParameters parameters = new SetCollectionCategoryParameters();
		// 1st test -insert
		parameters.setSetType("Add");
		
		CollectionCategory collectionCategory = new CollectionCategory();
		collectionCategory.setCollectionCategoryID(0);
		parameters.setCollectionCategory(collectionCategory);

//		ResultResponse<Object> result = sitemgmtService.setCollectionCategoryDelete(parameters);
//		assertNotNull(result.getData());
		fail("Not yet implemented");

		// 2nd test -update
		parameters.setSetType("Upd");
		collectionCategory.setCollectionCategoryID(8);
		fail("Not yet implemented");

	}
	
	/**
	 * 
	 * testGetPaidCampaign
	 * 
	 * @since 2018. 10. 22.
	 * @author Nayeon Kim
	 */
	@Test
	public void testGetPaidCampaign() {
		GetPaidCampaignResponse result = sitemgmtService.getPaidCampaign();
		assertNotNull(result);	
	}
	
	/**
	 * 
	 * testSetPaidCampaign
	 * 
	 * @since 2018. 10. 22.
	 * @author Nayeon Kim
	 */
	@Test
	public void testSetPaidCampaign() {
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 11. 5.
	 * @author Reo
	 */
	@Test
	public void testGetProductAttributes() {
		GetProductAttributesParameter parameters = new GetProductAttributesParameter();
		parameters.setTabNo(5);
		parameters.setPrevTab(1);
		parameters.setCategoryID(8);
		GetProductAttributesResponse result = sitemgmtService.getProductAttributes(parameters);
		
		if(result != null) {
			List<CodeData> codeDataList = result.getCodeDataList();
			if(!CollectionUtils.isEmpty(codeDataList)) {
				assertNotNull(result.getCodeDataList());
			}
		}
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 11. 5.
	 * @author Reo
	 */
	@Ignore
	@Test
	public void testSetProductAttributes() {
		SetProductAttributesParameter parameters = new SetProductAttributesParameter();
		parameters.setTabNo(1);
		parameters.setbType("save");
		parameters.setCodeID(1);
		parameters.setAttrName("Beadedtest");
		parameters.setActive(true);
		
		ResultCode result = sitemgmtService.setProductAttributes(parameters);
		if (result != null) {
			assertTrue(result.getSuccess());
		}
	}
	
	/**
	 * Test GetVendorList
	 * 
	 * @since 2018. 11. 5.
	 * @author Incheol Jung
	 */
	@Test
	public void testGetVendorList() {
		GetVendorListResponse result = sitemgmtService.getVendorList();
		assertTrue(result.getVendorSummarylist().size() > 0);
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 11. 5.
	 * @author Reo
	 */
	@Ignore
	@Test
	public void testSetProductAttributesActive() {
		SetProductAttributesParameter parameters = new SetProductAttributesParameter();
		parameters.setTabNo(1);
		parameters.setCodeID(1);
		parameters.setActive(false);
		
		ResultCode result = sitemgmtService.setProductAttributesActive(parameters);
		if(result != null) {
			assertTrue(result.getSuccess());
		}
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 11. 5.
	 * @author Reo
	 */
	@Ignore
	@Test
	public void testSetProductAttributesMapping() {
		SetProductAttributesMappingParameter parameters = new SetProductAttributesMappingParameter();
		parameters.setTabNo(1);
		parameters.setCategoryID(8);
		
		List<ProductAttribute> productAttributeList = new ArrayList<ProductAttribute>();
		//first row
		ProductAttribute productAttribute = new ProductAttribute();
		productAttribute.setCodeID(36);
		productAttribute.setMapID(0);
		productAttribute.setCategoryID(8);
		productAttributeList.add(productAttribute);
		
		//second row
		productAttribute = new ProductAttribute();
		productAttribute.setCodeID(38);
		productAttribute.setMapID(0);
		productAttribute.setCategoryID(8);
		productAttributeList.add(productAttribute);
		
		parameters.setProductAttributeList(productAttributeList);
		ResultCode result = sitemgmtService.setProductAttributesMapping(parameters);
		if(result != null) {
			assertTrue(result.getSuccess());
		}
	}
	/**
	 * Test GetTodaydeal
	 * 
	 * @since 2018. 11. 5.
	 * @author Incheol Jung
	 * @throws ParseException
	 */
	@Test
	public void testGetTodaydeal() throws ParseException {
		GetTodaydealParameter parameters = new GetTodaydealParameter();
		parameters.setPagenum("1");
		parameters.setPagesize("30");
		parameters.setCompanytypeid1("true");
		parameters.setCompanytypeid2("true");
		parameters.setCompanytypeid3("true");
		parameters.setCategoryid("");
		parameters.setFromdate("11/1/2018, 12:00:00 AM");
		parameters.setTodate("11/30/2018, 11:59:59 PM");
		parameters.setActive("true");
		parameters.setOrderby("");
		
		GetTodaydealResponse result = sitemgmtService.getTodaydeal(parameters);
		if(result.getTotal()!= null) {
			if(result.getTotal().getRecCnt() > 0) {
				assertTrue(result.getTodayDealDetail().size() > 0);
			}
		}
	}
	
	/**
	 * 
	 * Test GetTodayDealCalendarList
	 * 
	 * @since 2018. 11. 5.
	 * @author Incheol Jung
	 */
	@Test
	public void testGetTodayDealCalendarList() {
		GetTodayDealCalendarListParameter parameters = new GetTodayDealCalendarListParameter();
		parameters.setWholesalerid(0);
		parameters.setSelectdate("11/5/2018");
		
		GetTodayDealCalendarListResponse result = sitemgmtService.getTodayDealCalendarList(parameters);
		if(!CollectionUtils.isEmpty(result.getInactiveTodayDeals())) {
			assertNotNull(result.getInactiveTodayDeals().get(0).getTodayDealID());
		}
	}
	
	
	/**
	 * 
	 * Test GetTrendReportCategory
	 * 
	 * @since 2018. 11. 5.
	 * @author Incheol Jung
	 */
	@Test
	public void testGetTrendReportCategory() {
		GetTrendReportCategoryResponse result = sitemgmtService.getTrendReportCategory();
		assertTrue(result.getCategoryList().size() > 0);
	}
	
	/**
	 * 
	 * Test GetTodayDealCalendar
	 * 
	 * @since 2018. 11. 5.
	 * @author Incheol Jung
	 */
	@Test
	public void testGetTodayDealCalendar() {
		GetTodayDealCanlendarParameter parameters = new GetTodayDealCanlendarParameter();
		parameters.setFromdate("2018-11-1");
		parameters.setTodate("2018-11-30");
		
		GetTodayDealCalendarResponse result = sitemgmtService.getTodayDealCalendar(parameters);
		if(!CollectionUtils.isEmpty(result.getCalendarDetails())) {
			assertNotNull(result.getCalendarDetails().get(0).getTodayDealID());
		}
	}
	
	/**
	 * 
	 * Test GetVendorCategory
	 * 
	 * @since 2018. 11. 5.
	 * @author Incheol Jung
	 */
	@Test 
	public void testGetVendorCategory() {
		GetVendorCategoryResponse result = sitemgmtService.getVendorCategory(2858);
		if(!CollectionUtils.isEmpty(result.getVendorCategorySummaryList())) {
			assertNotNull(result.getVendorCategorySummaryList().get(0).getVendorCategoryID());
		}
	}
	/**
	 * 
	 * Test SetTodayDealCalendar
	 * 
	 * @since 2018. 11. 5.
	 * @author Incheol Jung
	 */
	@Ignore
	@Test
	public void testSetTodayDealCalendar() {
		SetTodayDealCalendarParameter parameters = new SetTodayDealCalendarParameter();
		parameters.setTodayDealID(17612);
		parameters.setFromDate("11/7/2018");
		parameters.setActive(false);
		parameters.setNotes("Test delete Notes");
		
		ResultCode _result = sitemgmtService.setTodayDealCalendar(parameters);
		assertNotNull(_result);
	}
	
	/**
	 * 
	 * Test SetNewTodayDeal
	 * 
	 * @since 2018. 11. 5.
	 * @author Incheol Jung
	 */
	@Ignore
	@Test
	public void testSetNewTodayDeal() {
		SetNewTodayDealParameter parameters = new SetNewTodayDealParameter();
		parameters.setProductID(8487416);
		parameters.setFromDate("2018-11-07 00:00:00");
		parameters.setToDate("2018-11-07 00:00:00");
		parameters.setTodayDealPrice(BigDecimal.valueOf(25));
		
		Integer _result = sitemgmtService.setNewTodayDeal(parameters);
		assertNotNull(_result);
	}
	
	/**
	 * 
	 * Test GetDMRequest
	 * 
	 * @since 2018. 11. 5.
	 * @author Incheol Jung
	 */
	@Test
	public void testGetDMRequest() {
		GetDMRequestParameter parameters = new GetDMRequestParameter();
		parameters.setPagenum("1");
		parameters.setPagesize("20");
		parameters.setCompanytypecd("2,1,3");
		parameters.setStatus("Requested");

		GetDMRequestResponse result = sitemgmtService.getDMRequest(parameters);
		if(!CollectionUtils.isEmpty(result.getDmList())) {
			assertNotNull(result.getDmList().get(0).getCatalogID());
		}
	}
	
	/**
	 * 
	 * Test GetDMRequestSendList
	 * 
	 * @since 2018. 11. 5.
	 * @author Incheol Jung
	 */
	@Test
	public void testGetDMRequestSendList() {
		GetDMRequestSendListParameter parameters = new GetDMRequestSendListParameter();
		parameters.setDmIds(Arrays.asList(92181,92178));
		
		JSONObject result = sitemgmtService.getDMRequestSendList(parameters);
		if(!CollectionUtils.isEmpty(result)) {
			assertTrue(result.get(92181) != null || result.get(92178) != null);
		}
	}
	
	/**
	 * 
	 * Test SetFGCatalog
	 * 
	 * @since 2018. 11. 5.
	 * @author Incheol Jung
	 */
	@Ignore
	@Test
	public void testSetFGCatalog() {
		SetFGCatalogParameter parameters = new SetFGCatalogParameter();
		parameters.setSubject("Test Title");
		parameters.setContents("<html></html>");
		parameters.setIncludedvendors("SHUSHOP,STORIA");
		parameters.setVendorcode("10526,10525");
		
		ResultCode result = sitemgmtService.setFGCatalog(parameters);
		assertNotNull(result);
	}
	
	/**
     * 
     * testGetPolicyManagement
     * 
     * @since 2018. 11. 6.
     * @author Dahye
     */
	@Test
	public void testGetPolicyManagement() {
		
	}

	/**
     * 
     * testSetAddDelPolicyManagement
     * 
     * @since 2018. 11. 6.
     * @author Dahye
     */
	@Test
	@Ignore
	public void testSetAddDelPolicyManagement() {
		
	}

	/**
     * 
     * testGetPolicyDetail
     * 
     * @since 2018. 11. 6.
     * @author Dahye
     */
	@Test
	public void testGetPolicyDetail() {
		
	}

	/**
     * 
     * testGetPolicyManagementDetail
     * 
     * @since 2018. 11. 6.
     * @author Dahye
     */
	@Test
	public void testGetPolicyManagementDetail() {
		
	}

	/**
     * 
     * testGetCommunicationReasonAll
     * 
     * @since 2018. 11. 6.
     * @author Dahye
     */
	@Test
	public void testGetCommunicationReasonAll() {
		
	}

	/**
     * 
     * testDeleteCommunicationReason
     * 
     * @since 2018. 11. 6.
     * @author Dahye
     */
	@Test
	@Ignore
	public void testDeleteCommunicationReason() {
		
	}

	/**
     * 
     * testSetCommunicationReasonActive
     * 
     * @since 2018. 11. 6.
     * @author Dahye
     */
	@Test
	@Ignore
	public void testSetCommunicationReasonActive() {
		
	}

	/**
     * 
     * testSetCommunicationReason
     * 
     * @since 2018. 11. 6.
     * @author Dahye
     */
	@Test
	@Ignore
	public void testSetCommunicationReason() {
		
	}
}
