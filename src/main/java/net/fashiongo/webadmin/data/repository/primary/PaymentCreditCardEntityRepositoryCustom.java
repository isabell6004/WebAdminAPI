package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.model.payment.CreditCardInfo;
import net.fashiongo.webadmin.data.model.payment.PaymentCreditCardInfo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PaymentCreditCardEntityRepositoryCustom {
    List<PaymentCreditCardInfo> getPaymentCreditCardInfo(Integer creditCardID);

    Page<CreditCardInfo> getCreditCardInfo(Integer pageNum, Integer pageSize, String cardID, Boolean isDefaultCard, Integer cardTypeID, Integer cardStatusID, String billingID,
                                           String country, String state, String buyer, String referencedID, String orderBy, String orderGUBN);
}
