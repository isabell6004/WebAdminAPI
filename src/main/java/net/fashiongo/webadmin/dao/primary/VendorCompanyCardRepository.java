package net.fashiongo.webadmin.dao.primary;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.VendorCompanyCard;

/**
 * 
 * @author Reo
 *
 */
public interface VendorCompanyCardRepository extends CrudRepository<VendorCompanyCard, Integer> {
	VendorCompanyCard findOneBywholeSalerId(Integer wholeSalerID);
}
