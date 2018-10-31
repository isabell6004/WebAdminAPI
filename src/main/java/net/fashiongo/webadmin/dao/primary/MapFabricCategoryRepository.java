package net.fashiongo.webadmin.dao.primary;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.MapFabricCategory;

/**
 * 
 * @author Reo
 *
 */
public interface MapFabricCategoryRepository extends CrudRepository<MapFabricCategory, Integer> {
	List<MapFabricCategory> findByCategoryIDIn(Integer categoryID);
}
