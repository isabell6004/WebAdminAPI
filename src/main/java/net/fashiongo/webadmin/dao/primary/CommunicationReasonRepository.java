package net.fashiongo.webadmin.dao.primary;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.CommunicationReason;
/**
 * 
 * @author DAHYE
 *
 */
public interface CommunicationReasonRepository extends CrudRepository<CommunicationReason, Integer> {
	List<CommunicationReason> findAll();
	CommunicationReason findOneByReasonID(Integer reasonID);
	
}
