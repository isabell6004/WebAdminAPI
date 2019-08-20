package net.fashiongo.webadmin.data.repository.primary.view;

import net.fashiongo.webadmin.data.model.sitemgmt.CodeData;
import org.springframework.data.domain.Page;

public interface CategoryViewRepository {

	Page<CodeData> findAllvwLengthCategoryByCategoryIdOrderByLengthName(Integer categoryId, int pageNumber, int pageSize);

	Page<CodeData> findAllvwStyleCategoryByCategoryIdOrderByStyleName(Integer categoryId, int pageNumber, int pageSize);

	Page<CodeData> findAllvwFabricCategoryByCategoryIdOrderByfabricName(Integer categoryId, int pageNumber, int pageSize);

	Page<CodeData> findAllvwPatternCategoryByCategoryIdOrderByPatternName(Integer categoryId, int pageNumber, int pageSize);

}
