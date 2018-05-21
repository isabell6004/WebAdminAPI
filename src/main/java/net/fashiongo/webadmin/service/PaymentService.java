/**
 * 
 */
package net.fashiongo.webadmin.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.fashiongo.webadmin.common.jdbcHelper;
import net.fashiongo.webadmin.common.PagedResult;
import net.fashiongo.webadmin.common.QueryParam;
import net.fashiongo.webadmin.common.SingleValueResult;
import net.fashiongo.webadmin.common.Utility;
import net.fashiongo.webadmin.model.fgpay.Dispute;
import net.fashiongo.webadmin.model.fgpay.DisputeDetail;
import net.fashiongo.webadmin.model.fgpay.DisputeDetailInfo;
import net.fashiongo.webadmin.model.fgpay.DisputeDocumentInfo;
import net.fashiongo.webadmin.model.fgpay.DisputeHeaderInfo;
import net.fashiongo.webadmin.model.fgpay.DisputeMergeOrderInfo;


/**
 * @author Brian
 *
 */
@Component("paymentService")
public class PaymentService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	protected jdbcHelper jdbcHelper;
	
	@SuppressWarnings("unchecked")
	public PagedResult<Dispute> getDisputes(QueryParam q) {
		String spName = "up_wa_Get_DisputeList";
		List<Object> params = new ArrayList<Object>();
		params.add(q.getPn());
		params.add(q.getPs());
		params.add(q.getReason());
		params.add(q.getStatus());
		params.add(q.getCn());
		params.add(q.getPo());
		params.add(q.getInvoceNumber());
		params.add(q.getDf());
		params.add(q.getDt());
		params.add(q.getOrderBy());
		List<Object> _results = jdbcHelper.executeSP(spName, params, SingleValueResult.class, Dispute.class);
		List<SingleValueResult> rs1 = (List<SingleValueResult>) _results.get(0);
		List<Dispute> rs2 = (List<Dispute>) _results.get(1);
		
		PagedResult<Dispute> result = new PagedResult<>();
		result.setTotal(rs1.get(0));
		result.setPageNumber(q.getPn());
		result.setRecords(rs2);
		//PagedResult<Dispute> result = new PagedResult<>();
		return result;	
	}
	
	@SuppressWarnings("unchecked")
	public DisputeDetail getDispute(String disputeId, Integer orderType) {
		String spName = "up_wa_Get_DisputeDetail";
		List<Object> params = new ArrayList<Object>();
		params.add(disputeId);
		params.add(orderType);
		List<Object> _results = jdbcHelper.executeSP(spName, params, DisputeHeaderInfo.class, DisputeMergeOrderInfo.class, DisputeDetailInfo.class, DisputeDocumentInfo.class);
		List<DisputeHeaderInfo> header = (List<DisputeHeaderInfo>) _results.get(0);
		List<DisputeMergeOrderInfo> mergeInfo = (List<DisputeMergeOrderInfo>) _results.get(1);
		List<DisputeDetailInfo> detailInfo = (List<DisputeDetailInfo>) _results.get(2);
		List<DisputeDocumentInfo> documentInfo = (List<DisputeDocumentInfo>) _results.get(3);
		
		DisputeDetail result = new DisputeDetail();
		result.setHeader(header.get(0));
		result.setMergeOrders(mergeInfo);
		result.setDetailInfo(detailInfo.get(0));
		result.setDocumentInfo(documentInfo);
		return result;
	}
}
