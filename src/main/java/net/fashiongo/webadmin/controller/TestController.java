package net.fashiongo.webadmin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.fashiongo.common.JsonResponse;
import net.fashiongo.webadmin.model.pojo.parameter.GetMessageParameters;
import net.fashiongo.webadmin.service.TestService;

@RestController
@RequestMapping(value = "/test", produces = "application/json")
public class TestController {
	
	@Autowired
	TestService testService;
	
	/**
	 * 
	 * @since 2018. 9. 19.
	 * @author Incheol Jung
	 * @return 
	 * Desc :
	 */
	@GetMapping("/simple")
	public JsonResponse<String> simpleTest() {
		JsonResponse<String> response = new JsonResponse<String>(false, null, null);
		response.setData(testService.simpleTest());
		response.setSuccess(true);
		
		return response;
	}
	
	/**
	 * 
	 * @since 2018. 9. 19.
	 * @author Incheol Jung
	 * @return 
	 * Desc :
	 */
	@GetMapping("/exception")
	public JsonResponse<String> executeException() {
		JsonResponse<String> response = new JsonResponse<String>(false, null, null);
		response.setData(testService.executeException());
		response.setSuccess(true);
		
		return response;
	}
	
	/**
	 * 
	 * @since 2018. 9. 19.
	 * @author Incheol Jung
	 * @param message
	 * @return 
	 * Desc :
	 */
	@GetMapping("/http")
	public JsonResponse<String> callhttpNetwork(String message) {
		JsonResponse<String> response = new JsonResponse<String>(false, null, null);
		response.setData(testService.callhttpNetwork(message));
		response.setSuccess(true);
		
		return response;
	}
	
	/**
	 * 
	 * @since 2018. 9. 19.
	 * @author Incheol Jung
	 * @param parameters
	 * @return 
	 * Desc :
	 */
	@GetMapping("/procedure")
	public JsonResponse<String> callProc(@RequestBody GetMessageParameters parameters) {
		JsonResponse<String> response = new JsonResponse<String>(false, null, null);
		response.setData(testService.callProc(parameters));
		response.setSuccess(true);
		
		return response;
	}
}