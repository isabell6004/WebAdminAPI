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
	private LoginService loginService;

	@Test
	public void testCheckIP() {
		assertTrue(loginService.checkIP("8.41.55.212"));
		assertTrue(loginService.checkIP("10.77.252.136"));
		assertTrue(loginService.checkIP("10.78.232.251"));
	}

}
