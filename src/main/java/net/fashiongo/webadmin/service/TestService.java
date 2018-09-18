package net.fashiongo.webadmin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import net.fashiongo.webadmin.dao.fgem.EmApplicationRepository;
import net.fashiongo.webadmin.model.fgem.EmApplication;
import net.fashiongo.webadmin.utility.HttpClient;

/**
 * 
 * @author Incheol Jung
 */
@Service
public class TestService {
	
	@Autowired
	@Qualifier("webAdminJsonClient")
	private HttpClient httpClient;
	
	@Autowired
	EmApplicationRepository emApplicationRepository;

	public String getMethod(String message) {
		if(message.equals("error")) {
			List<String> arr = new ArrayList<String>();
			arr.add("test");
			arr.get(10);
		}else {
//			JSONObject obj = new JSONObject();
//			obj.put("token", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJyb2xlaWQiOiJTIiwidXNlcklkIjoxMDksInVzZXJuYW1lIjoia3JkZXYiLCJmdWxsbmFtZSI6IktvcmVhIERldmVsb3BlciIsImlwYWRkciI6IjEwMy4yNDMuMjAwLjEyIiwidXNlcmFnZW50IjoiTW96aWxsYS81LjAgKFdpbmRvd3MgTlQgMTAuMDsgV2luNjQ7IHg2NCkgQXBwbGVXZWJLaXQvNTM3LjM2IChLSFRNTCwgbGlrZSBHZWNrbykgQ2hyb21lLzY4LjAuMzQ0MC4xMDYgU2FmYXJpLzUzNy4zNiIsIm5iZiI6MTUzNzIyODE3Mi4wLCJpYXQiOjE1MzcyMjgxNzIuMCwiZXhwIjoxNTM3MjcxMzcyLjB9.RC4DJ-xa_MiWsuLxRqCDc6BdpxCW8Ok1EZ1yqW_IhI1K4_iGGdPGrVdA_pEGB08E_8SOkZxUFU2ho1YYWvfb-A");
//			JsonResponse result = httpClient.postObject("Account/TokenCheck", obj);
////			JsonResponse result = httpClient.get("Account/Test");
//			System.out.println("result.getMessage()" + result.getMessage());
			
			EmApplication em = emApplicationRepository.findOne(1);
			System.out.println("em.getName()" + em.getName());
		}
		
		return "testService response message";
	}
}
