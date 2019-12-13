package net.fashiongo.webadmin.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
import net.fashiongo.webadmin.service.ConsolidationService;
import net.fashiongo.webadmin.utility.JsonResponse;

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

	
}
