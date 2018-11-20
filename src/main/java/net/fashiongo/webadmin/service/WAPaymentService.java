package net.fashiongo.webadmin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.fashiongo.webadmin.model.pojo.payment.response.GetPaymentStatusSearchOptionResponse;
import net.fashiongo.webadmin.model.primary.PaymentStatus;
import net.fashiongo.webadmin.model.primary.VendorCompany;
import net.fashiongo.webadmin.utility.JsonResponse;

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
	 * @param 
	 * @return 
	 */
	public void getPaymentStatusList() {
		
		
	}
	
	/**
	 * GetPendingPaymentTransaction
	 * 
	 * @since 2018. 11. 20.
	 * @author Dahye
	 * @param 
	 * @return 
	 */
	public void getPendingPaymentTransaction() {
		
		
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
