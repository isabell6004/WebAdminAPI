package net.fashiongo.webadmin.service;

import net.fashiongo.webadmin.dao.primary.*;
import net.fashiongo.webadmin.data.entity.primary.ProductsEntity;
import net.fashiongo.webadmin.data.entity.primary.VendorCategoryEntity;
import net.fashiongo.webadmin.data.repository.primary.ProductsEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.VendorCategoryEntityRepository;
import net.fashiongo.webadmin.model.pojo.ad.CollectionCategory;
import net.fashiongo.webadmin.model.pojo.ad.*;
import net.fashiongo.webadmin.model.pojo.ad.parameter.*;
import net.fashiongo.webadmin.model.pojo.ad.response.*;
import net.fashiongo.webadmin.model.pojo.common.ResultCode;
import net.fashiongo.webadmin.model.pojo.parameter.GetCategoryAdItemForBidVendorParameter;
import net.fashiongo.webadmin.model.pojo.response.GetCategoryAdItemForBidVendorResponse;
import net.fashiongo.webadmin.model.primary.*;
import net.fashiongo.webadmin.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AdService extends ApiService {
	
	@Autowired
	private AdPageRepository adPageRepository;
	@Autowired
	private AdPageSpotRepository adPageSpotRepository;
	@Autowired
	private AdVendorRepository adVendorRepository;
	@Autowired
	private CodeBodySizeRepository codeBodySizeRepository;
	@Autowired
	private CollectionCategoryItemRepository collectionCategoryItemRepository;
	@Autowired
	private MapAdVendorItemRepository mapAdVendorItemRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private CacheService cacheService;
	@Autowired
	private VendorCategoryEntityRepository vendorCategoryRepository;
	@Autowired
	private ProductsEntityRepository productsEntityRepository;

	/**
	 * 
	 * Set Add Page
	 * 
	 * @since 2018. 10. 05.
	 * @author Nayeon Kim
	 * @param SetAddPageParameter
	 * @return
	 */
	@Transactional(value = "primaryTransactionManager")
	public ResultCode setAdPage(SetAddPageParameter parameters) {
		AdPage adPage = new AdPage();
		Integer pageID = parameters.getPageID();
		String pageName = parameters.getPageName();
		
		if (pageID == null) { // new (insert)
			AdPage adPage2 = adPageRepository.findTopByOrderByPageIDDesc();
			if (adPage2 != null) {
				pageID = adPage2.getPageID() + 1;
				adPage.setPageID(pageID);
				adPage.setPageName(pageName);
				adPageRepository.save(adPage);
			}
		} else { // not null (update)
			AdPage adPage2 = adPageRepository.findOneByPageID(pageID);
			adPage2.setPageName(pageName);
			// adPage2.setPageUrl(pageUrl);
			adPageRepository.save(adPage2);
		}
		return new ResultCode(true, 1, MSG_SAVE_SUCCESS);
	}

	/**
	 * 
	 * Get Body Size Code
	 * 
	 * @since 2018. 10. 08.
	 * @author Nayeon Kim
	 * @return List<CodeBodySize>
	 */
	public List<CodeBodySize> getBodySizeCode() {
		List<CodeBodySize> result = codeBodySizeRepository.findAll();
		return result;
	}

	/**
	 * 
	 * Get Spot Check
	 * 
	 * @since 2018. 10. 08.
	 * @author Nayeon Kim
	 * @param spotID
	 * @return GetSpotCheckResponse
	 */
	public GetSpotCheckResponse getSpotCheck(Integer spotID) {
		GetSpotCheckResponse result = new GetSpotCheckResponse();
		AdVendor advendor = adVendorRepository.findTopBySpotID(spotID);
		
		if(advendor != null) {
			result.setSpotID(spotID);
		}
		return result;
	}

	/**
	 * 
	 * Delete Spot Setting
	 * 
	 * @since 2018. 10. 05.
	 * @author Nayeon Kim
	 * @param spotID
	 * @return
	 */
	@Transactional(value = "primaryTransactionManager")
	public ResultCode delSpotSetting(Integer spotID) {
		adPageSpotRepository.deleteById(spotID);
		return new ResultCode(true, 1, MSG_DELETE_SUCCESS);
	}
	
	/**
	 * 
	 * Set Add Spot Setting
	 * 
	 * @since 2018. 10. 05.
	 * @author Nayeon Kim
	 * @param SetAddSpotSettingParameter
	 * @return
	 */
	@Transactional(value = "primaryTransactionManager")
	public ResultCode setAddSpotSetting(SetAddSpotSettingParameter parameters) {
		AdPageSpot adPageSpot = new AdPageSpot();
		Integer spotID = parameters.getSpotID(); 
		LocalDateTime createdOn = LocalDateTime.now();
		LocalDateTime modifiedOn = createdOn;
		
		if(spotID == 0) { // new (insert)
			this.saveSpotSetting(adPageSpot, parameters);
			adPageSpot.setCreatedOn(createdOn);
			adPageSpot.setCreatedBy(Utility.getUsername());
			adPageSpotRepository.save(adPageSpot);
		} else { // update
			adPageSpot = adPageSpotRepository.findOneBySpotID(spotID);
			this.saveSpotSetting(adPageSpot, parameters);
			adPageSpot.setModifiedOn(modifiedOn);
			adPageSpot.setModifiedBy(Utility.getUsername());
			adPageSpotRepository.save(adPageSpot);
		}
		return new ResultCode(true, 1, MSG_SAVE_SUCCESS);
	}
	
	private void saveSpotSetting(AdPageSpot adPageSpot, SetAddSpotSettingParameter parameters) {
		adPageSpot.setPageID(parameters.getPageID());
		adPageSpot.setCategoryID(parameters.getCategoryID());
		adPageSpot.setBodySizeID(parameters.getBodySizeID());
		adPageSpot.setSpotName(parameters.getSpotName());
		adPageSpot.setPrice1(parameters.getPrice1());
		adPageSpot.setPrice2(parameters.getPrice2());
		adPageSpot.setPrice3(parameters.getPrice3());
		adPageSpot.setPrice4(parameters.getPrice4());
		adPageSpot.setPrice5(parameters.getPrice5());
		adPageSpot.setPrice6(parameters.getPrice6());
		adPageSpot.setPrice7(parameters.getPrice7());
		adPageSpot.setActive(parameters.getActive());
		adPageSpot.setIncludeVendorCategory(parameters.getIncludeVendorCategory());
		adPageSpot.setSpotInstanceCount(parameters.getSpotInstanceCount());
		adPageSpot.setMaxPurchasable(parameters.getMaxPurchasable());
		adPageSpot.setSpotItemCount(parameters.getSpotItemCount());
		adPageSpot.setBidEffectiveOn(parameters.getBidEffectiveOn());
	}
	
	/**
	 * 
	 * Get FG Category List Ad Info
	 * 
	 * @since 2019. 03. 29
	 * @author David Lee
	 * @param categoryDate
	 * @return GetCategoryAdCalendar
	 */
	public GetFGCategoryListAdCountResponse GetFGCategoryAdCount(GetFGCategoryListAdCountParameter parameters) {
		GetFGCategoryListAdCountResponse result = new GetFGCategoryListAdCountResponse();
		String spName = "up_wa_GetFGCategoryAdCalendar";
		List<Object> params = new ArrayList<Object>();
		
        params.add(parameters.getCategoryDate());
        params.add(parameters.getCategoryID());
        params.add(parameters.getLvl());
        params.add(parameters.getLastDate());

		List<Object> _result = jdbcHelper.executeSP(spName, params, FGListADCalendar.class);
		List<FGListADCalendar> AdCountList = (List<FGListADCalendar>) _result.get(0);
		result.setFgCalendarList(AdCountList);
		return result;
	}
	
	/**
	 * 
	 * Del Category Ad Item
	 * 
	 * @since 2018. 10. 24.
	 * @author Jiwon Kim
	 * @param ccitemid
	 * @return DelCategoryAdItem
	 */
	@Transactional(value = "primaryTransactionManager")
	public ResultCode DelCategoryAdItem(Integer CcItemID) {
		ResultCode result = new ResultCode(true, 1, MSG_DELETE_SUCCESS);
		collectionCategoryItemRepository.deleteById(CcItemID);
		return result;
	}
	
	/**
	 * 
	 * Get Category Ad Item For Bid Vendor
	 * 
	 * @since 2018. 10. 25.
	 * @author Jiwon Kim
	 * @param AdID
	 * @return GetCategoryAdItemForBidVendor
	 */
	public GetCategoryAdItemSearchResponse GetCategoryAdItemSearch(GetCategoryAdItemSearchParameter parameters) {
		GetCategoryAdItemSearchResponse result = new GetCategoryAdItemSearchResponse();
		String spName = "up_wa_GetCollectionCategoryItemsSearch";
		List<Object> params = new ArrayList<Object>();
        params.add(parameters.getPagenum());
        params.add(parameters.getPagesize());
        params.add(parameters.getCategoryIDs());
        params.add(parameters.getCollectionCategoryID());
        params.add(parameters.getVendorID());
        params.add(parameters.getSelectedCategoryID());
        params.add(parameters.getFromDate());
        params.add(parameters.getToDate());
        params.add(null);
        params.add(null);
        params.add(parameters.getBodySizeIDs());
        params.add(parameters.getPatternIDs());
        params.add(parameters.getLengthIDs());
        params.add(parameters.getStyleIDs());
        params.add(parameters.getFabricIDs());
        params.add(parameters.getColorNames());
        params.add(parameters.getFilter());
        params.add("ProductDescription");
        params.add(null);
        params.add(null);
        params.add(null);
        params.add(null);
        params.add(null);
        params.add(parameters.getOrderBy());
        params.add(parameters.getVendorOrderBy());
        params.add(null);
        params.add(null);
        params.add(parameters.getSearchAndOr());
        params.add(parameters.getKeyword());
        params.add(parameters.getStyleNo());
        params.add(parameters.getVendorDateFrom());
        params.add(parameters.getVendorDateTo());
        params.add(parameters.getNeverUsed());

		List<Object> _result = jdbcHelper.executeSP(spName, params, CategoryAdCount.class, SelectData.class, VendorCount.class, VendorData1.class, VendorData2.class);
		List<CategoryAdCount> count = (List<CategoryAdCount>) _result.get(0);
		List<SelectData> selectData = (List<SelectData>) _result.get(1);
		List<VendorCount> vendorCount = (List<VendorCount>) _result.get(2);
		List<VendorData1> vendorData1 = (List<VendorData1>) _result.get(3);
		List<VendorData2> vendorData2 = (List<VendorData2>) _result.get(4);

		result.setCount(count);
		result.setSelectData(selectData);
		result.setVendorCount(vendorCount);
		result.setVendorData1(vendorData1);
		result.setVendorData2(vendorData2);
		
		return result;
	}
	
	
	/**
	 * 
	 * Get Category Ad Item Search Vendor
	 * 
	 * @since 2018. 10. 29.
	 * @author Jiwon Kim
	 * @param GetCategoryAdItemSearchVendorParameter
	 * @return GetCategoryAdItemSearchVendor
	 */
	public GetCategoryAdItemSearchVendorResponse GetCategoryAdItemSearchVendor(GetCategoryAdItemSearchVendorParameter parameters) {
		GetCategoryAdItemSearchVendorResponse result = new GetCategoryAdItemSearchVendorResponse();
		String spName = "up_wa_GetCollectionCategoryItemsSearchVendor";
		List<Object> params = new ArrayList<Object>();
        params.add(parameters.getPagenum());
        params.add(parameters.getPagesize());
        params.add(parameters.getCategoryIDs());
        params.add(parameters.getCollectionCategoryID());
        params.add(parameters.getVendorID());
        params.add(parameters.getSelectedCategoryID());
        params.add(parameters.getFromDate());
        params.add(parameters.getToDate());
        params.add(null);
        params.add(null);
        params.add(parameters.getBodySizeIDs());
        params.add(parameters.getPatternIDs());
        params.add(parameters.getLengthIDs());
        params.add(parameters.getStyleIDs());
        params.add(parameters.getFabricIDs());
        params.add(parameters.getColorNames());
        params.add(parameters.getFilter());
        params.add("ProductDescription");
        params.add(null);
        params.add(null);
        params.add(null);
        params.add(null);
        params.add(null);
        params.add(null);        
        params.add(null);
        params.add(null);
        params.add(parameters.getSearchAndOr());
        params.add(parameters.getKeyword());
        params.add(null);

		List<Object> _result = jdbcHelper.executeSP(spName, params, CategoryAdCount.class, SelectData.class);
		List<CategoryAdCount> count = (List<CategoryAdCount>) _result.get(0);
		List<SelectData> selectData = (List<SelectData>) _result.get(1);

		result.setCount(count);
		result.setSelectData(selectData);
		
		return result;
	}
	
	public GetFGCategoryAdListResponse GetFGCategoryAdList(GetFGCategoryAdListParameter parameters) {
		GetFGCategoryAdListResponse result = new GetFGCategoryAdListResponse();
		String spName = "up_wa_GetFGCategoryAdList";
		List<Object> params = new ArrayList<Object>();
        params.add(parameters.getCategoryDate());
        params.add(parameters.getCategoryId());
		

        List<Object> _result = jdbcHelper.executeSP(spName, params, FGListADList.class);
		List<FGListADList> fgCategoryList = (List<FGListADList>) _result.get(0);
		
		result.setFgCategoryList(fgCategoryList);
		
		return result;
	}
	
	
	/**
	 * 
	 * Save Category Ad Item For Bid Vendor
	 * 
	 * @since 2018. 10. 31.
	 * @author Jiwon Kim
	 * @param SaveCategoryAdItemForBidVendorParameter
	 * @return SaveCategoryAdItemForBidVendor
	 */
	@Transactional(value = "primaryTransactionManager")
	public ResultCode SaveCategoryAdItemForBidVendor(SaveCategoryAdItemForBidVendorParameter parameters) {
		
		List<MapAdVendorItem> delMapAdVendorItemList = mapAdVendorItemRepository.findByAdID(parameters.getAdID());
		if (delMapAdVendorItemList.size()>0)
		{
			mapAdVendorItemRepository.deleteAll(delMapAdVendorItemList);
			AdVendor advendor = adVendorRepository.findByAdID(parameters.getAdID());
			advendor.setVendorCategoryID(parameters.getVendorCategoryID());
			adVendorRepository.save(advendor);
		}
		List<MapAdVendorItem> mvil = new ArrayList<MapAdVendorItem>();
		for (MapAdVendorItem mapAdVendorItem : parameters.getMapAdVendorItem()) {
			MapAdVendorItem mvi = new MapAdVendorItem();
			mvi.setAdID(mapAdVendorItem.getAdID());
			mvi.setProductID(mapAdVendorItem.getProductID());
			mvi.setListOrder(mapAdVendorItem.getListOrder());
			mvil.add(mvi);
		}
		mapAdVendorItemRepository.saveAll(mvil);

		//cacheService.resetCache("ListAd","#"+parameters.getAdID().toString());
		cacheService.GetRedisCacheEvict("ListAd","#"+parameters.getAdID().toString());
		
		
		return new ResultCode(true, 1, MSG_SAVE_SUCCESS);
	}
	
	/**
	 * 
	 * Set Category Ad Item
	 * 
	 * @since 2018. 11. 01.
	 * @author Jiwon Kim
	 * @param SetCategoryAdItemParameter
	 * @return SetCategoryAdItem
	 */
	@Transactional(value = "primaryTransactionManager")
	public ResultCode SetCategoryAdItem(SetCategoryAdItemParameter parameters) {
		LocalDateTime createdOn = LocalDateTime.now();
		
		CollectionCategoryItem cci = new CollectionCategoryItem();
		cci.setSpotID(parameters.getSpotID());
		cci.setFromDate(parameters.getFromDate());
		cci.setCollectionCategoryID(parameters.getCollectioncategoryid());
		cci.setProductID(parameters.getProductID());
		cci.setCreatedBy(Utility.getUsername());
		cci.setCreatedOn(createdOn);
		cci.setCollectionCategoryType(1);
		cci.setWholeSalerID(parameters.getWholesalerID());

		collectionCategoryItemRepository.save(cci);

		return new ResultCode(true, 1, MSG_SAVE_SUCCESS);
	}

	/**
	 * Get VendorCategory with Product Count
	 *
	 * @param wholesalerId
	 * @return AdVendorCategoryResponse
	 * @author Kevin Kwon
	 */
	@Transactional(value = "primaryTransactionManager")
	public List<AdVendorCategoryResponse> getBannerAdsListVendorCategory(Integer wholesalerId) {

		List<VendorCategoryEntity> vendorCategories = vendorCategoryRepository.findByWholeSalerIDAndActive(wholesalerId, Boolean.TRUE);
		Map<Integer, Long> productCount = productsEntityRepository.findByVendorCategoryEntityInAndActive(vendorCategories, Boolean.TRUE)
				.stream()
				.collect(
						Collectors.groupingBy(
								ProductsEntity::getVendorCategoryID,
								Collectors.counting()
						)
				);

		return vendorCategories.stream()
				.map(vendorCategory -> AdVendorCategoryResponse.of(vendorCategory, productCount.getOrDefault(vendorCategory.getVendorCategoryID(), 0L)))
				.collect(Collectors.toList());
	}
}
