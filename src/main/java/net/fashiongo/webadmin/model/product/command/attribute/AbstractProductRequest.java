package net.fashiongo.webadmin.model.product.command.attribute;

import net.fashiongo.webadmin.service.externalutil.FashionGoApiConfig;
import org.springframework.http.HttpMethod;

public abstract class AbstractProductRequest {
	protected final String baseUrl = FashionGoApiConfig.fashionGoApi + "/v1.0/product";

	public AttributeRequestInfo build() {
		return new AttributeRequestInfo(buildEndpoint(), makePayload(), getMethod());
	}

	protected abstract String buildEndpoint();

	protected abstract Object makePayload();

	protected abstract HttpMethod getMethod();
}
