package net.fashiongo.webadmin.dao.primary;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.VendorBlocked;

/**
 * 
 * @author Reo
 *
 */
public interface VendorBlockedRepository extends CrudRepository<VendorBlocked, Integer> {
	void deleteByBlockID(Integer blockID);
}
