package net.fashiongo.webadmin.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fashiongo.webadmin.model.pojo.parameter.GetCollectionCategoryListParameters;
import net.fashiongo.webadmin.model.pojo.parameter.SetCollectionCategoryListorderParameters;
import net.fashiongo.webadmin.model.pojo.parameter.SetCollectionCategoryParameters;
import net.fashiongo.webadmin.model.pojo.response.GetCollectionCategoryListResponse;
import net.fashiongo.webadmin.model.pojo.response.SetCollectionCategoryListorderResponse;
import net.fashiongo.webadmin.model.primary.CollectionCategory2;
import net.fashiongo.webadmin.model.pojo.ResultResponse;
import net.fashiongo.webadmin.service.SitemgmtCollectionCategoryService;
import net.fashiongo.webadmin.utility.JsonResponse;

/*
 * @author Sanghyup Kim
 */
@Api(description = "Site Management", tags = { "sitemgmt" })
@RestController
@Consumes(MediaType.APPLICATION_JSON)
@RequestMapping(value = "/sitemgmt", produces = "application/json")
public class SitemgmtCollectionCategoryController {

	@Autowired
	SitemgmtCollectionCategoryService siteMgmtCollectionCategoryService;

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
	@ApiOperation("site management > collection category setting - get CollectionCategory List")
	public JsonResponse<GetCollectionCategoryListResponse> getCollectionCategoryList(
			@RequestBody GetCollectionCategoryListParameters parameters) {

		JsonResponse<GetCollectionCategoryListResponse> results = new JsonResponse<GetCollectionCategoryListResponse>();

		GetCollectionCategoryListResponse result = siteMgmtCollectionCategoryService
				.getCollectionCategoryList(parameters);
		results.setData(result);
		results.setSuccess(true);

		return results;
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
	@ApiOperation("site management > collection category setting - set CollectionCategory Listorder")
	public JsonResponse<SetCollectionCategoryListorderResponse> setCollectionCategoryListorder(
			@RequestBody SetCollectionCategoryListorderParameters parameters) {

		SetCollectionCategoryListorderResponse result = siteMgmtCollectionCategoryService
				.setCollectionCategoryListorder(parameters);
		List<CollectionCategory2> collectionCategory2 = result.getCategoryCollectionlist();

		result.setCategoryCollectionlist(collectionCategory2);

		JsonResponse<SetCollectionCategoryListorderResponse> results = new JsonResponse<SetCollectionCategoryListorderResponse>();
		results.setData(result);
		results.setSuccess(true);

		return results;
	}

	/**
	 * 
	 * set collection category
	 * 
	 * @since 2018. 10. 02.
	 * @author Sanghyup Kim
	 * @param SetCollectionCategoryParameters
	 * @return JsonResponse<Object>
	 */
	@RequestMapping(value = "setcollectioncategory", method = RequestMethod.POST)
	@ApiOperation("site management > collection category setting - set CollectionCategory (active, delete, save")
	public @ResponseBody JsonResponse<Integer> setCollectionCategory(
			@RequestBody SetCollectionCategoryParameters parameters) {

		JsonResponse<Integer> results = new JsonResponse<Integer>();

		String setType = parameters.getSetType();

		ResultResponse<Integer> result = new ResultResponse<Integer>();
		switch (setType) {
		case "Act":// change active/inactive
			result = siteMgmtCollectionCategoryService.setCollectionCategoryActive(parameters);

			break;
		case "Del":// delete
			result = siteMgmtCollectionCategoryService.setCollectionCategoryDelete(parameters);

			break;

		case "Add":// insert
		case "Upd":// update
			result = siteMgmtCollectionCategoryService.setCollectionCategory(parameters, setType);

		default:
			break;
		}

		results.setSuccess(result.getSuccess());
		results.setMessage(result.getMessage());
		results.setData(result.getPk());
//		results.setCode(result.getCode());
//		results.setPk(result.getPk());
		return results;
	}

	/**
	 * 
	 * set collection category policy
	 * 
	 * @since 2018. 10. 03.
	 * @author Sanghyup Kim
	 * @param
	 * @return JsonResponse<Object>
	 */
	@Deprecated
	@RequestMapping(value = "getcollectioncategorypolicy", method = RequestMethod.POST)
	@ApiOperation("[n/a]site management > collection category setting - get CollectionCategory Policy")
	public @ResponseBody JsonResponse<Integer> getCollectionCategoryPolicy() {
		JsonResponse<Integer> results = new JsonResponse<Integer>();

		return results;
	}

	/**
	 * 
	 * set collection category policy
	 * 
	 * @since 2018. 10. 03.
	 * @author Sanghyup Kim
	 * @param
	 * @return JsonResponse<Object>
	 */
	@Deprecated
	@RequestMapping(value = "setcollectioncategorypolicy", method = RequestMethod.POST)
	@ApiOperation("[n/a]site management > collection category setting - set CollectionCategory Policy")
	public @ResponseBody JsonResponse<Integer> setCollectionCategoryPolicy() {
		JsonResponse<Integer> results = new JsonResponse<Integer>();

		return results;
	}

	// collection category setting
	// ----------------------------------------------------

}
