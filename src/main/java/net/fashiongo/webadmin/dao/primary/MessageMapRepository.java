package net.fashiongo.webadmin.dao.primary;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.MessageMap;

/**
 * 
 * @author Reo
 *
 */
public interface MessageMapRepository extends CrudRepository<MessageMap, Integer> {
	MessageMap findOneByMessageID(Integer messageID);
	List<MessageMap> findByMessageIDInReadOnIsNull(List<Integer> messageIDs);
}
