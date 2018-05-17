/**
 * 
 */
package net.fashiongo.webadmin.controller;

import java.time.LocalDateTime;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.fashiongo.common.JsonResponse;
import net.fashiongo.webadmin.common.PagedResult;
import net.fashiongo.webadmin.common.QueryParam;
import net.fashiongo.webadmin.common.Utility;
import net.fashiongo.webadmin.dao.fgpay.DisputeDocumentRepository;
import net.fashiongo.webadmin.model.fgpay.Dispute;
import net.fashiongo.webadmin.model.fgpay.DisputeDetail;
import net.fashiongo.webadmin.model.fgpay.DisputeDocument;
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
		PagedResult<Dispute> disputes = null;
		
		try {
			disputes = paymentService.getDisputes(queryParam);
			response.setData(disputes);
			response.setSuccess(true);
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setSuccess(false);
			logger.error("Error: getDisputes()", e);
		}
		return response;
	}
	
	@GetMapping("/dispute/{disputeId}/{orderType}")
	public JsonResponse<?> getDisputeDetail(@PathVariable("disputeId") String disputeId, @PathVariable("orderType") Integer orderType) {
		logger.debug("getDisputeDetail() orderId: {}", disputeId);
		
		JsonResponse<Object> response = new JsonResponse<>(false, null, null);
		DisputeDetail dispute = null;
		try {
			dispute = paymentService.getDispute(disputeId, orderType);
			response.setData(dispute);
			response.setSuccess(true);
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setSuccess(false);
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
			String user = Utility.getUsername();
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
}
