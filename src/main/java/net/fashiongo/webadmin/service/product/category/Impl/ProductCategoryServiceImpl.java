package net.fashiongo.webadmin.service.product.category.Impl;

import net.fashiongo.webadmin.model.pojo.common.ResultResponse;
import net.fashiongo.webadmin.model.pojo.login.WebAdminLoginUser;
import net.fashiongo.webadmin.model.pojo.sitemgmt.CategoryListOrder;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.SetCategoryListOrderParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.SetCategoryParameter;
import net.fashiongo.webadmin.model.product.command.ProductRequestWrapper;
import net.fashiongo.webadmin.model.product.command.category.CategoryResponse;
import net.fashiongo.webadmin.model.product.command.category.ProductCategoryResponse;
import net.fashiongo.webadmin.model.product.type.category.CategoryRequestType;
import net.fashiongo.webadmin.service.CacheService;
import net.fashiongo.webadmin.service.externalutil.FashionGoApiConfig;
import net.fashiongo.webadmin.service.externalutil.FashionGoApiHeader;
import net.fashiongo.webadmin.service.externalutil.HttpClientWrapper;
import net.fashiongo.webadmin.service.externalutil.response.CollectionObject;
import net.fashiongo.webadmin.service.externalutil.response.FashionGoApiResponse;
import net.fashiongo.webadmin.service.externalutil.response.SingleObject;
import net.fashiongo.webadmin.service.product.category.ProductCategoryService;
import net.fashiongo.webadmin.utility.Utility;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

	private final ProductRequestWrapper productRequestWrapper;

	private final CacheService cacheService;

	private final HttpClientWrapper httpCaller;

	public ProductCategoryServiceImpl(ProductRequestWrapper productRequestWrapper, CacheService cacheService, HttpClientWrapper httpCaller) {
		this.productRequestWrapper = productRequestWrapper;
		this.cacheService = cacheService;
		this.httpCaller = httpCaller;
	}

	@Override
	public ResultResponse<Void> setCategory(SetCategoryParameter parameter) {
		FashionGoApiResponse<?> response = parseAndExecute(parameter);

		ResultResponse<Void> result = makeResultResponse(response.getHeader().isSuccessful(), response.getHeader().getResultMessage(), extractPk(response), null);

		try {
			cacheService.GetRedisCacheEvict("HierarchicalMenuCategories", null);
		} catch (Exception ignored) {
		}

		return result;
	}

	private FashionGoApiResponse<?> parseAndExecute(SetCategoryParameter parameter) {
		CategoryRequestType requestType = CategoryRequestType.fromSetType(parameter.getSettype());

		switch (requestType) {
			case ADD:
				return productRequestWrapper.category()
						.create()
						.level(parameter.getObjCategory().getLvl())
						.parentCategoryId(parameter.getObjCategory().getParentCategoryID())
						.parentParentCategoryId(parameter.getObjCategory().getParentParentCategoryID())
						.categoryName(parameter.getObjCategory().getCategoryName())
						.categoryDescription(parameter.getObjCategory().getCategoryDescription())
						.titleImage(parameter.getObjCategory().getTitleImage())
						.isLandingPage(parameter.getObjCategory().getIsLandingPage())
						.isFeatured(parameter.getObjCategory().getIsFeatured())
						.listOrder(parameter.getObjCategory().getListOrder())
						.isActive(parameter.getObjCategory().getActive())
						.execute();
			case UPDATE:
				return productRequestWrapper.category()
						.update()
						.categoryId(parameter.getObjCategory().getCategoryID())
						.level(parameter.getObjCategory().getLvl())
						.parentCategoryId(parameter.getObjCategory().getParentCategoryID())
						.parentParentCategoryId(parameter.getObjCategory().getParentParentCategoryID())
						.categoryName(parameter.getObjCategory().getCategoryName())
						.categoryDescription(parameter.getObjCategory().getCategoryDescription())
						.titleImage(parameter.getObjCategory().getTitleImage())
						.isLandingPage(parameter.getObjCategory().getIsLandingPage())
						.isFeatured(parameter.getObjCategory().getIsFeatured())
						.listOrder(parameter.getObjCategory().getListOrder())
						.isActive(parameter.getObjCategory().getActive())
						.execute();
			case ACTIVATE:
				return productRequestWrapper.category()
						.activate()
						.categoryId(parameter.getObjCategory().getCategoryID())
						.categoryActive(parameter.getObjCategory().getActive())
						.execute();
			case DELETE:
				return productRequestWrapper.category()
						.delete()
						.categoryId(parameter.getObjCategory().getCategoryID())
						.execute();
			default:
				throw new RuntimeException("internal server error");
		}
	}

	private Integer extractPk(FashionGoApiResponse<?> response) {
		return (Integer) Optional.ofNullable(response.getData())
				.filter(data -> data instanceof SingleObject)
				.map(data -> (SingleObject<?>) data)
				.map(SingleObject::getContent)
				.filter(data -> data instanceof Integer)
				.orElse(null);
	}

	@Override
	public ResultResponse<List<CategoryListOrder>> setCategoryListOrder(SetCategoryListOrderParameter parameter) {
		FashionGoApiResponse<CollectionObject<CategoryResponse>> response = executeListOrder(parameter);

		return makeResultResponse(response.getHeader().isSuccessful(), response.getHeader().getResultMessage(), null, response.getData()
				.getContents()
				.stream()
				.map(categoryResponse -> new CategoryListOrder(categoryResponse.getCategoryId(),
						categoryResponse.getParentCategoryId(),
						categoryResponse.getCategoryName(),
						categoryResponse.getLevel(),
						categoryResponse.getListOrder(),
						categoryResponse.isActive()))
				.collect(Collectors.toList()));
	}

	public CollectionObject<ProductCategoryResponse> getCategories() {
		final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/products/categories";

		WebAdminLoginUser userInfo = Utility.getUserInfo();


		FashionGoApiResponse<CollectionObject<ProductCategoryResponse>> response = httpCaller.get(endpoint, FashionGoApiHeader.getHeader(userInfo.getUserId(), userInfo.getUsername()),
				new ParameterizedTypeReference<FashionGoApiResponse<CollectionObject<ProductCategoryResponse>>>() {});

		if (response == null) {
			throw new RuntimeException("unknown exception occurred while calling fashiongo api.");
		}

		if (response.getHeader().isSuccessful()) {
			return response.getData();
		} else {
			throw new RuntimeException("fail to call fashiongo api: " + response.getHeader().getResultMessage());
		}
	}

	private FashionGoApiResponse<CollectionObject<CategoryResponse>> executeListOrder(SetCategoryListOrderParameter parameter) {
		return productRequestWrapper.category()
				.listOrder()
				.categoryId(parameter.getCategoryid())
				.parentCategoryId(Optional.ofNullable(parameter.getParentcategoryid()).orElse(0))
				.listOrder(parameter.getListorder())
				.execute();
	}

	private <T> ResultResponse<T> makeResultResponse(boolean isSuccessful, String message, Integer pk, T data) {
		if (isSuccessful) {
			return new ResultResponse<>(true, 1, pk, "success", data);
		} else {
			return new ResultResponse<>(false, 0, pk, message, data);
		}
	}
}
