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

import com.fasterxml.jackson.core.JsonProcessingException;

import net.fashiongo.webadmin.model.pojo.common.ResultCode;
import net.fashiongo.webadmin.model.pojo.message.RetailerNews;
import net.fashiongo.webadmin.model.pojo.message.VwWaMessage;
import net.fashiongo.webadmin.model.pojo.message.parameter.DelVendorNewsParameter;
import net.fashiongo.webadmin.model.pojo.message.parameter.GetContactUsParameter;
import net.fashiongo.webadmin.model.pojo.message.parameter.GetMessageParameter;
import net.fashiongo.webadmin.model.pojo.message.parameter.GetRetailerNewsDetailParameter;
import net.fashiongo.webadmin.model.pojo.message.parameter.GetRetailerNewsParameter;
import net.fashiongo.webadmin.model.pojo.message.parameter.GetVendorNewsDetailParameter;
import net.fashiongo.webadmin.model.pojo.message.parameter.GetVendorNewsParameter;
import net.fashiongo.webadmin.model.pojo.message.parameter.SetContactUsReplyParameter;
import net.fashiongo.webadmin.model.pojo.message.parameter.SetRetailerNewsParameter;
import net.fashiongo.webadmin.model.pojo.message.response.GetMessageReplyResponse;
import net.fashiongo.webadmin.model.pojo.message.response.GetMessageResponse;
import net.fashiongo.webadmin.model.pojo.message.response.GetRetailerNewsResponse;
import net.fashiongo.webadmin.model.pojo.message.response.GetVendorNewsResponse;
import net.fashiongo.webadmin.model.pojo.parameter.SetDenyBannerParameter;
import net.fashiongo.webadmin.model.pojo.vendor.response.GetContactUsResponse;
import net.fashiongo.webadmin.model.primary.VendorNewsDetail;
import net.fashiongo.webadmin.model.primary.VendorNewsView;
import net.fashiongo.webadmin.model.primary.TblRetailerNews;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageServiceTest {
	
	@Autowired
	MessageService messageService;

	@Test
	public void testGetMessage() {
		GetMessageParameter parameters = new GetMessageParameter();
		parameters.setPagesize(20);
		parameters.setPagenum(0);
		parameters.setSendertypeid(1);
		
		GetMessageResponse result = messageService.getMessage(parameters);
		
		if(result != null) {
			for(VwWaMessage msg : result.getMessagelist()) {
				assertNotNull(msg.getTitle());
			}
		}
	}
	
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
	 * @since 2018. 11. 26.
	 * @author Reo
	 */
	@Test
	public void testGetContactUs() {
		GetContactUsParameter parameters = new GetContactUsParameter();
		parameters.setPageNum(0);
		parameters.setPageSize(20);
		parameters.setTopic(null);
		GetContactUsResponse result = messageService.getContactUs(parameters);
		if(!CollectionUtils.isEmpty(result.getContactUsList())) {
			assertNotNull(result.getContactUsList());
			assertNotNull(result.getTotal());
		}
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 11. 26.
	 * @author Reo
	 * @throws JsonProcessingException 
	 */
	@Ignore
	@Test
	public void testSetContactUsReply() throws JsonProcessingException {
		SetContactUsReplyParameter parameters = new SetContactUsReplyParameter();
		parameters.setContactID(18849);
		parameters.setRepliedBy("krdev");
		parameters.setReply("test2222");
		ResultCode result = messageService.setContactUsReply(parameters);
		assertTrue(result.getResultCode() > 0);
	}
	
	/**
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
}