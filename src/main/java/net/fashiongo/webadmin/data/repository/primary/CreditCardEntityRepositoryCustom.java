package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.CreditCardEntity;

import java.util.List;

public interface CreditCardEntityRepositoryCustom {

	List<CreditCardEntity> findAllByRetailerIdWithCodeCreditCardType(int retailerId);
}
