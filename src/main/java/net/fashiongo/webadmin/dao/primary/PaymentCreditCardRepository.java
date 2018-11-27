package net.fashiongo.webadmin.dao.primary;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.PaymentCreditCard;

/**
 * 
 * @author DAHYE
 *
 */
public interface PaymentCreditCardRepository extends CrudRepository<PaymentCreditCard, Integer> {
	PaymentCreditCard findOneByCreditCardID(Integer creditCardID);
}
