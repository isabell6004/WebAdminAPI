package net.fashiongo.webadmin.model.product.command;

import net.fashiongo.webadmin.model.product.command.attribute.AttributeDeleteRequest;
import net.fashiongo.webadmin.model.product.command.attribute.AttributeMappingRequest;
import net.fashiongo.webadmin.model.product.command.attribute.AttributeSaveRequest;
import net.fashiongo.webadmin.model.product.command.category.CategoryListOrderRequest;
import net.fashiongo.webadmin.model.product.type.attribute.AttributeType;
import net.fashiongo.webadmin.service.externalutil.FashionGoApiConfig;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.HttpMethod;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ProductRequestInfoBuilderTest {

	private static String fashionGoApiUrl = "http://localhost";

	private static ProductRequestWrapper productRequestWrapper = new ProductRequestWrapper(null);

	@BeforeClass
	public static void init() {
		FashionGoApiConfig.fashionGoApi = fashionGoApiUrl;
	}

	@Test
	public void success_attribute_create() {
		// given
		AttributeType attributeType = AttributeType.LENGTHS;
		String attributeName = "test";
		boolean attributeActive = true;

		// when
		ProductRequestWrapper.AttributeRequest.Create create = productRequestWrapper.attribute()
				.create()
				.attributeType(attributeType)
				.attributeName(attributeName)
				.attributeActive(attributeActive);

		// then
		Assert.assertEquals(fashionGoApiUrl + "/v1.0/products/lengths", create.buildEndpoint());
		Assert.assertEquals(HttpMethod.POST, create.getMethod());
		Assert.assertEquals(attributeName, ((AttributeSaveRequest) create.makePayload()).getName());
		Assert.assertEquals(attributeActive, ((AttributeSaveRequest) create.makePayload()).getActive());
	}

	@Test
	public void fail_attribute__create_illegal_argument() {
		// given
		AttributeType attributeType = AttributeType.PATTERNS;
		String attributeName = "test";
		boolean attributeActive = true;

		// when
		boolean failed = false;
		try {
			// null name
			productRequestWrapper.attribute()
					.create()
					.attributeType(attributeType)
					.attributeActive(attributeActive)
					.makePayload();
		} catch (IllegalArgumentException e) {
			failed = true;
			Assert.assertTrue(e.getMessage().startsWith("attributeName"));
		}

		// then
		Assert.assertTrue(failed);

		// when
		failed = false;
		try {
			// empty name
			productRequestWrapper.attribute()
					.create()
					.attributeType(attributeType)
					.attributeName("")
					.attributeActive(attributeActive)
					.makePayload();
		} catch (IllegalArgumentException e) {
			failed = true;
			Assert.assertTrue(e.getMessage().startsWith("attributeName"));
		}

		// then
		Assert.assertTrue(failed);

		// when
		failed = false;
		try {
			// null active
			productRequestWrapper.attribute()
					.create()
					.attributeType(attributeType)
					.attributeName(attributeName)
					.makePayload();
		} catch (IllegalArgumentException e) {
			failed = true;
			Assert.assertTrue(e.getMessage().startsWith("attributeActive"));
		}

		// then
		Assert.assertTrue(failed);
	}

	@Test
	public void success_attribute_update() {
		// given
		AttributeType attributeType = AttributeType.FABRICS;
		int attributeId = 1;
		String attributeName = "test";
		Boolean attributeActive = null;

		// when
		ProductRequestWrapper.AttributeRequest.Update update = productRequestWrapper.attribute()
				.update()
				.attributeType(attributeType)
				.attributeId(attributeId)
				.attributeName(attributeName)
				.attributeActive(attributeActive);

		// then
		Assert.assertEquals(fashionGoApiUrl + "/v1.0/products/fabrics/1", update.buildEndpoint());
		Assert.assertEquals(HttpMethod.PUT, update.getMethod());
		Assert.assertEquals(attributeName, ((AttributeSaveRequest) update.makePayload()).getName());
		Assert.assertEquals(attributeActive, ((AttributeSaveRequest) update.makePayload()).getActive());
	}

	@Test
	public void fail_attribute_update_illegal_argument() {
		// given
		AttributeType attributeType = AttributeType.PATTERNS;
		String attributeName = "test";
		boolean attributeActive = true;

		// when
		boolean failed = false;
		try {
			// null id
			productRequestWrapper.attribute()
					.update()
					.attributeType(attributeType)
					.attributeName(attributeName)
					.attributeActive(attributeActive)
					.buildEndpoint();
		} catch (IllegalArgumentException e) {
			failed = true;
			Assert.assertTrue(e.getMessage().startsWith("attributeId"));
		}

		// then
		Assert.assertTrue(failed);

		// when
		failed = false;
		try {
			// empty name
			productRequestWrapper.attribute()
					.update()
					.attributeType(attributeType)
					.attributeName("")
					.attributeActive(attributeActive)
					.makePayload();
		} catch (IllegalArgumentException e) {
			failed = true;
		}

		// then
		Assert.assertTrue(failed);
	}

	@Test
	public void success_attribute_delete() {
		// given
		AttributeType attributeType = AttributeType.STYLES;
		List<Integer> targetIds = Stream.of(1, 2, 3).collect(Collectors.toList());

		// when
		ProductRequestWrapper.AttributeRequest.Delete delete = productRequestWrapper.attribute()
				.delete()
				.attributeType(attributeType)
				.targetIds(targetIds);

		// then
		Assert.assertEquals(fashionGoApiUrl + "/v1.0/products/styles/1,2,3", delete.buildEndpoint());
		Assert.assertEquals(HttpMethod.DELETE, delete.getMethod());
	}

	@Test
	public void success_attribute_mapping() {
		// given
		AttributeType attributeType = AttributeType.LENGTHS;
		int categoryId = 1;
		List<Integer> targetIds = Stream.of(1, 2, 3).collect(Collectors.toList());

		// when
		ProductRequestWrapper.AttributeRequest.Mapping mapping = productRequestWrapper.attribute()
				.mapping()
				.attributeType(attributeType)
				.categoryId(categoryId)
				.targetIds(targetIds);

		// then
		Assert.assertEquals(fashionGoApiUrl + "/v1.0/products/lengths/mapping", mapping.buildEndpoint());
		Assert.assertEquals(HttpMethod.PUT, mapping.getMethod());
		Assert.assertEquals(categoryId, ((AttributeMappingRequest) mapping.makePayload()).getCategoryId());
		Assert.assertEquals(targetIds.size(), ((AttributeMappingRequest) mapping.makePayload()).getIds().size());
		Assert.assertTrue(targetIds.containsAll(((AttributeMappingRequest) mapping.makePayload()).getIds()));
	}

	@Test
	public void success_category_create() {
		String categoryName = "test";
		int parentCategoryId = 1;
		int level = 2;
		int listOrder = 1;

		// when
		ProductRequestWrapper.CategoryRequest.Create create = productRequestWrapper.category()
				.create()
				.categoryName(categoryName)
				.parentCategoryId(parentCategoryId)
				.level(level)
				.listOrder(listOrder);

		// then
		Assert.assertEquals(fashionGoApiUrl + "/v1.0/products/categories", create.buildEndpoint());
		Assert.assertEquals(HttpMethod.POST, create.getMethod());
	}

	@Test
	public void success_category_update() {
		int categoryId = 1;
		String categoryName = "test";
		int parentCategoryId = 1;
		int level = 2;
		int listOrder = 1;

		// when
		ProductRequestWrapper.CategoryRequest.Update update = productRequestWrapper.category()
				.update()
				.categoryId(categoryId)
				.categoryName(categoryName)
				.parentCategoryId(parentCategoryId)
				.level(level)
				.listOrder(listOrder);

		// then
		Assert.assertEquals(fashionGoApiUrl + "/v1.0/products/categories/1", update.buildEndpoint());
		Assert.assertEquals(HttpMethod.PUT, update.getMethod());
	}

	@Test
	public void success_category_delete() {
		// given
		int categoryId = 1;

		// when
		ProductRequestWrapper.CategoryRequest.Delete delete = productRequestWrapper.category()
				.delete()
				.categoryId(categoryId);

		// then
		Assert.assertEquals(fashionGoApiUrl + "/v1.0/products/categories/1", delete.buildEndpoint());
		Assert.assertEquals(HttpMethod.DELETE, delete.getMethod());
		Assert.assertNull(delete.makePayload());
	}

	@Test
	public void success_category_activate() {
		// given
		int categoryId = 1;
		boolean categoryActive = true;

		// when
		ProductRequestWrapper.CategoryRequest.Activate activate = productRequestWrapper.category()
				.activate()
				.categoryId(categoryId)
				.categoryActive(categoryActive);

		// then
		Assert.assertEquals(fashionGoApiUrl + "/v1.0/products/categories/1/activity", activate.buildEndpoint());
		Assert.assertEquals(HttpMethod.PUT, activate.getMethod());
		Assert.assertTrue((boolean) activate.makePayload());
	}

	@Test
	public void success_category_list_order() {
		// given
		int categoryId = 2;
		int parentCategoryId = 1;
		int listOrder = 2;

		// when
		ProductRequestWrapper.CategoryRequest.ListOrder request = productRequestWrapper.category()
				.listOrder()
				.categoryId(categoryId)
				.parentCategoryId(parentCategoryId)
				.listOrder(listOrder);

		// then
		Assert.assertEquals(fashionGoApiUrl + "/v1.0/products/categories/2/list-order", request.buildEndpoint());
		Assert.assertEquals(HttpMethod.PUT, request.getMethod());
		Assert.assertEquals(1, (((CategoryListOrderRequest) request.makePayload()).getParentId()));
		Assert.assertEquals(2, (((CategoryListOrderRequest) request.makePayload()).getListOrder()));
	}
}
