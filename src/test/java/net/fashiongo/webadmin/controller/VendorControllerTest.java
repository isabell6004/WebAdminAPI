package net.fashiongo.webadmin.controller;

import net.fashiongo.webadmin.controller.vendor.VendorController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.model.pojo.common.PagedResult;
import net.fashiongo.webadmin.model.primary.VendorContent;
import net.fashiongo.webadmin.utility.JsonResponse;

/**
 * @author Kenny/Kyungwoo
 * @since 2019-04-16
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class VendorControllerTest {
	@Autowired
    private VendorController vendorController;
	
	@Test
	public void getMediaRequests() {
		 JsonResponse<PagedResult<VendorContent>> response = vendorController.getMediaRequests("1", "10", null, null, null, null, null);
		 log.debug(response.toString());
	}
	
	@Test
	public void approveMediaRequest() {
//		 JsonResponse<String> response = vendorController.approveMediaRequest("1");
//		 log.debug(response.toString());
//		 Assert.assertTrue(response.isSuccess());
//		 
//		 response = vendorController.approveMediaRequest("1");
//		 log.debug(response.toString());
//		 Assert.assertFalse(response.isSuccess());
	}
	
	@Test
	public void denyeMediaRequest() {
//		 JsonResponse<String> response = vendorController.denyMediaRequest("1");
//		 log.debug(response.toString());
//		 Assert.assertTrue(response.isSuccess());
//		 
//		 response = vendorController.denyMediaRequest("1");
//		 log.debug(response.toString());
//		 Assert.assertFalse(response.isSuccess());
	}
}
