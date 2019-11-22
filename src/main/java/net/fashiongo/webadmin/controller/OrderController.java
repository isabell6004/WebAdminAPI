package net.fashiongo.webadmin.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.fashiongo.webadmin.data.model.order.GetPrintPoUrlParameter2;
import net.fashiongo.webadmin.model.pojo.order.parameter.GetPrintPoUrlParameter;
import net.fashiongo.webadmin.service.OrderService;
import net.fashiongo.webadmin.service.renewal.RenewalOrderService;
import net.fashiongo.webadmin.utility.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

	@Autowired
	private RenewalOrderService renewalOrderService;

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

	@RequestMapping(value="getprintpourl2", method=RequestMethod.POST)
	public JsonResponse getWebRequest2(@RequestBody GetPrintPoUrlParameter2 parameters) throws JsonProcessingException {
		JsonResponse results = renewalOrderService.getWebRequest2(parameters);

		return results;
	}

	@RequestMapping(value="getprintmergepourl", method=RequestMethod.POST)
	public JsonResponse getPrintMergePOUrl(@RequestBody GetPrintPoUrlParameter2 parameters) throws JsonProcessingException {
		JsonResponse results = renewalOrderService.getPrintMergePOUrl(parameters);

		return results;
	}
}
