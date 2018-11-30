package net.fashiongo.webadmin.dao.primary;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.VendorImageRequest;

/**
 * 
 * @author Reo
 *
 */
public interface VendorImageRequestRepository extends CrudRepository<VendorImageRequest, Integer> {
	VendorImageRequest findOneByImageRequestID(Integer imageRequestID);
}
