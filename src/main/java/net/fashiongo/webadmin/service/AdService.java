package net.fashiongo.webadmin.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.fashiongo.webadmin.common.Utility;
import net.fashiongo.webadmin.dao.primary.AdPageRepository;
import net.fashiongo.webadmin.dao.primary.AdPageSpotRepository;
import net.fashiongo.webadmin.dao.primary.AdVendorRepository;
import net.fashiongo.webadmin.dao.primary.CodeBodySizeRepository;
import net.fashiongo.webadmin.dao.primary.CollectionCategoryItemRepository;
import net.fashiongo.webadmin.dao.primary.MapAdVendorItemRepository;
import net.fashiongo.webadmin.model.pojo.ad.AdSettingList;
import net.fashiongo.webadmin.model.pojo.ad.AdSettingSubList;
import net.fashiongo.webadmin.model.pojo.ad.BidList;
import net.fashiongo.webadmin.model.pojo.ad.BiddingList;
import net.fashiongo.webadmin.model.pojo.ad.BiddingList2;
import net.fashiongo.webadmin.model.pojo.ad.CategoryAdCount;
import net.fashiongo.webadmin.model.pojo.ad.CategoryList;
import net.fashiongo.webadmin.model.pojo.ad.CollectionCategory;
import net.fashiongo.webadmin.model.pojo.ad.CuratedBestList;
import net.fashiongo.webadmin.model.pojo.ad.CuratedList;
import net.fashiongo.webadmin.model.pojo.ad.SelectData;
import net.fashiongo.webadmin.model.pojo.ad.VendorCount;
import net.fashiongo.webadmin.model.pojo.ad.VendorData1;
import net.fashiongo.webadmin.model.pojo.ad.VendorData2;
import net.fashiongo.webadmin.model.pojo.ad.parameter.GetCategoryAdCalendarParameter;
import net.fashiongo.webadmin.model.pojo.ad.parameter.GetCategoryAdDetailParameter;
import net.fashiongo.webadmin.model.pojo.ad.parameter.GetCategoryAdItemSearchParameter;
import net.fashiongo.webadmin.model.pojo.ad.parameter.GetCategoryAdItemSearchVendorParameter;
import net.fashiongo.webadmin.model.pojo.ad.parameter.GetCategoryAdListParameter;
import net.fashiongo.webadmin.model.pojo.ad.parameter.SaveCategoryAdItemForBidVendorParameter;
import net.fashiongo.webadmin.model.pojo.ad.parameter.SetAddPageParameter;
import net.fashiongo.webadmin.model.pojo.ad.parameter.SetAddSpotSettingParameter;
import net.fashiongo.webadmin.model.pojo.ad.parameter.SetCategoryAdItemParameter;
import net.fashiongo.webadmin.model.pojo.ad.response.GetADSettingResponse;
import net.fashiongo.webadmin.model.pojo.ad.response.GetCategoryAdCalendarResponse;
import net.fashiongo.webadmin.model.pojo.ad.response.GetCategoryAdDetailResponse;
import net.fashiongo.webadmin.model.pojo.ad.response.GetCategoryAdItemSearchResponse;
import net.fashiongo.webadmin.model.pojo.ad.response.GetCategoryAdItemSearchVendorResponse;
import net.fashiongo.webadmin.model.pojo.ad.response.GetCategoryAdListResponse;
import net.fashiongo.webadmin.model.pojo.ad.response.GetSpotCheckResponse;
import net.fashiongo.webadmin.model.pojo.common.ResultCode;
import net.fashiongo.webadmin.model.pojo.parameter.GetCategoryAdItemForBidVendorParameter;
import net.fashiongo.webadmin.model.pojo.response.GetCategoryAdItemForBidVendorResponse;
import net.fashiongo.webadmin.model.primary.AdPage;
import net.fashiongo.webadmin.model.primary.AdPageSpot;
import net.fashiongo.webadmin.model.primary.AdVendor;
import net.fashiongo.webadmin.model.primary.CodeBodySize;
import net.fashiongo.webadmin.model.primary.CollectionCategoryItem;
import net.fashiongo.webadmin.model.primary.MapAdVendorItem;

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

	/**
	 * 
	 * Get AD Setting
	 * 
	 * @since 2018. 10. 02.
	 * @author Nayeon Kim
	 * @return GetADSettingResponse
	 */
	@SuppressWarnings("unchecked")
	public GetADSettingResponse getAdsetting() {
		GetADSettingResponse result = new GetADSettingResponse();
		String spName = "up_wa_GetAdSetting";
		
		List<Object> params = new ArrayList<Object>();
		List<Object> _result = jdbcHelper.executeSP(spName, params, AdSettingSubList.class, AdSettingList.class);
		result.setAdSettingSubList((List<AdSettingSubList>) _result.get(0));
		result.setAdSettingList((List<AdSettingList>) _result.get(1));
		return result;
	}

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
	 * Get Category Ad Calendar
	 * 
	 * @since 2018. 10. 23.
	 * @author Jiwon Kim
	 * @param categoryDate
	 * @return GetCategoryAdCalendar
	 */
	public GetCategoryAdCalendarResponse GetCategoryAdCalendar(GetCategoryAdCalendarParameter parameters) {
		GetCategoryAdCalendarResponse result = new GetCategoryAdCalendarResponse();
		String spName = "up_wa_GetCategoryAdCalendar2";
		List<Object> params = new ArrayList<Object>();

        params.add(parameters.getCategoryDate());

		List<Object> _result = jdbcHelper.executeSP(spName, params, CollectionCategory.class, BiddingList.class, CuratedList.class);
		List<CollectionCategory> collectionCategory = (List<CollectionCategory>) _result.get(0);
		List<BiddingList> biddingList = (List<BiddingList>) _result.get(1);
		List<CuratedList> curatedList = (List<CuratedList>) _result.get(2);
		
		result.setCollectionCategory(collectionCategory);
		result.setBiddingList(biddingList);
		result.setCuratedList(curatedList);

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
	 * Get Category Ad Detail
	 * 
	 * @since 2018. 10. 24.
	 * @author Jiwon Kim
	 * @param spotid,categorydate
	 * @return GetCategoryAdDetail
	 */
	public GetCategoryAdDetailResponse GetCategoryAdDetail(GetCategoryAdDetailParameter parameters) {
		GetCategoryAdDetailResponse result = new GetCategoryAdDetailResponse();
		String spName = "up_wa_GetCategoryAdDetail";
		List<Object> params = new ArrayList<Object>();
        params.add(parameters.getCategorydate());
        params.add(parameters.getSpotID());

		List<Object> _result = jdbcHelper.executeSP(spName, params, BiddingList2.class, CuratedBestList.class);
		List<BiddingList2> biddingList = (List<BiddingList2>) _result.get(0);
		List<CuratedBestList> curatedBestList = (List<CuratedBestList>) _result.get(1);
		
		result.setBiddingList(biddingList);
		result.setCuratedBestList(curatedBestList);

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
	public GetCategoryAdItemForBidVendorResponse GetCategoryAdItemForBidVendor(GetCategoryAdItemForBidVendorParameter parameters) {
		GetCategoryAdItemForBidVendorResponse result = new GetCategoryAdItemForBidVendorResponse();
		String spName = "up_wa_GetCategoryAdItemForBidVendor";
		List<Object> params = new ArrayList<Object>();
        params.add(parameters.getAdID());

		List<Object> _result = jdbcHelper.executeSP(spName, params, BidList.class);
		List<BidList> bidList = (List<BidList>) _result.get(0);
		result.setBidList(bidList);
		
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
	
	
	/**
	 * 
	 * Get Category Ad List
	 * 
	 * @since 2018. 10. 30.
	 * @author Jiwon Kim
	 * @param GetCategoryAdListParameter
	 * @return GetCategoryAdList
	 */
	public GetCategoryAdListResponse GetCategoryAdList(GetCategoryAdListParameter parameters) {
		GetCategoryAdListResponse result = new GetCategoryAdListResponse();
		String spName = "up_wa_GetCategoryAdList";
		List<Object> params = new ArrayList<Object>();
        params.add(parameters.getCategoryDate());

        List<Object> _result = jdbcHelper.executeSP(spName, params, CategoryList.class, BiddingList2.class, CuratedBestList.class);
		List<CategoryList> categoryList = (List<CategoryList>) _result.get(0);
		List<BiddingList2> biddingList = (List<BiddingList2>) _result.get(1);
		List<CuratedBestList> curatedBestList = (List<CuratedBestList>) _result.get(2);
		
		result.setCategoryList(categoryList);
		result.setBiddingList(biddingList);
		result.setCuratedBestList(curatedBestList);
		
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
	
	
	
}
