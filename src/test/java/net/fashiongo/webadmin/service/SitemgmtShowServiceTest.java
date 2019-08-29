/**
 * 
 */
package net.fashiongo.webadmin.service;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import net.fashiongo.webadmin.data.model.sitemgmt.show.AdminShowListResponse;
import net.fashiongo.webadmin.data.model.sitemgmt.show.AdminShowScheduleListResponse;
import net.fashiongo.webadmin.service.renewal.RenewalSiteManagementShowService;
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

import net.fashiongo.webadmin.model.pojo.common.ResultResponse;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.DelShowParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.GetShowListParameters;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.GetShowParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.GetShowScheduleListParameters;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.SetShowInfoParameters;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.SetShowParameters;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.SetShowParticipatingVendorParameters;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.SetShowPromotionPlanParameters;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.SetShowScheduleParameters;
import net.fashiongo.webadmin.model.pojo.sitemgmt.response.GetShowCategoriesResponse;
import net.fashiongo.webadmin.model.pojo.sitemgmt.response.GetShowListResponse;
import net.fashiongo.webadmin.model.pojo.sitemgmt.response.GetShowParticipatingVendorsResponse;
import net.fashiongo.webadmin.model.pojo.sitemgmt.response.GetShowPromotionPlanResponse;
import net.fashiongo.webadmin.model.pojo.sitemgmt.response.GetShowScheduleListResponse;
import net.fashiongo.webadmin.model.primary.show.ListShow;
import net.fashiongo.webadmin.model.primary.show.MapShowSchedulePromotionPlanVendor;
import net.fashiongo.webadmin.model.primary.show.ShowSchedule;
import net.fashiongo.webadmin.model.primary.show.ShowSchedulePromotionPlan;

/**
 * @author Sanghyup
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SitemgmtShowServiceTest {

	@Autowired
	SitemgmtShowService sitemgmtShowService;

	@Autowired
	private RenewalSiteManagementShowService renewalSiteManagementShowService;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link net.fashiongo.webadmin.service.renewal.RenewalSiteManagementShowService#getShowList(net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.GetShowListParameters)}.
	 */
	@Test
	public final void testGetShowList() {
		GetShowListParameters p= testGetShowListParameters();
		AdminShowListResponse response = renewalSiteManagementShowService.getShowList(p);
		
		assertFalse(CollectionUtils.isEmpty(response.getShowList()));
	}

	private GetShowListParameters testGetShowListParameters() {
		GetShowListParameters p = new GetShowListParameters();
		p.setPageNum(1);
		p.setPageSize(10);
		p.setActive(1);
		p.setLocation("");
		p.setOrderBy("");
		p.setShowName("");
		p.setFromDate(null);
		p.setToDate(null);

		return p;
	}
	/**
	 * Test method for {@link net.fashiongo.webadmin.service.SitemgmtShowService#setShowInfo(net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.SetShowInfoParameters)}.
	 */
	@Test
	@Ignore("Not yet implemented")
	public final void testSetShowInfo() {
//		fail("Not yet implemented"); // TODO
		SetShowInfoParameters p = new SetShowInfoParameters();
		p.setCrud("U");
		p.setSType("show");
		p.setShowd(1);
		p.setShowName("showName");
		p.setLocation("location");
		p.setShowUrl("ShowUrl");
		p.setActive(true);
		
		p.setShowScheduleId(1);
		p.setListOrder(1);
		p.setDateFrom(LocalDateTime.now());
		p.setDateTo(LocalDateTime.now());
		
		ResultResponse<Integer> r = sitemgmtShowService.setShowInfo(p);
		int data = r.getData();

		assertTrue(data > 0);
	}

	/**
	 * Test method for {@link net.fashiongo.webadmin.service.SitemgmtShowService#getShowDetail(java.lang.Integer)}.
	 */
	@Test
	public final void testGetShowDetail() {
//		fail("Not yet implemented"); // TODO

		GetShowListParameters param = testGetShowListParameters();
		AdminShowListResponse response = renewalSiteManagementShowService.getShowList(param);
		Integer showId = response.getShowList().get(0).getShowId();
		
		ResultResponse<ListShow> r = sitemgmtShowService.getShowDetail(showId);
		
		assertNotNull(r.getData());
	}

	/**
	 * Test method for {@link net.fashiongo.webadmin.service.renewal.RenewalSiteManagementShowService#getShowScheduleList(net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.GetShowScheduleListParameters)}.
	 */
	@Test
	public final void testGetShowScheduleList() {
//		fail("Not yet implemented"); // TODO
		
		GetShowScheduleListParameters p = testGetShowScheduleListParameters();
		
		AdminShowScheduleListResponse r = renewalSiteManagementShowService.getShowScheduleList(p);
		
		assertFalse(CollectionUtils.isEmpty(r.getScheduleResponses()));
		
	}

	
	private GetShowScheduleListParameters testGetShowScheduleListParameters() {

		GetShowScheduleListParameters p = new GetShowScheduleListParameters();
		p.setPageNum(1);
		p.setPageSize(10);
		p.setShowId(1);
		p.setActive(1);
		
		p.setShowName("");
		p.setLocation("");
		p.setOrderBy("");
		p.setDateFrom("");
		p.setDateTo("");
		
		return p;
	}
	
	/**
	 * Test method for {@link net.fashiongo.webadmin.service.SitemgmtShowService#setShow(net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.SetShowParameters)}.
	 */
	@Test
	@Ignore("Not yet implemented")
	public final void testSetShow() {
//		fail("Not yet implemented"); // TODO
		SetShowParameters p = new SetShowParameters();
		p.setShowId(1);
		p.setActive(true);
		p.setLocation("location");
		p.setShowName("showName");
		p.setUrl("url");
		
		ResultResponse<Integer> r = sitemgmtShowService.setShow(p);

		assertTrue(r.getData() > 0);
	}

	/**
	 * Test method for {@link net.fashiongo.webadmin.service.SitemgmtShowService#setDeleteShow(net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.DelShowParameter)}.
	 */
	@Test
	@Ignore("Not yet implemented")
	public final void testSetDeleteShow() {
//		fail("Not yet implemented"); // TODO
		DelShowParameter p = new DelShowParameter();
		p.setShowID(1);
		
		ResultResponse<Integer> r = sitemgmtShowService.setDeleteShow(p);
		
		assertTrue(r.getData() > 0);

	}

	/**
	 * Test method for {@link net.fashiongo.webadmin.service.SitemgmtShowService#setDeleteShowSchedule(net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.DelShowParameter)}.
	 */
	@Test
	@Ignore("Not yet implemented")
	public final void testSetDeleteShowSchedule() {
//		fail("Not yet implemented"); // TODO
		DelShowParameter p = new DelShowParameter();
		p.setShowScheduleID(1);
		
		ResultResponse<Integer> r = sitemgmtShowService.setDeleteShowSchedule(p);
		
		assertTrue(r.getData() > 0);
	}

	/**
	 * Test method for {@link net.fashiongo.webadmin.service.SitemgmtShowService#setShowSchedule(net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.SetShowScheduleParameters)}.
	 * @throws Exception 
	 */
	@Test
	@Ignore("Not yet implemented")
	public final void testSetShowSchedule() throws Exception {
//		fail("Not yet implemented"); // TODO
		SetShowScheduleParameters p = new SetShowScheduleParameters();
		
		p.setShowScheduleId(1);
		p.setShowId(1);
		p.setBannerImage("bannerImage");
		p.setMobileImage("mobileImage");
		p.setTitleImage("titleImage");
		p.setDateFrom("10/01/2018");
		p.setDateTo("10/31/2018");
		p.setListOrder(0);
		p.setActive(true);

		ResultResponse<Integer> r = sitemgmtShowService.setShowSchedule(p);
		assertTrue(r.getData() > 0);

	}

	/**
	 * Test method for {@link net.fashiongo.webadmin.service.SitemgmtShowService#getShowScheduleDetail(java.lang.Integer)}.
	 */
	@Test
	public final void testGetShowScheduleDetail() {
//		fail("Not yet implemented"); // TODO
//		GetShowScheduleListParameters param = testGetShowScheduleListParameters();
//		GetShowScheduleListResponse res = sitemgmtShowService.getShowScheduleList(param);
//		Integer showScheduleId = res.getShowScheduleList().get(0).getShowScheduleID();
		Integer showScheduleId = 1;
		ResultResponse<ShowSchedule> r = sitemgmtShowService.getShowScheduleDetail(showScheduleId);
		
//		assertNotNull(r.getData());
		assertNotNull(r);
	}

	/**
	 * Test method for {@link net.fashiongo.webadmin.service.SitemgmtShowService#getShowCategories(net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.GetShowParameter)}.
	 */
	@Test
	public final void testGetShowCategories() {
//		fail("Not yet implemented"); // TODO
		GetShowParameter p = new GetShowParameter();
		p.setShowID(1);

		GetShowCategoriesResponse r = sitemgmtShowService.getShowCategories(p);
		
		assertFalse(CollectionUtils.isEmpty(r.getShowCategoryList()));
	}

	/**
	 * Test method for {@link net.fashiongo.webadmin.service.SitemgmtShowService#getShowPromotionPlans(net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.GetShowParameter)}.
	 */
	@Test
	public final void testGetShowPromotionPlans() {
//		fail("Not yet implemented"); // TODO
//		GetShowScheduleListParameters param = testGetShowScheduleListParameters();
//		GetShowScheduleListResponse res = sitemgmtShowService.getShowScheduleList(param);
//		Integer showScheduleId = res.getShowScheduleList().get(0).getShowScheduleID();

		Integer showScheduleId = 1;
		GetShowParameter p = new GetShowParameter();
		p.setShowScheduleID(showScheduleId);
		List<ShowSchedulePromotionPlan> r = sitemgmtShowService.getShowPromotionPlans(p);
		
//		assertFalse(CollectionUtils.isEmpty(r));
		assertNotNull(r);
	}

	/**
	 * Test method for {@link net.fashiongo.webadmin.service.SitemgmtShowService#getShowParticipatingVendors(net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.GetShowParameter)}.
	 */
	@Test
	public final void testGetShowParticipatingVendors() {
//		fail("Not yet implemented"); // TODO
		GetShowParameter p = new GetShowParameter();
		p.setPageNum(1);
		p.setPageSize(10);
		p.setShowScheduleID(1);
		p.setPlanID(1);
		
		GetShowParticipatingVendorsResponse r = sitemgmtShowService.getShowParticipatingVendors(p);
//		assertFalse(CollectionUtils.isEmpty(r.getShowSchedulePromotionPlanVendorList()));
		assertNotNull(r.getShowSchedulePromotionPlanVendorList());

	}

	/**
	 * Test method for {@link net.fashiongo.webadmin.service.SitemgmtShowService#getShowPromotionPlan(java.lang.Integer)}.
	 */
	@Test
	public final void testGetShowPromotionPlan() {
//		fail("Not yet implemented"); // TODO
		
		GetShowPromotionPlanResponse r = sitemgmtShowService.getShowPromotionPlan(1);
		assertNotNull(r.getShowSchedulePromotionPlan());
	}

	/**
	 * Test method for {@link net.fashiongo.webadmin.service.SitemgmtShowService#getShowParticipatingVendor(java.lang.Integer)}.
	 */
	@Test
	public final void testGetShowParticipatingVendor() {
//		fail("Not yet implemented"); // TODO
		ResultResponse<MapShowSchedulePromotionPlanVendor> r = sitemgmtShowService.getShowParticipatingVendor(1);
		assertNotNull(r.getData());

	}

	/**
	 * Test method for {@link net.fashiongo.webadmin.service.SitemgmtShowService#setShowParticipatingVendor(net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.SetShowParticipatingVendorParameters)}.
	 */
	@Test
	@Ignore("Not yet implemented")
	public final void testSetShowParticipatingVendor() {
//		fail("Not yet implemented"); // TODO
		SetShowParticipatingVendorParameters p = new SetShowParticipatingVendorParameters();
		p.setMapId(1);
		p.setPlanId(1);
		p.setWholesalerId(1);
		p.setCommissionRate(1.00);
		p.setCommissionRate(1);
		p.setFee(new BigDecimal(1.00));
		
		ResultResponse<Integer> r = sitemgmtShowService.setShowParticipatingVendor(p);
		
		assertTrue(r.getData() > 0);
		
		
	}

	/**
	 * Test method for {@link net.fashiongo.webadmin.service.SitemgmtShowService#setShowPromotionPlan(net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.SetShowPromotionPlanParameters)}.
	 */
	@Test
	@Ignore("Not yet implemented")
	public final void testSetShowPromotionPlan() {
//		fail("Not yet implemented"); // TODO
		SetShowPromotionPlanParameters p = new SetShowPromotionPlanParameters();
		p.setPlanId(1);
		p.setShowScheduleId(1);
		p.setModifiedBy("krdev");
		p.setIsOnline(true);
		p.setIsOffline(false);
		p.setCommissionEffectiveFrom("");
		p.setCommissionEffectiveTo("");
		
		ResultResponse<Integer> r = sitemgmtShowService.setShowPromotionPlan(p);
		assertTrue(r.getData() > 0);
	}

	/**
	 * Test method for {@link net.fashiongo.webadmin.service.SitemgmtShowService#setDeleteShowPromotionPlan(net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.DelShowParameter)}.
	 */
	@Test
	@Ignore("Not yet implemented")
	public final void testSetDeleteShowPromotionPlan() {
//		fail("Not yet implemented"); // TODO
		DelShowParameter p = new DelShowParameter();
		p.setPlanId(1);
		ResultResponse<Integer> r = sitemgmtShowService.setDeleteShowPromotionPlan(p);
		assertTrue(r.getData() > 0);

	}

	/**
	 * Test method for {@link net.fashiongo.webadmin.service.SitemgmtShowService#setDeleteShowParticipatingVendor(net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.DelShowParameter)}.
	 */
	@Test
	@Ignore("Not yet implemented")
	public final void testSetDeleteShowParticipatingVendor() {
//		fail("Not yet implemented"); // TODO	
		DelShowParameter p = new DelShowParameter();
		p.setMapId(1);
		ResultResponse<Integer> r = sitemgmtShowService.setDeleteShowParticipatingVendor(p);
		assertTrue(r.getData() > 0);

	}

}
