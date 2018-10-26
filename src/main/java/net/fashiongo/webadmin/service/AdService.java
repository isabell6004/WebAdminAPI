package net.fashiongo.webadmin.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.fashiongo.webadmin.model.pojo.AdSettingSubList;
import net.fashiongo.webadmin.model.pojo.ResultCode;
import net.fashiongo.webadmin.model.pojo.parameter.SetAddPageParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetAddSpotSettingParameter;
import net.fashiongo.webadmin.common.Utility;
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
}
