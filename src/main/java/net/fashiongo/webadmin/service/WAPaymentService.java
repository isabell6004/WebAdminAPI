package net.fashiongo.webadmin.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.fashiongo.webadmin.dao.primary.EntityActionLogRepository;
import net.fashiongo.webadmin.dao.primary.OrderPaymentStatusRepository;
import net.fashiongo.webadmin.dao.primary.PaymentCreditCardRepository;
import net.fashiongo.webadmin.model.pojo.common.ResultCode;
import net.fashiongo.webadmin.model.pojo.message.Total;
import net.fashiongo.webadmin.model.pojo.payment.CreditCardInfo;
import net.fashiongo.webadmin.model.pojo.payment.CreditCardStatus;
import net.fashiongo.webadmin.model.pojo.payment.OrderPayment;
import net.fashiongo.webadmin.model.pojo.payment.PaymentStatusID;
import net.fashiongo.webadmin.model.pojo.payment.PaymentStatusList;
import net.fashiongo.webadmin.model.pojo.payment.PayoutHistory;
import net.fashiongo.webadmin.model.pojo.payment.TotalCount;
import net.fashiongo.webadmin.model.pojo.payment.parameter.GetAllSavedCreditCardInfoParameter;
import net.fashiongo.webadmin.model.pojo.payment.parameter.GetPaymentStatusListParameter;
import net.fashiongo.webadmin.model.pojo.payment.parameter.GetPayoutHistoryParameter;
import net.fashiongo.webadmin.model.pojo.payment.parameter.GetPendingPaymentTransactionParameter;
import net.fashiongo.webadmin.model.pojo.payment.parameter.SetRestorePendingPaymentTransactionParameter;
import net.fashiongo.webadmin.model.pojo.payment.response.GetAllSavedCreditCardInfoResponse;
import net.fashiongo.webadmin.model.pojo.payment.response.GetPaymentStatusListResponse;
import net.fashiongo.webadmin.model.pojo.payment.response.GetPaymentStatusSearchOptionResponse;
import net.fashiongo.webadmin.model.pojo.payment.response.GetPayoutHistoryResponse;
import net.fashiongo.webadmin.model.pojo.payment.response.GetPendingPaymentTransactionResponse;
import net.fashiongo.webadmin.model.primary.CardStatus;
import net.fashiongo.webadmin.model.primary.CodeCreditCardType;
import net.fashiongo.webadmin.model.primary.EntityActionLog;
import net.fashiongo.webadmin.model.primary.OrderPaymentStatus;
import net.fashiongo.webadmin.model.primary.PaymentCreditCard;
import net.fashiongo.webadmin.model.primary.PaymentStatus;
import net.fashiongo.webadmin.model.primary.VendorCompany;
import net.fashiongo.webadmin.utility.Utility;

/**
 * 
 * @author DAHYE
 *
 */
@Service
public class WAPaymentService extends ApiService {
	
	String sessionUserID = Utility.getUsername();
	
	@Autowired
	private OrderPaymentStatusRepository orderPaymentStatusRepository;
	@Autowired
	private EntityActionLogRepository entityActionLogRepository;
	@Autowired
	private PaymentCreditCardRepository paymentCreditCardRepository;
	/**
	 * 
	 * 
	 * @since 2018. 11. 01.
	 * @author Dahye
	 * @param 
	 * @return GetPaymentStatusSearchOptionResponse
	 */
	@SuppressWarnings("unchecked")
	public GetPaymentStatusSearchOptionResponse getPaymentStatusSearchOption() {
		GetPaymentStatusSearchOptionResponse result = new GetPaymentStatusSearchOptionResponse();
		String spName = "up_wa_pay_GetPaymentStatusSearchOption";
		List<Object> params = new ArrayList<Object>();
		List<Object> _results = jdbcHelper.executeSP(spName, params, VendorCompany.class, PaymentStatus.class);
		result.setVendorList((List<VendorCompany>) _results.get(0));
		result.setPaymentStatusList((List<PaymentStatus>) _results.get(1));
		return result;
	}
	
	
	/**
	 * getPaymentStatusList
	 * 
	 * @since 2018. 11. 20.
	 * @author Dahye
	 * @param GetPaymentStatusListParameter
	 * @return GetPaymentStatusListResponse
	 */
	@SuppressWarnings("unchecked")
	public GetPaymentStatusListResponse getPaymentStatusList(GetPaymentStatusListParameter param) {
		GetPaymentStatusListResponse result = new GetPaymentStatusListResponse();
		String spName = "up_wa_pay_GetPaymentStatusList";
		List<Object> params = new ArrayList<Object>();
		params.add(param.getPageNum());
		params.add(param.getPageSize());
		params.add(param.getWholeSalerID());
		params.add(param.getPaymentStatusID());
		params.add(param.getFromDate());
		params.add(param.getToDate());
		params.add(param.getPoNumber());
		params.add(param.getConsolidationID());
		params.add(param.getBuyerName());
		params.add(param.getTransactionType());
		params.add(param.getSearchSuccess());
		params.add(param.getOrderBy());
		List<Object> _results = jdbcHelper.executeSP(spName, params, PaymentStatusList.class, Total.class);
		result.setPaymentStatusList((List<PaymentStatusList>) _results.get(0));
		result.setTotal((List<Total>) _results.get(1));
		return result;
	}
	
	/**
	 * GetPendingPaymentTransaction
	 * 
	 * @since 2018. 11. 22.
	 * @author Dahye
	 * @param GetPendingPaymentTransactionParameter
	 * @return GetPendingPaymentTransactionResponse
	 */
	@SuppressWarnings("unchecked")
	public GetPendingPaymentTransactionResponse getPendingPaymentTransaction(GetPendingPaymentTransactionParameter param) {
		GetPendingPaymentTransactionResponse result = new GetPendingPaymentTransactionResponse();
		String spName = "up_wa_pay_GetPendingPaymentTransaction";
		List<Object> params = new ArrayList<Object>();
		params.add(param.getCreditcardid());
		List<Object> _results = jdbcHelper.executeSP(spName, params, CreditCardStatus.class, OrderPayment.class);
		result.setCreditCardStatusList((List<CreditCardStatus>) _results.get(0));
		result.setOrderPaymentStatusList((List<OrderPayment>) _results.get(1));
		return result;
	}
	
	/**
	 * GetCreditCardType
	 * 
	 * @since 2018. 11. 20.
	 * @author Dahye
	 * @param 
	 * @return CodeCreditCardType
	 */
	@SuppressWarnings("unchecked")
	public List<CodeCreditCardType> getCreditCardType() {
		List<CodeCreditCardType> result = new ArrayList<CodeCreditCardType>();
		String spName = "up_wa_Pay_GetCreditCardType";
		List<Object> params = new ArrayList<Object>();
		List<Object> _results = jdbcHelper.executeSP(spName, params, CodeCreditCardType.class);
		result = (List<CodeCreditCardType>) _results.get(0);
		return result;
	}
	
	/**
	 * getCreditCardStatus
	 * 
	 * @since 2018. 11. 20.
	 * @author Dahye
	 * @param 
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	public List<CardStatus> getCreditCardStatus() {
		List<CardStatus> result = new ArrayList<CardStatus>();
		String spName = "up_wa_Pay_GetCreditCardStatus";
		List<Object> params = new ArrayList<Object>();
		List<Object> _results = jdbcHelper.executeSP(spName, params, CardStatus.class);
		result = (List<CardStatus>) _results.get(0);
		return result;
	}
	
	/**
	 * getAllSavedCreditCardInfo
	 * 
	 * @since 2018. 11. 26.
	 * @author Dahye
	 * @param GetAllSavedCreditCardInfoParameter
	 * @return GetAllSavedCreditCardInfoResponse
	 */
	@SuppressWarnings("unchecked")
	public GetAllSavedCreditCardInfoResponse getAllSavedCreditCardInfo(GetAllSavedCreditCardInfoParameter param) {
		GetAllSavedCreditCardInfoResponse result = new GetAllSavedCreditCardInfoResponse();
		String spName = "up_wa_Pay_GetCreditCardList";
		List<Object> params = new ArrayList<Object>();
		params.add(param.getPageNum());
		params.add(param.getPageSize());
		params.add(param.getCardID());
		params.add(param.getDefaultCard());
		params.add(param.getCardTypeID());
		params.add(param.getCardStatusID());
		params.add(param.getBillingID());
		params.add(param.getCreditCountry());
		params.add(param.getCreditState());
		params.add(param.getBuyer());
		params.add(param.getReferenceID());
		params.add(param.getOrderBy());
		params.add(param.getOrderGubn());
		List<Object> _results = jdbcHelper.executeSP(spName, params, CreditCardInfo.class, TotalCount.class);
		result.setCreditCardInfo((List<CreditCardInfo>) _results.get(0));
		result.setTotalList((List<TotalCount>) _results.get(1));
		return result;		
	}
	
	/**
	 * SetRestorePendingPaymentTransaction
	 * 
	 * @since 2018. 11. 20.
	 * @author Dahye
	 * @param SetRestorePendingPaymentTransactionParameter
	 * @return 
	 */
	@Transactional("primaryTransactionManager")
	public ResultCode setRestorePendingPaymentTransaction(SetRestorePendingPaymentTransactionParameter param) {
		LocalDateTime modifiedOn = LocalDateTime.now();
		for(PaymentStatusID item: param.getPaymentStatusID()) {
			OrderPaymentStatus a = orderPaymentStatusRepository.findOneByOrderPaymentStatusID(item.getOrderPaymentStatusID());
			if(a.getOrderPaymentStatusID() > 0) {
				a.setPaymentStatusID(item.getPaymentStatusID());
				a.setPrePaymentStatusID(item.getPrePaymentStatusID());
				a.setModifiedBy(sessionUserID);
				a.setModifiedOn(modifiedOn);
				orderPaymentStatusRepository.save(a);
				
				EntityActionLog b = new EntityActionLog();
				b.setEntityTypeID(1);
				b.setEntityID(1);
				b.setActionID(7002);
				b.setActedOn(modifiedOn);
				b.setActedBy(sessionUserID);
				b.setRemark("order payment status restored");
				entityActionLogRepository.save(b);
			}
		}
		PaymentCreditCard c = paymentCreditCardRepository.findOneByCreditCardID(param.getCreditcardid());
		c.setCardStatusID(1);
		c.setModifiedBy(sessionUserID);
		c.setModifiedOn(modifiedOn);
		paymentCreditCardRepository.save(c);
		
		EntityActionLog d = new EntityActionLog();
		d.setEntityTypeID(1);
		d.setEntityID(param.getCreditcardid());
		d.setActionID(7001);
		d.setActedOn(modifiedOn);
		d.setActedBy(sessionUserID);
		d.setRemark("credit card status changed");
		entityActionLogRepository.save(d);
		
		return new ResultCode(true, 1, "Restore successfully!");
	}
	
	/**
	 * GetPayoutHistory
	 * 
	 * @since 2018. 11. 20.
	 * @author Dahye
	 * @param GetPayoutHistoryParameter
	 * @return GetPayoutHistoryResponse
	 */
	public GetPayoutHistoryResponse getPayoutHistory(GetPayoutHistoryParameter param) {
		GetPayoutHistoryResponse result = new GetPayoutHistoryResponse();
		String spName = "up_wa_pay_GetPayoutList";
		List<Object> params = new ArrayList<Object>();
		params.add(param.getPagenum());
		params.add(param.getPagesize());
		params.add(param.getWholesalerid());
		params.add(param.getFromdate());
		params.add(param.getTodate());
		params.add(param.getPayoutstatus());
		params.add(param.getPayoutschedule());
		params.add(param.getOrderby());
		List<Object> _results = jdbcHelper.executeSP(spName, params, Total.class, PayoutHistory.class);
		result.setTotal((List<Total>) _results.get(0));
		result.setPayoutList((List<PayoutHistory>) _results.get(1));
		return result;		
	}
}
