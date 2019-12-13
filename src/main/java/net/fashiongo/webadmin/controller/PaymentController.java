package net.fashiongo.webadmin.controller;

import java.time.LocalDateTime;
import java.util.List;

import net.fashiongo.webadmin.data.model.payment.GetPaymentAccountInfoParameter;
import net.fashiongo.webadmin.data.model.payment.SetPaymentAccountBankParameter;
import net.fashiongo.webadmin.data.model.payment.SetPaymentAccountInfoParameter;
import net.fashiongo.webadmin.model.pojo.payment.parameter.PaymentRequest;
import net.fashiongo.webadmin.model.pojo.payment.response.PaymentStatusResponse;
import net.fashiongo.webadmin.model.pojo.payment.response.PaymentTransactionResponse;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import net.fashiongo.webadmin.utility.JsonResponse;
import net.fashiongo.webadmin.dao.primary.DisputeDocumentRepository;
import net.fashiongo.webadmin.model.fgpay.Dispute;
import net.fashiongo.webadmin.model.fgpay.DisputeDetail;
import net.fashiongo.webadmin.model.pojo.common.PagedResult;
import net.fashiongo.webadmin.model.pojo.payment.parameter.QueryParam;
import net.fashiongo.webadmin.model.primary.DisputeDocument;
import net.fashiongo.webadmin.service.PaymentService;

/**
 * @author Brian
 *
 */
@RestController
@RequestMapping(value = "/payment", produces = "application/json")
public class PaymentController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private DisputeDocumentRepository disputeDocumentRepository;
	
	@Autowired
	private PaymentService paymentService;
	
	@GetMapping("/disputes")
	public JsonResponse<PagedResult<Dispute>> getDisputes(@ModelAttribute QueryParam queryParam) {
		JsonResponse<PagedResult<Dispute>> response = new JsonResponse<>(false, null, null);
		
		try {
			PagedResult<Dispute> disputes = paymentService.getDisputes(queryParam);
			response.setData(disputes);
			response.setSuccess(true);
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setSuccess(false);
			logger.error("Error: getDisputes()", e);
		}
		return response;
	}
	
	@GetMapping("/dispute/{disputeId}")
	public JsonResponse<?> getDisputeDetail(@PathVariable("disputeId") String disputeId, @ModelAttribute QueryParam queryParam) {
		logger.debug("getDisputeDetail() orderId: {}", disputeId);
		boolean success = false;
		JsonResponse<Object> response = new JsonResponse<>(false, null, null);
		Integer orderType = queryParam.getTypeId();
		Integer wid = queryParam.getWid();
		try {
			DisputeDetail dispute = paymentService.getDispute(disputeId, orderType);
			if(dispute.getHeader().getWholesalerId().equals(wid)) {
				success = true;
				response.setData(dispute);
			} else {
				response.setMessage("wholesalerId does not match with this dispute information");
			}
			response.setSuccess(success);
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setSuccess(success);
			logger.error("Error: getDisputeDetail()", e);
		}
		
		return response;
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/dispute/save")
	public JsonResponse<?> saveDisputeDocuments(@RequestBody DisputeDocument disputeDocument) {
		logger.info("saveDisputeDocuments(): {}", disputeDocument.toString());
		JsonResponse<Object> response = new JsonResponse<>(false, null, null);
		try {
			String user = disputeDocument.getUserName();
			DisputeDocument doc = disputeDocumentRepository.findOneByDisputeId(disputeDocument.getDisputeId());
			if(doc != null) {
				doc.setModifiedBy(user);
				doc.setModifiedOn(LocalDateTime.now());
			} else {
				doc = new DisputeDocument();
				doc.setDisputeId(disputeDocument.getDisputeId());
				doc.setCreatedBy(user);
				doc.setCreatedOn(LocalDateTime.now());
			}
			doc.setFileName1(disputeDocument.getFileName1());
			doc.setFileName2(disputeDocument.getFileName2());
			doc.setFileName3(disputeDocument.getFileName3());
			doc.setFileName4(disputeDocument.getFileName4());
			doc.setFileName5(disputeDocument.getFileName5());
			doc.setFileName6(disputeDocument.getFileName6());
			doc.setFileName7(disputeDocument.getFileName7());
			doc.setFileName8(disputeDocument.getFileName8());
			if(disputeDocument.getIsAccepted() != null && disputeDocument.getIsAccepted()) {
				doc.setIsAccepted(disputeDocument.getIsAccepted());
				doc.setAcceptedOn(LocalDateTime.now());
				doc.setAcceptedBy(user);
				JSONObject json = new JSONObject();
				json.put("disputeId", disputeDocument.getDisputeId());
				json.put("acceptedOn", LocalDateTime.now().toString());
				json.put("acceptedBy", user);
				//serviceJsonClient.post("/email/sendDisputeAcceptNotice/", json.toString());
			}
			disputeDocumentRepository.save(doc);
			response.setData(doc);
			response.setSuccess(true);
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setSuccess(false);
			
		}
		return response;
	}

	@PostMapping(value = "getpaymentaccountinfo")
	public JsonResponse<?> getpaymentaccountinfo(@RequestBody GetPaymentAccountInfoParameter param) {
		JsonResponse<?> response = new JsonResponse<>(false, null, null);
		Integer wid = param.getWid();

		try {
			response = paymentService.getPaymentAccountInfo(wid);
			response.setSuccess(true);
		} catch (Exception ex) {
			logger.error("PaymentController.getpaymentaccountinfo()", ex);
			response.setMessage(ex.getMessage());
		}

		return response;
	}

	@PostMapping(value = "/setpaymentaccountinfo")
	public JsonResponse<?> setPaymentAccountInfo(@RequestBody SetPaymentAccountInfoParameter param) {
		try {
			return paymentService.setPaymentAccountInfo(param);
		} catch (Exception e) {
			logger.error("PaymentController.setPaymentAccountInfo()", e);
			return new JsonResponse<>(false, e.getMessage(), null);
		}
	}

	@PostMapping(value = "/setpaymentaccountbank")
	public JsonResponse<?> setPaymentAccountBank(@RequestBody SetPaymentAccountBankParameter param) {
		try {
			return paymentService.setPaymentAccountBank(param);
		} catch (Exception e) {
			logger.error("PaymentController.setPaymentAccountInfo()", e);
			return new JsonResponse<>(false, e.getMessage(), null);
		}
	}

	@GetMapping(value = "/creditCardStatus")
	public JsonResponse<PaymentStatusResponse> getCreditCardStatus(
			@RequestParam(value = "creditCardId") Integer creditCardId,
			@RequestParam(value = "consolidationId") Integer consolidationId) {
		try {
			return new JsonResponse<>(true, null,
					paymentService.getCreditCardStatus(creditCardId, consolidationId));
		} catch (Exception e) {
			logger.error("PaymentController.getCreditCardStatus()", e);
			return new JsonResponse<>(false, e.getMessage(), null);
		}
	}

	@PostMapping(value = "/sale")
	public JsonResponse<?> setSale(@RequestBody PaymentRequest paymentRequest) {
		try {
			return paymentService.setSale(paymentRequest);
		} catch (Exception e) {
			logger.error("PaymentController.setSale()", e);
			return new JsonResponse<>(false, e.getMessage(), null);
		}
	}

	@GetMapping(value = "/transactions")
	public JsonResponse<List<PaymentTransactionResponse>> getTransactions(
			@RequestParam(value = "consolidationId") Integer consolidationId) {
		try {
			return new JsonResponse<>(true, null,
					paymentService.getTransactions(consolidationId));
		} catch (Exception e) {
			logger.error("PaymentController.getTransactions()", e);
			return new JsonResponse<>(false, e.getMessage(), null);
		}
	}

	@PostMapping(value = "/refund")
	public JsonResponse<?> setRefund(@RequestBody PaymentRequest paymentRequest) {
		try {
			return paymentService.setRefund(paymentRequest);
		} catch (Exception e) {
			logger.error("PaymentController.setSale()", e);
			return new JsonResponse<>(false, e.getMessage(), null);
		}
	}
}
