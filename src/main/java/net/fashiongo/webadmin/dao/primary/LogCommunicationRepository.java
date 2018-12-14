package net.fashiongo.webadmin.dao.primary;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.LogCommunication;

/**
 * 
 * @author Reo
 *
 */
public interface LogCommunicationRepository extends CrudRepository<LogCommunication, Integer> {
	List<LogCommunication> findByRetailerIDAndIsForVendorOrderByCommunicationIDDesc(Integer wholeSalerID, Boolean isForVendor);
}
