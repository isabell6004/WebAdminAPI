package net.fashiongo.webadmin.service.product.attribute;

import net.fashiongo.webadmin.model.pojo.common.ResultCode;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.SetProductAttributesMappingParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.SetProductAttributesParameter;

public interface ProductAttributeService {
	ResultCode setProductAttributes(SetProductAttributesParameter parameter);

	ResultCode setProductAttributesMapping(SetProductAttributesMappingParameter parameter);
}
