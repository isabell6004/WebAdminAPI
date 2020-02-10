package net.fashiongo.webadmin.model.product.command.attribute;

import net.fashiongo.webadmin.model.product.type.attribute.AttributeType;
import net.fashiongo.webadmin.service.externalutil.FashionGoApiConfig;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.HttpMethod;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class AttributeRequestInfoTest {

	private static String fashionGoApiUrl = "http://localhost";

	@BeforeClass
	public static void init() {
		FashionGoApiConfig.fashionGoApi = fashionGoApiUrl;
	}

	@Test
	public void success_create() {
		// given
		AttributeType attributeType = AttributeType.LENGTH;
		String attributeName = "test";
		boolean attributeActive = true;

		// when
		AttributeRequestInfo info = AttributeRequestInfo.builder()
				.create()
				.attributeType(attributeType)
				.attributeName(attributeName)
				.attributeActive(attributeActive)
				.build();

		// then
		Assert.assertEquals(fashionGoApiUrl + "/v1.0/product/length", info.getEndpoint());
		Assert.assertEquals(HttpMethod.POST, info.getMethod());
		Assert.assertEquals(attributeName, ((AttributeSaveRequest) info.getPayload()).getName());
		Assert.assertEquals(attributeActive, ((AttributeSaveRequest) info.getPayload()).getActive());
	}

	@Test
	public void fail_create_illegal_argument() {
		// given
		AttributeType attributeType = AttributeType.PATTERN;
		String attributeName = "test";
		boolean attributeActive = true;

		// when
		boolean failed = false;
		try {
			// null name
			AttributeRequestInfo info = AttributeRequestInfo.builder()
					.create()
					.attributeType(attributeType)
					.attributeActive(attributeActive)
					.build();
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
			AttributeRequestInfo info = AttributeRequestInfo.builder()
					.create()
					.attributeType(attributeType)
					.attributeName("")
					.attributeActive(attributeActive)
					.build();
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
			AttributeRequestInfo info = AttributeRequestInfo.builder()
					.create()
					.attributeType(attributeType)
					.attributeName(attributeName)
					.build();
		} catch (IllegalArgumentException e) {
			failed = true;
			Assert.assertTrue(e.getMessage().startsWith("attributeActive"));
		}

		// then
		Assert.assertTrue(failed);
	}

	@Test
	public void success_update() {
		// given
		AttributeType attributeType = AttributeType.FABRIC;
		int attributeId = 1;
		String attributeName = "test";
		Boolean attributeActive = null;

		// when
		AttributeRequestInfo info = AttributeRequestInfo.builder()
				.update()
				.attributeType(attributeType)
				.attributeId(attributeId)
				.attributeName(attributeName)
				.attributeActive(attributeActive)
				.build();

		// then
		Assert.assertEquals(fashionGoApiUrl + "/v1.0/product/fabric/1", info.getEndpoint());
		Assert.assertEquals(HttpMethod.PUT, info.getMethod());
		Assert.assertEquals(attributeName, ((AttributeSaveRequest) info.getPayload()).getName());
		Assert.assertEquals(attributeActive, ((AttributeSaveRequest) info.getPayload()).getActive());
	}

	@Test
	public void fail_update_illegal_argument() {
		// given
		AttributeType attributeType = AttributeType.PATTERN;
		String attributeName = "test";
		boolean attributeActive = true;

		// when
		boolean failed = false;
		try {
			// null id
			AttributeRequestInfo info = AttributeRequestInfo.builder()
					.update()
					.attributeType(attributeType)
					.attributeName(attributeName)
					.attributeActive(attributeActive)
					.build();
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
			AttributeRequestInfo info = AttributeRequestInfo.builder()
					.update()
					.attributeType(attributeType)
					.attributeName("")
					.attributeActive(attributeActive)
					.build();
		} catch (IllegalArgumentException e) {
			failed = true;
		}

		// then
		Assert.assertTrue(failed);
	}

	@Test
	public void success_delete() {
		// given
		AttributeType attributeType = AttributeType.STYLE;
		List<Integer> targetIds = Stream.of(1, 2, 3).collect(Collectors.toList());

		// when
		AttributeRequestInfo info = AttributeRequestInfo.builder()
				.delete()
				.attributeType(attributeType)
				.targetIds(targetIds)
				.build();

		// then
		Assert.assertEquals(fashionGoApiUrl + "/v1.0/product/style/delete", info.getEndpoint());
		Assert.assertEquals(HttpMethod.POST, info.getMethod());
		Assert.assertEquals(targetIds.size(), ((AttributeDeleteRequest) info.getPayload()).getIds().size());
		Assert.assertTrue(targetIds.containsAll(((AttributeDeleteRequest) info.getPayload()).getIds()));
	}

	@Test
	public void success_mapping() {
		// given
		AttributeType attributeType = AttributeType.LENGTH;
		int categoryId = 1;
		List<Integer> targetIds = Stream.of(1, 2, 3).collect(Collectors.toList());

		// when
		AttributeRequestInfo info = AttributeRequestInfo.builder()
				.mapping()
				.attributeType(attributeType)
				.categoryId(categoryId)
				.targetIds(targetIds)
				.build();

		// then
		Assert.assertEquals(fashionGoApiUrl + "/v1.0/product/length/mapping", info.getEndpoint());
		Assert.assertEquals(HttpMethod.PUT, info.getMethod());
		Assert.assertEquals(categoryId, ((AttributeMappingRequest) info.getPayload()).getCategoryId());
		Assert.assertEquals(targetIds.size(), ((AttributeMappingRequest) info.getPayload()).getIds().size());
		Assert.assertTrue(targetIds.containsAll(((AttributeMappingRequest) info.getPayload()).getIds()));
	}
}
