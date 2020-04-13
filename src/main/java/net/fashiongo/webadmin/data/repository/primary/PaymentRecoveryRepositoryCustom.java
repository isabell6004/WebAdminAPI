package net.fashiongo.webadmin.data.repository.primary;

import org.springframework.data.domain.Page;

import net.fashiongo.webadmin.data.model.payment.GetPaymentRecoveryListParameter;
import net.fashiongo.webadmin.data.model.payment.PaymentRecoveryList;


public interface PaymentRecoveryRepositoryCustom {

	Page<PaymentRecoveryList> getPaymentRecoveryListWithCount(GetPaymentRecoveryListParameter param);
	
}
