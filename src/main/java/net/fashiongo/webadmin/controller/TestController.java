package net.fashiongo.webadmin.controller;

import java.util.List;

import net.fashiongo.webadmin.utility.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.fashiongo.webadmin.model.pojo.message.parameter.GetMessageParameter;
import net.fashiongo.webadmin.model.primary.TrendReport;
import net.fashiongo.webadmin.service.TestService;

@RestController
@RequestMapping(value = "/test", produces = "application/json")
public class TestController {
	
	@Autowired
	TestService testService;
	
	/**
	 * Description Example
	 * @since 2018. 9. 19.
	 * @author Incheol Jung
	 */
	@RequestMapping(value="/simple", method=RequestMethod.GET)
	public JsonResponse<String> simpleTest() {
		JsonResponse<String> response = new JsonResponse<>(false, null, null);
		response.setData(testService.simpleTest());
		response.setSuccess(true);
		
		return response;
	}
	
	/**
	 * Description Example
	 * @since 2018. 9. 19.
	 * @author Incheol Jung
	 * @throws Exception 
	 */
	@RequestMapping(value="/exception", method=RequestMethod.GET)
	public JsonResponse<String> executeException() throws Exception {
		JsonResponse<String> response = new JsonResponse<>(false, null, null);
		response.setData(testService.executeException());
		response.setSuccess(true);
		
		return response;
	}
	
	/**
	 * Description Example
	 * @since 2018. 9. 19.
	 * @author Incheol Jung
	 * @param message
	 */
	@RequestMapping(value="/http", method=RequestMethod.GET)
	public JsonResponse<String> callhttpNetwork(String message) {
		JsonResponse<String> response = new JsonResponse<>(false, null, null);
		response.setData(testService.callhttpNetwork(message));
		response.setSuccess(true);
		
		return response;
	}
	
	/**
	 * Description Example
	 * @since 2018. 9. 19.
	 * @author Incheol Jung
	 * @param parameters
	 */
	@RequestMapping(value="/procedure", method=RequestMethod.POST)
	public JsonResponse<String> callProc(@RequestBody GetMessageParameter parameters) {
		JsonResponse<String> response = new JsonResponse<>(false, null, null);
		response.setData(testService.callProc(parameters));
		response.setSuccess(true);
		
		return response;
	}
	
	/**
	 * Description Example
	 * @since 2018. 9. 19.
	 * @author Incheol Jung
	 */
	@RequestMapping(value="/session", method=RequestMethod.GET)
	public JsonResponse<String> getSession() {
		JsonResponse<String> response = new JsonResponse<>(false, null, null);
		response.setData(testService.getSession());
		response.setSuccess(true);
		
		return response;
	}
	
	@RequestMapping(value="/testTransaction", method=RequestMethod.GET)
	public JsonResponse<TrendReport> testTransaction(@RequestParam List<Integer> ids) {
		JsonResponse<TrendReport> response = new JsonResponse<>(false, null, null);
		response.setData(testService.TransactionTest(ids));
		response.setSuccess(true);
		
		return response;
	}
}