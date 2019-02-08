package net.fashiongo.webadmin.dao.primary;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.Message;

/**
 * 
 * @author Reo
 *
 */
public interface MessageRepository extends CrudRepository<Message, Integer> {
	Message findByMessageID(Integer topReferenceID);
}
