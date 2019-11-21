package net.fashiongo.webadmin.controller;

import java.time.LocalDateTime;

import net.fashiongo.webadmin.model.pojo.payment.parameter.PaymentSaleRequest;
import net.fashiongo.webadmin.model.pojo.payment.response.PaymentStatusResponse;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import net.fashiongo.common.JsonResponse;
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

		try {
			Integer orderType = queryParam.getTypeId();
			Integer wid = queryParam.getWid();
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
	public JsonResponse<Object> setSale(@RequestBody PaymentSaleRequest paymentSaleRequest) {
		try {
			return paymentService.setSale(paymentSaleRequest);
		} catch (Exception e) {
			logger.error("PaymentController.setSale()", e);
			return new JsonResponse<>(false, e.getMessage(), null);
		}
	}
}
