package net.fashiongo.webadmin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TestService {

	public String getMethod(String message) {
		if(message.equals("error")) {
			List<String> arr = new ArrayList<String>();
			arr.add("test");
			arr.get(10);
		}
		
		return "testService response message";
	}
}
