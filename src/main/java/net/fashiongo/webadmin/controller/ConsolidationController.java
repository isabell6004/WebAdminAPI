package net.fashiongo.webadmin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.fashiongo.webadmin.model.pojo.consolidation.parameter.GetOrderConsolidationSummaryParameter;
import net.fashiongo.webadmin.model.pojo.consolidation.response.GetOrderConsolidationSummaryResponse;
import net.fashiongo.webadmin.service.ConsolidationService;
import net.fashiongo.webadmin.utility.JsonResponse;

@RestController
@RequestMapping(value="/order", produces = "application/json")
public class ConsolidationController {
	@Autowired
	private ConsolidationService consolidationService;

	/**
	 * Get Consolidation Orders Summary
	 * @since 2019. 11. 05
	 * @author Nicole Lee
	 * @return Consolidation Orders List Summary
	 */
	@RequestMapping(value="getorderconsolidationsummary", method=RequestMethod.POST)
	public JsonResponse<GetOrderConsolidationSummaryResponse> getOrderConsolidationSummary(@RequestBody GetOrderConsolidationSummaryParameter parameters) {
		
		GetOrderConsolidationSummaryResponse result = consolidationService.getOrderConsolidationSummary(parameters);	
		return new JsonResponse<GetOrderConsolidationSummaryResponse>(true, null, 0, result);
	}

//	/**
//	 * Get Consolidation Orders List
//	 * @since 2019. 11. 06
//	 * @author Nicole Lee
//	 * @return Consolidation Orders List
//	 */
//	@RequestMapping(value="getorderconsolidation", method=RequestMethod.POST)
//	public JsonResponse<GetOrderConsolidationResponse> getConsolidation(@RequestBody GetOrderConsolidationParameter parameters) {
//		
//		GetOrderConsolidationResponse result = consolidationService.getOrderConsolidation(parameters);	
//		return new JsonResponse<GetOrderConsolidationResponse>(true, null, 0, result);
//	}

}
