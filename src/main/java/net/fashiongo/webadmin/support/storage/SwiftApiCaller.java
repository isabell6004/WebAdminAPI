package net.fashiongo.webadmin.support.storage;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SwiftApiCaller {
	private static final Logger logger = LoggerFactory.getLogger(SwiftApiCaller.class);

	private static final int GET_OK_CODE = HttpStatus.SC_OK;
	private static final int UPLOAD_OK_CODE = HttpStatus.SC_CREATED;
	private static final int HEAD_OK_CODE = HttpStatus.SC_OK;
	private static final int DELETE_OK_CODE = HttpStatus.SC_NO_CONTENT;
	private static final int GET_PARTIAL_CONTENT_CODE = HttpStatus.SC_PARTIAL_CONTENT;

	private RequestBuilder requestBuilder;
	private List<Integer> expectResponseCodes;
	private String containerName;
	private String baseUrl;
	private SwiftAuth swiftAuth;

	public SwiftApiCaller(SwiftAuth swiftAuth, String baseUrl) {
		this.baseUrl = baseUrl;
		this.swiftAuth = swiftAuth;
		this.expectResponseCodes = new ArrayList<>();
	}

	private SwiftApiCaller createRequest(String httpMethod) {
		requestBuilder = RequestBuilder.create(httpMethod);
		requestBuilder.addHeader("X-Auth-Token", swiftAuth.getAccessToken());
		// Connection close를 명시적으로 주지 않으면 Toast Cloud 'Object Storage'에서 Keep Alive response가 내려옴
		requestBuilder.addHeader("Connection", "Close");
		return this;
	}

	private SwiftApiCaller containerName(String containerName) {
		this.containerName = containerName;
		return this;
	}

	private SwiftApiCaller addUri(String url) {
		requestBuilder.setUri(baseUrl + url);
		return this;
	}

	private SwiftApiCaller addParameter(String name, String value) {
		requestBuilder.addParameter(name, value);
		return this;
	}

	private SwiftApiCaller addBody(String body) {
		requestBuilder.setEntity(new StringEntity(body, ContentType.TEXT_PLAIN));
		return this;
	}

	private SwiftApiCaller expectResponseCode(int expectResponseCode) {
		expectResponseCodes.add(expectResponseCode);
		return this;
	}

	private SwiftApiCaller addHeader(String name, String value) {
		requestBuilder.addHeader(name, value);
		return this;
	}

	private SwiftApiCaller addHeader(Map<String, String> headers) {
		if (headers != null) {
			headers.forEach((key, value) -> requestBuilder.addHeader(key, value));
		}
		return this;
	}

	private SwiftApiCaller inputStream(InputStream inputStream, boolean isAutoClose) {
		CloseableInputStreamEntity closeableInputStreamEntity = new CloseableInputStreamEntity(inputStream);
		closeableInputStreamEntity.setAutoCloseStream(isAutoClose);

		requestBuilder.setEntity(closeableInputStreamEntity);
		return this;
	}

	private CloseableHttpClient createDefault() {
		int timeout = 60;
		RequestConfig config = RequestConfig.custom()
				.setConnectTimeout(timeout * 1000)
				.setConnectionRequestTimeout(timeout * 1000)
				.setSocketTimeout(timeout * 1000).build();

		return HttpClientBuilder.create()
				.setDefaultRequestConfig(config)
				.build();
	}

	public class ConvertHttpExecutor<T> {
		private ResponseHandler responseHandler;

		public ConvertHttpExecutor(ResponseHandler<T> responseHandler) {
			this.responseHandler = responseHandler;
		}

		public T execute() {
			try {
				return (T) createDefault().execute(requestBuilder.build(), responseHandler);
			} catch (ClientProtocolException e) {
				logger.debug(e.getMessage(), e);
				// in case of an http protocol error
				throw new SwiftApiCallException("ClientProtocolException : [" + requestBuilder.getUri() + "]" + e.getMessage(), 0, containerName);
			} catch (ConnectionPoolTimeoutException e) {
				logger.debug(e.getMessage(), e);
				throw new SwiftConnectionTimeoutException("ConnectionPoolTimeoutException. " + e.getMessage(), 0);
			} catch (SocketTimeoutException e) {
				logger.debug(e.getMessage(), e);
				throw new SwiftSocketTimeoutException("SocketTimeoutException. " + e.getMessage(), 0);
			} catch (IOException e) {
				logger.debug(e.getMessage(), e);
				// in case of a problem or the connection was aborted
				throw new SwiftApiCallException("IOException. " + e.getMessage(), 0);
			}
		}

		public SwiftApiCaller.ConvertHttpExecutor<T> addParam(String key, String value) {
			SwiftApiCaller.this.addParameter(key, value);
			return this;
		}
	}

	public class HttpExecutor {
		private FallbackPolicy fallbackPolicy;

		public CloseableHttpResponse executeWithoutHandler() {
			try {
				CloseableHttpResponse execute = createDefault().execute(requestBuilder.build());
				int statusCode = execute.getStatusLine().getStatusCode();

				if (fallbackPolicy != null && fallbackPolicy.isNeedFallback(statusCode)) {
					fallbackPolicy.getFallbackFunction().apply(SwiftApiCaller.this);
				}

				if (!expectResponseCodes.contains(statusCode)) {
					throw new SwiftUnexpectedStatusException("fail response from api [http status : " + statusCode + "]", statusCode, containerName);
				}
				return execute;
			} catch (ClientProtocolException e) {
				logger.debug(e.getMessage(), e);
				// in case of an http protocol error
				throw new SwiftApiCallException("ClientProtocolException : [" + requestBuilder.getUri() + "]" + e.getMessage(), 0, containerName);
			} catch (ConnectionPoolTimeoutException e) {
				logger.debug(e.getMessage(), e);
				throw new SwiftConnectionTimeoutException("ConnectionPoolTimeoutException. " + e.getMessage(), 0);
			} catch (SocketTimeoutException e) {
				logger.debug(e.getMessage(), e);
				throw new SwiftSocketTimeoutException("SocketTimeoutException. " + e.getMessage(), 0);
			} catch (IOException e) {
				logger.debug(e.getMessage(), e);
				// in case of a problem or the connection was aborted
				throw new SwiftApiCallException("IOException. " + e.getMessage(), 0);
			}
		}

		/**
		 * FallbackPolicy : status code == 404
		 * FallbackMethod : create container
		 *
		 * @return create container response
		 */
		public SwiftApiCaller.HttpExecutor addContainerNotFoundFallback() {
			Function<SwiftApiCaller, CloseableHttpResponse> createContainerFunction = swiftApiCaller -> swiftApiCaller.container().setName(SwiftApiCaller.this.containerName).executeWithoutHandler();
			this.fallbackPolicy = new FallbackPolicy(createContainerFunction, Collections.singletonList(404));
			return this;
		}

		public SwiftApiCaller.HttpExecutor addHeader(String key, String value) {
			SwiftApiCaller.this.addHeader(key, value);
			return this;
		}

		public SwiftApiCaller.HttpExecutor addHeader(Map<String, String> headers) {
			SwiftApiCaller.this.addHeader(headers);
			return this;
		}
	}

	public SwiftApiCaller.Files files() {
		return new SwiftApiCaller.Files();
	}

	public SwiftApiCaller.Container container() {
		return new SwiftApiCaller.Container();
	}

	public class Files {
		public SwiftApiCaller.HttpExecutor download(String containerName, String objectId) {
			return download(containerName, objectId, null);
		}

		public SwiftApiCaller.HttpExecutor download(String containerName, String objectId, String range) {
			SwiftApiCaller.this
					.createRequest("GET")
					.containerName(containerName)
					.addUri("/" + containerName + "/" + objectId)
					.expectResponseCode(GET_OK_CODE)
					.expectResponseCode(GET_PARTIAL_CONTENT_CODE);

			if (StringUtils.isNotBlank(range)) {
				SwiftApiCaller.this.addHeader("Range", range);
			}

			return new SwiftApiCaller.HttpExecutor();
		}

		public SwiftApiCaller.HttpExecutor upload(String containerName, String objectId, InputStream inputStream) {
			this.upload(containerName, objectId, inputStream, true);

			return new SwiftApiCaller.HttpExecutor();
		}

		public SwiftApiCaller.HttpExecutor upload(String containerName, String objectId, InputStream inputStream, boolean isAutoClose) {
			SwiftApiCaller.this
					.createRequest("PUT")
					.containerName(containerName)
					.addUri("/" + containerName + "/" + objectId)
					.inputStream(inputStream, isAutoClose)
					.expectResponseCode(UPLOAD_OK_CODE);

			return new SwiftApiCaller.HttpExecutor();
		}

		public SwiftApiCaller.HttpExecutor createFolder(String containerName, String objectId) throws IOException {
			SwiftApiCaller.this
					.createRequest("PUT")
					.containerName(containerName)
					.addUri("/" + containerName + "/" + objectId)
					.expectResponseCode(UPLOAD_OK_CODE);

			SwiftApiCaller.this.addHeader("Content-Length", "0");

			return new SwiftApiCaller.HttpExecutor();
		}

		public SwiftApiCaller.HttpExecutor copy(String containerName, String objectId, String copiedObjectId) {
			SwiftApiCaller.this
					.createRequest("COPY")
					.containerName(containerName)
					.addUri("/" + containerName + "/" + objectId)
					.addHeader("Destination", containerName + "/" + copiedObjectId)
					.expectResponseCode(UPLOAD_OK_CODE);

			return new SwiftApiCaller.HttpExecutor();
		}

		public SwiftApiCaller.HttpExecutor delete(String containerName, String objectId) {
			SwiftApiCaller.this
					.createRequest("DELETE")
					.containerName(containerName)
					.addUri("/" + containerName + "/" + objectId)
					.expectResponseCode(DELETE_OK_CODE);

			return new SwiftApiCaller.HttpExecutor();
		}

		public SwiftApiCaller.HttpExecutor bulkDelete(List<SwiftBulkOperation> operations) {
			SwiftApiCaller.this
					.createRequest("DELETE")
					.addUri("/?bulk-delete")
					.addBody(
							operations.stream()
									.filter(swiftBulkOperation -> StringUtils.isNotBlank(swiftBulkOperation.getContainerName()) && StringUtils.isNotBlank(swiftBulkOperation.getFileName()))
									.map(SwiftBulkOperation::toString).collect(Collectors.joining("\n"))
					)
					.expectResponseCode(GET_OK_CODE);

			return new SwiftApiCaller.HttpExecutor();
		}

		/**
		 * 하위 목록 조회 API 구현 추가하기
		 * - delimiter에  파라미터에 / 를 주면 하위 파일까지 안오고 폴더경로까지만 줌
		 */
		public SwiftApiCaller.ConvertHttpExecutor<List<String>> hierarchicalList(String containerName, String prefix, String delimiter, Integer limit) {
			SwiftApiCaller.this
					.createRequest("GET")
					.containerName(containerName)
					.addUri("/" + containerName)
					.addParameter("prefix", prefix);

			if (StringUtils.isNotBlank(delimiter)) {
				SwiftApiCaller.this.addParameter("delimiter", delimiter);
			}

			if (limit != null) {
				SwiftApiCaller.this.addParameter("limit", Integer.toString(limit));
			}

			return new ConvertHttpExecutor<>(response -> {
				int statusCode = response.getStatusLine().getStatusCode();

				if (statusCode == HttpStatus.SC_OK) {
					String responseBodyString = EntityUtils.toString(response.getEntity());
					return Arrays.asList(responseBodyString.split("\n"));

				} else if (statusCode == HttpStatus.SC_NO_CONTENT) {
					return Collections.emptyList();

				} else {
					throw new SwiftApiCallException("fail response from api [http status : " + statusCode + "]", statusCode);
				}
			});
		}

		public SwiftApiCaller.HttpExecutor head(String containerName, String objectName) {
			SwiftApiCaller.this
					.createRequest("HEAD")
					.containerName(containerName)
					.addUri("/" + containerName + "/" + objectName)
					.expectResponseCode(HEAD_OK_CODE);

			return new SwiftApiCaller.HttpExecutor();
		}
	}

	public class Container {

		public SwiftApiCaller.HttpExecutor setName(String containerName) {
			SwiftApiCaller.this
					.createRequest("PUT")
					.containerName(containerName)
					.addUri("/" + containerName)
					.expectResponseCode(HttpStatus.SC_CREATED)
					.expectResponseCode(HttpStatus.SC_ACCEPTED);

			return new SwiftApiCaller.HttpExecutor();
		}

		public SwiftApiCaller.HttpExecutor delete(String containerName) {
			SwiftApiCaller.this
					.createRequest("DELETE")
					.containerName(containerName)
					.addUri("/" + containerName)
					.expectResponseCode(HttpStatus.SC_NO_CONTENT);

			return new SwiftApiCaller.HttpExecutor();
		}

		public SwiftApiCaller.HttpExecutor get(String containerName) {
			SwiftApiCaller.this
					.createRequest("GET")
					.containerName(containerName)
					.addUri("/" + containerName)
					.expectResponseCode(HttpStatus.SC_OK)
					.expectResponseCode(HttpStatus.SC_NO_CONTENT);

			return new SwiftApiCaller.HttpExecutor();
		}

		public SwiftApiCaller.ConvertHttpExecutor<List<String>> list(String containerName) {
			SwiftApiCaller.this
					.createRequest("GET")
					.containerName(containerName)
					.addUri("/" + containerName);

			return new ConvertHttpExecutor<>(response -> {
				int statusCode = response.getStatusLine().getStatusCode();

				if (statusCode == HttpStatus.SC_OK) {
					String responseBodyString = EntityUtils.toString(response.getEntity());
					return Arrays.asList(responseBodyString.split("\n"));

				} else if (statusCode == HttpStatus.SC_NO_CONTENT) {
					return Collections.emptyList();

				} else {
					throw new SwiftApiCallException("fail response from api [http status : " + statusCode + "]", statusCode);
				}
			});
		}
	}

	private class FallbackPolicy {
		private List<Integer> fallbackStatusCode;
		private Function<SwiftApiCaller, CloseableHttpResponse> fallbackFunction;

		public FallbackPolicy(Function<SwiftApiCaller, CloseableHttpResponse> retryFunction, List<Integer> fallbackStatusCode) {
			this.fallbackFunction = retryFunction;
			this.fallbackStatusCode = fallbackStatusCode;
		}

		public boolean isNeedFallback(int statusCode) {
			return fallbackStatusCode.contains(statusCode);
		}

		public Function<SwiftApiCaller, CloseableHttpResponse> getFallbackFunction() {
			return fallbackFunction;
		}
	}
}

