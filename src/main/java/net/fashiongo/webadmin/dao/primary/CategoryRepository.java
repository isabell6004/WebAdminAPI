package net.fashiongo.webadmin.dao.primary;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import net.fashiongo.webadmin.model.primary.Category;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
	List<Category> findByActiveTrue();
	
	Category findOneByCategoryID(Integer categoryID); 
	
	void deleteByCategoryID(Integer categoryID);
	
	List<Category> findByParentCategoryIDAndLvlAndCategoryIDNotOrderByListOrderAsc(Integer parentCategoryID, Integer lvl, Integer categoryID);
	
	List<Category> findByParentCategoryIDAndLvlAndCategoryIDNot(Integer parentCategoryID, Integer lvl, Integer categoryID);
	
	List<Category> findByParentCategoryIDAndLvlOrderByListOrderAsc(Integer parentCategoryID, Integer lvl);
	
	@Query(value="SELECT CategoryID FROM Category WHERE Lvl =:lvl", nativeQuery=true)
	List<Integer> getCategoryIDWithLvl(@Param("lvl") Integer lvl);
	
	@Query(value="SELECT CategoryID FROM Category WHERE Lvl =:lvl and CategoryID =:categoryID", nativeQuery=true)
	List<Integer> getCategoryIDListWithCategoryID(@Param("categoryID") Integer categoryID,@Param("lvl") Integer lvl);
}
