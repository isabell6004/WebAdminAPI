package net.fashiongo.webadmin.service.product.category;

import net.fashiongo.webadmin.model.pojo.common.ResultResponse;
import net.fashiongo.webadmin.model.pojo.sitemgmt.CategoryListOrder;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.SetCategoryListOrderParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.SetCategoryParameter;

import java.util.List;

public interface ProductCategoryService {
	ResultResponse<Void> setCategory(SetCategoryParameter parameter);

	ResultResponse<List<CategoryListOrder>> setCategoryListOrder(SetCategoryListOrderParameter parameter);
}
