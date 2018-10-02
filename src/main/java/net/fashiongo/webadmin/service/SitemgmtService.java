package net.fashiongo.webadmin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Isolation;

import net.fashiongo.webadmin.model.pojo.parameter.GetCollectionCategoryListParameters;
import net.fashiongo.webadmin.model.pojo.parameter.SetCollectionCategoryListorderParameters;
import net.fashiongo.webadmin.model.pojo.parameter.SetCollectionCategoryParameters;
import net.fashiongo.webadmin.model.pojo.response.GetCollectionCategoryListResponse;
import net.fashiongo.webadmin.model.pojo.response.SetCollectionCategoryListorderResponse;
import net.fashiongo.webadmin.dao.primary.CollectionCategory2Repository;
import net.fashiongo.webadmin.dao.primary.CollectionCategoryRepository;
import net.fashiongo.webadmin.dao.primary.MapCollectionCategoryRepository;
import net.fashiongo.webadmin.model.pojo.ResultResponse;
import net.fashiongo.webadmin.model.pojo.Total;
import net.fashiongo.webadmin.model.pojo.parameter.GetCategoryListParameters;
import net.fashiongo.webadmin.model.pojo.response.GetCategoryListResponse;
import net.fashiongo.webadmin.model.primary.AdPageSpot;
import net.fashiongo.webadmin.model.primary.CollectionCategory;
import net.fashiongo.webadmin.model.primary.CollectionCategory2;
import net.fashiongo.webadmin.model.primary.MapCollectionCategory;
import net.fashiongo.webadmin.model.primary.Category;

/**
 * 
 * @author Sanghyup Kim
 */
@Service
public class SitemgmtService extends ApiService {

	@Autowired
	private CollectionCategoryRepository collectionCategoryRepository;

	@Autowired
	private CollectionCategory2Repository collectionCategory2Repository;

	@Autowired
	private MapCollectionCategoryRepository mapCollectionCategoryRepository;

	/**
	 * 
	 * Get Collection Category List
	 * 
	 * @since 2018. 9. 28.
	 * @author Sanghyup Kim
	 * @param GetCollectionCategoryListParameters
	 * @return GetCollectionCategoryListResponse
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED, transactionManager = "primaryTransactionManager")
	public GetCollectionCategoryListResponse getCollectionCategoryList(GetCollectionCategoryListParameters parameters) {

		List<Object> params = new ArrayList<Object>();

		// add parameters
		params.add(parameters.getCategoryId());
		params.add(parameters.getExpandAll());
		String spName = "up_wa_GetCollectionCategory";

		GetCollectionCategoryListResponse resultSet = new GetCollectionCategoryListResponse();
		if ((int) params.get(0) == 0) { // list
			List<Object> _result = jdbcHelper.executeSP(spName, params, CollectionCategory.class);
			List<CollectionCategory> collectionCategoryList = (List<CollectionCategory>) _result.get(0);
			resultSet.setCollectionCategoryList(collectionCategoryList);
		} else { // detail by categoryId
			List<Object> _result = jdbcHelper.executeSP(spName, params, CollectionCategory.class,
					MapCollectionCategory.class, AdPageSpot.class, Category.class);

			List<CollectionCategory> collectionCategoryList = (List<CollectionCategory>) _result.get(0);
			List<MapCollectionCategory> mapCollectionCategoryList = (List<MapCollectionCategory>) _result.get(1);
			List<AdPageSpot> adPageSpotist = (List<AdPageSpot>) _result.get(2);
			List<Category> categoryList = (List<Category>) _result.get(3);

			resultSet.setCollectionCategoryList(collectionCategoryList);
			resultSet.setMapCollectionCategoryList(mapCollectionCategoryList);
			resultSet.setAdPageSpotList(adPageSpotist);
			resultSet.setCategoryList(categoryList);
		}

		return resultSet;
	}

	/**
	 * 
	 * Get Category List
	 * 
	 * @since 2018. 9. 28.
	 * @author Sanghyup Kim
	 * @param GetCategoryListParameters
	 * @return GetCategoryListResponse
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED, transactionManager = "primaryTransactionManager")
	public GetCategoryListResponse getCategoryList(GetCategoryListParameters parameters) {

		List<Object> params = new ArrayList<Object>();

		params.add(parameters.getCategoryId());
		params.add(parameters.getExpandAll());
		String spName = "up_wa_GetCategoryList";

		List<Object> _result = jdbcHelper.executeSP(spName, params, CollectionCategory.class);
		List<CollectionCategory> collectionCategorylist = (List<CollectionCategory>) _result.get(0);

		GetCategoryListResponse resultSet = new GetCategoryListResponse();
		resultSet.setCategorylist(collectionCategorylist);

		return resultSet;
	}

	/**
	 * 
	 * set Collection Category Listorder
	 * 
	 * @since 2018. 10. 01.
	 * @author Sanghyup Kim
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, transactionManager = "primaryTransactionManager")
	public SetCollectionCategoryListorderResponse setCollectionCategoryListorder(
			SetCollectionCategoryListorderParameters parameters) {

		// set parameters
		int collectionCategoryID = parameters.getCategoryId();
		int parentCollectionCategoryID = parameters.getParentCategoryId();
		int listOrder = parameters.getListOrder();
		int lvl = parameters.getLvl();
		int newListOrder = listOrder;

		CollectionCategory2 collectionCategory = collectionCategory2Repository
				.findOneByCollectionCategoryID(collectionCategoryID);
		if (collectionCategory != null) {
			List<CollectionCategory2> collectionCategoryList = collectionCategory2Repository
					.findByParentCollectionCategoryIDAndLvlAndCollectionCategoryIDNotOrderByListOrderAsc(
							parentCollectionCategoryID, lvl, collectionCategoryID);

			for (CollectionCategory2 collectionCategory2 : collectionCategoryList) {
				if (collectionCategory2.getListOrder() >= listOrder) {
					newListOrder++;
					collectionCategory2.setListOrder(newListOrder);
					collectionCategory2Repository.save(collectionCategory2);
				}
			}

			/*
			 * collectionCategoryList.stream().filter(x -> x.getListOrder() >=
			 * listOrder).forEach(c -> { newListOrder++; c.setListOrder(newListOrder);
			 * collectionCategoryRepository.save(c);
			 * 
			 * });
			 */

			/*
			 * collectionCategoryList.stream().forEach(c -> { int n = listOrder; if
			 * (c.getListOrder() >= listOrder) { n++; c.setListOrder(n);
			 * collectionCategoryRepository.save(c); } });
			 */

			collectionCategory.setParentCollectionCategoryID(parentCollectionCategoryID);
			collectionCategory.setListOrder(listOrder);
			collectionCategory2Repository.save(collectionCategory);
		}

		List<CollectionCategory2> collectionCategoryList2 = collectionCategory2Repository
				.findByParentCollectionCategoryIDAndLvlOrderByListOrderAsc(parentCollectionCategoryID, lvl);

		SetCollectionCategoryListorderResponse resultSet = new SetCollectionCategoryListorderResponse();
		resultSet.setCategoryCollectionlist(collectionCategoryList2);
		return resultSet;
	}

	/**
	 * 
	 * set Collection Category Active
	 * 
	 * @since 2018. 10. 02.
	 * @author Sanghyup Kim
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, transactionManager = "primaryTransactionManager")
	public ResultResponse<Object> setCollectionCategoryActive(SetCollectionCategoryParameters parameters) {

		// set parameters
		CollectionCategory collectionCategory = parameters.getCollectionCategory();
		int collectionCategoryID = collectionCategory.getCollectionCategoryID();
		Boolean active = collectionCategory.getActive();

		ResultResponse<Object> result = new ResultResponse<Object>();
		if (active) {
			CollectionCategory collectionCategory2 = collectionCategoryRepository
					.findOneByCollectionCategoryID(collectionCategoryID);

			if (collectionCategory2 != null) {
				collectionCategory2.setActive(active);
				collectionCategoryRepository.save(collectionCategory2);

				result.setResultWrapper(true, 1, collectionCategoryID, "Changed successfully!", null);
			}
		} else { // inactive all of sub node items
			String spName = "up_wa_SetCollectionCategoryInactive";
			List<Object> params = new ArrayList<Object>();

			params.add(collectionCategoryID);

			List<Object> _result = jdbcHelper.executeSP(spName, params, Total.class);
			int affectedRows = (Integer) _result.get(0);

			result.setResultWrapper(true, affectedRows, collectionCategoryID, "Changed successfully!", null);
		}

		return result;
	}

	/**
	 * 
	 * set Collection Category Delete
	 * 
	 * @since 2018. 10. 02.
	 * @author Sanghyup Kim
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, transactionManager = "primaryTransactionManager")
	public ResultResponse<Object> setCollectionCategoryDelete(SetCollectionCategoryParameters parameters) {

		// set parameters
		CollectionCategory collectionCategory = parameters.getCollectionCategory();
		int collectionCategoryID = collectionCategory.getCollectionCategoryID();

		// deleteInBulkByCollectionCategoryID
		mapCollectionCategoryRepository.deleteInBulkByCollectionCategoryID(collectionCategoryID);
		// deleteByCollectionCategoryIDOrParentCollectionCategoryID
		collectionCategoryRepository.deleteByCollectionCategoryIDOrParentCollectionCategoryID(collectionCategoryID,
				collectionCategoryID);

		ResultResponse<Object> result = new ResultResponse<Object>();
		result.setResultWrapper(true, 1, collectionCategoryID, "Deleted successfully!", null);

		return result;
	}

	/**
	 * 
	 * set Collection Category
	 * 
	 * @since 2018. 10. 02.
	 * @author Sanghyup Kim
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, transactionManager = "primaryTransactionManager")
	public ResultResponse<Object> setCollectionCategory(SetCollectionCategoryParameters parameters, String setType) {

		// set parameters
		CollectionCategory collectionCategory = parameters.getCollectionCategory();

		ResultResponse<Object> result = new ResultResponse<Object>();

		switch (setType) {
		case "Add":
			CollectionCategory newCollectionCategory = collectionCategoryRepository.save(collectionCategory);

			result.setResultWrapper(true, 1, newCollectionCategory.getCollectionCategoryID(), "Added successfully!",
					null);
			break;

		case "Upd":
			int collectionCategoryID = collectionCategory.getCollectionCategoryID();
			CollectionCategory collectionCategory2 = collectionCategoryRepository
					.findOneByCollectionCategoryID(collectionCategoryID);

			if (collectionCategory2 != null) {
				collectionCategory2.setCollectionCategoryName(collectionCategory.getCollectionCategoryName());
				collectionCategory2.setParentCollectionCategoryID(collectionCategory.getParentCollectionCategoryID());
				collectionCategory2.setLvl(collectionCategory.getLvl());
				collectionCategory2.setListOrder(collectionCategory.getListOrder());
				collectionCategory2.setActive(collectionCategory.getActive());
				collectionCategory2.setSpotID(collectionCategory.getSpotID());
				collectionCategory2.setServiceInUse(collectionCategory.getServiceInUse());
				collectionCategory2.setVendorType(collectionCategory.getVendorType());
				collectionCategory2.setVendorTierGroup(collectionCategory.getVendorTierGroup());
				collectionCategory2.setOrderBy(collectionCategory.getOrderBy());
				collectionCategory2.setModifiedBy(collectionCategory.getModifiedBy());
				collectionCategory2.setModifiedOn(collectionCategory.getModifiedOn());

				collectionCategoryRepository.save(collectionCategory2);

				result.setResultWrapper(true, 1, collectionCategoryID, "Updated successfully!", null);
			}
			break;
		default:
			break;
		}

		return result;
	}

}
