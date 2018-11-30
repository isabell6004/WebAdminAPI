package net.fashiongo.webadmin.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import net.fashiongo.webadmin.model.pojo.common.ResultCode;
import net.fashiongo.webadmin.model.pojo.message.RetailerNews;
import net.fashiongo.webadmin.model.pojo.message.parameter.DelVendorNewsParameter;
import net.fashiongo.webadmin.model.pojo.message.parameter.GetMessageParameter;
import net.fashiongo.webadmin.model.pojo.message.parameter.GetRetailerNewsDetailParameter;
import net.fashiongo.webadmin.model.pojo.message.parameter.GetRetailerNewsParameter;
import net.fashiongo.webadmin.model.pojo.message.parameter.GetVendorNewsDetailParameter;
import net.fashiongo.webadmin.model.pojo.message.parameter.GetVendorNewsParameter;
import net.fashiongo.webadmin.model.pojo.message.parameter.SetMessageParameter;
import net.fashiongo.webadmin.model.pojo.message.parameter.SetMessageReadYNParameter;
import net.fashiongo.webadmin.model.pojo.message.parameter.SetRetailerNewsParameter;
import net.fashiongo.webadmin.model.pojo.message.response.GetMessageReplyResponse;
import net.fashiongo.webadmin.model.pojo.message.response.GetMessageResponse;
import net.fashiongo.webadmin.model.pojo.message.response.GetRetailerNewsResponse;
import net.fashiongo.webadmin.model.pojo.message.response.GetVendorNewsResponse;
import net.fashiongo.webadmin.model.primary.MessageCategory;
import net.fashiongo.webadmin.model.primary.RetailerCompany;
import net.fashiongo.webadmin.model.primary.TblRetailerNews;
import net.fashiongo.webadmin.model.primary.VendorNewsDetail;
import net.fashiongo.webadmin.model.primary.VendorNewsView;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageServiceTest {
	
	@Autowired
	MessageService messageService;
	
	@Autowired 
	BuyerService buyerService;
	
	/**
     * 
     * testGetVendorNews
     * 
     * @since 2018. 11. 5.
     * @author Dahye
     */
	@Test
	public void testGetVendorNews() {
		GetVendorNewsParameter param = new GetVendorNewsParameter();
		param.setPageNum(1);
		param.setPageSize(10);
		param.setVendor("FashionGo");
		param.setNewsTitle("");
		param.setActive("1");
		param.setOrderBy("");
		param.setDropOffNotice(false);
		param.setFromDate("1/1/2018 00:00:00");
		param.setToDate("12/31/2018 23:59:59");
		
		GetVendorNewsResponse result = messageService.getVendorNews(param);
		
		if(result != null) {
			assertTrue(result.getNewsList().size() > 0);
			
		}
	}

	/**
     * 
     * testDelVendorNews
     * 
     * @since 2018. 11. 5.
     * @author Dahye
     */
	@Test
	@Ignore
	public void testDelVendorNews() {
		DelVendorNewsParameter param = new DelVendorNewsParameter();
		param.setArrayNewsID("12637");
		Integer result = messageService.delVendorNews(param);
		assertSame(result, 1);	
	}
	
	/**
     * 
     * testGetVendorNewsDetail
     * 
     * @since 2018. 11. 5.
     * @author Dahye
     */
	@Test
	public void testGetVendorNewsDetail() {
		GetVendorNewsDetailParameter param = new GetVendorNewsDetailParameter();
		param.setNewsID(18593);
		VendorNewsView result = messageService.getVendorNewsDetail(param);
		assertNotNull(result);
	}

	/**
     * 
     * testSetVendorNews
     * 
     * @since 2018. 11. 5.
     * @author Dahye
     */
	@Test
	@Ignore
	public void testSetVendorNews() {
		String selectedvendor = "";
		VendorNewsDetail news = new VendorNewsDetail();
		news.setActive(true);
		news.setSortNo(0);
		news.setShowBanner(false);
		news.setNewsTitle("junit test");
		news.setNewsContent("<p>test</p>\\n");
		news.setNewsID(null);
		news.setFromDate("09/07/2018");
		news.setToDate("09/30/2018");
		
		Integer result = messageService.setVendorNews(news, selectedvendor);
		assertSame(result, 1);
	}

	/**
     * 
     * testSetVendorNewsInActive
     * 
     * @since 2018. 11. 5.
     * @author Dahye
     */
	@Test
	@Ignore
	public void testSetVendorNewsInActive() {
		DelVendorNewsParameter param = new DelVendorNewsParameter();
		param.setArrayNewsID("12637");
		Integer result = messageService.setVendorNewsInActive(param);
		assertSame(result, 1);	
	}



	/**
	 * 
	 * Description Example
	 * @since 2018. 11. 5.
	 * @author Reo
	 */
	@Test
	public void testGetRetailerNews() {
		GetRetailerNewsParameter parameters = new GetRetailerNewsParameter();
		parameters.setPageNum(1);
		parameters.setPageSize(15);
		parameters.setNewsTitle(null);
		parameters.setActive("A");
		parameters.setOrderBy(null);
		parameters.setFromDate(null);
		parameters.setToDate(null);
		
		GetRetailerNewsResponse result = messageService.getRetailerNews(parameters);
		if(result != null) {
			List<RetailerNews> retailerNewsList = result.getRetailerNewsList();
			if(!CollectionUtils.isEmpty(retailerNewsList)) {
				assertNotNull(result.getRetailerNewsList());
			}
		}
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 11. 5.
	 * @author Reo
	 */
	@Test
	public void testGetRetailerNewsDetail() {
		GetRetailerNewsDetailParameter parameters = new GetRetailerNewsDetailParameter();
		parameters.setNewsID(215);
		
		TblRetailerNews result = messageService.getRetailerNewsDetail(parameters);
		if(result != null) {
			assertNotNull(result);
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
	public void testSetRetailerNews() {
		SetRetailerNewsParameter parameters = new SetRetailerNewsParameter();
		parameters.setNewsID(215);
		parameters.setActive(false);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		parameters.setFromDate(now);
		parameters.setToDate(null);
		parameters.setNewsTitle("ntroducing Merged Orders!");
		parameters.setNewsContent("<p><img alt=\\\"\\\" src=\\\"https://s3-us-west-2.amazonaws.com/fg-business-data/notice_image/buyer/7ad91663ac7ef5e.jpg\\\" style=\\\"float: left; width: 1022px; height: 1800px;\\\" /></p>\\n");
		parameters.setSortNo(0);
		
		ResultCode result = messageService.setRetailerNews(parameters);
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
	public void testDelRetailerNews() {
		List<Integer> parameters = new ArrayList<Integer>();
		parameters.add(216);
		
		ResultCode result = messageService.delRetailerNews(parameters);
		if(result != null) {
			assertTrue(result.getSuccess());
		}
	}
	
	/**
	 * 
	 * Description Example
	 * 
	 * @since 2018. 11. 26.
	 * @author Incheol Jung
	 */
	@Test
	public void testGetMessageReply() {
		GetMessageReplyResponse result = messageService.getMessageReply(4131283);
		
		if(!CollectionUtils.isEmpty(result.getMessageReplyList())) {
			assertNotNull(result.getMessageReplyList().get(0).getMessageID());
		}
	}
	
	/**
	 * 
	 * test GetMessageCategory
	 * 
	 * @since 2018. 11. 26.
	 * @author Incheol Jung
	 */
	@Test
	public void testGetMessageCategory() {
		List<MessageCategory> result = messageService.getMessageCategory();
		if(!CollectionUtils.isEmpty(result)) {
			assertNotNull(result.get(0).getMessageCategoryID());
		}
	}
	
	/**
	 * 
	 * test GetMessage
	 * 
	 * @since 2018. 11. 27.
	 * @author Incheol Jung
	 */
	@Test
	public void testGetMessage() {
		GetMessageParameter parameters = new GetMessageParameter();
		parameters.setPagenum(1);
		parameters.setPagesize(100);
		parameters.setSendertypeid(1);
		
		GetMessageResponse result = messageService.getMessage(parameters);
		if(!CollectionUtils.isEmpty(result.getMessagelist())) {
			assertNotNull(result.getMessagelist().get(0).getMessageID());
		}
	}
	
	/**
	 * 
	 * test GetRetailerListForCompanyName
	 * 
	 * @since 2018. 11. 27.
	 * @author Incheol Jung
	 */
	@Test
	public void testGetRetailerListForCompanyName() {
		List<RetailerCompany> result = buyerService.GetRetailerListForCompanyName("fashion");
		if(!CollectionUtils.isEmpty(result)) {
			assertNotNull(result.get(0).getRetailerID());
		}
	}
	
	/**
	 * 
	 * test SetMessage
	 * 
	 * @since 2018. 11. 27.
	 * @author Incheol Jung
	 */
	@Ignore
	@Test
	public void testSetMessage() {
		SetMessageParameter parameters = new SetMessageParameter();
		parameters.setSenderid(109);
		parameters.setCreatedby("krdev");
		parameters.setRecipientid(34694);
		parameters.setRecipientid(1);
		parameters.setTitle("test title");
		parameters.setContent("test contents");
		parameters.setTopic("1");
		parameters.setTopreferenceid(0);
		
		ResultCode result = messageService.setMessage(parameters);
		assertTrue(result.getSuccess());
	}
	
	/**
	 * 
	 * test setMessageReadYN
	 * 
	 * @since 2018. 11. 27.
	 * @author Incheol Jung
	 */
	@Ignore
	@Test
	public void testsetMessageReadYN() {
		SetMessageReadYNParameter parameters = new SetMessageReadYNParameter();
		parameters.setMessageID(1);
		parameters.setReadYn(false);
		
		ResultCode result = messageService.setMessageReadYN(parameters);
		assertTrue(result.getSuccess());
	}
}