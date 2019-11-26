package net.fashiongo.webadmin.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import net.fashiongo.webadmin.model.pojo.consolidation.Consolidation;
import net.fashiongo.webadmin.model.pojo.consolidation.ConsolidationDetail;
import net.fashiongo.webadmin.model.pojo.consolidation.ConsolidationDetailList;
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
		String spName = "up_wa_GetConsolidationSummary_v1";
		List<Object> params = new ArrayList<>();
		GetConsolidationSummaryResponse result = new GetConsolidationSummaryResponse();

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime lastDayOfMonth = LocalDateTime.of(now.getYear(), now.getMonth(), 1, 0, 0, 0).minusDays(1).minusSeconds(1);
        
        params.add(
				lastDayOfMonth
				.minusMonths(q.getPeriodType() == null ? 0 : q.getPeriodType()).toString());
		params.add(lastDayOfMonth.toString());
        
		List<Object> _result = jdbcHelper.executeSP(spName, params, ConsolidationSummary.class);
		
		result.setOrderConsolidationSummary((List<ConsolidationSummary>) _result.get(0));
		
		return result;
	}

	@SuppressWarnings("unchecked")
	public GetConsolidationResponse getConsolidationList(GetConsolidationParameter q) {
		String spName = "up_wa_GetConsolidation_v2";
		List<Object> params = new ArrayList<>();
		GetConsolidationResponse result = new GetConsolidationResponse();
		
		int iShipped = q.getBshipped() == null ? 2 : q.getBshipped();
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
        
		params.add(q.getPageNum());
		params.add(q.getPageSize());
		params.add(q.getDtFrom());
		params.add(q.getDtTo() != null ? q.getDtTo().plusDays(1) : q.getDtTo());
		params.add(q.getDateColumn());
		params.add(bShipped);
		params.add(q.getPaymentStatus());		
		params.add(StringUtils.isEmpty(q.getWn()) ? null : q.getWn());
		params.add(StringUtils.isEmpty(q.getRn()) ? null : q.getRn());
		params.add(StringUtils.isEmpty(q.getPn()) ? null : q.getPn());
		params.add(StringUtils.isEmpty(q.getCn()) ? null : q.getCn());
		params.add(StringUtils.isEmpty(q.getTn()) ? null : q.getTn());
		params.add(q.getOrderBy());
		
		List<Object> _result = jdbcHelper.executeSP(spName, params, TotalCount.class, Consolidation.class);

		result.setTotalCount((List<TotalCount>) _result.get(0));
		result.setConsolidation((List<Consolidation>) _result.get(1));
		
		return result;
	}

	@SuppressWarnings("unchecked")
	public GetConsolidationDetailResponse getConsolidationDetailList(GetConsolidationDetailParameter q) {
		String spName = "up_wa_GetConsolidationDetail";
		List<Object> params = new ArrayList<>();
		GetConsolidationDetailResponse result = new GetConsolidationDetailResponse();
        
		params.add(q.getConsolidationId());
		
		List<Object> _result = jdbcHelper.executeSP(spName, params, ConsolidationDetail.class, ConsolidationDetailList.class);
		
		result.setConsolidationDetail((List<ConsolidationDetail>) _result.get(0));
		result.setConsolidationDetailList((List<ConsolidationDetailList>) _result.get(1));
		
		return result;
	}
	
}
