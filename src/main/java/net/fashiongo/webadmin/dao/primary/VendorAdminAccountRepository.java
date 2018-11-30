package net.fashiongo.webadmin.dao.primary;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.VendorAdminAccount;

/**
 * 
 * @author Reo
 *
 */
public interface VendorAdminAccountRepository extends CrudRepository<VendorAdminAccount, Integer> {
	List<VendorAdminAccount> findByWholeSalerIDIn(Integer wholeSalerID);
}
