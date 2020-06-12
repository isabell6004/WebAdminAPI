package net.fashiongo.webadmin.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.fashiongo.webadmin.data.model.order.*;
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

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.List;
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

	@RequestMapping(value="getprintpourl3", method=RequestMethod.POST)
	public JsonResponse getWebRequest3(@RequestBody GetPrintPoUrlParameter2 parameters) throws JsonProcessingException {
		JsonResponse results = renewalOrderService.getWebRequest3(parameters);

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

	@RequestMapping(value="setconsolidationdetailmessage", method=RequestMethod.POST)
	public JsonResponse setConsolidationDetailMessage(@RequestBody SetConsolidationDetailMessageParameter parameters) throws JsonProcessingException {
		Integer orderId = Optional.ofNullable(parameters.getOrderId()).orElse(0);
		Integer consolidationId = Optional.ofNullable(parameters.getConsolidationId()).orElse(0);
		Integer wholesalerId = Optional.ofNullable(parameters.getWholesalerId()).orElse(0);
		String newsTitle = parameters.getNewsTitle();
		String newsContent = Optional.ofNullable(parameters.getNewsContent())
				.filter(s -> StringUtils.hasLength(s))
				.map(s -> {
					try {
						return URLDecoder.decode(s, "UTF-8");
					} catch (UnsupportedEncodingException e) {
						throw new RuntimeException(e);
					}
				}).orElse(null);

		String lastUser = parameters.getLastUser();
		LocalDateTime fromDate = Optional.ofNullable(parameters.getFromDate())
				.filter(s -> StringUtils.hasLength(s))
				.map(s -> new Date(s).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
				.orElse(LocalDateTime.of(1970,1,1,0,0,0,0));

		fromDate = LocalDateTime.now().minusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);

		LocalDateTime toDate = Optional.ofNullable(parameters.getToDate())
				.filter(s -> StringUtils.hasLength(s))
				.map(s -> new Date(s).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
				.map(dateTime -> {
					if(dateTime.getHour() == 0) {
						return dateTime.plusDays(1).minusSeconds(1);
					}
					return dateTime;
				})
				.orElse(LocalDateTime.of(2099,12,31,0,0,0,0));

		JsonResponse results = renewalOrderService.setConsolidationDetailMessage(orderId,consolidationId,wholesalerId,newsTitle,newsContent,lastUser,fromDate,toDate);

		return results;
	}

	@RequestMapping(value="setundoremoveconsolidationdetail", method=RequestMethod.POST)
	public JsonResponse setUndoRemoveConsolidationDetail(@RequestBody SetUndoRemoveConsolidationDetailParameter parameters) throws JsonProcessingException {
		Integer orderId = Optional.ofNullable(parameters.getOrderId()).orElse(0);
		String sessionUserName = Utility.getUsername();

		JsonResponse results = renewalOrderService.setUndoRemoveConsolidationDetail(orderId,sessionUserName);

		return results;
	}

	@RequestMapping(value="setremoveconsolidationdetail", method=RequestMethod.POST)
	public JsonResponse setRemoveConsolidationDetail(@RequestBody SetRemoveConsolidationDetailParameter parameters) throws JsonProcessingException {
		List<Integer> orderId = Optional.ofNullable(parameters.getDatas()).orElse(Collections.EMPTY_LIST);
		String sessionUserName = Utility.getUsername();

		JsonResponse results = renewalOrderService.setRemoveConsolidationDetail(orderId,sessionUserName);

		return results;
	}

	@RequestMapping(value="setconsolidationdetailorderstatus", method=RequestMethod.POST)
	public JsonResponse setConsolidationDetailOrderStatus(HttpServletRequest httpServletRequest, @RequestBody SetConsolidationDetailOrderStatusParameter parameters) throws JsonProcessingException {
		Integer consolidationId = Optional.ofNullable(parameters.getConsolidationId()).orElse(0);
		Integer orderId = Optional.ofNullable(parameters.getOrderId()).orElse(0);
		Integer orderStatusId = Optional.ofNullable(parameters.getOrderStatusId()).orElse(0);
		String sessionUserName = Utility.getUsername();
		String ipAddress = Utility.getIpAddress(httpServletRequest);

		JsonResponse results = renewalOrderService.setConsolidationDetailOrderStatus(orderId,consolidationId,orderStatusId,sessionUserName,ipAddress);

		return results;
	}
}
