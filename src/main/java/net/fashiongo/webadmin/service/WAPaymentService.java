package net.fashiongo.webadmin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import net.fashiongo.webadmin.model.pojo.message.Total;
import net.fashiongo.webadmin.model.pojo.payment.CreditCardStatus;
import net.fashiongo.webadmin.model.pojo.payment.OrderPaymentStatus;
import net.fashiongo.webadmin.model.pojo.payment.PaymentStatusList;
import net.fashiongo.webadmin.model.pojo.payment.parameter.GetPaymentStatusListParameter;
import net.fashiongo.webadmin.model.pojo.payment.parameter.GetPendingPaymentTransactionParameter;
import net.fashiongo.webadmin.model.pojo.payment.response.GetPaymentStatusListResponse;
import net.fashiongo.webadmin.model.pojo.payment.response.GetPaymentStatusSearchOptionResponse;
import net.fashiongo.webadmin.model.pojo.payment.response.GetPendingPaymentTransactionResponse;
import net.fashiongo.webadmin.model.primary.PaymentStatus;
import net.fashiongo.webadmin.model.primary.VendorCompany;

/**
 * 
 * @author DAHYE
 *
 */
@Service
public class WAPaymentService extends ApiService {
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
		List<Object> _results = jdbcHelper.executeSP(spName, params, CreditCardStatus.class, OrderPaymentStatus.class);
		result.setCreditCardStatusList((List<CreditCardStatus>) _results.get(0));
		result.setOrderPaymentStatusList((List<OrderPaymentStatus>) _results.get(1));
		return result;
	}
	
	/**
	 * GetCreditCardType
	 * 
	 * @since 2018. 11. 20.
	 * @author Dahye
	 * @param 
	 * @return 
	 */
	public void getCreditCardType() {
		
		
	}
	
	/**
	 * getCreditCardStatus
	 * 
	 * @since 2018. 11. 20.
	 * @author Dahye
	 * @param 
	 * @return 
	 */
	public void getCreditCardStatus() {
		
		
	}
	
	/**
	 * getAllSavedCreditCardInfo
	 * 
	 * @since 2018. 11. 20.
	 * @author Dahye
	 * @param 
	 * @return 
	 */
	public void getAllSavedCreditCardInfo() {
		
		
	}
	
	/**
	 * SetRestorePendingPaymentTransaction
	 * 
	 * @since 2018. 11. 20.
	 * @author Dahye
	 * @param 
	 * @return 
	 */
	public void setRestorePendingPaymentTransaction() {
		
		
	}
	
	/**
	 * GetPayoutHistory
	 * 
	 * @since 2018. 11. 20.
	 * @author Dahye
	 * @param 
	 * @return 
	 */
	public void getPayoutHistory() {
		
		
	}
}
