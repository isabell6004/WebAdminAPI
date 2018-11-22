package net.fashiongo.webadmin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.fashiongo.webadmin.model.pojo.common.ResultCode;
import net.fashiongo.webadmin.model.pojo.payment.parameter.GetPaymentStatusListParameter;
import net.fashiongo.webadmin.model.pojo.payment.parameter.GetPendingPaymentTransactionParameter;
import net.fashiongo.webadmin.model.pojo.payment.response.GetPaymentStatusListResponse;
import net.fashiongo.webadmin.model.pojo.payment.response.GetPaymentStatusSearchOptionResponse;
import net.fashiongo.webadmin.model.pojo.payment.response.GetPendingPaymentTransactionResponse;
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
	 * @param GetPaymentStatusListParameter
	 * @return GetPaymentStatusListResponse
	 */
	@RequestMapping(value = "getPaymentStatusList", method = RequestMethod.POST)
	public JsonResponse<GetPaymentStatusListResponse> getPaymentStatusList(@RequestBody GetPaymentStatusListParameter parameters) {
		GetPaymentStatusListResponse result = waPaymentService.getPaymentStatusList(parameters);
		return new JsonResponse<GetPaymentStatusListResponse>(true, null, 0, result);
	}
	
	/**
	 * GetPendingPaymentTransaction
	 * 
	 * @since 2018. 11. 22.
	 * @author Dahye
	 * @param GetPendingPaymentTransactionParameter
	 * @return 
	 */
	@RequestMapping(value = "getpendingpaymenttransaction", method = RequestMethod.POST)
	public JsonResponse<GetPendingPaymentTransactionResponse> getPendingPaymentTransaction(@RequestBody GetPendingPaymentTransactionParameter parameters) {
		GetPendingPaymentTransactionResponse result = waPaymentService.getPendingPaymentTransaction(parameters);
		return new JsonResponse<GetPendingPaymentTransactionResponse>(true, null, 0, result);
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
