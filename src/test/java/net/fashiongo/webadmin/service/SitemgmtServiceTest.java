/**
 * 
 */
package net.fashiongo.webadmin.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
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

import net.fashiongo.webadmin.model.fgem.EmConfiguration;
import net.fashiongo.webadmin.model.pojo.common.ResultCode;
import net.fashiongo.webadmin.model.pojo.common.ResultResponse;
import net.fashiongo.webadmin.model.pojo.parameter.GetCollectionCategoryListParameters;
import net.fashiongo.webadmin.model.pojo.parameter.SetCollectionCategoryParameters;
import net.fashiongo.webadmin.model.pojo.sitemgmt.CategoryListOrder;
import net.fashiongo.webadmin.model.pojo.sitemgmt.CodeData;
import net.fashiongo.webadmin.model.pojo.sitemgmt.ProductAttribute;
import net.fashiongo.webadmin.model.pojo.sitemgmt.TrendReportKmmImage;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.DeleteCommunicationReasonParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.GetCategoryListParameters;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.GetCategoryVendorListParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.GetDMRequestParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.GetDMRequestSendListParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.GetPolicyDetailParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.GetPolicyManagementDetailParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.GetProductAttributesParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.GetProductDetailParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.GetTodayDealCalendarListParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.GetTodayDealCanlendarParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.GetTodaydealParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.GetTrendReport2Parameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.GetTrendReportItemParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.PageSizeParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.SetCategoryListOrderParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.SetCollectionCategoryListorderParameters;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.SetCommunicationReasonActiveParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.SetCommunicationReasonParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.SetFGCatalogParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.SetNewTodayDealParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.SetPaidCampaignParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.SetProductAttributesMappingParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.SetProductAttributesParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.SetTodayDealCalendarParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.response.DeleteCommunicationReasonResponse;
import net.fashiongo.webadmin.model.pojo.sitemgmt.response.GetCategoryListResponse;
import net.fashiongo.webadmin.model.pojo.sitemgmt.response.GetCategoryVendorListResponse;
import net.fashiongo.webadmin.model.pojo.sitemgmt.response.GetCollectionCategoryListResponse;
import net.fashiongo.webadmin.model.pojo.sitemgmt.response.GetDMRequestResponse;
import net.fashiongo.webadmin.model.pojo.sitemgmt.response.GetFeaturedItemCountResponse;
import net.fashiongo.webadmin.model.pojo.sitemgmt.response.GetPaidCampaignResponse;
import net.fashiongo.webadmin.model.pojo.sitemgmt.response.GetPolicyDetailResponse;
import net.fashiongo.webadmin.model.pojo.sitemgmt.response.GetPolicyManagementDetailResponse;
import net.fashiongo.webadmin.model.pojo.sitemgmt.response.GetPolicyManagementResponse;
import net.fashiongo.webadmin.model.pojo.sitemgmt.response.GetProductAttributesResponse;
import net.fashiongo.webadmin.model.pojo.sitemgmt.response.GetProductAttributesTotalResponse;
import net.fashiongo.webadmin.model.pojo.sitemgmt.response.GetProductDetailResponse;
import net.fashiongo.webadmin.model.pojo.sitemgmt.response.GetTodayDealCalendarListResponse;
import net.fashiongo.webadmin.model.pojo.sitemgmt.response.GetTodayDealCalendarResponse;
import net.fashiongo.webadmin.model.pojo.sitemgmt.response.GetTodaydealResponse;
import net.fashiongo.webadmin.model.pojo.sitemgmt.response.GetTrendReport2Response;
import net.fashiongo.webadmin.model.pojo.sitemgmt.response.GetTrendReportCategoryResponse;
import net.fashiongo.webadmin.model.pojo.sitemgmt.response.GetTrendReportItemResponse;
import net.fashiongo.webadmin.model.pojo.sitemgmt.response.GetVendorCategoryResponse;
import net.fashiongo.webadmin.model.pojo.sitemgmt.response.GetVendorListResponse;
import net.fashiongo.webadmin.model.pojo.sitemgmt.response.SetCollectionCategoryListorderResponse;
import net.fashiongo.webadmin.model.primary.CollectionCategory;
import net.fashiongo.webadmin.model.primary.CommunicationReason;
import net.fashiongo.webadmin.model.primary.Policy;
import net.fashiongo.webadmin.model.primary.TrendReport;

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
	 * Test method for {@link net.fashiongo.webadmin.service.SitemgmtService#getCategoryList(net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.GetCategoryListParameters)}.
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
	 * Test method for {@link net.fashiongo.webadmin.service.SitemgmtService#setCollectionCategoryListorder(net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.SetCollectionCategoryListorderParameters)}.
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
	@Ignore
	@Test
	public void testSetPaidCampaign() {
		SetPaidCampaignParameter parameters = new SetPaidCampaignParameter();
		List<EmConfiguration> emConfigurationList = new ArrayList<EmConfiguration>();
		EmConfiguration emConfiguration = new EmConfiguration();

		emConfiguration.setConfigID(3);
		emConfiguration.setConfigType("Reservation Cancellation Fee");
		emConfiguration.setConfigValue("110");
		emConfigurationList.add(emConfiguration);

		emConfiguration.setConfigID(4);
		emConfiguration.setConfigType("Campaign Schedule Time");
		emConfiguration.setConfigValue("08:00:00");
		emConfigurationList.add(emConfiguration);

		emConfiguration.setConfigID(5);
		emConfiguration.setConfigType("Email Unit Price");
		emConfiguration.setConfigValue("0.2");
		emConfigurationList.add(emConfiguration);

		emConfiguration.setConfigID(6);
		emConfiguration.setConfigType("MinimumSendingFee");
		emConfiguration.setConfigValue("100.00");
		emConfigurationList.add(emConfiguration);

		emConfiguration.setConfigID(7);
		emConfiguration.setConfigType("Reservation Cancellation Fee Percent");
		emConfiguration.setConfigValue("50");
		emConfigurationList.add(emConfiguration);

		parameters.setObjList(emConfigurationList);
		ResultCode result = sitemgmtService.setPaidCampaign(parameters);
		assertTrue(result.getSuccess());
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
		parameters.setVendorStatus("0");
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
		PageSizeParameter param = new PageSizeParameter();
		param.setPageNum(1);
		param.setPageSize(10);
		GetPolicyManagementResponse result = sitemgmtService.getPolicyManagement(param);
		assertTrue(result.getVpolicyList().size() > 0);
		assertTrue(result.getRecCnt() == result.getVpolicyList().size());
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
		String type = "Upd";
		Policy objPolicy = new Policy();
		objPolicy.setPolicyID(6);
		objPolicy.setPolicyTitle("FG Pay Implementaion Schedule");
		objPolicy.setPolicyContents("Dear valued FashionGo vendors, <p><br>\\n\\n\\nPlease note that with the implementation of FG Pay on February 1st, 2018, any new orders placed ON OR AFTER February 1st, 2018 will NOT store any credit card information. Orders placed BEFORE February 1st, 2018 will have credit card information stored until February 28th, 2018. <p><br>\\n\\n\\nIf you have not yet received any information about the new payment gateway or if you have any questions about how FG Pay can improve your order processing, please be sure to contact the FashionGo team at marketing@fashiongo.net or at 213 745 2667. Our representatives would be more than happy to advise you!<p><br>\\n\\n\\nSincerely,<p><br>\\n\\n\\n-FashionGo Team-\\n\\n");
		objPolicy.setEffectiveOn("1/30/2018");
		objPolicy.setForVendor(true);
		objPolicy.setForRetailer(false);
		objPolicy.setActive(false);
		ResultCode result = new ResultCode();
		assertTrue(result.getSuccess());
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
		GetPolicyDetailParameter param = new GetPolicyDetailParameter();
		param.setPageNum(1);
		param.setPageSize(10);
		param.setSearchItem("CompanyName");
		param.setPolicyID(6);
		
		GetPolicyDetailResponse result = sitemgmtService.getPolicyDetail(param);
		assertTrue(result.getPolicyDetail().size() > 0);
		param.setSearchTxt("love");
		result = sitemgmtService.getPolicyDetail(param);
		assertTrue(result.getPolicyDetail().size() > 0);
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
		GetPolicyManagementDetailParameter param = new GetPolicyManagementDetailParameter();
		param.setPolicyID(6);
		GetPolicyManagementDetailResponse result = sitemgmtService.getPolicyManagementDetail(param);
		assertNotNull(result.getPolicy());
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
		List<CommunicationReason> result = sitemgmtService.getCommunicationReasonAll();
		assertTrue(result.size() > 0);
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
		DeleteCommunicationReasonParameter param = new DeleteCommunicationReasonParameter();
		param.setReasonIDs("45");
		DeleteCommunicationReasonResponse result = sitemgmtService.deleteCommunicationReason(param);
		assertNotNull(result.getResult().get(0));
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
		SetCommunicationReasonActiveParameter param = new SetCommunicationReasonActiveParameter();
		param.setActive(true);
		param.setReasonID(45);
		Integer result = sitemgmtService.setCommunicationReasonActive(param);
		assertSame(result, 1);
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
		SetCommunicationReasonParameter param = new SetCommunicationReasonParameter();
		param.setReasonID(0);
		param.setParentID("41");
		param.setReason("test1111");
		param.setActive(true);
		Integer result = sitemgmtService.setCommunicationReason(param);
		assertSame(result, 1);
	}
	
	/**
	 *
	 * Test GetCategoryVendorList
	 *
	 * @since 2018. 11. 6.
	 * @author Nayeon Kim
	 */
	@Test
	public void testGetCategoryVendorList() {
		GetCategoryVendorListParameter parameters = new GetCategoryVendorListParameter();
		parameters.setCategoryid("");
		parameters.setVendorname("entro");
		GetCategoryVendorListResponse result = sitemgmtService.getCategoryVendorList(parameters);
		assertTrue(result.getCategoryVendorList().size() > 0);
	}

	/**
	 *
	 * Test GetProductAttributesTotal
	 *
	 * @since 2018. 11. 6.
	 * @author Nayeon Kim
	 */
	@Test
	public void testGetProductAttributesTotal() {
		GetProductAttributesTotalResponse result = sitemgmtService.getProductAttributesTotal();
		assertTrue(result.getPatternInfolist().size() > 0);
		assertTrue(result.getLengthInfolist().size() > 0);
		assertTrue(result.getStyleInfolist().size() > 0);
		assertTrue(result.getFabricInfolist().size() > 0);
		assertTrue(result.getBodySizeInfolist().size() > 0);
		assertTrue(result.getColorListInfolist().size() > 0);
	}

	/**
	 *
	 * Test GetFeaturedItemCount
	 *
	 * @since 2018. 11. 6.
	 * @author Nayeon Kim
	 */
	@Test
	public void testGetFeaturedItemCount() {
		GetFeaturedItemCountResponse result = sitemgmtService.getFeaturedItemCount("2018-11");
		if (!CollectionUtils.isEmpty(result.getFeaturedItemList())) {
			assertNotNull(result.getFeaturedItemList().get(0).getFeaturedItemID());
		}
	}

	/**
	 *
	 * Test GetFeaturedItemListDay
	 *
	 * @since 2018. 11. 6.
	 * @author Nayeon Kim
	 */
	@Test
	public void testGetFeaturedItemListDay() {
		GetFeaturedItemCountResponse result = sitemgmtService.getFeaturedItemCount("2018-11");
		assertNotNull(result.getFeaturedItemList());
	}

	/**
	 *
	 * Test GetProductDetail
	 *
	 * @since 2018. 11. 6.
	 * @author Nayeon Kim
	 */
	@Test
	public void testGetProductDetail() {
		GetProductDetailParameter parameters = new GetProductDetailParameter();
		parameters.setProductID("9213809");
		parameters.setTrendReportID("0");
		GetProductDetailResponse result = sitemgmtService.getProductDetail(parameters);
		if (!CollectionUtils.isEmpty(result.getProductInfolist())) {
			assertNotNull(result.getProductInfolist().get(0).getProductID());
		}
	}

	/**
	 *
	 * Test GetTrendReport2
	 *
	 * @since 2018. 11. 6.
	 * @author Nayeon Kim
	 */
	@Test
	public void testGetTrendReport2() {
		GetTrendReport2Parameter parameters = new GetTrendReport2Parameter();
		parameters.setPagenum("1");
		parameters.setPagesize("10");
		parameters.setSearchtxt("");
		parameters.setFromdate("9/1/2018 00:00:00");
		parameters.setTodate("9/30/2018 23:59:59");
		parameters.setOrderby("DateFrom");
		parameters.setOrderbygubn("Desc");
		parameters.setActive("true");
		parameters.setCuratedType("1");
		GetTrendReport2Response result = sitemgmtService.getTrendReport2(parameters);
		assertTrue(result.getTrendReportList().size() > 0);
	}

	/**
	 *
	 * Test GetTrendReportItem
	 *
	 * @since 2018. 11. 6.
	 * @author Nayeon Kim
	 */
	@Test
	public void testGetTrendReportItem() {
		GetTrendReportItemParameter parameters = new GetTrendReportItemParameter();
		parameters.setPagenum("1");
		parameters.setPagesize("10");
		parameters.setTrendreportid("328");
		GetTrendReportItemResponse result = sitemgmtService.getTrendReportItem(parameters);
		assertTrue(result.getTrendReportItem().size() > 0);
	}

	/**
	 *
	 * Test SetTrendReportSort
	 *
	 * @since 2018. 11. 6.
	 * @author Nayeon Kim
	 */
	@Test
	public void testSetTrendReportSort() {
		ResultCode result = sitemgmtService.setTrendReportSort("<ROOT></ROOT>");
		assertNotNull(result);
	}

	/**
	 *
	 * Test GetLastKMMData
	 *
	 * @since 2018. 11. 6.
	 * @author Nayeon Kim
	 */
	@Test
	public void testGetLastKMMData() {
		TrendReportKmmImage result = new TrendReportKmmImage();
		TrendReport trendReport = new TrendReport();
		trendReport.setSquareImage("KMM_SquareImage.jpg");
		trendReport.setImage("KMM_WideImage.png");
		trendReport.setMiniImage("KMM_MiniImage.png");
		trendReport.setkMMImage1("KMM_homecoming.jpg");
		trendReport.setkMMImage2("Unique vintage.png");
		assertNotNull(result);
	}

	/**
	 *
	 * Test SetCategory
	 *
	 * @since 2018. 11. 6.
	 * @author Nayeon Kim
	 */
	@Test
	public void testSetCategory() {

	}

	/**
	 *
	 * Test SetCategoryListOrder
	 *
	 * @since 2018. 11. 6.
	 * @author Nayeon Kim
	 */
    @Ignore
	@Test
	public void testSetCategoryListOrder() {
		SetCategoryListOrderParameter parameters = new SetCategoryListOrderParameter();
		parameters.setCategoryid(386);
		parameters.setParentcategoryid(99);
		parameters.setListorder(101);
		parameters.setLvl(3);
		List<CategoryListOrder> result = sitemgmtService.setCategoryListOrder(parameters);
		assertNotNull(result);
	}
}
