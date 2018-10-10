package net.fashiongo.webadmin.dao.primary;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.TopCategories;

/**
 * 
 * @author DAHYE
 *
 */
public interface TopCategoriesRepository extends CrudRepository<TopCategories, Integer> {
	List<TopCategories> findByActiveAndLvlOrderByListOrder(Boolean active, Integer lvl);
}
