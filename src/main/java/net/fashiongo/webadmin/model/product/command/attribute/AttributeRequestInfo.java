package net.fashiongo.webadmin.model.product.command.attribute;

import lombok.Getter;
import net.fashiongo.webadmin.model.product.type.attribute.AttributeType;
import org.springframework.http.HttpMethod;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Getter
public class AttributeRequestInfo {

	private final String endpoint;

	private final Object payload;

	private final HttpMethod method;

	public AttributeRequestInfo(String endpoint, Object payload, HttpMethod method) {
		this.endpoint = endpoint;
		this.payload = payload;
		this.method = method;
	}

	public static AttributeRequestInfoBuilder builder() {
		return new AttributeRequestInfoBuilder();
	}

	public static class AttributeRequestInfoBuilder {

		private AttributeRequestInfoBuilder() {
		}

		public Create create() {
			return new Create();
		}

		public Update update() {
			return new Update();
		}

		public Delete delete() {
			return new Delete();
		}

		public Mapping mapping() {
			return new Mapping();
		}

		public class Create extends AbstractProductRequest {
			private final String path = "/{attributeType}";

			private String attributeType;

			private String attributeName;

			private Boolean attributeActive;

			private Create() {
			}

			public Create attributeType(AttributeType attributeType) {
				Assert.notNull(attributeType, "attributeType must not be null");

				this.attributeType = attributeType.name().toLowerCase();
				return this;
			}

			public Create attributeName(String attributeName) {
				this.attributeName = attributeName;
				return this;
			}

			public Create attributeActive(boolean attributeActive) {
				this.attributeActive = attributeActive;
				return this;
			}

			@Override
			protected String buildEndpoint() {
				Assert.notNull(this.attributeType, "attributeType must not be null");

				return UriComponentsBuilder.fromHttpUrl(baseUrl)
						.path(path)
						.buildAndExpand(attributeType)
						.toUriString();
			}

			@Override
			protected Object makePayload() {
				Assert.isTrue(!StringUtils.isEmpty(this.attributeName), "attributeName must not be null or empty string when create");
				Assert.notNull(this.attributeActive, "attributeActive must not be null when create");

				return new AttributeSaveRequest(this.attributeName, this.attributeActive);
			}

			@Override
			protected HttpMethod getMethod() {
				return HttpMethod.POST;
			}
		}

		public class Update extends AbstractProductRequest {
			private final String path = "/{attributeType}/{attributeId}";

			private String attributeType;

			private Integer attributeId;

			private String attributeName;

			private Boolean attributeActive;

			private Update() {
			}

			public Update attributeType(AttributeType attributeType) {
				Assert.notNull(attributeType, "attributeType must not be null");

				this.attributeType = attributeType.name().toLowerCase();
				return this;
			}

			public Update attributeId(int attributeId) {
				this.attributeId = attributeId;
				return this;
			}

			public Update attributeName(String attributeName) {
				this.attributeName = attributeName;
				return this;
			}

			public Update attributeActive(Boolean attributeActive) {
				this.attributeActive = attributeActive;
				return this;
			}

			@Override
			protected String buildEndpoint() {
				Assert.notNull(this.attributeType, "attributeType must not be null");
				Assert.notNull(this.attributeId, "attributeId must not be null");

				return UriComponentsBuilder.fromHttpUrl(baseUrl)
						.path(path)
						.buildAndExpand(attributeType, attributeId)
						.toUriString();
			}

			@Override
			protected Object makePayload() {
				Optional.ofNullable(this.attributeName)
						.ifPresent(name -> Assert.isTrue(!"".equals(attributeName), "attributeName must not be empty string when update"));

				return new AttributeSaveRequest(this.attributeName, this.attributeActive);
			}

			@Override
			protected HttpMethod getMethod() {
				return HttpMethod.PUT;
			}
		}

		public class Delete extends AbstractProductRequest {
			private final String path = "/{attributeType}/delete";

			private String attributeType;

			private List<Integer> targetIds;

			private Delete() {
			}

			public Delete attributeType(AttributeType attributeType) {
				Assert.notNull(attributeType, "attributeType must not be null");

				this.attributeType = attributeType.name().toLowerCase();
				return this;
			}

			public Delete targetIds(List<Integer> targetIds) {
				this.targetIds = Collections.unmodifiableList(targetIds);
				return this;
			}

			@Override
			protected String buildEndpoint() {
				Assert.notNull(this.attributeType, "attributeType must not be null");

				return UriComponentsBuilder.fromHttpUrl(baseUrl)
						.path(path)
						.buildAndExpand(attributeType)
						.toUriString();
			}

			@Override
			protected Object makePayload() {
				Assert.notNull(this.targetIds, "targetIds must not be null");

				return new AttributeDeleteRequest(this.targetIds);
			}

			@Override
			protected HttpMethod getMethod() {
				return HttpMethod.POST;
			}
		}

		public class Mapping extends AbstractProductRequest {
			private final String path = "/{attributeType}/mapping";

			private String attributeType;

			private List<Integer> targetIds;

			private Integer categoryId;

			private Mapping() {
			}

			public Mapping attributeType(AttributeType attributeType) {
				Assert.notNull(attributeType, "attributeType must not be null");

				this.attributeType = attributeType.name().toLowerCase();
				return this;
			}

			public Mapping categoryId(int categoryId) {
				this.categoryId = categoryId;
				return this;
			}

			public Mapping targetIds(List<Integer> targetIds) {
				this.targetIds = Collections.unmodifiableList(targetIds);
				return this;
			}

			@Override
			protected String buildEndpoint() {
				Assert.notNull(this.attributeType, "attributeType must not be null");

				return UriComponentsBuilder.fromHttpUrl(baseUrl)
						.path(path)
						.buildAndExpand(attributeType)
						.toUriString();
			}

			@Override
			protected Object makePayload() {
				Assert.notNull(this.categoryId, "categoryId must not be null");
				Assert.notNull(this.targetIds, "targetIds must not be null");

				return new AttributeMappingRequest(this.categoryId, this.targetIds);
			}

			@Override
			protected HttpMethod getMethod() {
				return HttpMethod.PUT;
			}
		}
	}
}
