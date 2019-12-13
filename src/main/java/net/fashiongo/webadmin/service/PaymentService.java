package net.fashiongo.webadmin.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import net.fashiongo.webadmin.dao.primary.PaymentTransactionEntityRepository;
import net.fashiongo.webadmin.data.entity.primary.PaymentTransactionEntity;
import net.fashiongo.webadmin.model.pojo.payment.response.PaymentTransactionResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.fashiongo.webadmin.data.model.payment.SetPaymentAccountBankParameter;
import net.fashiongo.webadmin.data.model.payment.SetPaymentAccountInfoParameter;
import net.fashiongo.webadmin.utility.HttpClient;
import net.fashiongo.webadmin.utility.JsonResponse;
import net.fashiongo.webadmin.dao.primary.OrderPaymentStatusRepository;
import net.fashiongo.webadmin.dao.primary.PaymentCreditCardRepository;
import net.fashiongo.webadmin.dao.primary.PaymentStatusRepository;
import net.fashiongo.webadmin.model.pojo.payment.parameter.PaymentRequest;
import net.fashiongo.webadmin.model.pojo.payment.response.PaymentStatusResponse;
import net.fashiongo.webadmin.model.primary.CardStatus;
import net.fashiongo.webadmin.model.primary.OrderPaymentStatus;
import net.fashiongo.webadmin.model.primary.PaymentCreditCard;
import net.fashiongo.webadmin.model.primary.PaymentStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import net.fashiongo.webadmin.model.fgpay.Dispute;
import net.fashiongo.webadmin.model.fgpay.DisputeDetail;
import net.fashiongo.webadmin.model.fgpay.DisputeDetailInfo;
import net.fashiongo.webadmin.model.fgpay.DisputeDocumentInfo;
import net.fashiongo.webadmin.model.fgpay.DisputeHeaderInfo;
import net.fashiongo.webadmin.model.fgpay.DisputeMergeOrderInfo;
import net.fashiongo.webadmin.model.pojo.common.PagedResult;
import net.fashiongo.webadmin.model.pojo.common.SingleValueResult;
import net.fashiongo.webadmin.model.pojo.payment.parameter.QueryParam;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;


/**
 * @author Brian
 *
 */
@Component("paymentService")
public class PaymentService extends ApiService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired private OrderPaymentStatusRepository orderPaymentStatusRepository;
	@Autowired private PaymentCreditCardRepository paymentCreditCardRepository;
	@Autowired private PaymentStatusRepository paymentStatusRepository;
	@Autowired private PaymentTransactionEntityRepository paymentTransactionEntityRepository;
	@Autowired private WAPaymentService waPaymentService;

	@Autowired
	@Qualifier("paymentApiJsonClient")
	private HttpClient httpClient;

	@SuppressWarnings("unchecked")
	public PagedResult<Dispute> getDisputes(QueryParam q) {
		String spName = "up_wa_Get_DisputeList";
		List<Object> params = new ArrayList<>();
		params.add(q.getPn());
		params.add(q.getPs());
		params.add(q.getReason());
		params.add(q.getStatus());
		params.add(q.getCn());
		params.add(q.getPo());
		params.add(q.getInvoceNumber());
		params.add(q.getDf());
		params.add(q.getDt());
		params.add(q.getOrderBy());
		List<Object> _results = jdbcHelper.executeSP(spName, params, SingleValueResult.class, Dispute.class);
		List<SingleValueResult> rs1 = (List<SingleValueResult>) _results.get(0);
		List<Dispute> rs2 = (List<Dispute>) _results.get(1);
		
		PagedResult<Dispute> result = new PagedResult<>();
		result.setTotal(rs1.get(0));
		result.setPageNumber(q.getPn());
		result.setRecords(rs2);
		//PagedResult<Dispute> result = new PagedResult<>();
		return result;	
	}
	
	@SuppressWarnings("unchecked")
	public DisputeDetail getDispute(String disputeId, Integer orderType) {
		String spName = "up_wa_Get_DisputeDetail";
		List<Object> params = new ArrayList<>();
		params.add(disputeId);
		params.add(orderType);
		List<Object> _results = jdbcHelper.executeSP(spName, params, DisputeHeaderInfo.class, DisputeMergeOrderInfo.class, DisputeDetailInfo.class, DisputeDocumentInfo.class);
		List<DisputeHeaderInfo> header = (List<DisputeHeaderInfo>) _results.get(0);
		List<DisputeMergeOrderInfo> mergeInfo = (List<DisputeMergeOrderInfo>) _results.get(1);
		List<DisputeDetailInfo> detailInfo = (List<DisputeDetailInfo>) _results.get(2);
		List<DisputeDocumentInfo> documentInfo = (List<DisputeDocumentInfo>) _results.get(3);
		
		DisputeDetail result = new DisputeDetail();
		result.setHeader(header.get(0));
		result.setMergeOrders(mergeInfo);
		result.setDetailInfo(detailInfo.get(0));
		result.setDocumentInfo(documentInfo);
		return result;
	}

	public JsonResponse<?> getPaymentAccountInfo(Integer wid) {
		return (JsonResponse<?>) httpClient.get("/account/" + wid);
	}

	public JsonResponse<?> setSale(PaymentRequest request) throws JsonProcessingException {
		return (JsonResponse<?>) httpClient.post("/sale", new ObjectMapper().writeValueAsString(request));
	}

	public JsonResponse<?> setRefund(PaymentRequest request) throws Exception {
		if (request == null) {
			throw new Exception("No request");
		}

		BigDecimal refundAmount = request.getAmount();
		if (refundAmount == null || refundAmount.compareTo(BigDecimal.ZERO) <= 0) {
			throw new Exception("Refund amount must be greater than 0");
		}

		Integer consolidationId = request.getReferenceId();
		if (consolidationId == null) {
			throw new Exception("No consolidation is requested");
		}

		List<PaymentTransactionEntity> transactions =
				paymentTransactionEntityRepository.findByReferenceIDAndReferenceTypeIDOrderByTransactionIDDesc(
						consolidationId, 0/* 0 = Consolidation */);
		BigDecimal authCaptureAmount = getAuthCaptureAmount(transactions);
		if (authCaptureAmount == null) {
			throw new Exception("No auth & capture amount exists");
		}
		if (authCaptureAmount.compareTo(refundAmount) < 0) {
			throw new Exception("Refund amount must be less than the shipping amount");
		}

		return (JsonResponse<?>) httpClient.post("refund", new ObjectMapper().writeValueAsString(request));
	}

	public PaymentStatusResponse getCreditCardStatus(Integer creditCardId, Integer consolidationId) throws Exception {
		if (creditCardId == null) throw new Exception("No credit card ID");
		if (consolidationId == null) throw new Exception("No consolidation ID");

		PaymentCreditCard creditCard = paymentCreditCardRepository.findOneByCreditCardID(creditCardId);
		if (creditCard == null) throw new Exception("No credit card exists");

		OrderPaymentStatus orderPaymentStatus = orderPaymentStatusRepository.findOneByReferenceIDAndIsOrderOrderByOrderPaymentStatusIDDesc(consolidationId, 0);

		Integer id;
		if (orderPaymentStatus != null && orderPaymentStatus.getPaymentStatusID() != null) {
			id = orderPaymentStatus.getPaymentStatusID();
		} else if (existsCardStatusId(creditCard.getCardStatusID())) {
			id = 1;
		} else {
			id = 99;
		}

		Optional<PaymentStatus> paymentStatus = paymentStatusRepository.findById(id);
		String name;
		if (paymentStatus.isPresent()) {
			name = paymentStatus.get().getPaymentStatus();
		} else {
			name = "Pending";
		}

		return PaymentStatusResponse.builder()
				.cardStatusId(creditCard.getCardStatusID())
				.paymentStatusId(id)
				.paymentStatusName(name)
				.build();
	}

	private boolean existsCardStatusId(Integer id) {
		if (id == null) return false;

		List<CardStatus> statuses = waPaymentService.getCreditCardStatus();
		Set<Integer> ids = statuses.stream().map(CardStatus::getCardStatusID).collect(Collectors.toSet());

		return ids.contains(id);
	}

	@Transactional(transactionManager = "primaryTransactionManager", isolation = Isolation.READ_UNCOMMITTED)
	public List<PaymentTransactionResponse> getTransactions(Integer consolidationId) throws Exception {
		if (consolidationId == null) throw new Exception("No consolidation is selected");

		List<PaymentTransactionEntity> transactions =
				paymentTransactionEntityRepository.findByReferenceIDAndReferenceTypeIDOrderByTransactionIDDesc(
						consolidationId, 0/* 0 = Consolidation */);

		BigDecimal authCaptureAmount = getAuthCaptureAmount(transactions);
		return CollectionUtils.isEmpty(transactions) ? null :
				transactions.stream()
				.map(t -> PaymentTransactionResponse.builder()
						.transactionId(t.getTransactionID())
						.transactionType(t.getTransactionType())
						.creditCardId(t.getCreditCardID())
						.amount(t.getAmount())
						.authCaptureAmount(authCaptureAmount)
						.success(t.isSuccess())
						.reasonCode(t.getReasonCode())
						.detail(t.getDetail())
						.createdBy(t.getCreatedBy())
						.createdOn(t.getCreatedOn().toLocalDateTime())
						.build())
				.collect(Collectors.toList());
	}

	private BigDecimal getAuthCaptureAmount(List<PaymentTransactionEntity> transactions) {
		BigDecimal authCaptureAmount = null;
		if (!CollectionUtils.isEmpty(transactions)) {
			for (PaymentTransactionEntity t : transactions) {
				if (t.isSuccess() && t.getTransactionType() == 3/* 3 = Auth & Capture */) {
					authCaptureAmount = t.getAmount();
					break;
				}
			}
		}
		return authCaptureAmount;
	}

	public JsonResponse<?> setPaymentAccountInfo(SetPaymentAccountInfoParameter p) throws JsonProcessingException {
		String url = "/account/" + p.getWholeSalerId() + "/update";
		p.setMaxPayoutPerDay(p.getMaxPayoutPerDay() == null ? 0 : p.getMaxPayoutPerDay());
		p.setPayoutSchedule(p.getPayoutSchedule() == null ? 0 : p.getPayoutSchedule());
		p.setWholeSalerId(p.getWholeSalerId() == null ? 0 : p.getWholeSalerId());

		return (JsonResponse<?>) httpClient.post(url, new ObjectMapper().writeValueAsString(p));
	}

	public JsonResponse<?> setPaymentAccountBank(SetPaymentAccountBankParameter p) throws JsonProcessingException {
		String url = "/account/bank/" + p.getWholeSalerId() + "/add";
		p.setWholeSalerId(p.getWholeSalerId() == null ? 0 : p.getWholeSalerId());

		return (JsonResponse<?>) httpClient.post(url, new ObjectMapper().writeValueAsString(p));
	}
}
