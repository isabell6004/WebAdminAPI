package net.fashiongo.webadmin.dao.primary;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.Category;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
	List<Category> findByActiveTrue();
	
	Category findOneByCategoryID(Integer categoryID); 
	
	void deleteByCategoryID(Integer categoryID);
	
	List<Category> findByParentCategoryIDAndLvlAndCategoryIDNotOrderByListOrderAsc(Integer parentCategoryID, Integer lvl, Integer categoryID);
	
	List<Category> findByParentCategoryIDAndLvlAndCategoryIDNot(Integer parentCategoryID, Integer lvl, Integer categoryID);
	
	List<Category> findByParentCategoryIDAndLvlOrderByListOrderAsc(Integer parentCategoryID, Integer lvl);
}
