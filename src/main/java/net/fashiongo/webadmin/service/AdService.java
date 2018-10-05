package net.fashiongo.webadmin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.fashiongo.webadmin.model.pojo.AdSettingSubList;
import net.fashiongo.webadmin.model.pojo.ResultResponse;
import net.fashiongo.webadmin.model.pojo.parameter.DelSpotSettingParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetSpotCheckParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetAddPageParameter;
import net.fashiongo.webadmin.dao.primary.AdPageRepository;
import net.fashiongo.webadmin.dao.primary.AdPageSpotRepository;
import net.fashiongo.webadmin.model.pojo.AdSettingList;
import net.fashiongo.webadmin.model.pojo.response.GetADSettingResponse;
import net.fashiongo.webadmin.model.pojo.response.GetBodySizeCodeResponse;
import net.fashiongo.webadmin.model.pojo.response.GetSpotCheckResponse;
import net.fashiongo.webadmin.model.primary.AdPage;

@Service
public class AdService extends ApiService {
	
	@Autowired
	private AdPageRepository adPageRepository;
	@Autowired
	private AdPageSpotRepository adPageSpotRepository;

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
	//@Transactional
	public ResultResponse<Object> setAdPage(SetAddPageParameter parameters) {
		AdPage adPage = new AdPage();
		Integer pageID = parameters.getPageID();
		String pageName = parameters.getPageName();
		ResultResponse<Object> result = new ResultResponse<Object>(false,-1,0,"failure",null);

		if (pageID == null) { // new (insert)
			AdPage adPage2 = adPageRepository.findTopByOrderByPageIDDesc();
			if (adPage2 != null) {
				pageID = adPage2.getPageID() + 1;
				adPage.setPageID(pageID);
				adPage.setPageName(pageName);

				adPageRepository.save(adPage);
			}
		} else { // not null // update
			AdPage adPage2 = adPageRepository.findOneByPageID(pageID);
			adPage2.setPageName(pageName);
			// adPage2.setPageUrl(pageUrl);
			adPageRepository.save(adPage2);
		}

		result.setSuccess(true);
		result.setCode(1);
		result.setMessage(MSG_SAVE_SUCCESS);

		return result;
	}

	/**
	 * 
	 * Get Body Size Code
	 * 
	 * @since 2018. 10. 05.
	 * @author Nayeon Kim
	 * @return GetBodySizeCodeResponse
	 */
	public GetBodySizeCodeResponse getBodySizeList() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * Get Spot Check
	 * 
	 * @since 2018. 10. 05.
	 * @author Nayeon Kim
	 * @param GetSpotCheckParameter
	 * @return GetSpotCheckResponse
	 */
	public GetSpotCheckResponse getSpotCheck(GetSpotCheckParameter parameters) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * Delete Spot Setting
	 * 
	 * @since 2018. 10. 05.
	 * @author Nayeon Kim
	 * @param DelSpotSettingParameter
	 * @return
	 */
	//@Transactional
	public ResultResponse<Object> delSpotSetting(DelSpotSettingParameter parameters) {
		// AdPageSpot adPageSpot = new AdPageSpot();
		ResultResponse<Object> result = new ResultResponse<Object>(false,-1,0,"deletefailure",null);

		Integer spotID = parameters.getSpotID();
		adPageSpotRepository.deleteById(spotID);
		
		result.setSuccess(true);
		result.setCode(1);
		result.setMessage(MSG_DELETE_SUCCESS);

		return result;
	}
}
