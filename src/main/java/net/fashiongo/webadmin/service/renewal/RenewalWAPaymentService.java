package net.fashiongo.webadmin.service.renewal;

import net.fashiongo.webadmin.dao.primary.PaymentTransactionEntityRepository;
import net.fashiongo.webadmin.data.model.Total;
import net.fashiongo.webadmin.data.model.payment.PaymentStatus;
import net.fashiongo.webadmin.data.model.payment.response.GetPaymentStatusSearchOptionResponse;
import net.fashiongo.webadmin.data.model.vendor.Vendor;
import net.fashiongo.webadmin.data.repository.primary.ListPaymentStatusEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.WholeSalerEntityRepository;
import net.fashiongo.webadmin.model.pojo.payment.PaymentStatusList;
import net.fashiongo.webadmin.model.pojo.payment.parameter.GetPaymentStatusListParameter;
import net.fashiongo.webadmin.model.pojo.payment.response.GetPaymentStatusListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RenewalWAPaymentService {

	@Autowired
	private WholeSalerEntityRepository wholeSalerEntityRepository;

	@Autowired
	private ListPaymentStatusEntityRepository listPaymentStatusEntityRepository;

	@Autowired
	private PaymentTransactionEntityRepository paymentTransactionEntityRepository;

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
				pageNum,pageSize,wholeSalerID,paymentStatusID,fromDate,toDate,
				poNumber,consolidationID,buyerName,transactionType,searchSuccess,orderBy
		);

		result.setPaymentStatusList(paymentStatusList.getContent());
		result.setTotal(Arrays.asList(Total.builder().recCnt((int) paymentStatusList.getTotalElements()).build()));
		return result;
	}
}
