package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.model.payment.CodeCreditCardType;

import java.util.List;

public interface CodeCreditCardTypeEntityRepositoryCustom {
    List<CodeCreditCardType> findAllCodeCreditCardType();
}
