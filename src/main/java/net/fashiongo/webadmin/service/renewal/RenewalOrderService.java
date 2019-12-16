package net.fashiongo.webadmin.service.renewal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.fashiongo.webadmin.data.entity.primary.ConsolidationOrdersEntity;
import net.fashiongo.webadmin.data.entity.primary.NewsEntity;
import net.fashiongo.webadmin.data.model.order.ConsolidationOrderSummary;
import net.fashiongo.webadmin.data.model.order.GetPrintPoUrlParameter2;
import net.fashiongo.webadmin.data.repository.primary.*;
import net.fashiongo.webadmin.model.pojo.order.parameter.GetPrintPoUrlParameter;
import net.fashiongo.webadmin.utility.HttpClient;
import net.fashiongo.webadmin.utility.JsonResponse;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;
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

	@Autowired
	private ConsolidationShipMethodEntityRepository consolidationShipMethodEntityRepository;

	@Autowired
	private ShipMethodEntityRepository shipMethodEntityRepository;

	@Autowired
	private OrderPaymentStatusEntityRepository orderPaymentStatusEntityRepository;

	@Autowired
	private ConsolidationEntityRepository consolidationEntityRepository;

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

	public JsonResponse setConsolidationDetailMessage(Integer orderId, Integer consolidationId, Integer wholesalerId, String newsTitle, String newsContent, String lastUser, LocalDateTime fromDate, LocalDateTime toDate) {
		boolean bSuccess = false;
		int retCode = 1;
		String retMsg = "Send successfully!";

		try {

			TransactionTemplate tx = new TransactionTemplate(platformTransactionManager);

			bSuccess = tx.execute(transactionStatus -> {
				LocalDateTime now = LocalDateTime.now();
				// 1. insert news
				NewsEntity newsEntity = new NewsEntity();

				newsEntity.setWholeSalerId(wholesalerId);
				newsEntity.setNewsTitle(newsTitle);
				newsEntity.setNewsContent(newsContent);
				newsEntity.setStartingDate(fromDate);
				newsEntity.setFromDate(fromDate);
				newsEntity.setToDate(toDate);
				newsEntity.setShowBanner(true);
				newsEntity.setNewsType("C");
				newsEntity.setActive(true);
				newsEntity.setLastModifiedDateTime(now);
				newsEntity.setLastUser(lastUser);

				newsEntity = newsEntityRepository.save(newsEntity);

				Integer newsId = newsEntity.getNewsId();

				// 2. update order consolidation status
				ConsolidationOrdersEntity consolidationOrders = consolidationOrdersEntityRepository.findById(orderId).orElseGet(() -> {
					ConsolidationOrdersEntity consolidationOrdersEntity = new ConsolidationOrdersEntity();
					consolidationOrdersEntity.setOrderID(orderId);
					consolidationOrdersEntity.setConsolidationID(consolidationId);
					return consolidationOrdersEntity;
				});

				consolidationOrders.setNotifiedBy(lastUser);
				consolidationOrders.setNotifiedOn(Timestamp.valueOf(now));

				if (consolidationOrders.getDropReferenceID() != null) {

					newsEntityRepository.findById(consolidationOrders.getDropReferenceID())
							.ifPresent(news -> {
								news.setActive(false);
								news.setLastModifiedDateTime(now);
								newsEntityRepository.save(news);
							});
				}

				consolidationOrders.setDropReferenceID(newsId);

				consolidationOrdersEntityRepository.save(consolidationOrders);
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

	public JsonResponse setUndoRemoveConsolidationDetail(Integer orderId, String sessionUserName) {
		boolean bSuccess = false;
		int retCode = 1;
		String retMsg = "Successfully restored the consolidation order!";

		try {

			TransactionTemplate tx = new TransactionTemplate(platformTransactionManager);
			LocalDateTime NOW = LocalDateTime.now();

			bSuccess = tx.execute(transactionStatus -> {

				ordersEntityRepository.findById(orderId).ifPresent(ordersEntity -> {

					Integer consolidationID = ordersEntity.getConsolidationID();

					//Restore default consolidation shipping method
					int defaultConsolidationShipMethodId = 10001;

					consolidationShipMethodEntityRepository.findOneByIdAndActive(defaultConsolidationShipMethodId,true)
							.map(consolidationShipMethodEntity -> shipMethodEntityRepository.findById(consolidationShipMethodEntity.getShipMethodID()).orElse(null))
							.ifPresent(shipMethodEntity -> {

								ordersEntity.setShipMethodID(shipMethodEntity.getShipMethodID());
								ordersEntity.setShipMethod(shipMethodEntity.getShipMethodName());
								ordersEntity.setIsConsolidated(true);
								ordersEntity.setModifiedOn(Timestamp.valueOf(NOW));
								ordersEntity.setModifiedBy(sessionUserName);

								ordersEntityRepository.save(ordersEntity);

								//NEWS CHECK
								consolidationOrdersEntityRepository.findByConsolidationId(consolidationID)
										.stream()
										.findFirst()
										.ifPresent(consolidationOrdersEntity -> {
											Integer dropReferenceID = consolidationOrdersEntity.getDropReferenceID();

											if(dropReferenceID != null) {

												newsEntityRepository.findById(dropReferenceID).ifPresent(newsEntity -> {
													newsEntity.setActive(true);
													newsEntity.setLastModifiedDateTime(NOW);
													newsEntityRepository.save(newsEntity);
												});
											}
										});

								setConsolidationSum(consolidationID,sessionUserName);

								orderPaymentStatusEntityRepository.findFirst(consolidationID,0,999)
										.ifPresent(orderPaymentStatusEntity -> {

											Integer prePaymentStatusID = orderPaymentStatusEntity.getPrePaymentStatusID();
											orderPaymentStatusEntity.setPrePaymentStatusID(999);
											orderPaymentStatusEntity.setModifiedBy(sessionUserName);
											orderPaymentStatusEntity.setModifiedOn(Timestamp.valueOf(NOW));
											orderPaymentStatusEntity.setPaymentStatusID(prePaymentStatusID == null ? 1 : prePaymentStatusID);
											orderPaymentStatusEntityRepository.save(orderPaymentStatusEntity);
										});
							});
				});
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

	private void setConsolidationSum(int consolidationId,String sessionUserId) {

		consolidationEntityRepository.findById(consolidationId)
				.ifPresent(consolidationEntity -> {

					ConsolidationOrderSummary consolidationOrderSummary = ordersEntityRepository.summaryByConsolidationId(consolidationId);
					if(consolidationOrderSummary != null) {
						consolidationEntity.setOrderCount(consolidationOrderSummary.getOrderCount().intValue());
						consolidationEntity.setTotalQty(consolidationOrderSummary.getSumTotalQty());
						consolidationEntity.setTotalAmount(consolidationOrderSummary.getSumTotalAmount());
					} else {
						consolidationEntity.setOrderCount(0);
						consolidationEntity.setTotalQty(0);
						consolidationEntity.setTotalAmount(BigDecimal.ZERO);
					}

					consolidationEntity.setModifiedBy(sessionUserId);
					consolidationEntity.setModifiedOn(LocalDateTime.now());

					consolidationEntityRepository.save(consolidationEntity);
				});
	}
}
