package net.fashiongo.webadmin.dao.primary;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.MapLengthCategory;

/**
 * 
 * @author Reo
 *
 */
public interface MapLengthCategoryRepository extends CrudRepository<MapLengthCategory, Integer> {
	List<MapLengthCategory> findByCategoryIDIn(Integer categoryID);
}
