package net.fashiongo.webadmin.controller.product;

import net.fashiongo.webadmin.model.pojo.common.ResultCode;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.SetProductAttributesMappingParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.SetProductAttributesParameter;
import net.fashiongo.webadmin.service.CacheService;
import net.fashiongo.webadmin.service.product.attribute.ProductAttributeService;
import net.fashiongo.webadmin.utility.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductAttributeController {

	@Autowired
	private ProductAttributeService productAttributeService;

	@Autowired
	private CacheService cacheService;

	@RequestMapping(value = "/sitemgmt/setproductattributes", method = RequestMethod.POST)
	public JsonResponse<ResultCode> setProductAttributes(@RequestBody SetProductAttributesParameter parameter) {
		ResultCode result = productAttributeService.setProductAttributes(parameter);
		cacheService.GetRedisCacheEvict("BodySizeVendors", null);

		return new JsonResponse<>(result.getSuccess(), result.getResultMsg(), result.getResultCode(), result);
	}

	@RequestMapping(value = "/sitemgmt/setproductattributesactive", method = RequestMethod.POST)
	public JsonResponse<ResultCode> setProductAttributesActive(@RequestBody SetProductAttributesParameter parameter) {
		parameter.setbType("save");
		ResultCode result = productAttributeService.setProductAttributes(parameter);

		return new JsonResponse<>(result.getSuccess(), result.getResultMsg(), result.getResultCode(), result);
	}

	@RequestMapping(value = "/sitemgmt/setproductattributesmapping", method = RequestMethod.POST)
	public JsonResponse<ResultCode> setProductAttributesMapping(@RequestBody SetProductAttributesMappingParameter parameter) {
		ResultCode result = productAttributeService.setProductAttributesMapping(parameter);
		cacheService.GetRedisCacheEvict("BodySizeVendors", null);

		return new JsonResponse<>(result.getSuccess(), result.getResultMsg(), result.getResultCode(), result);
	}
}
