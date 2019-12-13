package net.fashiongo.webadmin.service.renewal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.fashiongo.webadmin.data.entity.primary.ConsolidationOrdersEntity;
import net.fashiongo.webadmin.data.model.order.GetPrintPoUrlParameter2;
import net.fashiongo.webadmin.data.repository.primary.ConsolidationOrdersEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.MergeOrdersEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.NewsEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.OrdersEntityRepository;
import net.fashiongo.webadmin.model.pojo.order.parameter.GetPrintPoUrlParameter;
import net.fashiongo.webadmin.utility.HttpClient;
import net.fashiongo.webadmin.utility.JsonResponse;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class RenewalOrderService {

	@Autowired
	@Qualifier("serviceJsonClient")
	private HttpClient httpClient;

	@Autowired
	private MergeOrdersEntityRepository mergeOrdersEntityRepository;

	@Autowired
	private OrdersEntityRepository ordersEntityRepository;

	@Autowired
	@Qualifier("primaryTransactionManager")
	private PlatformTransactionManager platformTransactionManager;

	@Autowired
	private ConsolidationOrdersEntityRepository consolidationOrdersEntityRepository;

	@Autowired
	private NewsEntityRepository newsEntityRepository;

	/**
	 *
	 * Description Example
	 * @since 2018. 11. 23.
	 * @author Reo
	 * @param url
	 * @return
	 * @throws JsonProcessingException
	 */
	public JsonResponse getWebRequest(GetPrintPoUrlParameter parameters) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String url = "/" + parameters.getResultType() + "/po/" + parameters.getOrderSessionGUID() + "/" + parameters.getOids() + "?t=" + parameters.getT() + "&forPdf=" + parameters.getForPdf() + "&withImage=" + parameters.getWithImage() + "&withVendorStyleNo=" + parameters.getWithVendorStyleNo();
		JSONObject jsonObj = new JSONObject();
		JsonResponse<?> result = httpClient.get(url);

		return result;
	}

	public JsonResponse getWebRequest2(GetPrintPoUrlParameter2 parameters) throws JsonProcessingException {
		int orderId = parameters.getOrderid();
		String orderSessionGUID = ordersEntityRepository.findById(parameters.getOrderid()).map(ordersEntity -> ordersEntity.getOrderSessionGUID()).orElse("");

		String url = "/" + parameters.getResulttype() + "/po/" + orderSessionGUID + "/" + orderId + "?t=" + parameters.getT() + "&forPdf=" + parameters.getForpdf() + "&withImage=" + parameters.getWithimage() + "&withVendorStyleNo=" + parameters.getWithvendorstyleno();
		JsonResponse<?> result = httpClient.get(url);

		return result;
	}

	public JsonResponse getPrintMergePOUrl(GetPrintPoUrlParameter2 parameters) {
		int orderId = parameters.getOrderid();

		String orderSessionGUID = mergeOrdersEntityRepository.getMergeOrderWholesalerGuid(orderId).orElse(null);


		String url = "/" + parameters.getResulttype() + "/merged-po/" + orderSessionGUID + "/" + orderId + "?t=" + parameters.getT() + "&forPdf=" + parameters.getForpdf() + "&withImage=" + parameters.getWithimage() + "&withVendorStyleNo=" + parameters.getWithvendorstyleno();
		JsonResponse<?> result = httpClient.get(url);

		return result;
	}

	public JsonResponse setConsolidationDetailDroppedBy(Integer orderId, Integer consolidationId, String droppedBy, String receivedBy, LocalDateTime receivedOn, String sessionUserName) {
		boolean bSuccess = false;
		int retCode = 1;
		String retMsg = "Saved successfully!";

		try {

			TransactionTemplate tx = new TransactionTemplate(platformTransactionManager);

			bSuccess = tx.execute(transactionStatus -> {
				// 1. consolidation order update
				ConsolidationOrdersEntity consolidationOrders = consolidationOrdersEntityRepository.findById(orderId).orElseGet(() -> {
					ConsolidationOrdersEntity consolidationOrdersEntity = new ConsolidationOrdersEntity();
					consolidationOrdersEntity.setOrderID(orderId);
					consolidationOrdersEntity.setConsolidationID(consolidationId);
					return consolidationOrdersEntity;
				});

				consolidationOrders.setDroppedBy(droppedBy);
				consolidationOrders.setReceivedBy(sessionUserName);
				consolidationOrders.setReceivedOn(Optional.ofNullable(receivedOn).map(dateTime -> Timestamp.valueOf(dateTime)).orElse(null));

				consolidationOrdersEntityRepository.save(consolidationOrders);

				// 3. tblnews update (if droppedby is assigned, tblnews will be closed automatically)
				Integer newsId = consolidationOrders.getDropReferenceID();
				if(newsId != null) {

					newsEntityRepository.findById(newsId)
							.ifPresent(newsEntity -> {
								newsEntity.setActive(false);
								newsEntityRepository.save(newsEntity);
							});
				}

				return true;

			});
		} catch (Exception ex) {
			bSuccess = false;
			retCode = -1;
			retMsg = ex.getMessage();
		}

		JsonResponse result = new JsonResponse(bSuccess,retMsg,retCode,"");
		return result;
	}
}
