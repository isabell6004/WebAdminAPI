package net.fashiongo.webadmin.controller;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.model.payment.CodeCreditCardType;
import net.fashiongo.webadmin.data.model.payment.GetPaymentRecoveryListParameter;
import net.fashiongo.webadmin.data.model.payment.response.GetAllSavedCreditCardInfoResponse;
import net.fashiongo.webadmin.data.model.payment.response.GetPaymentRecoveryResponse;
import net.fashiongo.webadmin.data.model.payment.response.GetPendingPaymentTransactionResponse;
import net.fashiongo.webadmin.model.pojo.common.ResultCode;
import net.fashiongo.webadmin.model.pojo.payment.parameter.*;
import net.fashiongo.webadmin.model.pojo.payment.response.GetPaymentStatusListResponse;
import net.fashiongo.webadmin.model.pojo.payment.response.GetPayoutHistoryResponse;
import net.fashiongo.webadmin.model.primary.CardStatus;
import net.fashiongo.webadmin.service.WAPaymentService;
import net.fashiongo.webadmin.service.renewal.RenewalWAPaymentService;
import net.fashiongo.webadmin.utility.JsonResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/payment", produces = "application/json")
public class WAPaymentController {

    @Autowired
    private WAPaymentService waPaymentService;

    @Autowired
    private RenewalWAPaymentService renewalWAPaymentService;

    @PostMapping(value = "getPaymentStatusSearchOption")
    public JsonResponse<net.fashiongo.webadmin.data.model.payment.response.GetPaymentStatusSearchOptionResponse> getPaymentStatusSearchOption() {
        net.fashiongo.webadmin.data.model.payment.response.GetPaymentStatusSearchOptionResponse paymentStatusSearchOption = renewalWAPaymentService.getPaymentStatusSearchOption();
        return new JsonResponse<>(true, null, 0, paymentStatusSearchOption);
    }

    @PostMapping(value = "getPaymentStatusList")
    public JsonResponse<GetPaymentStatusListResponse> getPaymentStatusList(
            @Valid @RequestBody GetPaymentStatusListParameter parameters) {
        GetPaymentStatusListResponse result = renewalWAPaymentService.getPaymentStatusList(parameters);
        return new JsonResponse<>(true, null, 0, result);
    }

    @PostMapping(value = "getpendingpaymenttransaction")
    public JsonResponse<GetPendingPaymentTransactionResponse> getPendingPaymentTransaction(
            @Valid @RequestBody GetPendingPaymentTransactionParameter parameters) {
        GetPendingPaymentTransactionResponse result = renewalWAPaymentService.getPendingPaymentTransaction(parameters);
        return new JsonResponse<>(true, null, 0, result);
    }

    @PostMapping(value = "getCreditCardType")
    public JsonResponse<List<net.fashiongo.webadmin.data.model.payment.CodeCreditCardType>> getCreditCardType() {
        List<CodeCreditCardType> result = renewalWAPaymentService.getCreditCardType();
        return new JsonResponse<>(true, null, 0, result);
    }

    @PostMapping(value = "getCreditCardStatus")
    public JsonResponse<List<CardStatus>> getCreditCardStatus() {
        List<CardStatus> result = waPaymentService.getCreditCardStatus();
        return new JsonResponse<>(true, null, 0, result);
    }

    @PostMapping(value = "getAllSavedCreditCardInfo")
    public JsonResponse<net.fashiongo.webadmin.data.model.payment.response.GetAllSavedCreditCardInfoResponse> getAllSavedCreditCardInfo(
            @Valid @RequestBody GetAllSavedCreditCardInfoParameter param) {
        GetAllSavedCreditCardInfoResponse result = renewalWAPaymentService.getAllSavedCreditCardInfo(param);
        return JsonResponse.success(result);
    }

    @PostMapping(value = "setrestorependingpaymenttransaction")
    public JsonResponse<String> setRestorePendingPaymentTransaction(
            @Valid @RequestBody SetRestorePendingPaymentTransactionParameter param) {
        ResultCode result = waPaymentService.setRestorePendingPaymentTransaction(param);
        return new JsonResponse<>(result.getSuccess(), result.getResultMsg(), result.getResultCode(), null);
    }

    @PostMapping(value = "getpayouthistory")
    public JsonResponse<GetPayoutHistoryResponse> getPayoutHistory(
            @Valid @RequestBody GetPayoutHistoryParameter param) {
        GetPayoutHistoryResponse result = waPaymentService.getPayoutHistory(param);
        return new JsonResponse<>(true, null, 0, result);
    }
    
    // stripe payment failure
	@PostMapping(value = "/paymentrecovery")
	public JsonResponse<?> setPaymentrecovery(@RequestBody PaymentRecovery paymentrecovery) {	
		try {
			return new JsonResponse<>(true, null,
					renewalWAPaymentService.setPaymentrecovery(paymentrecovery));
		} catch (Exception e) {
			//logger.error("PaymentController.setPaymentrecovery()", e);
			return new JsonResponse<>(false, "PaymentController.setPaymentrecovery()", null);
		}
	}	
	
	@PostMapping(value = "getpaymentrecovery")
	public JsonResponse<GetPaymentRecoveryResponse> getPaymentRecovery(@RequestBody GetPaymentRecoveryListParameter param) {
       
		JsonResponse<GetPaymentRecoveryResponse> response = new JsonResponse<GetPaymentRecoveryResponse>(true, null, null);
		GetPaymentRecoveryResponse result = renewalWAPaymentService.getPaymentRecoveryList(param);
		
		response.setSuccess(true);
		response.setData(result);
		return response;
	}
}
