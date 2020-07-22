package net.fashiongo.webadmin.service.renewal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.fashiongo.webadmin.dao.primary.OrderRepository;
import net.fashiongo.webadmin.dao.primary.OrderStatusChangeLogRepository;
import net.fashiongo.webadmin.data.entity.primary.ConsolidationOrdersEntity;
import net.fashiongo.webadmin.data.entity.primary.ConsolidationShipMethodEntity;
import net.fashiongo.webadmin.data.entity.primary.NewsEntity;
import net.fashiongo.webadmin.data.entity.primary.ShipMethodEntity;
import net.fashiongo.webadmin.data.entity.primary.OrderStatusChangeLogEntity;
import net.fashiongo.webadmin.data.entity.primary.WholeShipMethodEntity;
import net.fashiongo.webadmin.data.model.order.ConsolidationOrderSummary;
import net.fashiongo.webadmin.data.model.order.GetPrintPoUrlParameter2;
import net.fashiongo.webadmin.data.repository.primary.*;
import net.fashiongo.webadmin.model.pojo.order.parameter.GetPrintPoUrlParameter;
import net.fashiongo.webadmin.utility.HttpClient;
import net.fashiongo.webadmin.utility.JsonResponse;
import net.fashiongo.webadmin.utility.Utility;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
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

	@Autowired
	private WholeShipMethodEntityRepository wholeShipMethodEntityRepository;

	@Autowired
	private OrderStatusChangeLogEntityRepository orderStatusChangeLogEntityRepository;

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

	public JsonResponse getWebRequest3(GetPrintPoUrlParameter2 parameters) throws JsonProcessingException {
		int orderId = parameters.getOrderid();
		String orderSessionGUID = ordersEntityRepository.findById(parameters.getOrderid()).map(ordersEntity -> ordersEntity.getOrderSessionGUID()).orElse("");

		String url = "/v2/" + parameters.getResulttype() + "/po/" + orderSessionGUID + "/" + orderId + "?t=" + parameters.getT() + "&forPdf=" + parameters.getForpdf() + "&withImage=" + parameters.getWithimage() + "&withVendorStyleNo=" + parameters.getWithvendorstyleno();
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

	public JsonResponse getPrintMergePOUrl2(GetPrintPoUrlParameter2 parameters) {
		int orderId = parameters.getOrderid();

		String orderSessionGUID = mergeOrdersEntityRepository.getMergeOrderWholesalerGuid(orderId).orElse(null);

		String url = "/v2/" + parameters.getResulttype() + "/merged-po/" + orderSessionGUID + "/" + orderId + "?t=" + parameters.getT() + "&forPdf=" + parameters.getForpdf() + "&withImage=" + parameters.getWithimage() + "&withVendorStyleNo=" + parameters.getWithvendorstyleno();
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

					ConsolidationShipMethodEntity consolidationShipMethodEntity = consolidationShipMethodEntityRepository.findOneByIdAndActive(defaultConsolidationShipMethodId, true)
							.orElseGet(() -> {
								ConsolidationShipMethodEntity shipMethodEntity = new ConsolidationShipMethodEntity();
								shipMethodEntity.setConsolidationShipMethodID(defaultConsolidationShipMethodId);
								shipMethodEntity.setActive(true);
								shipMethodEntity.setShipMethodID(-1);
								return shipMethodEntity;
							});

					ShipMethodEntity shipMethodEntity = shipMethodEntityRepository.findById(consolidationShipMethodEntity.getShipMethodID()).orElseGet(() -> {
						ShipMethodEntity entity = new ShipMethodEntity();
						return entity;
					});

					if(shipMethodEntity != null ) {

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
					}
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

	public JsonResponse setRemoveConsolidationDetail(List<Integer> orderIds, String sessionUserName) {
		boolean bSuccess = false;
		int retCode = 1;
		String retMsg = "Removed successfully!";

		try {
			TransactionTemplate tx = new TransactionTemplate(platformTransactionManager);

			bSuccess = tx.execute(transactionStatus -> {

				orderIds.stream()
						.forEach(orderId -> {
							LocalDateTime NOW = LocalDateTime.now();

							ordersEntityRepository.findById(orderId).ifPresent(ordersEntity -> {

								WholeShipMethodEntity firstWithShipMethod = wholeShipMethodEntityRepository.findFirstWithShipMethod(true, ordersEntity.getWholeSalerID(), true);

								if(firstWithShipMethod != null) {
									ordersEntity.setShipMethod(firstWithShipMethod.getShipMethod().getShipMethodName());
									ordersEntity.setShipMethodID(firstWithShipMethod.getShipMethod().getShipMethodID());
								}

								ordersEntity.setIsConsolidated(false);
								ordersEntity.setModifiedBy(sessionUserName);
								ordersEntity.setModifiedOn(Timestamp.valueOf(NOW));
								ordersEntityRepository.save(ordersEntity);

								Integer consolidationID = ordersEntity.getConsolidationID();

								if(consolidationID != null) {
									//NEWS CHECK
									consolidationOrdersEntityRepository.findByConsolidationId(consolidationID)
											.stream()
											.findFirst()
											.ifPresent(consolidationOrdersEntity -> {
												Integer dropReferenceID = consolidationOrdersEntity.getDropReferenceID();

												if(dropReferenceID != null) {

													newsEntityRepository.findById(dropReferenceID).ifPresent(newsEntity -> {
														newsEntity.setActive(false);
														newsEntity.setShowBanner(false);
														newsEntity.setLastModifiedDateTime(NOW);
														newsEntityRepository.save(newsEntity);
													});
												}
											});

									if(ordersEntityRepository.hasOrderByConsolidationIdAndIsConsolidate(consolidationID,true) == false) {
										orderPaymentStatusEntityRepository.deleteByReferenceIdAndIsOrder(consolidationID,0);
									}

									setConsolidationSum(consolidationID,sessionUserName);

									checkConsolidationPaymentStatus(consolidationID);
								}

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

	private void checkConsolidationPaymentStatus(Integer consolidationId) {
		LocalDateTime modifiedOn = LocalDateTime.now();

		if (consolidationId == null || consolidationId == 0){
			return;
		}

		orderPaymentStatusEntityRepository.findOneByReferenceIDAndIsOrder(consolidationId, 0)
				.ifPresent(orderPaymentStatusEntity -> {
					if(isAllConsolidationsInvalid(consolidationId)){
						//Update the order payment status id to 999
						orderPaymentStatusEntity.setPrePaymentStatusID(orderPaymentStatusEntity.getPaymentStatusID());
						orderPaymentStatusEntity.setPaymentStatusID(999);
						orderPaymentStatusEntity.setModifiedOn(Timestamp.valueOf(modifiedOn));
						orderPaymentStatusEntity.setModifiedBy(Utility.getUsername());
						orderPaymentStatusEntityRepository.save(orderPaymentStatusEntity);
					}
				});
	}

	private boolean isAllConsolidationsInvalid(Integer consolidationId) {
		long totalConsolidatedOrders = ordersEntityRepository.countByConsolidationID(consolidationId);
		long totalInvalidOrders = ordersEntityRepository.countInvalidConsolidationOrder(consolidationId);
		return totalConsolidatedOrders == totalInvalidOrders;
	}

	public JsonResponse setConsolidationDetailOrderStatus(Integer orderId, Integer consolidationId, Integer orderStatusId, String sessionUserName, String ipAddress) {
		boolean bSuccess = false;
		int retCode = 1;
		String retMsg = "success";

		try {

			TransactionTemplate tx = new TransactionTemplate(platformTransactionManager);

			bSuccess = tx.execute(transactionStatus -> {
				ordersEntityRepository.findById(orderId).ifPresent(ordersEntity -> {

					Integer currentOrderStatusID = ordersEntity.getOrderStatusID();
					LocalDateTime NOW = LocalDateTime.now();
					Timestamp timestampNow = Timestamp.valueOf(NOW);
					ordersEntity.setOrderStatusID(orderStatusId);

					switch (orderStatusId)
					{
						case 1: // Newly Created
							ordersEntity.setCheckOutDate(timestampNow);
							break;
						case 2: // Confirmed
							ordersEntity.setConfirmDate(timestampNow);
							break;
						case 4: // Fully Shipped
							ordersEntity.setShipDate(timestampNow);
							break;
						case 5: // Cancelled
							ordersEntity.setCancelDate(timestampNow);
							break;

						default:
							break;
					}
					ordersEntity.setModifiedOn(timestampNow);
					ordersEntity.setModifiedBy(sessionUserName);

					ordersEntityRepository.save(ordersEntity);

					//SetConsolidationSum(consolidationId);

					if(currentOrderStatusID != ordersEntity.getOrderStatusID()){

						OrderStatusChangeLogEntity orderStatusChangeLogEntity = new OrderStatusChangeLogEntity();

						orderStatusChangeLogEntity.setOrderId(orderId);
						orderStatusChangeLogEntity.setIsAdmin(false);
						orderStatusChangeLogEntity.setWholesalerId(0);
						orderStatusChangeLogEntity.setUserName(sessionUserName);
						orderStatusChangeLogEntity.setAccessIp(ipAddress);
						orderStatusChangeLogEntity.setOrderStatusId(ordersEntity.getOrderStatusID());
						orderStatusChangeLogEntity.setCreatedOn(NOW);
						orderStatusChangeLogEntity.setHappenedAt(4);
						orderStatusChangeLogEntity.setReferenceTypeId(1);

						orderStatusChangeLogEntityRepository.save(orderStatusChangeLogEntity);
					}

					if(orderStatusId == 5 && consolidationId != null){
						checkConsolidationPaymentStatus(consolidationId);
					}
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
}
