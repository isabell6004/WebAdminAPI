package net.fashiongo.webadmin.service.product.attribute.Impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.fashiongo.webadmin.model.pojo.common.ResultCode;
import net.fashiongo.webadmin.model.pojo.login.WebAdminLoginUser;
import net.fashiongo.webadmin.model.pojo.sitemgmt.CodeData;
import net.fashiongo.webadmin.model.pojo.sitemgmt.ProductAttribute;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.SetProductAttributesMappingParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.SetProductAttributesParameter;
import net.fashiongo.webadmin.model.product.command.attribute.AttributeRequestInfo;
import net.fashiongo.webadmin.model.product.type.attribute.AttributeRequestType;
import net.fashiongo.webadmin.model.product.type.attribute.AttributeType;
import net.fashiongo.webadmin.service.externalutil.FashionGoApiHeader;
import net.fashiongo.webadmin.service.externalutil.HttpClientWrapper;
import net.fashiongo.webadmin.service.externalutil.response.FashionGoApiResponse;
import net.fashiongo.webadmin.service.product.attribute.ProductAttributeService;
import net.fashiongo.webadmin.utility.Utility;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductAttributeServiceImpl implements ProductAttributeService {

	private final HttpClientWrapper httpCaller;

	public ProductAttributeServiceImpl(HttpClientWrapper httpCaller) {
		this.httpCaller = httpCaller;
	}

	@Override
	public ResultCode setProductAttributes(SetProductAttributesParameter parameter) {
		AttributeRequestInfo info = parseParameter(parameter);

		String response = doRequest(info.getMethod(), info.getEndpoint(), info.getPayload());
		return parseResponse(response);
	}

	private AttributeRequestInfo parseParameter(SetProductAttributesParameter parameter) {
		AttributeRequestType requestType = AttributeRequestType.fromBType(parameter.getbType());
		AttributeType attributeType = AttributeType.fromTabNo(parameter.getTabNo());

		AttributeRequestInfo.AttributeRequestInfoBuilder builder = AttributeRequestInfo.builder();

		switch (requestType) {
			case DELETE_ALL:
				return builder.delete()
						.attributeType(attributeType)
						.targetIds(parameter.getCodeDataList().stream()
								.map(CodeData::getCodeID)
								.collect(Collectors.toList()))
						.build();
			case DELETE:
				return builder.delete()
						.attributeType(attributeType)
						.targetIds(Collections.singletonList(parameter.getCodeID()))
						.build();
			case SAVE:
				return builder.update()
						.attributeType(attributeType)
						.attributeId(parameter.getCodeID())
						.attributeName(parameter.getAttrName())
						.attributeActive(parameter.getActive())
						.build();
			case ADD:
				return builder.create()
						.attributeType(attributeType)
						.attributeName(parameter.getAttrName())
						.attributeActive(parameter.getActive())
						.build();
			default:
				throw new RuntimeException("internal server error");
		}
	}

	@Override
	public ResultCode setProductAttributesMapping(SetProductAttributesMappingParameter parameter) {
		AttributeRequestInfo info = parseMappingParameter(parameter);

		String response = doRequest(info.getMethod(), info.getEndpoint(), info.getPayload());
		return parseResponse(response);
	}

	private AttributeRequestInfo parseMappingParameter(SetProductAttributesMappingParameter parameter) {
		AttributeType attributeType = AttributeType.fromTabNo(parameter.getTabNo());

		return AttributeRequestInfo.builder()
				.mapping()
				.attributeType(attributeType)
				.categoryId(parameter.getCategoryID())
				.targetIds(parameter.getProductAttributeList().stream()
						.map(ProductAttribute::getCodeID)
						.collect(Collectors.toList()))
				.build();
	}

	private String doRequest(HttpMethod httpMethod, String endpoint, Object payload) {
		WebAdminLoginUser userInfo = Utility.getUserInfo();
		Map<String, String> header = FashionGoApiHeader.getHeader(userInfo.getUserId(), userInfo.getUsername());

		switch (httpMethod) {
			case POST:
				return httpCaller.post(endpoint, payload, header);
			case PUT:
				return httpCaller.put(endpoint, payload, header);
			default:
				return null;
		}
	}

	private ResultCode parseResponse(String responseBody) {
		if (StringUtils.isEmpty(responseBody)) {
			return new ResultCode(false, 0, "internal server error");
		}

		try {
			FashionGoApiResponse<?> response = new ObjectMapper().readValue(responseBody, new TypeReference<FashionGoApiResponse<?>>() {
			});

			if (response.getHeader().isSuccessful()) {
				return new ResultCode(true, 1, "success");
			} else {
				return new ResultCode(false, 0, response.getHeader().getResultMessage());
			}

		} catch (IOException e) {
			return new ResultCode(false, 0, "internal server error while post-process. please refresh the page.");
		}
	}
}
