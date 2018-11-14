package net.fashiongo.webadmin.dao.primary;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.EntityActionLog;

/**
 * 
 * @author Reo
 *
 */
public interface EntityActionLogRepository extends CrudRepository<EntityActionLog, Integer> {
	List<EntityActionLog> findByEntityTypeIDAndEntityIDOrderByLogIDDesc(Integer entityTypeID, Integer wholeSalerID);
}
