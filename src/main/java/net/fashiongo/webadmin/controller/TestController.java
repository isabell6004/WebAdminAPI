package net.fashiongo.webadmin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.fashiongo.common.JsonResponse;
import net.fashiongo.webadmin.service.TestService;

@RestController
@RequestMapping(value = "/test", produces = "application/json")
public class TestController {
	
	@Autowired
	TestService testService;
	
	@GetMapping("/get")
	public JsonResponse<String> getDisputes(String message) {
		JsonResponse<String> response = new JsonResponse<String>(false, null, null);
		response.setData(testService.getMethod(message));
		response.setSuccess(true);
		
		return response;
	}
}