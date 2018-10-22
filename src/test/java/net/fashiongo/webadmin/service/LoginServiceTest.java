package net.fashiongo.webadmin.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginServiceTest {
	
	@Autowired
	LoginService loginService;

	@Test
	public void testCheckIP() {
		boolean result  = loginService.checkIP("::1");
		assertTrue(!result);
	}

}
