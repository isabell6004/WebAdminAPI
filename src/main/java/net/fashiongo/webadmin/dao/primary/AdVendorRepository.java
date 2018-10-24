package net.fashiongo.webadmin.dao.primary;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.AdVendor;

/**
 * @author Nayeon Kim
 */
public interface AdVendorRepository extends CrudRepository<AdVendor, Integer> {
	AdVendor findTopBySpotID(Integer spotID);
}
