package net.fashiongo.webadmin.dao.primary;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.MapPatternCategory;

/**
 * 
 * @author Reo
 *
 */
public interface MapPatternCategoryRepository extends CrudRepository<MapPatternCategory, Integer> {
	List<MapPatternCategory> findByCategoryIDIn(Integer categoryID);
}
