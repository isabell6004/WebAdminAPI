package net.fashiongo.webadmin.controller.product;

import net.fashiongo.webadmin.model.pojo.common.ResultResponse;
import net.fashiongo.webadmin.model.pojo.sitemgmt.CategoryListOrder;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.SetCategoryListOrderParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.SetCategoryParameter;
import net.fashiongo.webadmin.service.CacheService;
import net.fashiongo.webadmin.service.product.category.ProductCategoryService;
import net.fashiongo.webadmin.utility.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductCategoryController {

	@Autowired
	private ProductCategoryService productCategoryService;

	@Autowired
	private CacheService cacheService;

	@RequestMapping(value = "/sitemgmt/setcategory", method = RequestMethod.POST)
	public JsonResponse<Void> setCategory(@RequestBody SetCategoryParameter parameters) {
		ResultResponse<Void> result = productCategoryService.setCategory(parameters);

		cacheService.GetRedisCacheEvict("CategoryVendors", null); // When a vendor is activated or deactivated
		cacheService.GetRedisCacheEvict("Category", null); // When FashionGo categories is changed (delete, add, modify)

		return new JsonResponse<>(result.getSuccess(), result.getMessage(), result.getCode(), result.getPk(), result.getData());
	}

	@RequestMapping(value = "/sitemgmt/setcategorylistorder", method = RequestMethod.POST)
	public JsonResponse<List<CategoryListOrder>> setCategoryListOrder(@RequestBody SetCategoryListOrderParameter parameters) {
		ResultResponse<List<CategoryListOrder>> result = productCategoryService.setCategoryListOrder(parameters);

		cacheService.GetRedisCacheEvict("CategoryVendors", null); // When a vendor is activated or deactivated
		cacheService.GetRedisCacheEvict("Category", null); // When FashionGo categories is changed (delete, add, modify)

		return new JsonResponse<>(result.getSuccess(), result.getMessage(), result.getCode(), result.getData());
	}
}
