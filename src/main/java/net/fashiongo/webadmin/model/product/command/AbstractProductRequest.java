package net.fashiongo.webadmin.model.product.command;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.fashiongo.webadmin.model.pojo.login.WebAdminLoginUser;
import net.fashiongo.webadmin.service.externalutil.FashionGoApiConfig;
import net.fashiongo.webadmin.service.externalutil.FashionGoApiHeader;
import net.fashiongo.webadmin.service.externalutil.HttpClientWrapper;
import net.fashiongo.webadmin.service.externalutil.response.FashionGoApiResponse;
import net.fashiongo.webadmin.utility.Utility;
import org.springframework.http.HttpMethod;

import java.io.IOException;
import java.util.Map;

public abstract class AbstractProductRequest<T> {

	protected final String baseUrl = FashionGoApiConfig.fashionGoApi + "/v1.0/products";

	private final HttpClientWrapper httpCaller;

	private final ObjectMapper objectMapper = new ObjectMapper();

	public AbstractProductRequest(HttpClientWrapper httpCaller) {
		this.httpCaller = httpCaller;
	}

	public FashionGoApiResponse<T> execute() {
		String responseBody = doRequest(getMethod(), buildEndpoint(), makePayload());

		return parseResponse(responseBody);
	}

	abstract String buildEndpoint();

	protected abstract Object makePayload();

	protected abstract HttpMethod getMethod();

	private String doRequest(HttpMethod httpMethod, String endpoint, Object payload) {
		WebAdminLoginUser userInfo = Utility.getUserInfo();
		Map<String, String> header = FashionGoApiHeader.getHeader(userInfo.getUserId(), userInfo.getUsername());

		switch (httpMethod) {
			case POST:
				return httpCaller.post(endpoint, payload, header);
			case PUT:
				return httpCaller.put(endpoint, payload, header);
			case DELETE:
				return httpCaller.delete(endpoint, header);
			default:
				return null;
		}
	}

	protected FashionGoApiResponse<T> parseResponse(String responseBody) {
		try {
			return objectMapper.readValue(responseBody, new TypeReference<FashionGoApiResponse<T>>() {
			});
		} catch (IOException e) {
			throw new RuntimeException("internal server error while post-process. please refresh the page.");
		}
	}

	protected ObjectMapper getObjectMapper() {
		return this.objectMapper;
	}
}
