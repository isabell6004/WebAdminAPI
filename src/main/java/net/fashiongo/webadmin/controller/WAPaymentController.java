package net.fashiongo.webadmin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.fashiongo.webadmin.model.pojo.payment.response.GetPaymentStatusSearchOptionResponse;
import net.fashiongo.webadmin.service.WAPaymentService;
import net.fashiongo.webadmin.utility.JsonResponse;

/**
 * 
 * @author DAHYE
 *
 */
@RestController
@RequestMapping(value = "/payment", produces = "application/json")
public class WAPaymentController {
	@Autowired
	private WAPaymentService waPaymentService;
	
	/**
	 * getPaymentStatusSearchOption
	 * 
	 * @since 2018. 11. 20.
	 * @author Dahye
	 * @param 
	 * @return GetPaymentStatusSearchOptionResponse
	 */
	@RequestMapping(value = "getPaymentStatusSearchOption", method = RequestMethod.POST)
	public JsonResponse<GetPaymentStatusSearchOptionResponse> getPaymentStatusSearchOption() {
		GetPaymentStatusSearchOptionResponse result = waPaymentService.getPaymentStatusSearchOption();
		
		return new JsonResponse<GetPaymentStatusSearchOptionResponse>(true, null, 0, result);
	}
	
	/**
	 * getPaymentStatusList
	 * 
	 * @since 2018. 11. 20.
	 * @author Dahye
	 * @param 
	 * @return 
	 */
	@RequestMapping(value = "getPaymentStatusList", method = RequestMethod.POST)
	public JsonResponse<String> getPaymentStatusList() {
		
		return new JsonResponse<>();
	}
	
	/**
	 * GetPendingPaymentTransaction
	 * 
	 * @since 2018. 11. 20.
	 * @author Dahye
	 * @param 
	 * @return 
	 */
	@RequestMapping(value = "getpendingpaymenttransaction", method = RequestMethod.POST)
	public JsonResponse<String> getPendingPaymentTransaction() {
		
		return new JsonResponse<>();
	}
	
	/**
	 * GetCreditCardType
	 * 
	 * @since 2018. 11. 20.
	 * @author Dahye
	 * @param 
	 * @return 
	 */
	@RequestMapping(value = "getCreditCardType", method = RequestMethod.POST)
	public JsonResponse<String> getCreditCardType() {
		
		return new JsonResponse<>();
	}
	
	/**
	 * getCreditCardStatus
	 * 
	 * @since 2018. 11. 20.
	 * @author Dahye
	 * @param 
	 * @return 
	 */
	@RequestMapping(value = "getCreditCardStatus", method = RequestMethod.POST)
	public JsonResponse<String> getCreditCardStatus() {
		
		return new JsonResponse<>();
	}
	
	/**
	 * getAllSavedCreditCardInfo
	 * 
	 * @since 2018. 11. 20.
	 * @author Dahye
	 * @param 
	 * @return 
	 */
	@RequestMapping(value = "getAllSavedCreditCardInfo", method = RequestMethod.POST)
	public JsonResponse<String> getAllSavedCreditCardInfo() {
		
		return new JsonResponse<>();
	}
	
	/**
	 * SetRestorePendingPaymentTransaction
	 * 
	 * @since 2018. 11. 20.
	 * @author Dahye
	 * @param 
	 * @return 
	 */
	@RequestMapping(value = "setrestorependingpaymenttransaction", method = RequestMethod.POST)
	public JsonResponse<String> setRestorePendingPaymentTransaction() {
		
		return new JsonResponse<>();
	}
	
	/**
	 * GetPayoutHistory
	 * 
	 * @since 2018. 11. 20.
	 * @author Dahye
	 * @param 
	 * @return 
	 */
	@RequestMapping(value = "getpayouthistory", method = RequestMethod.POST)
	public JsonResponse<String> getPayoutHistory() {
		
		return new JsonResponse<>();
	}
}
