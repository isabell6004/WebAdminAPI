package net.fashiongo.webadmin.service;

import org.springframework.stereotype.Service;

@Service
public class TestService {

	public String getMethod() {
		return "testService response message";
	}
}
