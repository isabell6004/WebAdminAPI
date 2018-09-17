package net.fashiongo.webadmin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import net.fashiongo.webadmin.utility.HttpClient;
import net.fashiongo.webadmin.utility.JsonResponse;

@Service
public class TestService {
	
	@Autowired
	@Qualifier("webAdminJsonClient")
	private HttpClient httpClient;

	public String getMethod(String message) {
		if(message.equals("error")) {
			List<String> arr = new ArrayList<String>();
			arr.add("test");
			arr.get(10);
		}else {
//			JsonResponse result = httpClient.post("Account/TokenCheck", "");
			JsonResponse result = httpClient.get("Account/Test");
			System.out.println("result.getMessage()" + result.getMessage());
		}
		
		return "testService response message";
	}
}
