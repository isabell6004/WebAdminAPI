package net.fashiongo.webadmin.controller;

import java.util.List;

import net.fashiongo.webadmin.model.pojo.consolidation.parameter.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import net.fashiongo.webadmin.model.pojo.consolidation.response.GetConsolidationDetailResponse;
import net.fashiongo.webadmin.model.pojo.consolidation.response.GetConsolidationResponse;
import net.fashiongo.webadmin.model.pojo.consolidation.response.GetConsolidationSummaryResponse;
import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.model.pojo.consolidation.response.ShipMethodResponse;
import net.fashiongo.webadmin.service.ConsolidationService;
import net.fashiongo.webadmin.utility.JsonResponse;
import net.fashiongo.webadmin.utility.Utility;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping(value="/order", produces = "application/json")
public class ConsolidationController {
	@Autowired
	private ConsolidationService consolidationService;

	/**
	 * Get Consolidation Orders List Summary
	 * @since 2019. 11. 05
	 * @author Nicole Lee
	 * @return Consolidation Orders List Summary
	 */
	@RequestMapping(value="getorderconsolidationsummary", method=RequestMethod.POST)
	public JsonResponse<GetConsolidationSummaryResponse> getOrderConsolidationListSummary(@RequestBody GetConsolidationSummaryParameter parameters) {

		GetConsolidationSummaryResponse result = consolidationService.getOrderConsolidationListSummary(parameters);
		return new JsonResponse<>(true, null, 0, result);
	}

	/**
	 * Get Consolidation Orders List
	 * @since 2019. 11. 06
	 * @author Nicole Lee
	 * @return Consolidation Orders List
	 */
	@RequestMapping(value="getorderconsolidation", method=RequestMethod.POST)
	public JsonResponse<GetConsolidationResponse> getConsolidationList(@RequestBody GetConsolidationParameter parameters) {
		
		GetConsolidationResponse result = consolidationService.getConsolidationList(parameters);
		return new JsonResponse<>(true, null, 0, result);
	}

	/**
	 * Get Consolidation Orders Detail
	 * @since 2019. 11. 07
	 * @author Nicole Lee
	 * @return Consolidation Orders Detail
	 */
	@RequestMapping(value="getorderconsolidationdetail", method=RequestMethod.POST)
	public JsonResponse<GetConsolidationDetailResponse> getConsolidationDetailList(@RequestBody GetConsolidationDetailParameter parameters) {

		GetConsolidationDetailResponse result = consolidationService.getConsolidationDetailList(parameters);
		return new JsonResponse<>(true, null, 0, result);
	}

	@PostMapping(value = "consolidationDetail")
	public JsonResponse<Object> setConsolidationDetail(
			@RequestBody ConsolidationDetailRequest consolidationDetailRequest,
			HttpServletRequest request) {
		try {
			consolidationService.setConsolidationDetail(
					consolidationDetailRequest,
					Utility.getUsername(),
					Utility.getIpAddress(request));
			return new JsonResponse<>(true, null, null);
		} catch (Exception e) {
			log.error("ConsolidationController.setConsolidationDetail() request={}", request, e);
			return new JsonResponse<>( false, e.getMessage(), null);
		}
	}

	@PostMapping(value = "fullyShipped/{consolidationId}")
	public JsonResponse<Object> setFullyShipped(
			@PathVariable("consolidationId") Integer consolidationId,
			HttpServletRequest request) {
		try {
			consolidationService.setFullyShipped(
					consolidationId,
					Utility.getUsername(),
					Utility.getIpAddress(request));
			return new JsonResponse<>(true, null, null);
		} catch (Exception e) {
			log.error("ConsolidationController.setFullyShipped() consolidationId={}", consolidationId, e);
			return new JsonResponse<>( false, e.getMessage(), null);
		}
	}

	@GetMapping(value = "consolidationShipMethod")
	public JsonResponse<List<ShipMethodResponse>> getConsolidationShipMethod() {
		try {
			return new JsonResponse<>(true, null, consolidationService.getConsolidationShipMethod());
		} catch (Exception e) {
			log.error("ConsolidationController.getConsolidationShipMethod()", e);
			return new JsonResponse<>(false, e.getMessage(), null);
		}
	}

	@PostMapping(value = "consolidationMemo")
	public JsonResponse<Object> setConsolidationMemo(@RequestBody ConsolidationMemoRequest request) {
		try {
			consolidationService.setConsolidationMemo(request, Utility.getUsername());
			return new JsonResponse<>(true, null, null);
		} catch (Exception e) {
			log.error("ConsolidationController.setConsolidationMemo() request={}", request, e);
			return new JsonResponse<>(false, e.getMessage(), null);
		}
	}

	@PostMapping(value = "consolidationDetailShippingAddress")
	public JsonResponse<Object> setConsolidationDetailShippingAddress(
			@RequestBody ConsolidationDetailShippingAddressRequest request) {
		try {
			consolidationService.setConsolidationDetailShippingAddress(request, Utility.getUsername());
			return new JsonResponse<>(true, null, null);
		} catch (Exception e) {
			log.error("ConsolidationController.setConsolidationDetailShippingAddress() request={}", request, e);
			return new JsonResponse<>( false, e.getMessage(), null);
		}
	}

	@PostMapping(value = "checkConsolidationPaymentStatus")
	public JsonResponse<Object> checkConsolidationPaymentStatus(Integer consolidationId) {
		try {
			consolidationService.checkConsolidationPaymentStatus(consolidationId);
			return new JsonResponse<>(true, null, null);
		} catch (Exception e) {
			log.error("ConsolidationController.checkConsolidationPaymentStatus() consolidationId={}", consolidationId, e);
			return new JsonResponse<>(false, e.getMessage(), null);
		}
	}

//	@PostMapping(value = "setconsolidationdetailorderstatus")
//	public JsonResponse<Object> setConsolidationDetailOrderStatus(@RequestBody ConsolidationDetailOrderStatusRequest orderStatusRequest) {
//		try {
//			consolidationService.setConsolidationDetailOrderStatus(orderStatusRequest, Utility.getUsername());
//			return new JsonResponse<>(true, null, null);
//		} catch (Exception e) {
//			log.error("ConsolidationController.setConsolidationDetailOrderStatus() orderStatusRequest={}", orderStatusRequest, e);
//			return new JsonResponse<>(false, e.getMessage(), null);
//		}
//	}


	@PostMapping(value = "checkAddressCommercial")
	public JsonResponse<Boolean> checkAddressCommercial(@RequestBody ConsolidationDetailShippingAddressRequest addressRequest) {
		try {
			return new JsonResponse<>(true, null, consolidationService.checkAddressCommercial(addressRequest));
		} catch (Exception e) {
			log.error("ConsolidationController.checkAddressCommercial() addressRequest={}", addressRequest, e);
			return new JsonResponse<>( false, e.getMessage(), null);
		}
	}

	@PostMapping(value = "sendConsolidationEmail/{consolidationId}")
	public JsonResponse<Boolean> sendConsolidationEmail(@PathVariable("consolidationId") Integer consolidationId) {
		try {
			return new JsonResponse<>(true, null, consolidationService.sendConsolidationEmail(consolidationId));
		} catch (Exception e) {
			log.error("ConsolidationController.sendConsolidationEmail() consolidationId={}", consolidationId, e);
			return new JsonResponse<>( false, e.getMessage(), null);
		}
	}
	
	@PostMapping(value = "/consolidation/get/dropoff")
	public JsonResponse<Object> getDropOff(@RequestBody ConsolidationDropOffRequest dropoffRequest) {
		try {
			return new JsonResponse<>(true, null, consolidationService.getDropOffConsolidationOrder(dropoffRequest.getPoNumber()));
		} catch (Exception e) {
			log.error("ConsolidationController.getDropOff() dropoffRequest={}", dropoffRequest.getPoNumber(), e);
			return new JsonResponse<>(false, e.getMessage(), null);
		}
	}
	
	@PostMapping(value = "/consolidation/set/dropoff")
	public JsonResponse<Boolean> setDropOff(@RequestBody ConslidationDropOffSaveRequest dropoffSaveRequest) {
		try {
			return new JsonResponse<>(true, null, consolidationService.setDropOffConsolidationOrder(dropoffSaveRequest));
		} catch (Exception e) {
			log.error("ConsolidationController.setDropOff() dropoffSaveRequest={}", dropoffSaveRequest, e);
			return new JsonResponse<>(false, e.getMessage(), null);
		}
	}
}
