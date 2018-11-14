package net.fashiongo.webadmin.dao.primary;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.VendorCatalogSendQueue;

/**
 * 
 * @author Incheol Jung
 */
public interface VendorCatalogSendQueueRepository extends CrudRepository<VendorCatalogSendQueue, Integer> {
	VendorCatalogSendQueue findOneByCatalogSendQueueID(Integer catalogSendQueueID);
	VendorCatalogSendQueue findFirstByOrderByCatalogSendQueueIDDesc();
}