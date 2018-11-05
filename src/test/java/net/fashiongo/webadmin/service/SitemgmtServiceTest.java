/**
 * 
 */
package net.fashiongo.webadmin.service;

import static org.junit.Assert.*;

import java.text.ParseException;

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

import net.fashiongo.webadmin.model.pojo.InactiveTodayDealDetail;
import net.fashiongo.webadmin.model.pojo.ResultResponse;
import net.fashiongo.webadmin.model.pojo.parameter.*;
import net.fashiongo.webadmin.model.pojo.response.*;
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
	
	@Test
	public void testGetTodayDealCalendarList() {
		GetTodayDealCalendarListParameter parameters = new GetTodayDealCalendarListParameter();
		parameters.setWholesalerid(0);
		parameters.setSelectdate("11/5/2018");
		
		GetTodayDealCalendarListResponse result = sitemgmtService.getTodayDealCalendarList(parameters);
		if(!CollectionUtils.isEmpty(result.getInactiveTodayDeals())) {
			for(InactiveTodayDealDetail detail : result.getInactiveTodayDeals()) {
				assertNotNull(detail.getTodayDealID());
			}
		}
	}
	
	
}
