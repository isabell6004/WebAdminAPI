package net.fashiongo.webadmin.dao.primary;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.MessageCategory;

/**
 * 
 * @author Reo
 *
 */
public interface MessageCategoryRepository extends CrudRepository<MessageCategory, Integer> {
	List<MessageCategory> findByActiveOrderBySortNoRetailerDesc(Boolean active);
}
