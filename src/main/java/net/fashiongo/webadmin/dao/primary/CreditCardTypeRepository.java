package net.fashiongo.webadmin.dao.primary;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.CreditCardType;


public interface CreditCardTypeRepository extends CrudRepository<CreditCardType, Integer> {
	List<CreditCardType> findAllByActiveTrueOrderByCreditCardTypeID();
}
