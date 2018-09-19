package net.fashiongo.webadmin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	@RequestMapping(value="/simple")
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
	 * @throws Exception 
	 */
	@RequestMapping(value="/exception")
	public JsonResponse<String> executeException() throws Exception {
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
	@RequestMapping(value="/http")
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
	@RequestMapping(value="/procedure", method=RequestMethod.POST)
	public JsonResponse<String> callProc(@RequestBody GetMessageParameters parameters) {
		JsonResponse<String> response = new JsonResponse<String>(false, null, null);
		response.setData(testService.callProc(parameters));
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
	@RequestMapping(value="/session")
	public JsonResponse<String> getSession() {
		JsonResponse<String> response = new JsonResponse<String>(false, null, null);
		response.setData(testService.getSession());
		response.setSuccess(true);
		
		return response;
	}
}