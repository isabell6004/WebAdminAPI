package net.fashiongo.webadmin.dao.primary;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.MapStyleCategory;

/**
 * 
 * @author Reo
 *
 */
public interface MapStyleCategoryRepository extends CrudRepository<MapStyleCategory, Integer> {
	List<MapStyleCategory> findByCategoryIDIn(Integer categoryID);
}
