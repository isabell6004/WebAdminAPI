package net.fashiongo.webadmin.service.renewal;

import net.fashiongo.common.dal.JdbcHelper;
import net.fashiongo.webadmin.dao.primary.PaymentTransactionEntityRepository;
import net.fashiongo.webadmin.data.model.Total;
import net.fashiongo.webadmin.data.model.TotalCount;
import net.fashiongo.webadmin.data.model.payment.CodeCreditCardType;
import net.fashiongo.webadmin.data.model.payment.CreditCardInfo;
import net.fashiongo.webadmin.data.model.payment.GetPaymentRecoveryListParameter;
import net.fashiongo.webadmin.data.model.payment.OrderPayment;
import net.fashiongo.webadmin.data.model.payment.PaymentCreditCardInfo;
import net.fashiongo.webadmin.data.model.payment.PaymentRecoveryList;
import net.fashiongo.webadmin.data.model.payment.PaymentStatus;
import net.fashiongo.webadmin.data.model.payment.response.GetAllSavedCreditCardInfoResponse;
import net.fashiongo.webadmin.data.model.payment.response.GetPaymentRecoveryResponse;
import net.fashiongo.webadmin.data.model.payment.response.GetPaymentStatusSearchOptionResponse;
import net.fashiongo.webadmin.data.model.payment.response.GetPendingPaymentTransactionResponse;
import net.fashiongo.webadmin.data.model.vendor.Vendor;
import net.fashiongo.webadmin.data.repository.primary.CodeCreditCardTypeEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.ListPaymentStatusEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.OrderPaymentStatusEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.PaymentCreditCardEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.PaymentRecoveryRepository;
import net.fashiongo.webadmin.data.repository.primary.WholeSalerEntityRepository;
import net.fashiongo.webadmin.model.pojo.payment.PaymentStatusList;
import net.fashiongo.webadmin.model.pojo.payment.parameter.GetAllSavedCreditCardInfoParameter;
import net.fashiongo.webadmin.model.pojo.payment.parameter.GetPaymentStatusListParameter;
import net.fashiongo.webadmin.model.pojo.payment.response.GetPaymentStatusListResponse;
import net.fashiongo.webadmin.model.pojo.payment.response.PaymentRecoveryResponse;
import net.fashiongo.webadmin.utility.Utility;
import net.fashiongo.webadmin.model.pojo.payment.parameter.GetPendingPaymentTransactionParameter;
import net.fashiongo.webadmin.model.pojo.payment.parameter.PaymentRecovery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RenewalWAPaymentService {

	@Autowired
	private WholeSalerEntityRepository wholeSalerEntityRepository;

	@Autowired
	private ListPaymentStatusEntityRepository listPaymentStatusEntityRepository;

	@Autowired
	private PaymentTransactionEntityRepository paymentTransactionEntityRepository;

	@Autowired
	private PaymentCreditCardEntityRepository paymentCreditCardEntityRepository;

	@Autowired
	private OrderPaymentStatusEntityRepository orderPaymentStatusEntityRepository;

	@Autowired
	private CodeCreditCardTypeEntityRepository codeCreditCardTypeEntityRepository;
	
	@Autowired 
	private PaymentRecoveryRepository paymentRecoveryRepository;	

	private JdbcHelper jdbcHelper;
	
	@Transactional(transactionManager = "primaryTransactionManager")
	public GetPaymentStatusSearchOptionResponse getPaymentStatusSearchOption() {

		List<PaymentStatus> paymentStatusList = listPaymentStatusEntityRepository.findAll().stream()
				.map(listPaymentStatusEntity -> new PaymentStatus(listPaymentStatusEntity.getPaymentStatusID(), listPaymentStatusEntity.getPaymentStatus(), listPaymentStatusEntity.getPaymentStatusDescription()))
				.collect(Collectors.toList());

		List<Vendor> vendorList = wholeSalerEntityRepository.findAllByOrderActiveOrderByCompanyNameAsc(true);
		return GetPaymentStatusSearchOptionResponse.builder()
				.vendorList(vendorList)
				.paymentStatusList(paymentStatusList)
				.build();
	}

	public GetPaymentStatusListResponse getPaymentStatusList(GetPaymentStatusListParameter param) {
		GetPaymentStatusListResponse result = new GetPaymentStatusListResponse();

		int pageNum = param.getPageNum();
		int pageSize = param.getPageSize();
		Integer wholeSalerID = param.getWholeSalerID();
		Integer paymentStatusID = param.getPaymentStatusID();
		LocalDateTime fromDate = param.getFromDate();
		LocalDateTime toDate = param.getToDate();
		String poNumber = param.getPoNumber();
		Integer consolidationID = param.getConsolidationID();
		String buyerName = param.getBuyerName();
		Integer transactionType = param.getTransactionType();
		Integer searchSuccess = param.getSearchSuccess();
		String orderBy = param.getOrderBy();

		Page<PaymentStatusList> paymentStatusList = paymentTransactionEntityRepository.getPaymentStatusList(
				pageNum, pageSize, wholeSalerID, paymentStatusID, fromDate, toDate,
				poNumber, consolidationID, buyerName, transactionType, searchSuccess, orderBy
		);

		result.setPaymentStatusList(paymentStatusList.getContent());
		result.setTotal(Arrays.asList(Total.builder().recCnt((int) paymentStatusList.getTotalElements()).build()));
		return result;
	}

	@Transactional(value = "primaryTransactionManager", isolation = Isolation.READ_UNCOMMITTED)
	public GetPendingPaymentTransactionResponse getPendingPaymentTransaction(GetPendingPaymentTransactionParameter param) {
		Integer creditCardId = param.getCreditCardId();

		List<PaymentCreditCardInfo> paymentCreditCardInfo = paymentCreditCardEntityRepository.getPaymentCreditCardInfo(creditCardId);
		List<OrderPayment> orderPaymentList = orderPaymentStatusEntityRepository.getOrderPayment(creditCardId);

		return GetPendingPaymentTransactionResponse.builder()
				.creditCardStatusList(paymentCreditCardInfo)
				.orderPaymentStatusList(orderPaymentList).build();
	}

	public List<CodeCreditCardType> getCreditCardType() {
		return codeCreditCardTypeEntityRepository.findAllCodeCreditCardType();
	}

	public GetAllSavedCreditCardInfoResponse getAllSavedCreditCardInfo(GetAllSavedCreditCardInfoParameter param) {
		Integer pageNum = param.getPageNum();
		Integer pageSize = param.getPageSize();
		String cardID = param.getCardID();
		Boolean isDefaultCard = param.getDefaultCard();
		Integer cardTypeID = param.getCardTypeID();
		Integer cardStatusID = param.getCardStatusID();
		String billingID = param.getBillingID();
		String country = param.getCreditCountry();
		String state = param.getCreditState();
		String buyer = param.getBuyer();
		String referencedID = param.getReferenceID();
		String orderBy = param.getOrderBy();
		String orderGUBN = param.getOrderGubn();

		Page<CreditCardInfo> creditCardInfo = paymentCreditCardEntityRepository.getCreditCardInfo(pageNum, pageSize, cardID, isDefaultCard, cardTypeID, cardStatusID,
				billingID, country, state, buyer, referencedID, orderBy, orderGUBN);

		return GetAllSavedCreditCardInfoResponse.builder()
				.creditCardInfo(creditCardInfo.getContent())
				.totalList(Arrays.asList(new TotalCount((int) creditCardInfo.getTotalElements())))
				.build();
	}
	//public PaymentRecoveryResponse setPaymentrecovery(PaymentRecovery paymentrecovery) throws Exception {
	public 	PaymentRecoveryResponse	setPaymentrecovery(PaymentRecovery paymentrecovery) throws Exception {
		
		PaymentRecoveryResponse result = null;
		
		//String PaymentDate;
		//String isoDatePattern = "yyyy-MM-dd HH:mm:ss";
		 
		//SimpleDateFormat simpleDateFormat = new SimpleDateFormat(isoDatePattern);
	 
		//PaymentDate = simpleDateFormat.format(paymentrecovery.getPaymentDate());		
		
		String spName = "up_wa_stripe_payment_failure";
		List<Object> params = new ArrayList<>();
		params.add(paymentrecovery.getTransactionType());
		params.add(paymentrecovery.getReferenceID());
		params.add(paymentrecovery.getReferenceTypeID());
		params.add(paymentrecovery.getCreditCardReferenceID());
		params.add(paymentrecovery.getPGReferenceID());
		params.add(paymentrecovery.getNetAmount());
		params.add(paymentrecovery.getTransferAmount());
		//params.add(PaymentDate);
		params.add(Utility.getUsername());
		
		//PaymentRecoveryResponse result = jdbcHelper.executeSP(spName, params, PaymentRecoveryResponse.class);
		List<Object> _result = jdbcHelper.executeSP(spName, params, PaymentRecoveryResponse.class);

		if (CollectionUtils.isEmpty(_result)) {
			
			return result;
		}	
		
		List<PaymentRecoveryResponse> rs1 = (List<PaymentRecoveryResponse>) _result.get(0);
		
		if(!CollectionUtils.isEmpty(rs1)) {
			result = rs1.get(0);
			
			//String requestedBy = invoiceDetail.getCreatedBy().toLowerCase();
			//String currentUser = Utility.getWebAdminUserName().toLowerCase();
			//Boolean isDeletableUser = false;
			//isDeletableUser = requestedBy.equals(currentUser) ? true : false;
			//invoiceDetail.setIsDeletableUser(isDeletableUser);
		}		
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public GetPaymentRecoveryResponse getPaymentRecoveryList(GetPaymentRecoveryListParameter q) {
	
		Page<PaymentRecoveryList> result = paymentRecoveryRepository.getPaymentRecoveryListWithCount(q);

		GetPaymentRecoveryResponse respone = GetPaymentRecoveryResponse.builder()
													.recCnt(Arrays.asList(Total.builder().recCnt((int) result.getTotalElements()).build()))
													.paymentrecoverylist(result.getContent())
													.build();
		
		return respone;		
	}
}
