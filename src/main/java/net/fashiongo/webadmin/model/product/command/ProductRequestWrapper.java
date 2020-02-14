package net.fashiongo.webadmin.model.product.command;

import com.fasterxml.jackson.core.type.TypeReference;
import net.fashiongo.webadmin.model.product.command.attribute.AttributeMappingRequest;
import net.fashiongo.webadmin.model.product.command.attribute.AttributeSaveRequest;
import net.fashiongo.webadmin.model.product.command.category.CategoryListOrderRequest;
import net.fashiongo.webadmin.model.product.command.category.CategoryResponse;
import net.fashiongo.webadmin.model.product.command.category.CategorySaveRequest;
import net.fashiongo.webadmin.model.product.type.attribute.AttributeType;
import net.fashiongo.webadmin.service.externalutil.HttpClientWrapper;
import net.fashiongo.webadmin.service.externalutil.response.CollectionObject;
import net.fashiongo.webadmin.service.externalutil.response.FashionGoApiResponse;
import net.fashiongo.webadmin.service.externalutil.response.SingleObject;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProductRequestWrapper {

	private final HttpClientWrapper httpCaller;

	public ProductRequestWrapper(HttpClientWrapper httpCaller) {
		this.httpCaller = httpCaller;
	}

	public AttributeRequest attribute() {
		return new AttributeRequest();
	}

	public CategoryRequest category() {
		return new CategoryRequest();
	}

	public class AttributeRequest {

		private AttributeRequest() {
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

		public class Create extends AbstractProductRequest<SingleObject<Integer>> {

			private final String path = "/{attributeType}";

			private String attributeType;

			private String attributeName;

			private Boolean attributeActive;

			private Create() {
				super(ProductRequestWrapper.this.httpCaller);
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

		public class Update extends AbstractProductRequest<Void> {

			private final String path = "/{attributeType}/{attributeId}";

			private String attributeType;

			private Integer attributeId;

			private String attributeName;

			private Boolean attributeActive;

			private Update() {
				super(ProductRequestWrapper.this.httpCaller);
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

		public class Delete extends AbstractProductRequest<Void> {

			private final String path = "/{attributeType}/{ids}";

			private String attributeType;

			private List<Integer> targetIds;

			private Delete() {
				super(ProductRequestWrapper.this.httpCaller);
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
				Assert.notEmpty(this.targetIds, "targetIds must not be empty");

				String targetIdString = targetIds.stream()
						.map(Object::toString)
						.collect(Collectors.joining(","));

				return UriComponentsBuilder.fromHttpUrl(baseUrl)
						.path(path)
						.buildAndExpand(attributeType, targetIdString)
						.toUriString();
			}

			@Override
			protected Object makePayload() {
				return null;
			}

			@Override
			protected HttpMethod getMethod() {
				return HttpMethod.DELETE;
			}
		}

		public class Mapping extends AbstractProductRequest<Void> {

			private final String path = "/{attributeType}/mapping";

			private String attributeType;

			private List<Integer> targetIds;

			private Integer categoryId;

			private Mapping() {
				super(ProductRequestWrapper.this.httpCaller);
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

	public class CategoryRequest {

		private CategoryRequest() {
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

		public Activate activate() {
			return new Activate();
		}

		public ListOrder listOrder() {
			return new ListOrder();
		}

		public class Create extends AbstractProductRequest<SingleObject<Integer>> {

			private final String path = "/categories";

			private String categoryName;

			private String categoryDescription;

			private Integer parentCategoryId;

			private Integer parentParentCategoryId;

			private Integer level;

			private String titleImage;

			private boolean isLandingPage;

			private boolean isFeatured;

			private Integer listOrder;

			private boolean isActive;

			private Create() {
				super(ProductRequestWrapper.this.httpCaller);
			}

			public Create categoryName(String categoryName) {
				this.categoryName = categoryName;

				return this;
			}

			public Create categoryDescription(String categoryDescription) {
				this.categoryDescription = categoryDescription;

				return this;
			}

			public Create parentCategoryId(int parentCategoryId) {
				this.parentCategoryId = parentCategoryId;

				return this;
			}

			public Create parentParentCategoryId(int parentParentCategoryId) {
				this.parentParentCategoryId = parentParentCategoryId;

				return this;
			}

			public Create level(int level) {
				this.level = level;

				return this;
			}

			public Create titleImage(String titleImage) {
				this.titleImage = titleImage;

				return this;
			}

			public Create isLandingPage(boolean isLandingPage) {
				this.isLandingPage = isLandingPage;

				return this;
			}

			public Create isFeatured(boolean isFeatured) {
				this.isFeatured = isFeatured;

				return this;
			}

			public Create listOrder(int listOrder) {
				this.listOrder = listOrder;

				return this;
			}

			public Create isActive(boolean isActive) {
				this.isActive = isActive;

				return this;
			}

			@Override
			protected String buildEndpoint() {
				return UriComponentsBuilder.fromHttpUrl(baseUrl)
						.path(path)
						.toUriString();
			}

			@Override
			protected Object makePayload() {
				return new CategorySaveRequest(categoryName,
						categoryDescription,
						parentCategoryId,
						parentParentCategoryId,
						level,
						titleImage,
						isLandingPage,
						isFeatured,
						listOrder,
						isActive);
			}

			@Override
			protected HttpMethod getMethod() {
				return HttpMethod.POST;
			}

			@Override
			protected FashionGoApiResponse<SingleObject<Integer>> parseResponse(String responseBody) {
				try {
					return getObjectMapper().readValue(responseBody, new TypeReference<FashionGoApiResponse<SingleObject<Integer>>>() {
					});
				} catch (IOException e) {
					throw new RuntimeException("internal server error");
				}
			}
		}

		public class Update extends AbstractProductRequest<Void> {

			private final String path = "/categories/{categoryId}";

			private Integer categoryId;

			private String categoryName;

			private String categoryDescription;

			private Integer parentCategoryId;

			private Integer parentParentCategoryId;

			private Integer level;

			private String titleImage;

			private boolean isLandingPage;

			private boolean isFeatured;

			private Integer listOrder;

			private boolean isActive;

			private Update() {
				super(ProductRequestWrapper.this.httpCaller);
			}

			public Update categoryId(int categoryId) {
				this.categoryId = categoryId;

				return this;
			}

			public Update categoryName(String categoryName) {
				this.categoryName = categoryName;

				return this;
			}

			public Update categoryDescription(String categoryDescription) {
				this.categoryDescription = categoryDescription;

				return this;
			}

			public Update parentCategoryId(int parentCategoryId) {
				this.parentCategoryId = parentCategoryId;

				return this;
			}

			public Update parentParentCategoryId(int parentParentCategoryId) {
				this.parentParentCategoryId = parentParentCategoryId;

				return this;
			}

			public Update level(int level) {
				this.level = level;

				return this;
			}

			public Update titleImage(String titleImage) {
				this.titleImage = titleImage;

				return this;
			}

			public Update isLandingPage(boolean isLandingPage) {
				this.isLandingPage = isLandingPage;

				return this;
			}

			public Update isFeatured(boolean isFeatured) {
				this.isFeatured = isFeatured;

				return this;
			}

			public Update listOrder(int listOrder) {
				this.listOrder = listOrder;

				return this;
			}

			public Update isActive(boolean isActive) {
				this.isActive = isActive;

				return this;
			}

			@Override
			protected String buildEndpoint() {
				Assert.notNull(categoryId, "categoryId must not be null");

				return UriComponentsBuilder.fromHttpUrl(baseUrl)
						.path(path)
						.buildAndExpand(categoryId)
						.toUriString();
			}

			@Override
			protected Object makePayload() {
				return new CategorySaveRequest(categoryName,
						categoryDescription,
						parentCategoryId,
						parentParentCategoryId,
						level,
						titleImage,
						isLandingPage,
						isFeatured,
						listOrder,
						isActive);
			}

			@Override
			protected HttpMethod getMethod() {
				return HttpMethod.PUT;
			}
		}

		public class Delete extends AbstractProductRequest<Void> {

			private final String path = "/categories/{categoryId}";

			private Integer categoryId;

			public Delete categoryId(int categoryId) {
				this.categoryId = categoryId;

				return this;
			}

			private Delete() {
				super(ProductRequestWrapper.this.httpCaller);
			}

			@Override
			protected String buildEndpoint() {
				Assert.notNull(categoryId, "categoryId must not be null");

				return UriComponentsBuilder.fromHttpUrl(baseUrl)
						.path(path)
						.buildAndExpand(categoryId)
						.toUriString();
			}

			@Override
			protected Object makePayload() {
				return null;
			}

			@Override
			protected HttpMethod getMethod() {
				return HttpMethod.DELETE;
			}
		}

		public class Activate extends AbstractProductRequest<Void> {

			private final String path = "/categories/{categoryId}/activity";

			private Integer categoryId;

			private Boolean categoryActive;

			private Activate() {
				super(ProductRequestWrapper.this.httpCaller);
			}

			public Activate categoryId(int categoryId) {
				this.categoryId = categoryId;

				return this;
			}

			public Activate categoryActive(boolean categoryActive) {
				this.categoryActive = categoryActive;

				return this;
			}

			@Override
			protected String buildEndpoint() {
				Assert.notNull(categoryId, "categoryId must not be null");

				return UriComponentsBuilder.fromHttpUrl(baseUrl)
						.path(path)
						.buildAndExpand(categoryId)
						.toUriString();
			}

			@Override
			protected Object makePayload() {
				Assert.notNull(categoryActive, "categoryActive must not be null");

				return categoryActive;
			}

			@Override
			protected HttpMethod getMethod() {
				return HttpMethod.PUT;
			}
		}

		public class ListOrder extends AbstractProductRequest<CollectionObject<CategoryResponse>> {

			private final String path = "/categories/{categoryId}/list-order";

			private Integer categoryId;

			private Integer parentCategoryId;

			private Integer listOrder;

			private ListOrder() {
				super(ProductRequestWrapper.this.httpCaller);
			}

			public ListOrder categoryId(int categoryId) {
				this.categoryId = categoryId;

				return this;
			}

			public ListOrder parentCategoryId(int parentCategoryId) {
				this.parentCategoryId = parentCategoryId;

				return this;
			}

			public ListOrder listOrder(Integer listOrder) {
				this.listOrder = listOrder;

				return this;
			}

			@Override
			protected String buildEndpoint() {
				Assert.notNull(categoryId, "categoryId must not be null");

				return UriComponentsBuilder.fromHttpUrl(baseUrl)
						.path(path)
						.buildAndExpand(categoryId)
						.toUriString();
			}

			@Override
			protected Object makePayload() {
				Assert.notNull(parentCategoryId, "parentCategoryId must not be null");
				Assert.notNull(listOrder, "listOrder must not be null");

				return new CategoryListOrderRequest(parentCategoryId, listOrder);
			}

			@Override
			protected HttpMethod getMethod() {
				return HttpMethod.PUT;
			}

			@Override
			protected FashionGoApiResponse<CollectionObject<CategoryResponse>> parseResponse(String responseBody) {
				try {
					return getObjectMapper().readValue(responseBody, new TypeReference<FashionGoApiResponse<CollectionObject<CategoryResponse>>>() {
					});
				} catch (IOException e) {
					throw new RuntimeException("internal server error");
				}
			}
		}
	}
}
