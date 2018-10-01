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
import net.fashiongo.webadmin.model.pojo.response.GetCollectionCategoryListResponse;
import net.fashiongo.webadmin.model.pojo.response.SetCollectionCategoryListorderResponse;
import net.fashiongo.webadmin.dao.primary.CollectionCategory2Repository;
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
	private CollectionCategory2Repository collectionCategoryRepository;

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

		GetCollectionCategoryListResponse resultSet = new GetCollectionCategoryListResponse();
		String spName = "up_wa_GetCollectionCategory";
		List<Object> params = new ArrayList<Object>();

		// add parameters
		params.add(parameters.getCategoryId());
		params.add(parameters.getExpandAll());

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

		GetCategoryListResponse resultSet = new GetCategoryListResponse();
		String spName = "up_wa_GetCategoryList";
		List<Object> params = new ArrayList<Object>();

		params.add(parameters.getCategoryId());
		params.add(parameters.getExpandAll());

		List<Object> _result = jdbcHelper.executeSP(spName, params, CollectionCategory.class);
		List<CollectionCategory> collectionCategorylist = (List<CollectionCategory>) _result.get(0);

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

		CollectionCategory2 collectionCategory = collectionCategoryRepository
				.findOneByCollectionCategoryID(collectionCategoryID);
		if (collectionCategory != null) {
			List<CollectionCategory2> collectionCategoryList = collectionCategoryRepository
					.findByParentCollectionCategoryIDAndLvlAndCollectionCategoryIDNotOrderByListOrderAsc(
							parentCollectionCategoryID, lvl, collectionCategoryID);

			for (CollectionCategory2 collectionCategory2 : collectionCategoryList) {
				if (collectionCategory2.getListOrder() >= listOrder) {
					newListOrder++;
					collectionCategory2.setListOrder(newListOrder);
					collectionCategoryRepository.save(collectionCategory2);
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
			collectionCategoryRepository.save(collectionCategory);
		}

		List<CollectionCategory2> collectionCategoryList2 = collectionCategoryRepository
				.findByParentCollectionCategoryIDAndLvlOrderByListOrderAsc(parentCollectionCategoryID, lvl);

		SetCollectionCategoryListorderResponse resultSet = new SetCollectionCategoryListorderResponse();
		resultSet.setCategoryCollectionlist(collectionCategoryList2);
		return resultSet;
	}

}
