package net.fashiongo.webadmin.service.renewal;

import net.fashiongo.webadmin.data.model.payment.PaymentStatus;
import net.fashiongo.webadmin.data.model.payment.response.GetPaymentStatusSearchOptionResponse;
import net.fashiongo.webadmin.data.model.vendor.Vendor;
import net.fashiongo.webadmin.data.repository.primary.ListPaymentStatusEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.WholeSalerEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RenewalWAPaymentService {

	@Autowired
	private WholeSalerEntityRepository wholeSalerEntityRepository;

	@Autowired
	private ListPaymentStatusEntityRepository listPaymentStatusEntityRepository;

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
}
