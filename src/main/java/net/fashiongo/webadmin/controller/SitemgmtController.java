package net.fashiongo.webadmin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.fashiongo.common.JsonResponse;
import net.fashiongo.webadmin.model.pojo.parameter.GetCollectionCategoryListParameters;
import net.fashiongo.webadmin.model.pojo.parameter.SetCollectionCategoryListorderParameters;
import net.fashiongo.webadmin.model.pojo.parameter.SetCollectionCategoryParameters;
import net.fashiongo.webadmin.model.pojo.response.GetCollectionCategoryListResponse;
import net.fashiongo.webadmin.model.pojo.response.SetCollectionCategoryListorderResponse;
import net.fashiongo.webadmin.model.primary.CollectionCategory2;
import net.fashiongo.webadmin.model.pojo.ResultResponse;
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

//		JsonResponse<GetCollectionCategoryListResponse> results = new JsonResponse<GetCollectionCategoryListResponse>(
//				false, null, null);

		GetCollectionCategoryListResponse result = sitemgmtService.getCollectionCategoryList(parameters);
//		results.setData(result);
//		results.setSuccess(true);
//		return results.getData();
		return result;
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

//		JsonResponse<GetCategoryListResponse> results = new JsonResponse<GetCategoryListResponse>(false, null, null);

		GetCategoryListResponse result = sitemgmtService.getCategoryList(parameters);
//		results.setData(result);
//		results.setSuccess(true);

//		return results.getData();
		return result;
	}

	/**
	 * 
	 * set collection category listorder
	 * 
	 * @since 2018. 10. 01.
	 * @author Sanghyup Kim
	 * @param SetCollectionCategoryListorderParameters {collectionCategoryID,
	 *                                                 parentCollectionCategoryID,
	 *                                                 lvl, listOrder}
	 * @return JsonResponse<Object>
	 */
	@RequestMapping(value = "setcollectioncategorylistorder", method = RequestMethod.POST)
	public JsonResponse<Object> setCollectionCategoryListorder(
			@RequestBody SetCollectionCategoryListorderParameters parameters) {

		SetCollectionCategoryListorderResponse result = sitemgmtService.setCollectionCategoryListorder(parameters);
		List<CollectionCategory2> collectionCategory2 = result.getCategoryCollectionlist();

		JsonResponse<Object> results = new JsonResponse<Object>(false, null, null);
		results.setData(collectionCategory2);
		results.setSuccess(true);

		return results;
	}

	/**
	 * 
	 * set collection category
	 * 
	 * @since 2018. 10. 01.
	 * @author Sanghyup Kim
	 * @param SetCollectionCategoryParameters
	 * @return JsonResponse<Object>
	 */
	@RequestMapping(value = "setcollectioncategory", method = RequestMethod.POST)
	public @ResponseBody ResultResponse<Object> setCollectionCategory(
			@RequestBody SetCollectionCategoryParameters parameters) {

		String setType = parameters.getSetType();

		ResultResponse<Object> result = new ResultResponse<Object>();
		switch (setType) {
		case "Act":// change active/inactive
			result = sitemgmtService.setCollectionCategoryActive(parameters);

			break;
		case "Del":// delete
			result = sitemgmtService.setCollectionCategoryDelete(parameters);

			break;

		case "Add":// insert
		case "Upd":// update
			result = sitemgmtService.setCollectionCategory(parameters, setType);

		default:
			break;
		}

		return result;
	}

	// collection category setting
	// ----------------------------------------------------

}
