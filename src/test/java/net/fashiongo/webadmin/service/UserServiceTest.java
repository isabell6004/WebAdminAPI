package net.fashiongo.webadmin.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.fashiongo.webadmin.model.pojo.ResultCode;
import net.fashiongo.webadmin.model.pojo.parameter.SetModifyPasswordParameter;
/**
 * 
 * @author DAHYE
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	@Autowired
	UserService userService;
	
	/**
     * 
     * testResetPassword
     * 
     * @since 2018. 10. 22.
     * @author Dahye
     */
	@Test
	public void testResetPassword() {
		SetModifyPasswordParameter param = new SetModifyPasswordParameter();
		param.setUserName("admingo");
		param.setNewPassword("1234");
		ResultCode result = userService.ResetPassword(param);
		assertTrue(result.getSuccess());		
	}

}
