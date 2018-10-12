package net.fashiongo.webadmin.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.fashiongo.webadmin.model.pojo.AdSettingSubList;
import net.fashiongo.webadmin.model.pojo.ResultCode;
import net.fashiongo.webadmin.model.pojo.parameter.GetSpotCheckParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetAddPageParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetAddSpotSettingParameter;
import net.fashiongo.webadmin.dao.primary.AdPageRepository;
import net.fashiongo.webadmin.dao.primary.AdPageSpotRepository;
import net.fashiongo.webadmin.dao.primary.AdVendorRepository;
import net.fashiongo.webadmin.dao.primary.CodeBodySizeRepository;
import net.fashiongo.webadmin.model.pojo.AdSettingList;
import net.fashiongo.webadmin.model.pojo.response.GetADSettingResponse;
import net.fashiongo.webadmin.model.pojo.response.GetSpotCheckResponse;
import net.fashiongo.webadmin.model.primary.AdPage;
import net.fashiongo.webadmin.model.primary.AdPageSpot;
import net.fashiongo.webadmin.model.primary.AdVendor;
import net.fashiongo.webadmin.model.primary.CodeBodySize;

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
		List<AdSettingSubList> adSettingSubList = (List<AdSettingSubList>) _result.get(0);
		List<AdSettingList> adSettingList = (List<AdSettingList>) _result.get(1);

		result.setAdSettingSubList(adSettingSubList);
		result.setAdSettingList(adSettingList);

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
		ResultCode result = new ResultCode(true, 1, "Saved successfully!");

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
		return result;
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
		List<CodeBodySize> codeBodySizeList = codeBodySizeRepository.findAll();
		return codeBodySizeList;
	}

	/**
	 * 
	 * Get Spot Check
	 * 
	 * @since 2018. 10. 08.
	 * @author Nayeon Kim
	 * @param GetSpotCheckParameter
	 * @return GetSpotCheckResponse
	 */
	public GetSpotCheckResponse getSpotCheck(GetSpotCheckParameter parameters) {
		GetSpotCheckResponse result = new GetSpotCheckResponse();
		Integer spotID = parameters.getSpotID();
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
	public ResultCode delSpotSetting(int spotID) {
		ResultCode result = new ResultCode(true, 0, "Deleted successfully!");

		adPageSpotRepository.deleteById(spotID);

		return result;
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
		ResultCode result = new ResultCode(true, 0, "Saved successfully!");
		
		AdPageSpot adPageSpot = new AdPageSpot();
		Integer spotID = parameters.getSpotID();
		Integer pageID = parameters.getPageID();
		Integer categoryID = parameters.getCategoryID();
		Integer bodySizeID = parameters.getBodySizeID();
		String spotName = parameters.getSpotName();
		BigDecimal price1 = parameters.getPrice1();
		BigDecimal price2 = parameters.getPrice2();
		BigDecimal price3 = parameters.getPrice3();
		BigDecimal price4 = parameters.getPrice4();
		BigDecimal price5 = parameters.getPrice5();
		BigDecimal price6 = parameters.getPrice6();
		BigDecimal price7 = parameters.getPrice7();
		Boolean active = parameters.getActive();
		Boolean includeVendorCategory = parameters.getIncludeVendorCategory();
		Integer spotInstanceCount = parameters.getSpotInstanceCount();
		Integer maxPurchasable = parameters.getMaxPurchasable();
		Integer spotItemCount = parameters.getSpotItemCount();
		//LocalDateTime bidEffectiveOn = parameters.getBidEffectiveOn();	
		
//		Calendar createdOn = Calendar.getInstance();
//		LocalDateTime createdOn = LocalDateTime.now();
		Date createdOn = new Date();
//		birthDateWithoutTime = DateUtils.truncate(birthDateWithTime, Calendar.DATE);

		//String createdBy =  Utility.getUsername();
//		LocalDateTime modifiedOn = LocalDateTime.now();
		Date modifiedOn = createdOn;
		//String modifiedBy =  Utility.getUsername();
		
		if(spotID == 0) { // new (insert)
			adPageSpot.setPageID(pageID);
			adPageSpot.setPageID(categoryID);
			adPageSpot.setPageID(bodySizeID);
			adPageSpot.setSpotName(spotName);
			adPageSpot.setPrice1(price1);
			adPageSpot.setPrice2(price2);
			adPageSpot.setPrice3(price3);
			adPageSpot.setPrice4(price4);
			adPageSpot.setPrice5(price5);
			adPageSpot.setPrice6(price6);
			adPageSpot.setPrice7(price7);
			adPageSpot.setActive(active);
			adPageSpot.setIncludeVendorCategory(includeVendorCategory);
			adPageSpot.setSpotInstanceCount(spotInstanceCount);
			adPageSpot.setMaxPurchasable(maxPurchasable);
			adPageSpot.setSpotItemCount(spotItemCount);
			//adPageSpot.setBidEffectiveOn(bidEffectiveOn);
			adPageSpot.setCreatedOn(createdOn);
			//adPageSpot.setCreatedBy(createdBy);
			
			adPageSpotRepository.save(adPageSpot);
			
		} else { // update
			adPageSpotRepository.findOneBySpotID(spotID);
			
			adPageSpot.setPageID(pageID);
			adPageSpot.setPageID(categoryID);
			adPageSpot.setPageID(bodySizeID);
			adPageSpot.setSpotName(spotName);
			adPageSpot.setPrice1(price1);
			adPageSpot.setPrice2(price2);
			adPageSpot.setPrice3(price3);
			adPageSpot.setPrice4(price4);
			adPageSpot.setPrice5(price5);
			adPageSpot.setPrice6(price6);
			adPageSpot.setPrice7(price7);
			adPageSpot.setActive(active);
			adPageSpot.setIncludeVendorCategory(includeVendorCategory);
			adPageSpot.setSpotInstanceCount(spotInstanceCount);
			adPageSpot.setMaxPurchasable(maxPurchasable);
			adPageSpot.setSpotItemCount(spotItemCount);
			//adPageSpot.setBidEffectiveOn(bidEffectiveOn);
			adPageSpot.setModifiedOn(modifiedOn);
			//adPageSpot.setModifiedBy(modifiedBy);
			
			adPageSpotRepository.save(adPageSpot);
		}
		
		return result;
	}
}
