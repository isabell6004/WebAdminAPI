package net.fashiongo.webadmin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
	 * @return CodeCreditCardType
	 */
	@RequestMapping(value = "getCreditCardType", method = RequestMethod.POST)
	public JsonResponse<List<CodeCreditCardType>> getCreditCardType() {
		List<CodeCreditCardType> result = waPaymentService.getCreditCardType();
		return new JsonResponse<List<CodeCreditCardType>>(true, null, 0, result);
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
	public JsonResponse<List<CardStatus>> getCreditCardStatus() {
		List<CardStatus> result = waPaymentService.getCreditCardStatus();
		return new JsonResponse<List<CardStatus>>(true, null, 0, result);
	}
	
	/**
	 * getAllSavedCreditCardInfo
	 * 
	 * @since 2018. 11. 26.
	 * @author Dahye
	 * @param GetAllSavedCreditCardInfoParameter
	 * @return GetAllSavedCreditCardInfoResponse
	 */
	@RequestMapping(value = "getAllSavedCreditCardInfo", method = RequestMethod.POST)
	public JsonResponse<GetAllSavedCreditCardInfoResponse> getAllSavedCreditCardInfo(@RequestBody GetAllSavedCreditCardInfoParameter param) {
		GetAllSavedCreditCardInfoResponse result = waPaymentService.getAllSavedCreditCardInfo(param);
		return new JsonResponse<GetAllSavedCreditCardInfoResponse>(true, null, 0, result);
	}
	
	/**
	 * SetRestorePendingPaymentTransaction
	 * 
	 * @since 2018. 11. 20.
	 * @author Dahye
	 * @param SetRestorePendingPaymentTransactionParameter
	 * @return 
	 */
	@RequestMapping(value = "setrestorependingpaymenttransaction", method = RequestMethod.POST)
	public JsonResponse<String> setRestorePendingPaymentTransaction(@RequestBody SetRestorePendingPaymentTransactionParameter param) {
		
		return new JsonResponse<>();
	}
	
	/**
	 * GetPayoutHistory
	 * 
	 * @since 2018. 11. 20.
	 * @author Dahye
	 * @param GetPayoutHistoryParameter
	 * @return GetPayoutHistoryResponse
	 */
	@RequestMapping(value = "getpayouthistory", method = RequestMethod.POST)
	public JsonResponse<GetPayoutHistoryResponse> getPayoutHistory(@RequestBody GetPayoutHistoryParameter param) {
		GetPayoutHistoryResponse result = waPaymentService.getPayoutHistory(param);
		return new JsonResponse<GetPayoutHistoryResponse>(true, null, 0, result);
	}
}
