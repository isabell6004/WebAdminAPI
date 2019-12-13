package net.fashiongo.webadmin.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.fashiongo.webadmin.data.model.order.GetPrintPoUrlParameter2;
import net.fashiongo.webadmin.data.model.order.SetConsolidationDetailDroppedByParameter;
import net.fashiongo.webadmin.model.pojo.order.parameter.GetPrintPoUrlParameter;
import net.fashiongo.webadmin.service.OrderService;
import net.fashiongo.webadmin.service.renewal.RenewalOrderService;
import net.fashiongo.webadmin.utility.JsonResponse;
import net.fashiongo.webadmin.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

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

	@RequestMapping(value="setconsolidationdetaildroppedby", method=RequestMethod.POST)
	public JsonResponse setConsolidationDetailDroppedBy(@RequestBody SetConsolidationDetailDroppedByParameter parameters) throws JsonProcessingException {
		Integer orderId = Optional.ofNullable(parameters.getOrderId()).orElse(0);
		Integer consolidationId = Optional.ofNullable(parameters.getConsolidationId()).orElse(0);
		String droppedBy = parameters.getDroppedBy();
		String receivedBy = parameters.getReceivedBy();
		LocalDateTime receivedOn = Optional.ofNullable(parameters.getReceivedOn())
				.filter(s -> StringUtils.hasLength(s))
				.map(s -> new Date(s).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
				.orElse(LocalDateTime.of(1970,1,1,0,0,0,0));

		String sessionUserName = Utility.getUsername();

		JsonResponse results = renewalOrderService.setConsolidationDetailDroppedBy(orderId,consolidationId,droppedBy,receivedBy,receivedOn,sessionUserName);

		return results;
	}
}
