/**
 * 
 */
package net.fashiongo.webadmin.dao.fgpay;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.fgpay.DisputeDocument;


/**
 * @author Brian
 *
 */
public interface DisputeDocumentRepository extends CrudRepository<DisputeDocument, Integer>{
	DisputeDocument findOneByDisputeId(String disputeId);
}
