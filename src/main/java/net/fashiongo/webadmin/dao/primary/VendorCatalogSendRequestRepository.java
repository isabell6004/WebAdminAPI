package net.fashiongo.webadmin.dao.primary;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.VendorCatalogSendRequest;

/**
 * 
 * @author Incheol Jung
 */
public interface VendorCatalogSendRequestRepository extends CrudRepository<VendorCatalogSendRequest, Integer> {
	List<VendorCatalogSendRequest> findByCatalogSendRequestIDIn(List<Integer> catalogSendQueueIDs);
}