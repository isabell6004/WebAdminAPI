package net.fashiongo.webadmin.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import net.fashiongo.webadmin.controller.response.ShipMethodResponse;
import net.fashiongo.webadmin.dao.primary.ShipMethodRepository;
import net.fashiongo.webadmin.data.entity.primary.ShipMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import net.fashiongo.webadmin.model.pojo.consolidation.OrderConsolidationSummary;
import net.fashiongo.webadmin.model.pojo.consolidation.parameter.GetOrderConsolidationSummaryParameter;
import net.fashiongo.webadmin.model.pojo.consolidation.response.GetOrderConsolidationSummaryResponse;
import net.fashiongo.webadmin.utility.HttpClient;
import org.springframework.util.CollectionUtils;

@Service
public class ConsolidationService extends ApiService {
	@Autowired
	@Qualifier("serviceJsonClient")
	private HttpClient httpClient;

	@Autowired
	private ShipMethodRepository shipMethodRepository;

	@SuppressWarnings("unchecked")
	public GetOrderConsolidationSummaryResponse getOrderConsolidationSummary(GetOrderConsolidationSummaryParameter q) {
		String spName = "up_wa_GetConsolidationSummary";
		List<Object> params = new ArrayList<Object>();
		GetOrderConsolidationSummaryResponse result = new GetOrderConsolidationSummaryResponse();
		params.add(q.getFromDate());
		params.add(q.getToDate());

		List<Object> _result = jdbcHelper.executeSP(spName, params, OrderConsolidationSummary.class);
		
		result.setOrderConsolidationSummary((List<OrderConsolidationSummary>) _result.get(0));
		return result;
	}

	/*
	@SuppressWarnings("unchecked")
	public GetConsolidationResponse getConsolidation(getConsolidationParameter q) {
		String spName = "up_wa_GetConsolidation";
		List<Object> params = new ArrayList<Object>();
		GetConsolidationResponse result = new GetConsolidationResponse();
		params.add(q.getFromDate());
		params.add(q.getToDate());

		List<Object> _result = jdbcHelper.executeSP(spName, params, Consolidation.class);
		
		result.setConsolidation((List<Consolidation>) _result.get(0));
		return result;
	}
	*/

	public List<ShipMethodResponse> getConsolidationShipMethod() {
		// Get shipMethods
		List<ShipMethod> shipMethods =
				shipMethodRepository.findByActiveAndIdIn(
						true,
						Arrays.asList(
								3, // UPS
								9 // Fedex
						)
				);

		// Parse to shipMethodResponses
		return CollectionUtils.isEmpty(shipMethods) ? new ArrayList<>() :
				shipMethods
					.stream()
					.map(shipMethod -> ShipMethodResponse.builder()
							.shipMethodId(shipMethod.getId())
							.shipMethodName(shipMethod.getShipMethodName())
							.build())
					.collect(Collectors.toList());
	}
}
