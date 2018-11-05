package net.fashiongo.webadmin.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.fashiongo.webadmin.model.pojo.Message;
import net.fashiongo.webadmin.model.pojo.parameter.DelVendorNewsParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetMessageParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetVendorNewsDetailParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetVendorNewsParameter;
import net.fashiongo.webadmin.model.pojo.response.GetMessageResponse;
import net.fashiongo.webadmin.model.pojo.response.GetVendorNewsResponse;
import net.fashiongo.webadmin.model.primary.VendorNewsDetail;
import net.fashiongo.webadmin.model.primary.VendorNewsView;

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
			for(Message msg : result.getMessagelist()) {
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



}