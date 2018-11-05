package net.fashiongo.webadmin.service;

import static org.junit.Assert.assertNotNull;
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

import net.fashiongo.webadmin.model.pojo.Message;
import net.fashiongo.webadmin.model.pojo.ResultCode;
import net.fashiongo.webadmin.model.pojo.RetailerNews;
import net.fashiongo.webadmin.model.pojo.parameter.GetMessageParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetRetailerNewsDetailParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetRetailerNewsParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetRetailerNewsParameter;
import net.fashiongo.webadmin.model.pojo.response.GetMessageResponse;
import net.fashiongo.webadmin.model.pojo.response.GetRetailerNewsResponse;
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
			for(Message msg : result.getMessagelist()) {
				assertNotNull(msg.getTitle());
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
}