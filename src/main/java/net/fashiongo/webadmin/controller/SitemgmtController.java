package net.fashiongo.webadmin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.fashiongo.common.JsonResponse;
import net.fashiongo.webadmin.model.pojo.parameter.GetCollectionCategoryListParameters;
import net.fashiongo.webadmin.model.pojo.response.GetCollectionCategoryListResponse;
import net.fashiongo.webadmin.model.pojo.parameter.GetCategoryListParameters;
import net.fashiongo.webadmin.model.pojo.response.GetCategoryListResponse;
import net.fashiongo.webadmin.service.SitemgmtService;

/*
 * @author Sanghyup Kim
 */
@RestController
@RequestMapping(value = "/sitemgmt", produces = "application/json")
public class SitemgmtController {

	@Autowired
	SitemgmtService sitemgmtService;

	// ----------------------------------------------------
	// collection category setting
	/**
	 * 
	 * get Collection Category List
	 * 
	 * @since 2018. 9. 28.
	 * @author Sanghyup Kim
	 * @param GetCollectionCategoryListParameters
	 * @return GetCollectionCategoryListResponse
	 */
	@RequestMapping(value = "getcollectioncategorylist", method = RequestMethod.POST)
	public GetCollectionCategoryListResponse getCollectionCategoryList(
			@RequestBody GetCollectionCategoryListParameters parameters) {
		JsonResponse<GetCollectionCategoryListResponse> results = new JsonResponse<GetCollectionCategoryListResponse>(
				false, null, null);

		GetCollectionCategoryListResponse result = sitemgmtService.GetCollectionCategoryList(parameters);
		results.setData(result);
		results.setSuccess(true);

		return results.getData();
	}

	/**
	 * 
	 * get Category List
	 * 
	 * @since 2018. 9. 28.
	 * @author Sanghyup Kim
	 * @param GetCategoryListParameters
	 * @return GetCategoryListResponse
	 */
	@RequestMapping(value = "getcategorylist", method = RequestMethod.POST)
	public GetCategoryListResponse getCategoryList(@RequestBody GetCategoryListParameters parameters) {
		JsonResponse<GetCategoryListResponse> results = new JsonResponse<GetCategoryListResponse>(false, null, null);

		GetCategoryListResponse result = sitemgmtService.GetCategoryList(parameters);
		results.setData(result);
		results.setSuccess(true);

		return results.getData();
	}

	// collection category setting
	// ----------------------------------------------------

}
