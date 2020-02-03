package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.model.payment.PaymentCreditCardInfo;

import java.util.List;

public interface PaymentCreditCardEntityRepositoryCustom {
    List<PaymentCreditCardInfo> getPaymentCreditCardInfo(Integer creditCardID);
}
