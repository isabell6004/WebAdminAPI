package net.fashiongo.webadmin.service;

import java.util.ArrayList;
//import java.util.Date;
import java.util.List;

//import org.apache.commons.lang3.time.FastDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Isolation;

import net.fashiongo.webadmin.model.pojo.common.ResultResponse;
import net.fashiongo.webadmin.model.pojo.parameter.GetCollectionCategoryListParameters;
import net.fashiongo.webadmin.model.pojo.parameter.SetCollectionCategoryParameters;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.SetCollectionCategoryListorderParameters;
import net.fashiongo.webadmin.model.pojo.sitemgmt.response.GetCollectionCategoryListResponse;
import net.fashiongo.webadmin.model.pojo.sitemgmt.response.SetCollectionCategoryListorderResponse;
import net.fashiongo.webadmin.dao.primary.CollectionCategory2Repository;
import net.fashiongo.webadmin.dao.primary.CollectionCategoryRepository;
import net.fashiongo.webadmin.dao.primary.MapCollectionCategoryRepository;
//import net.fashiongo.webadmin.model.pojo.Total;
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
public class SitemgmtCollectionCategoryService extends ApiService {

	@Autowired
	private CollectionCategoryRepository collectionCategoryRepository;

	@Autowired
	private CollectionCategory2Repository collectionCategory2Repository;

	@Autowired
	private MapCollectionCategoryRepository mapCollectionCategoryRepository;

	/**
	 * 
	 * set Collection Category Listorder
	 * 
	 * @since 2018. 10. 01.
	 * @author Sanghyup Kim
	 * @param SetCollectionCategoryListorderParameters
	 * @return SetCollectionCategoryListorderResponse
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, transactionManager = "primaryTransactionManager")
	public SetCollectionCategoryListorderResponse setCollectionCategoryListorder(
			SetCollectionCategoryListorderParameters parameters) {

		// set parameters
		final int collectionCategoryID = parameters.getCategoryId();
		final int parentCollectionCategoryID = parameters.getParentCategoryId();
		final int listOrder = parameters.getListOrder();
		final int lvl = parameters.getLvl();
		int newListOrder = listOrder;

		final CollectionCategory2 collectionCategory = collectionCategory2Repository
				.findOneByCollectionCategoryID(collectionCategoryID);
		if (collectionCategory != null) {
			final List<CollectionCategory2> collectionCategoryList = collectionCategory2Repository
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

		final List<CollectionCategory2> collectionCategoryList2 = collectionCategory2Repository
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
	 * @param SetCollectionCategoryParameters
	 * @return ResultResponse<Object>
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, transactionManager = "primaryTransactionManager")
	public ResultResponse<Integer> setCollectionCategoryActive(SetCollectionCategoryParameters parameters) {

		// set parameters
		final CollectionCategory collectionCategory = parameters.getCollectionCategory();
		final int collectionCategoryID = collectionCategory.getCollectionCategoryID();
		final Boolean active = collectionCategory.getActive();

		ResultResponse<Integer> result = new ResultResponse<Integer>();
		if (active) {
			CollectionCategory collectionCategory2 = collectionCategoryRepository
					.findOneByCollectionCategoryID(collectionCategoryID);

			if (collectionCategory2 != null) {
				collectionCategory2.setActive(active);
				collectionCategoryRepository.save(collectionCategory2);

				result.setResultWrapper(true, 1, collectionCategoryID, MSG_CHANGE_SUCCESS, 1);
			}
		} else { // inactive all of sub node items
			/*
			 * String spName = "up_wa_SetCollectionCategoryInactive"; List<Object> params =
			 * new ArrayList<Object>(); params.add(collectionCategoryID); List<Object>
			 * _result = jdbcHelper.executeSP(spName, params, Integer.class); // return []
			 */
			collectionCategoryRepository.upWaSetCollectionCategoryInactive(collectionCategoryID);
			int affectedRows = 1;
			result.setResultWrapper(true, affectedRows, collectionCategoryID, MSG_CHANGE_SUCCESS, 1);
		}

		return result;
	}

	/**
	 * 
	 * set Collection Category Delete
	 * 
	 * @since 2018. 10. 02.
	 * @author Sanghyup Kim
	 * @param SetCollectionCategoryParameters
	 * @return ResultResponse<Object>
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, transactionManager = "primaryTransactionManager")
	public ResultResponse<Integer> setCollectionCategoryDelete(SetCollectionCategoryParameters parameters) {

		// set parameters
		final CollectionCategory collectionCategory = parameters.getCollectionCategory();
		final int collectionCategoryID = collectionCategory.getCollectionCategoryID();

		// deleteInBulkByCollectionCategoryID
		mapCollectionCategoryRepository.deleteInBulkByCollectionCategoryID(collectionCategoryID);
		// deleteByCollectionCategoryIDOrParentCollectionCategoryID
		collectionCategoryRepository.deleteByCollectionCategoryIDOrParentCollectionCategoryID(collectionCategoryID,
				collectionCategoryID);

		ResultResponse<Integer> result = new ResultResponse<Integer>();
		result.setResultWrapper(true, 1, collectionCategoryID, MSG_DELETE_SUCCESS, null);

		return result;
	}

	/**
	 * 
	 * set Collection Category - insert/update
	 * 
	 * @since 2018. 10. 02.
	 * @author Sanghyup Kim
	 * @param SetCollectionCategoryParameters, setType
	 * @return ResultResponse<Object>
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, transactionManager = "primaryTransactionManager")
	public ResultResponse<Integer> setCollectionCategory(SetCollectionCategoryParameters parameters, String setType) {

		// set parameters
		final CollectionCategory collectionCategory = parameters.getCollectionCategory();
//		Date modifiedOn = new Date();
//		collectionCategory.setModifiedOn(modifiedOn);

		int collectionCategoryID = collectionCategory.getCollectionCategoryID();

		ResultResponse<Integer> result = new ResultResponse<Integer>();

		switch (setType) {
		case "Add":
			final CollectionCategory newCollectionCategory = collectionCategoryRepository.save(collectionCategory);
			// collectionCategoryID update
			collectionCategoryID = newCollectionCategory.getCollectionCategoryID();

			result.setResultWrapper(true, 1, collectionCategoryID, MSG_INSERT_SUCCESS, 1);
			break;

		case "Upd":
			CollectionCategory collectionCategory2 = collectionCategoryRepository
					.findOneByCollectionCategoryID(collectionCategoryID);

			if (collectionCategory2 != null) {
				// edit
				collectionCategory2.setCollectionCategoryName(collectionCategory.getCollectionCategoryName());
				collectionCategory2.setParentCollectionCategoryID(collectionCategory.getParentCollectionCategoryID());
				collectionCategory2.setLvl(collectionCategory.getLvl());
				collectionCategory2.setListOrder(collectionCategory.getListOrder());
				collectionCategory2.setActive(collectionCategory.getActive());
				collectionCategory2.setSpotID(collectionCategory.getSpotID());

				// auto selection policy
				collectionCategory2.setServiceInUse(collectionCategory.getServiceInUse());
				collectionCategory2.setVendorType(collectionCategory.getVendorType());
				collectionCategory2.setVendorTierGroup(collectionCategory.getVendorTierGroup());
				collectionCategory2.setOrderBy(collectionCategory.getOrderBy());

				collectionCategory2.setModifiedBy(collectionCategory.getModifiedBy());
				collectionCategory2.setModifiedOn(collectionCategory.getModifiedOn());

				collectionCategoryRepository.save(collectionCategory2);

				result.setResultWrapper(true, 1, collectionCategoryID, MSG_UPDATE_SUCCESS, 1);
			}
			break;
		default:
			break;
		}

		// update map_collection category items
		final List<MapCollectionCategory> mapCollectionCategory = parameters.getMapCollectionCategoryList();
		if (mapCollectionCategory != null) {
			mapCollectionCategoryRepository.deleteInBulkByCollectionCategoryID(collectionCategoryID);

			for (MapCollectionCategory mapCollectionCategory2 : mapCollectionCategory) {
				if (mapCollectionCategory2.getCollectionCategoryID() == 0) { // insert
					mapCollectionCategory2.setCollectionCategoryID(collectionCategoryID);
				}
				mapCollectionCategoryRepository.save(mapCollectionCategory2);
			}
			/*
			 * final int collectionCategoryID2 = collectionCategoryID;
			 * mapCollectionCategory.stream().forEach(x -> { if (x.getCollectionCategoryID()
			 * == 0) { // insert x.setCollectionCategoryID(collectionCategoryID2); }
			 * mapCollectionCategoryRepository.save(x); });
			 */
		}
		return result;
	}

}
