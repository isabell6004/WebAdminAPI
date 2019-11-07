package net.fashiongo.webadmin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import net.fashiongo.webadmin.model.pojo.consolidation.OrderConsolidationSummary;
import net.fashiongo.webadmin.model.pojo.consolidation.parameter.GetOrderConsolidationSummaryParameter;
import net.fashiongo.webadmin.model.pojo.consolidation.response.GetOrderConsolidationSummaryResponse;
import net.fashiongo.webadmin.utility.HttpClient;

@Service
public class ConsolidationService extends ApiService {
	@Autowired
	@Qualifier("serviceJsonClient")
	private HttpClient httpClient;

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
	
}
