package net.fashiongo.webadmin.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import net.fashiongo.webadmin.utility.JsonResponse;

import net.fashiongo.webadmin.model.pojo.parameter.SetModifyPasswordParameter;
/**
 * 
 * @author DAHYE
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BuyerServiceTest {
	@Autowired
	BuyerService buyerService;

	@Test
	public void testSetModifyPassword() {
		SetModifyPasswordParameter parameters = new SetModifyPasswordParameter();
		parameters.setUserName("admingo");
		parameters.setNewPassword("1234");
		
		JsonResponse result = buyerService.SetModifyPassword(parameters);
		assertTrue(result.isSuccess());
	}

}
