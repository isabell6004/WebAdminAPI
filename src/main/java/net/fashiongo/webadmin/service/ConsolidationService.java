package net.fashiongo.webadmin.service;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import net.fashiongo.webadmin.model.pojo.consolidation.Consolidation;
import net.fashiongo.webadmin.model.pojo.consolidation.ConsolidationDetail;
import net.fashiongo.webadmin.model.pojo.consolidation.ConsolidationSummary;
import net.fashiongo.webadmin.model.pojo.consolidation.TotalCount;
import net.fashiongo.webadmin.model.pojo.consolidation.parameter.GetConsolidationDetailParameter;
import net.fashiongo.webadmin.model.pojo.consolidation.parameter.GetConsolidationParameter;
import net.fashiongo.webadmin.model.pojo.consolidation.parameter.GetConsolidationSummaryParameter;
import net.fashiongo.webadmin.model.pojo.consolidation.response.GetConsolidationDetailResponse;
import net.fashiongo.webadmin.model.pojo.consolidation.response.GetConsolidationResponse;
import net.fashiongo.webadmin.model.pojo.consolidation.response.GetConsolidationSummaryResponse;
import net.fashiongo.webadmin.utility.HttpClient;

@Service
public class ConsolidationService extends ApiService {
	@Autowired
	@Qualifier("serviceJsonClient")
	private HttpClient httpClient;

	@SuppressWarnings("unchecked")
	public GetConsolidationSummaryResponse getOrderConsolidationListSummary(GetConsolidationSummaryParameter q) {
		String spName = "up_wa_GetConsolidationSummary";
		List<Object> params = new ArrayList<Object>();
		GetConsolidationSummaryResponse result = new GetConsolidationSummaryResponse();

        LocalDateTime today = LocalDateTime.now();
        LocalDateTime lastDayOfMonth = new LocalDateTime(today.getYear(), today.getMonthOfYear(), 1, 0, 0).plusDays(-1);
        LocalDateTime lastMonth = new LocalDateTime(lastDayOfMonth.getYear(), lastDayOfMonth.getMonthOfYear(), 1, 0, 0);
        LocalDateTime firstDayOfMonth = lastMonth.plusMonths(-1 * q.getPeriodType());

        LocalDateTime dtFrom = firstDayOfMonth;
        LocalDateTime dtTo = lastDayOfMonth;
        
		params.add(dtFrom);
		params.add(dtTo);

		List<Object> _result = jdbcHelper.executeSP(spName, params, ConsolidationSummary.class);
		
		result.setOrderConsolidationSummary((List<ConsolidationSummary>) _result.get(0));
		
		return result;
	}

	@SuppressWarnings("unchecked")
	public GetConsolidationResponse getConsolidationList(GetConsolidationParameter q) {
		String spName = "up_wa_GetConsolidation_v1";
		List<Object> params = new ArrayList<Object>();
		GetConsolidationResponse result = new GetConsolidationResponse();
		
		Integer iShipped = q.getBshipped() == null ? 2 : q.getBshipped();
        Boolean bShipped;
        
        switch (iShipped)
        {
            case 1:
                bShipped = true;
                break;
            case 0:
                bShipped = false;
                break;
            default:
                bShipped = null;
                break;
        }
        
		params.add(q.getPeriodType());
		params.add(q.getPageNum());
		params.add(q.getPageSize());
		params.add(q.getDtFrom());
		params.add(q.getDtTo() != null ? q.getDtTo().plusDays(1) : q.getDtTo());
		params.add(q.getDateColumn());
		params.add(bShipped);
		params.add(q.getWn() == "" ? null : q.getWn());
		params.add(q.getRn() == "" ? null : q.getRn());
		params.add(q.getPn() == "" ? null : q.getPn());
		params.add(q.getCn() == "" ? null : q.getCn());
		params.add(q.getOrderBy());
		
		List<Object> _result = jdbcHelper.executeSP(spName, params, Consolidation.class);
		
		result.setConsolidation((List<Consolidation>) _result.get(0));
		result.setTotalCount((List<TotalCount>) _result.get(1));
		
		return result;
	}

	@SuppressWarnings("unchecked")
	public GetConsolidationDetailResponse getConsolidationDetailList(GetConsolidationDetailParameter q) {
		String spName = "up_wa_GetConsolidationDetail";
		List<Object> params = new ArrayList<Object>();
		GetConsolidationDetailResponse result = new GetConsolidationDetailResponse();
        
		params.add(q.getConsolidationId());
		
		List<Object> _result = jdbcHelper.executeSP(spName, params, Consolidation.class);
		
		result.setConsolidations((List<Consolidation>) _result.get(0));
		result.setConsolidationDetail((List<ConsolidationDetail>) _result.get(1));
		
		return result;
	}
	
}
