package net.fashiongo.webadmin.dao.primary;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.VendorImageRequest;

/**
 * 
 * @author Reo
 *
 */
public interface VendorImageRequestRepository extends CrudRepository<VendorImageRequest, Integer> {
	List<VendorImageRequest> findByWholeSalerIDAndVendorImageTypeIDInAndActiveOrderByVendorImageTypeIDAscImageRequestIDAsc(Integer wholeSalerID, List<Integer> vendorImageTypeIDs, Boolean active);

	Optional<VendorImageRequest> findByImageRequestID(Integer id);
}
