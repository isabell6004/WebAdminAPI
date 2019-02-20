package net.fashiongo.webadmin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import net.fashiongo.webadmin.model.pojo.order.parameter.GetPrintPoUrlParameter;
import net.fashiongo.webadmin.service.OrderService;
import net.fashiongo.webadmin.utility.JsonResponse;

/**
 * 
 * @author Reo
 *
 */
@RestController
@RequestMapping(value="/order", produces = "application/json")
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 11. 23.
	 * @author Reo
	 * @param url
	 * @return
	 * @throws JsonProcessingException 
	 */
	@RequestMapping(value="getprintpourl", method=RequestMethod.POST)
	public JsonResponse getWebRequest(@RequestBody GetPrintPoUrlParameter parameters) throws JsonProcessingException {
		JsonResponse results = orderService.getWebRequest(parameters);
		
		return results;
	}
}
