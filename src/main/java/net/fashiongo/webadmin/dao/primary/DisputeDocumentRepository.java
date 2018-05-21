/**
 * 
 */
package net.fashiongo.webadmin.dao.primary;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.DisputeDocument;


/**
 * @author Brian
 *
 */
public interface DisputeDocumentRepository extends CrudRepository<DisputeDocument, Integer>{
	DisputeDocument findOneByDisputeId(String disputeId);
}
