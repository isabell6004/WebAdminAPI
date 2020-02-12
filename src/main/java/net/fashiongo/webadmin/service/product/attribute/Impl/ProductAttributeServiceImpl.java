package net.fashiongo.webadmin.service.product.attribute.Impl;

import net.fashiongo.webadmin.model.pojo.common.ResultCode;
import net.fashiongo.webadmin.model.pojo.sitemgmt.CodeData;
import net.fashiongo.webadmin.model.pojo.sitemgmt.ProductAttribute;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.SetProductAttributesMappingParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.SetProductAttributesParameter;
import net.fashiongo.webadmin.model.product.command.ProductRequestWrapper;
import net.fashiongo.webadmin.model.product.type.attribute.AttributeRequestType;
import net.fashiongo.webadmin.model.product.type.attribute.AttributeType;
import net.fashiongo.webadmin.service.externalutil.response.FashionGoApiResponse;
import net.fashiongo.webadmin.service.product.attribute.ProductAttributeService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class ProductAttributeServiceImpl implements ProductAttributeService {

	private final ProductRequestWrapper productRequestWrapper;

	public ProductAttributeServiceImpl(ProductRequestWrapper productRequestWrapper) {
		this.productRequestWrapper = productRequestWrapper;
	}

	@Override
	public ResultCode setProductAttributes(SetProductAttributesParameter parameter) {
		FashionGoApiResponse<?> response = parseAndExecute(parameter);

		return makeResultCode(response);
	}

	private FashionGoApiResponse<?> parseAndExecute(SetProductAttributesParameter parameter) {
		AttributeRequestType requestType = AttributeRequestType.fromBType(parameter.getbType());
		AttributeType attributeType = AttributeType.fromTabNo(parameter.getTabNo());

		switch (requestType) {
			case DELETE_ALL:
				return productRequestWrapper.attribute()
						.delete()
						.attributeType(attributeType)
						.targetIds(parameter.getCodeDataList().stream()
								.map(CodeData::getCodeID)
								.collect(Collectors.toList()))
						.execute();
			case DELETE:
				return productRequestWrapper.attribute()
						.delete()
						.attributeType(attributeType)
						.targetIds(Collections.singletonList(parameter.getCodeID()))
						.execute();
			case UPDATE:
				return productRequestWrapper.attribute()
						.update()
						.attributeType(attributeType)
						.attributeId(parameter.getCodeID())
						.attributeName(parameter.getAttrName())
						.attributeActive(parameter.getActive())
						.execute();
			case ADD:
				return productRequestWrapper.attribute()
						.create()
						.attributeType(attributeType)
						.attributeName(parameter.getAttrName())
						.attributeActive(parameter.getActive())
						.execute();
			default:
				throw new RuntimeException("internal server error");
		}
	}

	@Override
	public ResultCode setProductAttributesMapping(SetProductAttributesMappingParameter parameter) {
		FashionGoApiResponse<Void> response = parseAndExecute(parameter);

		return makeResultCode(response);
	}

	private FashionGoApiResponse<Void> parseAndExecute(SetProductAttributesMappingParameter parameter) {
		AttributeType attributeType = AttributeType.fromTabNo(parameter.getTabNo());

		return productRequestWrapper.attribute()
				.mapping()
				.attributeType(attributeType)
				.categoryId(parameter.getCategoryID())
				.targetIds(parameter.getProductAttributeList().stream()
						.map(ProductAttribute::getCodeID)
						.collect(Collectors.toList()))
				.execute();
	}

	private ResultCode makeResultCode(FashionGoApiResponse<?> response) {
		if (response.getHeader().isSuccessful()) {
			return new ResultCode(true, 1, "success");
		} else {
			return new ResultCode(false, 0, response.getHeader().getResultMessage());
		}
	}
}
