package net.fashiongo.webadmin.dao.primary;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.VendorCreditCard;

/**
 * 
 * @author Reo
 *
 */
public interface VendorCreditCardRepository extends CrudRepository<VendorCreditCard, Integer> {
	VendorCreditCard findOneByVendorCreditCardID(Integer vendorCreditCardID);
}
