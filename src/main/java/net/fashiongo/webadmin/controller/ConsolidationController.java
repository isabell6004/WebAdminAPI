package net.fashiongo.webadmin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.fashiongo.webadmin.model.pojo.consolidation.parameter.GetConsolidationDetailParameter;
import net.fashiongo.webadmin.model.pojo.consolidation.parameter.GetConsolidationParameter;
import net.fashiongo.webadmin.model.pojo.consolidation.parameter.GetConsolidationSummaryParameter;
import net.fashiongo.webadmin.model.pojo.consolidation.response.GetConsolidationDetailResponse;
import net.fashiongo.webadmin.model.pojo.consolidation.response.GetConsolidationResponse;
import net.fashiongo.webadmin.model.pojo.consolidation.response.GetConsolidationSummaryResponse;
import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.model.pojo.consolidation.parameter.ConsolidationDetailShippingAddressRequest;
import net.fashiongo.webadmin.model.pojo.consolidation.parameter.ConsolidationMemoRequest;
import net.fashiongo.webadmin.model.pojo.consolidation.response.ShipMethodResponse;
import net.fashiongo.webadmin.service.ConsolidationService;
import net.fashiongo.webadmin.utility.JsonResponse;
import net.fashiongo.webadmin.utility.Utility;

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
	public JsonResponse<Object> setConsolidationMemo(@RequestBody ConsolidationMemoRequest memoRequest) {
		try {
			consolidationService.setConsolidationMemo(memoRequest, Utility.getUsername());
			return new JsonResponse<>(true, null, null);
		} catch (Exception e) {
			log.error("ConsolidationController.setConsolidationMemo() memoRequest={}", memoRequest, e);
			return new JsonResponse<>(false, e.getMessage(), null);
		}
	}

	@PostMapping(value = "consolidationDetailShippingAddress")
	public JsonResponse<Object> setConsolidationDetailShippingAddress(
			@RequestBody ConsolidationDetailShippingAddressRequest addressRequest) {
		try {
			consolidationService.setConsolidationDetailShippingAddress(addressRequest, Utility.getUsername());
			return new JsonResponse<>(true, null, null);
		} catch (Exception e) {
			log.error("ConsolidationController.setConsolidationDetailShippingAddress() addressRequest={}", addressRequest, e);
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
			log.error("ConsolidationController.setConsolidationDetailShippingAddress() addressRequest={}", addressRequest, e);
			return new JsonResponse<>( false, e.getMessage(), null);
		}
	}
}
